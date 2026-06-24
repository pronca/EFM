package it.eng.care.domain.flow.tabgen.dao;

import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenValueDAOImpl;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabgenValueDAO extends JpaRepository<TabgenValueDO, String> {

    @QueryByCriteria(TabgenValueDAOImpl.class)
    List<String> retriveColumnValueByColumnName(BaseSearchInput poInput);

}
