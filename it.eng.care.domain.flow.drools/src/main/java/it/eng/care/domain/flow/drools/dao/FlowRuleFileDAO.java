package it.eng.care.domain.flow.drools.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.drools.entity.FlowRuleDO;
import it.eng.care.domain.flow.drools.entity.FlowRuleFileDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;

public interface FlowRuleFileDAO extends JpaRepository<FlowRuleFileDO, String> {
	
	@QueryByCriteria(FlowRuleFileDAOImpl.class)
	public List<FlowRuleDO> searchFiles(String flowCode, String versionId, String type);
	
}
