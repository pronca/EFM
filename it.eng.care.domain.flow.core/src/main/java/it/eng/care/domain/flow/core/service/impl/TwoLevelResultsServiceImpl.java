package it.eng.care.domain.flow.core.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;

import it.eng.care.domain.flow.b2b.model.ErrorFieldsDTO;
import it.eng.care.domain.flow.b2b.model.ErrorsDTO;
import it.eng.care.domain.flow.b2b.model.FieldErrorsDTO;
import it.eng.care.domain.flow.b2b.model.KeysDTO;
import it.eng.care.domain.flow.b2b.model.LockedMessageDTO;
import it.eng.care.domain.flow.b2b.model.ProcessSingleFlowResult;
import it.eng.care.domain.flow.b2b.model.RegionErrorModelDTO;
import it.eng.care.domain.flow.b2b.service.JsonFlowService;
import it.eng.care.domain.flow.b2b.service.RegionMessageService;
import it.eng.care.domain.flow.b2b.utils.ErrorService;
import it.eng.care.domain.flow.core.auditLog.UploadRitornoConverter;
import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.AppIdentityDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;
import it.eng.care.domain.flow.core.enumeration.RegionModelTypeEnum;
import it.eng.care.domain.flow.core.service.AppIdentityService;
import it.eng.care.domain.flow.core.service.FlowExportRequestService;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.TransactionalTwoLevelResultsService;
import it.eng.care.domain.flow.core.service.TwoLevelResultsService;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenField;
import it.eng.care.domain.flow.tabgen.dto.TabgenMap;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueMap;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.domain.flow.tabgen.utility.TabgenUtility;
import it.eng.care.platform.audit.api.model.privacymanager.annotation.PrivacyManagerLog;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventActionEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventCategoryEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityTypeEnum;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jxl.Sheet;
import jxl.Workbook;

