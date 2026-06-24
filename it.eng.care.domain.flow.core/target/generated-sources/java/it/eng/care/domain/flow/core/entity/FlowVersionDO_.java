package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowVersionDO.class)
public abstract class FlowVersionDO_ {

	public static volatile SingularAttribute<FlowVersionDO, String> jsonSchema;
	public static volatile SingularAttribute<FlowVersionDO, FlowVersionPrimaryKeyDO> id;
	public static volatile SingularAttribute<FlowVersionDO, VersionDO> version;
	public static volatile SingularAttribute<FlowVersionDO, FlowDO> flow;

	public static final String JSON_SCHEMA = "jsonSchema";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String FLOW = "flow";

}

