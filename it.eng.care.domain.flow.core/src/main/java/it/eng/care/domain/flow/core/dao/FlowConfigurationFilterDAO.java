package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.dao.querySearch.FlowConfigurationFilterDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface FlowConfigurationFilterDAO extends JpaRepository<FlowConfigurationFilterDO, String> {

	@QueryByCriteria(FlowConfigurationFilterDAOQueryByBaseSearchInput.class)
    List<FlowConfigurationFilterDO> cerca(BaseSearchInput poInput);
	
	@QueryByCriteria(FlowConfigurationFilterDAOQueryByBaseSearchInput.class)
    List<FlowConfigurationFilterDO> cerca(BaseSearchInput poInput, Boolean useFlowProfile);
}
