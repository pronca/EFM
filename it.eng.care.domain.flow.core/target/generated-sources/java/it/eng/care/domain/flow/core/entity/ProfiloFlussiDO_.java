package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfiloFlussiDO.class)
public abstract class ProfiloFlussiDO_ {

	public static volatile SingularAttribute<ProfiloFlussiDO, String> site;
	public static volatile SingularAttribute<ProfiloFlussiDO, String> role;
	public static volatile SingularAttribute<ProfiloFlussiDO, String> consolidamento;
	public static volatile SingularAttribute<ProfiloFlussiDO, String> organization;
	public static volatile SingularAttribute<ProfiloFlussiDO, ProfiloFlussiPrimaryKeyDO> id;
	public static volatile SingularAttribute<ProfiloFlussiDO, FlowDO> flow;

	public static final String SITE = "site";
	public static final String ROLE = "role";
	public static final String CONSOLIDAMENTO = "consolidamento";
	public static final String ORGANIZATION = "organization";
	public static final String ID = "id";
	public static final String FLOW = "flow";

}

