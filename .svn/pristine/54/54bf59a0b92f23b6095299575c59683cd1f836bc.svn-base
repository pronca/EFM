package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.DashboardConfigDO;
import it.eng.care.domain.flow.core.entity.FlowDO;

@Repository
public interface DashboardConfigDAO extends JpaRepository<DashboardConfigDO, String> {
	
	List<DashboardConfigDO> findAllByFlowAndBadgeAndTipoOrderByName(FlowDO flow, String badge, String tipo);
	
	List<DashboardConfigDO> findAllByFlowAndCodiceAzienda(FlowDO flow, String codiceAzienda);
	
	List<DashboardConfigDO> findAllByFlowAndCodiceAziendaIn(FlowDO flow, List<String> aziende);
	
	List<DashboardConfigDO> findAllByFlow(FlowDO flow);
	
	List<DashboardConfigDO> findAllByFlowAndCodiceAziendaIsNull(FlowDO flow);
	
	List<DashboardConfigDO> findAllByCodiceAziendaIn(List<String> aziende);
	
	List<DashboardConfigDO> findAllByFlowAndBadgeAndTipoAndCodiceAziendaInOrderByName(FlowDO flow, String badge, String tipo, List<String> aziende);
	
	List<DashboardConfigDO> findAllByFlowAndBadgeAndTipoAndCodiceAziendaIsNullOrderByName(FlowDO flow, String badge, String tipo);
	
}