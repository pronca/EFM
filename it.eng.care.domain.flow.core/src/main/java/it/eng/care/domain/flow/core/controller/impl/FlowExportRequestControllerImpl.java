package it.eng.care.domain.flow.core.controller.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import it.eng.care.platform.tool.security.annotation.NoAuthenticationRequired;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.statemachine.state.State;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.Files;

import it.eng.care.domain.flow.b2b.model.LockedMessageDTO;
import it.eng.care.domain.flow.b2b.model.ProcessSingleFlowResult;
import it.eng.care.domain.flow.b2b.service.JsonFlowService;
import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.controller.FlowExportRequestController;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowExportRequestDOtoFlowExportRequestDO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowExportRequestDOtoFlowExportRequestDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowExportRequestDTOtoFlowExportRequestDO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDOtoJobTalendDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDTOtoJobTalendDO;
import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.dao.FlowExportReqFileTalendDAO;
import it.eng.care.domain.flow.core.dao.FlowExportRequestDAO;
import it.eng.care.domain.flow.core.dto.CheckExtractResultDTO;
import it.eng.care.domain.flow.core.dto.FlowExportRequestDTO;
import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.ProfiloFlussiDTO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;
import it.eng.care.domain.flow.core.entity.FlowExportMessageDetailsDO;
import it.eng.care.domain.flow.core.entity.FlowExportReqFileTalendDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.enumeration.MachineEvent;
import it.eng.care.domain.flow.core.enumeration.StateSendRegionEnum;
import it.eng.care.domain.flow.core.service.BrickLayerInvokeService;
import it.eng.care.domain.flow.core.service.FlowDrgService;
import it.eng.care.domain.flow.core.service.FlowExportRequestService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.TalendThreadService;
import it.eng.care.domain.flow.core.spring.statemachine.StateMachineFlow;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.webservice.model.SimulateFlow;
import it.eng.care.domain.flow.webservice.service.WebService;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.FlussiFile;
import it.eng.care.platform.authentication.api.model.bean.LoggedUser;
import it.eng.care.platform.authentication.api.model.dto.OrganizationDTO;
import it.eng.care.platform.authentication.api.model.dto.RoleDTO;
import it.eng.care.platform.authentication.api.model.dto.SiteDTO;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/FlowExportDTO")
public class FlowExportRequestControllerImpl implements FlowExportRequestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlowExportRequestControllerImpl.class);

	@Autowired
	private LoggedUserService loggUserService;
	@Autowired
	private FlowExportRequestService flowExportService;

	@Autowired
	private ConversionService conversionService;

	private MachineEvent machineEvent;
	@Autowired(required = false)
	private StateMachineFlow stateMachineFlow;

	@Autowired
	private FlowExportRequestDOtoFlowExportRequestDO flowExportRequestDOtoFlowExportRequestDOConverter;

	@Autowired
	private FlowExportRequestDOtoFlowExportRequestDTO flowExportRequestDOtoFlowExportRequestDTOConverter;

	@Autowired
	private FlowExportRequestDTOtoFlowExportRequestDO flowExportRequestDTOtoFlowExportRequestDOConverter;

	@Autowired
	private JobTalendDTOtoJobTalendDO jobTalendDTOtoJobTalendDO;
	@Autowired
	private JobTalendDOtoJobTalendDTO jobTalendDOtoJobTalendDTO;

	@Autowired
	private FlowExportReqFileTalendDAO flowExportReqFileTalendDAO;

	@Autowired
	private FlowExportRequestDAO flowExportDAO;
	
	@Autowired
    private ConfigurationDAO configuration;

	@Autowired
	private FlowManagerProfileService flowManagerProfileService;

	@Autowired
	private TalendThreadService talendThreadService;

	@Autowired
	private WebService webService;

	@Autowired
	private BrickLayerInvokeService brickLayerInvokeService;
    
    @Autowired
	private FlowDrgService flowDrgService;
    
    @Autowired
    private LogAccessiPMConfig logAccessiPMConfig;
    
    @Autowired
	private LoggedUserService loggedUserService;
    	
    @Autowired
	protected JsonFlowService jsonFlowService;

	@PostConstruct
	public void post() {
		conversionService.registerConverter(FlowExportRequestDO.class, FlowExportRequestDO.class,
				flowExportRequestDOtoFlowExportRequestDOConverter);
		conversionService.registerConverter(FlowExportRequestDO.class, FlowExportRequestDTO.class,
				flowExportRequestDOtoFlowExportRequestDTOConverter);
		conversionService.registerConverter(FlowExportRequestDTO.class, FlowExportRequestDO.class,
				flowExportRequestDTOtoFlowExportRequestDOConverter);
		conversionService.registerConverter(JobTalendDO.class, JobTalendDTO.class, jobTalendDOtoJobTalendDTO);
		conversionService.registerConverter(JobTalendDTO.class, JobTalendDO.class, jobTalendDTOtoJobTalendDO);
	}

	@Override
	@PostMapping("/_search")
	@ResponseBody
	public SearchOperationResult<FlowExportRequestDTO> search(@RequestBody BaseSearchInput searchInput) {
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		searchInput.setParam("aziende", aziende);
		
		Pair<List<FlowExportRequestDO>, SearchInfo> searchResults = flowExportService.retrieveAllFiltered(searchInput,
				true);
		
		List<FlowExportRequestDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(),
				FlowExportRequestDTO.class);
		List<FlowExportRequestDTO> dtos2 = new ArrayList<>();
				
		LoggedUser currentUser = loggedUserService.getCurrentUser();
		if(currentUser != null) {
			OrganizationDTO organization = currentUser.getCurrentOrganization();
			SiteDTO site = currentUser.getCurrentSite();
			RoleDTO role = currentUser.getCurrentRole();
			
			for (FlowExportRequestDTO flowExportRequestDTO : dtos) {
				if (flowExportRequestDTO.getFlow() != null && flowExportRequestDTO.getFlow().getProfiloFlussi() != null && !flowExportRequestDTO.getFlow().getProfiloFlussi().isEmpty()) {
					Set<ProfiloFlussiDTO> lprofiloFlussiDTO = new HashSet<>();
					if (organization != null) {
						lprofiloFlussiDTO=flowExportRequestDTO.getFlow().getProfiloFlussi().stream().filter(tr -> tr.getOrganization().equals(organization.getCode())).collect(Collectors.toCollection(HashSet::new));
						flowExportRequestDTO.getFlow().setProfiloFlussi(lprofiloFlussiDTO);
					}
					if (site != null) {
						lprofiloFlussiDTO=flowExportRequestDTO.getFlow().getProfiloFlussi().stream().filter(tr -> tr.getSite().equals(site.getCode())).collect(Collectors.toCollection(HashSet::new));
						flowExportRequestDTO.getFlow().setProfiloFlussi(lprofiloFlussiDTO);
					}
					if (role != null) {
						lprofiloFlussiDTO=flowExportRequestDTO.getFlow().getProfiloFlussi().stream().filter(tr -> tr.getRole().equals(role.getCode())).collect(Collectors.toCollection(HashSet::new));
						flowExportRequestDTO.getFlow().setProfiloFlussi(lprofiloFlussiDTO);
					}
					flowExportRequestDTO.getFlow().setProfiloFlussi(lprofiloFlussiDTO);
					dtos2.add(flowExportRequestDTO);
				}
			}
		}
		
		return SearchOperationResult.success(dtos2, searchResults.getSecond());
	}

	@Override
	@PostMapping("/save")
	@ResponseBody
	public SaveOperationResult<FlowExportRequestDTO> save(@RequestBody FlowExportRequestDTO flowExportDTO) {
		
		FlowExportRequestDO flowExportRequestDO = conversionService.convertTo(flowExportDTO, FlowExportRequestDO.class);
		
		if (!flowManagerProfileService.checkFlowById(flowExportRequestDO.getFlow().getId())) {
			return null;
		}
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		if (!aziende.isEmpty()) {
			flowExportRequestDO.setAziendeProfiloFlussi(String.join(",",aziende));
		}
				
		if (flowExportRequestDO.getId() == null || flowExportRequestDO.getId().trim().equals("")) {
			flowExportRequestDO = flowExportService.createEntity(flowExportRequestDO);
		} else {
			flowExportRequestDO = flowExportService.updateEntity(flowExportRequestDO);
		}

		FlowExportRequestDTO flowExportRequestDTOresult = conversionService.convertTo(flowExportRequestDO,
				FlowExportRequestDTO.class);

		return SaveOperationResult.success(flowExportRequestDTOresult);
	}

	@Override
	@PostMapping("/delete")
	@ResponseBody
	@Transactional
	public OperationResult<Boolean> delete(@RequestBody List<FlowExportRequestDTO> flowExportRequestDTOS) {

	    List<FlowExportRequestDO> dosToDelete = new ArrayList<>();

	    // recupero entità reali dal DB e filtro per profilo utente
	    for (FlowExportRequestDTO dto : flowExportRequestDTOS) {
	        if (dto == null || dto.getId() == null || dto.getId().isBlank()) {
	            continue;
	        }

	        FlowExportRequestDO flowExportRequestDO = flowExportService.retriveOne(dto.getId());
	        if (flowExportRequestDO == null) {
	            continue;
	        }

	        if (!flowManagerProfileService.checkFlowById(flowExportRequestDO.getFlow().getId())) {
	            continue;
	        }

	        dosToDelete.add(flowExportRequestDO);
	    }

	    // cancellazione logica
	    flowExportService.logicDelete(dosToDelete);

	    // reinvio eventuali pratiche parcheggiate in FM_LOCKED_MESSAGE
	    for (FlowExportRequestDO flowExportRequestDO : dosToDelete) {
	        String flowId = flowExportRequestDO.getFlow() != null ? flowExportRequestDO.getFlow().getId() : null;
	        String extrId = flowExportRequestDO.getId();
	        String versionName = null;

	        if (flowExportRequestDO.getVersion() != null && flowExportRequestDO.getVersion().getVersion() != null) {
	            versionName = flowExportRequestDO.getVersion().getVersion();
	        }

	        List<LockedMessageDTO> lockedMessages = jsonFlowService.findPendingLockedMessages(flowId, extrId);

	        for (LockedMessageDTO lockedMessage : lockedMessages) {
	            try {
	                ProcessSingleFlowResult replayResult = jsonFlowService.processSingleFlow(
	                        lockedMessage.getMessage(),
	                        lockedMessage.getFlowName(),
	                        versionName,
	                        null
	                );

	                if (replayResult != null && replayResult.isSuccess() && replayResult.getExtractionId() != null) {
	                    jsonFlowService.markLockedMessageProcessed(
	                            lockedMessage.getId(),
	                            replayResult.getExtractionId()
	                    );
	                } else {
	                    LOGGER.warn("Riprocessamento non riuscito per locked message flowId={} keyMessage={}",
	                            lockedMessage.getFlowId(), lockedMessage.getKeyMessage());
	                }

	            } catch (Exception e) {
	                LogUtil.logException(LOGGER,
	                        "Errore nel riprocessamento del messaggio bloccato key=" + lockedMessage.getKeyMessage(), e);
	            }
	        }
	    }

	    return OperationResult.success(true);
	}
	
	@Override
	@PostMapping("/start")
	public SaveOperationResult<FlowExportRequestDTO> startExtraction(@RequestBody FlowExportRequestDTO flowExportDTO) {
		return startExtractionIn(flowExportDTO, true);
	}

	@Override
	public SaveOperationResult<FlowExportRequestDTO> startExtractionIn(FlowExportRequestDTO flowExportDTO,
			Boolean checkFlow) {
		FlowExportRequestDO flowExportRequestDO = flowExportService.retriveOne(flowExportDTO.getId());

		FlowExportRequestDTO flowExportRequestDTO = new FlowExportRequestDTO();

		if (checkFlow && !flowManagerProfileService.checkFlowById(flowExportRequestDO.getFlow().getId())) {
			return null;
		}

		Boolean res = true;
		String status = flowExportRequestDO.getStatus();
		Set<FlowExportMessageDetailsDO> errorsDO = new HashSet<FlowExportMessageDetailsDO>();
		try {
			State<String, String> stateExtraction = flowExportService.executeStateMachine(flowExportRequestDO.getId(),
					"EXPORT", machineEvent.ESEGUI_EXPORT.getEvent());
			String state = stateExtraction.getId();

			if (!status.equals(state)) {

				flowExportRequestDO.setStatus(state);
				flowExportService.updateEntityRow(flowExportRequestDO);
//				websocketServer.broadcastFlowExtractRequest("update");
				if (brickLayerInvokeService.existMicroService()) {
					res = brickLayerInvokeService.invokeExternalService(flowExportRequestDO.getId());
				} else {
					res = flowExportService.jobTalendJar(flowExportRequestDO.getId());
				}

			} else {
				// PASSAGGIO DI STATO NON PERMESSO
				res = false;
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
			// ERRORE
			try {
				State<String, String> stateExtraction = stateMachineFlow.execute(flowExportDTO.getId(), "EXPORT",
						MachineEvent.EXPORT_FAILED.getEvent());
				String state = stateExtraction.getId();
				flowExportRequestDO.setStatus(state);
				flowExportService.updateEntityRow(flowExportRequestDO);
				FlowExportMessageDetailsDO error = new FlowExportMessageDetailsDO();
				error.setErrorValue("Errore durante l'esecuzione del jar");
				error.setFlowExportRequest(flowExportRequestDO);
				errorsDO.add(error);
				flowExportRequestDO.setFlowExportMessageDetails(errorsDO);
				talendThreadService.save(flowExportRequestDO);
			} catch (Exception e1) {
//				e1.printStackTrace();
				LogUtil.logException(LOGGER, "", e1);
			}
			res = false;
		}

		if (res) {
			//ricarico l'oggetto flowExportRequestDO salvato nella procedura flowExportService.jobTalendJar tramite thread per il binding con il FE
			flowExportRequestDO = flowExportService.retriveOne(flowExportDTO.getId());

			//carico solo i dati utili al FE per il bindings e aggiornare i dati in tempo reale sulla riga estrazione flussi di riferimento
			flowExportRequestDTO.setId(flowExportRequestDO.getId());
			flowExportRequestDTO.setConsolidata(flowExportRequestDO.getConsolidata());
			flowExportRequestDTO.setRecord(flowExportRequestDO.getRecord());
			flowExportRequestDTO.setStatus(flowExportRequestDO.getStatus());
			flowExportRequestDTO.setEndExtractionDate(flowExportRequestDO.getEndExtractionDate());
			flowExportRequestDTO.setStartExtractionDate(flowExportRequestDO.getStartExtractionDate());
			flowExportRequestDTO.setValidationStatus(flowExportRequestDO.getValidationStatus());
			flowExportRequestDTO.setValidationStatusDrl(flowExportRequestDO.getValidationStatusDrl());
			flowExportRequestDTO.setRegionValidationStatus(flowExportRequestDO.getRegionValidationStatus());
			
			return SaveOperationResult.success(flowExportRequestDTO);
		} else {
			return SaveOperationResult.failure();
		}
	}

	@Override
	@PostMapping("/pingServiceExtraction")
	@ResponseBody
	public OperationResult<List<FlowExportRequestDTO>> pingServiceExtraction(
			@RequestBody List<FlowExportRequestDTO> flowExportDTOList) {

		List<FlowExportRequestDO> flowExportDOList = new ArrayList<FlowExportRequestDO>();
		for (FlowExportRequestDTO flowExportDTO : flowExportDTOList) {
			if ("IN_CORSO".equals(flowExportDTO.getStatus()) || "RICHIESTA".equals(flowExportDTO.getStatus())) {
				FlowExportRequestDO flowExportDO = flowExportService.retriveOne(flowExportDTO.getId());
				if (!flowExportDO.getStatus().equals(flowExportDTO.getStatus())) {
					flowExportDOList.add(flowExportDO);
				}
			}
		}
		List<FlowExportRequestDTO> dtos = conversionService.convertAllTo(flowExportDOList, FlowExportRequestDTO.class);
		if (dtos.size() > 0) {
			return OperationResult.success(dtos);
		} else {
			return OperationResult.failure();
		}

	}

	@Override
	@PostMapping("/getFields")
	@ResponseBody
	public OperationResult<HashMap<String, Object>> getFlowTableFieldByFlowAndVersion(
			@RequestBody BaseSearchInput searchInput) {

		String versionId = (String) searchInput.getValue("version");
		String flowId = (String) searchInput.getValue("flusso");

		HashMap<String, Object> result = flowExportService.getFieldsByFlowAndVersion(versionId, flowId);

		return OperationResult.success(result);
	}

	@Override
	@PostMapping(value = "/_downloadXml", produces = "application/zip", consumes = "text/plain")
	@NoAuthenticationRequired
	@ResponseBody
	public HttpEntity<byte[]> downloadXML(@RequestBody String exportId) throws IOException {

		HttpHeaders header = new HttpHeaders();
		header.set("Content-Disposition", "attachment;");

		FlowExportRequestDO example = new FlowExportRequestDO();
		example.setId(exportId);

		List<FlowExportReqFileTalendDO> exports = flowExportReqFileTalendDAO.findAllByflowExportRequest(example);
		List<File> files = new ArrayList<>();
		exports.forEach((FlowExportReqFileTalendDO export) -> {
			try {
				String fileName = "";
				if (flowManagerProfileService.checkFlowById(export.getFlowExportRequest().getFlow().getId())) {
					if (export.getXml() != null) {
						byte[] byt = export.getXml();
						if (export.getNomeFile() != null) {
							fileName = export.getNomeFile();
						} else {
							fileName = export.getFlowExportRequest().getFlow().getName() + "_" + exports.indexOf(export) + (export.getSezApp()!=null ? "_" + export.getSezApp() : "");
						}
						File file = File.createTempFile(fileName + "_", export.getFileExt());
						File file2 = new File(file.getParent()+"\\"+fileName + (export.getFileExt()!=null ? export.getFileExt() : ".txt"));
						file.renameTo(file2);
						Files.write(byt, file2);
						files.add(file2);
					}
				}
			} catch (IOException e) {
				LogUtil.logException(LOGGER, "", e);
//				e.printStackTrace();
			}
		});

		if (files != null && files.size() > 0) {
			String[] fileNames = new String[files.size()];
			int i = 0;
			for (File file : files) {
				fileNames[i] = file.getName();
				i++;
			}

			byte[] byt = FileUtility.compressingFiles(files.get(0).getParent(),
					files.get(0).getParent() + "/extraction.zip", fileNames);
			
			if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogDownXMLEstrazFlusso())) {
	    		byte[] resultsLogAccessi = flowExportService.sendAuditDownXMLEstrFlussiToPM(exportId, byt);
	        }

			return new HttpEntity<byte[]>(byt, header);
		}
		return null;

	}

	@Override
	@PostMapping(value = "/_downloadLog", produces = "application/zip", consumes = "text/plain")
	@NoAuthenticationRequired
	@ResponseBody
	public HttpEntity<byte[]> downloadLOG(@RequestBody String exportId) throws IOException {
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Disposition", "attachment;");

		FlowExportRequestDO example = new FlowExportRequestDO();
		example.setId(exportId);

		List<FlowExportReqFileTalendDO> exports = flowExportReqFileTalendDAO.findAllByflowExportRequest(example);
		List<File> files = new ArrayList<>();
		exports.forEach((FlowExportReqFileTalendDO export) -> {
			try {
				if (flowManagerProfileService.checkFlowById(export.getFlowExportRequest().getFlow().getId())) {
					if (export.getLog() != null) {
						String fileName = export.getFlowExportRequest().getFlow().getName() + "_" + exports.indexOf(export) + (export.getSezApp()!=null ? "_" + export.getSezApp() : "");
						byte[] byt = export.getLog();
						File file = File.createTempFile(fileName + "_", ".txt");
						File file2 = new File(file.getParent()+"\\"+fileName + ".txt");
						file.renameTo(file2);
						Files.write(byt, file2);
						files.add(file2);
					}
				}
			} catch (IOException e) {
				LogUtil.logException(LOGGER, "", e);
//				e.printStackTrace();
			}
		});

		if (files != null && files.size() > 0) {
			String[] fileNames = new String[files.size()];
			int i = 0;
			for (File file : files) {
				fileNames[i] = file.getName();
				i++;
			}

			byte[] byt = FileUtility.compressingFiles(files.get(0).getParent(),
					files.get(0).getParent() + "/extraction.zip", fileNames);
			
			if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogDownLOGEstrazFlusso())) {
	    		byte[] resultsLogAccessi = flowExportService.sendAuditDownLOGEstrFlussiToPM(exportId, byt);
	        }
	    	
			return new HttpEntity<byte[]>(byt, header);
		}
		return null;

	}

	@Override
	public SearchOperationResult<FlowExportRequestDTO> count(BaseSearchInput searchInput) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@PostMapping("/checkExtractObsoleta")
	@Transactional
	public OperationResult<CheckExtractResultDTO> checkExtractObsoleta(@RequestBody FlowExportRequestDTO flowExportDTO) throws SQLException {

	    if (!flowManagerProfileService.checkFlowById(flowExportDTO.getFlow().getId())) {
	        return null;
	    }

	    CheckExtractResultDTO result = new CheckExtractResultDTO();

	    List<Object[]> checkList = flowExportService.checkExtractObsoleta(flowExportDTO);
	    if (!checkList.isEmpty()) {
	        result.setCheckType("DISALLINEATA");
	        result.setItems(checkList);
	        return OperationResult.success(result);
	    }

	    checkList = flowExportService.checkExtractGiaConsolidataInviata(flowExportDTO);
	    if (!checkList.isEmpty()) {
	        result.setCheckType("INVIATA");
	        result.setItems(checkList);
	        return OperationResult.success(result);
	    }

	    ConfigurationDO validazioneRegionaleCfg = configuration.findByKeyId("UPLOAD_RESULT_REG_" + flowExportDTO.getFlow().getCode());
	    StateSendRegionEnum stato =
	            (validazioneRegionaleCfg == null || "true".equalsIgnoreCase(validazioneRegionaleCfg.getValue()))
	                    ? StateSendRegionEnum.INVIATA
	                    : StateSendRegionEnum.ACCETTATA;
	    
	    // se devo usare i ritorni regionali, mi assicuro prima che la tabella esista
        if (StateSendRegionEnum.INVIATA.equals(stato)) {
            try {
				flowExportService.ensureRegionErrorTableExists(flowExportDTO);
			} catch (Exception e) {
				LogUtil.logException(LOGGER, "", e);
			}
        }
	    
	    Integer pratiche = flowExportService.consolidaPratiche(flowExportDTO, stato.name());
	    if (pratiche == -1) {
	        return OperationResult.failure();
	    } else {
	        FlowExportRequestDO flowExportRequestDO = flowExportService.retriveOne(flowExportDTO.getId());
	        flowExportRequestDO.setConsolidata(true);
	        flowExportRequestDO.setDateCons(new Date());
	        flowExportRequestDO.setUserCons(loggUserService.getCurrentUser().getUsername());
	        flowExportDAO.save(flowExportRequestDO);

	        result.setCheckType(null);
	        result.setItems(new ArrayList<>());
	        return OperationResult.success(result);
	    }
	}
	
	/**
	 * @author mpirozzi Sconsolida pratiche serve ad appunto sconsolidare le
	 *         pratiche precedentemente consolidate.
	 */

	@Override
	@PostMapping("/sconsolidaPratiche")
	@Transactional
	public OperationResult<String> sconsolidaPratiche(@RequestBody FlowExportRequestDTO flowExportDTO) {

		if (!flowManagerProfileService.checkFlowById(flowExportDTO.getFlow().getId())) {
			return null;
		}

		// MP: utilizzo il metodo consolida che in realtà è generalizzato per entrambe
		// le funzionalità
		Integer pratiche = flowExportService.consolidaPratiche(flowExportDTO, StateSendRegionEnum.DA_INVIARE.name());
		if (pratiche == -1) {
			return OperationResult.failure();
		} else {
			// spostare queste istruzioni nel service quando non ritorna -1 ed eliminare
			// @Transactional
			FlowExportRequestDO flowExportRequestDO = flowExportService.retriveOne(flowExportDTO.getId());
			flowExportRequestDO.setConsolidata(false);
			flowExportRequestDO.setDateCons(null);
			flowExportRequestDO.setUserCons(null);
			// FlowExportRequestDO flowExportRequestDO =
			// conversionService.convertTo(flowExportDTO, FlowExportRequestDO.class);
			flowExportDAO.save(flowExportRequestDO);
			// flowExportRequestDO = flowExportService.updateEntity(flowExportRequestDO);
			
			//controllo se ci sono pratiche sbloccate e parcheggiate nella tabella FM_LOCKED_MESSAGE da reinviare	
			String flowId = flowExportRequestDO.getFlow() != null ? flowExportRequestDO.getFlow().getId() : null;
			String extrId = flowExportRequestDO.getId();
			String versionName = null;

			if (flowExportRequestDO.getVersion() != null && flowExportRequestDO.getVersion().getVersion() != null) {
				versionName = flowExportRequestDO.getVersion().getVersion();
			}

			List<LockedMessageDTO> lockedMessages = jsonFlowService.findPendingLockedMessages(flowId, extrId);

			for (LockedMessageDTO lockedMessage : lockedMessages) {
				try {
					ProcessSingleFlowResult replayResult = jsonFlowService.processSingleFlow(
							lockedMessage.getMessage(),
							lockedMessage.getFlowName(),
							versionName,
							null
					);

					if (replayResult != null && replayResult.isSuccess() && replayResult.getExtractionId() != null) {
						jsonFlowService.markLockedMessageProcessed(
						        lockedMessage.getId(),
						        replayResult.getExtractionId()
						);
					} else {
						LOGGER.warn("Riprocessamento non riuscito per locked message flowId={} keyMessage={}",
								lockedMessage.getFlowId(), lockedMessage.getKeyMessage());
					}

				} catch (Exception e) {
					LogUtil.logException(LOGGER,
							"Errore nel riprocessamento del messaggio bloccato key=" + lockedMessage.getKeyMessage(), e);
				}
			}
			
			return OperationResult.success("sconsolidamento");

		}
	}

	@Override
	@PostMapping("/killExtraction")
	public OperationResult<String> killExtraction(@RequestBody String exportId) {

		String id = exportId.replace("\"", "");

		boolean result = false;

		try {
			if (brickLayerInvokeService.existMicroService()) {
				result = brickLayerInvokeService.invokeExternalServiceKillJob(id);
			} else {
				result = flowExportService.killJob(id);
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

		if (result) {
			return OperationResult.success(exportId);
		} else {
			return OperationResult.failure();
		}

	}

	// calcolo drg: cambiare nome servizio

	@Override
	@PostMapping("/_drg")
	@ResponseBody
	public OperationResult<String> simulateFlowDrg(@RequestBody SimulateFlow sim) throws IOException {

		Object[] respResult = webService.loadAndSimulateFlowOnRegion(sim.getExportId(), sim.getFlusso(), true);
		FlussiFile[] result = (FlussiFile[])respResult[0];
		if (result == null) {
			if(respResult[1] != null) {
				return OperationResult.failure((String)respResult[1]);
			}else {
				return OperationResult.failure("Operazione Fallita");				
			}
		} else {

			return OperationResult.success("Operazione effettuata con successo");
		}

	}

	@Override
	@PostMapping(value = "/_downloadSimulation", produces = "application/zip", consumes = "application/json")
	@NoAuthenticationRequired
	@ResponseBody
	public HttpEntity<byte[]> downloadSimulation(@RequestBody SimulateFlow sim) throws IOException {

		Object[] respResult = webService.loadAndSimulateFlowOnRegion(sim.getExportId(), sim.getFlusso(), false);
		FlussiFile[] result = (FlussiFile[])respResult[0];
		
		HttpHeaders header = new HttpHeaders();
		header.set("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, filename, erroreRegionale");
		header.set("Access-Control-Expose-Headers", "filename, erroreRegionale");
		String fileName = sim.getExportId() + "_" + sim.getFlusso() + "_SIMULAZIONEFLUSSO.zip";
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename(fileName)
                .build();
        header.setContentDisposition(contentDisposition);
		if(result == null) {
			String error = (String)respResult[1];
			header.set("filename", "ERRORE");
			header.set("erroreRegionale", error);
			byte[] bytes = error.getBytes();			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ZipOutputStream zos = new ZipOutputStream(baos);
	        ZipEntry entry = new ZipEntry("ERRORS.txt");
	        entry.setSize(bytes.length);
	        zos.putNextEntry(entry);
	        zos.write(bytes);
	        zos.closeEntry();
	        zos.close();
	        byte[] zipbytes = baos.toByteArray();
			
			return new HttpEntity<byte[]>(zipbytes,header);
		}else {
			header.set("filename", "simulazioneFlusso");
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ZipOutputStream zos = new ZipOutputStream(baos);
	    for (int i = 0; i < result.length; i++) {
	    	byte[] byt = result[i].getFile();
		    ZipEntry entry = new ZipEntry(result[i].getNomefileOriginale());
		    entry.setSize(byt.length);
		    zos.putNextEntry(entry);
		    zos.write(byt);

	    }
	    
	    zos.closeEntry();
	    zos.close();
	
		return new HttpEntity<byte[]>(baos.toByteArray(),header);

	}
	
	@Override
	public void createDrgRequest(String requestId) {
		FlowExportRequestDO request = flowExportDAO.findById(requestId).orElse(null);

		if(request.isDrg()) {
		
			flowDrgService.createDrgInvocationResults(requestId);

		}
		
	}
	

}
