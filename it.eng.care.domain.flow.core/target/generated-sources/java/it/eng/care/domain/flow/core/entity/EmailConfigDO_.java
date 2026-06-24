package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmailConfigDO.class)
public abstract class EmailConfigDO_ {

	public static volatile SingularAttribute<EmailConfigDO, String> id;
	public static volatile SingularAttribute<EmailConfigDO, String> flow;
	public static volatile SingularAttribute<EmailConfigDO, String> email;
	public static volatile SingularAttribute<EmailConfigDO, String> enabled;

	public static final String ID = "id";
	public static final String FLOW = "flow";
	public static final String EMAIL = "email";
	public static final String ENABLED = "enabled";

}

