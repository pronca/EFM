package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ResetValidationRequestDO.class)
public abstract class ResetValidationRequestDO_ {

	public static volatile SingularAttribute<ResetValidationRequestDO, String> month;
	public static volatile SingularAttribute<ResetValidationRequestDO, String> year;
	public static volatile SingularAttribute<ResetValidationRequestDO, Date> processingDate;
	public static volatile SingularAttribute<ResetValidationRequestDO, String> id;
	public static volatile SingularAttribute<ResetValidationRequestDO, Date> creationDate;
	public static volatile SingularAttribute<ResetValidationRequestDO, String> userId;
	public static volatile SingularAttribute<ResetValidationRequestDO, FlowDO> flow;
	public static volatile SingularAttribute<ResetValidationRequestDO, String> status;

	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String PROCESSING_DATE = "processingDate";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String USER_ID = "userId";
	public static final String FLOW = "flow";
	public static final String STATUS = "status";

}

