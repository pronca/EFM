package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SendDrgDO.class)
public abstract class SendDrgDO_ {

	public static volatile SingularAttribute<SendDrgDO, Integer> nretry;
	public static volatile SingularAttribute<SendDrgDO, String> extrId;
	public static volatile SingularAttribute<SendDrgDO, String> json;
	public static volatile SingularAttribute<SendDrgDO, String> id;
	public static volatile SingularAttribute<SendDrgDO, Date> creationDate;
	public static volatile SingularAttribute<SendDrgDO, Date> dateSent;
	public static volatile SingularAttribute<SendDrgDO, FlowDO> flowId;
	public static volatile SingularAttribute<SendDrgDO, String> status;

	public static final String NRETRY = "nretry";
	public static final String EXTR_ID = "extrId";
	public static final String JSON = "json";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String DATE_SENT = "dateSent";
	public static final String FLOW_ID = "flowId";
	public static final String STATUS = "status";

}

