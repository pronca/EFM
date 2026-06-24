package it.eng.care.domain.flow.jobs.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import it.eng.care.domain.flow.b2b.utils.EurekaClientService;
import it.eng.care.domain.flow.b2b.utils.JWTAuth;
import it.eng.care.domain.flow.core.controller.impl.FlowControllerImpl;
import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFlowDTO;
import it.eng.care.domain.flow.core.dao.DashboardConfigDAO;
import it.eng.care.domain.flow.core.dao.DashboardDAO;
import it.eng.care.domain.flow.core.dao.DashboardErrorsDAO;
import it.eng.care.domain.flow.core.dao.DashboardErrorsFilterDAO;
import it.eng.care.domain.flow.core.dao.DashboardFilterDAO;
import it.eng.care.domain.flow.core.dao.DashboardPraticheDAO;
import it.eng.care.domain.flow.core.dao.DashboardPraticheFilterDAO;
import it.eng.care.domain.flow.core.dao.IntegrationServiceDAO;
import it.eng.care.domain.flow.core.dao.VersionDAO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.DashboardConfigDO;
import it.eng.care.domain.flow.core.entity.DashboardDO;
import it.eng.care.domain.flow.core.entity.DashboardErrorsDO;
import it.eng.care.domain.flow.core.entity.DashboardFilterDO;
import it.eng.care.domain.flow.core.entity.DashboardPraticheDO;
import it.eng.care.domain.flow.core.entity.DashboardPraticheFilterDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.IntegrationServiceDO;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.jobs.dao.DashboardJobDAO;
import it.eng.care.domain.flow.jobs.service.api.DashboardJobService;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import jakarta.annotation.PostConstruct;

//@Transactional
public class DashboardJobServiceImpl implements DashboardJobService {

	@Autowired
	private FlowService flowService;

	@Autowired
	private FormFlowService formFlowService;

	@Autowired
	private DashboardJobDAO dashboardJobDAO;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private FlowDOtoFlowDTO flowDOToFlowDTO;

	@Autowired
	private DashboardDAO dashboardDAO;

	@Autowired
	private DashboardPraticheDAO dashboardPraticheDAO;

	@Autowired
	private DashboardPraticheFilterDAO dashboardPraticheFilterDAO;

	@Autowired
	private DashboardErrorsFilterDAO dashboardErrorsFilterDAO;

	@Autowired
	private DashboardFilterDAO dashboardFilterDAO;

	@Autowired
	private DashboardErrorsDAO dashboardErrorsDAO;

	@Autowired
	private DashboardConfigDAO dashboardConfigDAO;

	@Autowired
	private IntegrationServiceDAO integrationServiceDAO;

	@Autowired
	private EurekaClientService eurekaClientService;

	@Autowired
	protected JWTAuth auth;

	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@Autowired
	private VersionDAO versionDAO;
	
