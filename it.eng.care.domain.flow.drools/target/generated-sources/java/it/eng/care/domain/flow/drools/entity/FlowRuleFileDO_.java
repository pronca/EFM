package it.eng.care.domain.flow.drools.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowRuleFileDO.class)
public abstract class FlowRuleFileDO_ {

	public static volatile SingularAttribute<FlowRuleFileDO, String> filename;
	public static volatile SingularAttribute<FlowRuleFileDO, String> ruleType;
	public static volatile SingularAttribute<FlowRuleFileDO, byte[]> rule;
	public static volatile SingularAttribute<FlowRuleFileDO, String> id;
	public static volatile SingularAttribute<FlowRuleFileDO, FlowRuleDO> flowRule;

	public static final String FILENAME = "filename";
	public static final String RULE_TYPE = "ruleType";
	public static final String RULE = "rule";
	public static final String ID = "id";
	public static final String FLOW_RULE = "flowRule";

}

