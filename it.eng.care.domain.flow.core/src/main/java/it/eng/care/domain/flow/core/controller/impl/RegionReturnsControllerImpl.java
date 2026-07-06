package it.eng.care.domain.flow.core.controller.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.controller.RegionReturnsController;
import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.dao.UploadReturnsRequestDAO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.RegionReturnsDTO;
import it.eng.care.domain.flow.core.dto.ReturnDownloadDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;
import it.eng.care.domain.flow.core.service.FlowExportRequestService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.TwoLevelResultsService;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.crypt.CryptoManager;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/RegionReturnsDTO")
public class RegionReturnsControllerImpl implements RegionReturnsController {
	
	@Autowired
    private ConfigurationDAO configuration;
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private TwoLevelResultsService twoLevelResultsService;
	
	@Autowired
	private FlowRegionUnionService flowRegionUnionService;
	
	@Autowired
	private FlowExportRequestService flowExportService;
	
	@Autowired
	private CryptoManager cryptoManager;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;
	
	@Autowired
    private LogAccessiPMConfig logAccessiPMConfig;
	
	@Autowired
    private FlowService flowService;
	
	@Autowired
	private UploadReturnsRequestDAO uploadReturnsRequestDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegionReturnsControllerImpl.class);

	//SERVIZIO PER L'UPLOAD DEI FILE REGIONALI DALLA SEARCH ACTIVITY DELL'ESTRAZIONE
	@Override
	@PostMapping(path = "/_import")
	@ResponseBody
	public FlowOperationResult<Boolean> fileUpload(
			@RequestHeader(name = "extractionId", defaultValue = "unknown") String extractionId,
			@RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
			@RequestBody byte[] bytes) {

		File file = null;
		File filea = null;
		String flowId = null;
		String versionId = null;
		Boolean consolidata = false;
		try {
			//caricamento lista aziende visibili dall'utente
			List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
			
			file = File.createTempFile(fileName, fileName.substring(fileName.length() - 3));
			
			filea = new File(file.getParent() + "/" + fileName);
			
			FileUtils.writeByteArrayToFile(filea, bytes);
			
			BaseSearchInput searchInputExport = new BaseSearchInput();
			searchInputExport.setValue("id", extractionId);
			searchInputExport.setValue("limit", 1);
			searchInputExport.setValue("offset", 0);
			searchInputExport.setParam("aziende", aziende);
			Pair<List<FlowExportRequestDO>, SearchInfo> searchResults = flowExportService.retrieveAllFiltered(searchInputExport, true);
			if(searchResults != null && searchResults.getFirst() != null && !searchResults.getFirst().isEmpty() && searchResults.getFirst().size() == 1) {
				FlowExportRequestDO expReq = searchResults.getFirst().get(0);
				if(expReq.getStatus() != null && expReq.getStatus().equals("TERMINATA_OK")) {
					if(expReq.getValidationStatus() == null || !expReq.getValidationStatus().equals("NOT_VALID")) {
						if(expReq.getRecord() == null || !expReq.getRecord().equals("0")) {
							flowId = expReq.getFlow().getId();
							versionId = expReq.getVersion().getId();
							consolidata = expReq.getConsolidata()==null ? false : expReq.getConsolidata();
						} else {
							return FlowOperationResult.failure("Caricamento non eseguito: Nessuna pratica estratta");	
						}
					} else {
						return FlowOperationResult.failure("Caricamento non eseguito: Estrazione non valida");	
					}
				} else {
					return FlowOperationResult.failure("Caricamento non eseguito: Estrazione non avviata");	
				}
			} else {
				return FlowOperationResult.failure("Errore in fase di caricamento: Estrazione non trovata");
			}
			
			BaseSearchInput searchInput = new BaseSearchInput();
			searchInput.setValue("flow", flowId);
			searchInput.setValue("version", versionId);
			Pair<List<FlowRegionUnionDO>, SearchInfo> unionList = flowRegionUnionService.retrieveAllFiltered(searchInput);
			if(unionList != null && unionList.getFirst() != null && !unionList.getFirst().isEmpty()) {
				FlowRegionUnionDO union = unionList.getFirst().get(0);
				FlowDO flowRegion = union.getFlowRegion();
				String flowRegionId = flowRegion.getId();
				// la versione è la stessa del flusso locale perchè il flusso regionale viene creato automaticamente durante la creazione del flusso locale
				FlowOperationResult<Boolean> result = twoLevelResultsService.uploadResults(filea, flowId, flowRegionId, versionId, extractionId,false, consolidata);
				
				if (result.getSuccess()) {
					if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogEstrazFlussiUploadRitorno())) {
						searchInput.setValue("id", extractionId);
						FlowDO flowLocal = flowService.retrieveOne(flowId);
						searchInput.setValue("flowName", flowLocal.getName());
						searchInput.setValue("fileName", fileName);
			    		byte[] resultsLogAccessi = twoLevelResultsService.sendAuditUploadRitornoToPM(searchInput, bytes);
			        }
				}
				
				return result;
			} else {
				return FlowOperationResult.failure("Errore in fase di caricamento: configurazione flusso regionale non trovata");
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		} 
//		finally {
//			if(file != null) {
//				file.delete();
//			}
//			if(filea != null) {
//				filea.delete();
//			}
//		}

		return FlowOperationResult.failure("Caricamento fallito");

	}
	
	//SERVIZIO PER LA CREAZIONE DELLA TABELLA PRESENTE NELL'ACCORDION DEL FLOW VIEW RIGUARDANTE LE TABELLE REGIONALI
	@Override
    @PostMapping("/getReturns")
    @ResponseBody
    public OperationResult<RegionReturnsDTO> getReturns(@RequestBody FlowViewFilterError flowViewFilterError) {
		
		LOGGER.debug("PROVA LOGGER " + flowViewFilterError.getFlow().getName());
		
		String cfgSuffixRegError = configuration.findByKeyId("suffix_reg_error").getValue();
		String tableNameRegErrors = flowViewFilterError.getFlow().getName() + "_" + cfgSuffixRegError;
		
//		String cfgSuffixRegUpdate = configuration.findByKeyId("suffix_reg_update").getValue();
//		String tableNameRegUpdate = flowViewFilterError.getFlow().getName() + "_" + cfgSuffixRegUpdate; 
		
		String queryString = "SELECT * FROM FM_TABGEN_VALUE WHERE TABGEN_ID = '" + tableNameRegErrors.toUpperCase() + "'";

		Query query = entityManager.createNativeQuery(queryString);
        List<Object[]> result = query.getResultList();
        
        Object[] severityColumn = new Object[] {"","SEVERITY","", "1000"};
        result.add(severityColumn);
        
    	for (int i = 0; i < result.size()-1; i++)  
        {  
            int min_idx = i;  
            for (int j = i+1; j < result.size(); j++) {  
            	String varJ = (String)result.get(j)[3];
            	String varMin = (String)result.get(min_idx)[3];
                if(Integer.parseInt(varJ)<Integer.parseInt(varMin)) {
                    min_idx = j;
                }
            }
            Collections.swap(result, min_idx, i);
        }
        
        List<Object> resultList = createQuery(flowViewFilterError, result, tableNameRegErrors);

        Query query2 = entityManager.createNativeQuery((String)resultList.get(0));
        List<Object[]> result2 = query2.getResultList();
        
        RegionReturnsDTO regionReturns = new RegionReturnsDTO();
        List<HashMap<String, String>> listMap = new ArrayList<HashMap<String,String>>();
        
        List<String> listaColonne = new ArrayList<String>();
        for(int i=0; i<result.size(); i++) {
        	listaColonne.add((String)result.get(i)[1]);
        }
        regionReturns.setColumns(listaColonne);
        
        for(int j=0; j<result2.size(); j++) {
        	HashMap<String, String> map = new HashMap<String,String>();
	        for(int i=0; i<result.size(); i++) {
	        	
	        	String value = (String)result2.get(j)[i];
	        	if(isCrypto((String)result.get(i)[1], flowViewFilterError.getFlow())) {
	        		value = cryptoManager.decryptString(value);
	        	}
	        	
		    	map.put((String)result.get(i)[1], value);
	        }
	        listMap.add(map);
        }
        regionReturns.setValues(listMap);
        
        return OperationResult.success(regionReturns);
        
    }
	
	private Boolean isCrypto(String name, FormFlowDTO configuration) {
		for(FormFlowTableDTO table : configuration.getFlowTableList()) {
			for(FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
				if(field.isCrypto() && field.getName().equalsIgnoreCase(name)) {
					return field.isCrypto();
				}
			}
		}
		return false;
	}
	
	 public List<Object> createQuery(FlowViewFilterError flowViewFilterError, List<Object[]> resultInput, String tableNameRegErrors) {
		 
		 String query = "";
		 String select = "SELECT ";
		 String from = "FROM " + tableNameRegErrors.toUpperCase() + " ";
		 String where = "WHERE " ;
		 
		 String[] pkList = flowViewFilterError.getPkList();
		 String[] pkNameList = flowViewFilterError.getPkNameList();

		 for(int i=0; i<resultInput.size(); i++) {
			 select += resultInput.get(i)[1] + " , ";
		 }
		 if (select.endsWith(" , ")) {
			 select = select.substring(0, select.length() - 2);
         }
		 for(int i = 0; i<pkList.length; i++) {
			 
			 String value = pkList[i];
			 if (value!=null) {
				 if(isCrypto(pkNameList[i], flowViewFilterError.getFlow())) {
					 value = cryptoManager.encryptString(value);
				 }
				 
				 where += pkNameList[i].toUpperCase() + " = '" + value + "' AND ";
				 
			 }
		 }
		 if (where.endsWith(" AND ")) {
			 where = where.substring(0, where.length() - 4);
         }
		 
		 if(flowViewFilterError.getExtractionId() != null && !flowViewFilterError.getExtractionId().isEmpty()) {
			 if (where.equals("WHERE ")) {
				 where += " extraction_id = '" + flowViewFilterError.getExtractionId() + "' ";
			 } else {
				 where += " AND extraction_id = '" + flowViewFilterError.getExtractionId() + "' ";
			 }
			 
		 }
		 
		 String orderBy = " order by severity ";
		 
		 query = select + from + where + orderBy;;
		 
		 
	 List<Object> result = new ArrayList<Object>();
	 result.add(query);
	 
	 return result;
	 }
	 
	 
	@Override
	@PostMapping(path = "/_importTot")
	@ResponseBody
	public FlowOperationResult<Boolean> fileUploadTot(
			@RequestHeader(name = "extractionId", defaultValue = "unknown") String extractionId,
			@RequestHeader(name = "flowId", defaultValue = "unknown") String flowId,
			@RequestHeader(name = "versionId", defaultValue = "unknown") String versionId,
			@RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
			@RequestBody byte[] bytes) {

		File file = null;
		File filea = null;
		try {
			file = File.createTempFile(fileName, fileName.substring(fileName.length() - 3));
			
			filea = new File(file.getParent() + "/" + fileName);
			
			FileUtils.writeByteArrayToFile(filea, bytes);
			
			//se il flusso è presente nella tabella di configurazione che censisce i flussi per cui bisogna caricare il drg
			String flowToDrg = configuration.findByKeyId("flowToDrg").getValue();
			if(!flowToDrg.contains(flowId)) {
				return FlowOperationResult.failure("Operazione non consentita per questo flusso");
			}
			
			BaseSearchInput searchInput = new BaseSearchInput();
			searchInput.setValue("flow", flowId);
			Pair<List<FlowRegionUnionDO>, SearchInfo> unionList = flowRegionUnionService.retrieveAllFiltered(searchInput);
			if(unionList != null && unionList.getFirst() != null && !unionList.getFirst().isEmpty()) {
				FlowRegionUnionDO union = unionList.getFirst().get(0);
				FlowDO flowRegion = union.getFlowRegion();
				String flowRegionId = flowRegion.getId();
				// la versione è la stessa del flusso locale perchè il flusso regionale viene creato automaticamente durante la creazione del flusso locale
				FlowOperationResult<Boolean> result = twoLevelResultsService.uploadResults(filea, flowId, flowRegionId, versionId ,extractionId,true,false);
				
				if (result.getSuccess()) {
					if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogDashboardUploadRitorno())) {
						FlowDO flowLocal = flowService.retrieveOne(flowId);
						searchInput.setValue("flowName", flowLocal.getName());
						searchInput.setValue("fileName", fileName);
			    		byte[] resultsLogAccessi = twoLevelResultsService.sendAuditUploadRitornoToPM(searchInput, bytes);
			        }
				}
				
				return result;
			} else {
				return FlowOperationResult.failure("Errore in fase di caricamento: configurazione flusso regionale non trovata");
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		} 
//		finally {
//			if(file != null) {
//				file.delete();
//			}
//			if(filea != null) {
//				filea.delete();
//			}
//		}

		return FlowOperationResult.failure("Caricamento fallito");

	}
	
