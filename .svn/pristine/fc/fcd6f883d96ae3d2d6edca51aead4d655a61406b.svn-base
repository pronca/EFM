package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.StateDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateDAO extends JpaRepository<StateDO, String> {

    @QueryByCriteria(StateDAOQueryByBaseSearchInput.class)
    List<StateDO> cerca(BaseSearchInput poInput);

}
