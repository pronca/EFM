package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.dao.querySearch.FlowConfigurationFilterFieldDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface FlowConfigurationFilterFieldDAO extends JpaRepository<FlowConfigurationFilterFieldDO, String> {

    @QueryByCriteria(FlowConfigurationFilterFieldDAOQueryByBaseSearchInput.class)
    List<FlowConfigurationFilterFieldDO> cerca(BaseSearchInput poInput);
    
}
