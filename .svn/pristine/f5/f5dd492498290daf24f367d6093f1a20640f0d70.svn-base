package it.eng.care.domain.flow.core.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.config.C3P0DataSource;
import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFormFlowDTO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FlowForeignKeyDOtoFlowForeignKeyDTO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FlowForeignKeyDOtoFormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FlowForeignKeyDTOtoFlowForeignKeyDO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FormFlowTableLinkDTOtoFlowForeignKeyDTO;
import it.eng.care.domain.flow.core.converter.FlowTable.FlowTableDOtoFormFlowTableDTO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDOtoFormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowDTOtoFlowDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableDTOtoFlowTableDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableFieldDTOtoFlowTableFieldDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableLinkDTOtoFlowForeignKeyDO;
import it.eng.care.domain.flow.core.converter.Version.VersionDOtoVersionDTO;
import it.eng.care.domain.flow.core.converter.Version.VersionDTOtoVersionDO;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.FlowForeignKeyDAO;
import it.eng.care.domain.flow.core.dao.FlowTableDAO;
import it.eng.care.domain.flow.core.dao.FlowTableFieldDAO;
import it.eng.care.domain.flow.core.dao.FlowVersionDAO;
import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.dto.ExportDataDTO;
import it.eng.care.domain.flow.core.dto.FlowForeignKeyDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.service.DataSourceService;
import it.eng.care.domain.flow.core.service.FlowForeignKeyService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.FlowTableService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.VersionService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FormFlowServiceImpl implements FormFlowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormFlowServiceImpl.class);

    @Autowired
    EntityManager entityManager;
    @Autowired
    private FlowDAO flowDAO;
    @Autowired
    private FlowForeignKeyDAO flowForeignKeyDAO;  
    @Autowired
    private FlowForeignKeyService flowForeignKeyService;  
    @Autowired
    private FlowVersionDAO flowVersionDAO;
    @Autowired
    private FlowTableDAO flowTableDAO;
    @Autowired
    private FlowTableFieldDAO flowTableFieldDAO;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private DataSourceService datasourceService;
    @Autowired
    private FlowDOtoFormFlowDTO flowDOtoFormFlowDTO;
    @Autowired
    private FormFlowDTOtoFlowDO formFlowDTOtoFlowDO;
    @Autowired
    private FormFlowTableFieldDTOtoFlowTableFieldDO formFlowTableFieldDTOtoFlowTableFieldDO;
    @Autowired
    private FormFlowTableDTOtoFlowTableDO formFlowTableDTOtoFlowTableDO;
    @Autowired
    private FlowTableDOtoFormFlowTableDTO flowTableDOtoFormFlowTableDTO;
    @Autowired
    private FormFlowTableLinkDTOtoFlowForeignKeyDTO formFlowTableLinkDTOtoFlowForeignKeyDTO;
    @Autowired
    private FlowForeignKeyDTOtoFlowForeignKeyDO flowForeignKeyDTOtoFlowForeignKeyDO;
    @Autowired
    FlowForeignKeyDOtoFlowForeignKeyDTO flowForeignKeyDOtoFlowForeignKeyDTO;
    @Autowired
    private FormFlowTableLinkDTOtoFlowForeignKeyDO formFlowTableLinkDTOtoFlowForeignKeyDO;
    @Autowired
    private FlowForeignKeyDOtoFormFlowTableLinkDTO  flowForeignKeyDOtoFormFlowTableLinkDTO;
    @Autowired
    private FlowTableFieldDOtoFormFlowTableFieldDTO flowTableFieldDOtoFormFlowTableFieldDTO;
    @Autowired
    private FlowService flowService;
    @Autowired
    private FlowTableService flowTableService;
    @Autowired
    private FlowRegionUnionService flowRegionUnionService;   
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;   
    @Autowired
    private VersionDOtoVersionDTO versionDOtoVersionDTOConvert;
    @Autowired
    private VersionDTOtoVersionDO versionDOTtoVersionDOConvert;
    @Autowired
    private FormFlowService formFlowService;

    @Override
    public void registerConverters() {
        conversionService.registerConverter(FormFlowDTO.class, FlowDO.class, formFlowDTOtoFlowDO);
        conversionService.registerConverter(FormFlowTableDTO.class, FlowTableDO.class, formFlowTableDTOtoFlowTableDO);
        conversionService.registerConverter(FormFlowTableFieldDTO.class, FlowTableFieldDO.class, formFlowTableFieldDTOtoFlowTableFieldDO);
        conversionService.registerConverter(FormFlowTableLinkDTO.class, FlowForeignKeyDO.class, formFlowTableLinkDTOtoFlowForeignKeyDO);
        conversionService.registerConverter(FlowDO.class, FormFlowDTO.class, flowDOtoFormFlowDTO);
        conversionService.registerConverter(FlowTableDO.class, FormFlowTableDTO.class, flowTableDOtoFormFlowTableDTO);
        conversionService.registerConverter(FormFlowTableLinkDTO.class, FlowForeignKeyDTO.class, formFlowTableLinkDTOtoFlowForeignKeyDTO);
        
        conversionService.registerConverter(FlowForeignKeyDO.class, FormFlowTableLinkDTO.class, flowForeignKeyDOtoFormFlowTableLinkDTO);
        conversionService.registerConverter(FlowForeignKeyDTO.class, FlowForeignKeyDO.class, flowForeignKeyDTOtoFlowForeignKeyDO);
        conversionService.registerConverter(FlowForeignKeyDO.class, FlowForeignKeyDTO.class, flowForeignKeyDOtoFlowForeignKeyDTO);
        conversionService.registerConverter(FlowTableFieldDO.class, FormFlowTableFieldDTO.class, flowTableFieldDOtoFormFlowTableFieldDTO);
        conversionService.registerConverter(VersionDO.class, VersionDTO.class, versionDOtoVersionDTOConvert);
        conversionService.registerConverter(VersionDTO.class, VersionDO.class, versionDOTtoVersionDOConvert);
    }

    @Override
    public String saveEntity(FormFlowDTO formFlowDTO, boolean isCreated) throws Exception {
    	FlowDO flowDO = new FlowDO();
    	try {
    		if(isCreated) {
    	    	BaseSearchInput filter = new BaseSearchInput();
    	    	filter.setValue("nameNoLike", formFlowDTO.getName());
    			List<FlowDO> tmp = flowDAO.cerca(filter);
    			if(tmp != null && tmp.size() > 0) {
    				throw new Exception("Flusso già esistente");
    			}
        	}
        	
        	if(StringUtils.isEmpty(formFlowDTO.getVersion())) {
        		throw new Exception("Versione non selezionata");
        	}
        	
            flowDO = convertAndSave(formFlowDTO);
          //  List<String> tableIds = getFlowTableIds(flowDO);
            
            List<List<FormFlowTableLinkDTO>> list = new ArrayList<>();
            VersionDO versionDO = versionService.retrieveOne(formFlowDTO.getVersion());

            // in caso di aggiornamento, tutte le chiavi esterne salvate verranno 
            // sostituite con quelle inviate con il salvataggio corrente
            if(!isCreated) {
            	flowForeignKeyDAO.deleteByFlow(formFlowDTO.getId());
            }

            for (FormFlowTableDTO formFlowTableDTO : formFlowDTO.getFlowTableList()) {
            	
            	FlowTableDO flowTableDO = new FlowTableDO();
            	formFlowTableDTOtoFlowTableDO.convert(formFlowTableDTO, flowTableDO, new ConversionContext(conversionService));
                list.add(formFlowTableDTO.getListFk());
                flowTableDO.setFlowDO(flowDO);
                flowTableDO.setVersionDO(versionDO);
                if (formFlowTableDTO.getListFk().size() > 0) {
                	if(!isCreated) {
                		// in aggiornamento, le fk vengono inviate dal client e non vanno ricalcolate
                		// per la creazione, invece, bisogna gestire il caso della duplicazione per il quale
                		// dal client arrivano le fk del flusso originale; queste chiavi andranno sostituite
                		// con le nuove fk generate dopo il salvataggio della configurazione. Vd gestione duplicazione più avanti
                		convertAndSaveFlowForeignKeys(formFlowTableDTO);
                	}
                }
                
                // 1 la tabella non esiste -> create table
            	// 2 la tabella esiste -> alter table
            	// 2.1 campo nuovo -> add column
                // 2.2 nome campo modificato -> rename column
            	// 2.3 tipo campo modificato -> modify column -> NON CONSENTITO
                // 2.4 dimensione campo modificata  -> modify column -> INUTILE, DIMENSIONE FISSA A 255 
                // 2.5 campo cancellato -> drop column -> NON CONSENTITO
                
                // 1 la tabella non esiste -> create table
                if(StringUtils.isEmpty(flowTableDO.getId())/* || !tableIds.contains(flowTableDO.getId())*/) {
                	createTable(formFlowDTO, formFlowTableDTO, isCreated);
                } else {
                	// 2 la tabella esiste -> alter table
                	boolean tableSecExs = 
                			checkTableSection(formFlowDTO, formFlowTableDTO,tableName(formFlowDTO, formFlowTableDTO));
                	if(!tableSecExs) {
                		createTable(formFlowDTO, formFlowTableDTO, true);
                	}else {
                		List<FlowTableFieldDO> reqFields = 
                				conversionService.convertAllTo(formFlowTableDTO.getFlowTableFieldList(), FlowTableFieldDO.class);
                		for(FlowTableFieldDO field: reqFields) {            		
                			if(StringUtils.isEmpty(field.getId())) {
                    			// 2.1 campo nuovo -> add column
                    			String sectionName = tableName(formFlowDTO, formFlowTableDTO);
                    			String sql = alterTables(sectionName, field.getName(), field.getFieldType());
                    			alterFields(sql);
                    		} else {              			
                    			// recupera campo da modificare
                    			FlowTableFieldDO currentTableField = flowTableFieldDAO.getOne(field.getId());
                    			
                    			// 2.2 nome campo modificato -> rename column
                    			if(!currentTableField.getName().equals(field.getName())) {
                    				renameColumnDB(flowDO.getName(), formFlowTableDTO.getSection() + "", currentTableField.getName(), field.getName());
                    			}
                    			
                    			//2.3 modifica obbligatorietà del campo
                    			if(currentTableField.getMandatory() != field.getMandatory()) {
                    				try {
                    					changeMandatoryColumnDB(flowDO.getName(), formFlowTableDTO.getSection() + "", currentTableField.getName(), field.getMandatory());
                    				} catch (Exception e) {
                    					LogUtil.logException(LOGGER, "", e);
//                    		            e.printStackTrace();
                    		        }
                    			}
                    		}
                		}
                	}
                	
                	//case where secTable excist but sec table message not
                	boolean tableSecMessageExs = checkTableSection(formFlowDTO, formFlowTableDTO, tableMessageName(formFlowDTO, formFlowTableDTO));
                	if(!tableSecMessageExs)
                		createTable(formFlowDTO, formFlowTableDTO, isCreated);
                      	
                }         
                flowTableDAO.save(flowTableDO);
                convertAndSaveFlowTableFields(formFlowTableDTO, flowTableDO);  
            }
            
            if (isCreated) {
                flowDO.getVersions().add(new FlowVersionDO(flowDO, versionDO));
                duplicateFlowForeignKeys(formFlowDTO, flowDO);
            }
    	} catch (Exception e) {
    		LogUtil.logException(LOGGER, "", e);
//    		e.printStackTrace();
    	}
    	
        return flowDO.getId();
    }
    
    
    /**
     * Compare two list's one sent from front(external) end existing one in db 
     * @param external
     * @param excisting
     * @return
     */
    private int compareTableFieldsList(List<FlowTableFieldDO> external , List<FlowTableFieldDO> excisting) { 	
    	if(external.size() > excisting.size()) {
    		return  1;
    	}else if(external.size() < excisting.size()) {
    		return -1;
    	}else {
    		return  0;
    	}
    }

    @Override
    public String updateEntity(FormFlowDTO formFlowDTO) throws Exception {
        return saveEntity(formFlowDTO, false);
    }
    
    @Override
    public Pair<List<FormFlowDTO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
    	return retrieveAllFiltered(searchInput, false);
    }

    @Override
    public Pair<List<FormFlowDTO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useProfile) {
    	//gets all flowTableFields
        List<FlowTableFieldDO> flowTableFieldDOList = flowTableFieldDAO.cerca(searchInput, useProfile);
    
        if(flowTableFieldDOList == null || flowTableFieldDOList.isEmpty()) {
        	return Pair.of(new ArrayList<FormFlowDTO>(), SearchInfos.create());	
        }
    
        HashMap<String, FormFlowDTO> hashMap = new HashMap<>();
        HashMap<String, FormFlowTableDTO> hashMapTable = new HashMap<>();
        FormFlowDTO formFlowDTO = null;
        List<String> flowTableId = new ArrayList<>();
        
        // iterating flowTableFieldList to get flowTable 
        for (FlowTableFieldDO flowTableFieldDO : flowTableFieldDOList) {
        
        	FormFlowTableFieldDTO formFlowTableFieldDTO = new FormFlowTableFieldDTO();
        	flowTableFieldDOtoFormFlowTableFieldDTO.convert(flowTableFieldDO, formFlowTableFieldDTO, new ConversionContext(conversionService));
            FlowTableDO flowTableDO = flowTableFieldDO.getFlowTable();

            if (!(flowTableFieldDO.getFlowTableFieldId() == null)) {
                flowTableId.add(flowTableFieldDO.getFlowTable().getId());
            }

            FormFlowTableDTO formFlowTableDTO = hashMapTable.get(flowTableDO.getId());
            if (formFlowTableDTO == null) {
            	
            	formFlowTableDTO = new FormFlowTableDTO();
            	flowTableDOtoFormFlowTableDTO.convert(flowTableDO, formFlowTableDTO, new ConversionContext(conversionService));            	

                // creare la lista da inserire
                formFlowTableDTO.setFlowTableFieldList(new ArrayList<>()); 
                
                List<FormFlowTableLinkDTO> tableFkDTO  = fillFormFlowTableFkDTO(flowTableDO);//converts FlowForeignKeyDO to FormFlowTableLinkDTO
                formFlowTableDTO.setListFk(tableFkDTO);
                hashMapTable.put(flowTableDO.getId(), formFlowTableDTO);
   
            }
            
            formFlowTableDTO.getFlowTableFieldList().add(formFlowTableFieldDTO);
            FlowDO flowDO = flowTableDO.getFlowDO();
            formFlowDTO = hashMap.get(flowDO.getId() + flowTableDO.getVersionDO().getId());
            
            if (formFlowDTO == null) {
            	formFlowDTO = new FormFlowDTO();
            	flowDOtoFormFlowDTO.convert(flowDO, formFlowDTO, new ConversionContext(conversionService));
                formFlowDTO.setVersion(flowTableDO.getVersionDO().getId());
                List<FormFlowTableDTO> flowTableDTOList = new ArrayList<>();
                formFlowDTO.setFlowTableList(flowTableDTOList);
                hashMap.put(flowDO.getId() + flowTableDO.getVersionDO().getId(), formFlowDTO);
            }
            boolean founded = false;
            for (FormFlowTableDTO formFlowTableDTO1 : formFlowDTO.getFlowTableList()) {
                if (formFlowTableDTO1.getId().trim().equals(formFlowTableDTO.getId().trim())) {
                    founded = true;
                    break;
                }
            }
            if (!founded)
                formFlowDTO.getFlowTableList().add(formFlowTableDTO);
        }
        
        //FILTRARE LA QUERY NON LA MAPPA, CODICE DUPLICATO!
        List<FormFlowDTO> formFlowDTOList = new ArrayList<>(hashMap.values());
        
        sortFormFlowDTO(formFlowDTOList);
        return Pair.of(formFlowDTOList, SearchInfos.create());
    }
    
    
    @Override
    public Pair<List<FormFlowDTO>, SearchInfo> retrieveAllFilteredInFlowConfiguration(BaseSearchInput searchInput, Boolean useProfile) {
    	List<FormFlowDTO> res = retrieveAllFiltered(searchInput, useProfile).getFirst();
    	
    	searchInput.setParam("status", true);
    	searchInput.setParam("flowType", searchInput.getValue("type"));
    	
        // aggiungi i flussi senza campi nelle sezioni
        List<FlowDO> flowList = flowDAO.cercaInProfile(searchInput);
        if(flowList != null && !flowList.isEmpty()) {
        	HashMap<String, FormFlowDTO> hashMap = new HashMap<>();
        	
        	flowList = flowList.stream().filter(f -> res.stream().filter(fres -> fres.getId().equals(f.getId())).count() == 0).collect(Collectors.toList());
        	if(flowList != null && !flowList.isEmpty()) {
        		for (FlowDO flowDO : flowList) {
        			
        			List<FlowTableDO> tables = flowTableDAO.getFlowTableByFlow(flowDO);
        			if(tables != null && !tables.isEmpty()) {
        				
        				for (FlowTableDO table : tables) {
        					FormFlowDTO formFlow = null;
        					
        					formFlow = hashMap.get(flowDO.getId() + table.getVersionDO().getId());
        					
        					if(formFlow == null) {
        						formFlow = new FormFlowDTO();
        						flowDOtoFormFlowDTO.convert(flowDO, formFlow, new ConversionContext(conversionService));
        						formFlow.setVersion(table.getVersionDO().getId());
            					formFlow.setFlowTableList(new ArrayList<>());
            					hashMap.put(flowDO.getId() + table.getVersionDO().getId(), formFlow);
        					}
        					
        					FormFlowTableDTO formFlowTable = new FormFlowTableDTO();
        	            	flowTableDOtoFormFlowTableDTO.convert(table, formFlowTable, new ConversionContext(conversionService));
        					formFlow.getFlowTableList().add(formFlowTable);
        					
						}
        				
        			}
				}
        		
        		res.addAll(hashMap.values());
        		
        	}
        }
        
        sortFormFlowDTO(res);
        return Pair.of(res, SearchInfos.create());
    }


    

	/**
     * Recupera la configurazione di un flusso
     *
     * @param flowId
     * @param versionId
     * @return
     */
    @Override
    public FormFlowDTO retrieveOne(String flowId, String versionId) {
        BaseSearchInput searchInput = new BaseSearchInput();
        searchInput.setValue("flowId", flowId);
        searchInput.setValue("versionId", versionId);
        Pair<List<FormFlowDTO>, SearchInfo> searchResults = retrieveAllFiltered(searchInput);
        return searchResults.getFirst().get(0);
    }
    
    //Working in spec condition
    @Override
    public void deleteEntity(String id) {
        flowDAO.deleteById(id);
    }
    
    /**
     * Script SQL for creating physical table depended on section
     * formatName = FM_FLOW_(flowName)_(flowTable.getSection())
     * @param flowName
     * @param flowTableDTO
     */
    public String createTableSection(String flowName, FormFlowTableDTO flowTableDTO) {
        List<String> primaryKey = new ArrayList<>();
        String scriptSQL = "";
        //Inserisco EXTRACTION_ID solo nelle tabelle REGIONALI
            scriptSQL = "CREATE TABLE " + "FM_FLOW_" + flowName + "_" + flowTableDTO.getSection() + "("
                    + "UUID VARCHAR(256), EXPORTED VARCHAR(1)," + "EXTRACTION_ID VARCHAR(64)," + "STATUS VARCHAR(32)," + "VERSION VARCHAR(64),"
                    + "STATUS_PROCESSING VARCHAR(8)," + "DATE_PROCESSING DATE,DATE_VALIDATION DATE, DATE_CROSS_VALIDATION DATE, STATUS_GROUP VARCHAR(32), MONTH_RIF VARCHAR(2), YEAR_RIF VARCHAR(4), IMPORT_TYPE VARCHAR(30) ," +
                    "STATE_RECEVIED_APP VARCHAR(30) ,STATE_SEND_REGION VARCHAR(30) , STATUS_REGION VARCHAR(32), LAST_STATE_REGION VARCHAR(32), ";

        List<String> listaCampiTableSection = new ArrayList<>();
        for (FormFlowTableFieldDTO formFlowTableFieldDTO : flowTableDTO.getFlowTableFieldList()) {
            String campo = "";
            String tableFieldLength = String.valueOf(formFlowTableFieldDTO.getPhysicalSize());
            
            if(tableFieldLength == null || tableFieldLength.isEmpty()) {
            	tableFieldLength = "225";
            }
            if (formFlowTableFieldDTO.isPk()) {
                primaryKey.add(formFlowTableFieldDTO.getName());
            }
            if ((formFlowTableFieldDTO.getFieldType().trim()).equals("Date")) {
                campo += formFlowTableFieldDTO.getName() + " DATE";
            }
            if ((formFlowTableFieldDTO.getFieldType().trim()).equals("Integer")) {
                campo += formFlowTableFieldDTO.getName() + " VARCHAR ("+tableFieldLength+")";
            }
            if ((formFlowTableFieldDTO.getFieldType().trim()).equals("String")) {
                campo += formFlowTableFieldDTO.getName() + " VARCHAR("+tableFieldLength+")";
            }

            if(false)
                campo += " NOT NULL,";
            else
                campo += ",";
            listaCampiTableSection.add(campo);
        }
        for (String c : listaCampiTableSection) {
            scriptSQL += c;
        }
        if (scriptSQL.endsWith(",")) {
            scriptSQL = scriptSQL.substring(0, scriptSQL.length() - 1);
        }

        if (primaryKey.size() > 0) {
            scriptSQL += ",PRIMARY KEY (";
            //Se REGIONALE rendo chiave primaria EXTRACTION_ID
            //if (flowName.endsWith("_REG")) {
                scriptSQL += "EXTRACTION_ID" + ",";
            // }
            for (String key : primaryKey) {
                scriptSQL += key + ",";
            }
            if (scriptSQL.endsWith(",")) {
                scriptSQL = scriptSQL.substring(0, scriptSQL.length() - 1);
            }
            scriptSQL += ")";
        }
        scriptSQL += ")";

        return scriptSQL.toUpperCase();
    }

    /**
     * Creazione Tabella Error Message
     * @param flowName
     * @param sectionNumber
     * @return
     */
    public String createTableMessage(String flowName, String sectionNumber, FormFlowTableDTO flowTableDTO) {
        String scriptSQL = "CREATE TABLE FM_FLOW_" + flowName + "_" + sectionNumber + "_" + "MESSAGE ("
                + "ROW_ID VARCHAR(64) ," + "MESSAGE VARCHAR(64) , " + "VALUE VARCHAR(64) ," + "FIELD VARCHAR(64) ,"
                + "CREATION_DATE DATE," + "SEVERITY VARCHAR(64) ";

        List<String> primaryKey = new ArrayList<>();
        for (FormFlowTableFieldDTO formFlowTableFieldDTO : flowTableDTO.getFlowTableFieldList()) {
            String campo = "";
            if (formFlowTableFieldDTO.isPk()) {
            	//va incluso nella PK della MESSAGE se è anche mandatory
                if (formFlowTableFieldDTO.isMandatory()) {
                    primaryKey.add(formFlowTableFieldDTO.getName());
                }
                if ((formFlowTableFieldDTO.getFieldType().trim()).equals("Date")) {
                    campo += formFlowTableFieldDTO.getName() + " DATE";
                }
                if ((formFlowTableFieldDTO.getFieldType().trim()).equals("Integer")) {
                    campo += formFlowTableFieldDTO.getName() + " VARCHAR (255)";
                }
                if ((formFlowTableFieldDTO.getFieldType().trim()).equals("String")) {
                    campo += formFlowTableFieldDTO.getName() + " VARCHAR(255)";
                }
                scriptSQL += "," + campo;
            }

        }
        scriptSQL += ",PRIMARY KEY (";
        if (primaryKey.size() > 0) {
            for (String key : primaryKey) {
                scriptSQL += key + ",";
            }
            if (scriptSQL.endsWith(",")) {
                scriptSQL = scriptSQL.substring(0, scriptSQL.length() - 1);
            }
            scriptSQL += ", ROW_ID, MESSAGE))";
        } else
            scriptSQL += "ROW_ID, MESSAGE))";

        return scriptSQL.toUpperCase();
    }
    
	/**
	 * create physical tables in db 
	 * @param dto
	 * @param tableDto
	 */
    public void createTable(FormFlowDTO dto,FormFlowTableDTO tableDto,boolean isCreated) {
    	if(!isCreated) {
	        createSection(dto, tableDto);
	        String name = dto.getName() + "_REG";
	        String code = dto.getCode() + "_REG";
	        dto.setName(name);
	        dto.setCode(code);
	        dto.setFlowType("REGIONE");
	        createSection(dto, tableDto);
    	} else {
    		createSection(dto, tableDto);
    	}
    }

    public boolean createTableDS(DataSourceDTO dataSourceDTO, String sql) {
        LOGGER.debug("SQL CREATE SCRIPT: " + sql);
        Connection connection = null;
        Statement statement = null;
        try {
            connection = C3P0DataSource.getInstance(dataSourceDTO).getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
            return false;
        } finally {
        	closeConnection(connection, statement);
        }
		return true;
    }
    
    private void closeConnection(Connection connection,Statement statement) {
    	try {
            if (statement != null) 
                statement.close();
            
            if (connection != null) 
                connection.close();
            
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();   
        }
    }

    public void deleteTableDS() {

    }

    // TODO
    public String updateTableSection(FormFlowDTO formFlowDTO) {
        return "";
    }

    // TODO
    public boolean checkStringAlphanumeric(String string) {

        if (string == null || string.trim().isEmpty())
            return false;
        else {
            Pattern alphanumeric = Pattern.compile("[^A-Za-z0-9]");
            // Pattern letter = Pattern.compile("[a-zA-z]");
            // Pattern digit = Pattern.compile("[0-9]");
            // Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            // Pattern eight = Pattern.compile (".{8}");
            Matcher hasAlphanumeric = alphanumeric.matcher(string);
            // Matcher hasDigit = digit.matcher(password);
            // Matcher hasSpecial = special.matcher(password);
            return hasAlphanumeric.find();

        }

    }

    private void deleteTableDS(DataSourceDTO dataSourceDTO, FormFlowTableDTO formFlowTableDTO) {
    }

    private String createDeleteSection(String name, FormFlowTableDTO formFlowTableDTO) {
        return "";
    }
    
    /**
     * checks if section table exist 
     * @param dto
     * @param formFlowTableDTO
     * @param secTableName
     * @return true or false
     */
    private boolean checkTableSection(
            FormFlowDTO dto,
            FormFlowTableDTO formFlowTableDTO,
            String secTableName) {

        return entityManager
                .unwrap(org.hibernate.Session.class)
                .doReturningWork(connection -> {

                    try {
                        DatabaseMetaData dbm = connection.getMetaData();

                        try (ResultSet tables = dbm.getTables(
                                null,
                                null,
                                secTableName.toUpperCase(),
                                null)) {

                            return tables.next();
                        }

                    } catch (SQLException e) {
                    	LogUtil.logException(LOGGER, "Errore nel controllo esistenza tabella "+secTableName+"", e);
                        return false;
                    }
                });
    }

    
    /**
     * creates table name sql string 
     * @param formFlowDTO
     * @param formFlowTableDTO
     * @return
     */
    private String tableName(FormFlowDTO formFlowDTO,FormFlowTableDTO formFlowTableDTO) {
    	return "FM_FLOW_" + formFlowDTO.getName().toUpperCase() + "_" + formFlowTableDTO.getSection();   	
    }
    
    /**
     * creates table messages name sql string 
     * @param formFlowDTO
     * @param formFlowTableDTO
     * @return
     */
    private String tableMessageName(FormFlowDTO formFlowDTO,FormFlowTableDTO formFlowTableDTO) {
    	return "FM_FLOW_" + formFlowDTO.getName().toUpperCase() + "_" + formFlowTableDTO.getSection()+"_MESSAGE";   	
    }
    
    /**
     * create table section in db 
     * FM_FLOW_
     * @param formFlowDTO
     * @param formFlowTableDTO
     */
    private void createSection(FormFlowDTO formFlowDTO,FormFlowTableDTO formFlowTableDTO) {
    	String name = tableName(formFlowDTO, formFlowTableDTO);
    	String nameMessage = tableMessageName(formFlowDTO, formFlowTableDTO);
    	
    	boolean sectionTable = checkTableSection(formFlowDTO, formFlowTableDTO,name);
    	if(!sectionTable) {
    		String flowTableSQL = createTableSection(formFlowDTO.getName(), formFlowTableDTO);
            Query flowTableQuery = entityManager.createNativeQuery(flowTableSQL);
            flowTableQuery.executeUpdate();
    	}
    	
    	boolean sectionMessaageTable = checkTableSection(formFlowDTO, formFlowTableDTO,nameMessage);
    	if(!sectionMessaageTable) {
    		String messageTableSQL = createTableMessage(formFlowDTO.getName(), Integer.toString(formFlowTableDTO.getSection()), formFlowTableDTO);
    		Query messageTableQuery = entityManager.createNativeQuery(messageTableSQL);      
            messageTableQuery.executeUpdate();
    	}      
    }
    

    public String deleteAllTables(List<String> tables) {
        String scriptSQL = "DELETE TABLE ";
        for (String table : tables) {
            scriptSQL += table + ";";
        }
        return scriptSQL;
    }
    
    @Override
    public void deleteFlowTable(BaseSearchInput baseSearchInput){
    	String id = baseSearchInput.getValue("id");
    	FlowTableDO flowTableDO = flowTableService.retrieveOne(id);
    	Set<FlowTableFieldDO> flowTableFieldsDOList = flowTableDO.getFields();
    	if(flowTableFieldsDOList.size() >0) {
    		for(FlowTableFieldDO fieldDO: flowTableFieldsDOList) {
        		flowTableFieldDAO.deleteById(fieldDO.getId());
        	}
    	}    	
    	String flow = flowTableDO.getFlowDO().getCode();
    	flowTableDO.setFlowDO(null);
        flowTableDAO.deleteById(id);
        try {
        deleteSectionTables(flow, flowTableDO);
        }catch(Exception e){
        	LogUtil.logException(LOGGER, "", e);
        }
    	
    }
    
    /**
     * delete all tables depended on creating new section
     * @param flow
     * @param tableDO 
     */
    public void deleteSectionTables(String flow,FlowTableDO tableDO) {
    	deleteSection(flow, tableDO);
    	deleteRegSection(flow,tableDO);
    	deleteMessageTable(flow, tableDO);
    	deleteRegMessageTable(flow, tableDO);
    }
    
    /**
     * delete physical section table 
     * @param flow
     * @param tableDO
     */
    private void deleteSection(String flow,FlowTableDO tableDO) {
    	String SCRIPT_SQL = "DROP TABLE " +
                "FM_FLOW_" + flow +"_"+ tableDO.getSection(); 
    	Query deleteSection = entityManager.createNativeQuery(SCRIPT_SQL);
        deleteSection.executeUpdate();
    }
    
    /**
     * delete physical section REG table 
     * @param flow
     * @param tableDO
     */
    private void deleteRegSection(String flow,FlowTableDO tableDO) {
    	 String SCRIPT_SQL = "DROP TABLE " +
                 "FM_FLOW_" + flow + "_REG" +"_" + tableDO.getSection();
    	 Query deleteSection = entityManager.createNativeQuery(SCRIPT_SQL);
         deleteSection.executeUpdate();
    }
    
    /**
     * delete physical section MESSAGE table 
     * @param flow
     * @param tableDO
     */
    private void deleteMessageTable(String flow,FlowTableDO tableDO) {
   	 String SCRIPT_SQL = "DROP TABLE " +
                "FM_FLOW_" + flow +"_" + tableDO.getSection() + "_MESSAGE";
   	 Query deleteSection = entityManager.createNativeQuery(SCRIPT_SQL);
        deleteSection.executeUpdate();
   }
    
    /**
     * delete physical section REG MESSAGE table 
     * @param flow
     * @param tableDO
     */
    private void deleteRegMessageTable(String flow,FlowTableDO tableDO) {
      	 String SCRIPT_SQL = "DROP TABLE " +
                   "FM_FLOW_" + flow +"_REG"+"_" + tableDO.getSection() + "_MESSAGE";
      	 Query deleteSection = entityManager.createNativeQuery(SCRIPT_SQL);
           deleteSection.executeUpdate();
      }
    
    /**
     * sql script for adding field in to section table
     * @param table
     * @param column
     * @param type
     * @return
     */
    private String alterTables(String table, String column, String type) {
        String scriptSQL = "ALTER TABLE " + table;
        if (type.equals("Date"))
            scriptSQL += " ADD " + column + " DATE";
        else
            scriptSQL += " ADD " + column + " VARCHAR(255)";
        return scriptSQL;
    }
    
    private void alterFields(String sql) {
    	Query flowTableQuery = entityManager.createNativeQuery(sql);
    	flowTableQuery.executeUpdate();
    }
    
    /**
     * rename section table in db
     * @param table
     * @param oldColumnName
     * @param newColumnName
     */
    private void renameColumnDB(String flowName, String section, String oldColumnName, String newColumnName) {
    	String sql = "ALTER TABLE FM_FLOW_" + flowName + "_" + section +" RENAME COLUMN " + oldColumnName + " TO " + newColumnName;
    	Query flowTableQuery = entityManager.createNativeQuery(sql);
    	flowTableQuery.executeUpdate();
    }
    
    /**
     * rename section table in db
     * @param table
     * @param oldColumnName
     * @param newColumnName
     */
    private void changeMandatoryColumnDB(String flowName,
            String section,
            String columnName,
            boolean mandatory) {

		final String tableName = "FM_FLOW_" + flowName + "_" + section;
		final String column = columnName.toUpperCase();
		
		final String sqlColumnCheck =
		"SELECT nullable FROM user_tab_columns " +
		"WHERE table_name = ? AND column_name = ?";
		
		entityManager.unwrap(org.hibernate.Session.class).doWork(conn -> {
			
				String sqlAlter = "";
				long nullCount = 0L;
				
				try (PreparedStatement ps = conn.prepareStatement(sqlColumnCheck)) {
				
				ps.setString(1, tableName);
				ps.setString(2, column);
				
					try (ResultSet rs = ps.executeQuery()) {
					
						if (rs.next()) {
							String nullable = rs.getString("nullable");
							
							if (mandatory) {
							
							// se è nullable → controllo se esistono NULL
							if ("Y".equalsIgnoreCase(nullable)) {
							
							   String countSql =
							           "SELECT COUNT(1) FROM " + tableName +
							           " WHERE " + column + " IS NULL";
							
							   try (PreparedStatement countPs = conn.prepareStatement(countSql);
							        ResultSet countRs = countPs.executeQuery()) {
							
							       if (countRs.next()) {
							           nullCount = countRs.getLong(1);
							       }
							   }
							
							   if (nullCount == 0) {
							       sqlAlter =
							               "ALTER TABLE " + tableName +
							               " MODIFY " + column + " NOT NULL";
							   }
							}
						
						} else {
						
							// se NON nullable → rendo nullable
							if ("N".equalsIgnoreCase(nullable)) {
							   sqlAlter =
							           "ALTER TABLE " + tableName +
							           " MODIFY " + column + " NULL";
							}
						}
					}
				}
				
				if (nullCount == 0 && !sqlAlter.isEmpty()) {
					try (PreparedStatement alterPs = conn.prepareStatement(sqlAlter)) {
						alterPs.executeUpdate();
					}
				}
				
			} catch (SQLException e) {
				LogUtil.logException(LOGGER, "", e);
			}
		});
	}
    
    @Override
    public Pair<FormFlowDTO, SearchInfo> getFlowWithLastVersion(BaseSearchInput input) {

        List<FlowDO> flows = flowDAO.cerca(input);

        String flowId = flows.get(0).getId();
        
        Boolean check = flowManagerProfileService.checkFlowById(flowId);
        if(!check) {
        	return Pair.of(new FormFlowDTO(), SearchInfos.create());
        }
        
        String versionId = null;

        Set<FlowVersionDO> flowVersions = flows.get(0).getVersions();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate = null;
        try {
            maxDate = sdf.parse("1700-01-01");
        } catch (ParseException e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
        }

        for (FlowVersionDO flowVersion : flowVersions) {

            if (flowVersion.getVersion().getCreationDate().compareTo(maxDate) > 0) {

                maxDate = flowVersion.getVersion().getStartDate();

                versionId = flowVersion.getVersion().getId();
            }

        }

        FormFlowDTO formFlowDTO = retrieveOne(flowId, versionId);

        return Pair.of(formFlowDTO, SearchInfos.create());
    }

    
    public Boolean deletebyExtractionId(String extractionId, FlowDO flowDO, VersionDO versionDO){
        FormFlowDTO formFlowDTO = retrieveOne(flowDO.getId(),versionDO.getId());
        for (FormFlowTableDTO formFlowTableDTO: formFlowDTO.getFlowTableList()) {
            String SCRIPT_SQL = "DELETE FROM " +
                                "FM_FLOW_" + formFlowDTO.getName() + "_REG" +"_" + formFlowTableDTO.getSection() +
                                " WHERE EXTRACTION_ID =" + "'" + extractionId + "'";
            Query deleteSection = entityManager.createNativeQuery(SCRIPT_SQL);
            deleteSection.executeUpdate();
        }
        return true;
    }
    
   /**
    * converts FlowTableDO to FormFlowTableLinkDTO
    * @param flowTableDO 
    */
   private List<FormFlowTableLinkDTO> fillFormFlowTableFkDTO(FlowTableDO flowTableDO){
	   List<FormFlowTableLinkDTO> link = null;
	   if (flowTableDO.getTable() != null) {
            Set<FlowForeignKeyDO> listFlowForeignKeyDO = flowTableDO.getTable();
            if (listFlowForeignKeyDO != null) {
        	   link =   conversionService.convertAllTo(listFlowForeignKeyDO, FormFlowTableLinkDTO.class); 
            }           
       } else {     	
       		BaseSearchInput searchInputTmp = new BaseSearchInput();
       		searchInputTmp.setParam("idTable", flowTableDO.getId());
       		List<FlowForeignKeyDO> listFk =  flowForeignKeyService.retrieveAllFiltered(searchInputTmp).getFirst();
       			if (listFk != null) {
       				link = conversionService.convertAllTo(listFk, FormFlowTableLinkDTO.class);               
       			}
       }
	   return link;
   }
   
   /**
    * converts formFlowDTO to flowDO and save to database
    * @param formFlowDTO
    * @return 
    */
   private FlowDO convertAndSave(FormFlowDTO formFlowDTO) {
	   FlowDO flowDO = new FlowDO();
       formFlowDTOtoFlowDO.convert(formFlowDTO, flowDO, new ConversionContext(conversionService));
       return flowDAO.save(flowDO);
   }
   
   /**
    * convert flowTableFieldsDTO to flowTableFieldsDO and save flowTableFieldsDO
    * @param formFlowTableDTO
    * @param flowTableDO
    */
   private void convertAndSaveFlowTableFields(FormFlowTableDTO formFlowTableDTO,FlowTableDO flowTableDO) {
	   for (FormFlowTableFieldDTO formFlowTableFieldDTO : formFlowTableDTO.getFlowTableFieldList()) {
       	FlowTableFieldDO flowTableFieldDO = new FlowTableFieldDO();
       	formFlowTableFieldDTOtoFlowTableFieldDO.convert(formFlowTableFieldDTO, flowTableFieldDO, new ConversionContext(conversionService));             
           flowTableFieldDO.setFlowTable(flowTableDO);
           flowTableFieldDAO.save(flowTableFieldDO);
       }
   }
   
   private void convertAndSaveFlowForeignKeys(FormFlowTableDTO formFlowTableDTO) {
	   
	   formFlowTableDTO.getListFk().removeIf(fk -> StringUtils.isEmpty(fk.getIdFieldTable()) || StringUtils.isEmpty(fk.getIdFieldTableReferenced()));
	   
	   for (FormFlowTableLinkDTO formFlowTableLinkDTO : formFlowTableDTO.getListFk()) {
		   if(!(StringUtils.isEmpty(formFlowTableLinkDTO.getIdFieldTable())) || StringUtils.isEmpty(formFlowTableLinkDTO.getIdFieldTableReferenced())) {
			   FlowForeignKeyDO flowForeignKeyDO = new FlowForeignKeyDO();
		       	formFlowTableLinkDTOtoFlowForeignKeyDO.convert(formFlowTableLinkDTO, flowForeignKeyDO, new ConversionContext(conversionService));                 
		        flowForeignKeyDAO.save(flowForeignKeyDO);			   
		   }
       }
   }
   
   /**
    * L'elenco delle chiavi esterne di una richiesta di duplicazione di un flusso contiene gli id table e id field del flusso originale; bisogna allora
    * aggiornare questi riferimento andando a recuperare i nuovi.
    * L'operazione deve essere eseguita dopo il salvataggio del nuovo flusso, ovvero dopo la generazione degli id table e field.
    * A partire dai nomi delle table e dei fields presenti nell'elenco originale delle chiavi esterne, si effettua una ricerca dei corrispondendi campi
    * del nuovo flusso.
    * 
    * DTO FK ORIGINALE -> ID_TABLE_ORIGINALE | ID_TABLE_FIELD_ORIGINALE | ID_REF_TABLE_ORIGINALE | ID_REF_TABLE_FIELD_ORIGINALE
    * DTO FK DUPLICATA = FK ORIGINALE
    * 
    * 1. PER OGNI COPPIA TABLE/FIELD IN FK DUPLICATA CERCA LA COPPIA TABLE/FIELD CORRISPONDENTE DEL FLUSSO DUPLICATO UTILIZZANDO TABLE.NAME E FIELD.NAME
    * 2. AGGIORNA I RIFERIMENTO  IN DO FK DUPLICATA
    * 3. PER OGNI COPPIA REF_TABLE/REF_FIELD IN FK DUPLICATA CERCA LA COPPIA REF_TABLE/REF_FIELD CORRISPONDENTE DEL FLUSSO DUPLICATO UTILIZZANDO REF_TABLE.NAME E REF_FIELD.NAME
    * 4. AGGIORNA I RIFERIMENTO  IN DO FK DUPLICATA
    * 
    * 
    * @param duplicatedFormFlowDTO
    * @param duplicatedformFlowDO
    */
   private void duplicateFlowForeignKeys(FormFlowDTO duplicatedFormFlowDTO, FlowDO duplicatedformFlowDO) {
	   List<FlowTableDO> upFlowTableList = flowTableDAO.getFlowTableByFlow(duplicatedformFlowDO);
	   
	   for (FormFlowTableDTO formFlowTableDTO : duplicatedFormFlowDTO.getFlowTableList()) {
		   if(formFlowTableDTO.getListFk() != null) {
			   
			   for (FormFlowTableLinkDTO duplicatedLinkDTO : formFlowTableDTO.getListFk()) {
				   Boolean ref1 = false, ref2 = false;
				   
			       	FlowForeignKeyDO duplicatedLinkDO = new FlowForeignKeyDO();
			       	formFlowTableLinkDTOtoFlowForeignKeyDO.convert(duplicatedLinkDTO, duplicatedLinkDO, new ConversionContext(conversionService));
			       	duplicatedLinkDO.setId(null);
			       	// cerca i campi e le sezioni del flusso duplicato usando i nomi delle sezioni e dei campi 
			       	// del flusso originale dichiarati nell'elenco delle fk da salvare
			       	String originalTable = duplicatedLinkDO.getIdTable().getName();
			       	String originalTableField = duplicatedLinkDO.getIdFieldTable().getName();
			       	String originalRefTable = duplicatedLinkDO.getIdTableReferenced().getName();
			       	String originalRefFieldTable = duplicatedLinkDO.getIdFieldTableReferenced().getName();
			       	
			       	FlowTableDO upFlowTable = upFlowTableList.stream().filter(ft -> ft.getName().equals(originalTable)).findAny().orElse(null);
			       	if(upFlowTable != null) {

			       		BaseSearchInput input = new BaseSearchInput();
		            	input.setParam("idTable", upFlowTable.getId());
		            	input.setParam("section", upFlowTable.getSection().toString());
		            	List<FlowTableFieldDO> upFlowTableFields = flowTableFieldDAO.cerca(input);
			       		
			       		FlowTableFieldDO upFlowTableField = upFlowTableFields.stream().filter(tf -> tf.getName().equals(originalTableField)).findAny().orElse(null);
			       		if(upFlowTableField != null) {
			       			duplicatedLinkDO.setIdTable(upFlowTable);
			       			duplicatedLinkDO.setIdFieldTable(upFlowTableField);
			       			ref1 = true;
			       		}
			       	}
			       	
			       	FlowTableDO upFlowRefTable = upFlowTableList.stream().filter(ft -> ft.getName().equals(originalRefTable)).findAny().orElse(null);
			       	if(upFlowRefTable != null) {
			       		
			       		BaseSearchInput input = new BaseSearchInput();
		            	input.setParam("idTable", upFlowRefTable.getId());
		            	input.setParam("section", upFlowRefTable.getSection().toString());
		            	List<FlowTableFieldDO> upFlowRefTableFields = flowTableFieldDAO.cerca(input);
			       		
			       		FlowTableFieldDO upFlowRefTableField = upFlowRefTableFields.stream().filter(tf -> tf.getName().equals(originalRefFieldTable)).findAny().orElse(null);
			       		if(upFlowRefTableField != null) {
			       			duplicatedLinkDO.setIdTableReferenced(upFlowRefTable);
			       			duplicatedLinkDO.setIdFieldTableReferenced(upFlowRefTableField);
			       			ref2 = true;
			       		}
			       	}
			       	
			       	if(ref1 && ref2) {
			       		flowForeignKeyDAO.save(duplicatedLinkDO);
			       	}			       	
			   }

		   }
       }
   }
   
   /**
    * gets all flowTables by the flowDO and adds id's to the list
    * @param flowDO
    * @return
    */
   private List<String>getFlowTableIds(FlowDO flowDO){
	   List<String> tableIds = new ArrayList<>();
       List<FlowTableDO>flowTableList = flowTableDAO.getFlowTableByFlow(flowDO);
       for(FlowTableDO table:flowTableList) {
       	tableIds.add(table.getId());
       }
       return tableIds;
   }
   
   /**
    * sort fm_flow by status
    * sort fm_flow_table by section
    * @param formFlowDTOList
    */
   private void sortFormFlowDTO(List<FormFlowDTO> formFlowDTOList) {
   	//ordino i flussi per stato Attivo...Disattivo
       formFlowDTOList.sort(Comparator.comparing(FormFlowDTO::isStatus).reversed());
       //ordino le sezioni del flusso 0,1,2,3...
       for (FormFlowDTO formFlowDTOsort : formFlowDTOList) {
           formFlowDTOsort.getFlowTableList().sort(Comparator.comparing(FormFlowTableDTO::getSection));
           //ordino i field per ogni sezione 0,1,2,3,4,5....
           for (FormFlowTableDTO formFlowTableSort : formFlowDTOsort.getFlowTableList()) {
        	   if(formFlowTableSort.getFlowTableFieldList() != null) {
        		   formFlowTableSort.getFlowTableFieldList().sort(Comparator.comparing(FormFlowTableFieldDTO::getPosition));
        	   }
           }
       }  		
	}

	@Override
	public FlowVersionDO getFlowVersion(String flowName, String versionName) {
		FlowVersionDO flowVersionDO = flowVersionDAO.findByFlowNameAndVersionVersion(flowName, versionName);		
		return flowVersionDO;
	}

	/**
	 * Container object for sql queries
	 */
	@Override
	public ExportDataDTO exportDataDTO(FormFlowDTO dto) {
			
		ExportDataDTO exportDto = new ExportDataDTO();
		exportDto.setVersionSql(insertVersionSql(dto));
		exportDto.setFlowSql(insertFmFlowSql(dto));
		exportDto.setFlowTableSql(insertFmFlowTableSql(dto));
		exportDto.setFlowTableFieldsSql(insertFmFlowTableFieldsSql(dto));
		exportDto.setFlowTableForeignFieldsSql(foreignKeysSqlInsert(dto));
		exportDto.setCreateTableSectionAndMessagesSql(createTableSectionsAndMessages(dto));
		exportDto.setInsertFlowVersionSql(insertFlowVersionSql(dto));
		
		return exportDto;
	}
	
	/**
	 * create INSERT query for FM_VERSION table
	 * @param formFlowDTO
	 * @return
	 */
	private String insertVersionSql(FormFlowDTO dto) {
		VersionDO versionDO = versionService.retrieveOne(dto.getVersion());
		String insertQuery = "INSERT INTO FM_VERSION (ID,VERSION,DESCRIPTION,START_DATE,END_DATE,CREATION_DATE)"+
				" VALUES ("
				+convertStringValue(versionDO.getId())+","
				+convertStringValue(versionDO.getVersion())+","
				+convertStringValue(versionDO.getDescription())+","
				+convertDateValue(versionDO.getStartDate())+","
				+convertDateValue(versionDO.getEndDate())+","
				+convertDateValue(versionDO.getCreationDate())+");";
		return insertQuery;
	}
	
	/**
	 * create INSERT query for FM_FLOW table
	 * @param formFlowDTO
	 * @return
	 */
	private String insertFmFlowSql(FormFlowDTO dto) {
		FlowDO flowDO = new FlowDO();
	    formFlowDTOtoFlowDO.convert(dto, flowDO, new ConversionContext(conversionService));
		String insertQuery = "INSERT INTO FM_FLOW "
				+ "(ID,CODE,NAME,DESCRIPTION,STATUS,REMOTE_SEND,UNIQUENESS,SCHEDULING,PERIODICITY,USER_CREATION,DATA_CREATION,FL_JSON_SCHEMA,MONTHLY_DEADLINE,YEARLY_DEADLINE,COMM_PROT,TYPE,DESCRB)"+
				" VALUES ("				
				+""+convertStringValue(flowDO.getId())+","
				+""+convertStringValue(flowDO.getCode())+","
				+""+convertStringValue(flowDO.getName())+","
				+""+convertStringValue(flowDO.getDescription())+","
				+""+convertBoolIntValue(flowDO.getStatus())+","				
				+""+convertBoolIntValue(flowDO.getRemoteSend())+","
				+""+convertBoolIntValue(flowDO.getUniqueness())+","
				+""+convertBoolIntValue(flowDO.getScheduling())+","
				+""+convertStringValue(flowDO.getPeriodicy())+","
				+""+convertStringValue(flowDO.getUserCreation())+","
				+""+convertDateValue(flowDO.getDataCreation())+","
				+"EMPTY_BLOB(),"
				+""+convertStringValue(flowDO.getMonthlyDeadline())+","
				+""+convertStringValue(flowDO.getYearlyDeadline())+","
				+""+convertStringValue(flowDO.getCommProt())+","
				+""+convertStringValue(flowDO.getFlowType())+","
				+""+convertStringValue(flowDO.getDescrb())+");";
		
		return insertQuery;
	}
	
	/**
	 * create INSERT query for FM_FLOW_TABLE table
	 * @param formFlowDTO
	 * @return
	 */
	private List<String> insertFmFlowTableSql(FormFlowDTO formFlowDTO) {
		List<String> insertFmFlowTableSql = new LinkedList<String>();
		for (FormFlowTableDTO formFlowTableDTO : formFlowDTO.getFlowTableList()) {
			FlowTableDO flowTableDO = flowTableService.retrieveOne(formFlowTableDTO.getId());       	
			
			String insertQuery = "INSERT INTO FM_FLOW_TABLE "
					+ "(ID,NAME,DESCRIPTION,SECTION,FLOW,VERSION,DATASOURCE,XSD,FLOWVERSION,REQUIRED)"+
					" VALUES ("				
					+""+convertStringValue(flowTableDO.getId())+","
					+""+convertStringValue(flowTableDO.getName())+","
					+""+convertStringValue(flowTableDO.getDescription())+","
					+""+flowTableDO.getSection()+","
					+""+convertStringValue(flowTableDO.getFlowDO().getId())+","				
					+""+convertStringValue(flowTableDO.getVersionDO().getId())+","
					+""+convertStringValue(flowTableDO.getDataSource() == null ? null : flowTableDO.getDataSource().getId())+","
					+"EMPTY_BLOB(),"
					+""+convertStringValue(null)+","
					+""+convertBoolIntValue(flowTableDO.getRequired())+");".concat("\n");
			
			insertFmFlowTableSql.add(insertQuery);
		}
		return insertFmFlowTableSql;
	}
	
	/**
	 * create INSERT query for FM_FLOW_TABLE_FIELD table
	 * @param formFlowDTO
	 * @return
	 */
	private List<String> insertFmFlowTableFieldsSql(FormFlowDTO formFlowDTO) {
		List<String> insertFmFlowTableFieldsSql = new LinkedList<String>();
		
		for (FormFlowTableDTO formFlowTableDTO : formFlowDTO.getFlowTableList()) {
			FlowTableDO flowTableDO = flowTableService.retrieveOne(formFlowTableDTO.getId()); 
			for(FlowTableFieldDO fields : flowTableDO.getFields()) {
				String insertQuery = "INSERT INTO FM_FLOW_TABLE_FIELD "
						+ "(ID,NAME,DESCRIPTION,FIELD_TYPE,POSITION,IS_PK,FLOW_TABLE,FLOW_TABLE_FIELD_ID,"
						+ "LENGTH,MANDATORY,REG_EXP,IS_REFERENCE_DATE,GROUPS,ENABLE_CRYPT,DESCRIPTIONSM,ACTIVE)"+
						" VALUES ("				
						+""+convertStringValue(fields.getId())+","
						+""+convertStringValue(fields.getName())+","
						+""+convertStringValue(fields.getDescription())+","
						+""+convertStringValue(fields.getFieldType())+","
						+""+fields.getPosition()+","
						+""+convertBoolCharValue(fields.getPk()) +","				
						+""+convertStringValue(fields.getFlowTable().getId())+","
						+""+convertStringValue(fields.getFlowTableFieldId())+","
						+""+fields.getLength()+","
						+""+convertBoolCharValue(fields.getMandatory())+","
						+""+convertStringValue(fields.getRegExp())+","
						+""+convertBoolCharValue(fields.getReferenceDate()) +","
						+""+convertBoolCharValue(fields.getGroups()) +","
						+""+convertBoolCharValue(fields.getEnableCrypt()) +","
						+""+convertStringValue(fields.getDescriptionsm())+","
						+""+convertBoolCharValue(fields.getActive())+");".concat("\n");
				
				insertFmFlowTableFieldsSql.add(insertQuery);
			}
		}
		
		return insertFmFlowTableFieldsSql;
	}
	
	/**
	 * create INSERT query for FM_FLOW_FOREIGNKEY table
	 * @param formFlowDTO
	 * @return
	 */
	private List<String> foreignKeysSqlInsert(FormFlowDTO formFlowDTO){
		List<String> foreignKeysSqlInsert = new LinkedList<String>();
		for (FormFlowTableDTO formFlowTableDTO : formFlowDTO.getFlowTableList()) {
			FlowTableDO flowTableDO = flowTableService.retrieveOne(formFlowTableDTO.getId()); 
			
			for(FlowForeignKeyDO keyDO:flowTableDO.getTable()) {
				String insertQuery = "INSERT INTO FM_FLOW_FOREIGNKEY "
						+ "(ID,ID_TABLE,ID_TABLE_REFERENCED,ID_FIELD_TABLE,ID_FIELD_TABLE_REFERENCED,JSON_FIELD,JSON_FIELD_TYPE,MANDATORY)"+
						" VALUES ("				
						+""+convertStringValue(keyDO.getId())+","
						+""+convertStringValue(keyDO.getIdTable().getId())+","
						+""+convertStringValue(keyDO.getIdTableReferenced().getId())+","
						+""+convertStringValue(keyDO.getIdFieldTable().getId())+","				
						+""+convertStringValue(keyDO.getIdFieldTableReferenced().getId())+","
						+""+convertStringValue(keyDO.getJsonField())+","
						+""+convertStringValue(keyDO.getJsonFieldType())+","
						+""+convertBoolCharValue(keyDO.isMandatory())+");".concat("\n");
				
				foreignKeysSqlInsert.add(insertQuery);
			}
		}
		return foreignKeysSqlInsert;
	}
	
	/**
	 * create 2 CREATE query for tables
	 * 1.FM_FLOW_(flowName)_(section)
	 * 2.FM_FLOW_(flowName)_(section)_MESSAGES
	 * @param formFlowDTO
	 * @return
	 */
	private List<String> createTableSectionsAndMessages(FormFlowDTO formFlowDTO){
		List<String>tableSecAndMessagesQuery = new LinkedList<String>();
		
		for(FormFlowTableDTO tableDto:formFlowDTO.getFlowTableList()) {
			FlowTableDO flowTableDO = flowTableService.retrieveOne(tableDto.getId());
			String flowName = flowTableDO.getFlowDO().getName();
			String sectionNum = String.valueOf(flowTableDO.getSection());
			String tableSecQuery = createTableSection(flowName, tableDto);
			String tableMessages = createTableMessage(flowName, sectionNum, tableDto);
			tableSecAndMessagesQuery.add(tableSecQuery);
			tableSecAndMessagesQuery.add(tableMessages);
		}
		return tableSecAndMessagesQuery;
	}
	
	/**
	 * create INSERT query for FM_FLOW_VERSION table
	 * @param formFlowDTO
	 * @return
	 */
	private List<String> insertFlowVersionSql(FormFlowDTO formFlowDTO){
		List<String>insertFlowVersionSql = new LinkedList<String>();
		FlowDO flowDO = flowService.retrieveOne(formFlowDTO.getId());
		for(FlowVersionDO flowVerDO : flowDO.getVersions()) {
			String insertQuery = "INSERT INTO FM_FLOW_VERSION "
					+ "(FLOW,VERSION)"+
					" VALUES ("				
					+""+convertStringValue(flowVerDO.getFlow().getId())+","
					+""+convertStringValue(flowVerDO.getVersion().getId())+");";
					//+""+convertStringValue(flowVerDO.getJsonSchema())+");";
			insertFlowVersionSql.add(insertQuery);
		}
	
		return insertFlowVersionSql;
	}
	
	/**
	 * create INSERT query for FM_FLOW_REG_UNION table
	 * @param formFlowDTO
	 * @return
	 */
	@Override
	public String getFlowRegionUnionSql(FormFlowDTO formFlowDTO) {
		BaseSearchInput baseSearchInput = new BaseSearchInput();
        baseSearchInput.setValue("flow", formFlowDTO.getId());
		List<FlowRegionUnionDO> regionUnionDOs =  flowRegionUnionService.retrieveAllFiltered(baseSearchInput).getFirst();
		FlowRegionUnionDO regionUnionDO = regionUnionDOs.get(0);
		String insertQuery = "INSERT INTO FM_FLOW_REG_UNION "
				+ "(ID,FLOW_LOC,FLOW_REG)"+
				" VALUES ("				
				+""+convertStringValue(regionUnionDO.getId())+","
				+""+convertStringValue(regionUnionDO.getFlowLocal().getId())+","
				+""+convertStringValue(regionUnionDO.getFlowRegion().getId())+");".concat("\n");
		
		return insertQuery;
	}
	
	private Integer convertBoolIntValue(Boolean value) {
		if(value != null) {
			return value ? 1 : 0;
		}
		return 0;
	}
	
	private String convertBoolCharValue(Boolean value) {
		return value == null || value ? "'1'" : "'0'";
	}
	
	private String convertStringValue(String value) {	
		return value == null ? null : "'"+value.replaceAll("(\\w)'(\\w)", "$1\\\''$2")+"'";
	}
	
	private String convertDateValue(Date dateVal) {
		String pattern = "dd-MMM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dateVal);
		return "'"+date.toUpperCase()+"'";		
	}

	@Override
	@Transactional
	public String executeImportFlowScript(File file,String version) {
		String confirm;
		StringBuffer buffer = new StringBuffer();
		List<String> tableQuerysList = new LinkedList<String>();
		try (FileReader fr = new FileReader(file);
			     BufferedReader br = new BufferedReader(fr)) {
			
			BaseSearchInput serInput = new BaseSearchInput();
			serInput.setValue("versionLike", version);
			List<VersionDO> versionDOs = versionService.retrieveAllFiltered(serInput).getFirst();
			
			String line;
			while((line=br.readLine())!=null) { 			  
				if(!line.isEmpty()) {
					if(line.startsWith("CREATE")) {
						tableQuerysList.add(line);
					}else {				
						if(line.contains("FM_VERSION") && versionDOs.size()>0)
							continue;
	           
						buffer.append(line);
						buffer.append("\n");	
			}
				}			 
			}
			br.close();
			fr.close();
			
			importFile(buffer.toString());
			confirm =  "YES";
					
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);	
//			e.printStackTrace();
			confirm =  "NO";
		}
		 		 
		if(confirm.equals("YES"))
			for(String line:tableQuerysList)
			createTables(line);
		
		return confirm;		 		 
		} 
	
	
	private	void importFile(String script) {
		if(script != null && !script.isEmpty() ) {
			String query = "BEGIN "+ script + " END;";
			Query q = entityManager.createNativeQuery(query);
			q.executeUpdate();
		}	
	}
	
	private void createTables(String line) {		
		Query crQuery = entityManager.createNativeQuery(line);
		crQuery.executeUpdate();
	}


}