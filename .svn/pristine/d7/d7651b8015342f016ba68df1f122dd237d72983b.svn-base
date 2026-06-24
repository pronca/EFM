package it.eng.care.domain.flow.tabgen.dao;

import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenDAOImpl;
import it.eng.care.domain.flow.tabgen.dto.TabgenFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabgenDAO extends JpaRepository<TabgenDO, String> {

    @QueryByCriteria(TabgenDAOImpl.class)
    TabgenDO getTabgenGetById(String id, String fetchRule);

    @QueryByCriteria(TabgenDAOImpl.class)
    List<TabgenDO> searchTabgenByFilter(TabgenFilter filter, Pageable pageable);

}
