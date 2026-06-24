package it.eng.care.domain.flow.core.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.DashboardDAO;
import it.eng.care.domain.flow.core.dao.DashboardErrorsDAO;
import it.eng.care.domain.flow.core.dao.DashboardPraticheDAO;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dto.MotherDashboardDTO;
import it.eng.care.domain.flow.core.entity.DashboardDO;
import it.eng.care.domain.flow.core.entity.DashboardErrorsDO;
import it.eng.care.domain.flow.core.entity.DashboardErrorsFilterDO;
import it.eng.care.domain.flow.core.entity.DashboardFilterDO;
import it.eng.care.domain.flow.core.entity.DashboardPraticheDO;
import it.eng.care.domain.flow.core.entity.DashboardPraticheFilterDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.service.DashboardService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.tabgen.utility.TabgenUtility;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardDAO dashboardDAO;

	@Autowired
	private DashboardPraticheDAO dashboardPraticheDAO;

	@Autowired
	private DashboardErrorsDAO dashboardErrorsDAO;
	
	@Autowired
	private FlowDAO flowDAO;
	
	@Autowired
    private FlowManagerProfileService flowManagerProfileService;
	
	@Autowired
	private ConversionService conversionService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServiceImpl.class);
	
	@Override
	public Pair<List<DashboardDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
		String flow = searchInput.getValue("flow");
		Integer month = searchInput.getValue("month");
		Integer year = searchInput.getValue("year");
		String widget_name = searchInput.getValue("widget_name");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		List<Map<String, String>> extraFilter = searchInput.getValue("extraFilter");
		Boolean canViewMonthFromToFilters = searchInput.getValue("canViewMonthFromToFilters");
		Boolean canViewDateFromToFilters = searchInput.getValue("canViewDateFromToFilters");
		Integer monthTo = searchInput.getValue("valueFilterMonthTo");
		Integer monthFrom = searchInput.getValue("valueFilterMonthFrom");
		String dateFromString = null;
		String dateToString = null;
		if (canViewDateFromToFilters) {
			//se 1 flusso è parametrizzato per entrambi le configurazioni prevale quella per range di data
			canViewMonthFromToFilters = false;
			Object rawFrom = searchInput.getValue("valueFilterDateFrom");
			Object rawTo   = searchInput.getValue("valueFilterDateTo");
			dateFromString = TabgenUtility.toDdMMyyyy(rawFrom);
			dateToString   = TabgenUtility.toDdMMyyyy(rawTo);
		}
		Date dateFrom = null;
		Date dateTo = null;
		if (dateFromString!=null && dateToString!=null) {
			LocalDate ldtExtraDateFrom = LocalDate.parse(dateFromString, fmt);
		    LocalDate ldtExtraDateTo   = LocalDate.parse(dateToString, fmt);
		    dateFrom = Date.from(ldtExtraDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    LocalDateTime endOfDay = ldtExtraDateTo.atTime(23, 59, 59);
		    dateTo = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }
		
		DashboardDO dashboard = new DashboardDO();
		dashboard.setFlow(flow);
		dashboard.setMonth(month);
		dashboard.setYear(year);
		if (widget_name != null) {
			dashboard.setWidgetName(widget_name);
		}
		
		List<String> flowList = flowManagerProfileService.getFlows();
		if(flowList == null || flowList.isEmpty()) {
			return Pair.of(new ArrayList<DashboardDO>(), SearchInfos.create());
		}
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		List<DashboardDO> results = new ArrayList<>();
		if (Boolean.TRUE.equals(canViewDateFromToFilters)) {
			if (widget_name != null) {
				if (!aziende.isEmpty()) {
					results = dashboardDAO.findByFlowWidgetAziendeAndDateBetweenOrNull(dateFrom, dateTo, flow, widget_name, aziende);
				} else {
					results = dashboardDAO.findByFlowWidgetAndDateBetweenOrNull(dateFrom, dateTo, flow, widget_name);
				}
			} else {
				if (!aziende.isEmpty()) {
					results = dashboardDAO.findByFlowAziendeAndDateBetweenOrNull(dateFrom, dateTo, flow, aziende);
				} else {
					results = dashboardDAO.findByFlowAndDateBetweenOrNull(dateFrom, dateTo, flow);
				}
			}
		} else if (Boolean.TRUE.equals(canViewMonthFromToFilters)){
			if (widget_name != null) {
				if (!aziende.isEmpty()) {
					results = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(monthFrom, monthTo, year, flow, widget_name, aziende);
				} else {
					results = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(monthFrom, monthTo, year, flow, widget_name);
				}
			} else {
				if (!aziende.isEmpty()) {
					results = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndCodiceAziendaIn(monthFrom, monthTo, year, flow, aziende);
				} else {
					results = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlow(monthFrom, monthTo, year, flow);
				}
			}
		} else {
			if (widget_name != null) {
		        if (!aziende.isEmpty()) {
		            results = dashboardDAO.findAllByFlowAndWidgetNameAndMonthAndYearAndCodiceAziendaIn(
		                    flow, widget_name, month, year, aziende);
		        } else {
		            results = dashboardDAO.findAllByFlowAndWidgetNameAndMonthAndYearAndCodiceAziendaIsNull(
		                    flow, widget_name, month, year);
		        }
		    } else {
		        if (!aziende.isEmpty()) {
		            results = dashboardDAO.findAllByFlowAndMonthAndYearAndCodiceAziendaIn(
		                    flow, month, year, aziende);
		        } else {
		            results = dashboardDAO.findAllByFlowAndMonthAndYearAndCodiceAziendaIsNull(
		                    flow, month, year);
		        }
		    }
		}
		results = results.stream()
			    .collect(Collectors.groupingBy(DashboardDO::getWidgetName))
			    .values().stream()
			    .map(list -> {
			        DashboardDO first = list.get(0);

			        java.math.BigDecimal sum = list.stream()
			            .map(DashboardDO::getValue)
			            .filter(Objects::nonNull)
			            .map(String::trim)
			            .filter(s -> !s.isEmpty())
			            .map(s -> s.replace(",", ".")) // se arrivano numeri con virgola
			            .map(s -> {
			                try { return new java.math.BigDecimal(s); }
			                catch (NumberFormatException ex) { return java.math.BigDecimal.ZERO; }
			            })
			            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

			        DashboardDO out = new DashboardDO();
			        out.setFlow(first.getFlow());
			        out.setYear(first.getYear());
			        out.setMonth(first.getMonth());
			        out.setWidgetName(first.getWidgetName());
			        out.setCodiceAzienda(first.getCodiceAzienda());
			        out.setLabel(first.getLabel());
			        out.setTooltip(first.getTooltip());
			        // VALUE aggregato
			        out.setValue(sum.stripTrailingZeros().toPlainString());
			        // lastUpdate escluso dal raggruppamento
			        out.setLastUpdate(null);
			        // id: scegli qualcosa di stabile
			        out.setId(null);

			        return out;
			    })
			    .collect(Collectors.toList());
		
		List<DashboardDO> filteredResults = new ArrayList<DashboardDO>();
		if(results != null && !results.isEmpty()) {
			for (DashboardDO dashboardDO : results) {
				for(String flowName : flowList) {
					if(flowName.equals(dashboardDO.getFlow())) {
						filteredResults.add(dashboardDO);
					}
				}
			}
		}
		
		if (extraFilter != null && !extraFilter.isEmpty() && filteredResults != null && !filteredResults.isEmpty()) {
			boolean compareWithExtraFilter = false;
			for (Map<String, String> filter : extraFilter) {
				if (!compareWithExtraFilter && filter.get("selected") != null && !filter.get("selected").isEmpty()) {
					compareWithExtraFilter = true;
				}
			}
			
			if (compareWithExtraFilter) {
				List<DashboardDO> resultsFiltered = new ArrayList<>();
				for (DashboardDO result : filteredResults) {
					Set<DashboardFilterDO> filterList = result.getDashboardFilter();
					boolean match = true;
					for (Map<String, String> extra : extraFilter) {
						if (match && extra.get("selected") != null && !extra.get("selected").isEmpty()) {
							for (DashboardFilterDO filter : filterList) {
								if (match) {
									if (filter.getFilterName().equals(extra.get("campo"))) {
										if (!filter.getFilterValue().equals(extra.get("selected"))) {
											match = false;
										}
									}
								}
							}
						}
					}
					if(match) {
						resultsFiltered.add(result);
					}
				}
				return Pair.of(groupResult(resultsFiltered), SearchInfos.create());
			} else {
				return Pair.of(groupResult(filteredResults), SearchInfos.create());
			}
		}
		
		if (Boolean.TRUE.equals(canViewMonthFromToFilters) || Boolean.TRUE.equals(canViewDateFromToFilters)) {
			filteredResults = groupResult(filteredResults);
		}

		return Pair.of(filteredResults, SearchInfos.create());
	}

	private List<DashboardDO> groupResult(List<DashboardDO> filteredResults) {
		Map<String, List<DashboardDO>> widgetGrouped = filteredResults.stream().collect(Collectors.groupingBy(w -> w.getWidgetName()));
		List<DashboardDO> groupedResults = new ArrayList<DashboardDO>();
		widgetGrouped.forEach((widget, list) -> {
			double value = 0;
			for (DashboardDO d : list) {
				try {
					value += Integer.valueOf(d.getValue());
				} catch (NumberFormatException nfe) {
					value += Double.valueOf(d.getValue());
				}
			}

			DashboardDO first = new DashboardDO();
			first.setFlow(list.get(0).getFlow());
			first.setId(list.get(0).getId());
			first.setLabel(list.get(0).getLabel());
			first.setLastUpdate(list.get(0).getLastUpdate());
			first.setWidgetName(list.get(0).getWidgetName());
			first.setYear(list.get(0).getYear());
			first.setMonth(list.get(0).getMonth());
			first.setTooltip(list.get(0).getTooltip());
			first.setValue(String.valueOf(value).endsWith(".0") ? String.valueOf(value).replace(".0", "") : String.valueOf(value));
			
			groupedResults.add(first);
		});
		return groupedResults;
	}

	@Override
	public Pair<List<MotherDashboardDTO>, SearchInfo> retrieveAllMotherFiltered(BaseSearchInput searchInput) {
		Integer monthFrom = searchInput.getValue("monthFrom");
		Integer monthTo = searchInput.getValue("monthTo");
		Integer year = searchInput.getValue("year");
		Boolean status = searchInput.getValue("status");
		BaseSearchInput search = new BaseSearchInput();
		search.setParam("status", status);
		search.setParam("flowType", "AZIENDA");

		List<FlowDO> flowList = flowDAO.cercaInProfile(search);

		List<MotherDashboardDTO> md = new ArrayList<>();	
		for (FlowDO flow : flowList) {
			//caricamento lista aziende visibili dall'utente
			List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
			
			List<DashboardDO> resultsPraticheThisYear = new ArrayList<>();
			List<DashboardDO> resultsPraticheLastYear = new ArrayList<>();
			List<DashboardDO> resultTotalThisYear = new ArrayList<>();
			List<DashboardDO> resultsTotalLastYear = new ArrayList<>();
			List<DashboardDO> resultsPraticheRegionThisYear = new ArrayList<>();
			List<DashboardDO> resultsPraticheRegionLastYear = new ArrayList<>();
			List<DashboardDO> resultTotalRegionThisYear = new ArrayList<>();
			List<DashboardDO> resultsTotalRegionLastYear = new ArrayList<>();
			
			if (!aziende.isEmpty()) {
				//risultati aziendali
				resultsPraticheThisYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(monthFrom, monthTo, year, flow.getCode(),
								"PRATICHE", aziende);
				resultsPraticheLastYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(monthFrom, monthTo, (year - 1), flow.getCode(),
								"PRATICHE", aziende);
				resultTotalThisYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(
						monthFrom, monthTo, year, flow.getCode(), "RICAVI_TOTALI", aziende);
				resultsTotalLastYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(
						monthFrom, monthTo, (year - 1), flow.getCode(), "RICAVI_TOTALI", aziende);
				
				//risultati regionali
				resultsPraticheRegionThisYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(monthFrom, monthTo, year, flow.getCode(),
								"PRATICHE_RIC_REG", aziende);
				resultsPraticheRegionLastYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(monthFrom, monthTo, (year - 1), flow.getCode(),
								"PRATICHE_RIC_REG", aziende);
				resultTotalRegionThisYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(
						monthFrom, monthTo, year, flow.getCode(), "RICAVI_TOT_RIC_REG", aziende);
				resultsTotalRegionLastYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(
						monthFrom, monthTo, (year - 1), flow.getCode(), "RICAVI_TOT_RIC_REG", aziende);
			} else {
				//risultati aziendali
				resultsPraticheThisYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(monthFrom, monthTo, year, flow.getCode(),
								"PRATICHE");
				resultsPraticheLastYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(monthFrom, monthTo, (year - 1), flow.getCode(),
								"PRATICHE");
				resultTotalThisYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(
						monthFrom, monthTo, year, flow.getCode(), "RICAVI_TOTALI");
				resultsTotalLastYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(
						monthFrom, monthTo, (year - 1), flow.getCode(), "RICAVI_TOTALI");
				
				//risultati regionali
				resultsPraticheRegionThisYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(monthFrom, monthTo, year, flow.getCode(),
								"PRATICHE_RIC_REG");
				resultsPraticheRegionLastYear = dashboardDAO
						.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(monthFrom, monthTo, (year - 1), flow.getCode(),
								"PRATICHE_RIC_REG");
				resultTotalRegionThisYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(
						monthFrom, monthTo, year, flow.getCode(), "RICAVI_TOT_RIC_REG");
				resultsTotalRegionLastYear = dashboardDAO.findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(
						monthFrom, monthTo, (year - 1), flow.getCode(), "RICAVI_TOT_RIC_REG");
			}
			
			resultsPraticheThisYear.addAll(resultTotalThisYear);
			resultsPraticheLastYear.addAll(resultsTotalLastYear);
			
			resultsPraticheRegionThisYear.addAll(resultTotalRegionThisYear);
			resultsPraticheRegionLastYear.addAll(resultsTotalRegionLastYear);
			
			MotherDashboardDTO dash = new MotherDashboardDTO();

			Integer tot = 0;
			BigDecimal totFloat = new BigDecimal(0.0);
			
			Collections.sort(resultsPraticheThisYear, new Comparator<DashboardDO>() {
			    public int compare(DashboardDO d1, DashboardDO d2) {
			        return d1.getLastUpdate().compareTo(d2.getLastUpdate());
			    }
			});
			
			for (DashboardDO result : resultsPraticheThisYear) {
				if (result.getValue() != null) {
					if (result.getWidgetName().equals("PRATICHE")) {
						tot += Integer.parseInt(result.getValue());
					} else {
	
						totFloat = totFloat.add(new BigDecimal(result.getValue()));
	
					}
				}
				dash.setLastUpdate(result.getLastUpdate());
			}
			Integer tot2 = 0;
			BigDecimal totFloat2 = new BigDecimal(0.0);

			for (DashboardDO result : resultsPraticheLastYear) {
				if (result.getValue() != null) {
					if (result.getWidgetName().equals("PRATICHE")) {
						tot2 += Integer.parseInt(result.getValue());
					} else {
						totFloat2 = totFloat2.add(new BigDecimal(result.getValue()));
	
					}
				}
			}
			
			
		
			Integer tot3 = 0;
			BigDecimal totFloat3 = new BigDecimal(0.0);
			for (DashboardDO result : resultsPraticheRegionThisYear) {
				if (result.getValue() != null) {
					if (result.getWidgetName().equals("PRATICHE_RIC_REG")) {
						tot3 += Integer.parseInt(result.getValue());
					} else {
	
						totFloat3 = totFloat3.add(new BigDecimal(result.getValue()));
	
					}
				}
			}
			Integer tot4 = 0;
			BigDecimal totFloat4 = new BigDecimal(0.0);

			for (DashboardDO result : resultsPraticheRegionLastYear) {
				if (result.getValue() != null) {
					if (result.getWidgetName().equals("PRATICHE_RIC_REG")) {
						tot4 += Integer.parseInt(result.getValue());
					} else {
						totFloat4= totFloat4.add(new BigDecimal(result.getValue()));
	
					}
				}
			}
			
			NumberFormat formatter = new DecimalFormat("###.#####");
			String f = formatter.format(totFloat);
			String f2 = formatter.format(totFloat2);
			String f3 = formatter.format(totFloat3);
			String f4 = formatter.format(totFloat4);

			dash.setFlow(flow.getCode());
			dash.setCountPraticheThisYear(tot);
			dash.setCountPraticheLastYear(tot2);
			dash.setCountPrtaicheRegioneThisYear(tot3);
			dash.setCountPrtaicheRegioneLastYear(tot4);
			dash.setTotalThisYear(f);
			dash.setTotalLastYear(f2);
			dash.setTotalRegioneThisYear(f3);
			dash.setTotalRegioneLastYear(f4);

			md.add(dash);

		}

		return Pair.of(md, SearchInfos.create());

	}

	@Override
	public Pair<List<DashboardPraticheDO>, SearchInfo> retrieveAllFilteredForPratiche(BaseSearchInput searchInput) {
		String flow = searchInput.getValue("flowName");
		
		if(flow != null) {
			// se flow è null la ricerca per nome non restituirà risultati
			Boolean check = flowManagerProfileService.checkFlowByName(flow);
			if(!check) {
				return Pair.of(new ArrayList<DashboardPraticheDO>(), SearchInfos.create());
			}
		}
		
		BaseSearchInput input = new BaseSearchInput();
		input.setParam("nameNoLike", flow.replace("_REG", ""));
		List<FlowDO> flowLoc = flowDAO.cerca(input);
		List<DashboardPraticheDO> results = new ArrayList<>();
		if (flowLoc!=null && !flowLoc.isEmpty()) {
			//caricamento lista aziende visibili dall'utente
			List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
			if (!aziende.isEmpty()) {
				results = dashboardPraticheDAO.findAllByFlowNameAndCodiceAziendaIn(flowLoc.get(0).getName() ,aziende);
			} else {
				results = dashboardPraticheDAO.findAllByFlowName(flowLoc.get(0).getName());
			}
			List<Map<String, String>> extraFilter = searchInput.getValue("extraFilter");
			if (extraFilter != null && !extraFilter.isEmpty() && results != null && !results.isEmpty()) {
				boolean compareWithExtraFilter = false;
				for (Map<String, String> filter : extraFilter) {
					if (!compareWithExtraFilter && filter.get("selected") != null && !filter.get("selected").isEmpty()) {
						compareWithExtraFilter = true;
					}
				}
				
				if (compareWithExtraFilter) {
					List<DashboardPraticheDO> resultsFiltered = new ArrayList<>();
					for (DashboardPraticheDO result : results) {
						Set<DashboardPraticheFilterDO> praticheFilterList = result.getDashboardPraticheFilter();
						boolean match = true;
						for (Map<String, String> filter : extraFilter) {
							if (match && filter.get("selected") != null && !filter.get("selected").isEmpty()) {
								for (DashboardPraticheFilterDO praticheFilter : praticheFilterList) {
									if (match) {
										if (praticheFilter.getKey().getFilterName().equals(filter.get("campo"))) {
											if (!praticheFilter.getFilterValue().equals(filter.get("selected"))) {
												match = false;
											}
										}
									}
								}
							}
						}
						if(match) {
							resultsFiltered.add(result);
						}
					}
					return Pair.of(resultsFiltered, SearchInfos.create());
				} else {
					DashboardPraticheDO grouped = new DashboardPraticheDO();
					grouped.setLastUpdate(results.get(0).getLastUpdate());
					grouped.setFlowName(results.get(0).getFlowName());
					grouped.setId(results.get(0).getId());
					int day1 = 0;
					int day2 = 0;
					int day3 = 0;
					int day4 = 0;
					int day5 = 0;
					int day6 = 0;
					int day7 = 0;
					for (DashboardPraticheDO r : results) {
						day1 += r.getDay1(); 
						day2 += r.getDay2(); 
						day3 += r.getDay3(); 
						day4 += r.getDay4(); 
						day5 += r.getDay5(); 
						day6 += r.getDay6(); 
						day7 += r.getDay7(); 
					}
					grouped.setDay1(day1);
					grouped.setDay2(day2);
					grouped.setDay3(day3);
					grouped.setDay4(day4);
					grouped.setDay5(day5);
					grouped.setDay6(day6);
					grouped.setDay7(day7);
					return Pair.of(Arrays.asList(grouped), SearchInfos.create());
				}
			}
		}
		
		return Pair.of(results, SearchInfos.create());
	}

	@Override
	public Pair<List<DashboardErrorsDO>, SearchInfo> retrieveAllFilteredForErrors(BaseSearchInput searchInput) {
		String flow = searchInput.getValue("flowName");
		String month = searchInput.getValue("month");
		String year = searchInput.getValue("year");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		List<Map<String, String>> extraFilter = searchInput.getValue("extraFilter");
		Boolean canViewMonthFromToFilters = searchInput.getValue("canViewMonthFromToFilters");
		Boolean canViewDateFromToFilters = searchInput.getValue("canViewDateFromToFilters");
		String monthTo = searchInput.getValue("valueFilterMonthTo") != null ? Integer.toString(searchInput.getValue("valueFilterMonthTo")) : null;
		String monthFrom = searchInput.getValue("valueFilterMonthFrom") != null ? Integer.toString(searchInput.getValue("valueFilterMonthFrom")) : null;
		String dateFromString = null;
		String dateToString = null;
		if (canViewDateFromToFilters) {
			//se 1 flusso è parametrizzato per entrambi le configurazioni prevale quella per range di data
			canViewMonthFromToFilters = false;
			Object rawFrom = searchInput.getValue("valueFilterDateFrom");
			Object rawTo   = searchInput.getValue("valueFilterDateTo");
			dateFromString = TabgenUtility.toDdMMyyyy(rawFrom);
			dateToString   = TabgenUtility.toDdMMyyyy(rawTo);
		}
		Date dateFrom = null;
		Date dateTo = null;
		if (dateFromString!=null && dateToString!=null) {
			LocalDate ldtExtraDateFrom = LocalDate.parse(dateFromString, fmt);
		    LocalDate ldtExtraDateTo   = LocalDate.parse(dateToString, fmt);
		    dateFrom = Date.from(ldtExtraDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    LocalDateTime endOfDay = ldtExtraDateTo.atTime(23, 59, 59);
		    dateTo = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }
		
		if(flow != null) {
			// se flow è null la ricerca per nome non restituirà risultati
			Boolean check = flowManagerProfileService.checkFlowByName(flow);
			if(!check) {
				return Pair.of(new ArrayList<DashboardErrorsDO>(), SearchInfos.create());
			}
		}
		
		//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		List<DashboardErrorsDO> results = new ArrayList<>();
		if (Boolean.TRUE.equals(canViewDateFromToFilters)) {
			if (!aziende.isEmpty()) {
				results = dashboardErrorsDAO.findByFlowNameAziendeAndDateBetweenOrNull(dateFrom, dateTo, flow, aziende);
			} else {
				results = dashboardErrorsDAO.findByFlowNameAndDateBetweenOrNull(dateFrom, dateTo, flow);
			}
		} else if (Boolean.TRUE.equals(canViewMonthFromToFilters)){
			if (!aziende.isEmpty()) {
				results = dashboardErrorsDAO.findAllByMonthBetweenAndFlowNameAndYearAndCodiceAziendaIn(monthFrom, monthTo, flow, year, aziende);
			} else {
				results = dashboardErrorsDAO.findAllByMonthBetweenAndFlowNameAndYear(monthFrom, monthTo, flow, year);
			}
		} else {
			if (!aziende.isEmpty()) {
				results = dashboardErrorsDAO.findAllByFlowNameAndMonthAndYearAndCodiceAziendaIn(flow, month, year, aziende);
			} else {
				results = dashboardErrorsDAO.findAllByFlowNameAndMonthAndYear(flow, month, year);
			}
		}
		
		//raggruppo i valori sommando il countError
		results = results.stream()
			    .collect(Collectors.groupingBy(DashboardErrorsDO::getError))
			    .values()
			    .stream()
			    .map(list -> {
			        DashboardErrorsDO first = list.get(0);
			        int sum = list.stream()
			            .map(DashboardErrorsDO::getCountError)
			            .filter(Objects::nonNull)
			            .mapToInt(Integer::intValue)
			            .sum();
			        DashboardErrorsDO out = new DashboardErrorsDO();
			        out.setError(first.getError());
			        out.setDescription(first.getDescription());
			        out.setFlowName(first.getFlowName());
			        out.setYear(first.getYear());
			        out.setMonth(first.getMonth());
			        out.setCodiceAzienda(first.getCodiceAzienda());
			        //sommatoria countError
			        out.setCountError(sum);
			        // lastUpdate escluso dal raggruppamento
			        out.setLastUpdate(null);
			        out.setId(null);
			        return out;
			    })
			    .collect(Collectors.toList());
		
		if (extraFilter != null && !extraFilter.isEmpty() && results != null && !results.isEmpty()) {
			boolean compareWithExtraFilter = false;
			for (Map<String, String> filter : extraFilter) {
				if (!compareWithExtraFilter && filter.get("selected") != null && !filter.get("selected").isEmpty()) {
					compareWithExtraFilter = true;
				}
			}
			
			if (compareWithExtraFilter) {
				List<DashboardErrorsDO> resultsFiltered = new ArrayList<>();
				for (DashboardErrorsDO result : results) {
					Set<DashboardErrorsFilterDO> errorsFilterList = result.getDashboardErrorsFilter();
					boolean match = true;
					for (Map<String, String> filter : extraFilter) {
						if (match && filter.get("selected") != null && !filter.get("selected").isEmpty()) {
							for (DashboardErrorsFilterDO errorsFilter : errorsFilterList) {
								if (match) {
									if (errorsFilter.getFilterName().equals(filter.get("campo"))) {
										if (!errorsFilter.getFilterValue().equals(filter.get("selected"))) {
											match = false;
										}
									}
								}
							}
						}
					}
					if(match) {
						resultsFiltered.add(result);
					}
				}
				return Pair.of(resultsFiltered, SearchInfos.create());
			} else {
				Map<String, List<DashboardErrorsDO>> errorGrouped = results.stream().collect(Collectors.groupingBy(e -> e.getError()));
				List<DashboardErrorsDO> groupedResults = new ArrayList<DashboardErrorsDO>();
				errorGrouped.forEach((error, list) -> {
					int countError = 0;
					for (DashboardErrorsDO d : list) {
						countError += d.getCountError().intValue();
					}

					DashboardErrorsDO first = new DashboardErrorsDO();
					first.setCountError(countError);
					first.setDescription(list.get(0).getDescription());
					first.setError(list.get(0).getError());
					first.setYear(list.get(0).getYear());
					first.setMonth(list.get(0).getMonth());
					first.setLastUpdate(list.get(0).getLastUpdate());
					first.setFlowName(list.get(0).getFlowName());
					first.setId(list.get(0).getMonth());
					
					groupedResults.add(first);
				
				});
				return Pair.of(groupedResults, SearchInfos.create());
			}
		}

		return Pair.of(results, SearchInfos.create());
	}

}
