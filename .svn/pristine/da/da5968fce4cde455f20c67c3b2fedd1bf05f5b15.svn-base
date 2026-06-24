package it.eng.care.domain.flow.tabgen.dao.querySearch;

import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.platform.common.lang.StringUtils;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.HashSet;
import java.util.Set;

public class TabgenFieldDAOImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	public CriteriaQuery<TabgenFieldDO> getFieldByTabgenValueColumn(String tabgenId, String tabgenValueColumn) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TabgenFieldDO> criteriaQuery = builder.createQuery(TabgenFieldDO.class);
		Root<TabgenFieldDO> root = criteriaQuery.from(TabgenFieldDO.class);
		Set<Predicate> restrictions = new HashSet<Predicate>();
		
		if (!StringUtils.isEmpty(tabgenValueColumn)) {
			restrictions.add(builder.equal(root.get("tabgenValueColumn"), tabgenValueColumn.toUpperCase()));
		}
		if (!StringUtils.isEmpty(tabgenId)) {
			Join<Object, Object> tabgen = root.join("tabgen");
			restrictions.add(builder.equal(tabgen.get("id"), tabgenId));
		}

		criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
		criteriaQuery.distinct(true);
		return criteriaQuery;
		
	}
	
	public  CriteriaQuery<TabgenFieldDO> searchTabgenFieldByFilter(BaseSearchInput filter) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TabgenFieldDO> criteriaQuery = builder.createQuery(TabgenFieldDO.class);
		Root<TabgenFieldDO> root = criteriaQuery.from(TabgenFieldDO.class);
		
		Set<Predicate> restrictions = new HashSet<Predicate>();
		
		Integer progressive  = (Integer)filter.getValue("progressive");
		if(progressive != null) {
			restrictions.add(builder.equal(root.get("progressive"), progressive));
		}
		
		String notId  = (String)filter.getValue("notId");
		if(!StringUtils.isEmpty(notId)) {
			restrictions.add(builder.notEqual(root.get("id"), notId));
		}
		
		String tabgenValueColumn = (String)filter.getValue("tabgenValueColumn");
		if(!StringUtils.isEmpty(tabgenValueColumn)) {
			restrictions.add(builder.equal(root.get("tabgenValueColumn"), tabgenValueColumn));
		}
		
		String pk  = (String)filter.getValue("pk");
		if (pk != null) {
			restrictions.add(builder.equal(root.get("pk"), pk));
		}
		
		String tabgenId  = (String)filter.getValue("tabgenId");
		if(!StringUtils.isEmpty(tabgenId)) {
			Join<Object, Object> tabgen = root.join("tabgen");
			restrictions.add(builder.equal(tabgen.get("id"), tabgenId.toUpperCase()));
		}
		
		criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
		criteriaQuery.distinct(true);
		
		criteriaQuery.orderBy(builder.asc(root.get("progressive")));
		
		return criteriaQuery;
		
	}

}
