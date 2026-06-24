package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowVersionPrimaryKeyDO.class)
public abstract class FlowVersionPrimaryKeyDO_ {

	public static volatile SingularAttribute<FlowVersionPrimaryKeyDO, String> version;
	public static volatile SingularAttribute<FlowVersionPrimaryKeyDO, String> flow;

	public static final String VERSION = "version";
	public static final String FLOW = "flow";

}

