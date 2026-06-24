package it.eng.care.domain.flow.jobs.dao;

import java.util.List;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.DashboardConfigDO;

public interface DashboardJobDAO {
	
	List<Object[]> countPraticheDay(FormFlowDTO formFlowDTO, String filterName, String valueName, String codiceAzienda);
	
	List<Object[]> countErrorsTop5(FormFlowDTO formFlowDTO, String filterName, String valueName, String codiceAzienda);
	
	List<Object[]> countQuery(FormFlowDTO formFlowDTO, DashboardConfigDO dashboardConfigDO, boolean jobYear, String filterName, String valueName, String codiceAzienda);
	
	void checkDashboardMeseAnno();
	
	List<Object[]> getAziendeAll(FormFlowDTO formFlowDTO, boolean onlyWeek);
	
}
