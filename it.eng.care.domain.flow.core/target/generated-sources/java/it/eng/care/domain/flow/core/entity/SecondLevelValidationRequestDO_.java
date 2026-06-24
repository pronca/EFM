package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SecondLevelValidationRequestDO.class)
public abstract class SecondLevelValidationRequestDO_ {

	public static volatile SingularAttribute<SecondLevelValidationRequestDO, String> month;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, String> year;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, Date> processingDate;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, String> id;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, Date> creationDate;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, String> userId;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, FlowDO> flow;
	public static volatile SingularAttribute<SecondLevelValidationRequestDO, String> status;

	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String PROCESSING_DATE = "processingDate";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String USER_ID = "userId";
	public static final String FLOW = "flow";
	public static final String STATUS = "status";

}

