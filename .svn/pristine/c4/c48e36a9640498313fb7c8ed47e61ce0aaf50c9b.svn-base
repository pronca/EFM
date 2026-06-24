package it.eng.care.domain.flow.core.controller.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.annotation.PostConstruct;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.anagraficaassistito.utility.AnagraficaAssistitoUtility;
import it.eng.care.domain.flow.b2b.utils.JsonSchemaUtils;
import it.eng.care.domain.flow.core.controller.FormFlowController;
import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFlowDTO;
import it.eng.care.domain.flow.core.converter.Flow.FlowDTOtoFlowDO;
import it.eng.care.domain.flow.core.converter.FlowTable.FlowTableDOtoFlowTableDTO;
import it.eng.care.domain.flow.core.converter.FlowTable.FlowTableDTOtoFlowTableDO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDOtoFlowTableFieldDTO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDTOtoFlowTableFieldDO;
import it.eng.care.domain.flow.core.converter.Version.VersionDOtoVersionDTO;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.FlowVersionDAO;
import it.eng.care.domain.flow.core.dto.ExportDataDTO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FlowTableDTO;
import it.eng.care.domain.flow.core.dto.FlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.service.FlowForeignKeyService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.FlowTableFieldService;
import it.eng.care.domain.flow.core.service.FlowTableService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.VersionService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.platform.authentication.api.model.bean.LoggedUser;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/FormFlowDTO")
public class FormFlowControllerImpl implements FormFlowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormFlowControllerImpl.class);
    
    @Autowired
    JsonSchemaUtils utils;
    @Autowired
    FlowVersionDAO flowVersionDAO;
    @Autowired
    private FlowService flowService;
    @Autowired
    private FormFlowService formFlowService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private FlowDOtoFlowDTO flowDOtoFlowDTO;
    @Autowired
    private FlowTableDOtoFlowTableDTO flowTableDOtoFlowTableDTO;
    @Autowired
    private FlowTableFieldDOtoFlowTableFieldDTO flowTableFieldDOtoFlowTableFieldDTO;
    @Autowired
    private FlowDTOtoFlowDO flowDTOtoFlowDO;
    @Autowired
    private FlowTableDTOtoFlowTableDO flowTableDTOtoFlowTableDO;
    @Autowired
    private FlowTableFieldDTOtoFlowTableFieldDO flowTableFieldDTOtoFlowTableFieldDO;
    @Autowired
    private FlowRegionUnionService flowRegionUnionService;
    @Autowired
    private FlowTableFieldService flowTableFieldService;
    @Autowired
    private FlowTableService flowTableService;
    @Autowired
    private FlowManagerProfileService profileService;   
    @Autowired
    private VersionDOtoVersionDTO versionDOtoVersionDTOConvert;
    @Autowired
    private VersionService versionService;

    @Autowired
    private FlowForeignKeyService flowForeignKeyService;
    
    @Autowired
	private TabgenDelegate tabgenDelegate;
    
    @Autowired
    private LoggedUserService loggUserService;
    
    @Autowired
    private FlowDAO flowDAO;
    
    public FormFlowControllerImpl() {

    }

    @PostConstruct
    public void post() {
        conversionService.registerConverter(FlowDO.class, FlowDTO.class, flowDOtoFlowDTO);
        conversionService.registerConverter(FlowDTO.class, FlowDO.class, flowDTOtoFlowDO);
        conversionService.registerConverter(FlowTableDO.class, FlowTableDTO.class, flowTableDOtoFlowTableDTO);
        conversionService.registerConverter(FlowTableDTO.class, FlowTableDO.class, flowTableDTOtoFlowTableDO);
        conversionService.registerConverter(FlowTableFieldDO.class, FlowTableFieldDTO.class,
                flowTableFieldDOtoFlowTableFieldDTO);
        conversionService.registerConverter(FlowTableFieldDTO.class, FlowTableFieldDO.class,
                flowTableFieldDTOtoFlowTableFieldDO);
        conversionService.registerConverter(VersionDO.class, VersionDTO.class, versionDOtoVersionDTOConvert);
    }

    @Override
    @PostMapping("/save")
    public SaveOperationResult<FormFlowDTO> save(@RequestBody FormFlowDTO inputDTO) {
    	Pair<List<FormFlowDTO>, SearchInfo> formFlowPair = null;
    	
    	try {
	        String flowLocal = "";
	        String flowRegion = "";
	        if (StringUtils.isEmpty(inputDTO.getId())) {
	        	 BaseSearchInput bsi = new BaseSearchInput();
	        	 bsi.setValue("name",inputDTO.getName());
	             Pair<List<FlowDO>, SearchInfo> flowDO = flowService.retrieveAllFiltered(bsi, true);
	    		if(flowDO != null && !flowDO.getFirst().isEmpty()) {
	    			return SaveOperationResult.failure("Nome flusso già esistente");
	    		}
	            flowLocal = formFlowService.saveEntity(inputDTO,true);
	            
	            // abilita il flusso per l'utente che lo ha creato aggiungendo la configurazione in PROFILO_FLUSSI
	            Tabgen tabgen = new Tabgen();
	            tabgen.setId("PROFILO_FLUSSI");
	            
	            LoggedUser user = loggUserService.getCurrentUser();
	            
	            TabgenValue config = new TabgenValue();
	            config.setTabgen(tabgen);
				config.setField1(user.getCurrentOrganization().getCode());
				config.setField2(user.getCurrentSite().getCode());
				config.setField3(user.getCurrentRole().getCode());
				config.setField4(inputDTO.getName());
				tabgenDelegate.saveValue(config);
	            
	            
	            String name = inputDTO.getName() + "_REG";
	            String code = null;
	            if (inputDTO.getCode()!=null) {
	            	code = inputDTO.getCode() + "_REG";
	            }
	            inputDTO.setName(name);
	            inputDTO.setCode(code);
	            inputDTO.setFlowType("REGIONE");
	            flowRegion = formFlowService.saveEntity(inputDTO,true);
	            FlowRegionUnionDO flowRegionUnionDO = new FlowRegionUnionDO();
	            BaseSearchInput baseSearchInput = new BaseSearchInput();
	            baseSearchInput.setValue("id", flowLocal);
	            flowRegionUnionDO.setFlowLocal(flowService.retrieveAllFiltered(baseSearchInput, false).getFirst().get((0)));
	            BaseSearchInput baseSearchInput2 = new BaseSearchInput();
	            baseSearchInput2.setValue("id", flowRegion);
	            flowRegionUnionDO.setFlowRegion(flowService.retrieveAllFiltered(baseSearchInput2, false).getFirst().get((0)));
	            flowRegionUnionService.createEntity(flowRegionUnionDO);
	            
	            // crea json schema del flusso regionale
	            updateJsonSchema(flowRegion);
	            
	        } else {
	            flowLocal = formFlowService.updateEntity(inputDTO);
	        }
	        LOGGER.info("FlowLocalId:: "+flowLocal);
	        
	        formFlowPair = updateJsonSchema(flowLocal);
	        
    	} catch (Exception e) {
    		LogUtil.logException(LOGGER, "ERRORE SALVATAGGIO FLUSSO", e);
    		return SaveOperationResult.failure(e.getMessage());
    	}
        
        return SaveOperationResult.success(formFlowPair.getFirst().get(0));
    }

    @Override
    public OperationResult<Object> getSectionBy(@RequestBody String flowId, @RequestBody String versionId) {
        return null;
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<FormFlowDTO> search(@RequestBody BaseSearchInput searchInput) {
        searchInput.setValue("type", "AZIENDA");

        Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFilteredInFlowConfiguration(searchInput, true);
        
        // filtra in base alle abilitazioni impostate in PROFILO_UTENTE(organization, role, site)
        List<FormFlowDTO> list = searchResults.getFirst();
        if(list != null && list.size() > 0) {
        	List<FormFlowDTO> newList = new ArrayList<FormFlowDTO>();
        	for (FormFlowDTO flow : list) {
				Boolean check = profileService.checkFlowById(flow.getId());
				if(check) {
					newList.add(flow);
				}
			}
        	list.clear();
        	list.addAll(newList);
        }
        
        return SearchOperationResult.success(searchResults.getFirst(), searchResults.getSecond());

    }

    @Override
    @DeleteMapping("/{entityKeyName}/{entityKeyValue}")
    public void deleteEntityBy(@PathVariable("entityKeyName") String entityKeyName,
                               @PathVariable("entityKeyValue") String entityKeyValue) {

        LOGGER.info("{} , {}", entityKeyName, entityKeyValue);
        formFlowService.deleteEntity(entityKeyValue);

    }

    /**
     * Delete Section
     *
     * @param id
     * @return
     */
    @Override
    @PostMapping("/deleteSection")
    @ResponseBody
    public void deleteSection(@RequestBody BaseSearchInput baseSearchInput) {
    	formFlowService.deleteFlowTable(baseSearchInput);
    }

    /**
     * Delete Field
     *
     * @param id
     * @return
     */
    @Override
    @PostMapping("/deleteField")
    @ResponseBody
    public void deleteField(@RequestBody String id) {
        flowTableFieldService.deleteEntity(id);
    }
    
    @Override
    @PostMapping("/deleteFkLink")
    @ResponseBody
    public void deleteFkLink(@RequestBody  BaseSearchInput searchInput)  {
    	    	
    	String id = searchInput.getValue("id");
    	String idTable = searchInput.getValue("idTable");
    	String idTableRef = searchInput.getValue("idTableReferenced");
    	String idField = searchInput.getValue("idFieldTable");
    	String idFieldRef = searchInput.getValue("idFieldTableReferenced");
    			
    	FlowTableDO flowTableDo =  flowTableService.retrieveOne(idTable);
    	Set<FlowForeignKeyDO> flowFkDO = flowTableDo.getTable();
    	Set<FlowForeignKeyDO> flowFkDORef = flowTableDo.getTableReferenced();
    	flowFkDO.removeIf(obj -> obj.getIdTable().getId().equals(idTable));
    	flowFkDORef.removeIf(obj -> obj.getIdTableReferenced().getId().equals(idTableRef));
    	
    	Set<FlowTableFieldDO> fields =  flowTableDo.getFields();
    	for(FlowTableFieldDO field : fields) {
    		Set<FlowForeignKeyDO> fieldTable = field.getFieldTable();
    		Set<FlowForeignKeyDO> fieldTableRef = field.getFieldableReferenced();
    		
    		fieldTable.removeIf(obj -> obj.getIdFieldTable().getId().equals(idField));
    		fieldTableRef.removeIf(obj -> obj.getIdFieldTableReferenced().getId().equals(idFieldRef));
    	}
    	flowForeignKeyService.deleteLink(id);
    	flowTableService.updateEntity(flowTableDo);
    	updateJsonSchema(flowTableDo.getFlowDO().getId());
    	
    }
    
    @Override
    @PostMapping("/delete")
    @ResponseBody
    public OperationResult<String> delete(@RequestBody  Map<String, String> body)  {
    	String id = body.get("id");
    	FlowDO flowDo = flowService.retrieveOne(id);
    	try {
    		if (flowDo != null) {
        		flowService.deleteFlow(flowDo);
        		//controllo se esiste il flusso regionale associato
        		BaseSearchInput input = new BaseSearchInput();
        		input.setParam("name", flowDo.getName()+"_REG");
        		List<FlowDO> flowsDoReg = flowDAO.cerca(input);
        		for (FlowDO flowDoReg: flowsDoReg) {
        			flowService.deleteFlow(flowDoReg);
        		}
        		//rinomino le tabelle in modo da poter ricreare un flusso con lo stesso nome senza errori lato db
        		flowService.renameTableFlow(flowDo);
        	} else {
        		return OperationResult.failure("Flusso inesistente");
        	}
    	} catch (Exception e) {
    		LogUtil.logException(LOGGER, "Errore nella eliminazione del flusso", e);
    		return OperationResult.failure("Errore nella eliminazione del flusso : " + e.toString());
    	}
    	
    	return OperationResult.success("Flusso Eliminato con successo");
    	
    }
    
    @Override
    @PostMapping("/searchFlowWithLastVersion")
    @ResponseBody
    public FormFlowDTO searchFlowWithLastVersion(@RequestBody BaseSearchInput searchInput) {

        Pair<FormFlowDTO, SearchInfo> searchResults = formFlowService.getFlowWithLastVersion(searchInput);
        return searchResults.getFirst();

    }
	
    @Override
	@PostMapping(value = "/_export", produces = "application/zip", consumes = "application/json")
	@ResponseBody
	public HttpEntity<byte[]> fileExportFormFlow(@RequestBody FormFlowDTO inputDTO){
		
		String name =	"FlowExport_XML";
		HttpHeaders header = new HttpHeaders();
        String filename =  name+".zip";
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
            .filename(filename)
            .build();
        header.setContentDisposition(contentDisposition);
        
        ExportDataDTO exportDataDTO =  formFlowService.exportDataDTO(inputDTO);
        		
        BaseSearchInput baseSearchInput = new BaseSearchInput();
        baseSearchInput.setValue("type", "REGIONE");
        baseSearchInput.setValue("name", inputDTO.getName().concat("_REG"));
        List<FormFlowDTO> fmRegDTOList = formFlowService.retrieveAllFiltered(baseSearchInput).getFirst();
        ExportDataDTO exportDataDTOReg = formFlowService.exportDataDTO(fmRegDTOList.get(0));
        exportDataDTOReg.setVersionSql("");        
        
        StreamResult sr;
        VersionDO versionDO = versionService.retrieveOne(inputDTO.getVersion());
        String flowName = inputDTO.getName()+"&"+versionDO.getVersion();
        
        File file = new File(flowName+".txt");        
		try {			
			
			sr =  new StreamResult(new FileWriter(file));
			Writer wr = sr.getWriter();
		
			printExportFlowData(wr,exportDataDTO);
			printExportFlowData(wr,exportDataDTOReg);
			wr.append("\n");
			wr.append(formFlowService.getFlowRegionUnionSql(inputDTO));
			wr.flush();
			
			wr.close();
			
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}
		
        byte[] zipbytes = null;
		try {
			byte[]	bytes = FileUtils.readFileToByteArray(file);
			zipbytes = AnagraficaAssistitoUtility.zipBytes(file.getName(), bytes);
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}
	   return new HttpEntity<byte[]>( zipbytes, header);
    }
	
	
    @Override
	@PostMapping(path = "/_import")
    @ResponseBody
    public OperationResult<String> fileImportFormFlow(
    		@RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
            @RequestHeader(name = "fileType", defaultValue = MediaType.ALL_VALUE) String fileType,
            @RequestBody byte[] bytes
            ){
		
		
		File file = null;
		String res = null;
		FileOutputStream fos;
		
		String flowVerStr = fileName.replace(".txt", "");
		String[] flowVerArr = flowVerStr.split("&");
		String flow = flowVerArr[0];
		String version = flowVerArr[1];
		BaseSearchInput input = new BaseSearchInput();
		input.setValue("name", flow);
		FlowVersionDO flowVersionDO = flowVersionDAO.findByFlowNameAndVersionVersion(flow, version);
		if(flowVersionDO==null) {
		try{
			file = File.createTempFile(fileName, ".txt");
			fos = new FileOutputStream(file);
			fos.write(bytes);
				res = formFlowService.executeImportFlowScript(file,version);
				if(res.equals("YES")) {	
					fos.close();
					return OperationResult.success(res);					
				}
				fos.close();
		}catch (Exception e) {
//			e.getMessage();
			LogUtil.logException(LOGGER, "", e);
		}finally {
			  if(file!=null && file.exists())
				file.delete();
			}
		}else {
			return OperationResult.failure();
		}
	return OperationResult.failure();
	
	}
    
    private void printExportFlowData(Writer wr,ExportDataDTO exportDataDTO) throws IOException {    	
    	wr.append(exportDataDTO.getVersionSql());
		wr.append("\n");
		wr.append(exportDataDTO.getFlowSql());
		wr.append("\n");
		wr.flush();
		
		for(String query:exportDataDTO.getFlowTableSql()) {
			wr.append(query);
			wr.flush();									
		}
		
		for(String queryField:exportDataDTO.getFlowTableFieldsSql()) {
			wr.append(queryField);
			wr.flush();
		
		}	
		
		for(String foreignKey:exportDataDTO.getFlowTableForeignFieldsSql()) {
			wr.append(foreignKey);
			wr.flush();
		}
		
		for(String secTableAndMess:exportDataDTO.getCreateTableSectionAndMessagesSql()) {
			wr.append(secTableAndMess);
			wr.append("\n");
			wr.flush();
		}
		
		for(String flowVer:exportDataDTO.getInsertFlowVersionSql()) {
			wr.append(flowVer);
			wr.flush();
		}
    }
    
    private Pair<List<FormFlowDTO>, SearchInfo> updateJsonSchema(String flowId) {
    	BaseSearchInput baseSearchInput = new BaseSearchInput();
        baseSearchInput.setValue("flowId", flowId);
        baseSearchInput.setValue("startIndex", "0");
        baseSearchInput.setValue("limit", "10");
        baseSearchInput.setValue("sortByBean", " size = 0");
        Pair<List<FormFlowDTO>, SearchInfo> formFlowPair = formFlowService.retrieveAllFiltered(baseSearchInput); 
        
        
        // aggiorna json schema del flusso aziendale
        try {
            utils.generateSchema(formFlowPair.getFirst().get(0));
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "ERRORE CREAZIONE JSON SCHEMA", e);
        }
        
        return formFlowPair;
    }

}
