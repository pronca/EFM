package it.eng.care.domain.flow.tabgen.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.utility.CsvFile;
import it.eng.care.domain.flow.core.utility.ExcelFile;
import it.eng.care.domain.flow.core.utility.FileType;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.OperationTypeEnum;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.domain.flow.tabgen.exception.InvalidTabgenOperationException;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.platform.common.lang.StringUtils;

public class ImportTabgenService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportTabgenService.class);
	
	//@Value("${tabgen.temp.dir}")
	//private String tempDir;

	private static final long serialVersionUID = -8249346112122502257L;
	//private Logger logger = Logger.getLogger(ImportTabgenService.class);

	@Autowired
	private TabgenService tabgenService;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	public String handleFileUpload(File file, String fileName, String anagraficaTableId) {
		//String filename = tempDir + "/" + file.getName();
		
		try {
			String enableDate = null;
			int index = fileName.lastIndexOf('.');
		    if(index > 0) {
		    	String extension = fileName.substring(index + 1);
		    	if (extension.equalsIgnoreCase(FileUtility.XLS) || extension.equalsIgnoreCase(FileUtility.XLSX) || extension.equalsIgnoreCase(FileUtility.CSV) || extension.equalsIgnoreCase(FileUtility.ZIP) || extension.equalsIgnoreCase(FileUtility.GZIP)) {
		    		List<TabgenDO> tabgen = loadInTabgen(file, enableDate, extension, fileName, anagraficaTableId);
		    		return tabgen.get(0).getId() + " - Importazione effettuata";
		    	}
		    	return "Tipo file non consentito";
		    } else {
		    	return "Tipo file non consentito";
		    }
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
			return manageError(e);
		}
	}
	
	private String manageError(Exception ex) {
		if (ex instanceof InvalidTabgenOperationException) {
			InvalidTabgenOperationException error = (InvalidTabgenOperationException) ex;
			String errorRow = error.getErrorExcelRowNumber() != null ? " Riga errata : " + error.getErrorExcelRowNumber() : "";
			String field = "";
			if (error.getFields() != null && error.getFields().size() > 0) {
				for (String f : error.getFields()) {
					field += f + " ";
				}
			}
			if (error.getOperation().equals(OperationTypeEnum.LOADING_DATA_TO_SHEET_EXCEL)) {
				if (error.isAbsentTable()) {
					return "Dati non validi : La tabella" + error.getSheetName() + " non esiste." + errorRow;
				} else if (error.isAbsentFields()) {
					return "Dati non validi : I campi non esistono." + errorRow;
				} else if (!StringUtils.isEmpty(error.getAbsentSheetColumn())) {
					return "Schemi differenti : la colonna " + error.getAbsentSheetColumn() + " non esiste." + errorRow;
				} else if (error.getSheetColumnNum() != null) {
					return "Schemi differenti : numero di colonne differente. Colonne esistenti=" + error.getTableColumnNum() + ""//
							+ " Colonne in caricamento=" + error.getSheetColumnNum() + errorRow;
				}
				if (!StringUtils.isEmpty(error.getSheetName())) {
					return "Dati non validi." + errorRow;
				} else {

				}
			} else if (error.getOperation().equals(OperationTypeEnum.SAVING_UPDATING_TABLE)) {
				if (error.isTableAssociatedValues()) {
					return "Impossibile salvare la tabella. Ci sono valori ad essa associati." + errorRow;
				} else if (error.isViolatedPk()) {
					return "L'identificativo scelto è già stato assegnato." + errorRow;
				}
			} else if (error.getOperation().equals(OperationTypeEnum.SAVING_UPDATING_FIELD)) {
				if (error.isDuplicatedProgressive()) {
					return "Impossibile salvare i campi. Progressivo campo duplicato per " + field + errorRow;
				} else if (error.isInvalidValueColumnName()) {
					return "Impossibile salvare i campi. Nome campo non valido : " + field + errorRow;
				} else if (error.isDuplicatedValueColumnName()) {
					return "Impossibile salvare i campi. Nome campo duplicato : " + error.getFieldTable() + errorRow;
				} else if (error.isFieldReferencingFields()) {
					return "Impossibile salvare i campi. Esistono riferimenti al campo " + field + errorRow;
				} else if (error.isFieldAssociatedValues()) {
					return "Impossibile salvare. Campo " + field + " valorizzato." + errorRow;
				} else if (error.isNotEnablingNotNullable()) {
					return "Impossibile rendere il campo " + field + " obbligatorio." + errorRow;
				} else if (error.isNotEnablingFk()) {
					return "Impossibile abilitare il riferimento ad altro campo per " + field + errorRow;
				} else if (error.isNotEnablingPk()) {
					return "Impossibile abilitare il vincolo su tabella per il campo " + field + errorRow;
				} else if (error.isNotDisablingPk()) {
					return "Impossibile disabilitare il vincolo su tabella per il campo " + field + errorRow;
				}
			} else if (error.getOperation().equals(OperationTypeEnum.SAVING_UPDATING_VALUE)) {
				if (error.getAbsentValueField() != null) {
					return "Impossibile salvare. Campo " + error.getAbsentValueField() + " inesistente." + errorRow;
				} else if (error.isViolatedFk() && error.getViolatedFkRefField() != null) {
					return "Impossibile salvare il campo " + error.getViolatedFkField() + ". Chiave esterna " + //
							error.getValue() + " non trovata in " + error.getViolatedFkRefFieldTable() + " - " + error.getViolatedFkRefField() + errorRow;
				} else if (error.isViolatedFk()) {
					return "Impossibile salvare. Campo " + error.getViolatedFkField() + " referenziato da altri campi." + errorRow;
				} else if (error.isNotNullable()) {
					return "Impossibile salvare. Campo " + error.getNotNullableField() + " obbligatorio." + errorRow;
				} else if (error.isTableAssociatedValues()) {
					return "Esistono dei campi e dei valori associati alla tabella." + //
							"Solo la descrizione può essere modificata." + errorRow;
				} else if (error.isDuplicatedProgressive()) {
					return "Progressivo duplicato per il campo " + field + ". Modificare i campi della tabella." + errorRow;
				} else if (error.isDuplicatedValueColumnName()) {
					return "Campo duplicato. Modificare i nomi dei campi:" + field + errorRow;
				} else if (error.getAbsentValueField() != null) {
					return "Il campo " + error.getAbsentValueField() + " non esiste per la tabella selezionata." + errorRow;
				} else if (error.isViolatedFk()) {
					return "Chiave esterna " + error.getViolatedFkRefField() + "non valida sul campo " + error.getViolatedFkField() + errorRow;
				} else if (error.isNotNullable()) {
					return "Il campo " + error.getNotNullableField() + " deve essere valorizzato." + errorRow;
				} else if (error.isViolatedPk()) {
					return "Vincolo pk violato su " + error.getValueTable() + errorRow;
				}
			}  else if (error.getOperation().equals(OperationTypeEnum.LOADING_DATA_CODING_ERROR)) {
				String msg = "Il contenuto della cella non e' in formato UTF-8<br><br>"+errorRow+"<br>";
				if (error.getFieldTable() != null) {
					msg += "<br>Campo errato: " + error.getFieldTable()+"<br>";
				}
				if (error.getValue() != null) {
					msg += "<br>Valore errato: " + error.getValue();
				}  
				return msg;
			}
		}
		return "Errore in fase di caricamento";

	}

	private List<TabgenDO> loadInTabgen(File file, String enabledDate, String extension, String fileName, String anagraficaTableId) throws InvalidTabgenOperationException, Exception {
		List<TabgenDO> tabgens = new ArrayList<>();
		List<FileType> fileTypes = new ArrayList<>();
		List<File> listaZipFiles = new ArrayList<>();
		//default skip import header file xls xlsx csv
		boolean skipColumnName=false;
		
		/***RECUPERO CONFIGUARAZIONE TIPOLOGIA IMPORT DATI (MODE_IMPORT_FILE_CONF)***/
		TabgenValue tabGenValueAnaImportConf = new TabgenValue();
		//FLOW_UPLOAD_CONF
		TabgenValueFilter filterAnaImportConf = new TabgenValueFilter();
		filterAnaImportConf.setTabgenId("MODE_IMPORT_FILE_CONF");
		BasePagingLoadResult<Tabgen> list = new BasePagingLoadResult<Tabgen>(null);
		list = tabgenDelegate.searchValue(filterAnaImportConf);
		if (list != null && list.getTotalLength() > 0 && list.getList() != null
				&& !list.getList().isEmpty()) {
			Tabgen flowAnaImportConf = list.getList().get(0);
			if (flowAnaImportConf != null && "MODE_IMPORT_FILE_CONF".equals(flowAnaImportConf.getId())) {
				List<TabgenValue> tabgenValues = flowAnaImportConf.getTabgenValues();
				if (tabgenValues != null && !tabgenValues.isEmpty()) {
					for (TabgenValue tabgenValue : tabgenValues) {
						tabGenValueAnaImportConf=tabgenValue;
					}
				}
			}
		}
		if (null != tabGenValueAnaImportConf && null != tabGenValueAnaImportConf.getField1()) {
			if (tabGenValueAnaImportConf.getField1().equalsIgnoreCase("S") || tabGenValueAnaImportConf.getField1().equalsIgnoreCase("SI") || tabGenValueAnaImportConf.getField1().equalsIgnoreCase("1")) {
				skipColumnName = true;
			}
		}
		
		if (extension.equalsIgnoreCase(FileUtility.XLS) || extension.equalsIgnoreCase(FileUtility.XLSX)) {
			ExcelFile excelFile = new ExcelFile(file, extension, file.getName());
			fileTypes.add(excelFile);
		} else if (extension.equalsIgnoreCase(FileUtility.CSV)) {
			CsvFile cvsFile = new CsvFile(file, extension, file.getName());
			fileTypes.add(cvsFile);
		} else {
			String fileNameZip = file.getName();
			if (extension.equalsIgnoreCase(FileUtility.ZIP)) {
	    		listaZipFiles = FileUtility.listZipFile(file);
	    	} else {
	    		listaZipFiles = FileUtility.listGzipFile(file, fileName);
	    	}
			for (File singleFileUnz : listaZipFiles) {
				String extSingleFileUnz="";
				int index = singleFileUnz.getName().lastIndexOf('.');
				if(index > 0) {
			    	extSingleFileUnz = singleFileUnz.getName().substring(index + 1);
				}
				if (extSingleFileUnz.equalsIgnoreCase(FileUtility.XLS) || extSingleFileUnz.equalsIgnoreCase(FileUtility.XLSX)) {
					ExcelFile excelFile = new ExcelFile(singleFileUnz, extSingleFileUnz, fileNameZip);
					fileTypes.add(excelFile);
				} else if (extSingleFileUnz.equalsIgnoreCase(FileUtility.CSV)) {
					CsvFile cvsFile = new CsvFile(singleFileUnz, extSingleFileUnz, fileNameZip);
					fileTypes.add(cvsFile);
				}
			}
		}
		
		for (FileType fileType : fileTypes) {
			TabgenDO tabgen = null;
			file = fileType.getFile();
			extension = fileType.getExtension();
			String fileTypeName = fileType.getName();
			
			try {
				int offset = 0;
				int max = 2000;
				if (extension.equalsIgnoreCase(FileUtility.XLS)) {
					max = 10000;
				} else if (extension.equalsIgnoreCase(FileUtility.XLSX)) {
					max = 50000;
				} else if (extension.equalsIgnoreCase(FileUtility.CSV)) {
					max = 100000;
				}
				
				TabgenDO tabgenTemp = tabgenTemp = tabgenService.loadExcelSheetInTabgen(file, enabledDate, offset, max, extension, fileTypeName, skipColumnName, anagraficaTableId);
				tabgens.add(tabgenTemp);
				while (tabgenTemp != null) {
					offset += max;
					tabgen = tabgenTemp;
					tabgenTemp = tabgenService.loadExcelSheetInTabgen(file, enabledDate, offset, max, extension, fileTypeName, skipColumnName, anagraficaTableId);
					tabgens.add(tabgenTemp);
				}
				try {
					if(tabgen != null) {
						tabgenService.dropTabgenView("drop view " + tabgen.getId());
					}
				} catch (Exception ex) {
					LogUtil.logException(LOGGER, "", ex);
//					ex.printStackTrace();
				}
				if(tabgen != null) {
					tabgenService.createReplaceTabgenView(tabgen.getId(), "create_ana_view");
				}
				
				//logger.info("createReplaceTabgenView result= " + result);
			} catch (Exception ex) {
				LogUtil.logException(LOGGER, "", ex);
				// roolback
				if (tabgen != null) {
					tabgenService.deleteTabgen(tabgen.getId(), false, false, true);
				}
				throw ex;
			}
		}
		
		return tabgens;
	}
	

}
