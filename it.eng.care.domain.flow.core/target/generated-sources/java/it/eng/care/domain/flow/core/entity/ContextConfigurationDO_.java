package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContextConfigurationDO.class)
public abstract class ContextConfigurationDO_ {

	public static volatile SingularAttribute<ContextConfigurationDO, String> activity;
	public static volatile SingularAttribute<ContextConfigurationDO, String> param;
	public static volatile SingularAttribute<ContextConfigurationDO, String> name;
	public static volatile SingularAttribute<ContextConfigurationDO, String> section;
	public static volatile SingularAttribute<ContextConfigurationDO, String> id;
	public static volatile SingularAttribute<ContextConfigurationDO, String> flow;

	public static final String ACTIVITY = "activity";
	public static final String PARAM = "param";
	public static final String NAME = "name";
	public static final String SECTION = "section";
	public static final String ID = "id";
	public static final String FLOW = "flow";

}

