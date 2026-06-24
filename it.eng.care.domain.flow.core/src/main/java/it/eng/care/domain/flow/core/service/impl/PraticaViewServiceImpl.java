package it.eng.care.domain.flow.core.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.b2b.exception.ValidationFlowException;
import it.eng.care.domain.flow.core.auditLog.PraticaDownloadConverter;
import it.eng.care.domain.flow.core.auditLog.PraticaViewConverter;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowDTOtoFlowDO;
import it.eng.care.domain.flow.core.dao.DashboardConfigDAO;
import it.eng.care.domain.flow.core.dao.PraticaViewDAO;
import it.eng.care.domain.flow.core.dto.PaginatedPraticaDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.PrivacyManagerDTO.PMPraticaView;
import it.eng.care.domain.flow.core.entity.ContextConfigurationDO;
import it.eng.care.domain.flow.core.entity.DashboardConfigDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.enumeration.StateSendRegionEnum;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.PraticaViewService;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.crypt.CryptoManager;
import it.eng.care.domain.flow.tabgen.utility.TabgenUtility;
import it.eng.care.platform.audit.api.model.privacymanager.annotation.PrivacyManagerLog;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventActionEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventCategoryEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityTypeEnum;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import jakarta.persistence.EntityManager;

public class PraticaViewServiceImpl implements PraticaViewService {
	
	private static final Logger logger = LoggerFactory.getLogger(PraticaViewServiceImpl.class);
	
    @Autowired
    private PraticaViewDAO praticaViewDAO;

    @Autowired
    private FlowManagerProfileService flowManagerProfileService;
    
    @Autowired(required = false)
	private CryptoManager cryptoManager;
    
    @Autowired
	private DashboardConfigDAO dashboardConfigDAO;
    
    @Autowired
    private ConversionService conversionService;
    
    @Autowired
    private FormFlowDTOtoFlowDO formFlowDTOtoFlowDO;
    
    @Autowired
	private EntityManager entityManager;
    
