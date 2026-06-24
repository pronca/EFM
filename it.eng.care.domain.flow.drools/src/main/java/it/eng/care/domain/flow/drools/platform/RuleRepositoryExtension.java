package it.eng.care.domain.flow.drools.platform;

import java.util.List;

import it.eng.care.platform.rules.impl.persist.model.RuleDO;
import it.eng.care.platform.rules.impl.persist.repository.RuleRepository;

public interface RuleRepositoryExtension extends RuleRepository {
	
	List<RuleDO> findByNames(List<String> ruleNameList);

}
