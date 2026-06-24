package it.eng.care.domain.flow.drools.dao;

import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.drools.entity.FlowRuleDO;
import it.eng.care.domain.flow.drools.entity.FlowRuleFileDO;

public class FlowRuleFileDAOImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	public CriteriaQuery<FlowRuleDO> searchFiles(String flowCode, String versionId, String type) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FlowRuleDO> criteriaQuery = builder.createQuery(FlowRuleDO.class);
		Root<FlowRuleDO> rootTabella = criteriaQuery.from(FlowRuleDO.class);
		criteriaQuery.distinct(true);
		Set<Predicate> restrictions = Sets.newHashSet();
		
		Join<FlowRuleDO, FlowRuleFileDO> files = rootTabella.join("files", JoinType.LEFT);
		Join<FlowRuleDO, FlowDO> flows = rootTabella.join("flow");
        Join<FlowRuleDO, VersionDO> versions = rootTabella.join("version");
        
        if(type != null) {
			 restrictions.add(builder.equal(files.get("ruleType"), type));
		}
        
        if (!StringUtils.isEmpty(flowCode)) {
            restrictions.add(builder.equal(flows.get("code"), flowCode));
        }
        
        if (!StringUtils.isEmpty(versionId)) {
            restrictions.add(builder.equal(versions.get("id"), versionId));
        }
        
        criteriaQuery
        .select(rootTabella)
        .where(restrictions.toArray(new Predicate[restrictions.size()]));
        
        return criteriaQuery;
	}
	
	
}
