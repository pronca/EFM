package it.eng.care.domain.flow.b2b.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.b2b.exception.ValidationFlowException;
import it.eng.care.domain.flow.b2b.model.FlowParameter;
import it.eng.care.domain.flow.b2b.model.FlowQueryBuilder;
import it.eng.care.domain.flow.b2b.model.FlowQueryBuilder.QueryType;
import it.eng.care.domain.flow.b2b.model.FlowResult;
import it.eng.care.domain.flow.b2b.model.KeysDTO;
import it.eng.care.domain.flow.b2b.model.LockedMessageDTO;
import it.eng.care.domain.flow.b2b.model.ProcessSingleFlowResult;
import it.eng.care.domain.flow.b2b.service.BatchFlowService;
import it.eng.care.domain.flow.b2b.service.JsonFlowService;
import it.eng.care.domain.flow.b2b.service.ValidationFlowService;
import it.eng.care.domain.flow.b2b.utils.FlowOperationResult;
import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.dao.FlowTableDAOCustom;
import it.eng.care.domain.flow.core.dao.LockedMessageDAO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.LockedMessageDO;
import it.eng.care.domain.flow.core.enumeration.ErrorServiceStatusEnum;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;
import it.eng.care.domain.flow.core.enumeration.StateReceviedAppEnum;
import it.eng.care.domain.flow.core.enumeration.StateSendRegionEnum;
import it.eng.care.domain.flow.core.service.FlowCacheService;
import it.eng.care.domain.flow.core.service.FlowExportRequestService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.FlowTableFieldService;
import it.eng.care.domain.flow.core.service.MonitorSdoXlService;
import it.eng.care.domain.flow.core.service.VersionService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.crypt.CryptoManager;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.platform.common.lang.StringUtils;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import jakarta.annotation.PostConstruct;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.sql.Clob;
import java.io.Reader;

@Service
@Transactional
public class JsonFlowServiceImpl implements JsonFlowService {

	private static Logger logger = LoggerFactory.getLogger(JsonFlowServiceImpl.class);

	@Value("${flow.b2b.tableCacheSize:10}")
	private int cacheSize;

	@Value("${flow.b2b.tableCacheExpireMin:10}")
	private int cacheExpireMin;

	@Autowired
	FlowTableDAOCustom flowTableDAO;
	
	@Autowired
	FlowCacheService flowCacheService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CryptoManager crypto;
	
	@Autowired
	FlowTableFieldService flowTableFieldService;
	
	@Autowired
	private MonitorSdoXlService monitorSdoXlService;
		
	@Autowired
	private ValidationFlowService validationFlowService;

	@Autowired
	private BatchFlowService batchFlowService;

	@Autowired
	private ConfigurationDAO configuration;

	@Autowired
	private VersionService versionService;

	@Autowired
	private FlowService flowService;

	@Autowired
	private TabgenService tabgenService;

	@Autowired
	private FlowExportRequestService flowExportRequestService;

	@Autowired
	private FlowOperationResult flowOperationResult;
	
	@Autowired
	private LockedMessageDAO lockedMessageDAO;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@PostConstruct
	protected void init() {
		/*
		 * flowTableCache =
		 * CacheBuilder.newBuilder().recordStats().maximumSize(cacheSize)
		 * .expireAfterWrite(cacheExpireMin, TimeUnit.MINUTES).build(new
		 * CacheLoader<String, FlowTableDO>() {
		 * 
		 * @Override public FlowTableDO load(String flownameVersion) throws Exception {
		 * String[] nameVersion = flownameVersion.split(CACHE_ID_SEPARATOR); return
		 * getRootTable(nameVersion[0], nameVersion[1]); } });
		 */
	}

	//obsoleto
	private FlowTableDO getRootTable(String flowName, String versionName) {
		BaseSearchInput poInput = new BaseSearchInput();
		poInput.setValue("flowNameEqual", flowName);
		poInput.setValue("versionNameEqual", versionName);
		// poInput.setSortBy(Arrays.asList(new SortBy("section", "asc")));

		List<FlowTableDO> tables = flowTableDAO.cerca(poInput);
		// ordino le sezioni del flusso 0,1,2,3...

		tables.sort(Comparator.comparing(FlowTableDO::getSection));

		if (tables != null && tables.size() > 0) {
			return tables.get(0);
		}
		return null;
	}

	private FlowTableDO getCachedRootTable(String flowName, String versionName) throws Exception {
	
		
		return flowCacheService.getRootTable(flowName, versionName);
	}

	/**
	 * Genera le query di insert/delete/update per la lista di Flusso contenuti
	 * passata in input
	 */
	@Override
	public List<FlowQueryBuilder> generateQueryFromJsonList(List<JsonObject> objList, String flowName,
			String versionName, String extractionId, List<String> stateReceviedApp, List<String> stateSendRegion)
			throws Exception {
		FlowTableDO table = getCachedRootTable(flowName, versionName);
		List<FlowQueryBuilder> toRet = new ArrayList<>();
		if (table != null) {

			for (int i = 0; i < objList.size(); i++) {
				toRet.addAll(generateQueryFromJson(objList.get(i), flowName, versionName, extractionId,
						stateReceviedApp.get(i), stateSendRegion.get(i)));
			}
		}
		return toRet;
	}

	/**
	 * Genera le query di insert/delete/update per il singolo Flusso contenuto
	 * nell'oggetto
	 * 
	 * @throws Exception
	 */
	@Override
	public List<FlowQueryBuilder> generateQueryFromJson(JsonObject obj, String flowName, String versionName,
	        String extractionId, String stateReceviedApp, String stateSendRegion) throws Exception {
	    return generateQueryFromJson(
	            obj,
	            flowName,
	            versionName,
	            extractionId,
	            stateReceviedApp,
	            stateSendRegion,
	            null,
	            null
	    );
	}
	public List<FlowQueryBuilder> generateQueryFromJson(JsonObject obj, String flowName, String versionName,
	        String extractionId, String stateReceviedApp, String stateSendRegion,
	        String statusRegion, String lastStateRegion) throws Exception {

	    FlowTableDO table = getCachedRootTable(flowName, versionName);
	    if (table != null) {

	        List<FlowQueryBuilder> toRet = executeGenerateQueryFromJson(
	                obj.getJsonObject(table.getName()),
	                flowName,
	                table,
	                extractionId,
	                null,
	                versionName,
	                stateReceviedApp,
	                stateSendRegion,
	                statusRegion,
	                lastStateRegion
	        );

	        toRet.sort((b1, b2) -> {
	            if (b1.getType() == b2.getType()) {
	                return b2.getTableName().compareTo(b1.getTableName());
	            } else {
	                return b1.getType().compareTo(b2.getType());
	            }
	        });

	        return toRet;
	    }
	    return new ArrayList<>(0);
	}

