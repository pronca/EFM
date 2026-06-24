package it.eng.care.domain.flow.drools.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.drools.entity.FlowRuleDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;

public interface FlowRuleDAO extends JpaRepository<FlowRuleDO, String> {
	
	@QueryByCriteria(FlowRuleDAOImpl.class)
	public List<FlowRuleDO> getFlowRules(String flowId, String versionId);
	
	public Optional<FlowRuleDO> findFirstByFlow_IdAndVersion_Id(String flowId, String versionId);

}
