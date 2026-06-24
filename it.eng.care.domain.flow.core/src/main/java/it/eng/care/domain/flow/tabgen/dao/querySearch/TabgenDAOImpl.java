package it.eng.care.domain.flow.tabgen.dao.querySearch;

import java.util.List;
import java.util.Set;

import org.hibernate.internal.SessionImpl;
import org.hibernate.query.sql.spi.NativeQueryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.core.utility.PreparedWork;
import it.eng.care.domain.flow.tabgen.converter.TabgenDOToDTOWithFieldsConverter;
import it.eng.care.domain.flow.tabgen.converter.TabgenDOToDTOWithValuesConverter;
import it.eng.care.domain.flow.tabgen.dto.TabgenFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.platform.common.lang.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TabgenDAOImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TabgenDAOImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	
	public CriteriaQuery<TabgenDO> searchTabgenByFilter(TabgenFilter filter) throws DataAccessException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TabgenDO> criteriaQuery = builder.createQuery(TabgenDO.class);
		Root<TabgenDO> rootTabella = criteriaQuery.from(TabgenDO.class);
		manageFetchRule(filter.getFetchRule(), rootTabella);
		Set<Predicate> restrictions = Sets.newHashSet();

		if (!StringUtils.isEmpty(filter.getId())) {
			restrictions.add(builder.like(builder.upper(rootTabella.get("id")), "%" + filter.getId().toUpperCase() + "%"));
		}

		if (!StringUtils.isEmpty(filter.getDescription())) {
			restrictions.add(builder.like(builder.upper(rootTabella.get("description")), "%" + filter.getDescription().toUpperCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getType())) {
			restrictions.add(builder.like(builder.upper(rootTabella.get("type")), "%" + filter.getType().toUpperCase() + "%"));
		}

		if (filter.getVisible() != null) {
			restrictions.add(builder.equal(rootTabella.get("visible"), filter.getVisible()));
		}
		
		criteriaQuery = criteriaQuery
				.select(rootTabella)
				.where(restrictions.toArray(new Predicate[restrictions.size()]));
		criteriaQuery.distinct(true);
		return criteriaQuery;

	}
	
	public CriteriaQuery<TabgenDO> getTabgenGetById(String id, String fetchRule) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TabgenDO> criteriaQuery = builder.createQuery(TabgenDO.class);
		
		Root<TabgenDO> rootTabella = criteriaQuery.from(TabgenDO.class);
		manageFetchRule(fetchRule, rootTabella);		
		Set<Predicate> restrictions = Sets.newHashSet();
		restrictions.add(builder.equal(builder.upper(rootTabella.get("id")), id));
		
		criteriaQuery = criteriaQuery
			.select(rootTabella)
			.where(restrictions.toArray(new Predicate[restrictions.size()]));
		
		criteriaQuery.distinct(true);
		
		return criteriaQuery;
		
	}
	
	private void manageFetchRule(String fetchRule, Root<TabgenDO> rootTabella) {
		if(fetchRule != null && (fetchRule.equals(TabgenDOToDTOWithValuesConverter.getMapId()) || TabgenDOToDTOWithFieldsConverter.getMapId().equals(fetchRule))) {
			if(fetchRule.equals(TabgenDOToDTOWithValuesConverter.getMapId())) {
				Join<TabgenDO, TabgenValueDO> tabgenValues = rootTabella.join("tabgenValues", JoinType.LEFT);
			}
			Join<TabgenDO, TabgenFieldDO> tabgenFields = rootTabella.join("tabgenFields", JoinType.LEFT);
			Join<TabgenFieldDO, TabgenFieldDO> tabgenField = tabgenFields.join("tabgenField", JoinType.LEFT);
			Join<TabgenFieldDO, TabgenDO> tabgen = tabgenField.join("tabgen", JoinType.LEFT);
		}
	}
	
	private SessionImpl getSession() {
		return ((SessionImpl) entityManager.getDelegate());
	}
	
	public Integer executeSql(String sql) {
		try {
			PreparedWork myWork = new PreparedWork(sql);
			getSession().doWork(myWork);
			return 0;//myWork.getResult();
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
		}
		
		return -1;
	}
	
	public Boolean tableExists(String tablename) {
		try {
			String sql = "select 1 from " + tablename + " where 0 = 1";
			NativeQueryImplementor query = getSession().createNativeQuery(sql);
			query.list();
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
			return false;
		}
		
		return true;
	}
	
	public List<Object[]> executeSearchQuery(String queryString) {
		NativeQueryImplementor query = getSession().createNativeQuery(queryString);
		return query.list();
	}
	

}