//	@PostMapping(path = "/_downloadImportTotFile", consumes = "text/plain")
//	@ResponseBody
//	public FlowOperationResult<ReturnDownloadDTO> downloadImportTotFile(@RequestBody ReturnDownloadDTO request) {
//	    try {
//	        if (request.getExtractionId() == null || request.getExtractionId().isBlank()) {
//	            return FlowOperationResult.failure("Parametro extractionId mancante");
//	        }
//
//	        String extractionIdTrimmed = request.getExtractionId().trim();
//
//	        UploadReturnsRequestDO req = uploadReturnsRequestDAO.findByExtractionIdAndFlowId(extractionIdTrimmed, request.getFlowId());
//	        if (req == null || req.getFile() == null || req.getFile().length == 0) {
//	            return FlowOperationResult.failure("File di upload ritorno regionale totale non trovato.");
//	        }
//	        if (req != null && req.getEndProcessDate() == null) {
//	            return FlowOperationResult.failure("File di upload ritorno regionale totale non ancora disponibile. Attendere il completamento del processo di upload.");
//	        }
//	        
//	        String originalFileName = (req.getFileNameOrigin()!=null ? req.getFileNameOrigin() : "file_upload_ritorno_totale.txt");
//	        byte[] zipContent = FileUtility.zipBytes(originalFileName, req.getFile());
//
//	        ReturnDownloadDTO out = new ReturnDownloadDTO();
//	        out.setExtractionId(req.getExtractionId());
//	        out.setAvailable(Boolean.TRUE);
//	        out.setFileName(originalFileName + ".zip");
//	        out.setContentBase64(Base64.getEncoder().encodeToString(zipContent));
//	        out.setFlowId(req.getFlowId());
//
//	        return FlowOperationResult.success(out);
//
//	    } catch (Exception e) {
//	        LogUtil.logException(LOGGER, "", e);
//	        return FlowOperationResult.failure("Errore durante il download del file di caricamento");
//	    }
//	}
//	
//	@PostMapping(path = "/_downloadImportTotErrors", consumes = "text/plain")
//	@ResponseBody
//	public FlowOperationResult<ReturnDownloadDTO> downloadImportTotErrors(@RequestBody ReturnDownloadDTO request) {
//	    try {
//	        if (request.getExtractionId() == null || request.getExtractionId().isBlank()) {
//	            return FlowOperationResult.failure("Parametro extractionId mancante");
//	        }
//
//	        String extractionIdTrimmed = request.getExtractionId().trim();
//	        UploadReturnsRequestDO req = uploadReturnsRequestDAO.findByExtractionIdAndFlowId(extractionIdTrimmed, request.getFlowId());
//	        
//	        if (req == null) {
//	            return FlowOperationResult.failure("File errori upload ritorno regionale totale non trovato.");
//	        }
//	        if (req != null && req.getEndProcessDate() == null) {
//	            return FlowOperationResult.failure("File errori upload ritorno regionale totale non ancora disponibile. Attendere il completamento del processo di upload.");
//	        }
//	        if (req != null && req.getEndProcessDate() != null && (req.getErrorFile() == null || req.getErrorFile().length == 0)) {
//	            return FlowOperationResult.failure("Errori non riscontrati nell'ultimo upload ritorni regionale totale.");
//	        }
//	        
//	        String originalFileName = "errori_upload_ritorno_totale.log";
//
//	        byte[] zipContent = FileUtility.zipBytes(originalFileName, req.getErrorFile());
//
//	        ReturnDownloadDTO out = new ReturnDownloadDTO();
//	        out.setExtractionId(req.getExtractionId());
//	        out.setAvailable(Boolean.TRUE);
//	        out.setFileName(originalFileName + ".zip");
//	        out.setContentBase64(Base64.getEncoder().encodeToString(zipContent));
//
//	        return FlowOperationResult.success(out);
//
//	    } catch (Exception e) {
//	        LogUtil.logException(LOGGER, "", e);
//	        return FlowOperationResult.failure("Errore durante il download del file errori");
//	    }
//	}
	
	@PostMapping(path = "/_downloadImportTotFile", consumes = "application/json")
	@ResponseBody
	public FlowOperationResult<ReturnDownloadDTO> downloadImportTotFile(@RequestBody ReturnDownloadDTO request) {
	    try {
	        if (request == null || request.getExtractionId() == null || request.getExtractionId().isBlank()) {
	            return FlowOperationResult.failure("Parametro extractionId mancante");
	        }

	        String extractionIdTrimmed = request.getExtractionId().trim();

	        UploadReturnsRequestDO req = uploadReturnsRequestDAO.findByExtractionIdAndFlowId(
	                extractionIdTrimmed,
	                request.getFlowId()
	        );

	        if (req == null || req.getFile() == null || req.getFile().length == 0) {
	            return FlowOperationResult.failure("File di upload ritorno regionale totale non trovato.");
	        }
	        if (req.getEndProcessDate() == null) {
	            return FlowOperationResult.failure("File di upload ritorno regionale totale non ancora disponibile. Attendere il completamento del processo di upload.");
	        }

	        String originalFileName = (req.getFileNameOrigin() != null ? req.getFileNameOrigin() : "file_upload_ritorno_totale.txt");
	        byte[] zipContent = FileUtility.zipBytes(originalFileName, req.getFile());

	        ReturnDownloadDTO out = new ReturnDownloadDTO();
	        out.setExtractionId(req.getExtractionId());
	        out.setAvailable(Boolean.TRUE);
	        out.setFileName(originalFileName + ".zip");
	        out.setContentBase64(Base64.getEncoder().encodeToString(zipContent));
	        out.setFlowId(req.getFlowId());

	        return FlowOperationResult.success(out);

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER, "", e);
	        return FlowOperationResult.failure("Errore durante il download del file di caricamento");
	    }
	}

	@PostMapping(path = "/_downloadImportTotErrors", consumes = "application/json")
	@ResponseBody
	public FlowOperationResult<ReturnDownloadDTO> downloadImportTotErrors(@RequestBody ReturnDownloadDTO request) {
	    try {
	        if (request == null || request.getExtractionId() == null || request.getExtractionId().isBlank()) {
	            return FlowOperationResult.failure("Parametro extractionId mancante");
	        }

	        String extractionIdTrimmed = request.getExtractionId().trim();

	        UploadReturnsRequestDO req = uploadReturnsRequestDAO.findByExtractionIdAndFlowId(
	                extractionIdTrimmed,
	                request.getFlowId()
	        );

	        if (req == null) {
	            return FlowOperationResult.failure("File errori upload ritorno regionale totale non trovato.");
	        }
	        if (req.getEndProcessDate() == null) {
	            return FlowOperationResult.failure("File errori upload ritorno regionale totale non ancora disponibile. Attendere il completamento del processo di upload.");
	        }
	        if (req.getErrorFile() == null || req.getErrorFile().length == 0) {
	            return FlowOperationResult.failure("Errori non riscontrati nell'ultimo upload ritorni regionale totale.");
	        }

	        String originalFileName = "errori_upload_ritorno_totale.log";
	        byte[] zipContent = FileUtility.zipBytes(originalFileName, req.getErrorFile());

	        ReturnDownloadDTO out = new ReturnDownloadDTO();
	        out.setExtractionId(req.getExtractionId());
	        out.setAvailable(Boolean.TRUE);
	        out.setFileName(originalFileName + ".zip");
	        out.setContentBase64(Base64.getEncoder().encodeToString(zipContent));
	        out.setFlowId(req.getFlowId());

	        return FlowOperationResult.success(out);

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER, "", e);
	        return FlowOperationResult.failure("Errore durante il download del file errori");
	    }
	}
	
}
