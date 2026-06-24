package it.eng.care.domain.flow.core.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.transaction.annotation.Transactional;

import it.eng.care.domain.flow.core.auditLog.FlowPatientGetConverter;
import it.eng.care.domain.flow.core.auditLog.FlowPatientSearchConverter;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.FlowViewDAO;
import it.eng.care.domain.flow.core.dao.SearchPatientFieldDAO;
import it.eng.care.domain.flow.core.dto.SearchFlowPatientFilter;
import it.eng.care.domain.flow.core.dto.SearchPatientDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.SearchPatientFieldDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowPatientService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.QueryGenerator;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.crypt.CryptoManager;
import it.eng.care.platform.audit.api.model.privacymanager.annotation.PrivacyManagerLog;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventActionEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventCategoryEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityTypeEnum;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@Transactional
public class FlowPatientServiceImpl implements FlowPatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowPatientServiceImpl.class);
	
	@Autowired
	private FormFlowService formFlowService;

	@Autowired
	private FlowService flowService;

	@Autowired
	private SearchPatientFieldDAO searchPatientFieldDAO;

	@Autowired
	private FlowViewDAO flowViewDAO;
	
	@Autowired
	private FlowDAO flowDAO;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;
	
	@Autowired(required = false)
	private CryptoManager cryptoManager;

	/**
	 * Ricerca il paziente in tutti i flussi aziendali
	 * 
	 * 1. carica le configurazione di tutti i flussi 2. per ogni flusso esegue una
	 * 2. carica la configurazione (tabgen FM_SEARCH_PATIENT_FIELDS) che definisce i
	 * campi di ricerca e i campi da mostrare all'utente ricerca per nome, cognome e
	 * codice fiscale 3.
	 */
	@Override
	public List<SearchPatientDTO> searchPatientOccurrences(SearchPatientDTO patientFilter) {
		List<SearchPatientDTO> result = new ArrayList<SearchPatientDTO>();
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		List<SearchPatientFieldDO> fmSearchPatientFieldsTabgen = new ArrayList<>();
		if (!aziende.isEmpty()) {
			fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByCodiceAziendaIn(aziende);
			if (fmSearchPatientFieldsTabgen.isEmpty()) {
				fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByCodiceAziendaIsNull();
			}
		} else {
			fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAll();
		}
		
		List<FlowDO> flowList = flowService.getAllWithVersions("AZIENDA");

		if (flowList != null) {
			for (FlowDO flow : flowList) {
				
				Boolean check = flowManagerProfileService.checkFlowByName(flow.getName());
				if(!check) {
					continue;
				}
				
				FormFlowDTO configuration = retrieveConfiguration(flow.getId(), flow.getVersions().iterator().next().getVersion().getId());

				List<String> searchFields = new ArrayList<String>();
				List<String> selectFields = new ArrayList<String>();
				List<String> columnFields = new ArrayList<String>();
				List<String> descrFields = new ArrayList<String>();
				List<Object> params = new ArrayList<Object>();
				
				String name = "NOME", surname = "COGNOME", birthDate = "DATANASCITA", taxCode = "CODICEFISCALE";
				
				if (fmSearchPatientFieldsTabgen != null && !fmSearchPatientFieldsTabgen.isEmpty()) {
					for (SearchPatientFieldDO patientFields : fmSearchPatientFieldsTabgen) {
						if(patientFields.getFlusso().equals(flow.getName())) {
							if(patientFields.getCampoFunzione().equals("NOME")) {
								name = (String)patientFields.getCampoTracciato();
								if(patientFilter.getName() != null && !patientFilter.getName().isEmpty()) {
									searchFields.add(name);
									params.add(patientFilter.getName());
								}
							} else if(patientFields.getCampoFunzione().equals("COGNOME")) {
								surname = (String)patientFields.getCampoTracciato();
								if(patientFilter.getSurname() != null && !patientFilter.getSurname().isEmpty()) {
									searchFields.add(surname);
									params.add(patientFilter.getSurname());
								}
							} else if(patientFields.getCampoFunzione().equals("DATANASCITA")) {
								birthDate = (String)patientFields.getCampoTracciato();
								if(patientFilter.getBirthDate() != null) {
									searchFields.add(birthDate);

									Date dataNascita = patientFilter.getBirthDate();
									
									Calendar calFrom = Calendar.getInstance();
									calFrom.setTime(dataNascita);
									calFrom.set(Calendar.HOUR_OF_DAY, 0);
									calFrom.set(Calendar.MINUTE, 0);
									calFrom.set(Calendar.SECOND, 0);
									calFrom.set(Calendar.MILLISECOND, 0);
									
									Calendar calTo = Calendar.getInstance();
									calTo.setTime(dataNascita);
									calTo.set(Calendar.HOUR_OF_DAY, 23);
									calTo.set(Calendar.MINUTE, 59);
									calTo.set(Calendar.SECOND, 59);
									calTo.set(Calendar.MILLISECOND, 999);
									
									params.add(calFrom.getTime());
									params.add(calTo.getTime());
									
								}
							} else if(patientFields.getCampoFunzione().equals("CODICEFISCALE")) {
								taxCode = (String)patientFields.getCampoTracciato();
								if(patientFilter.getTaxCode() != null && !patientFilter.getTaxCode().isEmpty()) {
									searchFields.add(taxCode);
									params.add(patientFilter.getTaxCode());
								}
							}
							
							selectFields.add((String) patientFields.getCampoTracciato());
							
							boolean founded = false;
							for(FormFlowTableDTO table : configuration.getFlowTableList()) {
								for(FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
									if(field.getName().equalsIgnoreCase((String)patientFields.getCampoTracciato())) {
										descrFields.add(field.getDescriptionsm() != null ? field.getDescriptionsm() : field.getDescription());
										
										founded = true;
									}
									if(founded) break;
								}
								if(founded) break;
							}
						}
					}
				}

				for(int i=0; i<searchFields.size(); i++) {
					boolean founded = false;
					for(FormFlowTableDTO table : configuration.getFlowTableList()) {
						for(FormFlowTableFieldDTO field: table.getFlowTableFieldList()) {
							if(searchFields.get(i).equalsIgnoreCase(field.getName()) && field.isCrypto()) {
								if("Date".equals(field.getFieldType())) {
									params.set(i, cryptoManager.encryptDate((Date)params.get(i)));
								}else {
									params.set(i, cryptoManager.encryptString((String)params.get(i)));
								}
							founded = true;
							}
							if(founded) break;
						}
						if(founded) break;
					}
				}
				
				
				descrFields.add("Mese");
				descrFields.add("Anno");
				descrFields.add("Occorrenze");
				
				columnFields.addAll(selectFields);
				columnFields.add("MESE");
				columnFields.add("ANNO");
				columnFields.add("OCCORRENZE");
				
				selectFields.add("MONTH_RIF");
				selectFields.add("YEAR_RIF");

				try {
					if (searchFields != null && !searchFields.isEmpty()) {
						String queryString = QueryGenerator.generateSearchPatientQuery(flow.getName(), searchFields, selectFields, true, aziende);
						
						List<Object[]> list = flowViewDAO.executeSearchQuery(queryString, params);
						if(list != null && !list.isEmpty()) {
							//GESTIONE VALORI CRIPTATI DA DECRIPTARE
							for(int j=0; j<list.size(); j++) {
								for(int i=0; i<selectFields.size(); i++) {
									boolean founded = false;
									for(FormFlowTableDTO table : configuration.getFlowTableList()) {
										for(FormFlowTableFieldDTO field: table.getFlowTableFieldList()) {
											if(selectFields.get(i).equalsIgnoreCase(field.getName()) && field.isCrypto()) {
												if("Date".equals(field.getFieldType())) {
													try {
														list.get(j)[i] = cryptoManager.decryptDate((String)list.get(j)[i]);
													} catch (ParseException e) {
														LogUtil.logException(LOGGER, "", e);
//														e.printStackTrace();
													}
												}else {
													list.get(j)[i] = cryptoManager.decryptString((String)list.get(j)[i]);
												}
												founded = true;
											}
											if(founded) break;
										}
										if(founded) break;
									}
								}
							}
							SearchPatientDTO patient = new SearchPatientDTO();
							patient.setFlow(configuration);
							
							patient.setColumnDescriptions(descrFields);
							patient.setColumns(columnFields);
							patient.setRows(list);
							result.add(patient);
						}
					}
				} catch (Exception ex) {
					LogUtil.logException(LOGGER, "", ex);
//					ex.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.SEARCH, category = AuditEventCategoryEnum.ACCESS_LOG, description="Ricerca di un paziente", converter= FlowPatientSearchConverter.class, entity= EntityEnum.PAZIENTE, entityType= EntityTypeEnum.ASSISTITO)
	public List<SearchPatientDTO> sendAuditRicAssToPM(SearchPatientDTO patientFilter, List<SearchPatientDTO> list) {
		return list;
	}
		
	@Override
	public SearchPatientDTO searchFlowPatient(SearchFlowPatientFilter filter, HashMap<String,String> campiPraticaSubjectGen) {
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		
		SearchPatientDTO patient = new SearchPatientDTO();
		
		Boolean check = flowManagerProfileService.checkFlowByName(filter.getFlow().getName());
		if(!check) {
			return patient;
		}
		
		List<String> pkFields = new ArrayList<String>();
		List<String> searchFields = new ArrayList<String>();
		List<Object> searchValues = new ArrayList<Object>();
		List<String> columns = new ArrayList<String>();
		List<String> columnDescriptions = new ArrayList<String>();
			
		for(String searchField : filter.getFilters().keySet()) {
			if(searchField.equals("OCCORRENZE")) {
				continue;
			}
			if(searchField.equals("MESE")) {
				searchFields.add("MONTH_RIF");
			} else if(searchField.equals("ANNO")) {
				searchFields.add("YEAR_RIF");
			} else if(searchField.equals("NOME") || searchField.equals("COGNOME") || searchField.equals("DATANASCITA") || searchField.equals("CODICEFISCALE")) {
				campiPraticaSubjectGen.forEach((k,v)-> {
					if(v.equalsIgnoreCase(searchField)) {
						searchFields.add((String)v);
					}
				});
			} else {
				searchFields.add(searchField);
			}
						
			if((filter.getFilters().get(searchField)) instanceof Date) {
				Date dataNascita = (Date)filter.getFilters().get(searchField);
				Calendar calFrom = Calendar.getInstance();
				calFrom.setTime(dataNascita);
				calFrom.set(Calendar.HOUR_OF_DAY, 0);
				calFrom.set(Calendar.MINUTE, 0);
				calFrom.set(Calendar.SECOND, 0);
				calFrom.set(Calendar.MILLISECOND, 0);
				
				Calendar calTo = Calendar.getInstance();
				calTo.setTime(dataNascita);
				calTo.set(Calendar.HOUR_OF_DAY, 23);
				calTo.set(Calendar.MINUTE, 59);
				calTo.set(Calendar.SECOND, 59);
				calTo.set(Calendar.MILLISECOND, 999);
				
				searchValues.add(calFrom.getTime());
				searchValues.add(calTo.getTime());
			} else {
				searchValues.add(filter.getFilters().get(searchField));
			}
			
		}
		
		String flowName = filter.getFlow() != null ? filter.getFlow().getName() : null;
		FlowDO flow = (flowName == null || flowName.isBlank()) ? null : flowDAO.findByName(flowName).orElse(null);
		if (flow != null) {
			Set<FlowVersionDO> fvList = flow.getVersions();
			if (fvList != null && !fvList.isEmpty()) {
				FormFlowDTO cfgFlow = null;
				for (FlowVersionDO fv : fvList) {
					cfgFlow = retrieveConfiguration(fv.getFlow().getId(), fv.getVersion().getId());
					
					for(int i=0; i<searchFields.size(); i++) {
						boolean founded = false;
						for(FormFlowTableDTO table : cfgFlow.getFlowTableList()) {
							for(FormFlowTableFieldDTO field: table.getFlowTableFieldList()) {
								if(searchFields.get(i).equalsIgnoreCase(field.getName()) && field.isCrypto()) {
									if("Date".equals(field.getFieldType())) {
										searchValues.set(i,cryptoManager.encryptDate((Date)searchValues.get(i)));
									}else {
										searchValues.set(i,cryptoManager.encryptString((String)searchValues.get(i)));
									}
								founded = true;
								}
								if(founded) break;
							}
							if(founded) break;
						}
					}
					
					
					for(FormFlowTableDTO table : cfgFlow.getFlowTableList()) {
						if(table.getSection() == 0) {
							for(FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
								if(field.isPk()) {
									pkFields.add(field.getName());
									columnDescriptions.add(field.getDescriptionsm() != null ? field.getDescriptionsm() : field.getDescription());
									columns.add(field.getName());
								}
							}
							
							break;
						}
					}
					
					break;
				}
				
				String queryString = QueryGenerator.generateSearchPatientQuery(filter.getFlow().getName(), searchFields, pkFields, false, aziende);
				List<Object[]> list = flowViewDAO.executeSearchQuery(queryString, searchValues);
				patient.setFlow(cfgFlow);
				patient.setColumns(columns);
				patient.setColumnDescriptions(columnDescriptions);
				patient.setRows(list);
				
			}
		}
		
		return patient;
		
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Visualizzazione pratiche di un assistito", converter= FlowPatientGetConverter.class, entity= EntityEnum.PAZIENTE, entityType= EntityTypeEnum.ASSISTITO)
	public SearchPatientDTO sendAuditVisuaPraAssToPM(SearchFlowPatientFilter patientFilter, HashMap<String,String> campiPraticaSubjectGen, SearchPatientDTO searchPatientDTO) {
		return searchPatientDTO;
	}
	
	private FormFlowDTO retrieveConfiguration(String flowId, String versionId) {
		BaseSearchInput searchInput = new BaseSearchInput();
		searchInput.setValue("flowId", flowId);
		searchInput.setValue("versionId", versionId);
		Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFiltered(searchInput);
		if(searchResults.getFirst().isEmpty()) {
			return null;
		}
		FormFlowDTO configuration = searchResults.getFirst().get(0);
		return configuration;
	}

}