	/**
	 * Genera le query per un singolo oggetto e poi richiama ricorsivamente lo
	 * stesso metodo per fare lo stesso sugli oggetti figli
	 * 
	 * @param obj          Oggetto da cui estrarre i valori
	 * @param flowName     Nome del flusso usato per generare il nome della tabella
	 * @param table        Tabella da cui estrarre i campi
	 * @param parentObject Oggetto padre (facoltativo), se presente il medoto prova
	 *                     ad estrarre i campi per le foreign key
	 * @param versionName
	 * @return
	 */
	private List<FlowQueryBuilder> executeGenerateQueryFromJson(JsonObject obj, String flowName, FlowTableDO table,
	        String extractionId, JsonObject parentObject, String versionName, String stateReceviedApp,
	        String stateSendRegion, String statusRegion, String lastStateRegion) {

	    String tableName = "FM_FLOW_" + flowName + "_" + table.getSection();

	    List<FlowQueryBuilder> toRet = new ArrayList<>();

	    List<FlowParameter> parameters = generateParameters(obj, table.getFields(), false);

	    String id = UUID.randomUUID().toString();

	    if (table.getSection() == 0) {
	        parameters.addAll(getMandatoryParameters(
	                id,
	                extractionId,
	                versionName,
	                ImportTypeEnum.FROM_REST_SERVICE.name(),
	                stateReceviedApp,
	                stateSendRegion,
	                statusRegion,
	                lastStateRegion
	        ));

	    } else {
	        parameters.addAll(getMandatoryParameters(
	                id,
	                extractionId,
	                versionName,
	                ImportTypeEnum.FROM_REST_SERVICE.name(),
	                null,
	                null,
	                null,
	                null
	        ));
	    }

	    if (parentObject != null) {
	        List<FlowParameter> foreignkeys = generateForeignParameters(parentObject, table.getTable());

	        parameters.addAll(foreignkeys);

	        FlowQueryBuilder deleteQuery = FlowQueryBuilder
	                .get(QueryType.DELETE)
	                .parameters(foreignkeys)
	                .tableName(tableName);

	        toRet.add(deleteQuery);

	        FlowQueryBuilder deleteMsgQuery = FlowQueryBuilder
	                .get(QueryType.DELETE_MSG)
	                .parameters(foreignkeys)
	                .tableName(tableName);

	        toRet.add(deleteMsgQuery);
	    } else {
	        FlowQueryBuilder deleteQuery = FlowQueryBuilder
	                .get(QueryType.DELETE)
	                .parameters(parameters)
	                .tableName(tableName);

	        toRet.add(deleteQuery);

	        FlowQueryBuilder deleteMsgQuery = FlowQueryBuilder
	                .get(QueryType.DELETE_MSG)
	                .parameters(parameters)
	                .tableName(tableName);

	        toRet.add(deleteMsgQuery);
	    }

	    FlowQueryBuilder insertQuery = FlowQueryBuilder
	            .get(QueryType.INSERT)
	            .parameters(parameters)
	            .tableName(tableName);
	    toRet.add(insertQuery);

	    Map<String, FlowForeignKeyDO> map = table.getTableReferenced().stream()
	            .collect(Collectors.toMap(
	                    k -> k.getJsonField(),
	                    fk -> fk,
	                    (oldFk, newFk) -> oldFk));

	    map.values().stream()
	            .forEach(fk -> {
	                String jsonFiled = fk.getJsonField();
	                JsonValue childObj = obj.get(jsonFiled);
	                if (childObj != null) {
	                    if (childObj.getValueType() == ValueType.OBJECT) {
	                        toRet.addAll(executeGenerateQueryFromJson(
	                                (JsonObject) childObj,
	                                flowName,
	                                fk.getIdTable(),
	                                extractionId,
	                                obj,
	                                versionName,
	                                stateReceviedApp,
	                                stateSendRegion,
	                                statusRegion,
	                                lastStateRegion
	                        ));
	                    } else if (childObj.getValueType() == ValueType.ARRAY) {
	                        JsonArray arr = childObj.asJsonArray();
	                        if (arr.size() == 0) {
	                            deleteSection(parentObject, flowName, fk.getIdTable());
	                        }

	                        arr.stream().forEach(jsObj -> {
	                            toRet.addAll(executeGenerateQueryFromJson(
	                                    (JsonObject) jsObj,
	                                    flowName,
	                                    fk.getIdTable(),
	                                    extractionId,
	                                    obj,
	                                    versionName,
	                                    stateReceviedApp,
	                                    stateSendRegion,
	                                    statusRegion,
	                                    lastStateRegion
	                            ));
	                        });
	                    }
	                }
	            });

	    return toRet;
	}
	
	/**
	 * Entry point for compare json obj with db data
	 */
	public Integer compareSecFlowData(JsonObject obj, String flowName, String versionName) throws Exception {
		FlowTableDO table = getCachedRootTable(flowName, versionName);
		return validateTableFieldData(obj.getJsonObject(table.getName()), flowName, versionName,table);
	}
	
	/**
	 * Recursive method which compare json object
	 * whith data from db.
	 * @param obj
	 * @param flowName
	 * @param versionName
	 * @param table
	 * @param parentObject
	 * @return 0 or -1 
	 */
	private Integer validateTableFieldData(JsonObject obj, String flowName, String versionName,FlowTableDO table, JsonObject... parentObject){
		Integer res = 0;
				
		String tableName = "FM_FLOW_" + flowName + "_" + table.getSection();		
		List<FlowParameter> jsonParameters = generateParameters(obj, table.getFields(), true);
		
		List<FlowParameter> pkParameters = generatePrimaryKeyParameters(obj, table.getFields(), parentObject);
				
		List<FlowTableFieldDO>flowTableFieldsDos = filterFields(flowName, table);		
						
		List<Object[]> secTableValue = getFlowSectionValues(tableName, flowTableFieldsDos,pkParameters);	
			
		Map<String,String>fieldAndValues = mergeFieldsAndValues(flowTableFieldsDos, secTableValue);		
		
		if(compareData(jsonParameters, fieldAndValues) == -1)
		return -1;
		
		Map<String, FlowForeignKeyDO> map = table.getTableReferenced().stream()
			.collect(Collectors.toMap(
			k -> k.getJsonField(),
			fk -> fk, 
			(oldFk, newFk) -> oldFk));				
		
		for(FlowForeignKeyDO fk : map.values()) {
			if(res == -1) {
				break;
			}
			String jsonFiled = fk.getJsonField();
			JsonValue childObj = obj.get(jsonFiled);
			if (childObj != null) {
				
				JsonObject[] newParentObject = null;
				if(parentObject == null || parentObject.length == 0) {
					newParentObject = new JsonObject[1];
				} else {
					newParentObject = Arrays.copyOf(parentObject, parentObject.length + 1);					
				}
				
				newParentObject[newParentObject.length - 1] = obj;
				
				if (childObj.getValueType() == ValueType.OBJECT) {
					
					res = validateTableFieldData((JsonObject) childObj, flowName, versionName, fk.getIdTable(), newParentObject);					

				} else if (childObj.getValueType() == ValueType.ARRAY) {
					String tableNames = "FM_FLOW_" + flowName + "_" + fk.getIdTable().getSection();
					List<FlowParameter> foreignkeys = new ArrayList<FlowParameter>();
					if (obj != null && parentObject !=null) { 
						
						 List<FlowForeignKeyDO> fkList = table.getTableReferenced().stream().filter(tr -> tr.getJsonField().equals(fk.getJsonField())).collect(Collectors.toList());
						// recupera le fk verso la testata
						foreignkeys.addAll(generateForeignParameters(parentObject[0], fkList));
						jsonParameters.addAll(foreignkeys);			
					}
					List<FlowTableFieldDO>flowTableFields = filterFields(flowName, fk.getIdTable());	
					List<Object[]> secTableValues = getFlowSectionValues(tableNames, flowTableFields,foreignkeys);	
					JsonArray arr = childObj.asJsonArray();		
					if(secTableValues.size() == arr.size()) {
						for(int i = 0; i < arr.size(); i++) {
							JsonValue jsObj = arr.get(i);
							if(res == -1) {
								break;
							}
							res = validateTableFieldData((JsonObject) jsObj, flowName, versionName, fk.getIdTable(), newParentObject);						
						}
					}else {
						res = -1;
					}					
				}
			}
		}
		
		return res;
	}
	/**
	 * get's values from FM_FLOW_"FLOWNAME"_"SECTION" table
	 * @param table
	 * @param fields
	 * @param foreignkeys
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object[]> getFlowSectionValues(String table, List<FlowTableFieldDO> fields, List<FlowParameter> primaryKey) {
	    String tables = fields.stream()
	            .map(FlowTableFieldDO::getName)
	            .collect(Collectors.joining(" , "));

	    StringBuilder query = new StringBuilder("SELECT " + tables + " FROM " + table);

	    if (primaryKey != null && !primaryKey.isEmpty()) {
	        query.append(" WHERE ");

	        for (int i = 0; i < primaryKey.size(); i++) {
	            FlowParameter param = primaryKey.get(i);
	            if (i > 0) {
	                query.append(" AND ");
	            }
	            query.append(param.getColumnName()).append(" = :").append(param.getColumnName());
	        }
	    }

	    Query queryExc = entityManager.createNativeQuery(query.toString());

	    if (primaryKey != null && !primaryKey.isEmpty()) {
	        for (FlowParameter param : primaryKey) {
	            Object value = param.getValue();

	            if (value instanceof Date) {
	                Date normalizedDate = normalizeDateForOracle((Date) value);
	                queryExc.setParameter(param.getColumnName(), new Timestamp(normalizedDate.getTime()));
	            } else {
	                queryExc.setParameter(param.getColumnName(), value);
	            }
	        }
	    }

	    return queryExc.getResultList();
	}

	
	/**
	 * merges fields from fm_flow_table_field with data from fm_flow_"flowName"_"section"
	 */
	private Map<String,String> mergeFieldsAndValues(List<FlowTableFieldDO> fields,List<Object[]>values){
		Map<String,String> fildsAndValues = new HashMap<String,String>();
		Object[] secValues = values.get(0);
		for(int i=0;i<fields.size();i++) {
			if(secValues[i] instanceof Timestamp) {
				Timestamp ts = (Timestamp)secValues[i];
				Date date = new Date(ts.getTime());
				SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				fildsAndValues.put(fields.get(i).getName(), sourceFormat.format(date));
			} else {
				fildsAndValues.put(fields.get(i).getName(), secValues[i] == null ? null : String.valueOf(secValues[i]));
			}
		}
		return fildsAndValues;
	}
	
