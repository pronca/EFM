package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.dao.querySearch.FlowConfFilterFieldValueDAOQuery;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface FlowConfigurationFilterFieldValueDAO extends JpaRepository<FlowConfigurationFilterFieldValueDO, String> {

    @QueryByCriteria(FlowConfFilterFieldValueDAOQuery.class)
    void deleteBy(BaseSearchInput searchInput);

    @QueryByCriteria(FlowConfFilterFieldValueDAOQuery.class)
    List<FlowConfigurationFilterFieldValueDO> cerca(BaseSearchInput searchInput);
    
}