public class TwoLevelResultsServiceImpl implements TwoLevelResultsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwoLevelResultsServiceImpl.class);
	
	@Autowired
	private TabgenService tabgenService;

	@Autowired
	private ConfigurationDAO configuration;

	@Autowired
	private FlowDAO flowDAO;
	
	@Autowired
	private TransactionalTwoLevelResultsService transactionalTwoLevelResultsService;
	
	@Autowired
	private FormFlowService formFlowService;
	
	@Autowired
	private LoggedUserService userService;
	
	@Autowired
	private FlowRegionUnionService flowRegionUnionService;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@Autowired
	private FlowExportRequestService flowExportService;
	
	@Autowired
	protected JsonFlowService jsonFlowService;
	
	@Autowired
	protected ErrorService errorService;
	
	@Autowired
	private RegionMessageService regionMessageService;
	
	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	private AppIdentityService appIdentityService;
	
	/**
	 * Caricamento esiti della validazione regionale
	 * 
	 * 0. caricamento configurazioni
	 * 1. verifica tipo file txt o zip
	 * 2. verifica corrispondenza dei nomi file con le configurazioni
	 * 3. per ogni file caricato
	 * 		3.1 carica e valida il tabgen
	 * 		3.2 crea la tabella del tabgen se non esiste
	 * 		3.3 valida i record in base al tabgen
	 * 4. salva la richiesta di caricamento del file
	 * 5. cancella eventuali ritorni associati all'estrazione e inserisci i nuovi
	 * 6. inserisci le segnalazioni
	 * 7. aggiorna i record con i dati forniti dalla regione
	 * 8. aggiorna tutti i record dell'estrazione con il campo state_send_region = 'ACCETTATO'
	 */
	@Override
	public FlowOperationResult<Boolean> uploadResults(File file, String flowLocalId, String flowRegionId, String versionId, String extractionId, boolean produzioneTot, boolean consolidata) throws Exception {
		FlowOperationResult<Boolean> result = FlowOperationResult.success(true);
		
		/************ 0 caricamento configurazioni **************/
		FlowDO flowRegion = (flowRegionId== null || flowRegionId.isBlank()) ? null : flowDAO.findById(flowRegionId).orElse(null);
		if (flowRegion != null) {
			String flowRegionName = flowRegion.getName();
			String suffix_reg_update = configuration.findByKeyId("suffix_reg_update").getValue();
			String suffix_reg_update_tot = configuration.findByKeyId("suffix_reg_update_tot").getValue();
			String suffix_reg_error = configuration.findByKeyId("suffix_reg_error").getValue();
			String suffix_reg_warning = configuration.findByKeyId("suffix_reg_warning").getValue();
			List<File> files = new ArrayList<File>();

			// nome del file contenente gli aggiornamenti regionali e della tabella
			// corrispondente
			String tableNameRegUpdate = flowRegionName + "_" + suffix_reg_update;
			tableNameRegUpdate = tableNameRegUpdate != null ? tableNameRegUpdate.toUpperCase() : "";
			
			String tableNameRegUpdateTot = flowRegionName + "_" + suffix_reg_update_tot;
			tableNameRegUpdateTot = tableNameRegUpdateTot != null ? tableNameRegUpdateTot.toUpperCase() : "";

			// nome del file contenente gli errori e le segnalazioni regionali e della
			// tabella corrispondente. Le segnalazioni e gli errori vengono salvati nella
			// tabella degli errori
			String tableNameRegErrors = flowRegionName + "_" + suffix_reg_error;
			tableNameRegErrors = tableNameRegErrors != null ? tableNameRegErrors.toUpperCase() : "";

			String tableNameRegWarnings = flowRegionName + "_" + suffix_reg_warning;
			tableNameRegWarnings = tableNameRegWarnings != null ? tableNameRegWarnings.toUpperCase() : "";

			List<String> errorsTableNames = new ArrayList<String>();
			errorsTableNames.add(tableNameRegWarnings);
			errorsTableNames.add(tableNameRegErrors);
			errorsTableNames.add(tableNameRegUpdate);
			errorsTableNames.add(tableNameRegUpdateTot);
			
			/************ 0 fine **************/
			
			/************ 1. verifica tipo file txt zip excel csv **************/
			FlowOperationResult<String> resultMT = FileUtility.checkMimeType(file, FileUtility.MEDIATYPE_ZIP, FileUtility.MEDIATYPE_TEXT, FileUtility.MEDIATYPE_XLS, FileUtility.MEDIATYPE_XLSX, FileUtility.MEDIATYPE_CSV, FileUtility.MEDIATYPE_GEN);
			if (!resultMT.getSuccess()) {
				return FlowOperationResult.failure(resultMT.getMessage());
			}
			/************ 1 fine **************/
			
			/************ 2 verifica corrispondenza dei nomi file con le configurazioni **************/
			String mimeType = resultMT.getResult();

			if (mimeType.equals(FileUtility.MEDIATYPE_ZIP)) {
				List<File> filesUnzipped = new ArrayList<File>();
				filesUnzipped = FileUtility.uncompressingFile(file.getAbsolutePath());
				if (filesUnzipped.isEmpty()) {
					return FlowOperationResult.failure("Il file zip caricato è vuoto");
				}
				for (File fileUnzipped : filesUnzipped) {
					result = FileUtility.checkFilename(errorsTableNames, fileUnzipped.getName(), FileUtility.TEXT, FileUtility.XLS, FileUtility.XLSX, FileUtility.CSV);
					if (!result.getSuccess()) {
						return result;
					}
					files.add(fileUnzipped);
				}
			} else {
				result = FileUtility.checkFilename(errorsTableNames, file.getName(), FileUtility.TEXT, FileUtility.XLS, FileUtility.XLSX, FileUtility.CSV);
				if (!result.getSuccess()) {
					return result;
				}
				files.add(file);
			}
			/************ 2 fine **************/
			
			/******************** recupero configurazione flusso *********************/
			FormFlowDTO configurationRegion = null;
			
			try {
				configurationRegion = retrieveConfiguration(flowRegionId, versionId);
			} catch (Exception ex) {
				LogUtil.logException(LOGGER, "", ex);
//				ex.printStackTrace();
			}
			
			if(configurationRegion == null) {
				return FlowOperationResult.failure("Errore durante il recupero della configurazione del flusso regionale");
			}
			
			/******************** recupero configurazione flusso fine *********************/
			
			/***INIZIO RECUPERO CONFIGUARAZIONI FLUSSO PER UPLOAD REQUEST DI RITORNO (FLOW_UPLOAD_CONF - FLOW_TIPOLOGIA_RITORNI)***/
			FlowDO flow = (flowLocalId== null || flowLocalId.isBlank()) ? null : flowDAO.findById(flowLocalId).orElse(null);
			TabgenValue tabGenValueFlowUploadConf = new TabgenValue();
			List<TabgenValue> tabGenValueFlowTipologiaRitorni = new ArrayList<TabgenValue>();
			BasePagingLoadResult<Tabgen> list = new BasePagingLoadResult<Tabgen>(null);
			if (flow!=null) {
				//FLOW_UPLOAD_CONF
				TabgenValueFilter filterFlowUploadConf = new TabgenValueFilter();
				filterFlowUploadConf.setTabgenId("FLOW_UPLOAD_CONF");
				list = tabgenDelegate.searchValue(filterFlowUploadConf);
				if (list != null && list.getTotalLength() > 0 && list.getList() != null
						&& !list.getList().isEmpty()) {
					Tabgen flowUploadConf = list.getList().get(0);
					if (flowUploadConf != null && "FLOW_UPLOAD_CONF".equals(flowUploadConf.getId())) {
						List<TabgenValue> tabgenValues = flowUploadConf.getTabgenValues();
						if (tabgenValues != null && !tabgenValues.isEmpty()) {
							for (TabgenValue tabgenValue : tabgenValues) {
								String flowName = tabgenValue.getField1();
								if (flow.getName().equalsIgnoreCase(flowName)) {
									tabGenValueFlowUploadConf=tabgenValue;
								}
							}
						}
					}
				}
				if (null==tabGenValueFlowUploadConf.getField1()) {
					return FlowOperationResult.failure("Configurare i parametri per il flusso di ritorno regionale del flusso "+flow.getName()+". Nome configurazione interna : FLOW_UPLOAD_CONF");
				}
				
				//FLOW_TIPOLOGIA_RITORNI
				TabgenValueFilter filterFlowTipologiaRitorni = new TabgenValueFilter();
				filterFlowTipologiaRitorni.setTabgenId("FLOW_TIPOLOGIA_RITORNI");
				list = tabgenDelegate.searchValue(filterFlowTipologiaRitorni);
				if (list != null && list.getTotalLength() > 0 && list.getList() != null
						&& !list.getList().isEmpty()) {
					Tabgen flowTipologiaRitorni = list.getList().get(0);
					if (flowTipologiaRitorni != null && "FLOW_TIPOLOGIA_RITORNI".equals(flowTipologiaRitorni.getId())) {
						List<TabgenValue> tabgenValues = flowTipologiaRitorni.getTabgenValues();
						if (tabgenValues != null && !tabgenValues.isEmpty()) {
							for (TabgenValue tabgenValue : tabgenValues) {
								String flowName = tabgenValue.getField1();
								if (flow.getName().equalsIgnoreCase(flowName)) {
									tabGenValueFlowTipologiaRitorni.add(tabgenValue);
								}
							}
						}
					}
				}
			} else {
				return FlowOperationResult.failure("Nessun flusso associato alla richiesta");
			}
			/***FINE RECUPERO CONFIGUARAZIONI FLUSSO PER UPLAD REQUEST DI RITORNO (FLOW_UPLOAD_CONF)***/
			
			/************ 3 **************/
			
			/***************** 3.1 carica e valida il tabgen - 3.2 crea la tabella del tabgen se non esiste **********************/
			FlowOperationResult<TabgenMap> checkTabgenErrors = new FlowOperationResult<TabgenMap>();
			FlowOperationResult<TabgenMap> checkTabgenUpdate = new FlowOperationResult<TabgenMap>();
			FlowOperationResult<TabgenMap> checkTabgenUpdateTot = new FlowOperationResult<TabgenMap>();
			
			Boolean checkErrorsAndWarnings = false;
			Boolean checkUpdate = false;
			Boolean checkUpdateTot = false;
			String flowName = tabGenValueFlowUploadConf.getField1();
			String extensionUploadConf = (null==tabGenValueFlowUploadConf.getField3() || tabGenValueFlowUploadConf.getField3().isEmpty() ? FileUtility.TEXT : tabGenValueFlowUploadConf.getField3());
			if (extensionUploadConf.equalsIgnoreCase(FileUtility.TEXT) || extensionUploadConf.equalsIgnoreCase(FileUtility.XLS) || extensionUploadConf.equalsIgnoreCase(FileUtility.XLSX) || extensionUploadConf.equalsIgnoreCase(FileUtility.CSV)) {
				for (File currentFile : files) {
					String filename = currentFile.getName();
					int index = filename.lastIndexOf('.');
				    if(index > 0) {
				    	String extension = filename.substring(index + 1);
				    	if (extension.equalsIgnoreCase(extensionUploadConf)) {
							if (filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.TEXT) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.TEXT) || filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.XLS) || filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.XLSX) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.XLS) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.XLSX) || filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.CSV) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.CSV)) {
								checkErrorsAndWarnings = true;
							} else if (filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.TEXT) || filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.XLS) || filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.XLSX) || filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.CSV)) {
								checkUpdate = true;
							} else if(filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.TEXT) || filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.XLS) || filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.XLSX) || filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.CSV)) {
								checkUpdateTot = true;
							}
				    	} else {
				    		return FlowOperationResult.failure("Per il flusso "+flowName+" il file di ritorno regionale è stato configurato con formato "+extensionUploadConf+". Modificare la configurazione qualora si voglia utilizzare il formato "+extension+"");
				    	}
				    } else {
				    	return FlowOperationResult.failure("Errore nella definizione della estensione del file "+filename+" di ritorno regionale per il flusso "+flowName+".");
				    }
				}
			} else {
				return FlowOperationResult.failure("Errore nella configurazione del tipo di estensione del file di ritorno regionale per il flusso "+flowName+". Le estensioni gestite sono "+FileUtility.TEXT+" "+FileUtility.XLS+" "+FileUtility.XLSX+" "+FileUtility.CSV+"");
			}
			
			if(checkErrorsAndWarnings) {
				checkTabgenErrors = checkAndLoadTabgenErrors(tableNameRegErrors, false);
				if(!checkTabgenErrors.getSuccess()) {
					return FlowOperationResult.failure(checkTabgenErrors.getMessage());
				}
				
				createTableIfNotExists(checkTabgenErrors.getResult());
			}
			
			if(checkUpdate) {
				checkTabgenUpdate = checkAndLoadTabgenErrors(tableNameRegUpdate, true);
				if(!checkTabgenUpdate.getSuccess()) {
					return FlowOperationResult.failure(checkTabgenUpdate.getMessage());
				}
			}
			
			if(checkUpdateTot) {
				checkTabgenUpdateTot = checkAndLoadTabgenErrors(tableNameRegUpdateTot, true);
				if(!checkTabgenUpdateTot.getSuccess()) {
					return FlowOperationResult.failure(checkTabgenUpdateTot.getMessage());
				}
			}
			
			/***************** 3.1 3.2 fine **********************/
			
			/******************** 3.3 valida i record in base al tabgen ************************/
			
			for (File currentFile : files) {
				String filename = currentFile.getName();
				if (filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.TEXT) || filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.XLS) || filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.XLSX) || filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.CSV)) {
					result = validateFileRecors(currentFile, checkTabgenErrors.getResult(), extractionId, tabGenValueFlowUploadConf);
				} else if (filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.TEXT) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.XLS) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.XLSX) || filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.CSV)) {
					checkTabgenErrors.getResult().setId(tableNameRegWarnings);
					result = validateFileRecors(currentFile, checkTabgenErrors.getResult(), extractionId, tabGenValueFlowUploadConf);
					checkTabgenErrors.getResult().setId(tableNameRegErrors);
				} else {
					if(checkUpdateTot) {
						result = validateFileRecors(currentFile, checkTabgenUpdateTot.getResult(), extractionId, tabGenValueFlowUploadConf);
					}else {
						result = validateFileRecors(currentFile, checkTabgenUpdate.getResult(), extractionId, tabGenValueFlowUploadConf);
					}
				}
				
				if(!result.getSuccess()) {
					return result;
				}
				
			}
			
			/***************** 3.3 fine **********************/
			
			/***************** 3 fine **********************/
			if(produzioneTot) {
				MyC myCallable = new MyC(produzioneTot, checkErrorsAndWarnings, files, tableNameRegUpdate, tableNameRegUpdateTot, tableNameRegErrors, 
						tableNameRegWarnings, configurationRegion, extractionId, flowRegionId, flowLocalId, versionId, checkTabgenErrors, checkTabgenUpdate, checkTabgenUpdateTot, file, this, tabGenValueFlowUploadConf, tabGenValueFlowTipologiaRitorni, consolidata);
	    			Thread thread = new Thread() {
					@Transactional
					public void run() {
						try {
							myCallable.call();
						} catch (Exception e) {
							LogUtil.logException(LOGGER, "", e);
//							e.printStackTrace();
						} finally {
							if(file != null) {
								file.delete();
							}
						}
					}

	                };
	    			thread.start();
//	    			thread.join();
			}else {
				result = uploadResultsThread(produzioneTot, checkErrorsAndWarnings, files, tableNameRegUpdate, tableNameRegUpdateTot, tableNameRegErrors, 
						tableNameRegWarnings, configurationRegion, extractionId, flowRegionId, flowLocalId, versionId, checkTabgenErrors, checkTabgenUpdate, checkTabgenUpdateTot, file, tabGenValueFlowUploadConf, tabGenValueFlowTipologiaRitorni, consolidata);
			}
			
		} else {
			return FlowOperationResult.failure("Nessun flusso associato alla richiesta");	
		}

		return result;
	}
	
	/**
	 * Validazione tabgen e caricamento
	 * 1. verifica esistenza tabgen
	 * 2. controllo struttura tabgen (NOME_CAMPO, POSIZIONE, LUNGHEZZA)
	 * 3. controllo valori tabgen (obb. campi e formato)
	 * @param tableNameRegErrors
	 * @return
	 * @throws Exception 
	 */
	@Override
	public FlowOperationResult<TabgenMap> checkAndLoadTabgenErrors(String tableNameRegErrors, Boolean updateTable) throws Exception {
		
		TabgenMap tabgenMap = new TabgenMap();
		tabgenMap.setId(tableNameRegErrors);
		List<TabgenValueMap> tabgenValueList = new ArrayList<TabgenValueMap>();
		tabgenMap.setTabgenValueList(tabgenValueList);
		
		List<TabgenField> tableFieldsRegErrors = tabgenService.searchFieldsByTabgenId(tableNameRegErrors);
		if (tableFieldsRegErrors == null || tableFieldsRegErrors.isEmpty()) {
			return FlowOperationResult.failure("Configurazione del tracciato degli errori regionali " + tableNameRegErrors + " assente.");
		}
		
		Boolean nomeCampoAbsent = true, lunghezzaAbsent = true, posizioneAbsent = true, campoControlloAbsent = true, campoChiaveAbsent = true;
		String fieldCampo = "", fieldLunghezza = "", fieldPosizione = "", fieldControllo = "", fieldChiave = "";
		
		for(TabgenField tabgenField : tableFieldsRegErrors) {
			
			if(tabgenField.getDescription().equalsIgnoreCase("NOME_CAMPO")) {
				nomeCampoAbsent = false;
				fieldCampo = "getField" + tabgenField.getProgressive();
			} else if(tabgenField.getDescription().equalsIgnoreCase("LUNGHEZZA")) {
				lunghezzaAbsent= false;
				fieldLunghezza = "getField" + tabgenField.getProgressive();
			} else if(tabgenField.getDescription().equalsIgnoreCase("POSIZIONE")) {
				posizioneAbsent = false;
				fieldPosizione = "getField" + tabgenField.getProgressive();
			} else if(tabgenField.getDescription().equalsIgnoreCase("CAMPO_CONTROLLO")) {
				campoControlloAbsent = false;
				fieldControllo = "getField" + tabgenField.getProgressive();
			} else if(tabgenField.getDescription().equalsIgnoreCase("CAMPO_CHIAVE")) {
				campoChiaveAbsent = false;
				fieldChiave = "getField" + tabgenField.getProgressive();
			}
			
		}
		
		if(!updateTable) {
			campoControlloAbsent = false;
		}
		
		if(nomeCampoAbsent || lunghezzaAbsent || posizioneAbsent || campoControlloAbsent||campoChiaveAbsent) {
			String message = "Configurazione del tracciato regionale dei ritorni " + tableNameRegErrors + " errato.";
			if(nomeCampoAbsent) {
				message += " Campo 'NOME_CAMPO' assente.";
			}
			if(lunghezzaAbsent) {
				message += " Campo 'LUNGHEZZA' assente.";
			}
			if(posizioneAbsent) {
				message += " Campo 'POSIZIONE' assente.";
			}
			if(campoControlloAbsent) {
				message += " Campo 'CAMPO_CONTROLLO' assente.";
			}
			if(campoChiaveAbsent) {
				message += " Campo 'CAMPO_CHIAVE' assente.";
			}
			
			return FlowOperationResult.failure(message);			
		}
		
		TabgenValueFilter filter = new TabgenValueFilter();
		filter.setTabgenId(tableNameRegErrors);
		List<TabgenValue> tableValuesRegErrors = tabgenService.searchTabgenValueDTOByFilter(filter);
		if (tableValuesRegErrors == null || tableValuesRegErrors.isEmpty()) {
			return FlowOperationResult.failure("Configurazione del tracciato degli errori regionali " + tableNameRegErrors + " incompleta. Aggiungere valori.");
		}
		
		
		for(TabgenValue tabgenValue : tableValuesRegErrors) {
			FlowOperationResult<String> result = checkTabgenValue("LUNGHEZZA", fieldLunghezza, tabgenValue, tableNameRegErrors, "[0-9]*");
			if(!result.getSuccess()) {
				return FlowOperationResult.failure(result.getMessage());
			}
			
			TabgenValueMap tabgenValueMap = new TabgenValueMap();
			
			tabgenValueMap.setLength(new Integer(result.getResult()));
			
			result = checkTabgenValue("POSIZIONE", fieldPosizione, tabgenValue, tableNameRegErrors, "[0-9]*");
			if(!result.getSuccess()) {
				return FlowOperationResult.failure(result.getMessage());
			}
			
			tabgenValueMap.setPosition(new Integer(result.getResult()));
			
			result = checkTabgenValue("NOME_CAMPO", fieldCampo, tabgenValue, tableNameRegErrors, null);
			if(!result.getSuccess()) {
				return FlowOperationResult.failure(result.getMessage());
			}
			
			tabgenValueMap.setFieldName(result.getResult());
						
			result = checkTabgenValue("CAMPO_CHIAVE", fieldChiave, tabgenValue, tableNameRegErrors, null);
			if(!result.getSuccess()) {
				return FlowOperationResult.failure(result.getMessage());
			}
			
			tabgenValueMap.setIsPk(result.getResult().equals("1"));
			
			if(updateTable) {
				
				result = checkTabgenValue("CAMPO_CONTROLLO", fieldControllo, tabgenValue, tableNameRegErrors, "[0-9]{1}");
				if(!result.getSuccess()) {
					return FlowOperationResult.failure(result.getMessage());
				}
				
				tabgenValueMap.setControlField(result.getResult().equals("1"));
			}
			
			tabgenValueList.add(tabgenValueMap);
			
		}
		
		tabgenMap.getTabgenValueList().sort(new Comparator<TabgenValueMap>() {
			@Override
			public int compare(TabgenValueMap o1, TabgenValueMap o2) {
				return o1.getPosition().compareTo(o2.getPosition());
			}
		});
		
		return FlowOperationResult.success(tabgenMap);
	}
	
	private FlowOperationResult<String> checkTabgenValue(String fieldName, String methodName, TabgenValue tabgenValue, String tabgen, String pattern) throws Exception {
		Method getMethod = TabgenUtility.getMethod(TabgenValue.class, methodName);
		String fieldValue = "";
		if (getMethod != null) {
			fieldValue = (String) getMethod.invoke(tabgenValue);
			if(fieldValue == null || fieldValue.isEmpty() || pattern != null && !fieldValue.matches(pattern)) {
				return FlowOperationResult.failure("Configurazione del tracciato degli errori regionali " + tabgen + " errato." //
						+ " Valorizzare correttamente il campo " + fieldName + " del tracciato degli errori regionali " + tabgen + ".");
			}
		} else {
			return FlowOperationResult.failure("Si è verificato un errore durante l'elaborazione del tracciato " + tabgen + ".");
		}
		
		return FlowOperationResult.success(fieldValue);
	}

	/**
	 * Controlla la lunghezza delle righe del file per i txt 
	 * Controlla che il numero delle colonne sia corrispondente al numero di record del tracciato corrispondente per i files excel e csv
	 * 
	 * @param file
	 * @param tabgenId
	 * @param tabgenValues
	 * @param tabgenFields
	 * @return
	 */
	private FlowOperationResult<Boolean> validateFileRecors(File file, TabgenMap tabgenMap, String extractionId, TabgenValue tabGenValueFlowUploadConf) {
		FlowOperationResult<Boolean> result = FlowOperationResult.success(true);
		
		TabgenValueMap tabgenValue = tabgenMap.getTabgenValueList().get(tabgenMap.getTabgenValueList().size() - 1);
		Integer tabgenRowLengthText = tabgenValue.getLength() + tabgenValue.getPosition() - 1;
		Integer tabgenRowLength = tabgenMap.getTabgenValueList().size();
		String skipHeader = Optional.ofNullable(tabGenValueFlowUploadConf.getField2()).orElse("N");
		
		String fileName = file.toString();
		String extension="";
		
	    int index = fileName.lastIndexOf('.');
	    if(index > 0) {
	    	extension = fileName.substring(index + 1);
	    	if (extension.equalsIgnoreCase(FileUtility.TEXT))  {
	    		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    			Integer seek = 0;
	    			String line = br.readLine();
	    			
	    			while (line != null) {
	    				seek++;
	    				//accettando file vuoti controllo solo se la lunghezza della line è diversa da 0
	    				if (line.length()!=0) {
	    					if (line.length() != tabgenRowLengthText) {
		    					return FlowOperationResult.failure("Lunghezza della riga " + seek
		    							+ " del tracciato degli errori regionali " + tabgenMap.getId() + " errata. Lunghezza corretta = " + tabgenRowLengthText);
		    				}
	    				}
	    				line = br.readLine();
	    			}
	    			
	    		} catch (Exception ex) {
	    			LogUtil.logException(LOGGER, "Errore in fase di lettura dei file", ex);
//	    			ex.printStackTrace();
	    			result = FlowOperationResult.failure("Errore in fase di lettura dei file");
	    		}
	    	} else if (extension.equalsIgnoreCase(FileUtility.XLS)){
	    		try {
	    			Workbook workbook = Workbook.getWorkbook(file);
	    			Sheet sheet = workbook.getSheet(0);    			
	    			
	    			int noOfColumns = sheet.getColumns();
	    			//se il file è vuoto non controllo la lunghezza
	    			if (noOfColumns!=0) {
	    				//controllo il numero di colonne solo se lo skipheard=S il che significa che i file hanno intestazione colonna
		                if (noOfColumns != tabgenMap.getTabgenValueList().size() && "S".equals(skipHeader))
		                {
		                	return FlowOperationResult.failure("Numero di colonne del file " + noOfColumns
	    							+ " del tracciato degli errori regionali " + tabgenMap.getId() + " errata. Numero di colonne corretto = " + tabgenRowLength);
		                }
	    			}

	           	} catch (Exception ex) {
	           		LogUtil.logException(LOGGER, "Errore in fase di lettura dei file", ex);
//	    			ex.printStackTrace();
	    			result = FlowOperationResult.failure("Errore in fase di lettura dei file");
	    		}
	    	} else if (extension.equalsIgnoreCase(FileUtility.XLSX)){
	    		try (FileInputStream filei = new FileInputStream(file)){
	    			XSSFWorkbook workbook = new XSSFWorkbook(filei);
	    			XSSFSheet sheet = workbook.getSheetAt(0);		
	    			
	    			if (null!=sheet.getRow(0)) {
	    				int noOfColumns = sheet.getRow(0).getLastCellNum();
	    				//se il file è vuoto non controllola lunghezza
	    				if (noOfColumns!=0) {
	    					//controllo il numero di colonne solo se lo skipheard=S il che significa che i file hanno intestazione colonna
			                if (noOfColumns != tabgenMap.getTabgenValueList().size() && "S".equals(skipHeader))
			                {
			                	return FlowOperationResult.failure("Numero di colonne del file " + noOfColumns
		    							+ " del tracciato degli errori regionali " + tabgenMap.getId() + " errata. Numero di colonne corretto = " + tabgenRowLength);
			                }
	    				}
	    			}

	           	} catch (Exception ex) {
	           		LogUtil.logException(LOGGER, "Errore in fase di lettura dei file", ex);
//	    			ex.printStackTrace();
	    			result = FlowOperationResult.failure("Errore in fase di lettura dei file");
	    		}
	    	} else if (extension.equalsIgnoreCase(FileUtility.CSV)){
	    		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    		    
	    			List<List<String>> lines = br.lines().map(line -> Arrays.asList(line.split(";")))
	    				      .collect(Collectors.toList());
	    			
	    			//se il file è vuoto non controllo la lunghezza
	    			boolean firstLineEmpty = lines != null
	    			        && lines.size() == 1
	    			        && lines.get(0) != null
	    			        && (lines.get(0).isEmpty()
	    			            || (lines.get(0).size() == 1 && "".equals(lines.get(0).get(0))));
	    			boolean fileVuoto = lines == null || lines.isEmpty() || firstLineEmpty;
	    			
	    			if (!fileVuoto) {
	    				//controllo il numero di colonne solo se lo skipheard=S il che significa che i file hanno intestazione colonna
		    			if (lines.get(0).size() != tabgenMap.getTabgenValueList().size() && "S".equals(skipHeader))
		                {
		                	return FlowOperationResult.failure("Numero di colonne del file " + lines
	    							+ " del tracciato degli errori regionali " + tabgenMap.getId() + " errata. Numero di colonne corretto = " + tabgenRowLength);
		                }
	    			}
	    			
	    		} catch (Exception ex) {
	    			LogUtil.logException(LOGGER, "Errore in fase di lettura dei file", ex);
//	    			ex.printStackTrace();
	    			result = FlowOperationResult.failure("Errore in fase di lettura dei file");
	    		}
	    	} else {
	    		result = FlowOperationResult.failure("Estensione file non ammessa");
	    	}
	    		
	    } else {
	    	result = FlowOperationResult.failure("Errore in fase di lettura dei file");
	    }
		
		return result;
		
	}
	
	private FormFlowDTO retrieveConfiguration(String flowId, String versionId) {
		BaseSearchInput searchInput = new BaseSearchInput();
		searchInput.setValue("flowId", flowId);
		searchInput.setValue("versionId", versionId);
		Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFiltered(searchInput);
		FormFlowDTO configuration = searchResults.getFirst().get(0);
		return configuration;
	}
	
	@Override
	public void createTableIfNotExists(TabgenMap tabgen) throws Exception {
		Boolean tableExists = true;
		
		try {
			tableExists = tabgenService.tableExists(tabgen.getId());
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
			tableExists = false;
		}
		
		if(!tableExists) {
			tabgenService.dropTabgenView("drop view " + tabgen.getId());
			TabgenMap tabgenExt = new TabgenMap();
			tabgenExt.setId(tabgen.getId());
			
			tabgenExt.setTabgenValueList(new ArrayList<TabgenValueMap>());
			tabgenExt.getTabgenValueList().addAll(tabgen.getTabgenValueList());
			
			TabgenValueMap extMap = new TabgenValueMap();
			extMap.setFieldName("extraction_id");
			extMap.setLength(256);
			
			TabgenValueMap errorType = new TabgenValueMap();
			errorType.setFieldName("severity");
			errorType.setLength(256);
			
			TabgenValueMap receivingDate = new TabgenValueMap();
			receivingDate.setFieldName("RECEIVING_DATE");
			receivingDate.setLength(256);
			
			tabgenExt.getTabgenValueList().add(extMap);
			tabgenExt.getTabgenValueList().add(errorType);
			tabgenExt.getTabgenValueList().add(receivingDate);
			
			tabgenService.createTabgenTable(tabgenExt);
		}
	}
	
	protected FlowOperationResult<Boolean> uploadResultsThread(boolean produzioneTot, boolean checkErrorsAndWarnings, List<File> files, String tableNameRegUpdate, String tableNameRegUpdateTot
			, String tableNameRegErrors, String tableNameRegWarnings, FormFlowDTO configurationRegion, String extractionId, String flowRegionId, String flowLocalId, String versionId, FlowOperationResult<TabgenMap> checkTabgenErrors,
	FlowOperationResult<TabgenMap> checkTabgenUpdate, FlowOperationResult<TabgenMap> checkTabgenUpdateTot, File file, TabgenValue tabGenValueFlowUploadConf, List<TabgenValue> tabGenValueFlowTipologiaRitorni, boolean consolidata) throws IOException, SQLException {
		
		FlowOperationResult<Boolean> result = FlowOperationResult.success(true);
		boolean processedScartoFile = false;
		boolean processedWarningFile = false;

		/***************** 4 salva la richiesta di caricamento ********************/
		UploadReturnsRequestDO request = new UploadReturnsRequestDO();
		try {
			//se si tratta di upload su dashboard (importazione TOTALE), elimino eventuali precedenti upload di tipologia TOTALE per il flusso di riferimento in modo da gestirne sempre e solo uno a livello FE 
			if (produzioneTot) {
		        transactionalTwoLevelResultsService.deleteUploadReturnsRequestByExtractionIdAndFlowId("TOTALE", flowLocalId);
		    }
		
			request.setId(UUID.randomUUID().toString());
			request.setExtractionId(extractionId);
			request.setCreationDate(new Date());
			request.setFile(FileUtils.readFileToByteArray(file));
			request.setFileNameOrigin(file.getName());
			request.setFlowId(flowLocalId);
			request.setUserId(userService.getCurrentUser().getUsername());
			if (consolidata) {
				request.setTipoValidazioneReg("DEFINITIVA");
			} else {
				request.setTipoValidazioneReg("PROVVISORIA");
			}
			
			transactionalTwoLevelResultsService.saveRequest(request);
	
			/***************** 4 fine ********************/
			
			/************ 5 cancella eventuali ritorni associati all'estrazione e inserisci i nuovi **************/
			if(checkErrorsAndWarnings) {
				transactionalTwoLevelResultsService.deleteReturnsFromExtraction(tableNameRegErrors, extractionId);
			}
	
			/************ 6 inserisci le segnalazioni - 7 aggiorna i record con i dati forniti dalla regione **************/
			for (File currentFile : files) {
				String filename = currentFile.getName();
	
				if (filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.TEXT)
						|| filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.XLS)
						|| filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.XLSX)
						|| filename.equalsIgnoreCase(tableNameRegUpdate + "." + FileUtility.CSV)) {
	
					result = transactionalTwoLevelResultsService.updateRecordsFromFile(
							configurationRegion, extractionId, flowRegionId, versionId, currentFile,
							checkTabgenUpdate.getResult(), false, tabGenValueFlowUploadConf);
	
				} else if (filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.TEXT)
						|| filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.XLS)
						|| filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.XLSX)
						|| filename.equalsIgnoreCase(tableNameRegUpdateTot + "." + FileUtility.CSV)) {
	
					result = transactionalTwoLevelResultsService.updateRecordsFromFile(
							configurationRegion, extractionId, flowRegionId, versionId, currentFile,
							checkTabgenUpdateTot.getResult(), true, tabGenValueFlowUploadConf);
	
				} else if (filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.TEXT)
						|| filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.XLS)
						|| filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.XLSX)
						|| filename.equalsIgnoreCase(tableNameRegErrors + "." + FileUtility.CSV)) {
	
					result = transactionalTwoLevelResultsService.insertRecordsFromFile(
							configurationRegion, currentFile, checkTabgenErrors.getResult(),
							extractionId, "SCARTO", tabGenValueFlowUploadConf, tabGenValueFlowTipologiaRitorni);
	
					if (result.getSuccess()) {
						processedScartoFile = true;
					}
	
				} else if (filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.TEXT)
						|| filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.XLS)
						|| filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.XLSX)
						|| filename.equalsIgnoreCase(tableNameRegWarnings + "." + FileUtility.CSV)) {
	
					result = transactionalTwoLevelResultsService.insertRecordsFromFile(
							configurationRegion, currentFile, checkTabgenErrors.getResult(),
							extractionId, "SEGNALAZIONE", tabGenValueFlowUploadConf, tabGenValueFlowTipologiaRitorni);
					
					if (result.getSuccess()) {
						processedWarningFile = true;
					}
				}
	
				if(!result.getSuccess()) {
					// Salvataggio file errori nei casi di produzione totale in quanto eseguita dal thread e non intercettata dal FE
					try {
					    List<String> allErrors = new ArrayList<>();
					    // se hai già result.getMessage() o collezioni msgErrors, usa quelle reali
					    if (result != null && !result.getSuccess() && result.getMessages() != null && !result.getMessages().isEmpty()) {
					        allErrors.addAll(result.getMessages());
					    }
					    if (!allErrors.isEmpty()) {
					        String errorText = String.join(System.lineSeparator(), allErrors);
					        request.setErrorFile(errorText.getBytes(java.nio.charset.StandardCharsets.UTF_8));
					    }
					    request.setHasErrors(Boolean.TRUE);
					} catch (Exception e) {
					    LogUtil.logException(LOGGER, "", e);
					}
					return result;
				} else {
					request.setHasErrors(Boolean.FALSE);
				}
			}
		} catch (Exception e) {
		    LogUtil.logException(LOGGER, "", e);
		} finally {
			//salvo in qualsiasi caso la data del termine processo di upload per gestire download file e log lato FE
			request.setEndProcessDate(new Date());
			transactionalTwoLevelResultsService.saveRequest(request);
		}
		/************ 4 5 6 fine **************/
		
		/************ 7 aggiorna tutti i record dell'estrazione con il campo state_send_region = 'ACCETTATO' *************/
		Boolean res;
		if(!produzioneTot) {
			res = transactionalTwoLevelResultsService.updateSendRegionStatus(
					extractionId,
					configurationRegion,
					checkErrorsAndWarnings,
					checkTabgenErrors != null ? checkTabgenErrors.getResult() : null,
					consolidata);
		} else {
			return result;
		}

		if(!res) {
			return FlowOperationResult.failure("Attenzione! Si è verificato un errore durante l'aggiornamento dei dati regionali");
		}

		FlowExportRequestDO extractionRequest = flowExportService.retriveOne(extractionId);
		if (extractionRequest == null) {
		    return FlowOperationResult.failure("Impossibile recuperare l'estrazione " + extractionId);
		}

		boolean processedRegionFile = processedScartoFile || processedWarningFile;

		if (processedRegionFile) {
		    if (Boolean.TRUE.equals(extractionRequest.getConsolidata())) {

		        // riprocesso eventuali messaggi bloccati nella FM_LOCKED_MESSAGE
		        FlowOperationResult<Boolean> reprocessResult = reprocessPendingLockedMessages(extractionId);
		        if (!reprocessResult.getSuccess()) {
		            return reprocessResult;
		        }

		        // accodo su FM_REGION_ERRORS solo se esiste la url IDENTITY_REG_ERRORS
		        boolean identityRegErrorsConfigured = isIdentityRegErrorsConfigured(configurationRegion, extractionRequest);

		        if (!identityRegErrorsConfigured) {
		            LOGGER.info(
		                    "Accodamento risultati regionali non eseguito per extractionId={} flowName={} perché FM_APP_IDENTITY.IDENTITY_REG_ERRORS non è configurato",
		                    extractionId,
		                    extractionRequest.getFlow() != null ? extractionRequest.getFlow().getName() : null
		            );
		            return result;
		        }

		        // carico i dati del file di ritorno regionali (SCARTO/SEGNALAZIONE) nella tabella FM_REGION_ERRORS per essere inviati ad APP tramite ws
		        FlowOperationResult<Boolean> queueResult =
		                queueRegionErrorsToSend(extractionId, processedScartoFile, processedWarningFile);
		        if (!queueResult.getSuccess()) {
		            return queueResult;
		        }

		        // carico nella tabella FM_REGION_ERRORS tutte le altre pratiche valide per la estrazione di riferimento
		        FlowOperationResult<Boolean> queueValidResult =
		                queueRegionValidToSend(extractionId);
		        if (!queueValidResult.getSuccess()) {
		            return queueValidResult;
		        }

		    } else {
		        LOGGER.info("Riprocessamento/Accodamento risultati regionali non eseguito per extractionId={} perché CONSOLIDATA != 1", extractionId);
		    }
		}

		return result;
	}
	
	private boolean isIdentityRegErrorsConfigured(FormFlowDTO configurationRegion, FlowExportRequestDO extractionRequest) {
	    if (configurationRegion == null || extractionRequest == null || extractionRequest.getFlow() == null) {
	        return false;
	    }

	    String flowName = extractionRequest.getFlow().getName();

	    try {
	        List<String> codiciAzienda = findCodiciAziendaByExtraction(configurationRegion, extractionRequest.getId());

	        // 1) controllo configurazione specifica per almeno una azienda dell'estrazione
	        if (codiciAzienda != null) {
	            for (String codiceAzienda : codiciAzienda) {
	                if (codiceAzienda == null || codiceAzienda.isBlank()) {
	                    continue;
	                }

	                AppIdentityDO appIdentity = appIdentityService.searchApp(flowName, codiceAzienda);
	                if (appIdentity != null
	                        && appIdentity.getIdentityRegErrors() != null
	                        && !appIdentity.getIdentityRegErrors().isBlank()) {
	                    return true;
	                }
	            }
	        }

	        // 2) fallback generico condiviso tra tutte le aziende del flow
	        AppIdentityDO genericAppIdentity = appIdentityService.searchApp(flowName);
	        return genericAppIdentity != null
	                && genericAppIdentity.getIdentityRegErrors() != null
	                && !genericAppIdentity.getIdentityRegErrors().isBlank();

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER,
	                "Errore nel recupero di FM_APP_IDENTITY.IDENTITY_REG_ERRORS per extractionId="
	                        + extractionRequest.getId() + " flowName=" + flowName, e);
	        return false;
	    }
	}
	
	@SuppressWarnings("unchecked")
	private List<String> findCodiciAziendaByExtraction(FormFlowDTO configurationRegion, String extractionId) {
	    String sql = "SELECT DISTINCT CODICEAZIENDA "
	               + "FROM FM_FLOW_" + configurationRegion.getName() + "_0 "
	               + "WHERE EXTRACTION_ID = :extractionId "
	               + "AND CODICEAZIENDA IS NOT NULL";

	    return entityManager.createNativeQuery(sql)
	            .setParameter("extractionId", extractionId)
	            .getResultList();
	}
	
	private FlowOperationResult<Boolean> queueRegionValidToSend(String extractionId) {
	    try {
	        FlowExportRequestDO extraction = flowExportService.retriveOne(extractionId);
	        if (extraction == null || extraction.getFlow() == null) {
	            return FlowOperationResult.failure("Nessun flusso associato alla extraction " + extractionId);
	        }

	        if (!Boolean.TRUE.equals(extraction.getConsolidata())) {
	            LOGGER.info("Accodamento risultati regionali nella tabella FM_REGION_ERRORS non eseguito per extractionId={} perché NON CONSOLIDATA", extractionId);
	            return FlowOperationResult.success(true);
	        }

	        String flowName = extraction.getFlow().getName();

	        FlowOperationResult<RegionErrorModelDTO> modelResult = buildRegionValidModel(extractionId, flowName);
	        if (!modelResult.getSuccess()) {
	            return FlowOperationResult.failure(modelResult.getMessage());
	        }

	        RegionErrorModelDTO model = modelResult.getResult();
	        if (model == null || model.getErrors() == null || model.getErrors().isEmpty()) {
	            LOGGER.info("Nessuna pratica da accodare nella tabella FM_REGION_ERRORS per extractionId={}", extractionId);
	            return FlowOperationResult.success(true);
	        }

	        ObjectMapper mapper = new ObjectMapper();

	        for (ErrorsDTO errorItem : model.getErrors()) {
	            RegionErrorModelDTO singleModel = new RegionErrorModelDTO();
	            singleModel.setIdRequest(model.getIdRequest());
	            singleModel.setDate(model.getDate());
	            singleModel.setCodiceAzienda(model.getCodiceAzienda());
	            singleModel.setStatusRegion(RegionModelTypeEnum.VALID.toString());
	            singleModel.setErrors(new ArrayList<>());

	            singleModel.getErrors().add(errorItem);

	            String keyMessage = buildKeyMessage(errorItem.getKeys());
	            String jsonString = mapper.writeValueAsString(singleModel);

	            regionMessageService.insertOrUpdatePendingEvent(singleModel, flowName, jsonString, keyMessage);
	        }

	        return FlowOperationResult.success(true);

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER, "Errore accodamento risultati regionali nella tabella FM_REGION_ERRORS per extractionId=" + extractionId, e);
	        return FlowOperationResult.failure(
	                "Errore durante l'accodamento dei risultati regionali nella tabella FM_REGION_ERRORS per extractionId=" + extractionId
	        );
	    }
	}
	
	private FlowOperationResult<Boolean> queueRegionErrorsToSend(
	        String extractionId,
	        boolean processedScartoFile,
	        boolean processedWarningFile) {
	    try {
	        FlowExportRequestDO extraction = flowExportService.retriveOne(extractionId);
	        if (extraction == null || extraction.getFlow() == null) {
	            return FlowOperationResult.failure("Nessun flusso associato alla extraction " + extractionId);
	        }

	        if (!Boolean.TRUE.equals(extraction.getConsolidata())) {
	            LOGGER.info("Accodamento risultati regionali nella tabella FM_REGION_ERRORS non eseguito per extractionId={} perché CONSOLIDATA != 1", extractionId);
	            return FlowOperationResult.success(true);
	        }

	        String flowName = extraction.getFlow().getName();

	        FlowOperationResult<RegionErrorModelDTO> modelResult =
	                buildRegionErrorModel(extractionId, flowName, processedScartoFile, processedWarningFile);

	        if (!modelResult.getSuccess()) {
	            return FlowOperationResult.failure(modelResult.getMessage());
	        }

	        RegionErrorModelDTO model = modelResult.getResult();
	        if (model == null || model.getErrors() == null || model.getErrors().isEmpty()) {
	            LOGGER.info("Nessun record trovato nelle tabelle di errori/segnalazioni regionali per extractionId={}", extractionId);
	            return FlowOperationResult.success(true);
	        }

	        ObjectMapper mapper = new ObjectMapper();

	        for (ErrorsDTO errorItem : model.getErrors()) {
	            RegionErrorModelDTO singleModel = new RegionErrorModelDTO();
	            singleModel.setIdRequest(model.getIdRequest());
	            singleModel.setDate(model.getDate());
	            singleModel.setCodiceAzienda(model.getCodiceAzienda());
	            singleModel.setStatusRegion(model.getStatusRegion());
	            singleModel.setErrors(new ArrayList<>());

	            singleModel.getErrors().add(errorItem);

	            String keyMessage = buildKeyMessage(errorItem.getKeys());
	            String jsonString = mapper.writeValueAsString(singleModel);

	            regionMessageService.insertOrUpdatePendingEvent(singleModel, flowName, jsonString, keyMessage);
	        }

	        return FlowOperationResult.success(true);

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER, "Errore accodamento risultati regionali nella tabella FM_REGION_ERRORS per extractionId=" + extractionId, e);
	        return FlowOperationResult.failure(
	                "Errore durante l'accodamento dei risultati regionali nella tabella FM_REGION_ERRORS per extractionId=" + extractionId
	        );
	    }
	}
	
	private String buildKeyMessage(List<KeysDTO> keys) {
	    if (keys == null || keys.isEmpty()) {
	        return null;
	    }

	    return keys.stream()
	            .filter(k -> k != null && k.getKey() != null)
	            .sorted(Comparator.comparing(KeysDTO::getKey, String.CASE_INSENSITIVE_ORDER))
	            .map(k -> k.getKey() + "=" + (k.getValue() != null ? k.getValue() : ""))
	            .collect(Collectors.joining(", "));
	}
	
	private FlowOperationResult<RegionErrorModelDTO> buildRegionValidModel(String extractionId, String flowName) {
	    return buildRegionModel(extractionId, flowName, RegionModelTypeEnum.VALID, false, false);
	}

	private FlowOperationResult<RegionErrorModelDTO> buildRegionErrorModel(
	        String extractionId,
	        String flowName,
	        boolean processedScartoFile,
	        boolean processedWarningFile) {
	    return buildRegionModel(extractionId, flowName, RegionModelTypeEnum.ERROR, processedScartoFile, processedWarningFile);
	}
	
	private FlowOperationResult<RegionErrorModelDTO> buildRegionModel(
	        String extractionId,
	        String flowName,
	        RegionModelTypeEnum type,
	        boolean processedScartoFile,
	        boolean processedWarningFile) {

	    try {
	        if (RegionModelTypeEnum.ERROR.equals(type) && !processedScartoFile && !processedWarningFile) {
	            return FlowOperationResult.success(null);
	        }

	        String suffixRegError = configuration.findByKeyId("suffix_reg_error").getValue();

	        String tabgenRitorniId = (flowName + "_REG_" + suffixRegError).toUpperCase();
	        String tableReg0 = ("FM_FLOW_" + flowName + "_REG_0").toUpperCase();

	        FlowOperationResult<TabgenMap> ritorniMapResult = loadTabgenMap(tabgenRitorniId);
	        if (!ritorniMapResult.getSuccess()) {
	            return FlowOperationResult.failure(ritorniMapResult.getMessage());
	        }

	        TabgenMap ritorniMap = ritorniMapResult.getResult();
	        if (ritorniMap == null || ritorniMap.getTabgenValueList() == null || ritorniMap.getTabgenValueList().isEmpty()) {
	            return FlowOperationResult.success(null);
	        }

	        List<TabgenValueMap> pkFields = ritorniMap.getTabgenValueList().stream()
	                .filter(v -> Boolean.TRUE.equals(v.getIsPk()))
	                .sorted(Comparator.comparing(TabgenValueMap::getPosition, Comparator.nullsLast(Integer::compareTo)))
	                .collect(Collectors.toList());

	        if (pkFields.isEmpty()) {
	            return FlowOperationResult.failure(
	                    "Nessun campo chiave (CAMPO_CHIAVE=1) configurato nel tracciato regionale " + tabgenRitorniId
	            );
	        }

	        RegionErrorModelDTO model = new RegionErrorModelDTO();
	        model.setIdRequest(extractionId);
	        model.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
	        model.setErrors(new ArrayList<>());

	        if (RegionModelTypeEnum.VALID.equals(type)) {
	            List<Map<String, Object>> validRows = loadValidRowsFromRegionZero(
	                    tableReg0,
	                    tabgenRitorniId,
	                    extractionId,
	                    pkFields
	            );

	            if (validRows == null || validRows.isEmpty()) {
	                return FlowOperationResult.success(null);
	            }

	            String codiceAzienda = null;

	            for (Map<String, Object> row : validRows) {
	                ErrorsDTO errorsDTO = new ErrorsDTO();
	                errorsDTO.setKeys(new ArrayList<>());
	                errorsDTO.setFields(new ArrayList<>());

	                for (TabgenValueMap pk : pkFields) {
	                    String columnName = pk.getFieldName().toUpperCase();
	                    Object value = row.get(columnName);

	                    KeysDTO key = new KeysDTO();
	                    key.setKey(pk.getFieldName().toLowerCase());
	                    key.setValue(value != null ? String.valueOf(value) : null);

	                    errorsDTO.getKeys().add(key);
	                }

	                model.getErrors().add(errorsDTO);

	                if (codiceAzienda == null && row.get("CODICEAZIENDA") != null) {
	                    codiceAzienda = String.valueOf(row.get("CODICEAZIENDA"));
	                }
	            }

	            model.setCodiceAzienda(codiceAzienda);
	            model.setStatusRegion(RegionModelTypeEnum.VALID.toString());

	            return FlowOperationResult.success(model);
	        }

	        if (!Boolean.TRUE.equals(tabgenService.tableExists(tabgenRitorniId))) {
	            LOGGER.info("Tabella {} non presente per extractionId={}", tabgenRitorniId, extractionId);
	            return FlowOperationResult.success(null);
	        }

	        Map<String, ErrorsDTO> groupMap = new LinkedHashMap<>();
	        Map<String, ErrorFieldsDTO> fieldMap = new LinkedHashMap<>();
	        AtomicBoolean hasError = new AtomicBoolean(false);
	        AtomicReference<String> codiceAziendaRef = new AtomicReference<>(null);

	        appendRegionRows(
	                model,
	                groupMap,
	                fieldMap,
	                ritorniMap,
	                extractionId,
	                codiceAziendaRef,
	                hasError
	        );

	        if (model.getErrors() == null || model.getErrors().isEmpty()) {
	            return FlowOperationResult.success(null);
	        }

	        model.setCodiceAzienda(codiceAziendaRef.get());
	        model.setStatusRegion(hasError.get() ?  RegionModelTypeEnum.ERROR.toString() : RegionModelTypeEnum.VALID.toString());

	        return FlowOperationResult.success(model);

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER,
	                "Errore buildRegionModel extractionId=" + extractionId + " type=" + type, e);
	        return FlowOperationResult.failure(
	                "Errore durante la costruzione del json dei risultati regionali per extractionId=" + extractionId
	        );
	    }
	}
	
	private FlowOperationResult<TabgenMap> loadTabgenMap(String tabgenId) {
	    try {
	        FlowOperationResult<TabgenMap> result = checkAndLoadTabgenErrors(tabgenId, false);

	        if (result == null) {
	            return FlowOperationResult.failure(
	                    "Errore durante il caricamento del tracciato regionale " + tabgenId + "."
	            );
	        }

	        if (!result.getSuccess()) {
	            return FlowOperationResult.failure(result.getMessage());
	        }

	        if (result.getResult() == null) {
	            return FlowOperationResult.failure(
	                    "Errore durante il caricamento del tracciato regionale " + tabgenId + "."
	            );
	        }

	        return FlowOperationResult.success(result.getResult());

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER, "Errore durante il caricamento del TabgenMap " + tabgenId, e);
	        return FlowOperationResult.failure(
	                "Errore durante il caricamento del tracciato regionale " + tabgenId + "."
	        );
	    }
	}
	
	private List<Map<String, Object>> loadRowsFromPhysicalTable(String tableName, String extractionId) {
	    List<Map<String, Object>> rows = new ArrayList<>();

	    entityManager.unwrap(org.hibernate.Session.class).doWork(connection -> {
	        String sql = "SELECT * FROM " + tableName + " WHERE EXTRACTION_ID = ?";

	        try (PreparedStatement ps = connection.prepareStatement(
	                sql,
	                ResultSet.TYPE_FORWARD_ONLY,
	                ResultSet.CONCUR_READ_ONLY)) {

	            ps.setFetchSize(1000);
	            ps.setString(1, extractionId);

	            try (ResultSet rs = ps.executeQuery()) {
	                ResultSetMetaData meta = rs.getMetaData();
	                int columnCount = meta.getColumnCount();

	                List<String> columnNames = new ArrayList<>(columnCount);
	                for (int i = 1; i <= columnCount; i++) {
	                    String columnName = meta.getColumnLabel(i);
	                    if (columnName == null || columnName.isBlank()) {
	                        columnName = meta.getColumnName(i);
	                    }
	                    columnNames.add(columnName != null ? columnName.toUpperCase() : "COL_" + i);
	                }

	                while (rs.next()) {
	                    Map<String, Object> row = new LinkedHashMap<>();
	                    for (int i = 1; i <= columnCount; i++) {
	                        row.put(columnNames.get(i - 1), rs.getObject(i));
	                    }
	                    rows.add(row);
	                }
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Errore lettura tabella fisica " + tableName + " per extractionId=" + extractionId, e);
	        }
	    });

	    return rows;
	}
	
	private void appendRegionRows(RegionErrorModelDTO model,
            Map<String, ErrorsDTO> groupMap,
            Map<String, ErrorFieldsDTO> fieldMap,
            TabgenMap tabgenMap,
            String extractionId,
            AtomicReference<String> codiceAziendaRef,
            AtomicBoolean hasError) throws Exception {

		List<Map<String, Object>> rows = loadRowsFromPhysicalTable(tabgenMap.getId(), extractionId);
		if (rows == null || rows.isEmpty()) {
			return;
		}
		
		List<TabgenValueMap> pkFields = tabgenMap.getTabgenValueList().stream()
		.filter(v -> Boolean.TRUE.equals(v.getIsPk()))
		.sorted(Comparator.comparing(TabgenValueMap::getPosition, Comparator.nullsLast(Integer::compareTo)))
		.collect(Collectors.toList());
		
		for (Map<String, Object> row : rows) {
			
			if (codiceAziendaRef.get() == null && row.get("CODICEAZIENDA") != null) {
				codiceAziendaRef.set(String.valueOf(row.get("CODICEAZIENDA")));
			}
			
			List<KeysDTO> keys = new ArrayList<>();
			String keyToString = "";
			
			for (TabgenValueMap pk : pkFields) {
				String columnName = pk.getFieldName().toUpperCase();
				Object value = row.get(columnName);
				
				KeysDTO key = new KeysDTO();
				key.setKey(pk.getFieldName().toLowerCase());
				key.setValue(value != null ? String.valueOf(value) : null);
				keys.add(key);
				
				keyToString += pk.getFieldName().toLowerCase() + "_" + (value != null ? value : "") + ";";
			}
			
			ErrorsDTO errorsDTO = groupMap.get(keyToString);
			if (errorsDTO == null) {
				errorsDTO = new ErrorsDTO();
				errorsDTO.setKeys(keys);
				errorsDTO.setFields(new ArrayList<>());
				model.getErrors().add(errorsDTO);
				groupMap.put(keyToString, errorsDTO);
			}
			
			String fieldName = getString(row.get("DESCRIZIONECAMPO"));
			if (fieldName == null || fieldName.isBlank()) {
				fieldName = "UNKNOWN_FIELD";
			}
			
			String fieldMapKey = keyToString + "_" + fieldName;
			ErrorFieldsDTO errorField = fieldMap.get(fieldMapKey);
			if (errorField == null) {
				errorField = new ErrorFieldsDTO();
				errorField.setField(fieldName);
				errorField.setFieldErrors(new ArrayList<>());
				errorsDTO.getFields().add(errorField);
				fieldMap.put(fieldMapKey, errorField);
			}
			
			String severityDb = getString(row.get("SEVERITY"));
			String severityJson = mapSeverityToJson(severityDb);
			
			if (RegionModelTypeEnum.ERROR.toString().equals(severityJson) || RegionModelTypeEnum.WARNING.toString().equals(severityJson)) {
				hasError.set(true);
			}
			
			FieldErrorsDTO fieldError = new FieldErrorsDTO();
			fieldError.setErrorKey(getString(row.get("CODICEERRORE")));
			fieldError.setErrorValue(getString(row.get("DESCRIZIONEERRORE")));
			fieldError.setSeverity(severityJson);
			
			errorField.getFieldErrors().add(fieldError);
		}
	}
	
	private List<Map<String, Object>> loadValidRowsFromRegionZero(
	        String tableReg0,
	        String tableRitorni,
	        String extractionId,
	        List<TabgenValueMap> pkFields) {

	    List<Map<String, Object>> rows = new ArrayList<>();

	    entityManager.unwrap(org.hibernate.Session.class).doWork(connection -> {

	        String selectPk = pkFields.stream()
	                .map(pk -> "r0." + pk.getFieldName().toUpperCase())
	                .collect(Collectors.joining(", "));

	        StringBuilder notExists = new StringBuilder();
	        notExists.append(" NOT EXISTS (");
	        notExists.append(" SELECT 1 FROM ").append(tableRitorni).append(" rr");
	        notExists.append(" WHERE rr.EXTRACTION_ID = r0.EXTRACTION_ID");

	        for (TabgenValueMap pk : pkFields) {
	            String col = pk.getFieldName().toUpperCase();
	            notExists.append(" AND NVL(rr.").append(col).append(", '#') = NVL(r0.").append(col).append(", '#')");
	        }

	        notExists.append(" )");

	        String orderBy = pkFields.stream()
	                .map(pk -> "r0." + pk.getFieldName().toUpperCase())
	                .collect(Collectors.joining(", "));

	        String sql = "SELECT " + selectPk +
	                " FROM " + tableReg0 + " r0" +
	                " WHERE r0.EXTRACTION_ID = ?" +
	                " AND " + notExists +
	                " ORDER BY " + orderBy;

	        try (PreparedStatement ps = connection.prepareStatement(
	                sql,
	                ResultSet.TYPE_FORWARD_ONLY,
	                ResultSet.CONCUR_READ_ONLY)) {

	            ps.setFetchSize(1000);
	            ps.setString(1, extractionId);

	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Map<String, Object> row = new LinkedHashMap<>();

	                    for (TabgenValueMap pk : pkFields) {
	                        String col = pk.getFieldName().toUpperCase();
	                        row.put(col, rs.getObject(col));
	                    }

	                    rows.add(row);
	                }
	            }

	        } catch (Exception e) {
	            throw new RuntimeException(
	                    "Errore lettura pratiche VALIDE dalla tabella " + tableReg0 + " per extractionId=" + extractionId, e);
	        }
	    });

	    return rows;
	}
	
	private String mapSeverityToJson(String dbSeverity) {
	    if (dbSeverity == null) {
	        return RegionModelTypeEnum.VALID.toString();
	    }

	    String value = dbSeverity.trim().toUpperCase();

	    if ("SCARTO".equals(value)) {
	        return RegionModelTypeEnum.ERROR.toString();
	    }
	    if ("SEGNALAZIONE".equals(value)) {
	        return RegionModelTypeEnum.WARNING.toString();
	    }
	    if ("VALIDO".equals(value) || "VALID".equals(value)) {
	        return RegionModelTypeEnum.VALID.toString();
	    }

	    return RegionModelTypeEnum.VALID.toString();
	}
	
	private String getString(Object value) {
	    return value != null ? String.valueOf(value) : null;
	}
	
	@SuppressWarnings("unchecked")
	private FlowOperationResult<Boolean> reprocessPendingLockedMessages(String extractionId) {
	    try {
	        FlowExportRequestDO flowExportRequestDO = flowExportService.retriveOne(extractionId);
	        if (flowExportRequestDO == null || flowExportRequestDO.getFlow() == null) {
	            return FlowOperationResult.failure(
	                    "Nessun flusso associato alla extraction " + extractionId
	            );
	        }

	        if (!Boolean.TRUE.equals(flowExportRequestDO.getConsolidata())) {
	            LOGGER.info("Riprocessamento pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE non eseguito per extractionId={} perché NON CONSOLIDATA", extractionId);
	            return FlowOperationResult.success(true);
	        }

	        String flowId = flowExportRequestDO.getFlow().getId();
	        String versionName = null;

	        if (flowExportRequestDO.getVersion() != null) {
	            versionName = flowExportRequestDO.getVersion().getVersion();
	        }

	        List<LockedMessageDTO> lockedMessages = jsonFlowService.findPendingLockedMessages(flowId, extractionId);

	        if (lockedMessages == null || lockedMessages.isEmpty()) {
	            LOGGER.info("Nessuna pratica parcheggiata da riprocessare per extractionId={}", extractionId);
	            return FlowOperationResult.success(true);
	        }

	        for (LockedMessageDTO lockedMessage : lockedMessages) {
	            try {
	            	String replayExtractionId = lockedMessage.getExtractionId();

	            	ProcessSingleFlowResult replayResult = jsonFlowService.processSingleFlow(
	            	        lockedMessage.getMessage(),
	            	        lockedMessage.getFlowName(),
	            	        versionName,
	            	        replayExtractionId
	            	);

	                if (replayResult != null && replayResult.isSuccess() && replayResult.getExtractionId() != null) {
	                    jsonFlowService.markLockedMessageProcessed(
	                            lockedMessage.getId(),
	                            replayResult.getExtractionId()
	                    );
	                } else {
	                    LOGGER.warn("Riprocessamento pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE non riuscito per locked message id={} keyMessage={}",
	                            lockedMessage.getId(), lockedMessage.getKeyMessage());

	                    return FlowOperationResult.failure(
	                            "Riprocessamento pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE non riuscito per il messaggio parcheggiato con id=" + lockedMessage.getId()
	                    );
	                }

	            } catch (Exception e) {
	                LogUtil.logException(LOGGER,
	                        "Errore nel riprocessamento pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE del messaggio bloccato key=" + lockedMessage.getKeyMessage(), e);

	                return FlowOperationResult.failure(
	                        "Errore nel riprocessamento pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE del messaggio parcheggiato con id=" + lockedMessage.getId()
	                );
	            }
	        }

	        return FlowOperationResult.success(true);

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER,
	                "Errore nel riprocessamento delle pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE per extractionId=" + extractionId, e);
	        return FlowOperationResult.failure(
	                "Errore nel riprocessamento delle pratiche parcheggiate nella tabella FM_LOCKED_MESSAGE per extractionId=" + extractionId
	        );
	    }
	}

	@Override
	public void updateExtractionsStatus(Collection<FlowExportRequestDO> collection) throws Exception {
		for(FlowExportRequestDO extraction : collection) {
			
			String flowId = extraction.getFlow().getId();
			String versionId = extraction.getVersion().getId();
			
			BaseSearchInput searchInput = new BaseSearchInput();
			searchInput.setValue("flow", flowId);
		
			
			Pair<List<FlowRegionUnionDO>, SearchInfo> unionList = flowRegionUnionService.retrieveAllFiltered(searchInput);
			if(unionList != null && unionList.getFirst() != null && !unionList.getFirst().isEmpty()) {
				FlowRegionUnionDO union = unionList.getFirst().get(0);
				FlowDO flowRegion = union.getFlowRegion();
				String flowRegionId = flowRegion.getId();
				
				searchInput = new BaseSearchInput();
				searchInput.setValue("flowId", flowRegionId);
				searchInput.setValue("versionId", versionId);
				Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFiltered(searchInput);
				FormFlowDTO configurationRegion = searchResults.getFirst().get(0);
				
				String cfgSuffixRegError = configuration.findByKeyId("suffix_reg_error").getValue();
				String tableNameRegErrors = configurationRegion.getName() + "_" + cfgSuffixRegError;
				
				TabgenMap checkTabgenErrors = checkAndLoadTabgenErrors(tableNameRegErrors, false).getResult();
				
				transactionalTwoLevelResultsService.updateSendRegionStatus(extraction.getId(), configurationRegion, true, checkTabgenErrors, true);
			}
		}
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.CREATE, category = AuditEventCategoryEnum.ACCESS_LOG, description="Upload Ritorno", converter= UploadRitornoConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.CARTELLA_CLINICA)
    public byte[] sendAuditUploadRitornoToPM(BaseSearchInput searchInput, byte[] bytes) {
    	return bytes;
    }
	
}