	@Autowired
	private TransactionTemplate txTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowControllerImpl.class);

	private String token;

	@PostConstruct
	public void post() {
		LOGGER.debug("FlowControllerImpl.post");
		conversionService.registerConverter(FlowDO.class, FlowDTO.class, flowDOToFlowDTO);
	}
	
	@Override
	public void dashboardCount(boolean jobYear) {
	    List<FlowDO> flows = flowService.findAll();
	    
	    for (FlowDO flow : flows) {
	      if (!"AZIENDA".equals(flow.getFlowType())) continue;

	      try {
	        txTemplate.executeWithoutResult(s -> dashboardCountOneFlow(jobYear, flow));
	      } catch (Throwable t) {
	    	LogUtil.logException(LOGGER, "Errore DashboardCount per flusso "+flow.getName()+"", t);
	      }
	    }
	  }

	public void dashboardCountOneFlow(boolean jobYear, FlowDO flow) {
		String versionId = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		versionId = versionDAO.findVersionIdsOrderByCreationDateDesc(flow.getId())
                .stream().findFirst().orElse(null);

		
		FormFlowDTO formFlowDTO = formFlowService.retrieveOne(flow.getId(), versionId);

		if (!jobYear) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			dashboardPratiche(flow, sdf, formFlowDTO, timestamp);

			dashboardErrors(flow, formFlowDTO, timestamp);

		} else {
			dashboardJobDAO.checkDashboardMeseAnno();
		}

		dashboard(jobYear, flow, formFlowDTO);
		
	}

	private List<DashboardConfigDO> dashboard(boolean jobYear, FlowDO flow, FormFlowDTO formFlowDTO) {
		
		//ELIMINO LE DASHBOARD PRESENTI PER IL FLUSSO DI RIFERIMENTO
		deleteDashboard(jobYear, flow);
		
		//carico la lista aziende coinvolte nel flusso di riferimento
		List<Object[]> aziendeFlowAll = dashboardJobDAO.getAziendeAll(formFlowDTO, false);
		List<String> aziende = new ArrayList<>();
		List<DashboardConfigDO> dashboardConfigListForAllAz = new ArrayList<>();
		if (aziendeFlowAll != null && !aziendeFlowAll.isEmpty()) {
			for(Object az: aziendeFlowAll){        
				aziende.add((String) az);        
			}
			//raggruppamento tutte le aziende del flusso coinvolte nel conteggio settimanale
			if (!aziende.isEmpty()) {
				aziende = aziende.stream().distinct().collect(Collectors.toList());
				//ciclo le aziende coinvolte nel conteggio settimanale controllando prima se hanno la gestione filtri sulla configurazione DASHBOARD_EXTRA
				for (String azienda : aziende) {
					List<DashboardConfigDO> dashboardConfigListFor = dashboardConfigDAO.findAllByFlowAndCodiceAzienda(flow, azienda);
					for (DashboardConfigDO dashboardConfig : dashboardConfigListFor) {
						if (dashboardConfig.getFlow() != null && flow.getCode().equals(dashboardConfig.getFlow().getCode())
								&& dashboardConfig.getCodiceAzienda() != null && azienda.equals(dashboardConfig.getCodiceAzienda())) {
							if (dashboardConfig.getQuery() != null) {
								if (!checkFilterDashboardExtra(jobYear, flow, formFlowDTO, dashboardConfig, azienda)) {
									generateDashboard(jobYear, flow, formFlowDTO, dashboardConfig, azienda);
								}
							} else {
								// SERVIZI INTEGRAZIONE
								generateDashboardServiziIntegraz(flow, dashboardConfig, azienda);
							}
						}
					}
					dashboardConfigListForAllAz.addAll(dashboardConfigListFor);
				}
				
				//controllo eventuali configurazioni dashboard senza azienda qualora la query viene condivisa da tutte le aziende
				List<DashboardConfigDO> dashboardConfigListFor = dashboardConfigDAO.findAllByFlowAndCodiceAziendaIsNull(flow);
				for (DashboardConfigDO dashboardConfig : dashboardConfigListFor) {
					if (dashboardConfig.getFlow() != null && flow.getCode().equals(dashboardConfig.getFlow().getCode())) {
						for (String azienda : aziende) {
							if (dashboardConfig.getQuery() != null) {
								if (!checkFilterDashboardExtra(jobYear, flow, formFlowDTO, dashboardConfig, azienda)) {
									generateDashboard(jobYear, flow, formFlowDTO, dashboardConfig, azienda);
								}
							}
						}
					}
				}
				dashboardConfigListForAllAz.addAll(dashboardConfigListFor);
			}
			
			return dashboardConfigListForAllAz;
		}
		return null;
		
	}
	
	private boolean checkFilterDashboardExtra (boolean jobYear, FlowDO flow, FormFlowDTO formFlowDTO, DashboardConfigDO dashboardConfig, String azienda) {
		boolean foundFilter = false;

		TabgenValueFilter filter = new TabgenValueFilter();
		filter.setTabgenId("DASHBOARD_EXTRA");

		BasePagingLoadResult<Tabgen> list = tabgenDelegate.searchValue(filter);

		if (list != null && list.getTotalLength() > 0 && list.getList() != null
				&& !list.getList().isEmpty()) {
			Tabgen dashboardExtra = list.getList().get(0);
			if (dashboardExtra != null && "DASHBOARD_EXTRA".equals(dashboardExtra.getId())) {
				List<TabgenValue> tabgenValues = dashboardExtra.getTabgenValues();
				if (tabgenValues != null && !tabgenValues.isEmpty()) {
					for (TabgenValue tabgenValue : tabgenValues) {
						String flowName = tabgenValue.getField1();
						String filterName = tabgenValue.getField2();
						String comboBoxTable = tabgenValue.getField3();
						String azFilter = (tabgenValue.getField5()!=null ? tabgenValue.getField5() : "");
						if (flow.getCode().equals(flowName) && azienda.equals(azFilter)) {
							foundFilter = true;

							filter = new TabgenValueFilter();
							filter.setTabgenId(comboBoxTable);
							BasePagingLoadResult<Tabgen> listValues = tabgenDelegate.searchValue(filter);

							if (listValues != null && listValues.getTotalLength() > 0
									&& listValues.getList() != null && !listValues.getList().isEmpty()) {
								Tabgen dashboardExtraValues = listValues.getList().get(0);
								List<TabgenValue> comboValues = dashboardExtraValues.getTabgenValues();
								for (TabgenValue value : comboValues) {
									generateDashboard(jobYear, flow, formFlowDTO, dashboardConfig,
											filterName, value.getField1(), azienda);
								}
							}
						}
					}
				}
			}
		}
		return foundFilter;
	}
	
	private void generateDashboardServiziIntegraz(FlowDO flow, DashboardConfigDO dashboardConfig, String azienda) {

	    IntegrationServiceDO integration = integrationServiceDAO
	        .findByFlussoAndNomeAndAbilitazioneAndCodiceAzienda(flow.getCode(), dashboardConfig.getName(), 1, azienda);

	    if (integration == null) return;

	    Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR);

	    String flowKey = flow.getCode(); // <-- scegli UNA chiave e usala sempre
	    String widget = integration.getNome();

	    URI eurekaName = eurekaClientService.retriveBalanceBaseURI(integration.getVerticale());
	    String url = eurekaName.toString() + integration.getUrl() + year;

	    HttpClient client = new DefaultHttpClient();
	    HttpGet get = new HttpGet(url);

	    if (token == null) {
	        try { token = auth.loginRestCall(); } catch (Exception e) { e.printStackTrace(); }
	    }
	    get.setHeader("Authorization", "Bearer " + token);

	    try {
	        HttpResponse response = client.execute(get);

	        if (response.getStatusLine().getStatusCode() == 401) {
	            get.abort();
	            token = auth.loginRestCall();
	            get = new HttpGet(url);
	            get.setHeader("Authorization", "Bearer " + token);
	            response = client.execute(get);
	        }

	        Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());

	        // 1) seed mesi 1..12 a 0 (solo se non esistono)
	        for (int m = 1; m <= 12; m++) {
	            boolean exists = dashboardDAO.existsByFlowAndWidgetNameAndMonthAndYearAndCodiceAzienda(
	                flowKey, widget, m, year, azienda
	            );

	            if (!exists) {
	                DashboardDO d = new DashboardDO();
	                d.setFlow(flowKey);
	                d.setWidgetName(widget);
	                d.setLabel(dashboardConfig.getLabel());
	                d.setValue("0");
	                d.setTooltip(dashboardConfig.getTooltip());
	                d.setMonth(m);
	                d.setYear(year);
	                d.setLastUpdate(lastUpdate);
	                d.setCodiceAzienda(azienda);
	                dashboardDAO.save(d);
	            }
	        }

	        // 2) se OK, aggiorna i mesi presenti nel JSON
	        if (response.getStatusLine().getStatusCode() == 200) {
	            String jsonObject = EntityUtils.toString(response.getEntity());
	            JSONArray jsonArray = new JSONArray(jsonObject);

	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject jsonObj = jsonArray.getJSONObject(i);
	                int month = Integer.parseInt(jsonObj.get("month").toString());
	                String count = jsonObj.get("count").toString();

	                DashboardDO d = dashboardDAO
	                    .findFirstByFlowAndWidgetNameAndMonthAndYearAndCodiceAzienda(flowKey, widget, month, year, azienda)
	                    .orElseGet(() -> {
	                        DashboardDO x = new DashboardDO();
	                        x.setFlow(flowKey);
	                        x.setWidgetName(widget);
	                        x.setMonth(month);
	                        x.setYear(year);
	                        x.setCodiceAzienda(azienda);
	                        return x;
	                    });

	                d.setLabel(dashboardConfig.getLabel());
	                d.setTooltip(dashboardConfig.getTooltip());
	                d.setValue(count);
	                d.setLastUpdate(lastUpdate);

	                dashboardDAO.save(d);
	            }

	        } else {
	            LOGGER.info("Error: " + response.getStatusLine().getStatusCode() + ", Servizio: " + url);
	        }

	    } catch (Exception e) {
	    	LogUtil.logException(LOGGER, "Invocazione servizio: " + url + " fallita", e);
	    }
	}

	
	private void generateDashboard(boolean jobYear, FlowDO flow, FormFlowDTO formFlowDTO,
			DashboardConfigDO dashboardConfig, String codiceAzienda) {
		generateDashboard(jobYear, flow, formFlowDTO, dashboardConfig, null, null, codiceAzienda);
	}
	
	private void generateDashboard(boolean jobYear, FlowDO flow, FormFlowDTO formFlowDTO,
	        DashboardConfigDO dashboardConfig, String filterName, String valueName, String codiceAzienda) {

	    List<Object[]> countQuery;
	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    // Lasciato invariato come nel metodo attuale
	    try {
		    countQuery = dashboardJobDAO.countQuery(formFlowDTO, dashboardConfig, jobYear, null, null, codiceAzienda);
	
		    if (countQuery == null || countQuery.isEmpty()) return;
	
		    for (int p = 0; p < countQuery.size(); p++) {
	
		        Object[] row = countQuery.get(p);
		        if (row == null || row.length < 3) continue; // sicurezza minima
	
		        // Se 3 colonne: mese, anno, conta
		        // Se 4 colonne: mese, anno, day, conta
		        final boolean hasDay = (row.length >= 4);
		        final int idxCount = hasDay ? 3 : 2;
		        final int idxDay   = hasDay ? 2 : -1;
	
		        String meseCut = "";
		        String value = "";
		        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
	
		        DashboardDO dashboardDO = new DashboardDO();
		        dashboardDO.setFlow(flow.getName());
		        dashboardDO.setWidgetName(dashboardConfig.getName());
		        dashboardDO.setLabel(dashboardConfig.getLabel());
		        dashboardDO.setTooltip(dashboardConfig.getTooltip());
	
		        // value (conta) con indice dinamico
		        try {
		            BigDecimal errorsBI = (BigDecimal) row[idxCount];
		            value = String.valueOf(errorsBI);
		        } catch (Exception e) {
		            BigInteger errorsBI = (BigInteger) row[idxCount];
		            value = String.valueOf(errorsBI);
		        }
	
		        if (value.length() > 3 && value.substring(value.length() - 3).equals(",00")) {
		            value = value.substring(0, value.length() - 3);
		        }
		        if (value.startsWith(",")) {
		            value = "0";
		        }
		        dashboardDO.setValue(value);
	
		        String mese = String.valueOf(row[0]);
		        if (mese.startsWith("0")) {
		            meseCut = mese.substring(1);
		            dashboardDO.setMonth(Integer.parseInt(meseCut));
		        } else {
		            dashboardDO.setMonth(Integer.parseInt(mese));
		        }
	
		        dashboardDO.setYear(Integer.parseInt(String.valueOf(row[1])));
	
		        // day SOLO se presente (query a 4 colonne)
		        if (hasDay) {
		            Object raw = row[idxDay];
		            Date day = null;
	
		            if (raw != null) {
		                if (raw instanceof java.sql.Timestamp ts) {
		                    day = new Date(ts.getTime());
		                } else if (raw instanceof java.sql.Date sd) {
		                    day = new Date(sd.getTime());
		                } else if (raw instanceof java.util.Date d) {
		                    day = d;
		                } else {
		                    String s = raw.toString().trim();
		                    // fallback se arriva una stringa: prova parsing
		                    // NB: qui mantengo la tua impostazione "mirata" senza cambiare troppo
		                    LocalDate ld = java.time.LocalDateTime.parse(s, fmt).toLocalDate();
		                    day = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		                }
		            }
	
		            if (day != null) {
		                dashboardDO.setDay(day);
		            }
		        }
	
		        dashboardDO.setLastUpdate(timestamp2);
		        dashboardDO.setCodiceAzienda(codiceAzienda);
		        
	//		DashboardDO filter2 = new DashboardDO();
	//		filter2.setFlow(flow.getCode());
	//		filter2.setWidgetName(dashboardConfig.getName());
	//		if (mese.startsWith("0")) {
	//			filter2.setMonth(Integer.parseInt(meseCut));
	//		} else {
	//			filter2.setMonth(Integer.parseInt(mese));
	//		}
	//		filter2.setYear(Integer.parseInt(String.valueOf(countQuery.get(p)[1])));
	//		filter2.setCodiceAzienda(codiceAzienda);
	//		filter2.setDay(day);
	//		
	//		List<DashboardDO> results2 = dashboardDAO.findAll(Example.of(filter2));
	//
	//		if (results2.size() > 0) {
	//			dashboardDO.setId(results2.get(0).getId());
	//		}
	
		        DashboardDO dash = dashboardDAO.save(dashboardDO);
	
		        if (filterName != null && valueName != null) {
		            DashboardFilterDO dashboardFilterDO =
		                    new DashboardFilterDO(filterName, valueName, dash.getId(), codiceAzienda);
		            dashboardFilterDAO.save(dashboardFilterDO);
		        }
		    }
	    } catch (Exception e) {
	    	LogUtil.logException(LOGGER, "Errore conteggio nella Dashboard "+dashboardConfig.getName()+" per il flusso "+formFlowDTO.getName()+"", e);
	    }

	}

	private void dashboardErrors(FlowDO flow, FormFlowDTO formFlowDTO, Timestamp timestamp) {
		
		//ELIMINO LE DASHBOARD ERRORS PRESENTI PER IL FLUSSO DI RIFERIMENTO
		deleteDashboardErrors(formFlowDTO);
		
		//carico la lista aziende coinvolte nel flusso di riferimento
		List<Object[]> aziendeFlowAll = dashboardJobDAO.getAziendeAll(formFlowDTO, false);
		List<String> aziende = new ArrayList<>();
		if (aziendeFlowAll != null && !aziendeFlowAll.isEmpty()) {
			for(Object az: aziendeFlowAll){        
				aziende.add((String) az);        
			}
			//raggruppamento tutte le aziende del flusso coinvolte nel conteggio settimanale
			if (null != aziende && !aziende.isEmpty()) {
				aziende = aziende.stream().distinct().collect(Collectors.toList());
				
				//ciclo le aziende coinvolte nel conteggio settimanale controllando prima se hanno la gestione filtri sulla configurazione DASHBOARD_EXTRA
				for (String azienda : aziende) {
					boolean foundFilter = false;
					TabgenValueFilter filter = new TabgenValueFilter();
					filter.setTabgenId("DASHBOARD_EXTRA");
					BasePagingLoadResult<Tabgen> list = tabgenDelegate.searchValue(filter);
					
					if (list != null && list.getTotalLength() > 0 && list.getList() != null && !list.getList().isEmpty()) {
						Tabgen dashboardExtra = list.getList().get(0);
						if (dashboardExtra != null && "DASHBOARD_EXTRA".equals(dashboardExtra.getId())) {
							List<TabgenValue> tabgenValues = dashboardExtra.getTabgenValues();
							if (tabgenValues != null && !tabgenValues.isEmpty()) {
								for (TabgenValue tabgenValue : tabgenValues) {
									String flowName = tabgenValue.getField1();
									String filterName = tabgenValue.getField2();
									String comboBoxTable = tabgenValue.getField3();
									String azFilter = (tabgenValue.getField5()!=null ? tabgenValue.getField5() : "");
									if (flow.getCode().equals(flowName) && azienda.equals(azFilter)) {
										foundFilter = true;

										filter = new TabgenValueFilter();
										filter.setTabgenId(comboBoxTable);
										BasePagingLoadResult<Tabgen> listValues = tabgenDelegate.searchValue(filter);

										if (listValues != null && listValues.getTotalLength() > 0 && listValues.getList() != null
												&& !listValues.getList().isEmpty()) {
											Tabgen dashboardExtraValues = listValues.getList().get(0);
											List<TabgenValue> comboValues = dashboardExtraValues.getTabgenValues();
											for (TabgenValue value : comboValues) {
												generateDashboardErrors(flow, formFlowDTO, timestamp, filterName,
														value.getField1(), azienda);
											}
										}
									}
								}
							}
						}
					}

					if (!foundFilter) {
						generateDashboardErrors(flow, formFlowDTO, timestamp, azienda);
					}
				}
			}
		}
	}

	private void generateDashboardErrors(FlowDO flow, FormFlowDTO formFlowDTO, Timestamp timestamp, String codiceAzienda) {
		generateDashboardErrors(flow, formFlowDTO, timestamp, null, null, codiceAzienda);
	}
	
	private void generateDashboardErrors(FlowDO flow, FormFlowDTO formFlowDTO, Timestamp timestamp, String filterName,
			String valueName, String codiceAzienda) {
		
		try {
			List<Object[]> countErrorsTop5 = dashboardJobDAO.countErrorsTop5(formFlowDTO, null, null, codiceAzienda);
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			int k = 0;
			while ((countErrorsTop5 != null) && (k < countErrorsTop5.size())) {
				String mese = (String) countErrorsTop5.get(k)[1];
				String year = (String) countErrorsTop5.get(k)[0];
				int count = 0;
				boolean exit = false;
				while (k < countErrorsTop5.size() && year.contentEquals((String) countErrorsTop5.get(k)[0])
						&& mese.equals((String) countErrorsTop5.get(k)[1]) && count < 5 && exit == false) {
					DashboardErrorsDO dashboardErrorsDO = new DashboardErrorsDO();
					
					dashboardErrorsDO.setFlowName(flow.getName());
					dashboardErrorsDO.setLastUpdate(timestamp);
					dashboardErrorsDO.setCodiceAzienda(codiceAzienda);
					String meseCut = "";
					if (mese.startsWith("0")) {
						meseCut = mese.substring(1);
						dashboardErrorsDO.setMonth(meseCut);
					} else {
						dashboardErrorsDO.setMonth(mese);
					}
					dashboardErrorsDO.setYear((String) countErrorsTop5.get(k)[0]);
					
					//gestione data validazione proveniente da query
					Object raw = countErrorsTop5.get(k)[2];
					if (raw!=null) {
						Date day;
						if (raw instanceof java.sql.Timestamp ts) {
						    day = new Date(ts.getTime());
						} else if (raw instanceof java.sql.Date sd) {
						    day = new Date(sd.getTime());
						} else if (raw instanceof java.util.Date d) {
						    day = d;
						} else {
						    // fallback SOLO se davvero arriva una stringa
						    String s = raw.toString().trim();
	
						    // prova prima formato JDBC "yyyy-MM-dd HH:mm:ss.S"
						    LocalDate ld = java.time.LocalDateTime.parse(s, fmt).toLocalDate();
	
						    day = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
						}
						dashboardErrorsDO.setDay(day);
					}
					
					dashboardErrorsDO.setError((String) countErrorsTop5.get(k)[3]);
					dashboardErrorsDO.setDescription((String) countErrorsTop5.get(k)[5]);
					BigInteger countBI = BigInteger.valueOf(((Number) countErrorsTop5.get(k)[4]).longValue());
					dashboardErrorsDO.setCountError(countBI.intValue());
	
					DashboardErrorsDO dash = dashboardErrorsDAO.save(dashboardErrorsDO);
	
					if (count == 4) {
						while (k < countErrorsTop5.size() - 1 && mese.contentEquals((String) countErrorsTop5.get(k + 1)[1])
								&& year.contentEquals((String) countErrorsTop5.get(k + 1)[0])) {
							k++;
						}
						exit = true;
					}
	
					count++;
					k++;
	
				}
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "Errore nel conteggio DashboardErrors per il flusso "+formFlowDTO.getName()+"", e);
		}
	}

	private void dashboardPratiche(FlowDO flow, SimpleDateFormat sdf, FormFlowDTO formFlowDTO, Timestamp timestamp) {
		//reimposto i valori a zero di tutta la settimana per tutte le aziende del flusso di riferimento
		resetDashboardPratiche(formFlowDTO, timestamp, flow, sdf);
		
		//carico la lista aziende coinvolte nel conteggio settimanale pratiche ricevute per il flusso di riferimento
		List<Object[]> aziendePraticheDayWeekly = dashboardJobDAO.getAziendeAll(formFlowDTO, true);
		List<String> aziendeWeekly = new ArrayList<>();
		if (aziendePraticheDayWeekly != null && !aziendePraticheDayWeekly.isEmpty()) {
			for(Object az: aziendePraticheDayWeekly){        
				aziendeWeekly.add((String) az);        
			}
			//raggruppamento tutte le aziende del flusso coinvolte nel conteggio settimanale
			if (null != aziendeWeekly && !aziendeWeekly.isEmpty()) {
				aziendeWeekly = aziendeWeekly.stream().distinct().collect(Collectors.toList());
				
				//ciclo le aziende coinvolte nel conteggio settimanale controllando prima se hanno la gestione filtri sulla configurazione DASHBOARD_EXTRA
				for (String azienda : aziendeWeekly) {
					boolean foundFilter = false;
					TabgenValueFilter filter = new TabgenValueFilter();
					filter.setTabgenId("DASHBOARD_EXTRA");
					BasePagingLoadResult<Tabgen> list = tabgenDelegate.searchValue(filter);
					if (list != null && list.getTotalLength() > 0 && list.getList() != null && !list.getList().isEmpty()) {
						Tabgen dashboardExtra = list.getList().get(0);
						if (dashboardExtra != null && "DASHBOARD_EXTRA".equals(dashboardExtra.getId())) {
							List<TabgenValue> tabgenValues = dashboardExtra.getTabgenValues();
							if (tabgenValues != null && !tabgenValues.isEmpty()) {
								for (TabgenValue tabgenValue : tabgenValues) {
									String flowName = tabgenValue.getField1();
									String filterName = tabgenValue.getField2();
									String comboBoxTable = tabgenValue.getField3();
									String azFilter = (tabgenValue.getField5()!=null ? tabgenValue.getField5() : "");
									if (flow.getCode().equals(flowName) && azienda.equals(azFilter)) {
										foundFilter = true;
										filter = new TabgenValueFilter();
										filter.setTabgenId(comboBoxTable);
										BasePagingLoadResult<Tabgen> listValues = tabgenDelegate.searchValue(filter);
										if (listValues != null && listValues.getTotalLength() > 0 && listValues.getList() != null
												&& !listValues.getList().isEmpty()) {
											Tabgen dashboardExtraValues = listValues.getList().get(0);
											List<TabgenValue> comboValues = dashboardExtraValues.getTabgenValues();
											for (TabgenValue value : comboValues) {
												generateDashboardPratiche(formFlowDTO, timestamp, flow, sdf, filterName,
														value.getField1(), azFilter);
											}
										}
									}
								}
							}
						}
					}
					
					if (!foundFilter) {
						generateDashboardPratiche(formFlowDTO, timestamp, flow, sdf, azienda);
					}
				}
			}
		}
	}
	
	private void generateDashboardPratiche(FormFlowDTO formFlowDTO, Timestamp timestamp, FlowDO flow,
			SimpleDateFormat sdf, String codiceAzienda) {
		generateDashboardPratiche(formFlowDTO, timestamp, flow, sdf, null, null, codiceAzienda);
	}
	
	private void generateDashboardPratiche(FormFlowDTO formFlowDTO, Timestamp timestamp, FlowDO flow,
			SimpleDateFormat sdf, String filterName, String valueName, String azienda) {
		
		List<Object[]> countPraticheDay = new ArrayList<>();
		
		//conto le pratiche della settimana
		try {
			countPraticheDay = dashboardJobDAO.countPraticheDay(formFlowDTO, filterName, valueName, azienda);
			if (countPraticheDay == null) return;
			
			List<Object[]> arrayApp = new ArrayList<>();
	
			arrayApp.addAll(countPraticheDay);
			countPraticheDay.clear();
			int d = 0;
	
			while (d < arrayApp.size()) {
				if (d == arrayApp.size() - 1) {
					countPraticheDay.add(arrayApp.get(d));
					d++;
				} else {
					Calendar cal = Calendar.getInstance();
					Date data1 = (Date) arrayApp.get(d)[1];
					String azienda1 = (String) arrayApp.get(d)[2];
					cal.setTime(data1);
					int a = cal.get(Calendar.DAY_OF_MONTH);
					Date data2 = (Date) arrayApp.get(d + 1)[1];
					String azienda2 = (String) arrayApp.get(d + 1)[2];
					cal.setTime(data2);
					int b = cal.get(Calendar.DAY_OF_MONTH);
					//se il record successivo è nello stesso giorno faccio la somma e registro un solo record 
					if (a == b && java.util.Objects.equals(azienda1, azienda2)) {
						int tot = 0;
						int e = d + 1;
						BigInteger count1 = BigInteger.valueOf(((Number) arrayApp.get(d)[0]).longValue());
						tot = count1.intValue();
						while (a == b && azienda1 == azienda2) {
							BigInteger count2 = BigInteger.valueOf(((Number) arrayApp.get(e)[0]).longValue());
							tot += count2.intValue();
							e++;
							if (e < arrayApp.size()) {
								data2 = (Date) arrayApp.get(e)[1];
								cal.setTime(data2);
								b = cal.get(Calendar.DAY_OF_MONTH);
							} else {
								break;
							}
						}
						countPraticheDay.add(new Object[] { tot, arrayApp.get(d)[1], arrayApp.get(d)[2] });
						d = e;
					} else {
						countPraticheDay.add(arrayApp.get(d));
						d++;
					}
				}
			}
			
			//aggiorno le pratiche della settimana per il flusso e l'azienda di riferimento
			if (countPraticheDay.size() != 0) {
				DashboardPraticheDO dashboardPraticheDO = new DashboardPraticheDO();
				dashboardPraticheDO.setFlowName(flow.getCode());
				dashboardPraticheDO.setLastUpdate(timestamp);
				dashboardPraticheDO.setCodiceAzienda(azienda);
				Method method = null;
				int i = 0;
				int j = 0;
				
				while (i < countPraticheDay.size() && j < 7) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -j);
					String oggi = sdf.format(cal.getTime());
					Date dataDB = (Date) countPraticheDay.get(i)[1];
					cal.setTime(dataDB);
					String dateApp = sdf.format(cal.getTime());
					if (dateApp.equals(oggi)) { // uguale a oggi - j
						BigInteger praticheBI = BigInteger.valueOf(((Number) countPraticheDay.get(i)[0]).longValue());
						try {
							method = DashboardPraticheDO.class.getMethod("setDay" + (j + 1), Integer.class);
							method.invoke(dashboardPraticheDO, new Integer(praticheBI.intValue()));
							i++;
							j++;
						} catch (Exception e) {
							LogUtil.logException(LOGGER, "", e);
//							e.printStackTrace();
						}
					} else {
						try {
							method = DashboardPraticheDO.class.getMethod("setDay" + (j + 1), Integer.class);
							method.invoke(dashboardPraticheDO, 0);
							j++;
						} catch (Exception e) {
							LogUtil.logException(LOGGER, "", e);
//							e.printStackTrace();
						}
					}
				}
				if (j < 7) {
					for (int h = j; h < 7; h++) {
						try {
							method = DashboardPraticheDO.class.getMethod("setDay" + (h + 1), Integer.class);
							method.invoke(dashboardPraticheDO, 0);
						} catch (Exception e) {
							LogUtil.logException(LOGGER, "", e);
//							e.printStackTrace();
						}
					}
				}
				
	//			DashboardPraticheDO filter = new DashboardPraticheDO();
	//			filter.setFlowName(flow.getCode());
	//			filter.setCodiceAzienda(azienda);
	//			List<DashboardPraticheDO> results = dashboardPraticheDAO.findAll(Example.of(filter));
	//			if (results.size() > 0) {
	//				dashboardPraticheDO.setId(results.get(0).getId());
	//			}
	//			
	//			DashboardPraticheDO dash = dashboardPraticheDAO.save(dashboardPraticheDO);
				
				dashboardPraticheDAO
			    .findFirstByFlowNameAndCodiceAzienda(flow.getCode(), azienda)
			    .ifPresent(existing -> dashboardPraticheDO.setId(existing.getId()));
	
				dashboardPraticheDAO.save(dashboardPraticheDO);
				
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "Errore nel conteggio DashboardPratiche per il flusso "+formFlowDTO.getName()+"", e);
		}
	}

	private void resetDashboardPratiche (FormFlowDTO formFlowDTO, Timestamp timestamp, FlowDO flow,	SimpleDateFormat sdf) {
		
		List<String> aziendeAll = new ArrayList<>();
		
		//ELIMINO EVENTUALI RECORD SULLA FM_DASHBOARD_PRATICHE CON CODICEAZIENDA A NULL ANCORA PRESENTI DALLA VECCHIA GESTIONE
		List<DashboardPraticheDO> resultToDel = dashboardPraticheDAO.findAllByFlowNameAndCodiceAziendaIsNull(formFlowDTO.getName());
		if (resultToDel.size() > 0) {
			for (DashboardPraticheDO result : resultToDel) {
				dashboardPraticheDAO.delete(result);
			}
		}
		
		//REIMPOSTO I VALORI DELLA SETTIMANA A ZERO SULLA FM_DASHBOARD_PRATICHE SU TUTTE LE AZIENDE DEL FLUSSO IN ESECUZIONE
		List<Object[]> aziendePraticheDayAll = dashboardJobDAO.getAziendeAll(formFlowDTO, false);
		if (aziendePraticheDayAll != null && !aziendePraticheDayAll.isEmpty()) {
			for(Object az: aziendePraticheDayAll){        
				aziendeAll.add((String) az);        
			}
			
			//raggruppamento tutte le aziende del flusso
			if (null != aziendeAll && !aziendeAll.isEmpty()) {
				aziendeAll = aziendeAll.stream().distinct().collect(Collectors.toList());
				
				for (String azAll : aziendeAll) {

				    DashboardPraticheDO dash = dashboardPraticheDAO
				        .findFirstByFlowNameAndCodiceAzienda(flow.getCode(), azAll)
				        .orElseGet(DashboardPraticheDO::new);

				    dash.setFlowName(flow.getCode());
				    dash.setCodiceAzienda(azAll);
				    dash.setLastUpdate(timestamp);

				    dash.setDay1(0);
				    dash.setDay2(0);
				    dash.setDay3(0);
				    dash.setDay4(0);
				    dash.setDay5(0);
				    dash.setDay6(0);
				    dash.setDay7(0);

				    dash = dashboardPraticheDAO.save(dash);
					
					//controllo presenza configurazione DASHBOARD_EXTRA per flusso e azienda di riferimento e quindi inserimento valori filtri su FM_DASHBOARD_PRATICHE_FILTER_VALUE
					TabgenValueFilter filterDE = new TabgenValueFilter();
					filterDE.setTabgenId("DASHBOARD_EXTRA");
					BasePagingLoadResult<Tabgen> list = tabgenDelegate.searchValue(filterDE);
					if (list != null && list.getTotalLength() > 0 && list.getList() != null && !list.getList().isEmpty()) {
						Tabgen dashboardExtra = list.getList().get(0);
						if (dashboardExtra != null && "DASHBOARD_EXTRA".equals(dashboardExtra.getId())) {
							List<TabgenValue> tabgenValues = dashboardExtra.getTabgenValues();
							if (tabgenValues != null && !tabgenValues.isEmpty()) {
								for (TabgenValue tabgenValue : tabgenValues) {
									String flowName = tabgenValue.getField1();
									String filterName = tabgenValue.getField2();
									String comboBoxTable = tabgenValue.getField3();
									String azFilter = (tabgenValue.getField5()!=null ? tabgenValue.getField5() : "");
									if (flow.getCode().equals(flowName) && azAll.equals(azFilter)) {
										filterDE = new TabgenValueFilter();
										filterDE.setTabgenId(comboBoxTable);
										BasePagingLoadResult<Tabgen> listValues = tabgenDelegate.searchValue(filterDE);
										if (listValues != null && listValues.getTotalLength() > 0 && listValues.getList() != null
												&& !listValues.getList().isEmpty()) {
											Tabgen dashboardExtraValues = listValues.getList().get(0);
											List<TabgenValue> comboValues = dashboardExtraValues.getTabgenValues();
											for (TabgenValue value : comboValues) {
												DashboardPraticheFilterDO dashboardPraticheFilterDO = new DashboardPraticheFilterDO(filterName, value.getField1(),
														dash.getId(), azAll);
												dashboardPraticheFilterDAO.save(dashboardPraticheFilterDO);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void deleteDashboardErrors (FormFlowDTO formFlowDTO) {
		//ELIMINO TUTTI I VALORI DELLE DASHBOARD ERRORI
		dashboardErrorsDAO.deleteByFlowEquals(formFlowDTO.getName());
	}
	
	private void deleteDashboard (boolean jobYear, FlowDO flow) {
		//ELIMINO EVENTUALI RECORD SULLA FM_DASHBOARD CON CODICEAZIENDA A NULL ANCORA PRESENTI DALLA VECCHIA GESTIONE
		dashboardDAO.deleteByFlowAndCodiceAziendaIsNull(flow.getName());
		
		//ELIMINO RECORD SULLA FM_DASHBOARD_FOR_DATE PER AGGIORNAMENTO CONTATORI
		List<Integer> anni = new ArrayList<>();
		List<Integer> mesi = new ArrayList<>();
		Integer mese;
		Integer meseScorso;
		Calendar cal = Calendar.getInstance();
		if(jobYear) {
			for(int i = 2018; i<=cal.get(Calendar.YEAR); i++) {
				anni.add(i);
			}
			for(int m = 1; m<=12; m++) {
				mesi.add(m);
			} 
		}else {
			int lastMonth = cal.get(Calendar.MONTH);
	    	int month = cal.get(Calendar.MONTH) + 1;
	    	mese = month;
	    	int lastYear = cal.get(Calendar.YEAR) -1;
	    	int year = cal.get(Calendar.YEAR);
	    	anni.add(year);
	    	if(lastMonth == 0) {
	    		lastMonth = 12;
	    		anni.add(lastYear);
	    		anni.add(year);
	    	}
	    	meseScorso = lastMonth;
	    	mesi.add(meseScorso);
	    	mesi.add(mese);
		}
		
		dashboardDAO.deleteByMonthsInAndYearsInAndFlowEquals(mesi, anni, flow.getName());
		
	}
}
