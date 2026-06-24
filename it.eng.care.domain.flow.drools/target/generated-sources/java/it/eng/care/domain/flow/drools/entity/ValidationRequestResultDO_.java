package it.eng.care.domain.flow.drools.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ValidationRequestResultDO.class)
public abstract class ValidationRequestResultDO_ {

	public static volatile SingularAttribute<ValidationRequestResultDO, String> vrId;
	public static volatile SingularAttribute<ValidationRequestResultDO, String> importingRequestId;
	public static volatile SingularAttribute<ValidationRequestResultDO, Integer> valids;
	public static volatile SingularAttribute<ValidationRequestResultDO, Integer> warnings;
	public static volatile SingularAttribute<ValidationRequestResultDO, Date> validationDate;
	public static volatile SingularAttribute<ValidationRequestResultDO, Integer> errors;

	public static final String VR_ID = "vrId";
	public static final String IMPORTING_REQUEST_ID = "importingRequestId";
	public static final String VALIDS = "valids";
	public static final String WARNINGS = "warnings";
	public static final String VALIDATION_DATE = "validationDate";
	public static final String ERRORS = "errors";

}

