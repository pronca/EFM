package it.eng.care.domain.flow.core.dao.querySearch;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.sql.spi.NativeQueryImplementor;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import it.eng.care.domain.flow.core.dao.FlowTableDAO;
import it.eng.care.domain.flow.core.dao.FlowTableFieldDAO;
import it.eng.care.domain.flow.core.dao.FlowViewDAO;
import it.eng.care.domain.flow.core.dao.VersionDAO;
import it.eng.care.domain.flow.core.dto.ExtractionErrorMessage;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterData;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;
import it.eng.care.domain.flow.core.service.FlowForeignKeyService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.crypt.CryptoManager;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

public class FlowViewDAOQueryByBaseSearchInput implements FlowViewDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowViewDAOQueryByBaseSearchInput.class);
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private FlowTableDAO flowTableDAO;

	@Autowired
	private FlowTableFieldDAO flowTableFieldDAO;

	@Autowired
	private VersionDAO versionDAO;

	@Autowired(required = false)
	private CryptoManager cryptoManager;
	
	@Autowired
	private FlowForeignKeyService flowForeignKeyService;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> searchForValidation(ValidationFilter request, FormFlowDTO formFlowDTO,
			List<FormFlowTableFieldDTO> groupListCfg, Integer fetchsize) {
		return (List<Map<String, Object>>) forValidation(request, formFlowDTO, groupListCfg, false, fetchsize);
	}

	@Override
	public ScrollableResults scrollForValidation(ValidationFilter request, FormFlowDTO formFlowDTO,
			List<FormFlowTableFieldDTO> groupListCfg, Integer fetchsize) {
		return (ScrollableResults) forValidation(request, formFlowDTO, groupListCfg, true, fetchsize);
	}

	private Object forValidation(ValidationFilter request, FormFlowDTO formFlowDTO,
			List<FormFlowTableFieldDTO> groupListCfg, Boolean scroll, Integer fetchsize) {
		FlowViewFilter flowViewFilter = new FlowViewFilter();
		flowViewFilter.setFlow(formFlowDTO);
		flowViewFilter.setVersionId(request.getVersionId());
		flowViewFilter.setImportId(request.getImportId());
		flowViewFilter.setField(request.getField());
//		flowViewFilter.setParam(request.getParam());
		flowViewFilter.setNotRowStatus(request.getNotRowStatus());
		flowViewFilter.setRowStatus(request.getRowStatus());
		flowViewFilter.setSelectExtractionId(true);
		flowViewFilter.setExported(request.getExported());
		flowViewFilter.setImportType(request.getImportType());

		List<Object> resultList = createQuery(flowViewFilter);
		
		String queryString = (String) resultList.get(0);

		if (groupListCfg != null && !groupListCfg.isEmpty()) {
			queryString += " order by ";
			boolean add = true;
			for (FormFlowTableFieldDTO groupField : groupListCfg) {
				if (groupField.getSection().equals(0)) {
					if (add) {
						queryString += groupField.getSectionName() + ".extraction_id , ";
					}
					add = false;
					String selectedField = (groupField.getSectionName() + "." + groupField.getName()).toLowerCase();
					queryString += selectedField + ", ";
				}
			}
			queryString = queryString.substring(0, queryString.length() - 2);
		}
		
		NativeQueryImplementor<?> query = (NativeQueryImplementor)flowViewParameter(queryString, flowViewFilter);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		if (scroll) {
			return query.setCacheable(false).setCacheMode(CacheMode.IGNORE)
					.setFetchSize(fetchsize != null ? fetchsize : 500).setReadOnly(true)
					.scroll(ScrollMode.FORWARD_ONLY);
		} else {
			return query.list();
		}
	}

	private SessionImpl getSession() {
		return ((SessionImpl) entityManager.getDelegate());
	}

	public List<Object> search(FlowViewFilter flowViewFilter) {

		flowViewFilter.setSelectExtractionId((false));
		List<Object> results = new ArrayList<Object>();
		HashMap<String,HashMap<String,String>> fieldMap = new HashMap<String,HashMap<String,String>>();

		Query query = null;
		Query queryPag2 = null;
		HashMap<String, List<Object>> hashMap = new HashMap<>();

		if (flowViewFilter.isPaginated()) {
			List<Object> resultListPag = createQueryPag(flowViewFilter);
			
			//APPLICO PARAMETRI E CONTROLLO LA CIFRATURA
			query = flowViewParameter((String)resultListPag.get(0), flowViewFilter);
			
			List<Object[]> resultPag = query.getResultList();
			
			if (flowViewFilter.isCount()) {
				 Object resultCount = resultPag.get(0);
				try {
					hashMap.put(((BigDecimal) resultCount).toString(), null);
				} catch (Exception e) {
					hashMap.put(((BigInteger) resultCount).toString(), null);
				}
				results.add(hashMap);
				return results;
			}

			if (resultPag == null || resultPag.isEmpty()) {
				for (int indexSection = 0; indexSection < flowViewFilter.getFlow().getFlowTableList()
						.size(); indexSection++) {
					FormFlowTableDTO flowField = flowViewFilter.getFlow().getFlowTableList().get(indexSection);
					List<FormFlowTableFieldDTO> list = flowField.getFlowTableFieldList();
					list = list.stream().filter(l -> Boolean.TRUE.equals(l.getActive())).collect(Collectors.toList());
					LinkedHashMap<String,String> sectionFieldMap = new LinkedHashMap<String,String>();
					hashMap.put(flowField.getName(),
							new ArrayList<Object>());
					for(int i=0; i<list.size(); i++) {
						sectionFieldMap.put(list.get(i).getName(), list.get(i).getDescriptionsm());
					}
					fieldMap.put(flowField.getName(), sectionFieldMap);
				}
				results.add(hashMap);
				results.add(fieldMap);
				return results;
			}

			List<Object> resultListPag2 = createQueryPag2(flowViewFilter, resultPag);
			
			queryPag2 = entityManager.createNativeQuery((String) resultListPag2.get(0));
			List<Object[]> resultPag2 = queryPag2.getResultList();

			Map<Integer, List<Integer>> pkMap = (Map<Integer, List<Integer>>) resultListPag2.get(1);
			
			int numeroCampiElaborati = 0;
			int campiSezione = 0;
			for (int indexSection = 0; indexSection < flowViewFilter.getFlow().getFlowTableList()
					.size(); indexSection++) {
				LinkedHashMap<String,String> sectionFieldMap = new LinkedHashMap<String,String>();
				List<Object> valoriSezione = new ArrayList<Object>();
				List<FormFlowTableFieldDTO> list = flowViewFilter.getFlow().getFlowTableList().get(indexSection).getFlowTableFieldList();
				list = list.stream().filter(l -> Boolean.TRUE.equals(l.getActive())).collect(Collectors.toList());
				if (indexSection == 0) {
					campiSezione = list.size() + 8;
				} else {
					campiSezione = list.size() + 2;
				}
				Map<String, String> elaboratedPkMap = new HashMap<String, String>();
				for (int indexRecord = 0; indexRecord < resultPag2.size(); indexRecord++) {
					Object[] record = resultPag2.get(indexRecord);
					ArrayList<Object> recordSezione = new ArrayList<Object>();
					boolean recordIsNull = true;
					String pk = "";
					for (int indexField = numeroCampiElaborati; indexField < campiSezione
							+ numeroCampiElaborati; indexField++) {
						
						FormFlowTableFieldDTO flowField = null;
						if((indexSection == 0 && indexField<numeroCampiElaborati+campiSezione - 8) || (indexSection >0 && indexField<numeroCampiElaborati+campiSezione - 2)) {
							flowField = list.get(indexField - numeroCampiElaborati);
							if(flowField.getActive() == null || flowField.getActive()) {
								sectionFieldMap.put(flowField.getName(), flowField.getDescriptionsm());
							}
						}
						if (record.length>indexField) {
							Object valore = record[indexField];
							if (flowField != null && flowField.isCrypto()) {
								if("Date".equals(flowField.getFieldType())) {
									try {
										valore = cryptoManager.decryptDate((String) valore);
									} catch (ParseException e) {
										LogUtil.logException(LOGGER, "", e);
//										e.printStackTrace();
									}
								}else {
									/*if(flowViewFilter.getSortField()!=null && flowField.getName().equals(flowViewFilter.getSortField())) {
										//cambiare oggetto di comunicazione con frontend per ritornare errore "non è possibile ordinare per campo criptato = dato sensibile"
									}*/
									valore = cryptoManager.decryptString((String) valore);
								}
							}
							recordSezione.add(valore);
							if (valore != null) {
								recordIsNull = false;
								if (pkMap.get(indexSection).contains(indexField)) {
									pk += valore.toString();
								}
							}
						}
					}
					if (!recordIsNull) {
						if (!elaboratedPkMap.containsKey(pk)) {
							valoriSezione.add(recordSezione.toArray());
							if (!pk.isEmpty()) {
								elaboratedPkMap.put(pk, "");
							}
						}
					}
				}
				numeroCampiElaborati += campiSezione;
				hashMap.put(flowViewFilter.getFlow().getFlowTableList().get(indexSection).getName(), valoriSezione);
				fieldMap.put(flowViewFilter.getFlow().getFlowTableList().get(indexSection).getName(), sectionFieldMap);
			}

		} else {
			List<Object> resultList = createQuery(flowViewFilter);
			
			query = flowViewParameter((String)resultList.get(0), flowViewFilter);
//			query.setFirstResult(flowViewFilter.getFirstResult());
//			query.setMaxResults(flowViewFilter.getMaxResult());
			
			int max;
			int first;

		    first = flowViewFilter.getFirstResult();
		    max   = flowViewFilter.getMaxResult();

			query.setFirstResult(Math.max(0, first));

			if (max > 0) {
			    query.setMaxResults(max);
			}
			
			List<Object[]> result = query.getResultList();
						
			Map<Integer, List<Integer>> pkMap = (Map<Integer, List<Integer>>) resultList.get(1);

			int numeroCampiElaborati = 0;
			for (int indexSection = 0; indexSection < flowViewFilter.getFlow().getFlowTableList()
					.size(); indexSection++) {
				List<Object> valoriSezione = new ArrayList<Object>();
				HashMap<String,String> sectionFieldMap = new HashMap<String,String>();
				FormFlowTableDTO flowTable = flowViewFilter.getFlow().getFlowTableList().get(indexSection);
				
				List<FormFlowTableFieldDTO> list = flowTable.getFlowTableFieldList();
				list = list.stream().filter(l -> Boolean.TRUE.equals(l.getActive())).collect(Collectors.toList());

				int campiSezione = list.size();
				Map<String, String> elaboratedPkMap = new HashMap<String, String>();
				for (int indexRecord = 0; indexRecord < result.size(); indexRecord++) {

					Object[] record = result.get(indexRecord);
					ArrayList<Object> recordSezione = new ArrayList<Object>();
					boolean recordIsNull = true;
					String pk = "";
					for (int indexField = numeroCampiElaborati; indexField < campiSezione
							+ numeroCampiElaborati; indexField++) {

						FormFlowTableFieldDTO flowField = null;

						if((indexSection == 0 && indexField<numeroCampiElaborati+campiSezione-7) || (indexSection >0 && indexField<numeroCampiElaborati+campiSezione-1)) {
							flowField = list.get(indexField - numeroCampiElaborati);
							if(flowField.getActive() == null || flowField.getActive()) {
								sectionFieldMap.put(flowField.getName(), flowField.getDescriptionsm());
							}
						}

						Object valore = record[indexField];
						if (flowField != null && flowField.isCrypto()) {
							valore = cryptoManager.decryptString((String) valore);
						}

						recordSezione.add(valore);
						if (valore != null) {
							recordIsNull = false;
							if (pkMap.get(indexSection).contains(indexField)) {
								pk += valore.toString();
							}
						}
					}
					if (!recordIsNull) {
						if (!elaboratedPkMap.containsKey(pk)) {
							valoriSezione.add(recordSezione.toArray());
							elaboratedPkMap.put(pk, "");
						}
					}
				}
				numeroCampiElaborati += campiSezione;
				hashMap.put(flowViewFilter.getFlow().getFlowTableList().get(indexSection).getName(), valoriSezione);
				fieldMap.put(flowViewFilter.getFlow().getFlowTableList().get(indexSection).getName(), sectionFieldMap);
			}
		}
		
		results.add(hashMap);
		results.add(fieldMap);
		
		return results;
	}

	public List<Object> createQueryPag2(FlowViewFilter flowViewFilter, List<Object[]> resultPag) {

		String importId = flowViewFilter.getImportId();
		String tipoImportazione = flowViewFilter.getTipoImportazione();
		if (tipoImportazione == null) {
			flowViewFilter.setTipoImportazione("Tutte");
		}
		String codicePresidio = flowViewFilter.getCodicePresidio();
		if (codicePresidio == null) {
			flowViewFilter.setCodicePresidio("Tutte");
		}
		String codiceAzienda = flowViewFilter.getCodiceAzienda();
		if (codiceAzienda == null) {
			flowViewFilter.setCodiceAzienda("Tutte");
		}
		
		List<Object> result = new ArrayList<Object>();
		String outerQuery = "";
		String tableName = "";
		String outerOrderBySQL = "ORDER BY ";
		String outerSelectSQL = "SELECT DISTINCT ";
		String outerSelectSectionSQL = "";
		String outerFromSQL = "FROM ";
		String outerWhereSQL = "WHERE ";
		String concatSQL = "";
		String leftJoinSQL = "LEFT JOIN";
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		
		Map<Integer, List<Integer>> pkMap = new HashMap<Integer, List<Integer>>();
		int count = 0;
		int count2 = 0;
		int countSys = 0;
		int tecField = 1;
		
		if(flowViewFilter.getSortDir()!=null && !"".equals(flowViewFilter.getSortDir())) {
			tecField++;
		}

		List<String> fieldSez1 = new ArrayList<String>();

		FormFlowDTO flow = flowViewFilter.getFlow();

		tableName = "FM_FLOW_" + flow.getName() + "_";
		String tableRootName = tableName;

		for (FormFlowTableDTO table : flow.getFlowTableList()) {
			List<Integer> pkList = new ArrayList<Integer>();
			outerSelectSectionSQL="";
			for (FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
				if (table.getSection() == 0 && field.isPk()) {
					fieldSez1.add(field.getName());
					count++;
				}
				if (field.isPk()) {
					pkList.add(count2);
				}
				count2++;
				
				if (!Boolean.TRUE.equals(field.getActive())) continue;
				String name = field.getName();
				if (name.length() > 10) {
					name = name.substring(0, 10);
				}
				outerSelectSectionSQL += tableName + table.getSection() + "." + field.getName() + " S" + table.getSection()
						+ "_" + field.getPosition() + "_" + name + " , ";
				
			}

			if (table.getSection() > 0) {
				count2 = count2 + 1;
			}

			if (table.getSection() == 0) {
				outerSelectSQL += outerSelectSectionSQL;
				tableRootName += table.getSection();
				count2 = count2 + 8;

				if (!resultPag.isEmpty()) {

					for (int i = 0; i < resultPag.size(); i++) {
						try {
							if (resultPag.get(i).length-tecField > count) {
								Object[] copy = Arrays.copyOf(resultPag.get(i), resultPag.get(i).length - tecField -1);
								resultPag.set(i, Arrays.copyOf(copy, copy.length));
							}else {
								Object[] copy = Arrays.copyOf(resultPag.get(i), resultPag.get(i).length - tecField);
								resultPag.set(i, Arrays.copyOf(copy, copy.length));
							}
						} catch (Exception ignore) {}
					}
					
					if(flowViewFilter.getSortDir()!=null && !"".equals(flowViewFilter.getSortDir())) {
						outerOrderBySQL += tableName + flowViewFilter.getSortSez() + "." + flowViewFilter.getSortField() + " " + flowViewFilter.getSortDir() + ", ";
					}
					outerOrderBySQL += tableName + "0.date_processing desc, ";
					
					if (!fieldSez1.isEmpty()) {
						if (fieldSez1.size() > 1) {
							for (int c = 0; c < fieldSez1.size(); c++) {
								if (outerSelectSectionSQL.toLowerCase().contains(fieldSez1.get(c).toLowerCase())) {
									outerOrderBySQL += tableName + "0." + fieldSez1.get(c) + " , ";
								}
							}
						} else {
							if (outerSelectSectionSQL.toLowerCase().contains(fieldSez1.get(0).toLowerCase())) {
								outerOrderBySQL += tableName + "0." + fieldSez1.get(0) + " , ";
							}
						}
					}
					
					for (int i = 0; i < resultPag.size(); i++) {
					    Object[] row = resultPag.get(i);
					    List<String> fieldsToConcat = new ArrayList<String>();
					    StringBuilder valueToCompare = new StringBuilder();

					    for (int c = 0; c < fieldSez1.size() && c < row.length; c++) {
					        Object val = row[c];
					        if (val != null) {
					            String columnName = tableName + "0." + fieldSez1.get(c);
					            fieldsToConcat.add(buildConcatColumnExpression(columnName, val));
					            valueToCompare.append(formatConcatValue(val));
					        }
					    }

					    if (!fieldsToConcat.isEmpty()) {
					        concatSQL = "";
					        if (fieldsToConcat.size() > 1) {
					            for (int c = 0; c < fieldsToConcat.size(); c++) {
					                if (c == 0) {
					                    concatSQL += "CONCAT( " + fieldsToConcat.get(c) + ", ";
					                } else if (c == 1) {
					                    concatSQL += fieldsToConcat.get(c) + ")";
					                } else {
					                    concatSQL += ", " + fieldsToConcat.get(c) + ")";
					                    concatSQL = "CONCAT ( " + concatSQL;
					                }
					            }
					        } else {
					            concatSQL = fieldsToConcat.get(0);
					        }

					        outerWhereSQL += concatSQL + " = '" + valueToCompare.toString().replace("'", "''") + "' OR ";
					    }
					}
				}
				outerSelectSQL += tableName + table.getSection() + ".DATE_PROCESSING" + " DATE_PROCESSING_"
						+ table.getSection() + "_" + countSys + " , ";
				outerSelectSQL += tableName + table.getSection() + ".STATUS_GROUP" + " STATUS_GROUP_"
						+ table.getSection() + "_" + countSys + " , ";
				outerSelectSQL += tableName + table.getSection() + ".STATE_RECEVIED_APP" + " STATE_RECEVIED_APP_"
						+ table.getSection() + "_" + countSys + " , ";
				outerSelectSQL += tableName + table.getSection() + ".STATE_SEND_REGION" + " STATE_SEND_REGION_"
						+ table.getSection() + "_" + countSys + " , ";
				outerSelectSQL += tableName + table.getSection() + ".STATUS_REGION" + " STATUS_REGION"
						+ table.getSection() + "_" + countSys + " , ";
				outerSelectSQL += tableName + table.getSection() + ".LAST_STATE_REGION" + " LAST_STATE_REGION"
						+ table.getSection() + "_" + countSys + " , ";
				countSys++;
			} else {
				if (table.getListFk().size() > 0) {
					outerSelectSQL += outerSelectSectionSQL;
				}
			}

			pkMap.put(table.getSection(), pkList);

			if (table.getSection() == 0) {
				outerFromSQL += tableName + table.getSection() + " ";
			} else {
				if (table.getListFk().size() > 0) {
					outerFromSQL += "LEFT JOIN " + tableName + table.getSection() + " ON (";
					for (FormFlowTableLinkDTO formFlowTableLinkDTO : table.getListFk()) {
						BaseSearchInput input = new BaseSearchInput();
		                input.setParam("idTable", formFlowTableLinkDTO.getIdTable());
		                Pair<List<FlowForeignKeyDO>, SearchInfo> flowReferenced = flowForeignKeyService
		                        .retrieveAllFiltered(input);
		                List<FlowForeignKeyDO> flowForeignKeyDOList = flowReferenced.getFirst();
		                for(FlowForeignKeyDO flowForeignKeyDO: flowForeignKeyDOList) {
							String sectionTable = flowForeignKeyDO.getIdTable().getSection().toString();
							String nameFieldTable = flowForeignKeyDO.getIdFieldTable().getName();
							String sectionTableReferenced = flowForeignKeyDO.getIdTableReferenced().getSection().toString();
							String nameFieldTableReferenced = flowForeignKeyDO.getIdFieldTableReferenced().getName();
							outerFromSQL += tableName + sectionTable + "." + nameFieldTable + " = " + tableName
									+ sectionTableReferenced + "." + nameFieldTableReferenced + " AND ";
		                }
		                if (importId != null) {
		                	outerFromSQL += tableRootName + ".EXTRACTION_ID = " + tableName + table.getSection() + ".EXTRACTION_ID AND ";
						}
		                break;
					}
				}
			}
			
			if (outerFromSQL.endsWith(" AND ")) {
				outerFromSQL = outerFromSQL.substring(0, outerFromSQL.length() - 4);
				outerFromSQL += ") ";
			}
			
			if (table.getSection() == 0 || (table.getSection() > 0 && table.getListFk().size() > 0)) {
				outerSelectSQL += tableName + table.getSection() + ".STATUS" + " STATUS_" + table.getSection() + "_"
						+ countSys + " , ";
				outerSelectSQL += tableName + table.getSection() + ".IMPORT_TYPE" + " IMPORT_TYPE_" + table.getSection() + "_"
						+ countSys + " , ";
			}
		}

		if (outerWhereSQL.endsWith(" OR ")) {
			outerWhereSQL = outerWhereSQL.substring(0, outerWhereSQL.length() - 3);
		}

		if (importId != null) {
			if (outerWhereSQL.equals("WHERE ")) {
				outerWhereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".EXTRACTION_ID = '"
						+ importId + "'";
			} else {
				outerWhereSQL = outerWhereSQL.substring(0, 6) + "(" + outerWhereSQL.substring(6, outerWhereSQL.length());
				outerWhereSQL += ") AND " + tableName + flow.getFlowTableList().get(0).getSection() + ".EXTRACTION_ID = '"
						+ importId + "'";
			}
		}
		
		if (!aziende.isEmpty()) {
			outerWhereSQL += " AND " + tableName + flow.getFlowTableList().get(0).getSection() + ".CODICEAZIENDA IN ('" + String.join("','",aziende) + "')";
		}
		
		//AGGIUNGO FILTRO IMPORT_TYPE
		if (tipoImportazione != null && !tipoImportazione.equals("Tutte")) {
			outerWhereSQL += " AND " + tableName + flow.getFlowTableList().get(0).getSection() + ".IMPORT_TYPE = '" + tipoImportazione + "'";
		}
		//AGGIUNGO FILTRO CODICE PRESIDIO
		if (codicePresidio != null && !codicePresidio.equals("Tutte")) {
			outerWhereSQL += " AND " + tableName + flow.getFlowTableList().get(0).getSection() + ".CODICEPRESIDIO = '" + codicePresidio + "'";
		}
		//AGGIUNGO FILTRO CODICEAZIENDA
		if (codiceAzienda != null && !codiceAzienda.equals("Tutte")) {
			outerWhereSQL += " AND " + tableName + flow.getFlowTableList().get(0).getSection() + ".CODICEAZIENDA = '" + codiceAzienda + "'";
		}
		
		if (outerSelectSQL.endsWith(" , ")) {
			outerSelectSQL = outerSelectSQL.substring(0, outerSelectSQL.length() - 2);
			outerOrderBySQL = outerOrderBySQL.substring(0, outerOrderBySQL.length() - 2);
		}
		
		if ("WHERE".equalsIgnoreCase(outerWhereSQL.trim())) {
			outerWhereSQL = "";
		}
		
		outerQuery = outerSelectSQL + outerFromSQL + outerWhereSQL + outerOrderBySQL;

		result.add(outerQuery);
		result.add(pkMap);

		return result;
	}
	
	private String buildConcatColumnExpression(String columnName, Object val) {
	    if (val instanceof java.sql.Timestamp) {
	        return "TO_CHAR(" + columnName + ", 'DD/MM/YYYY HH24:MI:SS')";
	    }
	    if (val instanceof java.sql.Date) {
	        return "TO_CHAR(" + columnName + ", 'DD/MM/YYYY')";
	    }
	    return columnName;
	}

	private String formatConcatValue(Object val) {
	    if (val instanceof java.sql.Timestamp) {
	        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
	                .format((java.sql.Timestamp) val);
	    }
	    if (val instanceof java.sql.Date) {
	        return new java.text.SimpleDateFormat("dd/MM/yyyy")
	                .format((java.sql.Date) val);
	    }
	    return String.valueOf(val);
	}

	public List<Object> createQueryPag(FlowViewFilter flowViewFilter) {

		List<Object> result = new ArrayList<Object>();
		String innerQuery = "";
		String tableName = "";
		String selectSQL = "SELECT DISTINCT ";
		String fromSQL = "FROM ";
		String whereSQL = "WHERE ";
		String orderBySQL = "";

		FormFlowDTO flow = flowViewFilter.getFlow();
		FlowViewFilterField[] fieldd = flowViewFilter.getField();
		FlowViewFilterData[] fieldData = flowViewFilter.getFieldData();
//		List<Date> dateIn = flowViewFilter.getDateIn();
//		List<Date> dateOut = flowViewFilter.getDateOut();
		String importId = flowViewFilter.getImportId();
		String rowStatus = flowViewFilter.getRowStatus();
		boolean scartiRegione = flowViewFilter.isScartiRegione();
		String tipoImportazione = flowViewFilter.getTipoImportazione();
		if (tipoImportazione == null) {
			flowViewFilter.setTipoImportazione("Tutte");
		}
		String codicePresidio = flowViewFilter.getCodicePresidio();
		if (codicePresidio == null) {
			flowViewFilter.setCodicePresidio("Tutte");
		}
		String codiceAzienda = flowViewFilter.getCodiceAzienda();
		if (codiceAzienda == null) {
			flowViewFilter.setCodiceAzienda("Tutte");
		}
		
		tableName = "FM_FLOW_" + flow.getName() + "_";
		String tableRootName = tableName;
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();

		for (FormFlowTableDTO table : flow.getFlowTableList()) {

			if (flowViewFilter.getSelectExtractionId()) {
				selectSQL += tableName + "0." + "EXTRACTION_ID extractionId , ";
			}
			for (FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
				if (table.getSection() == 0 && field.isPk()) {
					selectSQL += tableName + table.getSection() + "." + field.getName() + " " + field.getName() + " , ";
					orderBySQL += tableName + table.getSection() + "." + field.getName() + " , ";
				}
			}

			if (table.getSection() == 0) {
				selectSQL += tableName + table.getSection() + ".date_processing date_processing, ";
				orderBySQL = "ORDER BY " + tableName + table.getSection() + ".date_processing desc," + orderBySQL;
				if(flowViewFilter.getSortDir()!=null && !"".equals(flowViewFilter.getSortDir()) && flowViewFilter.getSortSez().equals(String.valueOf(table.getSection()))) {
					orderBySQL = "ORDER BY " + tableName + table.getSection() + "." + flowViewFilter.getSortField() + " " 
							+ flowViewFilter.getSortDir() + " , " + orderBySQL.substring(9);
				}
				fromSQL += tableName + table.getSection() + " ";
				tableRootName += table.getSection();
			} else {
				if(flowViewFilter.getSortDir()!=null && !"".equals(flowViewFilter.getSortDir()) && flowViewFilter.getSortSez().equals(String.valueOf(table.getSection()))) {
					orderBySQL = "ORDER BY " + tableName + table.getSection() + "." + flowViewFilter.getSortField() + " " 
							+ flowViewFilter.getSortDir() + " , " + orderBySQL.substring(9);
				}
				if (table.getListFk().size() > 0) {
					fromSQL += "LEFT JOIN " + tableName + table.getSection() + " ON (";
					for (FormFlowTableLinkDTO formFlowTableLinkDTO : table.getListFk()) {
						BaseSearchInput input = new BaseSearchInput();
		                input.setParam("idTable", formFlowTableLinkDTO.getIdTable());
		                Pair<List<FlowForeignKeyDO>, SearchInfo> flowReferenced = flowForeignKeyService
		                        .retrieveAllFiltered(input);
		                List<FlowForeignKeyDO> flowForeignKeyDOList = flowReferenced.getFirst();
		                for(FlowForeignKeyDO flowForeignKeyDO: flowForeignKeyDOList) {
							String sectionTable = flowForeignKeyDO.getIdTable().getSection().toString();
							String nameFieldTable = flowForeignKeyDO.getIdFieldTable().getName();
							String sectionTableReferenced = flowForeignKeyDO.getIdTableReferenced().getSection().toString();
							String nameFieldTableReferenced = flowForeignKeyDO.getIdFieldTableReferenced().getName();
							fromSQL += tableName + sectionTable + "." + nameFieldTable + " = " + tableName
									+ sectionTableReferenced + "." + nameFieldTableReferenced + " AND ";
		                }
		                if (importId != null) {
		                	fromSQL += tableRootName + ".EXTRACTION_ID = " + tableName + table.getSection() + ".EXTRACTION_ID AND ";
						}
		                break;
					}
				}
			}
			
			if (fromSQL.endsWith(" AND ")) {
				fromSQL = fromSQL.substring(0, fromSQL.length() - 4);
				fromSQL += ") ";
			}

			if (fieldd != null && fieldd.length!=0) {
				for (int p = 0; p < fieldd.length; p++) {
					if (fieldd[p].getName() != null) {
						if (table.getSection() == fieldd[p].getSection()) {
							whereSQL += "upper(REPLACE (" + tableName + fieldd[p].getSection() + "."
									+ fieldd[p].getName() + ", ' ', '')) = upper(?) AND ";
						}
					}
				}
			}
			if (fieldData != null && fieldData.length!=0) {
				for (int p = 0; p < fieldData.length; p++) {
					if (table.getSection() == fieldData[p].getSection()) {
						if (fieldData[p].getDateIn() != null && fieldData[p].getDateOut() != null && fieldData[p].getPosition() == 2) {
							whereSQL += tableName + fieldData[p].getSection() + "." + fieldData[p].getName()
									+ " between ? and ? AND ";
						} else if (fieldData[p].getDateIn() != null && fieldData[p].getPosition() == 0) {
							whereSQL += tableName + fieldData[p].getSection() + "." + fieldData[p].getName()
									+ " >= ? AND ";
						} else if (fieldData[p].getDateOut() != null && fieldData[p].getPosition() == 1) {
							whereSQL += tableName + fieldData[p].getSection() + "." + fieldData[p].getName()
									+ " <= ? AND ";
						} else {
							whereSQL += tableName + fieldData[p].getSection() + "." + fieldData[p].getName()
									+ " IS NULL AND ";
						}
					}
				}
			}

		}
		
		if(flowViewFilter.getSortDir()!=null && !"".equals(flowViewFilter.getSortDir())) {
			selectSQL += tableName + flowViewFilter.getSortSez() + "." + flowViewFilter.getSortField() + " " + flowViewFilter.getSortField() + "Sort ";
		}
		
		if (selectSQL.endsWith(", ")) {
			selectSQL = selectSQL.substring(0, selectSQL.length() - 2);
			selectSQL += " ";
		}

		if (importId != null) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".EXTRACTION_ID = '" + importId + "' AND ";
		}

		if (rowStatus != null && !rowStatus.isEmpty()) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".STATUS = '" + rowStatus + "' AND ";
		}
		
		if(scartiRegione) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".STATUS_REGION <> 'VALIDO' AND ";
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".STATUS_REGION IS NOT NULL AND ";
		}

		if (orderBySQL.endsWith(" , ")) {
			orderBySQL = orderBySQL.substring(0, orderBySQL.length() - 2);
		} else if (orderBySQL.endsWith(" ,") || orderBySQL.endsWith(",")) {
			orderBySQL = orderBySQL.substring(0, orderBySQL.length() - 1);
		}

		if (flowViewFilter.getDrg() != null && !"".equals(flowViewFilter.getDrg())) {
			whereSQL += tableName + flow.getFlowTableList().get(1).getSection() + ".DRG_REGIONALE IS NOT NULL AND ";
		}
		
		if (!aziende.isEmpty()) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".CODICEAZIENDA IN ('" + String.join("','",aziende) + "') AND ";
		}
		
		//AGGIUNGO FILTRO IMPORT_TYPE
		if (tipoImportazione != null && !tipoImportazione.equals("Tutte")) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".IMPORT_TYPE = '" + tipoImportazione + "' AND ";
		}
		//AGGIUNGO FILTRO CODICE PRESIDIO
		if (codicePresidio != null && !codicePresidio.equals("Tutte")) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".CODICEPRESIDIO = '" + codicePresidio + "' AND ";
		}
		//AGGIUNGO FILTRO CODICEAZIENDA
		if (codiceAzienda != null && !codiceAzienda.equals("Tutte")) {
			whereSQL += tableName + flow.getFlowTableList().get(0).getSection() + ".CODICEAZIENDA = '" + codiceAzienda + "' AND ";
		}
				
		if (whereSQL.endsWith(" AND ")) {
			whereSQL = whereSQL.substring(0, whereSQL.length() - 4);
		}

		if (whereSQL.endsWith("WHERE ")) {
			whereSQL = whereSQL.substring(0, whereSQL.length() - 6);
		}

		innerQuery = selectSQL + fromSQL + whereSQL + orderBySQL;

		if (flowViewFilter.isCount()) {
			innerQuery = "SELECT COUNT(1) FROM ( " + innerQuery + " ) CN";
		}

		result.add(innerQuery);

		return result;
	}

	public List<Object> createQuery(FlowViewFilter flowViewFilter) {

		FormFlowDTO formFlowDTO = flowViewFilter.getFlow();
		FlowViewFilterField[] fieldd = flowViewFilter.getField();
		FlowViewFilterData[] fieldData = flowViewFilter.getFieldData();
//		String[] param = flowViewFilter.getParam();
//		List<Date> dateIn = flowViewFilter.getDateIn();
//		List<Date> dateOut = flowViewFilter.getDateOut();
		String importId = flowViewFilter.getImportId();
		String rowStatus = flowViewFilter.getRowStatus();
		String notRowStatus = flowViewFilter.getNotRowStatus();
		String exported = flowViewFilter.getExported();
		ImportTypeEnum importType = flowViewFilter.getImportType();
		String tipoImportazione = flowViewFilter.getTipoImportazione();
		if (tipoImportazione == null) {
			flowViewFilter.setTipoImportazione("Tutte");
		}
		String codicePresidio = flowViewFilter.getCodicePresidio();
		if (codicePresidio == null) {
			flowViewFilter.setCodicePresidio("Tutte");
		}
		String codiceAzienda = flowViewFilter.getCodiceAzienda();
		if (codiceAzienda == null) {
			flowViewFilter.setCodiceAzienda("Tutte");
		}
		
		String version = null;
		if (flowViewFilter.getVersionId() != null) {
			Optional<VersionDO> v = versionDAO.findById(flowViewFilter.getVersionId());
			if (v != null) {
				version = v.get().getVersion();
			}
		}

		String selectSQL = "SELECT ";

		String fromSQl = " FROM ";
		String whereSQL = "WHERE ";
		String campiSelect = "";
		String tableRootName = "";
		int count2 = 0;
		int countA = 0;
		int countB = 0;
		Map<Integer, List<Integer>> pkMap = new HashMap<Integer, List<Integer>>();
		List<String> dateList = new ArrayList<String>();
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		
		try {
			formFlowDTO.getFlowTableList().sort(Comparator.comparing(FormFlowTableDTO::getSection));

			for (FormFlowTableDTO formFlowTableDTO : formFlowDTO.getFlowTableList()) { // PER OGNI SEZIONE

				String tableName = "FM_FLOW_" + formFlowDTO.getName() + "_" + formFlowTableDTO.getSection(); // NOME DI
																												// OGNI
																												// SEZIONE
				String tableRoot = "FM_FLOW_" + formFlowDTO.getName() + "_"; // PREFISSO DEL NOME DELLE SEZIONI
				if (formFlowTableDTO.getSection() == 0) {
					tableRootName = "FM_FLOW_" + formFlowDTO.getName() + "_" + formFlowTableDTO.getSection();

					if (flowViewFilter.getSelectExtractionId()) {
						selectSQL += tableRootName + ".EXTRACTION_ID \"extractionId\" , ";
					}

				}

				List<Integer> pkList = new ArrayList<Integer>();
				for (FormFlowTableFieldDTO field : formFlowTableDTO.getFlowTableFieldList()) {// PER OGNI CAMPO (DI OGNI SEZIONE
					//TK - 1240975
					if (!Boolean.TRUE.equals(field.getActive())) continue;
					
					// ACCORCIO L'ALIAS
					String name = field.getName();
					if (name.length() > 10) {
						name = name.substring(0, 10);
					}
					// CREO LA SELECT NELLA QUALE SELEZIONO TUTTI I CAMPI DI TUTTE LE SEZIONI, CON
					// ALIAS
					if ("String".equals(field.getFieldType())) {
						campiSelect += tableName + "." + field.getName() + " S"
								+ formFlowTableDTO.getSection() + "_" + field.getPosition() + "_" + name + " , ";
					} else {
						campiSelect += tableName + "." + field.getName() + " S" + formFlowTableDTO.getSection() + "_"
								+ field.getPosition() + "_" + name + " , ";
					}
					if (field.isPk()) { // SE IL CAMPO è CHIAVE PRIMARIA,INSERISCO LA SUA POSIZIONE ALL'INTERNO DI UNA
										// LISTA
						pkList.add(count2);
					}
					count2++;
					if ("Date".equals(field.getFieldType())) {
						dateList.add(field.getName());
					}
				}
				
				
				
				if (fieldd != null && fieldd.length!=0) {
					for (int p = 0; p < fieldd.length; p++) {
						if (fieldd[p].getName() != null) {
							if (formFlowTableDTO.getSection() == fieldd[p].getSection()) {
								whereSQL += "upper(REPLACE (" + tableName + "."
										+ fieldd[p].getName() + ", ' ', '')) = upper(?) AND ";
							}
						}
					}
				}
				if (fieldData != null && fieldData.length!=0) {
					for (int p = 0; p < fieldData.length; p++) {
						if (formFlowTableDTO.getSection() == fieldData[p].getSection()) {
							if (fieldData[p].getDateIn() != null && fieldData[p].getDateOut() != null && fieldData[p].getPosition() == 2) {
								whereSQL += tableName + "." + fieldData[p].getName()
										+ " between ? and ? AND ";
							} else if (fieldData[p].getDateIn() != null && fieldData[p].getPosition() == 0) {
								whereSQL += tableName + "." + fieldData[p].getName()
										+ " >= ? AND ";
							} else if (fieldData[p].getDateOut() != null && fieldData[p].getPosition() == 1) {
								whereSQL += tableName  + "." + fieldData[p].getName()
										+ " <= ? AND ";
							} else {
								whereSQL += tableName + "." + fieldData[p].getName()
										+ " IS NULL AND ";
							}
						}
					}
				}
				
				pkMap.put(formFlowTableDTO.getSection(), pkList); // LA MAPPA AVRA' COME CHIAVE LA SEZIONE E COME VALORE
																	// LA LISTA
				// DEGLI INDICI DELLE CHIAVI
				if (formFlowTableDTO.getSection() == 0) {
					fromSQl += tableName + " ";
				} else {
					if (formFlowTableDTO.getListFk().size() > 0) {
						
						fromSQl += "LEFT JOIN " + tableName + " ON (";
						for (FormFlowTableLinkDTO formFlowTableLinkDTO : formFlowTableDTO.getListFk()) {
							BaseSearchInput input = new BaseSearchInput();
			                input.setParam("idTable", formFlowTableLinkDTO.getIdTable());
			                Pair<List<FlowForeignKeyDO>, SearchInfo> flowReferenced = flowForeignKeyService
			                        .retrieveAllFiltered(input);
			                List<FlowForeignKeyDO> flowForeignKeyDOList = flowReferenced.getFirst();
			                for(FlowForeignKeyDO flowForeignKeyDO: flowForeignKeyDOList) {
								String sectionTable = flowForeignKeyDO.getIdTable().getSection().toString();
								String nameFieldTable = flowForeignKeyDO.getIdFieldTable().getName();
								String sectionTableReferenced = flowForeignKeyDO.getIdTableReferenced().getSection().toString();
								String nameFieldTableReferenced = flowForeignKeyDO.getIdFieldTableReferenced().getName();
								String tableNameNoSec = tableName.substring(0,tableName.length()-1);
								fromSQl += tableNameNoSec + sectionTable + "." + nameFieldTable + " = " + tableNameNoSec
										+ sectionTableReferenced + "." + nameFieldTableReferenced + " AND ";
			                }
			                if (importId != null) {
								fromSQl += tableRootName + ".EXTRACTION_ID = " + tableName + ".EXTRACTION_ID AND ";
							}
			                break;
						}
					}
				}
				if (fromSQl.endsWith(" AND ")) {
					fromSQl = fromSQl.substring(0, fromSQl.length() - 5);
					fromSQl += ") ";
				}
			}

			boolean filtroAggiunto = false;
			if (importId != null) {
				whereSQL += tableRootName + ".EXTRACTION_ID = '" + importId + "'";
				filtroAggiunto = true;
			}

			if (rowStatus != null && !rowStatus.isEmpty()) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".STATUS = '" + rowStatus + "'";
				filtroAggiunto = true;
			}

			if (notRowStatus != null && !notRowStatus.isEmpty()) {
				whereSQL += (filtroAggiunto ? " AND " : " NOT ") + tableRootName + ".STATUS = '" + notRowStatus + "'";
				filtroAggiunto = true;
			}

			if (exported != null && !exported.isEmpty()) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".EXPORTED = '" + exported + "'";
				filtroAggiunto = true;
			}

			if (importType != null) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".IMPORT_TYPE = '" + importType.name()
						+ "'";
				filtroAggiunto = true;
			}

			if (version != null) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".VERSION = '" + version + "'";
				filtroAggiunto = true;
			}
			
			if (!aziende.isEmpty()) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".CODICEAZIENDA IN ('" + String.join("','",aziende) + "')";
				filtroAggiunto = true;
			}
			
			//AGGIUNGO FILTRO IMPORT_TYPE
			if (tipoImportazione != null && !tipoImportazione.equals("Tutte")) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".IMPORT_TYPE = '" + tipoImportazione + "'";
				filtroAggiunto = true;
			}
			//AGGIUNGO FILTRO CODICE PRESIDIO
			if (codicePresidio != null && !codicePresidio.equals("Tutte")) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".CODICEPRESIDIO = '" + codicePresidio + "'";
				filtroAggiunto = true;
			}
			//AGGIUNGO FILTRO CODICEAZIENDA
			if (codiceAzienda != null && !codiceAzienda.equals("Tutte")) {
				whereSQL += (filtroAggiunto ? " AND " : " ") + tableRootName + ".CODICEAZIENDA = '" + codiceAzienda + "'";
				filtroAggiunto = true;
			}
			
			if (whereSQL.endsWith(" AND ")) {
				whereSQL = whereSQL.substring(0, whereSQL.length() - 5);
			} else if (whereSQL.endsWith("WHERE ")) {
				whereSQL = whereSQL.substring(0, whereSQL.length() - 6);
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

		if (campiSelect.endsWith(", ")) {
			campiSelect = campiSelect.substring(0, campiSelect.length() - 2);
		}

		String select = "";
		select = selectSQL + campiSelect + fromSQl + whereSQL;

		List<Object> result = new ArrayList<Object>();
		result.add(select);
		result.add(pkMap);

		return result;
	}

	@Override
	public List<ExtractionErrorMessage> getExtractionErrors(FormFlowDTO configuration, String extractionId) {
		List<ExtractionErrorMessage> errors = new ArrayList<ExtractionErrorMessage>();

		String select = "select extraction_id, err.description description, tmn.field field, tmn.value value, tmn.severity severity ";
		String where = "where extraction_id=?";

		List<FormFlowTableDTO> tableList = configuration.getFlowTableList();
		for (FormFlowTableDTO table : tableList) {

			String selectSec = select;
			String tableName = "fm_flow_" + configuration.getName() + "_" + table.getSection();
			String tableMessageName = tableName + "_message";
			String joinTableAndTMessage = tableName + " tn inner join " + tableMessageName + " tmn on ";
			String joinTMessageAndMessage = " inner join fm_error_message err on err.em_id  = tmn.message ";

			List<String> groupFields = new ArrayList<String>();

			String and = "";
			for (FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
				if (field.isPk()) {
					joinTableAndTMessage += and + "tn." + field.getName() + " = tmn." + field.getName();
					and = " and ";
					
					// i campi chiave della prima sezione sono anche i campi chiave della pratica
					if(field.getSection().intValue() == 0) {
						groupFields.add(field.getName());
						selectSec += " , tn." + field.getName();
					}
					
				}
				/*if (field.getGroups()) {
					groupFields.add(field.getName());
					selectSec += " , tn." + field.getName();
				}*/
			}

			String from = " from " + joinTableAndTMessage + " " + joinTMessageAndMessage;

			String queryString = selectSec + " " + from + " " + where;

			Query query = getSession().createNativeQuery(queryString);
			query.setParameter(1, extractionId);

			List<Object[]> res = query.getResultList();
			if (res != null && !res.isEmpty()) {
				for (Object[] rec : res) {
					ExtractionErrorMessage error = new ExtractionErrorMessage();
					error.setExtractionId((String) rec[0]);
					error.setErrorDescription((String) rec[1]);
					error.setFieldName((String) rec[2]);
					error.setFieldValue((String) rec[3]);
					error.setSeverity((String) rec[4]);
					error.setSectionName(table.getName());

					Map<String, Object> keys = new HashMap<String, Object>();
					error.setKeys(keys);

					for (int idx = 0; idx < groupFields.size(); idx++) {
						keys.put(groupFields.get(idx), rec[5 + idx]);
					}

					errors.add(error);
				}
			}

		}

		return errors;

	}

	@Override
	public List<Object[]> executeSearchQuery(String sql, List<Object> params) {
		Query query = getSession().createNativeQuery(sql);
		for (int i = 0; i < params.size(); i++) {
			if(params.get(i) instanceof Date) {
				query.setParameter(i + 1,(Date) params.get(i), TemporalType.DATE);
				
			}else {
			query.setParameter(i + 1, params.get(i));
			}
		}
		List<Object[]> res = query.getResultList();
		return res;
	}
	
	public Query flowViewParameter(String sql, FlowViewFilter flowViewFilter){
		
		FormFlowDTO flow = flowViewFilter.getFlow();
		FlowViewFilterField[] fieldd = flowViewFilter.getField();
		FlowViewFilterData[] fieldData = flowViewFilter.getFieldData();
//		String[] param = flowViewFilter.getParam();
//		List<Date> dateIn = flowViewFilter.getDateIn();
//		List<Date> dateOut = flowViewFilter.getDateOut();
		int k = 1;
//		int countA = 0;
//		int countB = 0;

		if(fieldd != null && fieldd.length!=0) {
			for(int i= 0; i<fieldd.length; i++) {
				if("NULL".equals(fieldd[i].getParam())) {
					String name = fieldd[i].getName();
					sql = sql.replace(name + ", ' ', '')) = upper(?)", name + ", ' ', '')) IS NULL");
	//				param = ArrayUtils.removeElement(param, param[i]);
					fieldd = ArrayUtils.removeElement(fieldd, fieldd[i]);
					i--;
				}
			}
		}
		
		if(fieldData != null && fieldData.length!=0) {
			for(int i= 0; i<fieldData.length; i++) {
				if(fieldData[i].getPosition() == 3) {
					fieldData = ArrayUtils.removeElement(fieldData, fieldData[i]);
//					dateIn.remove(i);
					i--;
				}
			}
		}

		Query query = entityManager.createNativeQuery(sql);
		
		for(FormFlowTableDTO table : flow.getFlowTableList()) {
			if (fieldd != null && fieldd.length!=0) {
				for (int i = 0; i < fieldd.length; i++) {
					boolean founded = false;
					if (fieldd[i].getName() != null) {
						if (table.getSection() == fieldd[i].getSection()) {
							for(FormFlowTableFieldDTO campo :table.getFlowTableFieldList()) {
								if(fieldd[i].getName().equalsIgnoreCase(campo.getName()) && campo.isCrypto()) {
									fieldd[i].setParam(cryptoManager.encryptString((String)fieldd[i].getParam()));
//									param[i] = cryptoManager.encryptString((String)param[i]);
									founded = true;
								}
								if(founded) break;
							}
							query.setParameter(k, fieldd[i].getParam());
							k++;
						}
					}
				}
			}
			if (fieldData != null && fieldData.length!=0) {
				for (int i = 0; i < fieldData.length; i++) {
					if (table.getSection() == fieldData[i].getSection()) {
						if (fieldData[i].getDateIn() != null && fieldData[i].getDateOut() != null && fieldData[i].getPosition() == 2) {
							Date date1 = (Date)fieldData[i].getDateIn();
							Calendar calFrom = Calendar.getInstance();
							calFrom.setTime(date1);
							calFrom.set(Calendar.HOUR_OF_DAY, 0);
							calFrom.set(Calendar.MINUTE, 0);
							calFrom.set(Calendar.SECOND, 0);
							calFrom.set(Calendar.MILLISECOND, 0);
							query.setParameter(k,calFrom.getTime(), TemporalType.DATE);
							k++;
							Date date2 = (Date)fieldData[i].getDateOut();
							Calendar calTo = Calendar.getInstance();
							calTo.setTime(date2);
							calTo.set(Calendar.HOUR_OF_DAY, 23);
							calTo.set(Calendar.MINUTE, 59);
							calTo.set(Calendar.SECOND, 59);
							calTo.set(Calendar.MILLISECOND, 999);
							query.setParameter(k,calTo.getTime(), TemporalType.DATE);
							k++;
//							countA++;
//							countB++;
						} else if (fieldData[i].getDateIn() != null && fieldData[i].getPosition() == 0) {
							Date date1 = (Date)fieldData[i].getDateIn();
							Calendar calFrom = Calendar.getInstance();
							calFrom.setTime(date1);
							calFrom.set(Calendar.HOUR_OF_DAY, 0);
							calFrom.set(Calendar.MINUTE, 0);
							calFrom.set(Calendar.SECOND, 0);
							calFrom.set(Calendar.MILLISECOND, 0);
							query.setParameter(k,calFrom.getTime(), TemporalType.DATE);
							k++;
//							countA++;
						} else if (fieldData[i].getDateOut() != null && fieldData[i].getPosition() == 1) {
							Date date2 = (Date)fieldData[i].getDateOut();
							Calendar calTo = Calendar.getInstance();
							calTo.setTime(date2);
							calTo.set(Calendar.HOUR_OF_DAY, 23);
							calTo.set(Calendar.MINUTE, 59);
							calTo.set(Calendar.SECOND, 59);
							calTo.set(Calendar.MILLISECOND, 999);
							query.setParameter(k,calTo.getTime(), TemporalType.DATE);
							k++;
//							countB++;
						}
					}
				}
			}
		}
		if(!flowViewFilter.isCount()) {
//			query.setFirstResult(flowViewFilter.getFirstResult());
//			query.setMaxResults(flowViewFilter.getMaxResult());
			int max;
			int first;

		    first = flowViewFilter.getFirstResult();
		    max   = flowViewFilter.getMaxResult();

			query.setFirstResult(Math.max(0, first));

			if (max > 0) {
			    query.setMaxResults(max);
			}
		}
//		List<Object[]> res = query.getResultList();
		return query;
	}

}
