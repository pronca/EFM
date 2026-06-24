package it.eng.care.domain.flow.drools.dao;

import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.drools.entity.FlowRuleDO;

public class FlowRuleDAOImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	public CriteriaQuery<FlowRuleDO> getFlowRules(String flowId, String versionId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FlowRuleDO> criteriaQuery = builder.createQuery(FlowRuleDO.class);

		Set<Predicate> restrictions = Sets.newHashSet();
		
		Root<FlowRuleDO> root = criteriaQuery.from(FlowRuleDO.class);
		
		Join<FlowRuleDO, FlowDO> flow = root.join("flow", JoinType.LEFT);
		restrictions.add(builder.equal(flow.get("id"), flowId));
		
		Join<FlowRuleDO, VersionDO> version = root.join("version", JoinType.LEFT);
		restrictions.add(builder.equal(version.get("id"), versionId));
		
		criteriaQuery = criteriaQuery
				.select(root)
				.where(restrictions.toArray(new Predicate[restrictions.size()]));
		
		return criteriaQuery;
	}

}
