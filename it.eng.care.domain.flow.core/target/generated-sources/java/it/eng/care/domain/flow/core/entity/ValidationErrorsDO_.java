package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ValidationErrorsDO.class)
public abstract class ValidationErrorsDO_ {

	public static volatile SingularAttribute<ValidationErrorsDO, Integer> nretry;
	public static volatile SingularAttribute<ValidationErrorsDO, String> codiceAzienda;
	public static volatile SingularAttribute<ValidationErrorsDO, String> extrId;
	public static volatile SingularAttribute<ValidationErrorsDO, String> json;
	public static volatile SingularAttribute<ValidationErrorsDO, String> id;
	public static volatile SingularAttribute<ValidationErrorsDO, Date> creationDate;
	public static volatile SingularAttribute<ValidationErrorsDO, Date> dateSent;
	public static volatile SingularAttribute<ValidationErrorsDO, FlowDO> flowId;
	public static volatile SingularAttribute<ValidationErrorsDO, String> status;

	public static final String NRETRY = "nretry";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String EXTR_ID = "extrId";
	public static final String JSON = "json";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String DATE_SENT = "dateSent";
	public static final String FLOW_ID = "flowId";
	public static final String STATUS = "status";

}

