package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.FlowRegionUnionDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowRegionUnionDAO extends JpaRepository<FlowRegionUnionDO, String> {

    @QueryByCriteria(FlowRegionUnionDAOQueryByBaseSearchInput.class)
    List<FlowRegionUnionDO> cerca(BaseSearchInput poInput);
}