	/**
	 * compares  FlowParameter name with map key
	 * if names are equal that we compare values
	 * @param jsonParameters
	 * @param dbFieldAndValues
	 * @return
	 */
	private Integer compareData(List<FlowParameter> jsonParameters, Map<String,String> dbFieldAndValues) {
		for(FlowParameter jsonParam:jsonParameters) {
			String val = dbFieldAndValues.get(jsonParam.getColumnName());
			Object paramValue = jsonParam.getValue();
			if(paramValue instanceof Date) {
				Date date = (Date)paramValue;
				
				SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				try {
					
					if((val == null || StringUtils.isEmpty(val.trim())) && date != null) {
						return -1;
					}
					
					if(val != null && !StringUtils.isEmpty(val.trim()) && date == null) {
						return -1;
					}
					
					Date valDate = sourceFormat.parse(val);
					
					if(valDate.compareTo(date) != 0) {
						return -1;
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					LogUtil.logException(logger, "", e);
//					e.printStackTrace();
				}
			} else {
				val = val == null ? "" : val;
				String jsonParamValue = jsonParam.getValue() == null ? "" : String.valueOf(jsonParam.getValue());
				
				if(!val.equals(jsonParamValue)) {		
					return -1;
				}
			}			
		}
		return 0;
	}
	
	/**
	 * Filter all fields with active == true property
	 * @param flowName
	 * @param table
	 * @return
	 */
	private List<FlowTableFieldDO> filterFields (String flowName,FlowTableDO table){
		BaseSearchInput searchInput = new BaseSearchInput();
		searchInput.setParam("flowNameEqual", flowName);
		searchInput.setParam("section", String.valueOf( table.getSection()));
		List<FlowTableDO> flowTableDos = flowTableDAO.cerca(searchInput);
		FlowTableDO tableDO = flowTableDos.get(0);
		List<FlowTableFieldDO> flowTableFieldsDos = tableDO.getFields().stream()
				.filter(f -> f.getActive())
				.collect(Collectors.toList());
		
		return flowTableFieldsDos;
	}
	
	private List<FlowParameter> generatePrimaryKeyParameters(JsonObject obj, Collection<FlowTableFieldDO> set, JsonObject[] jsonParents) {
		List<FlowParameter> list = new ArrayList<FlowParameter>();
		
		for(FlowTableFieldDO f : set) {
			if(f.getPk()) {
				if(obj.containsKey(f.getName().toLowerCase())) {
					Object objValue = extractValue(f, obj.get(f.getName().toLowerCase()));
					list.add(new FlowParameter(f.getName().toLowerCase(), objValue, f.getPosition(), f.getPk()));
				} else {
					// se il campo chiave non è presente nel json corrente, cerca nel json padre
					if(jsonParents != null && jsonParents.length > 0) {
						for(int idx = jsonParents.length - 1; idx >= 0; idx--) {
							JsonObject jsonParent = jsonParents[idx];
							if(jsonParent.containsKey(f.getName().toLowerCase())) {
								Object objValue = extractValue(f, jsonParent.get(f.getName().toLowerCase()));
								list.add(new FlowParameter(f.getName().toLowerCase(), objValue, f.getPosition(), f.getPk()));
								break;
							}
						}
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * genera i campi obbligatori presenti sulla tabella con i valori di default
	 * 
	 * @param extractionId Id dell'estrazione
	 * @param versionName
	 * @param yearRif
	 * @param monthRif
	 * @return
	 */
//	private List<FlowParameter> getMandatoryParameters(String id, String extractionId, String versionName,
//			String importType, String stateReceviedApp, String stateSendRegion) {
//		List<FlowParameter> mandatory = new ArrayList<>();
//
//		mandatory.add(new FlowParameter("extraction_id", extractionId, 0, false));
//		
//		String status = "TO_VALIDATE";
//		if(("" + stateReceviedApp).equals(StateReceviedAppEnum.CANCELLAZIONE.toString())) {
//			status = "VALID";
//		}
//		mandatory.add(new FlowParameter("status", status, 0, false));
//		
//		mandatory.add(new FlowParameter("version", versionName, 0, false));
//		mandatory.add(new FlowParameter("date_processing", new Date(), 0, false));
//		mandatory.add(new FlowParameter("import_type", importType, 0, false));
//		mandatory.add(new FlowParameter("UUID", id, 0, false));
//		
//		if (stateReceviedApp != null && stateSendRegion != null) {
//			mandatory.add(new FlowParameter("STATE_RECEVIED_APP", stateReceviedApp, 0, false));
//			mandatory.add(new FlowParameter("STATE_SEND_REGION", stateSendRegion, 0, false));
//		}
//		
//		return mandatory;
//	}
	private List<FlowParameter> getMandatoryParameters(String id, String extractionId, String versionName,
	        String importType, String stateReceviedApp, String stateSendRegion) {
	    return getMandatoryParameters(
	            id,
	            extractionId,
	            versionName,
	            importType,
	            stateReceviedApp,
	            stateSendRegion,
	            null,
	            null
	    );
	}
	private List<FlowParameter> getMandatoryParameters(String id, String extractionId, String versionName,
	        String importType, String stateReceviedApp, String stateSendRegion,
	        String statusRegion, String lastStateRegion) {

	    List<FlowParameter> mandatory = new ArrayList<>();

	    mandatory.add(new FlowParameter("extraction_id", extractionId, 0, false));

	    String status = "TO_VALIDATE";
	    if (("" + stateReceviedApp).equals(StateReceviedAppEnum.CANCELLAZIONE.toString())) {
	        status = "VALID";
	    }

	    mandatory.add(new FlowParameter("status", status, 0, false));
	    mandatory.add(new FlowParameter("version", versionName, 0, false));
	    mandatory.add(new FlowParameter("date_processing", new Date(), 0, false));
	    mandatory.add(new FlowParameter("import_type", importType, 0, false));
	    mandatory.add(new FlowParameter("UUID", id, 0, false));

	    if (stateReceviedApp != null && stateSendRegion != null) {
	        mandatory.add(new FlowParameter("STATE_RECEVIED_APP", stateReceviedApp, 0, false));
	        mandatory.add(new FlowParameter("STATE_SEND_REGION", stateSendRegion, 0, false));
	    }

	    mandatory.add(new FlowParameter("STATUS_REGION", statusRegion, 0, false));
	    mandatory.add(new FlowParameter("LAST_STATE_REGION", lastStateRegion, 0, false));

	    return mandatory;
	}
	
	/**
	 * 
	 * Genera i parametri e i relativi valori per la query usati come foreign key a
	 * partire dalla lista dei campi e dall'oggetto JSON padre
	 * 
	 * @param parentObject
	 * @param table
	 * @return
	 */
	private List<FlowParameter> generateForeignParameters(JsonObject parentObject, Collection<FlowForeignKeyDO> table) {
		return table.stream()//
				.map(fk -> {
					String parentParameter = fk.getIdFieldTableReferenced().getName().toLowerCase();
					String childParameter = fk.getIdFieldTable().getName().toLowerCase();

					JsonValue parentValue = parentObject.get(parentParameter);

					return new FlowParameter(childParameter, extractValue(fk.getIdFieldTableReferenced(), parentValue),
							fk.getIdFieldTable().getPosition(), true);
				}) //
				.collect(Collectors.toList());
	}

	/**
	 * Genera i parametri e i relativi valori per la query a partire dalla lista dei
	 * campi e dall'oggetto JSON
	 * 
	 * @param obj
	 * @param set
	 * @return
	 */
	private List<FlowParameter> generateParameters(JsonObject obj, Collection<FlowTableFieldDO> set, boolean checkIsActive) {
		return set.stream()//
				// tolgo i campi che non hanno valori sul JSON, e che non sono chiavi esterne e i campi non attivi
				.filter(f -> {
					return obj.containsKey(f.getName().toLowerCase()) && f.getFieldTable().size() == 0 && (!checkIsActive || Boolean.TRUE.equals(f.getActive()));
				})//
				.map(f -> {
					// estraggo il valore dal json
					Object objValue = extractValue(f, obj.get(f.getName().toLowerCase()));
					// restituisco il parametro
					return new FlowParameter(f.getName().toLowerCase(), objValue, f.getPosition(), f.getPk());
				})//
				.collect(Collectors.toList());
	}

	/**
	 * Estrae il valore contenuto nel JsonValue
	 * 
	 * @param f
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	private Object extractValue(FlowTableFieldDO f, JsonValue value) {

	    switch (value.getValueType()) {
	    case STRING:
	        String str = ((JsonString) value).getString();

	        if (f.getEnableCrypt()) {
	            return crypto.encryptString(str);
	        } else {
	            if ("Date".equalsIgnoreCase(f.getFieldType())) {
	                if (StringUtils.isNotEmpty((CharSequence) str)) {
	                    return parseDateValue(str);
	                } else {
	                    return null;
	                }
	            } else {
	                return str;
	            }
	        }

	    case NULL:
	        break;
	    case NUMBER:
	        return ((JsonNumber) value).intValue();
	    case FALSE:
	        return false;
	    case TRUE:
	        return true;
	    default:
	        break;
	    }

	    return null;
	}

	private Date normalizeDateForOracle(Date date) {
	    if (date == null) {
	        return null;
	    }

	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}

	private Date parseDateValue(String value) {
	    if (value == null || value.trim().isEmpty()) {
	        return null;
	    }

	    String v = value.trim();

	    List<String> patterns = Arrays.asList(
	            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
	            "yyyy-MM-dd'T'HH:mm:ss'Z'",
	            "yyyy-MM-dd'T'HH:mm:ss.SSS",
	            "yyyy-MM-dd'T'HH:mm:ss",
	            "yyyy-MM-dd HH:mm:ss",
	            "yyyy-MM-dd",
	            "dd/MM/yyyy HH:mm:ss",
	            "dd/MM/yyyy"
	    );

	    for (String pattern : patterns) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	            sdf.setLenient(false);
	            return normalizeDateForOracle(sdf.parse(v));
	        } catch (ParseException e) {
	            // provo il formato successivo
	        }
	    }

	    throw new IllegalArgumentException("Formato data non gestito: " + value);
	}

	@Override
	public boolean deleteProcedure(String procedure, String flowName, String version) throws Exception {

		BaseSearchInput poInput = new BaseSearchInput();
		poInput.setValue("flowNameEqual", flowName);
		poInput.setValue("versionNameEqual", version);
		List<FlowTableDO> tables = flowTableDAO.cerca(poInput);
		String where = "";
		String where2 = "";
		String wherefinalDelete = "";

		try {

			JSONObject jsonObject = new JSONObject(procedure);

			for (FlowTableDO table : tables) {

				for (FlowTableFieldDO field : table.getFields()) {
					if (field.getPk() == true) {
						where += "s." + field.getName() + "=" + "m." + field.getName() + " AND ";
					}
					if (field.getGroups() == true) {
						where2 += "s." + field.getName() + "= '" + jsonObject.get(field.getName()) + "' AND ";
						wherefinalDelete += field.getName() + "= '" + jsonObject.get(field.getName()) + "' AND ";
					}
				}
				where += where2;
				if (where.toUpperCase().endsWith(" AND ")) {
					where = where.substring(0, where.length() - 5);
				}
				if (wherefinalDelete.toUpperCase().endsWith(" AND ")) {
					wherefinalDelete = wherefinalDelete.substring(0, wherefinalDelete.length() - 5);
				}

				entityManager.createNativeQuery("delete from " + "fm_flow_" + flowName + "_" + table.getSection()
						+ "_message m " + "where exists(select * from " + "fm_flow_" + flowName + "_"
						+ table.getSection() + " s " + " where " + where + ")").executeUpdate();
				entityManager.createNativeQuery("delete from " + "fm_flow_" + flowName + "_" + table.getSection()
						+ " where " + wherefinalDelete).executeUpdate();

				where = "";
				where2 = "";
				wherefinalDelete = "";
			}
			
			if(flowName.equals("SDO_XL")) {
				monitorSdoXlService.delete((String)jsonObject.get("codiceazienda"), (String)jsonObject.get("codicepresidio"), (String)jsonObject.get("progressivosdo"));
			}

		}
		
		catch (JSONException err) {
			LogUtil.logException(logger, "", err);
			return false;
		}

		return true;
	}

	//cancellazione totale nel caso di tabella vuota
	private void deleteSection(JsonObject parentObject, String flowName, FlowTableDO table) {

		String tableName = "FM_FLOW_" + flowName + "_" + table.getSection();
		String tableNameMessage = "FM_FLOW_" + flowName + "_" + table.getSection()+"_MESSAGE";
		
		String delete = "delete from " + tableName + " where ";
		String deleteMessage = "delete from " + tableNameMessage + " where ";
		String where = "";

		for (FlowForeignKeyDO fk : table.getTable()) {
			where += fk.getIdFieldTable().getName() + "=" +"'"+ parentObject.getString(fk.getIdFieldTable().getName())+"'" + " AND ";

		}

		if (where.toUpperCase().endsWith(" AND ")) {
			where = where.substring(0, where.length() - 5);
		}

		entityManager.createNativeQuery(delete + where).executeUpdate();
		entityManager.createNativeQuery(deleteMessage + where).executeUpdate();

	}
	
	@Override
	public boolean isStatusRegionScarto(String flowName, Map<String, String> mapPk) {
	    String tableName = "FM_FLOW_" + flowName + "_0";

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT STATUS_REGION ");
	    sql.append("FROM ").append(tableName).append(" ");
	    sql.append("WHERE 1=1 ");

	    for (String key : mapPk.keySet()) {
	        sql.append(" AND ").append(key.toUpperCase()).append(" = :").append(key.toLowerCase());
	    }

	    Query query = entityManager.createNativeQuery(sql.toString());

	    for (Map.Entry<String, String> entry : mapPk.entrySet()) {
	        query.setParameter(entry.getKey().toLowerCase(), entry.getValue());
	    }

	    @SuppressWarnings("unchecked")
	    List<Object> result = query.getResultList();

	    if (result == null || result.isEmpty() || result.get(0) == null) {
	        return false;
	    }

	    return "SCARTO".equalsIgnoreCase(String.valueOf(result.get(0)));
	}
	
	@Override
	public StateReceviedAppEnum getStateReceviedAppFromSectionZero(String flowName, Map<String, String> mapPk) {
	    try {
	        String tableName = "FM_FLOW_" + flowName + "_0";

	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT STATE_RECEVIED_APP ");
	        sql.append("FROM ").append(tableName).append(" ");
	        sql.append("WHERE 1=1 ");

	        for (String key : mapPk.keySet()) {
	            sql.append(" AND ").append(key.toUpperCase()).append(" = :").append(key.toLowerCase());
	        }

	        Query query = entityManager.createNativeQuery(sql.toString());

	        for (Map.Entry<String, String> entry : mapPk.entrySet()) {
	            query.setParameter(entry.getKey().toLowerCase(), entry.getValue());
	        }

	        @SuppressWarnings("unchecked")
	        List<Object> result = query.getResultList();

	        if (result == null || result.isEmpty() || result.get(0) == null) {
	            return StateReceviedAppEnum.NUOVA;
	        }

	        String dbValue = String.valueOf(result.get(0)).trim();

	        if (dbValue.isEmpty()) {
	            return StateReceviedAppEnum.NUOVA;
	        }

	        try {
	            return StateReceviedAppEnum.valueOf(dbValue.toUpperCase());
	        } catch (IllegalArgumentException ex) {
	            logger.warn("Valore STATE_RECEVIED_APP non valido: {} su tabella {}", dbValue, tableName);
	            return StateReceviedAppEnum.NUOVA;
	        }

	    } catch (Exception e) {
	        logger.warn("Errore nel recupero di STATE_RECEVIED_APP da section 0 per flowName={} pk={}", flowName, mapPk, e);
	        return StateReceviedAppEnum.NUOVA;
	    }
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void createPendingError(String flowName, JSONObject jsonObject, Map<String, Object> map, String extractionId) {
	    try {
	        BaseSearchInput bsi = new BaseSearchInput();
	        bsi.setValue("nameNoLike", flowName);

	        Pair<List<FlowDO>, SearchInfo> flowResult = flowService.retrieveAllFiltered(bsi, true);
	        if (flowResult == null || flowResult.getFirst() == null || flowResult.getFirst().isEmpty()) {
	            return;
	        }

	        FlowDO flow = flowResult.getFirst().get(0);
	        String flowId = flow.getId();

	        String codiceAzienda = extractCodiceAzienda(map);
	        String message = jsonObject != null ? jsonObject.toString() : null;

	        Map<String, String> mapPk = (Map<String, String>) map.get("pk");
	        String keyMessage = buildKeyMessage(mapPk);

	        List<String> extrIds = findConsolidatedExtractionIds(flowName, map);

	        if (extrIds == null || extrIds.isEmpty()) {
	            return;
	        }

	        for (String extrId : extrIds) {
	        	List<LockedMessageDO> existingList = lockedMessageDAO.findPendingLockedMessage(
	        	        flowId,
	        	        keyMessage,
	        	        extrId,
	        	        ErrorServiceStatusEnum.PENDING.getStatus()
	        	);
	        	LockedMessageDO existing = (existingList != null && !existingList.isEmpty()) ? existingList.get(0) : null;
	        	
	        	if (existing != null) {
	        	    existing.setCreationDate(new Date());
	        	    existing.setMessage(message);
	        	    existing.setCodiceAzienda(codiceAzienda);
	        	    existing.setFlowName(flowName);
	        	    existing.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());
	        	    existing.setDateProcessed(null);
	        	    existing.setExtractionId(extractionId);

	        	    lockedMessageDAO.save(existing);
	        	} else {
	        	    LockedMessageDO locked = new LockedMessageDO();
	        	    locked.setCreationDate(new Date());
	        	    locked.setFlowId(flow);
	        	    locked.setFlowName(flowName);
	        	    locked.setExtrId(extrId);              
	        	    locked.setExtractionId(extractionId);
	        	    locked.setKeyMessage(keyMessage);
	        	    locked.setMessage(message);
	        	    locked.setCodiceAzienda(codiceAzienda);
	        	    locked.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());

	        	    lockedMessageDAO.save(locked);
	        	}
	        }

	    } catch (Exception e) {
	        LogUtil.logException(logger, "Errore durante createPendingError", e);
	    }
	}
	
	@SuppressWarnings("unchecked")
	private List<String> findConsolidatedExtractionIds(String flowName, Map<String, Object> map) {
	    try {
	        Map<String, String> mapPk = (Map<String, String>) map.get("pk");
	        if (mapPk == null || mapPk.isEmpty()) {
	            return new ArrayList<>();
	        }

	        String regTableName = "FM_FLOW_" + flowName + "_REG_0";

	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT DISTINCT t.EXTRACTION_ID ");
	        sql.append("FROM ").append(regTableName).append(" t ");
	        sql.append("JOIN FM_FLOW_EXPORTING_REQUEST fer ON fer.ID = t.EXTRACTION_ID ");
	        sql.append("WHERE fer.CONSOLIDATA = 1 ");

	        for (String key : mapPk.keySet()) {
	            sql.append(" AND t.").append(key.toUpperCase()).append(" = :").append(key.toLowerCase());
	        }

	        sql.append(" ORDER BY t.EXTRACTION_ID");

	        Query query = entityManager.createNativeQuery(sql.toString());

	        for (Map.Entry<String, String> entry : mapPk.entrySet()) {
	            query.setParameter(entry.getKey().toLowerCase(), entry.getValue());
	        }

	        List<Object> result = query.getResultList();

	        List<String> extrIds = new ArrayList<>();
	        for (Object obj : result) {
	            if (obj != null) {
	                extrIds.add(String.valueOf(obj));
	            }
	        }

	        return extrIds;
	    } catch (Exception e) {
	        LogUtil.logException(logger, "Errore nel recupero degli extractionId consolidati", e);
	        return new ArrayList<>();
	    }
	}
	
	@SuppressWarnings("unchecked")
	private String extractCodiceAzienda(Map<String, Object> map) {
		try {
			Object codiceAzienda = map.get("codiceazienda");
			if (codiceAzienda != null) {
				return String.valueOf(codiceAzienda);
			}

			Map<String, String> mapPk = (Map<String, String>) map.get("pk");
			if (mapPk != null && mapPk.get("codiceazienda") != null) {
				return mapPk.get("codiceazienda");
			}

			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ProcessSingleFlowResult processSingleFlow(String flow, String flowName, String version, String extractionId) {
	    try {

	        // flowName inviato da verticale -> recupero config per associazione con flusso configurato su flow manager
	        TabgenValueDO tabgenvalue = tabgenService.searchByFlussoVerticale(flowName, version);
	        if (tabgenvalue != null) {
	            flowName = tabgenvalue.getField3();
	            version = tabgenvalue.getField4();
	        } else {
	            // vecchia gestione
	            if ("SDOIOR".equalsIgnoreCase(flowName)) {
	                flowName = "SDO";
	                if ("20.11.00".equalsIgnoreCase(version)) {
	                    version = "1.10";
	                }
	            }
	            if ("PS_ER".equalsIgnoreCase(flowName)) {
	                flowName = "EMUR_PS";
	                if ("20.11.00".equalsIgnoreCase(version)) {
	                    version = "1.7";
	                }
	            }
	        }

	        flow = normalizeJsonPayload(flow);
	        JSONObject jsonObject = new JSONObject(flow);
	        HashMap<String, Object> map = validationFlowService.checkRecordState(jsonObject, flowName, version);
	        
	        if (extractionId == null) {
	            extractionId = UUID.randomUUID().toString();
	        }
	        
	     // pratica già inviata alla regione -> parcheggia
	        if (map != null) {
	            if (StateSendRegionEnum.INVIATA.toString().equals(map.get("region"))) {
	                createPendingError(flowName, jsonObject, map, extractionId);
	                return new ProcessSingleFlowResult(
	                        flowOperationResult.failure(new ArrayList<>(), "FM_ERR_99", extractionId),
	                        extractionId,
	                        false);
	            }
	        }

	        FlowResult result = new FlowResult(extractionId);

	        Integer compareRes = -1;

	        JsonObject jsonObj = validationFlowService.validateSingleFlow(flow, flowName, version);

	        // lettura tipotrasmissione
	        String tipoTrasmissione = "I";
	        ConfigurationDO tipoTrasmissionePath = configuration.findByKeyId("CHECK_TIPO_TRASMISSIONE_PATH_" + flowName);
	        ConfigurationDO tipoTrasmissioneAnnullamento = configuration.findByKeyId("CHECK_TIPO_TRASMISSIONE_ANNULLAMENTO_" + flowName);

	        if (tipoTrasmissionePath != null
	                && tipoTrasmissionePath.getValue() != null
	                && !tipoTrasmissionePath.getValue().isEmpty()) {
	            tipoTrasmissione = extractPathValue(tipoTrasmissionePath.getValue(), jsonObject) + "";
	        }

	        String annullamentoValue = tipoTrasmissioneAnnullamento != null ? tipoTrasmissioneAnnullamento.getValue() : null;
	        tipoTrasmissione = normalizeTransmissionType(tipoTrasmissione, annullamentoValue);

	        TransmissionStateDecision decision = resolveTransmissionState(tipoTrasmissione, map, flowName);

	        boolean stateChanged = hasTransmissionStateChanged(map, decision);

	        if (decision.isCheckVariation() && map != null) {
	            ConfigurationDO cfgObj = configuration.findByKeyId("ENABLE_CHECK_VARIATION");
	            if (cfgObj != null && "TRUE".equalsIgnoreCase(cfgObj.getValue() + "")) {
	                compareRes = compareSecFlowData(jsonObj, flowName, version);
	            }
	        }

	        /*
	         * Eseguo il batch se:
	         * - compareSecFlowData ha rilevato una variazione dati
	         * oppure
	         * - è cambiato lo stato derivato dalla tipotrasmissione
	         */
	        if (compareRes == -1 || stateChanged) {
	            List<FlowQueryBuilder> queryList = generateQueryFromJson(
	                    jsonObj,
	                    flowName,
	                    version,
	                    extractionId,
	                    decision.getStateReceviedApp().toString(),
	                    decision.getStateSendRegion().toString(),
	                    decision.getStatusRegion(),
	                    decision.getLastStateRegion()
	            );

	            batchFlowService.executeBatch(queryList);
	        }

	        if (compareRes == -1) {
	            ConfigurationDO cfgObj = configuration.findByKeyId("flowToCopy");
	            if (cfgObj != null) {
	                String cfg = cfgObj.getValue();
	                String[] cfgSplitted = cfg.split("/");

	                for (int i = 0; i < cfgSplitted.length; i++) {
	                    String value = cfgSplitted[i];
	                    int iend = value.indexOf("(");
	                    String flusso = value.substring(0, iend);
	                    String flussoCopia = value.substring(iend + 1, value.length() - 1);

	                    if (flusso != null && !"".equals(flusso) && flusso.equals(flowName)) {
	                        BaseSearchInput searchInput = new BaseSearchInput();
	                        searchInput.setValue("nameNoLike", flussoCopia);

	                        Pair<List<FlowDO>, SearchInfo> flowDO = flowService.retrieveAllFiltered(searchInput, true);
	                        FlowVersionDO flowVersion = versionService.findByFlow(flowDO.getFirst().get(0));
	                        String versionNameCopia = flowVersion.getVersion().getDescription();

	                        return processSingleFlow(flow, flussoCopia, versionNameCopia, extractionId);
	                    }
	                }
	            }
	        }

	        return new ProcessSingleFlowResult(
	                flowOperationResult.success(result),
	                extractionId,
	                true);

	    } catch (ValidationFlowException ex) {
	        LogUtil.logException(logger, "", ex);
	        return new ProcessSingleFlowResult(
	                flowOperationResult.failure(ex.getErrors(), "FM_ERR_01"),
	                null,
	                false);
	    } catch (Exception e) {
	        LogUtil.logException(logger, "", e);
	        return new ProcessSingleFlowResult(
	                flowOperationResult.failure(new ArrayList<>(List.of(e.getMessage()))),
	                null,
	                false);
	    }
	}
	
	private boolean hasTransmissionStateChanged(
	        Map<String, Object> map,
	        TransmissionStateDecision decision) {

	    if (map == null || decision == null) {
	        return false;
	    }

	    String oldStateReceviedApp = getStringValue(
	            map,
	            "app",
	            "stateReceviedApp",
	            "STATE_RECEVIED_APP"
	    );

	    String oldStateSendRegion = getStringValue(
	            map,
	            "region",
	            "stateSendRegion",
	            "STATE_SEND_REGION"
	    );

	    String oldStatusRegion = getStringValue(
	            map,
	            "statusRegion",
	            "STATUS_REGION"
	    );

	    String oldLastStateRegion = getStringValue(
	            map,
	            "lastStateRegion",
	            "LAST_STATE_REGION"
	    );

	    String newStateReceviedApp = decision.getStateReceviedApp() != null
	            ? decision.getStateReceviedApp().toString()
	            : null;

	    String newStateSendRegion = decision.getStateSendRegion() != null
	            ? decision.getStateSendRegion().toString()
	            : null;

	    String newStatusRegion = decision.getStatusRegion();

	    String newLastStateRegion = decision.getLastStateRegion();

	    return isDifferent(oldStateReceviedApp, newStateReceviedApp)
	            || isDifferent(oldStateSendRegion, newStateSendRegion)
	            || isDifferent(oldStatusRegion, newStatusRegion)
	            || isDifferent(oldLastStateRegion, newLastStateRegion);
	}
	
	private String getStringValue(Map<String, Object> map, String... keys) {
	    if (map == null || keys == null) {
	        return null;
	    }

	    for (String key : keys) {
	        if (map.containsKey(key) && map.get(key) != null) {
	            return map.get(key).toString();
	        }
	    }

	    return null;
	}

	private boolean isDifferent(String oldValue, String newValue) {
	    String oldNormalized = oldValue != null ? oldValue.trim() : null;
	    String newNormalized = newValue != null ? newValue.trim() : null;

	    if (oldNormalized == null && newNormalized == null) {
	        return false;
	    }

	    if (oldNormalized == null || newNormalized == null) {
	        return true;
	    }

	    return !oldNormalized.equalsIgnoreCase(newNormalized);
	}
	
	private String extractPathValue(String pathString, JSONObject jsonObj) {
		return extractPathValue(pathString.split("\\."), jsonObj, 0);
	}

	private String extractPathValue(String[] path, JSONObject jsonObj, Integer index) {
		Object currentProp = jsonObj.get(path[index]);
		if (path.length > index + 1) {
			return extractPathValue(path, (JSONObject) currentProp, ++index);
		} else {
			return currentProp.toString();
		}
	}
	
	@Override
	public List<LockedMessageDTO> findPendingLockedMessages(String flowId, String extrId) {
	    List<LockedMessageDO> entities =
	            lockedMessageDAO.findPendingLockedMessages(
	                    flowId,
	                    extrId,
	                    ErrorServiceStatusEnum.PENDING.getStatus()
	            );

	    List<LockedMessageDTO> result = new ArrayList<>();

	    for (LockedMessageDO entity : entities) {
	        LockedMessageDTO dto = new LockedMessageDTO();
	        dto.setId(entity.getId());
	        dto.setFlowId(entity.getFlowId() != null ? entity.getFlowId().getId() : null);
	        dto.setFlowName(entity.getFlowName());
	        dto.setKeyMessage(entity.getKeyMessage());
	        dto.setMessage(entity.getMessage());
	        dto.setExtrId(entity.getExtrId());
	        dto.setExtractionId(entity.getExtractionId());
	        result.add(dto);
	    }

	    return result;
	}
	
	@Override
	@Transactional
	public void markLockedMessageProcessed(String lockedMessageId, String extractionId) {
	    try {
	        LockedMessageDO lockedMessage = lockedMessageDAO.findById(lockedMessageId).orElse(null);

	        if (lockedMessage == null) {
	            return;
	        }

	        if (lockedMessage.getFlowId() == null
	                || lockedMessage.getFlowId().getId() == null
	                || lockedMessage.getKeyMessage() == null
	                || lockedMessage.getCreationDate() == null) {

	            lockedMessage.setStatus(ErrorServiceStatusEnum.PROCESSED.getStatus());
	            lockedMessage.setExtractionId(extractionId);
	            lockedMessage.setDateProcessed(new Date());
	            lockedMessageDAO.save(lockedMessage);
	            return;
	        }

	        Date now = new Date();

	        lockedMessageDAO.markMessagesProcessedByFlowAndKeyUpToCreationDate(
	                lockedMessage.getFlowId().getId(),
	                lockedMessage.getKeyMessage(),
	                ErrorServiceStatusEnum.PENDING.getStatus(),
	                ErrorServiceStatusEnum.PROCESSED.getStatus(),
	                extractionId,
	                now,
	                lockedMessage.getCreationDate()
	        );

	    } catch (Exception e) {
	        LogUtil.logException(logger, "Errore nell'aggiornamento dello stato PROCESSED su FM_LOCKED_MESSAGE", e);
	    }
	}
	
	private String readClobValue(Object value) {
		try {
			if (value == null) {
				return null;
			}

			if (value instanceof String) {
				return (String) value;
			}

			if (value instanceof Clob) {
				Clob clob = (Clob) value;
				return clob.getSubString(1, (int) clob.length());
			}

			return String.valueOf(value);
		} catch (Exception e) {
			LogUtil.logException(logger, "Errore nella lettura del contenuto CLOB", e);
			return null;
		}
	}
	
	private String normalizeJsonPayload(String flow) {
		if (flow == null) {
			return null;
		}

		String normalized = flow.trim();

		// rimuove eventuale BOM UTF-8
		if (!normalized.isEmpty() && normalized.charAt(0) == '\uFEFF') {
			normalized = normalized.substring(1);
		}

		return normalized;
	}
	
	private String buildKeyMessage(Map<String, String> mapPk) {
	    if (mapPk == null || mapPk.isEmpty()) {
	        return null;
	    }

	    return mapPk.entrySet().stream()
	            .sorted(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER))
	            .map(e -> e.getKey() + "=" + (e.getValue() != null ? e.getValue() : ""))
	            .collect(Collectors.joining(", "));
	}

	private String buildKeyMessage(List<KeysDTO> keys) {
	    if (keys == null || keys.isEmpty()) {
	        return null;
	    }

	    return keys.stream()
	            .sorted(Comparator.comparing(KeysDTO::getKey, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
	            .map(k -> k.getKey() + "=" + (k.getValue() != null ? k.getValue() : ""))
	            .collect(Collectors.joining(", "));
	}
	
	private static class TransmissionStateDecision {
	    private StateReceviedAppEnum stateReceviedApp;
	    private StateSendRegionEnum stateSendRegion;
	    private String statusRegion;
	    private String lastStateRegion;
	    private boolean checkVariation;

	    public StateReceviedAppEnum getStateReceviedApp() {
	        return stateReceviedApp;
	    }

	    public void setStateReceviedApp(StateReceviedAppEnum stateReceviedApp) {
	        this.stateReceviedApp = stateReceviedApp;
	    }

	    public StateSendRegionEnum getStateSendRegion() {
	        return stateSendRegion;
	    }

	    public void setStateSendRegion(StateSendRegionEnum stateSendRegion) {
	        this.stateSendRegion = stateSendRegion;
	    }

	    public String getStatusRegion() {
	        return statusRegion;
	    }

	    public void setStatusRegion(String statusRegion) {
	        this.statusRegion = statusRegion;
	    }

	    public String getLastStateRegion() {
	        return lastStateRegion;
	    }

	    public void setLastStateRegion(String lastStateRegion) {
	        this.lastStateRegion = lastStateRegion;
	    }

	    public boolean isCheckVariation() {
	        return checkVariation;
	    }

	    public void setCheckVariation(boolean checkVariation) {
	        this.checkVariation = checkVariation;
	    }
	}

	private String normalizeTransmissionType(String tipoTrasmissione, String annullamentoValue) {
	    String tipo = tipoTrasmissione != null ? tipoTrasmissione.trim().toUpperCase() : "I";

	    // A va gestito come C
	    if ("A".equals(tipo)) {
	        return "C";
	    }

	    // se da configurazione il valore di annullamento è A, va comunque trattato come C
	    if (annullamentoValue != null && annullamentoValue.trim().equalsIgnoreCase(tipo)) {
	        return "C";
	    }

	    return tipo;
	}

	private boolean isRegionalDefinitiveValue(String value) {
	    if (value == null || value.trim().isEmpty()) {
	        return false;
	    }

	    String v = value.trim().toUpperCase();
	    return "VALIDO".equals(v)
	            || "SCARTO".equals(v)
	            || "ERRORE".equals(v)
	            || "SEGNALAZIONE".equals(v);
	}

	private boolean hasRegionalDefinitiveState(HashMap<String, Object> map) {
	    if (map == null) {
	        return false;
	    }

	    String statusRegion = map.get("statusRegion") != null ? String.valueOf(map.get("statusRegion")) : null;
	    String lastStateRegion = map.get("lastStateRegion") != null ? String.valueOf(map.get("lastStateRegion")) : null;

	    return isRegionalDefinitiveValue(statusRegion) || isRegionalDefinitiveValue(lastStateRegion);
	}

	private TransmissionStateDecision resolveTransmissionState(String tipoTrasmissione, HashMap<String, Object> map, String flowName) {
	    TransmissionStateDecision decision = new TransmissionStateDecision();

	    boolean hasExistingRecord = (map != null);
	    boolean hasRegionalDefinitive = hasRegionalDefinitiveState(map);

	    String lastStateRegion = null;
	    if (map != null && map.get("lastStateRegion") != null) {
	        lastStateRegion = String.valueOf(map.get("lastStateRegion"));
	    }

	    // con import JSON non aggiorni STATUS_REGION e non tocchi LAST_STATE_REGION
	    decision.setStatusRegion(null);
	    decision.setLastStateRegion(lastStateRegion);
	    decision.setCheckVariation(false);

	    switch (tipoTrasmissione) {
	        case "P":
	            if (!hasExistingRecord) {
	                decision.setStateReceviedApp(StateReceviedAppEnum.NUOVA);
	                decision.setStateSendRegion(StateSendRegionEnum.DA_NON_INVIARE);
	                decision.setLastStateRegion(null);
	            } else if (hasRegionalDefinitive) {
	            	//VECCHIA GESTIONE - SE LA CONFIGURAZIONE FLOW_MANAGE_SCARTO_REGIONE NON ESISTE OPPURE è IMPOSTATA 1 (TRUE) SIGNIFICA CHE LA REGIONE RICEVE E ACQUISISCE ANCHE GLI SCARTI E QUINDI LE MODIFICHE DELLE PRATICHE SU SCARTI DEVONO ANDARE IN STATE_RECEVEID_APP=VARIAZIONE 
					//PERSONALIZZAZIONE AOSP BOLOGNA - SE INVECE LA CONFIGURAZIONE FLOW_MANAGE_SCARTO_REGIONE ESISTE ED è IMPOSTATA 0 (FALSE) SIGNIFICA CHE LA REGIONE NON ACQUISISCE GLI SCARTI E QUINDI LE MODIFICHE DELLE PRATICHE SU SCARTI DEVONO ANDARE IN STATE_RECEVEID_APP=NUOVA IN QUANTO MAI RICEVUTE IN REGIONE
	            	if (flowName != null && !regionAcquiresScarti(flowName)) {
	            		Map<String, String> mapPk = (Map<String, String>) map.get("pk");
				        boolean isScarto = isStatusRegionScarto(flowName, mapPk);
				        if (!isScarto) {
				        	decision.setStateReceviedApp(StateReceviedAppEnum.VARIAZIONE);
				        } else {
				        	decision.setStateReceviedApp(getStateReceviedAppFromSectionZero(flowName, mapPk));
				        }
	            	} else {
	            		decision.setStateReceviedApp(StateReceviedAppEnum.VARIAZIONE);
	            	}
	                decision.setStateSendRegion(StateSendRegionEnum.DA_NON_INVIARE);
	                decision.setCheckVariation(true);
	            } else {
	                decision.setStateReceviedApp(StateReceviedAppEnum.NUOVA);
	                decision.setStateSendRegion(StateSendRegionEnum.DA_NON_INVIARE);
	                decision.setCheckVariation(true);
	            }
	            break;

	        case "I":
	            if (!hasExistingRecord) {
	                decision.setStateReceviedApp(StateReceviedAppEnum.NUOVA);
	                decision.setStateSendRegion(StateSendRegionEnum.DA_INVIARE);
	                decision.setLastStateRegion(null);
	            } else if (hasRegionalDefinitive) {
	            	//VECCHIA GESTIONE - SE LA CONFIGURAZIONE FLOW_MANAGE_SCARTO_REGIONE NON ESISTE OPPURE è IMPOSTATA 1 (TRUE) SIGNIFICA CHE LA REGIONE RICEVE E ACQUISISCE ANCHE GLI SCARTI E QUINDI LE MODIFICHE DELLE PRATICHE SU SCARTI DEVONO ANDARE IN STATE_RECEVEID_APP=VARIAZIONE 
					//PERSONALIZZAZIONE AOSP BOLOGNA - SE INVECE LA CONFIGURAZIONE FLOW_MANAGE_SCARTO_REGIONE ESISTE ED è IMPOSTATA 0 (FALSE) SIGNIFICA CHE LA REGIONE NON ACQUISISCE GLI SCARTI E QUINDI LE MODIFICHE DELLE PRATICHE SU SCARTI DEVONO ANDARE IN STATE_RECEVEID_APP=NUOVA IN QUANTO MAI RICEVUTE IN REGIONE
	            	if (flowName != null && !regionAcquiresScarti(flowName)) {
	            		Map<String, String> mapPk = (Map<String, String>) map.get("pk");
				        boolean isScarto = isStatusRegionScarto(flowName, mapPk);
				        if (!isScarto) {
				        	decision.setStateReceviedApp(StateReceviedAppEnum.VARIAZIONE);
				        } else {
				        	decision.setStateReceviedApp(getStateReceviedAppFromSectionZero(flowName, mapPk));
				        }
	            	} else {
	            		decision.setStateReceviedApp(StateReceviedAppEnum.VARIAZIONE);
	            	}
	                decision.setStateSendRegion(StateSendRegionEnum.DA_INVIARE);
	                decision.setCheckVariation(true);
	            } else {
	                decision.setStateReceviedApp(StateReceviedAppEnum.NUOVA);
	                decision.setStateSendRegion(StateSendRegionEnum.DA_INVIARE);
	                decision.setCheckVariation(true);
	            }
	            break;

	        case "C":
	            decision.setStateReceviedApp(StateReceviedAppEnum.CANCELLAZIONE);

	            if (!hasExistingRecord) {
	                decision.setStateSendRegion(StateSendRegionEnum.DA_NON_INVIARE);
	                decision.setLastStateRegion(null);
	            } else if (hasRegionalDefinitive) {
	                decision.setStateSendRegion(StateSendRegionEnum.ACCETTATA);
	            } else {
	                decision.setStateSendRegion(StateSendRegionEnum.DA_NON_INVIARE);
	            }
	            break;

	        default:
	            throw new IllegalArgumentException("Tipo trasmissione non gestito: " + tipoTrasmissione);
	    }

	    return decision;
	}
	
	private TabgenValue loadFlowManageScartoRegione(String flowName) {
	    try {
	        TabgenValueFilter filter = new TabgenValueFilter();
	        filter.setTabgenId("FLOW_MANAGE_SCARTO_REGIONE");

	        BasePagingLoadResult<Tabgen> list = tabgenDelegate.searchValue(filter);
	        if (list == null || list.getTotalLength() <= 0 || list.getList() == null || list.getList().isEmpty()) {
	            return null;
	        }

	        Tabgen tabgen = list.getList().get(0);
	        if (tabgen == null || tabgen.getTabgenValues() == null || tabgen.getTabgenValues().isEmpty()) {
	            return null;
	        }

	        for (TabgenValue tabgenValue : tabgen.getTabgenValues()) {
	            if (flowName.equalsIgnoreCase(tabgenValue.getField1())) {
	                return tabgenValue;
	            }
	        }

	        return null;
	    } catch (Exception e) {
	        LogUtil.logException(logger, "Errore nel recupero della configurazione FLOW_MANAGE_SCARTO_REGIONE per flowName=" + flowName, e);
	        return null;
	    }
	}
	
	private boolean regionAcquiresScarti(String flowName) {
	    TabgenValue cfg = loadFlowManageScartoRegione(flowName);

	    // vecchia logica: se non esiste oppure field2=1 => la regione acquisisce anche gli scarti
	    if (cfg == null) {
	        return true;
	    }

	    String value = cfg.getField2();
	    if (value == null || value.trim().isEmpty()) {
	        return true;
	    }

	    try {
	        return Integer.parseInt(value.trim()) == 1;
	    } catch (Exception e) {
	        LogUtil.logException(logger, "Valore FLOW_MANAGE_SCARTO_REGIONE.FIELD2 non valido per flowName=" + flowName + ": " + value, e);
	        return true;
	    }
	}
	
}	
