package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.DataSourceDAOueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataSourceDAO extends JpaRepository<DataSourceDO, String> {

    @QueryByCriteria(DataSourceDAOueryByBaseSearchInput.class)
    List<DataSourceDO> cerca(BaseSearchInput poInput);

}
