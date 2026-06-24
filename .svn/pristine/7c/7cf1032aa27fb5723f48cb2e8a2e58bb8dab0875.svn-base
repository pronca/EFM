package it.eng.care.domain.flow.core.dao;


import it.eng.care.domain.flow.core.dao.querySearch.FlowTableDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowTableDAO extends JpaRepository<FlowTableDO, String> {

    @QueryByCriteria(FlowTableDAOQueryByBaseSearchInput.class)
    List<FlowTableDO> cerca(BaseSearchInput poInput);
    
    @Query("SELECT ft FROM FlowTableDO ft WHERE ft.name = ?1")
    FlowTableDO findByName(String name);
    
    @Query("SELECT ft FROM FlowTableDO ft WHERE ft.flowDO = ?1")
    List<FlowTableDO> getFlowTableByFlow(FlowDO flowId);

    @Query("SELECT ft FROM FlowTableDO ft WHERE ft.id = ?1")
    Optional<FlowTableDO> findById(String id);
}
