package it.eng.care.domain.flow.tabgen.dao;

import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenFieldDAOImpl;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabgenFieldDAO extends JpaRepository<TabgenFieldDO, String> {
	
	@QueryByCriteria(TabgenFieldDAOImpl.class)
	public List<TabgenFieldDO> searchTabgenFieldByFilter(BaseSearchInput filter);

	@QueryByCriteria(TabgenFieldDAOImpl.class)
	public TabgenFieldDO getFieldByTabgenValueColumn(String tabgenId, String field);
	

}
