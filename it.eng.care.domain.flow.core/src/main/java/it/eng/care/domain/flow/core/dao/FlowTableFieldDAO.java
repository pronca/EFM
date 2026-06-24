package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.FlowTableFieldDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowTableFieldDAO extends JpaRepository<FlowTableFieldDO, String> {

    @QueryByCriteria(FlowTableFieldDAOQueryByBaseSearchInput.class)
    List<FlowTableFieldDO> cerca(BaseSearchInput poInput);
    
    @QueryByCriteria(FlowTableFieldDAOQueryByBaseSearchInput.class)
    List<FlowTableFieldDO> cerca(BaseSearchInput poInput, Boolean userFlowProfile);
    
    @Query("SELECT ft FROM FlowTableFieldDO ft WHERE ft.name = ?1")
    FlowTableFieldDO findByName(String name);
    
}
