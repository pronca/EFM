package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmailMessageConfigDO.class)
public abstract class EmailMessageConfigDO_ {

	public static volatile SingularAttribute<EmailMessageConfigDO, String> subject;
	public static volatile SingularAttribute<EmailMessageConfigDO, String> id;
	public static volatile SingularAttribute<EmailMessageConfigDO, String> text;
	public static volatile SingularAttribute<EmailMessageConfigDO, String> flow;

	public static final String SUBJECT = "subject";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String FLOW = "flow";

}

