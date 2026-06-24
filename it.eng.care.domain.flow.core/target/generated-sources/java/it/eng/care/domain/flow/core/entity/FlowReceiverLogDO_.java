package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowReceiverLogDO.class)
public abstract class FlowReceiverLogDO_ {

	public static volatile SingularAttribute<FlowReceiverLogDO, Date> sendingDate;
	public static volatile SingularAttribute<FlowReceiverLogDO, String> id;
	public static volatile SingularAttribute<FlowReceiverLogDO, String> pk;
	public static volatile SingularAttribute<FlowReceiverLogDO, String> versionName;
	public static volatile SingularAttribute<FlowReceiverLogDO, String> flowName;
	public static volatile SingularAttribute<FlowReceiverLogDO, String> operation;

	public static final String SENDING_DATE = "sendingDate";
	public static final String ID = "id";
	public static final String PK = "pk";
	public static final String VERSION_NAME = "versionName";
	public static final String FLOW_NAME = "flowName";
	public static final String OPERATION = "operation";

}