	private StateSendRegionEnum stateSendRegionEnum;
        
    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }
    
    
    @Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Visualizzazione Pratiche", converter= PraticaViewConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.SCHEDA_MEDICA)
    public PMPraticaView sendAuditVisuaPraDashToPM(FlowViewFilterError flowViewFilterError, HashMap<String,String>campiPraticaSubject, PMPraticaView pMPraticaView) throws ValidationFlowException {
    	return pMPraticaView;
    }
    
    @Override
    public PMPraticaView executeQueryPM(FlowViewFilterError flowViewFilterError, HashMap<String,String>campiPraticaSubject) throws ValidationFlowException {
    	PMPraticaView result = new PMPraticaView();
    	PaginatedPraticaDTO pratiche = new PaginatedPraticaDTO();
    	pratiche.setErrors(new ArrayList<>());
    	try {
	        Boolean check = flowManagerProfileService.checkFlowByName(flowViewFilterError.getFlow().getName());
	        if(!check) {
	            return result;
	        }
	
	        TabgenUtility.initMonthFilter(flowViewFilterError);
	        
			pratiche = praticaViewDAO.search(flowViewFilterError);
	
	        result.setPraticaViewReturns(pratiche);
	        
	        if (flowViewFilterError.getQueryDetail() != null) {
	        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheCountDetail(flowViewFilterError).intValue());
	        } else {
		        if(flowViewFilterError.getPraticheTot() != null) {
		        	if(flowViewFilterError.getExtractionId()!=null) {
		        		if ("INVIATA".equalsIgnoreCase(flowViewFilterError.getCheckType())) {
		                    result.getPraticaViewReturns().setCountTotal(
		                            praticaViewDAO.praticheGiaConsolidateInviateCount(flowViewFilterError).intValue()
		                    );
		                } else {
		                    result.getPraticaViewReturns().setCountTotal(
		                            praticaViewDAO.praticheDisallineateCount(flowViewFilterError).intValue()
		                    );
		                }
		        	}else {
		        		result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheCount(flowViewFilterError).intValue());
		        	}
		        }else if (flowViewFilterError.getMessage() != null) {
		        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheErrorAccordionCount(flowViewFilterError).intValue());
		        }else if (flowViewFilterError.getPkList() == null){
		        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheErrorCount(flowViewFilterError).intValue());
		        }else {
		        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheAccordionCount(flowViewFilterError).intValue());
		        }
	        }
	        
	        List<String> whereFields = new ArrayList<String>();
			List<String> whereParams = new ArrayList<String>();
			List<Object> whereParamsApp = new ArrayList<Object>();
			String app= "";
			for(int i=0; i<result.getPraticaViewReturns().getObjectList().size(); i++) {
				whereFields.clear();
				whereParamsApp.clear();
		        for(Map.Entry me : pratiche.getPraticaViewFields().entrySet()) {
					for(FormFlowTableFieldDTO field : flowViewFilterError.getFlow().getFlowTableList().get(0).getFlowTableFieldList()) {
						if(field.isPk() && field.getName().equalsIgnoreCase((String)me.getValue())) {
							whereFields.add(field.getName());
							whereParamsApp.add(result.getPraticaViewReturns().getObjectList().get(i)[(Integer)me.getKey()]);
						}
					}
				}
		        for(int j=0; j<whereParamsApp.size(); j++) {
					app += whereParamsApp.get(j);
				}
				whereParams.add(app);
				app = "";
			}
			if(!whereFields.isEmpty() && !whereParams.isEmpty()) {
				List<Object[]> resultQuery = praticaViewDAO.searchPratica(whereFields, whereParams, campiPraticaSubject, flowViewFilterError);
				
	//			GESTIONE VALORI CRIPTATI DA DECRIPTARE
				Set<String> keySet = campiPraticaSubject.keySet();
				HashMap<Integer, String> selectFields = new HashMap<Integer, String>();
				int pos = 0;
				for(Object key:keySet){
				     String value = campiPraticaSubject.get(key);
				     selectFields.put(pos, value);
				     pos++;
				}
				for(int i=0; i<selectFields.size(); i++) {
					boolean founded = false;
					for(FormFlowTableDTO table : flowViewFilterError.getFlow().getFlowTableList()) {
						for(FormFlowTableFieldDTO field: table.getFlowTableFieldList()) {
							if(selectFields.get(i).equalsIgnoreCase(field.getName()) && field.isCrypto()) {
								for(int k=0; k<resultQuery.size(); k++) {
									if("Date".equals(field.getFieldType())) {
										try {
											resultQuery.get(k)[i] = cryptoManager.decryptDate((String)resultQuery.get(k)[i]);
										} catch (ParseException e) {
											LogUtil.logException(logger, "", e);
//											e.printStackTrace();
										}
									}else {
										resultQuery.get(k)[i] = cryptoManager.decryptString((String)resultQuery.get(k)[i]);
									}
								}
								founded = true;
							}
							if(founded) break;
						}
						if(founded) break;
					}
				}
				
				result.setQueryResult(resultQuery);
			}
    	} catch (ValidationFlowException e) {
			throw new ValidationFlowException(e.getErrors());
		}
    	
        return result;
        
    }
    
    @Override
    public PMPraticaView executeQuery(FlowViewFilterError flowViewFilterError) throws ValidationFlowException {
	    PMPraticaView result = new PMPraticaView();
	    PaginatedPraticaDTO pratiche = new PaginatedPraticaDTO();
	    boolean praticheErr = false;
	    try {
	        Boolean check = flowManagerProfileService.checkFlowByName(flowViewFilterError.getFlow().getName());
	        if(!check) {
	            return result;
	        }
	        
	        TabgenUtility.initMonthFilter(flowViewFilterError);
	        
	        pratiche = praticaViewDAO.search(flowViewFilterError);
	        if (pratiche != null && pratiche.getErrors() != null && !pratiche.getErrors().isEmpty()) {
	        	praticheErr = true;
	        }
	        result.setPraticaViewReturns(pratiche);
	        
	        if (!praticheErr) {
		        if (flowViewFilterError.getQueryDetail()!=null) {
		        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheCountQueryDetail(flowViewFilterError).intValue());
		        } else {
		        	 if(flowViewFilterError.getPraticheTot() != null) {
		 	        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheCount(flowViewFilterError).intValue());
		 	        }else if (flowViewFilterError.getMessage() != null) {
		 	        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheErrorAccordionCount(flowViewFilterError).intValue());
		 	        }else if (flowViewFilterError.getPkList() == null){
		 	        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheErrorCount(flowViewFilterError).intValue());
		 	        }else {
		 	        	result.getPraticaViewReturns().setCountTotal(praticaViewDAO.praticheAccordionCount(flowViewFilterError).intValue());
		 	        }
		        }
	        }
	        
    	} catch (ValidationFlowException e) {
    		LogUtil.logException(logger, "", e);
			throw new ValidationFlowException(e.getErrors());
		}
	    
	    return result;
    }
    
    @Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Download xlsx Pratiche", converter= PraticaDownloadConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.SCHEDA_MEDICA)
    public byte[] sendAuditDownPraErrToPM(FlowViewFilterError flowViewFilterError, byte[] bytes) {
    	return bytes;
    }
    
	@Override
	public byte[] downloadFlowViewXlsx(FlowViewFilterError flowViewFilterError) {
	    flowViewFilterError.setMaxResult(0);
	    TabgenUtility.initMonthFilter(flowViewFilterError);

	    // caricamento lista aziende visibili dall'utente
	    FormFlowDTO flow = flowViewFilterError.getFlow();
	    FlowDO flowDO = new FlowDO();
	    formFlowDTOtoFlowDO.convert(flow, flowDO, new ConversionContext(conversionService));
	    String queryDetailExp = null;
	    String fieldAcceptedQueryDetail = null;

	    List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
	    List<DashboardConfigDO> dashboardConfigListFor = new ArrayList<>();

	    if (!aziende.isEmpty()) {
	        dashboardConfigListFor = dashboardConfigDAO.findAllByFlowAndCodiceAziendaIn(flowDO, aziende);
	        dashboardConfigListFor.addAll(dashboardConfigDAO.findAllByFlowAndCodiceAziendaIsNull(flowDO));

	        // Raggruppamento per nome con priorità al codiceAzienda valorizzato
	        Map<String, DashboardConfigDO> groupedMap = new LinkedHashMap<>();
	        for (DashboardConfigDO cfg : dashboardConfigListFor) {
	            String key = cfg.getName();
	            if (!groupedMap.containsKey(key)) {
	                groupedMap.put(key, cfg);
	            } else {
	                DashboardConfigDO existing = groupedMap.get(key);
	                if (existing.getCodiceAzienda() == null && cfg.getCodiceAzienda() != null) {
	                    groupedMap.put(key, cfg);
	                }
	            }
	        }
	        dashboardConfigListFor = new ArrayList<>(groupedMap.values());
	    } else {
	        dashboardConfigListFor = dashboardConfigDAO.findAllByFlow(flowDO);
	    }

	    for (DashboardConfigDO dashboardConfig : dashboardConfigListFor) {
	    	if (dashboardConfig.getFlow() != null && flow.getCode().equalsIgnoreCase(dashboardConfig.getFlow().getCode()) && (dashboardConfig.getName().equalsIgnoreCase(flowViewFilterError.getRegion()) || dashboardConfig.getName().equalsIgnoreCase(flowViewFilterError.getDashboardName()))) {  	
	            if (dashboardConfig.getQueryDetailExp() != null) {
	                queryDetailExp = dashboardConfig.getQueryDetailExp();
	                flowViewFilterError.setQueryDetail(queryDetailExp);
	            }
	            if (dashboardConfig.getFieldAcceptedQueryDetail() != null) {
	                fieldAcceptedQueryDetail = dashboardConfig.getFieldAcceptedQueryDetail();
	                flowViewFilterError.setFieldAcceptedQueryDetail(fieldAcceptedQueryDetail);
	            }
	            break;
	        }
	    }

	    // Inizializzazione workbook
	    XSSFWorkbook workBook = new XSSFWorkbook();
	    CreationHelper creationHelper = workBook.getCreationHelper();
	    XSSFCellStyle style = workBook.createCellStyle();
	    XSSFCellStyle styleData = workBook.createCellStyle();
	    XSSFFont font = workBook.createFont();
	    font.setFontHeightInPoints((short) 12);
	    font.setBold(true);

	    style.setBorderTop(BorderStyle.valueOf((short) 6));
	    style.setBorderBottom(BorderStyle.valueOf((short) 1));
	    style.setFont(font);
	    styleData.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yyyy"));

	    // Variabili comuni
	    List<Object[]> list = null;
	    List<Object> resultList = null;
	    List<Object> resultListStd = null;
	    Map<Integer, String> selectFieldsDetailList = null;
	    Map<Integer, String> selectDescriptionsDetailList = null;
	    List<FormFlowTableFieldDTO> filtersList = null;
	    List<String> pkNameList = null;
	    XSSFSheet sheet = null;

	    List<FormFlowTableFieldDTO> headers = new ArrayList<>();
	    List<String> headerString = new ArrayList<>();

	    int rowCount = 0;
	    Row row = null;

	    // =========================================================
	    // CASE 1: QUERY PERSONALIZZATA (queryDetailExp valorizzata)
	    // =========================================================
	    if (queryDetailExp != null) {
	        try {
	        	String region = flowViewFilterError.getRegion();
	        	String message = flowViewFilterError.getMessage();
	        	String errorCode = flowViewFilterError.getErrorCode();
	        	String tipoImportazione = flowViewFilterError.getTipoImportazione();
	        	String anni = flowViewFilterError.getYear();
	        	String mesi = flowViewFilterError.getMonth();
	        	Date extraDateFrom = flowViewFilterError.getExtraDateFrom();
	        	Date extraDateTo = flowViewFilterError.getExtraDateTo();
				if (tipoImportazione == null) {
					flowViewFilterError.setTipoImportazione("Tutte");
				}
				String codicePresidio = flowViewFilterError.getCodicePresidio();
				if (codicePresidio == null) {
					flowViewFilterError.setCodicePresidio("Tutte");
				}
				String codiceAzienda = flowViewFilterError.getCodiceAzienda();
				if (codiceAzienda == null) {
					flowViewFilterError.setCodiceAzienda("Tutte");
				}
				//imposto i parametri in minuscolo per non avere problemi nel setting
				queryDetailExp = TabgenUtility.normalizeAllParamsToLowerCase(flowViewFilterError.getQueryDetail());
				
	        	//elimino eventuali parametri non obbligatori e non settabili per azienda tipoImportazione codicePresidio
				if (aziende.isEmpty() && queryDetailExp.contains(":aziendeprofilate")) {
					queryDetailExp = queryDetailExp.replace(":aziendeprofilate", TabgenUtility.getFieldForParameter(queryDetailExp, ":aziendeprofilate"));
				}
				if ((tipoImportazione == null || tipoImportazione.equals("Tutte")) && queryDetailExp.contains(":tipoimportazione")) {
					queryDetailExp = queryDetailExp.replace(":tipoimportazione", TabgenUtility.getFieldForParameter(queryDetailExp, ":tipoimportazione"));
				}
				if ((codicePresidio == null || codicePresidio.equals("Tutte")) && queryDetailExp.contains(":codicepresidio")) {
					queryDetailExp = queryDetailExp.replace(":codicepresidio", TabgenUtility.getFieldForParameter(queryDetailExp, ":codicepresidio"));
				}
				if ((codiceAzienda == null || codiceAzienda.equals("Tutte")) && queryDetailExp.contains(":codiceazienda")) {
					queryDetailExp = queryDetailExp.replace(":codiceazienda", TabgenUtility.getFieldForParameter(queryDetailExp, ":codiceazienda"));
				}
				if ((message == null || message.equals("Tutte")) && queryDetailExp.contains(":message")) {
					queryDetailExp = queryDetailExp.replace(":message", TabgenUtility.getFieldForParameter(queryDetailExp, ":message"));
				}
				if ((errorCode == null || errorCode.equals("Tutte")) && queryDetailExp.contains(":codiceerrore")) {
					queryDetailExp = queryDetailExp.replace(":codiceerrore", TabgenUtility.getFieldForParameter(queryDetailExp, ":codiceerrore"));
				}
				flowViewFilterError.setQueryDetail(queryDetailExp);
	            resultList = praticaViewDAO.createQueryDetail(flowViewFilterError);
	            List<String> errors = (List<String>) resultList.get(6);
				
				if (!errors.isEmpty()) {
					try {
						flowViewFilterError.setErrors(errors);
						//Creo un file log in memoria con gli errori
				        byte[] bytes = FileUtility.generateFileLog(errors, "query_detail_exp_error_log");

				        //Creo direttamente un ByteArrayOutputStream da restituire
				        ByteArrayOutputStream bosBytes = new ByteArrayOutputStream();
				        bosBytes.write(bytes);
				        bosBytes.flush();

				        //Restituisco il contenuto del ByteArrayOutputStream
				        return bosBytes.toByteArray();
			            
					} catch (Exception e) {
						LogUtil.logException(logger, "", e);
					}
				} else {

		            if (resultList != null && resultList.get(0) != null) {
//		                Query query = entityManager.createNativeQuery((String) resultList.get(0));
		            	NativeQuery<?> query = entityManager.createNativeQuery((String) resultList.get(0)).unwrap(NativeQuery.class);
		                
		            	if (anni!=null && queryDetailExp.contains(":anni")) {
		            		query.setParameter("anni", anni);
		            	}
		            	if (mesi!=null && queryDetailExp.contains(":mesi")) {
		            		query.setParameterList("mesi", getMonthForQuery(mesi));
		            	}
		            	if (extraDateFrom!=null && queryDetailExp.contains(":datada")) {
		            		query.setParameter("datada", extraDateFrom);
		            	}
		            	if (extraDateTo!=null && queryDetailExp.contains(":dataa")) {
		            		query.setParameter("dataa", extraDateTo);
		            	}
		                if (!aziende.isEmpty() && queryDetailExp.contains(":aziendeprofilate")) {
		                    query.setParameterList("aziendeprofilate", aziende);
		                }
		                if (tipoImportazione != null && !tipoImportazione.equals("Tutte") && queryDetailExp.contains(":tipoimportazione")) {
							query.setParameter("tipoimportazione", tipoImportazione);
						}
						if (codicePresidio != null && !codicePresidio.equals("Tutte") && queryDetailExp.contains(":codicepresidio")) {
							query.setParameter("codicepresidio", codicePresidio);
						}
						if (codiceAzienda != null && !codiceAzienda.equals("Tutte") && queryDetailExp.contains(":codiceazienda")) {
							query.setParameter("codiceazienda", codiceAzienda);
						}
						if (message != null && !message.equals("Tutte")  && queryDetailExp.contains(":message")) {
							query.setParameter("message", message);
						}
						if (errorCode != null && !errorCode.equals("Tutte") && queryDetailExp.contains(":codiceerrore")) {
							query.setParameter("codiceerrore", errorCode);
						}
						
//		                if (flowViewFilterError.isAccordionPag()) {
//		                    query.setFirstResult(flowViewFilterError.getFirstResultAcc());
//		                    query.setMaxResults(flowViewFilterError.getMaxResultAcc());
//		                } else {
//		                    query.setFirstResult(flowViewFilterError.getFirstResult());
//		                    query.setMaxResults(flowViewFilterError.getMaxResult());
//		                }
//	
//		                if (flowViewFilterError.isTopFive()) {
//		                    query.setMaxResults(5);
//		                }
						int max;
						int first;
						if (flowViewFilterError.isAccordionPag()) {
						    first = flowViewFilterError.getFirstResultAcc();
						    max   = flowViewFilterError.getMaxResultAcc();
						} else {
						    first = flowViewFilterError.getFirstResult();
						    max   = flowViewFilterError.getMaxResult();
						}
						query.setFirstResult(Math.max(0, first));
						if (flowViewFilterError.isTopFive()) {
						    query.setMaxResults(5);
						} else {
						    if (max <= 0) {
						        max = 1000; // limite di sicurezza
						    }
						    query.setMaxResults(max);
						}
	
		                list = (List<Object[]>) query.getResultList();
	
		                if (resultList.get(1) != null) {
		                    selectFieldsDetailList = (Map<Integer, String>) resultList.get(1);
		                }
		                if (resultList.get(2) != null) {
		                    selectDescriptionsDetailList = (Map<Integer, String>) resultList.get(2);
		                }
		                if (resultList.get(3) != null) {
		                    filtersList = (List<FormFlowTableFieldDTO>) resultList.get(3);	
		                }
		                if (resultList.get(4) != null) {
		                    pkNameList =  (List<String>) resultList.get(4);
		                }
		            }
				}
				
	            //Popolamento dinamico header e scrittura dati
	            if (selectFieldsDetailList != null && !selectFieldsDetailList.isEmpty()) {
	                headerString.clear();
	                for (String desc : selectFieldsDetailList.values()) {
	                    if (desc != null && !desc.trim().isEmpty()) {
	                        headerString.add(desc.toUpperCase().trim());
	                    }
	                }
	            }

	            sheet = workBook.createSheet(flowViewFilterError.getName());

	            // Header
	            row = sheet.createRow(rowCount);
	            int columnCount = -1;
	            for (String head : headerString) {
	                Cell cell = row.createCell(++columnCount);
	                cell.setCellStyle(style);
	                cell.setCellValue(head);
	            }

	            // Data rows
	            if (list != null && !list.isEmpty()) {
	                for (int k = 0; k < list.size(); k++) {
	                    row = sheet.createRow(++rowCount);
	                    Object[] rowData = list.get(k);

	                    for (int i = 0; i < rowData.length; i++) {
	                        Cell cell = row.createCell(i);
	                        Object value = rowData[i];
	                        
//	   	                 if (value == null) continue;
	                        if (value != null) {
		                        try {
		                            String fieldName = null;
		                            if (selectFieldsDetailList != null && i < selectFieldsDetailList.size()) {
		                                fieldName = selectFieldsDetailList.get(i);
		                            }
		                            final String finalFieldName = fieldName;
		                            FormFlowTableFieldDTO fieldDTO = null;
		                            for (FormFlowTableDTO table : flowViewFilterError.getFlow().getFlowTableList()) {
		                                Optional<FormFlowTableFieldDTO> match =
		                                    table.getFlowTableFieldList().stream()
		                                        .filter(f -> f.getName().equalsIgnoreCase(finalFieldName))
		                                        .findFirst();
		                                if (match.isPresent()) {
		                                    fieldDTO = match.get();
		                                    break;
		                                }
		                            }
	
		                            if (fieldDTO != null) {
		                                if ("Date".equalsIgnoreCase(fieldDTO.getFieldType())) {
		                                    if (fieldDTO.isCrypto() && value instanceof String) {
		                                        value = cryptoManager.decryptDate((String) value);
		                                    }
		                                    cell.setCellStyle(styleData);
		                                    if (value instanceof Date) {
		                                        cell.setCellValue((Date) value);
		                                    } else if (value instanceof String) {
		                                        try {
		                                            cell.setCellValue(java.sql.Date.valueOf((String) value));
		                                        } catch (Exception ex) {
		                                            cell.setCellValue((String) value);
		                                        }
		                                    }
		                                } else {
		                                    if (fieldDTO.isCrypto() && value instanceof String) {
		                                        value = cryptoManager.decryptString((String) value);
		                                    }
		                                    cell.setCellValue(value.toString());
		                                }
		                            } else {
		                                // Campo non mappato
		                                if (value instanceof Date) {
		                                    cell.setCellStyle(styleData);
		                                    cell.setCellValue((Date) value);
		                                } else {
		                                    cell.setCellValue(value.toString());
		                                }
		                            }
	
		                        } catch (Exception ex) {
		                        	LogUtil.logException(logger, "Errore scrittura cella [" + k + "," + i + "]", ex);
		                            cell.setCellValue(String.valueOf(value));
		                        }
	                    	} else {
	                    		cell.setCellValue((String) null);
	                    	}
	                    }
	                }

	                // Autosize colonne
	                for (int i = 0; i < headerString.size(); i++) {
	                    sheet.autoSizeColumn(i);
	                }
	            }

	        } catch (Exception e) {
	        	LogUtil.logException(logger, "", e);
//	            e.printStackTrace();
	            try {
	            	List<String> errors = new ArrayList<>();
	            	errors.add("Errore su procedura downloadFlowViewXlsx : "+ e);
					flowViewFilterError.setErrors(errors);
					//Creo un file log in memoria con gli errori
			        byte[] bytes = FileUtility.generateFileLog(errors, "query_detail_exp_error_log");

			        //Creo direttamente un ByteArrayOutputStream da restituire
			        ByteArrayOutputStream bosBytes = new ByteArrayOutputStream();
			        bosBytes.write(bytes);
			        bosBytes.flush();

			        //Restituisco il contenuto del ByteArrayOutputStream
			        return bosBytes.toByteArray();
		            
				} catch (Exception e1) {
					LogUtil.logException(logger, "", e1);
				}
	        }
	    }

	 // ============================================================
	 // CASE 2: QUERY STANDARD (queryDetailExp == null)
	 // ============================================================
	 else {
		 String region = flowViewFilterError.getRegion();
	     // 1) Recupero query dinamica esattamente come CASE 1
	     if ("PRATICHE_NOT_SEND_REG".equals(flowViewFilterError.getName()) || "PRATICHE_REG".equals(flowViewFilterError.getName()) || "PRATICHE_RIC_REG".equals(flowViewFilterError.getName())) {
	         resultListStd = praticaViewDAO.searchBadgeDownloadXLS(flowViewFilterError);
	     } 
	     else if ("PRATICHE".equals(flowViewFilterError.getName())) {
	         resultListStd = praticaViewDAO.searchBadgeDownloadXLS(flowViewFilterError);
	     } 
	     else if ("PRATICHE_ERRATE".equals(flowViewFilterError.getName())) {
	         resultListStd = praticaViewDAO.searchBadgeDownloadXLS(flowViewFilterError);
	     }
	     else if ("PRATICHE_ERRATE_REG".equals(flowViewFilterError.getName()) || "PRATICHE_SEGNALAZIONI_REG".equals(flowViewFilterError.getName())) {
	         resultListStd = praticaViewDAO.searchBadgeDownloadXLS(flowViewFilterError);
	     }
	     else if ("ERRORI".equals(flowViewFilterError.getName()) || "ERRORI_REG".equals(flowViewFilterError.getName())) {
	         resultListStd = praticaViewDAO.searchBadgeDownloadXLS(flowViewFilterError);
	     }

	     if (resultListStd == null) {
	    	 logger.warn("Per il download in formato xlsx del badge "+ flowViewFilterError.getName() +" non è prevista una query statica lato BE e quindi occorre impostare la query personalizzata nella proprietà QUERY_DETAIL_EXP (FM_TABGEN_VALUE.FIELD15) della tabella generica FM_DASHBOARD_CONFIG per il flusso "+flowViewFilterError.getFlow().getName() +"");
	    	 try {
	            	List<String> errors = new ArrayList<>();
	            	errors.add("Per il download in formato xlsx del badge "+ flowViewFilterError.getName() +" non è prevista una query statica lato BE e quindi occorre impostare la query personalizzata nella proprietà QUERY_DETAIL_EXP (FM_TABGEN_VALUE.FIELD15) della tabella generica FM_DASHBOARD_CONFIG per il flusso "+flowViewFilterError.getFlow().getName() +"");
					flowViewFilterError.setErrors(errors);
					//Creo un file log in memoria con gli errori
			        byte[] bytes = FileUtility.generateFileLog(errors, "query_detail_exp_error_log");

			        //Creo direttamente un ByteArrayOutputStream da restituire
			        ByteArrayOutputStream bosBytes = new ByteArrayOutputStream();
			        bosBytes.write(bytes);
			        bosBytes.flush();

			        //Restituisco il contenuto del ByteArrayOutputStream
			        return bosBytes.toByteArray();
		            
				} catch (Exception e1) {
					LogUtil.logException(logger, "", e1);
				}
	     }

	     String sqlQuery = (String) resultListStd.get(0);
	     selectFieldsDetailList = (Map<Integer, String>) resultListStd.get(1);
	     selectDescriptionsDetailList = (Map<Integer, String>) resultListStd.get(2);
	     filtersList = (List<FormFlowTableFieldDTO>) resultListStd.get(3);
	     pkNameList = (List<String>) resultListStd.get(4);

	     // 2) Eseguo realmente la query
//	     Query query = entityManager.createNativeQuery(sqlQuery);
	     NativeQuery<?> query = entityManager.createNativeQuery(sqlQuery).unwrap(NativeQuery.class);
	    
	    if (flowViewFilterError.getCanViewDateFromToFilters()) {
	    	if (flowViewFilterError.getExtraDateFrom() != null && flowViewFilterError.getExtraDateTo() != null) {
				query.setParameter("datada", flowViewFilterError.getExtraDateFrom());
				query.setParameter("dataa", flowViewFilterError.getExtraDateTo());
			}
	    } else {
	    	if (flowViewFilterError.getMonth() != null && flowViewFilterError.getYear() != null) {
				query.setParameterList("mesi", getMonthForQuery(flowViewFilterError.getMonth()));
				query.setParameter("anni", flowViewFilterError.getYear());
			}
	    }
		if (!aziende.isEmpty() && sqlQuery.contains(":aziendeprofilate")) {
			query.setParameterList("aziendeprofilate", aziende);
		}
		if (flowViewFilterError.getTipoImportazione() != null && !flowViewFilterError.getTipoImportazione().equals("Tutte") && sqlQuery.contains(":tipoimportazione")) {
			query.setParameter("tipoimportazione", flowViewFilterError.getTipoImportazione());
		}
		if (flowViewFilterError.getCodicePresidio() != null && !flowViewFilterError.getCodicePresidio().equals("Tutte") && sqlQuery.contains(":codicepresidio")) {
			query.setParameter("codicepresidio", flowViewFilterError.getCodicePresidio());
		}
		if (flowViewFilterError.getCodiceAzienda() != null && !flowViewFilterError.getCodiceAzienda().equals("Tutte") && sqlQuery.contains(":codiceazienda")) {
			query.setParameter("codiceazienda", flowViewFilterError.getCodiceAzienda());
		}
		if (flowViewFilterError.getErrorCode() != null && !flowViewFilterError.getErrorCode().equals("Tutte") && sqlQuery.contains(":codiceerrore")) {
			query.setParameter("codiceerrore", flowViewFilterError.getErrorCode());
		}
		if (flowViewFilterError.getMessage() != null && !flowViewFilterError.getMessage().equals("Tutte") && sqlQuery.contains(":message")) {
			query.setParameter("message", flowViewFilterError.getMessage());
		}
		
		//CONTROLLO REGIONALE PER APPLICARE I PARAMETER
		if ("PRATICHE_NOT_SEND_REG".equals(flowViewFilterError.getName())) {
			query.setParameter("state", stateSendRegionEnum.INVIATA.toString());
			query.setParameter("state2", stateSendRegionEnum.ACCETTATA.toString());
	    }else if("PRATICHE_REG".equals(flowViewFilterError.getName())) {
	    	query.setParameter("state", stateSendRegionEnum.INVIATA.toString());
			query.setParameter("state2", stateSendRegionEnum.ACCETTATA.toString());
	    }else if("PRATICHE_RIC_REG".equals(flowViewFilterError.getName())) {
	    	query.setParameter("state", stateSendRegionEnum.ACCETTATA.toString());
	    	query.setParameter("state2", "VALIDO");
	    	query.setParameter("state3", "SEGNALAZIONE");
	    }
		
	     // paginazione
//	     if (flowViewFilterError.isAccordionPag()) {
//	         query.setFirstResult(flowViewFilterError.getFirstResultAcc());
//	         query.setMaxResults(flowViewFilterError.getMaxResultAcc());
//	     } else {
//	         query.setFirstResult(flowViewFilterError.getFirstResult());
//	         query.setMaxResults(flowViewFilterError.getMaxResult());
//	     }
		
		int max;
		int first;
		if (flowViewFilterError.isAccordionPag()) {
		    first = flowViewFilterError.getFirstResultAcc();
		    max   = flowViewFilterError.getMaxResultAcc();
		} else {
		    first = flowViewFilterError.getFirstResult();
		    max   = flowViewFilterError.getMaxResult();
		}
		query.setFirstResult(Math.max(0, first));
		if (flowViewFilterError.isTopFive()) {
		    query.setMaxResults(5);
		} else {
		    if (max <= 0) {
		        max = 1000; // limite di sicurezza
		    }
		    query.setMaxResults(max);
		}

//	     list = query.getResultList();
	     list = (List<Object[]>) query.getResultList();

	     // 3) COSTRUZIONE SHEET IDENTICO AL CASE 1
	     sheet = workBook.createSheet(flowViewFilterError.getName());

	     // HEADER
	     row = sheet.createRow(rowCount);
	     int columnCount = -1;
	     headerString.clear();

	     for (String desc : selectFieldsDetailList.values()) {
	         if (desc != null && !desc.trim().isEmpty()) {
	             headerString.add(desc.toUpperCase());
	         }
	     }
	     
	     if ("ERRORI".equals(flowViewFilterError.getName()) || "ERRORI_REG".equals(flowViewFilterError.getName())) {
	    	 headerString.add("TIPO IMPORTAZIONE");
	    	 headerString.add("MESE");
	    	 headerString.add("ANNO");
	    	 headerString.add("CODICE ERRORE");
	    	 headerString.add("DESCRIZIONE ERRORE");
	    	 headerString.add("GRAVITA");
	     } else if ("PRATICHE_ERRATE".equals(flowViewFilterError.getName())) {
	    	 headerString.add("TIPO IMPORTAZIONE");
	    	 headerString.add("MESE");
	    	 headerString.add("ANNO");
	    	 headerString.add("CODICE ERRORE");
	    	 headerString.add("GRAVITA");
	     } else if ("PRATICHE_ERRATE_REG".equals(flowViewFilterError.getName()) || "PRATICHE_SEGNALAZIONI_REG".equals(flowViewFilterError.getName())) {
	    	 headerString.add("TIPO IMPORTAZIONE");
	    	 headerString.add("MESE");
	    	 headerString.add("ANNO");
	    	 headerString.add("CODICE ERRORE");
	    	 headerString.add("DESCRIZIONE ERRORE");
	     } else if ("PRATICHE".equals(flowViewFilterError.getName()) || "PRATICHE_NOT_SEND_REG".equals(flowViewFilterError.getName()) || "PRATICHE_REG".equals(flowViewFilterError.getName()) || "PRATICHE_RIC_REG".equals(flowViewFilterError.getName())) {
	    	 headerString.add("TIPO IMPORTAZIONE");
	    	 headerString.add("MESE");
	    	 headerString.add("ANNO");
	     }
	    		 
	     for (String head : headerString) {
	         Cell cell = row.createCell(++columnCount);
	         cell.setCellStyle(style);
	         cell.setCellValue(head);
	     }

	     // DATA ROWS
	     if (list != null && !list.isEmpty()) {
	         for (int r = 0; r < list.size(); r++) {
	             row = sheet.createRow(++rowCount);
	             Object[] rowData = list.get(r);
	             
	             for (int c = 0; c < rowData.length; c++) {
	                 Cell cell = row.createCell(c);
	                 Object value = rowData[c];

//	                 if (value == null) continue;
	                 if (value != null) {
		                 try {
		                     String fieldName = selectFieldsDetailList.get(c);
		                     FormFlowTableFieldDTO fieldDTO = findFieldDTO(flowViewFilterError, fieldName);
	
		                     if (fieldDTO != null) {
		                         if ("Date".equalsIgnoreCase(fieldDTO.getFieldType())) {
	
		                             if (fieldDTO.isCrypto() && value instanceof String)
		                                 value = cryptoManager.decryptDate((String) value);
	
		                             cell.setCellStyle(styleData);
		                             if (value instanceof Date) {
		                                 cell.setCellValue((Date) value);
		                             } else {
		                                 cell.setCellValue(value.toString());
		                             }
	
		                         } else {
		                             if (fieldDTO.isCrypto() && value instanceof String)
		                                 value = cryptoManager.decryptString((String) value);
	
		                             cell.setCellValue(value.toString());
		                         }
		                     } else {
		                         if (value instanceof Date) {
		                             cell.setCellStyle(styleData);
		                             cell.setCellValue((Date) value);
		                         } else {
		                             cell.setCellValue(value.toString());
		                         }
		                     }
	
		                 } catch (Exception ex) {
		                	 LogUtil.logException(logger, "Errore scrittura cella [" + r + "," + c + "]", ex);
		                     cell.setCellValue(String.valueOf(value));
		                 }
	             	} else {
	             		cell.setCellValue((String) null);
	             	}
	             }
	         }

	         // Autosize
	         for (int i = 0; i < headerString.size(); i++) {
	             sheet.autoSizeColumn(i);
	         }
	     	}
	    }

	    // Scrittura su ByteArrayOutputStream
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    try {
	        workBook.write(bos);
	    } catch (IOException e) {
	    	LogUtil.logException(logger, "", e);
//	        e.printStackTrace();
	    } finally {
	        try {
	            bos.close();
	        } catch (IOException e) {
	        	LogUtil.logException(logger, "", e);
//	            e.printStackTrace();
	        }
	    }
	    return bos.toByteArray();
	}
	
	private FormFlowTableFieldDTO findFieldDTO(FlowViewFilterError filter, String name) {
	    if (name == null) return null;

	    for (FormFlowTableDTO table : filter.getFlow().getFlowTableList()) {
	        for (FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
	            if (name.equalsIgnoreCase(field.getName())) {
	                return field;
	            }
	        }
	    }
	    return null;
	}

    @Override
    public PaginatedPraticaDTO searchErrors(FlowViewFilterError flowViewFilterError) {

        PaginatedPraticaDTO pratiche = new PaginatedPraticaDTO();
        pratiche.setErrors(new ArrayList<>());
//        Boolean check = flowManagerProfileService.checkFlowByName(flowViewFilterError.getFlow().getName());
//        if(!check) {
//            return pratiche;
//        }
        TabgenUtility.initMonthFilter(flowViewFilterError);
        
        try {
	        List<Object[]> values = praticaViewDAO.searchErrors(flowViewFilterError);
	        if (flowViewFilterError.getErrors() != null && !flowViewFilterError.getErrors().isEmpty()) {
	        	pratiche.setErrors(flowViewFilterError.getErrors());
	        } else {
	        	if(!flowViewFilterError.isView()) {
	        		if (flowViewFilterError.getQueryDetail() != null) {
	        			pratiche.setCountTotal(praticaViewDAO.praticheCountQueryDetail(flowViewFilterError).intValue());
	        		} else {
	        			pratiche.setCountTotal(praticaViewDAO.errorCount(flowViewFilterError).intValue());
	        		}
		        }
	        }
	        pratiche.setObjectList(values);
        } catch (Exception e) {
        	LogUtil.logException(logger, "", e);
//			e.printStackTrace();
		}
        
        return pratiche;

    }
    
    @Override
    public HashMap<String,String> getContextValue(List<List<String>> keys, List<ContextConfigurationDO> config){
    	
    	HashMap<String,String> result = new HashMap<String,String>();
    	for(ContextConfigurationDO context: config) {
    		String flow = context.getFlow();
    		String section = context.getSection();
    		String param = context.getParam();
    		String value = praticaViewDAO.getContextValue(keys, flow, section, param);
    		String key = context.getName();
    		result.put(key, value);
    	}
    	
    	return result;
    }
    
    private List<Object> getMonthForQuery(String month) {
		if (month!= null && month.contains(",")) {
			List<Object> months =  Arrays.asList(month.split("\\s*,\\s*"));
			months.forEach(m -> {
				if (((String) m) != null && ((String) m).length() < 2) {
					 m = "0" + ((String) m);
				}
			});
			return months;
		} else {
			if (month != null && month.length() < 2) {
				month = "0" + month;
			}
			return Collections.singletonList(month);
		}
	}
    
    
}
    
