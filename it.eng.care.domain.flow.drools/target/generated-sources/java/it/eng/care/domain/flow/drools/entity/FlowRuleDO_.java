package it.eng.care.domain.flow.drools.entity;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowRuleDO.class)
public abstract class FlowRuleDO_ extends it.eng.care.platform.persistence.impl.jpa.AbstractEntity_ {

	public static volatile ListAttribute<FlowRuleDO, FlowRuleFileDO> files;
	public static volatile SingularAttribute<FlowRuleDO, VersionDO> version;
	public static volatile SingularAttribute<FlowRuleDO, FlowDO> flow;

	public static final String FILES = "files";
	public static final String VERSION = "version";
	public static final String FLOW = "flow";

}