class MyC implements Callable<FlowOperationResult<Boolean>>  {

	boolean produzioneTot; boolean checkErrorsAndWarnings; List<File> files; String tableNameRegUpdate; String tableNameRegUpdateTot; String tableNameRegErrors; 
	String tableNameRegWarnings; FormFlowDTO configurationRegion; String extractionId; String flowRegionId; String flowLocalId; String versionId; FlowOperationResult<TabgenMap> checkTabgenErrors;
	FlowOperationResult<TabgenMap> checkTabgenUpdate; FlowOperationResult<TabgenMap> checkTabgenUpdateTot; File file; TwoLevelResultsServiceImpl service; TabgenValue tabGenValueFlowUploadConf; List<TabgenValue> tabGenValueFlowTipologiaRitorni; boolean consolidata;
	
	public MyC(boolean produzioneTot, boolean checkErrorsAndWarnings, List<File> files, String tableNameRegUpdate,
		String tableNameRegUpdateTot, String tableNameRegErrors, String tableNameRegWarnings,
		FormFlowDTO configurationRegion, String extractionId, String flowRegionId, String flowLocalId, String versionId,
		FlowOperationResult<TabgenMap> checkTabgenErrors, FlowOperationResult<TabgenMap> checkTabgenUpdate,
		FlowOperationResult<TabgenMap> checkTabgenUpdateTot, File file, TwoLevelResultsServiceImpl service, TabgenValue tabGenValueFlowUploadConf, List<TabgenValue> tabGenValueFlowTipologiaRitorni, boolean consolidata) {
	super();
	this.produzioneTot = produzioneTot;
	this.checkErrorsAndWarnings = checkErrorsAndWarnings;
	this.files = files;
	this.tableNameRegUpdate = tableNameRegUpdate;
	this.tableNameRegUpdateTot = tableNameRegUpdateTot;
	this.tableNameRegErrors = tableNameRegErrors;
	this.tableNameRegWarnings = tableNameRegWarnings;
	this.configurationRegion = configurationRegion;
	this.extractionId = extractionId;
	this.flowRegionId = flowRegionId;
	this.flowLocalId = flowLocalId;
	this.versionId = versionId;
	this.checkTabgenErrors = checkTabgenErrors;
	this.checkTabgenUpdate = checkTabgenUpdate;
	this.checkTabgenUpdateTot = checkTabgenUpdateTot;
	this.file = file;
	this.service = service;
	this.tabGenValueFlowUploadConf = tabGenValueFlowUploadConf;
	this.tabGenValueFlowTipologiaRitorni = tabGenValueFlowTipologiaRitorni;
	this.consolidata = consolidata;
}


	@Override
	public FlowOperationResult<Boolean> call() throws Exception {
		return service.uploadResultsThread(produzioneTot, checkErrorsAndWarnings, files, tableNameRegUpdate, tableNameRegUpdateTot, tableNameRegErrors, 
				tableNameRegWarnings, configurationRegion, extractionId, flowRegionId, flowLocalId, versionId, checkTabgenErrors, checkTabgenUpdate, checkTabgenUpdateTot, file, tabGenValueFlowUploadConf, tabGenValueFlowTipologiaRitorni, consolidata);
	}
	
}
