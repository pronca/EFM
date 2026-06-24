package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfiloFlussiPrimaryKeyDO.class)
public abstract class ProfiloFlussiPrimaryKeyDO_ {

	public static volatile SingularAttribute<ProfiloFlussiPrimaryKeyDO, String> site;
	public static volatile SingularAttribute<ProfiloFlussiPrimaryKeyDO, String> role;
	public static volatile SingularAttribute<ProfiloFlussiPrimaryKeyDO, String> organization;
	public static volatile SingularAttribute<ProfiloFlussiPrimaryKeyDO, String> flow;

	public static final String SITE = "site";
	public static final String ROLE = "role";
	public static final String ORGANIZATION = "organization";
	public static final String FLOW = "flow";

}

