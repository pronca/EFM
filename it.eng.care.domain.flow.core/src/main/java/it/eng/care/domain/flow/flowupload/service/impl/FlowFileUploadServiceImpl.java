package it.eng.care.domain.flow.flowupload.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.sql.DataSource;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.statemachine.state.State;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.common.io.Files;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import it.eng.care.domain.flow.core.auditLog.CaricamentoFileUploadConverter;
import it.eng.care.domain.flow.core.auditLog.FileDownloadConverter;
import it.eng.care.domain.flow.core.auditLog.FileDownloadLogConverter;
import it.eng.care.domain.flow.core.converter.Flow.FlowDTOtoFlowDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableFieldDTOtoFlowTableFieldDO;
import it.eng.care.domain.flow.core.converter.Version.VersionDTOtoVersionDO;
import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.dao.VersionDAO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;
import it.eng.care.domain.flow.core.enumeration.MachineEvent;
import it.eng.care.domain.flow.core.enumeration.StateReceviedAppEnum;
import it.eng.care.domain.flow.core.enumeration.StateSendRegionEnum;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FmSequenceService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.QueryGenerator;
import it.eng.care.domain.flow.core.spring.statemachine.StateMachineFlow;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequest;
import it.eng.care.domain.flow.flowupload.bean.WellFormedStatusEnum;
import it.eng.care.domain.flow.flowupload.dao.FlowFileUploadRequestDAO;
import it.eng.care.domain.flow.flowupload.dao.FlowFileUploadRequestErrorDAO;
import it.eng.care.domain.flow.flowupload.dao.FlowSectionFileDAO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestErrorDO;
import it.eng.care.domain.flow.flowupload.model.FlowSectionFileDO;
import it.eng.care.domain.flow.flowupload.model.SectionFileDO;
import it.eng.care.domain.flow.flowupload.service.FlowFileUploadService;
import it.eng.care.domain.flow.flowupload.service.WellFormedService;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.platform.audit.api.model.privacymanager.annotation.PrivacyManagerLog;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventActionEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventCategoryEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityTypeEnum;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.common.dozer.converter.DozerConverter;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

@Transactional(propagation = Propagation.REQUIRED)
public class FlowFileUploadServiceImpl implements FlowFileUploadService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowFileUploadServiceImpl.class);

    @Autowired
    private DataSource dataSource;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ConfigurationDAO configuration;
	
	@Autowired
	private FlowFileUploadRequestErrorDAO flowFileUploadRequestErrorDAO;
	
	@Autowired
	private FlowSectionFileDAO flowSectionFileDAO;
	
	@Autowired
	private VersionDAO versionDAO;
	
	@Autowired
	private LoggedUserService userService;
	
	@Autowired
	private WellFormedService wellFormedService;
	
	@Autowired
	private FormFlowService formFlowService;
	
	@Autowired
	private FlowFileUploadRequestDAO flowFileUploadRequestDAO;
	
	@Autowired
	private DozerConverter mapper;
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private FlowDTOtoFlowDO flowDTOtoFlowDOConverver;

	@Autowired
	private VersionDTOtoVersionDO versionDTOtoVersionDOConverter;
	
	private QueryGenerator queryGenerator = new QueryGenerator();
	
	@Autowired(required = false)
    private StateMachineFlow stateMachineFlow;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@Autowired
    private FormFlowTableFieldDTOtoFlowTableFieldDO formFlowTableFieldDTOtoFlowTableFieldDO;
	
    @Autowired
    private FmSequenceService sequenceService;
    
	private MachineEvent machineEvent;
    
	@PostConstruct
	public void post() {
	conversionService.registerConverter(FlowDTO.class, FlowDO.class,
			flowDTOtoFlowDOConverver);
	conversionService.registerConverter(VersionDTO.class, VersionDO.class,
			versionDTOtoVersionDOConverter);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<FlowFileUploadRequestDO> getRequestsToValidate() {
		FlowFileUploadRequestDO flowFileUpload = new FlowFileUploadRequestDO();
		flowFileUpload.setStatus(WellFormedStatusEnum.WELL_FORMED.name());
	    List<FlowFileUploadRequestDO> result = new ArrayList<>();
	    List<FlowFileUploadRequestDO> list = flowFileUploadRequestDAO.findAll(Example.of(flowFileUpload));

	    for (FlowFileUploadRequestDO req : list) {
	        try {
	            //blocca la riga per evitare doppia esecuzione
	            req.setStatus(WellFormedStatusEnum.IN_IMPORT.name());
	            flowFileUploadRequestDAO.save(req);
	            result.add(req);
	        } catch (Exception ex) {
	        	LogUtil.logException(LOGGER, "Impossibile bloccare richiesta "+req.getId()+" per validazione", ex);
	        }
	    }
	    return result;
	}

	@Override
	public FlowOperationResult<String> upload(FlowFileUploadRequest request) {
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		
		if(request.getFlow() == null) {
			return FlowOperationResult.failure("Selezionare un flusso");
		}
		
		if(request.getVersion() == null) {
			return FlowOperationResult.failure("Selezionare una versione");
		}
		
		String requestId = request.getId();
		FlowFileUploadRequestDO requestDO = null;
		if(requestId == null || requestId.isEmpty()) {
//			requestId = UUID.randomUUID().toString();
			try {
				requestId = sequenceService.next(FlowFileUploadRequestDO.class).toString();
			} catch (Exception e) {
				requestId = UUID.randomUUID().toString();
			}
			requestDO = new FlowFileUploadRequestDO();
			requestDO.setId(requestId);
//			requestDO.setCreationDate(new Date());
			requestDO.setCreationDate(new Timestamp(new Date().getTime()));
			State<String, String> state = stateMachineFlow.createStateMachine(requestId + "_UPLOAD", "UPLOAD");
			if (state==null) {
				return FlowOperationResult.failure("Errore nella creazione dello state machine");
			}
			requestDO.setStatus(state.getId());
			requestDO.setUsername(userService.getCurrentUser().getUsername());
			
			try {
				requestDO.setFlow(conversionService.convertTo(request.getFlow(), FlowDO.class));
	        } catch (Exception e) {
	        	requestDO.setFlow(null);
	        }
	        try {
	        	requestDO.setVersion(conversionService.convertTo(request.getVersion(), VersionDO.class));
	        } catch (Exception e) {
	        	requestDO.setVersion(null);
	        }
			
			if (!aziende.isEmpty()) {
				requestDO.setAziendeProfiloFlussi(String.join(",",aziende));
//				requestDO.setAziendeLoadedInFile(String.join(",",aziende));
			}
			
			flowFileUploadRequestDAO.save(requestDO);
		} else {
		
			requestDO = flowFileUploadRequestDAO.getOne(requestId);
			
			if (!aziende.isEmpty()) {
				requestDO.setAziendeProfiloFlussi(String.join(",",aziende));
//				requestDO.setAziendeLoadedInFile(String.join(",",aziende));
			}
			
			// se i file sono ben formati allora non è possibile caricare altri file perchè l'operazione potrebbe influire su un eventuale processo di validazione in corso
			if(requestDO.getStatus().equals(WellFormedStatusEnum.WELL_FORMED.name()) || requestDO.getStatus().equals(WellFormedStatusEnum.IN_IMPORT.name())) {
				return FlowOperationResult.failure("Non e' possibile caricare altri file su una richiesta ben formata");
			}
			
			// il caricamento di un file ripristina la richiesta allo stato di elaborazione iniziale
			if(requestDO.getStatus().equals(WellFormedStatusEnum.INCOMPLETE.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(request.getId() + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_INCOMPLETO.getEvent());
					requestDO.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
			if(requestDO.getStatus().equals(WellFormedStatusEnum.NOT_WELL_FORMED.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(request.getId() + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_NOT_WELL_FORMED.getEvent());
					requestDO.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
			if(requestDO.getStatus().equals(WellFormedStatusEnum.VALIDATED.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(request.getId() + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_VALIDATED.getEvent());
					requestDO.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}

			// il caricamento di un file imposta la data e l'utente riguardanti l'ultima operazione ?
			
			if(requestDO.getErrors() != null) {
				requestDO.getErrors().clear();
			} else {
				requestDO.setErrors(new ArrayList<FlowFileUploadRequestErrorDO>());
			}	
			
		}
		
		return FlowOperationResult.success(requestId);
	}
	
	@Override
	public FlowOperationResult<Boolean> uploadFile(String requestId, byte[] bytes, int section, String extension) {
		
		FlowFileUploadRequestDO requestDO = flowFileUploadRequestDAO.getOne(requestId);
		
		if(requestDO.getStatus().equals(WellFormedStatusEnum.WELL_FORMED.name()) || requestDO.getStatus().equals(WellFormedStatusEnum.IN_IMPORT.name())) {
			return FlowOperationResult.failure("Non e' possibile caricare altri file su una richiesta ben formata");
		}
		
		// il caricamento di una sezione comporta la cancellazione di tutti i record dell'estrazione
		if(requestDO.getStatus().equals(WellFormedStatusEnum.VALIDATED.name()) || requestDO.getStatus().equals(WellFormedStatusEnum.VALIDATION_ERROR.name())) {
			try {
				deleteRecordsByExtractionId(retrieveConfiguration(requestDO.getFlow().getId(), requestDO.getVersion().getId()), requestId);
			} catch (SQLException e) {
				LogUtil.logException(LOGGER, "ERRORE CANCELLAZIONE RECORD DI EXTRACTION_ID "+ requestId +"", e);
//				e.printStackTrace();
				return FlowOperationResult.failure("Errore in fase di cancellazione dei record gia' associati alla richiesta di caricamento file");
			}
		}
		
		// il caricamento di un file ripristina la richiesta allo stato di elaborazione iniziale
		if(!WellFormedStatusEnum.LOADED.name().equals(requestDO.getStatus())) {
			if(requestDO.getStatus().equals(WellFormedStatusEnum.INCOMPLETE.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(requestId + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_INCOMPLETO.getEvent());
					requestDO.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
			if(requestDO.getStatus().equals(WellFormedStatusEnum.NOT_WELL_FORMED.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(requestId + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_NOT_WELL_FORMED.getEvent());
					requestDO.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
			if(requestDO.getStatus().equals(WellFormedStatusEnum.VALIDATED.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(requestId + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_VALIDATED.getEvent());
					requestDO.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
		}
		if(requestDO.getErrors() != null) {
			requestDO.getErrors().clear();
		} else {
			requestDO.setErrors(new ArrayList<FlowFileUploadRequestErrorDO>());
		}
		
		
		boolean founded = false;
		List<FlowSectionFileDO> sectionList = requestDO.getFiles();
		for(FlowSectionFileDO flowSectionFile : sectionList) {
			if(flowSectionFile.getSection() == section) {
				founded = true;
				flowSectionFile.setUploadDate(new Date());
				flowSectionFile.setUploadUsername(userService.getCurrentUser().getUsername());
				if(flowSectionFile.getSectionFileList().isEmpty()) {
					SectionFileDO file = new SectionFileDO();
					file.setId(UUID.randomUUID().toString());
					file.setFile(bytes);
					flowSectionFile.getSectionFileList().add(file);				
				} else {
					flowSectionFile.getSectionFileList().get(0).setFile(bytes);
					flowSectionFile.getSectionFileList().get(0).setWellFormedFile(null);
				}
				break;
			}
		}
		
		if(sectionList == null || sectionList.isEmpty() || !founded) {
			FlowSectionFileDO flowSectionFile = new FlowSectionFileDO();
			flowSectionFile.setId(UUID.randomUUID().toString());
			flowSectionFile.setRequest(requestDO);
			flowSectionFile.setSection(section);
			flowSectionFile.setUploadUsername(userService.getCurrentUser().getUsername());
			flowSectionFile.setUploadDate(new Date());
			flowSectionFile.setSectionFileList(new ArrayList<SectionFileDO>());
			
			SectionFileDO file = new SectionFileDO();
			file.setId(UUID.randomUUID().toString());
			file.setFile(bytes);
			file.setSection(flowSectionFile);
			file.setExtension(extension != null ? extension.toUpperCase() : null);
			flowSectionFile.getSectionFileList().add(file);	
			//founded.setFile(flowSectionFileDTO.getFile());
			flowSectionFile.setRequest(requestDO);
			
			if(requestDO.getFiles() == null) {
				requestDO.setFiles(new ArrayList<FlowSectionFileDO>());
			}
			requestDO.getFiles().add(flowSectionFile);
		}
		
		for(FlowSectionFileDO file : requestDO.getFiles()) {
			if(!file.getSectionFileList().isEmpty()) {
				file.getSectionFileList().get(0).setWellFormedFile(null);
			}
		}
		
		return FlowOperationResult.success();
	}
	
	@Override
	public WellFormedStatusEnum checkUploadedFiles(String requestId) throws IOException, SQLException {
		FlowFileUploadRequestDO request = flowFileUploadRequestDAO.getOne(requestId);
		WellFormedStatusEnum result = WellFormedStatusEnum.valueOf(request.getStatus());
		
		if(result.equals(WellFormedStatusEnum.LOADED) || result.equals(WellFormedStatusEnum.INCOMPLETE)) {
			result = wellFormedService.checkRequiredAndEmptySections(request);
		}
		
		request = flowFileUploadRequestDAO.getOne(requestId);
		if(result.equals(WellFormedStatusEnum.COMPLETE)) {
			result = wellFormedService.checkFiles(request);
		}
		
		return result;
		
	}
	
	@Override
	public FlowOperationResult<Boolean> deleteFile(String fileId) {
		FlowSectionFileDO file = flowSectionFileDAO.getOne(fileId);
		FlowFileUploadRequestDO request = file.getRequest();
		WellFormedStatusEnum result = WellFormedStatusEnum.valueOf(request.getStatus());
		
		// se i file sono ben formati allora non è possibile cancellarli perchè l'operazione potrebbe influire su un eventuale processo di validazione in corso
		if(result.equals(WellFormedStatusEnum.WELL_FORMED) || result.equals(WellFormedStatusEnum.IN_IMPORT)) {
			return FlowOperationResult.failure("Non è possibile cancellare un file ben formato");
		}
		
		// il caricamento di una sezione comporta la cancellazione di tutti i record dell'estrazione
		if(request.getStatus().equals(WellFormedStatusEnum.VALIDATED.name()) || request.getStatus().equals(WellFormedStatusEnum.VALIDATION_ERROR.name())) {
			try {
				deleteRecordsByExtractionId(retrieveConfiguration(request.getFlow().getId(), request.getVersion().getId()), request.getId());
			} catch (SQLException e) {
				LogUtil.logException(LOGGER, "ERRORE CANCELLAZIONE RECORD DI EXTRACTION_ID "+request.getId()+"", e);
//				e.printStackTrace();
				return FlowOperationResult.failure("Errore in fase di cancellazione dei record gia' associati alla richiesta di caricamento file");
			}
		}
		
		if(!WellFormedStatusEnum.LOADED.name().equals(request.getStatus())) {
			if(request.getStatus().equals(WellFormedStatusEnum.INCOMPLETE.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(request.getId() + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_INCOMPLETO.getEvent());
					request.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
			if(request.getStatus().equals(WellFormedStatusEnum.NOT_WELL_FORMED.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(request.getId() + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_NOT_WELL_FORMED.getEvent());
					request.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
			if(request.getStatus().equals(WellFormedStatusEnum.VALIDATED.name())) {
				try {
					State<String, String> stateUpload = stateMachineFlow.execute(request.getId() + "_UPLOAD", "UPLOAD", MachineEvent.UPLOAD_RICARICATO_FROM_VALIDATED.getEvent());
					request.setStatus(stateUpload.getId());
				} catch (Exception e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}
			}
		}
		
		flowSectionFileDAO.deleteById(fileId);
		request.getErrors().clear();
		
		return FlowOperationResult.success();
	}
	
	@Override
	public FlowOperationResult<Boolean> deleteRequest(String id) {
		FlowFileUploadRequestDO request = flowFileUploadRequestDAO.getOne(id);
		
		if (request == null) {
			LOGGER.error(">>>>>>>>>>>>>>>>> ERRORE IN FASE DI CANCELLAZIONE RECORD DELLA RICHIESTA DI ESTRAZIONE " + id);
			return FlowOperationResult.failure("ERRORE IN FASE DI CANCELLAZIONE RECORD DELLA RICHIESTA DI ESTRAZIONE");
		}
				
		if (request.getStatus() != null) {
			WellFormedStatusEnum result = WellFormedStatusEnum.valueOf(request.getStatus());
			// se i file sono ben formati allora non è possibile cancellarli perchè l'operazione potrebbe influire su un eventuale processo di validazione in corso
			if(result.equals(WellFormedStatusEnum.WELL_FORMED) || result.equals(WellFormedStatusEnum.IN_IMPORT)) {
				return FlowOperationResult.failure("Non è possibile cancellare un file ben formato");
			}
		}
		
		try {
			deleteRecordsByExtractionId(retrieveConfiguration(request.getFlow().getId(), request.getVersion().getId()), request.getId());
		} catch (SQLException e) {
			LogUtil.logException(LOGGER, "ERRORE IN FASE DI CANCELLAZIONE RECORD DELLA RICHIESTA DI ESTRAZIONE "+request.getId()+"", e);
//			e.printStackTrace();
			return FlowOperationResult.failure("ERRORE IN FASE DI CANCELLAZIONE RECORD DELLA RICHIESTA DI ESTRAZIONE");
		}
		
		flowFileUploadRequestDAO.delete(request);
		
		return FlowOperationResult.success();
	}
		
	@Override
    public Pair<List<FlowFileUploadRequestDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {

        int limit = searchInput.getParam("limit");
        int offset = searchInput.getParam("offset");
        Pageable page =  PageRequest.of((offset / limit), limit);

        Long totalItems = flowFileUploadRequestDAO.countFlowExportByFilter(searchInput);

        List<FlowFileUploadRequestDO> loList = flowFileUploadRequestDAO.cerca(searchInput, page);
        return Pair.of(loList, SearchInfos.create(totalItems));

    }
	
	@Override
	public List<SectionFileDO> getFlowSectionFile(FlowFileUploadRequest flowUpload) {

	    String requestId = flowUpload.getId();

	    List<FlowSectionFileDO> flowSectionFiles =
	            flowSectionFileDAO.findAllByRequest_Id(requestId);

	    List<SectionFileDO> sectionList = new ArrayList<>();
	    for (FlowSectionFileDO flowSectionFile : flowSectionFiles) {
	        List<SectionFileDO> lst = flowSectionFile.getSectionFileList();
	        if (lst != null) {
	            sectionList.addAll(lst);
	        }
	    }
	    return sectionList;
	}

	
	@Override
	public List<String> getFlowErrors(FlowFileUploadRequest flowUpload) {
		
		List<String> errors = new ArrayList<String>();
		FlowFileUploadRequestDO request = mapper.convert(flowUpload, FlowFileUploadRequestDO.class);
		List<FlowFileUploadRequestErrorDO> flowErrors = flowFileUploadRequestErrorDAO.findByRequest(request);
		for(FlowFileUploadRequestErrorDO flowError: flowErrors) {
			errors.add(flowError.getError());

		}
		
		return errors;
		
	}

	@Override
	public FlowOperationResult<Boolean> checkSectionFile(String requestId, int section) {
		FlowFileUploadRequestDO request = new FlowFileUploadRequestDO();
		request.setId(requestId);
		FlowOperationResult<Boolean> result = new FlowOperationResult<Boolean>();
		FlowSectionFileDO flowSectionFile = flowSectionFileDAO.findByRequestAndSection(request, section);
		if(flowSectionFile == null) {
			result.setSuccess(false);
			return result;
		}else {
			result = deleteFile(flowSectionFile.getId());
			result.setSuccess(true);
			return result;
		}
	}
	
	@Override
	public List<FlowFileUploadRequestErrorDO> checkIncompleteErrors(FlowFileUploadRequest flowUpload) {
		
		FlowFileUploadRequestDO request = mapper.convert(flowUpload, FlowFileUploadRequestDO.class);
		List<FlowFileUploadRequestErrorDO> errorList = flowFileUploadRequestErrorDAO.findByRequest(request);
		
		return errorList;
	}
	
	/**
	 * Crea una mappa colonna -> campo in base a skipColumnName
	 */
	private Map<Integer, FormFlowTableFieldDTO> buildColumnMapping(
	        List<FormFlowTableFieldDTO> fields,
	        String[] headers,
	        boolean skipColumnName) {

	    Map<Integer, FormFlowTableFieldDTO> mapping = new HashMap<>();

	    if (skipColumnName) {
	        // mapping posizionale (usa getPosition)
	        fields.stream()
	              .filter(f -> f.getPosition() != null)
	              .sorted(Comparator.comparing(FormFlowTableFieldDTO::getPosition))
	              .forEach(f -> mapping.put(f.getPosition(), f));
	    } else {
	        // mapping per intestazioni
	        for (int i = 0; i < headers.length; i++) {
	            String header = headers[i] != null ? headers[i].trim().toUpperCase() : "";
	            for (FormFlowTableFieldDTO field : fields) {
	                if ((field.getName() != null && field.getName().equalsIgnoreCase(header))
	                    || (field.getDescription() != null && field.getDescription().equalsIgnoreCase(header))) {
	                    mapping.put(i, field);
	                    break;
	                }
	            }
	        }
	    }
	    return mapping;
	}

	public FlowOperationResult<Boolean> insertRecords(String requestId) {
	    boolean skipColumnName = false;
	    TabgenValue tabGenValueAnaImportConf = new TabgenValue();

	    // Recupero configurazione MODE_IMPORT_FILE_CONF (come tuo)
	    TabgenValueFilter filterAnaImportConf = new TabgenValueFilter();
	    filterAnaImportConf.setTabgenId("MODE_IMPORT_FILE_CONF");
	    BasePagingLoadResult<Tabgen> list = tabgenDelegate.searchValue(filterAnaImportConf);
	    if (list != null && list.getTotalLength() > 0 && list.getList() != null && !list.getList().isEmpty()) {
	        Tabgen flowAnaImportConf = list.getList().get(0);
	        if (flowAnaImportConf != null && "MODE_IMPORT_FILE_CONF".equals(flowAnaImportConf.getId())) {
	            for (TabgenValue tv : flowAnaImportConf.getTabgenValues()) {
	                tabGenValueAnaImportConf = tv;
	            }
	        }
	    }
	    if (tabGenValueAnaImportConf != null && tabGenValueAnaImportConf.getField1() != null) {
	        String v = tabGenValueAnaImportConf.getField1();
	        if ("S".equalsIgnoreCase(v) || "SI".equalsIgnoreCase(v) || "1".equalsIgnoreCase(v)) {
	            skipColumnName = true;
	        }
	    }

	    FlowFileUploadRequestDO request = flowFileUploadRequestDAO.getOne(requestId);
	    WellFormedStatusEnum status = WellFormedStatusEnum.valueOf(request.getStatus());
	    if (!status.equals(WellFormedStatusEnum.WELL_FORMED) && !status.equals(WellFormedStatusEnum.IN_IMPORT)) {
	        return FlowOperationResult.failure("Non è possibile salvare i record di un flusso non ben formato");
	    }

	    try {
	        // gestione formati data (come tuo)
	        String cfgDateFormatConf = configuration.findByKeyId("upload_file_date_parser").getValue();
	        String cfgDateFormat = "yyyy-MM-dd";
	        int dateLenght = 10;
	        String[] cfgSplitted = cfgDateFormatConf.split("/");
	        for (String value : cfgSplitted) {
	            String[] dateCfg = value.split("=");
	            if (dateCfg[0].equals(request.getFlow().getName())) {
	                cfgDateFormat = dateCfg[1];
	                dateLenght = dateCfg[1].length();
	                break;
	            }
	        }
	        SimpleDateFormat dateFormat = new SimpleDateFormat(cfgDateFormat);

	        FormFlowDTO flowConfiguration = retrieveConfiguration(request.getFlow().getId(), request.getVersion().getId());
	        VersionDO version = versionDAO.getOne(request.getVersion().getId());
	        String versionName = version.getVersion();

	        List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();

	        final boolean skipColumnNameFinal = skipColumnName;
	        final int dateLengthFinal = dateLenght;

	        Session session = entityManager.unwrap(Session.class);
	        FlowOperationResult<Boolean> result = session.doReturningWork((ReturningWork<FlowOperationResult<Boolean>>) conn -> {
	            try {
	                for (FlowSectionFileDO sectionFile : request.getFiles()) {
	                    if (sectionFile.getSectionFileList() == null || sectionFile.getSectionFileList().isEmpty()) continue;

	                    List<FormFlowTableFieldDTO> fields = retrieveFields(sectionFile.getSection(), flowConfiguration);
	                    String insertQueryString = QueryGenerator.generateFlowRecordInsertQuery(fields,
	                            flowConfiguration.getName(), sectionFile.getSection());

	                    try (PreparedStatement ps = conn.prepareStatement(insertQueryString)) {

	                        SectionFileDO file = sectionFile.getSectionFileList().get(0);
	                        String ext = file.getExtension().toLowerCase();

	                        File afile = File.createTempFile(sectionFile.getId(), "." + ext);
	                        FileUtils.writeByteArrayToFile(afile, file.getFile());

	                        List<FlowFileUploadRequestErrorDO> errors = new ArrayList<>();

	                        try {
	                            switch (ext) {
	                                case FileUtility.TEXT:
	                                    processTxtFile(afile, ps, fields, aziende, request, sectionFile,
	                                            versionName, dateFormat, dateLengthFinal, skipColumnNameFinal, errors);
	                                    break;

	                                case FileUtility.XLS:
	                                    processXlsFile(afile, ps, fields, aziende, request, sectionFile,
	                                            versionName, dateFormat, skipColumnNameFinal, errors);
	                                    break;

	                                case FileUtility.XLSX:
	                                    processXlsxFile(afile, ps, fields, aziende, request, sectionFile,
	                                            versionName, dateFormat, skipColumnNameFinal, errors);
	                                    break;

	                                case FileUtility.CSV:
	                                    processCsvFile(afile, ps, fields, aziende, request, sectionFile,
	                                            versionName, dateFormat, skipColumnNameFinal, errors);
	                                    break;

	                                case FileUtility.ZIP:
	                                case FileUtility.TXT_GZIP:
	                                case FileUtility.CSV_GZIP:
	                                case FileUtility.XLS_GZIP:
	                                case FileUtility.XLSX_GZIP:
	                                case FileUtility.TAR_GZIP:
	                                case FileUtility.TGZ:
	                                    processArchiveFile(afile, ext, ps, fields, aziende, request, sectionFile,
	                                            versionName, dateFormat, dateLengthFinal, skipColumnNameFinal, errors);
	                                    break;

	                                default:
	                                    LOGGER.warn("Formato file non supportato: {}", ext);
	                                    break;
	                            }

	                            // flush finale PS se qualcosa è rimasto in batch
	                            ps.executeBatch();
	                            ps.clearBatch();
	                            ps.clearParameters();

	                        } catch (Exception e) {
	                            // Propaga: rollback transazione Spring
	                        	LogUtil.logException(LOGGER, "", e);
	                            throw new RuntimeException(e);
	                        } finally {
	                            try { afile.delete(); } catch (Exception ignore) {}
	                        }

	                        if (!errors.isEmpty()) {
	                            File checkFile = File.createTempFile("controllo_" + request.getFlow().getName(), "");
	                            try (PrintStream printStream = new PrintStream(checkFile)) {
	                                for (FlowFileUploadRequestErrorDO err : errors) {
	                                    printStream.println(err.getError());
	                                }
	                            }
	                            sectionFile.getSectionFileList().get(0)
	                                    .setWellFormedFile(Files.toByteArray(checkFile));
	                            errors.clear();
	                            return FlowOperationResult.failure("Errore durante l'elaborazione del flusso");
	                        }
	                    }
	                }

	                return FlowOperationResult.success(true);

	            } catch (RuntimeException re) {
	            	LogUtil.logException(LOGGER, "", re);
	            	
	                throw re;
	            } catch (Exception ex) {
	            	LogUtil.logException(LOGGER, "", ex);
	            	
	                throw new RuntimeException(ex);
	            }
	        });

	        if (!result.getSuccess()) {
	            return result;
	        }

	        flowFileUploadRequestDAO.save(request);
	        return FlowOperationResult.success();

	    } catch (Exception ex) {
	    	LogUtil.logException(LOGGER, "", ex);
	        return FlowOperationResult.failure("Errore durante l'elaborazione del flusso");
	    }
	}

	
	private static void closeQuietly(AutoCloseable c) {
	    if (c != null) try { c.close(); } catch (Exception ignore) {}
	}
	
	private void processTxtFile(File afile, PreparedStatement ps, List<FormFlowTableFieldDTO> fields, List<String> aziende,
	        FlowFileUploadRequestDO request, FlowSectionFileDO sectionFile, String versionName,
	        SimpleDateFormat dateFormat, int dateLenght, boolean skipColumnName,
	        List<FlowFileUploadRequestErrorDO> errors) {

	    int seek = 0;
	    try (BufferedReader br = new BufferedReader(new FileReader(afile))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            seek++;
	            boolean aziendaPermission = true;
	            int index = 1;

	            Timestamp ts = new Timestamp(new Date().getTime());
	            ps.setString(index++, UUID.randomUUID().toString());
	            ps.setString(index++, "0");
	            ps.setString(index++, request.getId());
	            ps.setString(index++, "");
	            ps.setString(index++, versionName);
	            ps.setString(index++, "");
	            ps.setTimestamp(index++, ts);
	            ps.setDate(index++, null);
	            ps.setDate(index++, null);
	            ps.setString(index++, null);
	            ps.setString(index++, null);
	            ps.setString(index++, null);
	            ps.setString(index++, ImportTypeEnum.FROM_FILE_UPLOAD.name());
	            ps.setString(index++, StateReceviedAppEnum.NUOVA.name());
	            ps.setString(index++, StateSendRegionEnum.DA_INVIARE.name());
	            ps.setString(index++, null);

	            int position = 0;
	            for (FormFlowTableFieldDTO field : fields) {
	                Integer length;
	                if (field.getFieldType().equalsIgnoreCase("date") || field.isReferenceDate()) {
	                    if (field.getLength() != null) length = Integer.valueOf(field.getLength());
	                    else if (field.getRegExp() != null) length = FileUtility.getMaxLengthRegex(field.getRegExp());
	                    else length = dateLenght;
	                } else {
	                    length = Integer.valueOf(field.getLength());
	                }

	                String fieldValue = line.substring(position, Math.min(position + length, line.length())).trim();
	                position += length;

	                if (field.getName().equalsIgnoreCase("CODICEAZIENDA")
	                        && !aziende.isEmpty()
	                        && fieldValue != null
	                        && !aziende.contains(fieldValue)) {
	                    aziendaPermission = false;
	                }

	                setPreparedValue(ps, index++, field, fieldValue, dateFormat);
	            }

	            if (aziendaPermission) {
	                ps.addBatch();
	            }

	            if (seek % 1000 == 0) {
	                ps.executeBatch();
	                ps.clearBatch();
	                ps.clearParameters();
	            }
	        }

	        ps.executeBatch();
	        ps.clearBatch();
	        ps.clearParameters();

	    } catch (Exception ex) {
	        errors.add(createError("Errore TXT flusso " + request.getFlow().getName() + "' - Riga " + seek + ": " + ex,
	                request, sectionFile.getSection(), seek));
	        LogUtil.logException(LOGGER, "Errore TXT flusso "+request.getFlow().getName()+"", ex);
	    }
	}
	
	private void processXlsFile(File afile, PreparedStatement ps, List<FormFlowTableFieldDTO> fields, List<String> aziende,
	        FlowFileUploadRequestDO request, FlowSectionFileDO sectionFile, String versionName,
	        SimpleDateFormat dateFormat, boolean skipColumnName,
	        List<FlowFileUploadRequestErrorDO> errors) {

	    int r = 0;
	    try {
	        WorkbookSettings ws = new WorkbookSettings();
	        ws.setEncoding("Cp1252");
	        Workbook workbook = Workbook.getWorkbook(new FileInputStream(afile), ws);
	        Sheet sheet = workbook.getSheet(0);
	        int rows = sheet.getRows();

	        Map<String, Integer> headerIndexMap = new HashMap<>();
	        if (!skipColumnName && rows > 0) {
	            String[] headers = new String[sheet.getColumns()];
	            for (int c = 0; c < sheet.getColumns(); c++) {
	                headers[c] = sheet.getCell(c, 0).getContents();
	            }
	            headerIndexMap = buildHeaderIndexMap(headers, fields);
	        }

	        for (r = (skipColumnName ? 0 : 1); r < rows; r++) {
	            boolean aziendaPermission = true;
	            int index = 1;
	            Timestamp ts = new Timestamp(new Date().getTime());

	            ps.setString(index++, UUID.randomUUID().toString());
	            ps.setString(index++, "0");
	            ps.setString(index++, request.getId());
	            ps.setString(index++, "");
	            ps.setString(index++, versionName);
	            ps.setString(index++, "");
	            ps.setTimestamp(index++, ts);
	            ps.setDate(index++, null);
	            ps.setDate(index++, null);
	            ps.setString(index++, null);
	            ps.setString(index++, null);
	            ps.setString(index++, null);
	            ps.setString(index++, ImportTypeEnum.FROM_FILE_UPLOAD.name());
	            ps.setString(index++, StateReceviedAppEnum.NUOVA.name());
	            ps.setString(index++, StateSendRegionEnum.DA_INVIARE.name());
	            ps.setString(index++, null);

	            for (FormFlowTableFieldDTO field : fields) {
	                String fieldValue = "";

	                if (skipColumnName && field.getPosition() != null) {
	                    int pos = field.getPosition();
	                    if (pos < sheet.getColumns()) {
	                        fieldValue = sheet.getCell(pos, r).getContents().trim();
	                    }
	                } else {
	                    Integer colIdx = headerIndexMap.get(field.getName().toUpperCase());
	                    if (colIdx != null && colIdx < sheet.getColumns()) {
	                        fieldValue = sheet.getCell(colIdx, r).getContents().trim();
	                    }
	                }

	                if (field.getName().equalsIgnoreCase("CODICEAZIENDA")
	                        && !aziende.isEmpty()
	                        && fieldValue != null
	                        && !aziende.contains(fieldValue)) {
	                    aziendaPermission = false;
	                }

	                setPreparedValue(ps, index++, field, fieldValue, dateFormat);
	            }

	            if (aziendaPermission) ps.addBatch();

	            if (r % 1000 == 0) {
	                ps.executeBatch();
	                ps.clearBatch();
	                ps.clearParameters();
	            }
	        }

	        ps.executeBatch();
	        ps.clearBatch();
	        ps.clearParameters();
	        workbook.close();

	    } catch (Exception ex) {
	        errors.add(createError(
	                "Errore XLS flusso " + request.getFlow().getName() + "' - Riga " + r + ": " + ex,
	                request, sectionFile.getSection(), r
	        ));
	        LogUtil.logException(LOGGER, "Errore XLS flusso "+request.getFlow().getName()+"", ex);
	    }
	}

	private void processXlsxFile(File afile, PreparedStatement ps, List<FormFlowTableFieldDTO> fields, List<String> aziende,
	        FlowFileUploadRequestDO request, FlowSectionFileDO sectionFile, String versionName,
	        SimpleDateFormat dateFormat, boolean skipColumnName,
	        List<FlowFileUploadRequestErrorDO> errors) {

	    try (OPCPackage pkg = OPCPackage.open(afile)) {
	        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
	        XSSFReader xssfReader = new XSSFReader(pkg);
	        StylesTable styles = xssfReader.getStylesTable();
	        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();

	        if (!iter.hasNext()) throw new IllegalArgumentException("File XLSX vuoto");

	        try (InputStream sheetStream = iter.next()) {
	            XMLReader parser = XMLReaderFactory.createXMLReader();
	            parser.setContentHandler(new SheetXlsxHandler(
	                    fields, ps, aziende, skipColumnName, dateFormat,
	                    strings, errors, request, sectionFile.getSection(), versionName
	            ));
	            parser.parse(new InputSource(sheetStream));
	        }

	        ps.executeBatch();
	        ps.clearBatch();
	        ps.clearParameters();

	    } catch (Exception ex) {
	        errors.add(createError(
	                "Errore XLSX flusso " + request.getFlow().getName() + ": " + ex,
	                request, sectionFile.getSection(), 0
	        ));
	        LogUtil.logException(LOGGER, "Errore XLSX flusso "+request.getFlow().getName()+"", ex);
	    }
	}

	public class SheetXlsxHandler extends DefaultHandler {
	    private final List<FormFlowTableFieldDTO> fields;
	    private final PreparedStatement ps;
	    private final List<String> aziende;
	    private final boolean skipColumnName;
	    private final SimpleDateFormat dateFormat;
	    private final ReadOnlySharedStringsTable strings;
	    private final List<FlowFileUploadRequestErrorDO> errors;
	    private final FlowFileUploadRequestDO request;
	    private final int section;
	    private final String versionName;
	    private final String id;

	    private List<String> rowValues = new ArrayList<>();
	    private int rowNum = 0;
	    private boolean vIsOpen;
	    private String cellType;
	    private int thisColumn = -1;
	    private int lastColumnNumber = -1;
	    private StringBuilder value = new StringBuilder();
	    private Map<String, Integer> headerIndexMap = new HashMap<>();

	    public SheetXlsxHandler(List<FormFlowTableFieldDTO> fields, PreparedStatement ps, List<String> aziende,
	                            boolean skipColumnName, SimpleDateFormat dateFormat,
	                            ReadOnlySharedStringsTable strings,
	                            List<FlowFileUploadRequestErrorDO> errors,
	                            FlowFileUploadRequestDO request, Integer section, String versionName) {
	        this.fields = fields;
	        this.ps = ps;
	        this.aziende = aziende;
	        this.skipColumnName = skipColumnName;
	        this.dateFormat = dateFormat;
	        this.strings = strings;
	        this.errors = errors;
	        this.request = request;
	        this.section = section;
	        this.versionName = versionName;
	        this.id = request.getId();
	    }

	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes) {
	        if ("row".equals(qName)) {
	            rowValues = new ArrayList<>();
	            lastColumnNumber = -1;
	        } else if ("c".equals(qName)) {
	            cellType = attributes.getValue("t");
	            String r = attributes.getValue("r");
	            thisColumn = nameToColumn(r.replaceAll("\\d", ""));
	            for (int i = lastColumnNumber + 1; i < thisColumn; i++) rowValues.add("");
	            value.setLength(0);
	            vIsOpen = true;
	        } else if ("v".equals(qName)) {
	            vIsOpen = true;
	            value.setLength(0);
	        }
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) {
	        if ("v".equals(qName)) {
	            String raw = value.toString();
	            String fieldValue;

	            if (cellType == null) {
	                fieldValue = raw;
	            } else if ("s".equals(cellType)) {
	            	int idx = Integer.parseInt(raw);
	                fieldValue = strings.getItemAt(idx).getString();
	            } else {
	                fieldValue = raw;
	            }

	            rowValues.add(fieldValue != null ? fieldValue.trim() : "");
	            lastColumnNumber = thisColumn;
	            vIsOpen = false;

	        } else if ("c".equals(qName) && value.length() == 0) {
	            rowValues.add("");
	            lastColumnNumber = thisColumn;

	        } else if ("row".equals(qName)) {
	            rowNum++;

	            if (!skipColumnName && rowNum == 1) {
	                headerIndexMap = buildHeaderIndexMap(rowValues.toArray(new String[0]), fields);
	                return;
	            }

	            try {
	                boolean aziendaPermission = true;
	                Timestamp ts = new Timestamp(new Date().getTime());
	                int index = 1;

	                ps.setString(index++, UUID.randomUUID().toString());
	                ps.setString(index++, "0");
	                ps.setString(index++, id);
	                ps.setString(index++, "");
	                ps.setString(index++, versionName);
	                ps.setString(index++, "");
	                ps.setTimestamp(index++, ts);
	                ps.setDate(index++, null);
	                ps.setDate(index++, null);
	                ps.setString(index++, null);
	                ps.setString(index++, null);
	                ps.setString(index++, null);
	                ps.setString(index++, ImportTypeEnum.FROM_FILE_UPLOAD.name());
	                ps.setString(index++, StateReceviedAppEnum.NUOVA.name());
	                ps.setString(index++, StateSendRegionEnum.DA_INVIARE.name());
	                ps.setString(index++, null);

	                for (FormFlowTableFieldDTO field : fields) {
	                    String fv = "";

	                    if (skipColumnName && field.getPosition() != null) {
	                        int pos = field.getPosition();
	                        if (pos < rowValues.size()) fv = rowValues.get(pos);
	                    } else {
	                        Integer colIdx = headerIndexMap.get(field.getName().toUpperCase());
	                        if (colIdx != null && colIdx < rowValues.size()) fv = rowValues.get(colIdx);
	                    }

	                    if ("CODICEAZIENDA".equalsIgnoreCase(field.getName())
	                            && !aziende.isEmpty()
	                            && fv != null
	                            && !aziende.contains(fv)) {
	                        aziendaPermission = false;
	                    }

	                    setPreparedValue(ps, index++, field, fv, dateFormat);
	                }

	                if (aziendaPermission) ps.addBatch();

	                if (rowNum % 1000 == 0) {
	                    ps.executeBatch();
	                    ps.clearBatch();
	                    ps.clearParameters();
	                }

	            } catch (Exception ex) {
	                errors.add(createError("Errore inserimento flusso " + request.getFlow().getName()
	                        + "' - Riga " + rowNum + ": " + ex,
	                        request, section, rowNum));
	                LogUtil.logException(LOGGER, "Errore Errore inserimento flusso "+request.getFlow().getName()+" su riga "+rowNum+"", ex);
	            }
	        }
	    }

	    @Override
	    public void characters(char[] ch, int start, int length) {
	        if (vIsOpen) value.append(ch, start, length);
	    }

	    private int nameToColumn(String name) {
	        int column = -1;
	        for (int i = 0; i < name.length(); ++i) {
	            int c = name.charAt(i);
	            column = (column + 1) * 26 + c - 'A';
	        }
	        return column;
	    }

	    private Map<String, Integer> buildHeaderIndexMap(String[] headerRow, List<FormFlowTableFieldDTO> fields) {
	        Map<String, Integer> map = new HashMap<>();
	        for (int i = 0; i < headerRow.length; i++) {
	            String header = headerRow[i] != null ? headerRow[i].trim().toUpperCase() : "";
	            for (FormFlowTableFieldDTO field : fields) {
	                if (header.equalsIgnoreCase(field.getName()) || header.equalsIgnoreCase(field.getDescription())) {
	                    map.put(field.getName().toUpperCase(), i);
	                    break;
	                }
	            }
	        }
	        return map;
	    }
	}
	
	private void processCsvFile(File afile, PreparedStatement ps, List<FormFlowTableFieldDTO> fields, List<String> aziende,
	        FlowFileUploadRequestDO request, FlowSectionFileDO sectionFile, String versionName,
	        SimpleDateFormat dateFormat, boolean skipColumnName,
	        List<FlowFileUploadRequestErrorDO> errors) {

	    int rowNum = 0;

	    try {
	        CsvParserSettings settings = new CsvParserSettings();
	        settings.setDelimiterDetectionEnabled(true);
	        settings.setHeaderExtractionEnabled(false);

	        CsvParser parser = new CsvParser(settings);

	        try (Reader reader = new FileReader(afile)) {
	            parser.beginParsing(reader);
	            String[] row;
	            Map<String, Integer> headerIndexMap = new HashMap<>();

	            if (!skipColumnName) {
	                String[] headers = parser.parseNext();
	                if (headers == null) throw new IllegalArgumentException("File CSV vuoto o privo di intestazione");
	                headerIndexMap = buildHeaderIndexMap(headers, fields);
	            }

	            while ((row = parser.parseNext()) != null) {
	                rowNum++;
	                boolean aziendaPermission = true;
	                int index = 1;
	                Timestamp ts = new Timestamp(new Date().getTime());

	                ps.setString(index++, UUID.randomUUID().toString());
	                ps.setString(index++, "0");
	                ps.setString(index++, request.getId());
	                ps.setString(index++, "");
	                ps.setString(index++, versionName);
	                ps.setString(index++, "");
	                ps.setTimestamp(index++, ts);
	                ps.setDate(index++, null);
	                ps.setDate(index++, null);
	                ps.setString(index++, null);
	                ps.setString(index++, null);
	                ps.setString(index++, null);
	                ps.setString(index++, ImportTypeEnum.FROM_FILE_UPLOAD.name());
	                ps.setString(index++, StateReceviedAppEnum.NUOVA.name());
	                ps.setString(index++, StateSendRegionEnum.DA_INVIARE.name());
	                ps.setString(index++, null);

	                for (FormFlowTableFieldDTO field : fields) {
	                    String fieldValue = "";

	                    if (skipColumnName && field.getPosition() != null) {
	                        int pos = field.getPosition();
	                        if (pos < row.length) fieldValue = (row[pos] != null ? row[pos].trim() : null);
	                    } else {
	                        Integer colIdx = headerIndexMap.get(field.getName().toUpperCase());
	                        if (colIdx != null && colIdx < row.length) fieldValue = (row[colIdx] != null ? row[colIdx].trim() : null);
	                    }

	                    if (field.getName().equalsIgnoreCase("CODICEAZIENDA")
	                            && !aziende.isEmpty()
	                            && fieldValue != null
	                            && !aziende.contains(fieldValue)) {
	                        aziendaPermission = false;
	                    }

	                    setPreparedValue(ps, index++, field, fieldValue, dateFormat);
	                }

	                if (aziendaPermission) ps.addBatch();

	                if (rowNum % 1000 == 0) {
	                    ps.executeBatch();
	                    ps.clearBatch();
	                    ps.clearParameters();
	                }
	            }

	            ps.executeBatch();
	            ps.clearBatch();
	            ps.clearParameters();
	            parser.stopParsing();
	        }
	    } catch (Exception ex) {
	        errors.add(createError(
	                "Errore CSV flusso " + request.getFlow().getName() + "' - Riga " + rowNum + ": " + ex,
	                request, sectionFile.getSection(), rowNum
	        ));
	        LogUtil.logException(LOGGER, "Errore CSV flusso "+request.getFlow().getName()+" su riga "+rowNum+"", ex);
	    }
	}
	
	private void processArchiveFile(File archiveFile, String ext, PreparedStatement ps, List<FormFlowTableFieldDTO> fields,
	        List<String> aziende, FlowFileUploadRequestDO request, FlowSectionFileDO sectionFile,
	        String versionName, SimpleDateFormat dateFormat, int dateLength, boolean skipColumnName,
	        List<FlowFileUploadRequestErrorDO> errors) {

	    try {
	        if (ext.equalsIgnoreCase(FileUtility.ZIP)) {
	            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archiveFile))) {
	                ZipEntry entry;
	                while ((entry = zis.getNextEntry()) != null) {
	                    if (entry.isDirectory()) continue;

	                    String innerName = entry.getName().toLowerCase();
	                    File tempFile = File.createTempFile("unzipped_", "_" + new File(innerName).getName());

	                    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	                        IOUtils.copy(zis, fos);
	                    }

	                    dispatchExtractedFile(tempFile, innerName, ps, fields, aziende, request, sectionFile,
	                            versionName, dateFormat, dateLength, skipColumnName, errors);

	                    tempFile.delete();
	                    zis.closeEntry();
	                }
	            }
	        } else if (ext.equalsIgnoreCase(FileUtility.TAR_GZIP) || ext.equalsIgnoreCase(FileUtility.TGZ)) {
	            try (FileInputStream fis = new FileInputStream(archiveFile);
	                 BufferedInputStream bis = new BufferedInputStream(fis);
	                 GzipCompressorInputStream gzis = new GzipCompressorInputStream(bis);
	                 TarArchiveInputStream tis = new TarArchiveInputStream(gzis)) {

	                ArchiveEntry entry;
	                while ((entry = tis.getNextEntry()) != null) {
	                    if (entry.isDirectory()) continue;

	                    String innerName = entry.getName().toLowerCase();
	                    File tempFile = File.createTempFile("targz_", "_" + new File(innerName).getName());

	                    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	                        IOUtils.copy(tis, fos);
	                    }

	                    dispatchExtractedFile(tempFile, innerName, ps, fields, aziende, request, sectionFile,
	                            versionName, dateFormat, dateLength, skipColumnName, errors);

	                    tempFile.delete();
	                }
	            }
	        } else if (ext.equalsIgnoreCase(FileUtility.TXT_GZIP)
	                || ext.equalsIgnoreCase(FileUtility.CSV_GZIP)
	                || ext.equalsIgnoreCase(FileUtility.XLS_GZIP)
	                || ext.equalsIgnoreCase(FileUtility.XLSX_GZIP)) {

	            try (GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(archiveFile))) {
	                String originalName = archiveFile.getName().replaceAll("(?i)\\.gz$", "");
	                File tempFile = File.createTempFile("gunzipped_", "_" + originalName);

	                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	                    IOUtils.copy(gzis, fos);
	                }

	                dispatchExtractedFile(tempFile, originalName.toLowerCase(), ps, fields, aziende, request, sectionFile,
	                        versionName, dateFormat, dateLength, skipColumnName, errors);

	                tempFile.delete();
	            }
	        }
	    } catch (Exception ex) {
	        errors.add(createError(
	                "Errore durante la decompressione archivio " + archiveFile.getName() + " : " + ex.getMessage(),
	                request, sectionFile.getSection(), 0
	        ));
	        LogUtil.logException(LOGGER, "Errore durante la decompressione archivio "+archiveFile.getName()+"", ex);
	    }
	}

	private void dispatchExtractedFile(File file, String fileNameLower, PreparedStatement ps, List<FormFlowTableFieldDTO> fields,
	        List<String> aziende, FlowFileUploadRequestDO request, FlowSectionFileDO sectionFile,
	        String versionName, SimpleDateFormat dateFormat, int dateLength, boolean skipColumnName,
	        List<FlowFileUploadRequestErrorDO> errors) {

	    try {
	        if (fileNameLower.endsWith(FileUtility.TEXT)) {
	            processTxtFile(file, ps, fields, aziende, request, sectionFile, versionName,
	                    dateFormat, dateLength, skipColumnName, errors);

	        } else if (fileNameLower.endsWith(FileUtility.CSV)) {
	            processCsvFile(file, ps, fields, aziende, request, sectionFile, versionName,
	                    dateFormat, skipColumnName, errors);

	        } else if (fileNameLower.endsWith(FileUtility.XLS)) {
	            processXlsFile(file, ps, fields, aziende, request, sectionFile, versionName,
	                    dateFormat, skipColumnName, errors);

	        } else if (fileNameLower.endsWith(FileUtility.XLSX)) {
	            processXlsxFile(file, ps, fields, aziende, request, sectionFile, versionName,
	                    dateFormat, skipColumnName, errors);

	        } else {
	            LOGGER.warn("File ignorato nell’archivio: {}", fileNameLower);
	        }

	    } catch (Exception ex) {
	        errors.add(createError(
	                "Errore nell’elaborazione file estratto " + fileNameLower + " : " + ex.getMessage(),
	                request, sectionFile.getSection(), 0
	        ));
	        LogUtil.logException(LOGGER, "Errore nell’elaborazione file estratto "+fileNameLower+"", ex);
	    }
	}
	
	/** Utilità per header -> colonna */
	private Map<String, Integer> buildHeaderIndexMap(String[] headerRow, List<FormFlowTableFieldDTO> fields) {
	    Map<String, Integer> map = new HashMap<>();
	    for (int i = 0; i < headerRow.length; i++) {
	        String header = headerRow[i].trim().toUpperCase();
	        for (FormFlowTableFieldDTO field : fields) {
	            if (header.equalsIgnoreCase(field.getName()) || header.equalsIgnoreCase(field.getDescription())) {
	                map.put(field.getName().toUpperCase(), i);
	                break;
	            }
	        }
	    }
	    return map;
	}

	/** Utilità per valorizzare campo PreparedStatement */
	private void setPreparedValue(PreparedStatement ps, int index, FormFlowTableFieldDTO field,
            String fieldValue, SimpleDateFormat dateFormat) throws SQLException {
		
		try {
				if (fieldValue == null || fieldValue.trim().isEmpty()) {
					ps.setObject(index, null);
					return;
				}
			
			// 🔹 Caso campo data o riferimento data
			if (field.getFieldType().equalsIgnoreCase("date") || field.isReferenceDate()) {
				Date parsedDate = null;
			
				// 1️⃣ - Riconosci se è un numero Excel (es. 45902 o 45902.025)
				if (fieldValue.matches("^\\d+(\\.\\d+)?$")) {
					try {
					  double excelDate = Double.parseDouble(fieldValue);
					  if (DateUtil.isValidExcelDate(excelDate)) {
					      parsedDate = DateUtil.getJavaDate(excelDate);
					  }
					} catch (Exception e) {
					  // ignora, passo ai prossimi tentativi
					}
				}
				
				// 2️⃣ - Tenta con i formati noti (FileUtility)
				if (parsedDate == null) {
					try {
					  parsedDate = FileUtility.tryParseDateToDate(fieldValue);
					} catch (Exception e) {
					  // ignora
					}
				}
				
				// 3️⃣ - Fallback con il formato configurato (upload_file_date_parser)
				if (parsedDate == null) {
					try {
					  parsedDate = dateFormat.parse(fieldValue);
					} catch (ParseException e) {
					  // ignora
					}
				}
				
				// 4️⃣ - Fallback ISO
				if (parsedDate == null) {
					try {
					  parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue);
					} catch (ParseException e) {
					  // ignora
					}
				}
				
				// 5️⃣ - Se ancora null → scarta come valore non valido
				if (parsedDate == null) {
					LOGGER.warn("⚠️ Data non valida per campo " + field.getName() + ": [" + fieldValue + "]");
					ps.setObject(index, null);
					return;
				}
		
				// ✅ Imposta come DATE o TIMESTAMP
				if (fieldValue.length() <= 10) {
					ps.setDate(index, new java.sql.Date(parsedDate.getTime()));
				} else {
					ps.setTimestamp(index, new Timestamp(parsedDate.getTime()));
				}
		
			} else {
			// 🔹 Campo generico (stringa, numero, ecc.)
					ps.setString(index, fieldValue);
			}
	
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "ERRORE su campo "+field.getName()+" valore "+fieldValue+"", ex);
			ps.setObject(index, null);
		}
	}
	
	private FormFlowDTO retrieveConfiguration(String flowId, String versionId) {
		BaseSearchInput searchInput = new BaseSearchInput();
		searchInput.setValue("flowId", flowId);
		searchInput.setValue("versionId", versionId);
		Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFiltered(searchInput);
		FormFlowDTO configuration = searchResults.getFirst().get(0);
		return configuration;
	}
	
	private List<FormFlowTableFieldDTO> retrieveFields(Integer section, FormFlowDTO configuration) {
		List<FormFlowTableFieldDTO> fields = null;
		for(FormFlowTableDTO table : configuration.getFlowTableList()) {
			if(table.getSection() == section.intValue()) {
				fields = table.getFlowTableFieldList();
				break;
			}
		}
		
		if(fields!=null && fields.size()>0) {
			fields.sort(new Comparator<FormFlowTableFieldDTO>() {

				@Override
				public int compare(FormFlowTableFieldDTO arg0, FormFlowTableFieldDTO arg1) {
					return  arg0.getPosition().compareTo((arg1.getPosition()));
				}
			});
		}
		
		return fields;
	}

	@Override
	public void markAsValidated(String status, String extractionId) {
		FlowFileUploadRequestDO req = flowFileUploadRequestDAO.getOne(extractionId);
		req.setStatus(status);
	}
	
	private void deleteRecordsByExtractionId(FormFlowDTO configuration, String extractionId) throws SQLException {
	    final Map<Integer, String> rowQueryMap = queryGenerator.generateDeleteRowMessageByExtractionIdQuery(configuration);
	    final Map<Integer, String> messageQueryMap = queryGenerator.generateDeleteRowByExtractionIdQuery(configuration);
	    final String extId = extractionId;

	    try {
	        Session session = entityManager.unwrap(Session.class);
	        session.doWork(conn -> {
	            try {
	                for (Integer sec : rowQueryMap.keySet()) {
	                    try (PreparedStatement rowps = conn.prepareStatement(rowQueryMap.get(sec));
	                         PreparedStatement msgps = conn.prepareStatement(messageQueryMap.get(sec))) {

	                        rowps.setString(1, extId);
	                        msgps.setString(1, extId);

	                        rowps.executeUpdate();
	                        msgps.executeUpdate();
	                    }
	                }
	            } catch (SQLException e) {
	            	LogUtil.logException(LOGGER, "", e);
	            	
	                throw new RuntimeException("Errore deleteRecordsByExtractionId", e);
	            }
	        });
	    } catch (RuntimeException re) {
	    	LogUtil.logException(LOGGER, "", re);
	    	
	        Throwable cause = re.getCause();
	        if (cause instanceof SQLException se) throw se;
	        throw new SQLException("Errore deleteRecordsByExtractionId", re);
	    }
	}

	
	@Override
	public String getFlowId(String requestId) {
		return flowFileUploadRequestDAO.getOne(requestId).getFlow().getId();
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Download file caricamento flusso", converter= FileDownloadConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.SCHEDA_MEDICA)
	public byte[] sendAuditDownXMLFileUplToPM(FlowFileUploadRequest flowFileUploadRequest, byte[] byt) {
		return byt;
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Download file errori caricamento flusso", converter= FileDownloadLogConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.SCHEDA_MEDICA)
	public byte[] sendAuditDownLOGFileUplToPM(FlowFileUploadRequest flowFileUploadRequest, byte[] byt) {
		return byt;
	}
	
	private FlowFileUploadRequestErrorDO createError(String description, FlowFileUploadRequestDO request, Integer section, Integer record) {
		FlowFileUploadRequestErrorDO error = new FlowFileUploadRequestErrorDO();
		error.setId(UUID.randomUUID().toString());
		error.setRequest(request);
		error.setError(description);
		error.setSection(section);
		error.setIndexRecord(record);
		return error;
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.CREATE, category = AuditEventCategoryEnum.ACCESS_LOG, description="Caricamento File Upload", converter= CaricamentoFileUploadConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.CARTELLA_CLINICA)
    public  byte[] sendAuditUploadCaricamentoFile(String idEstraz, String flowName, String fileName, AuditEventActionEnum typeOperation, byte[] byt) {
    	return byt;
    }
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.DELETE, category = AuditEventCategoryEnum.ACCESS_LOG, description="Eliminazione Caricamento File Upload", converter= CaricamentoFileUploadConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.CARTELLA_CLINICA)
    public  byte[] sendAuditUploadCaricamentoFileDel(String idEstraz, String flowName, String fileName, AuditEventActionEnum typeOperation, byte[] byt) {
    	return byt;
    }

}
