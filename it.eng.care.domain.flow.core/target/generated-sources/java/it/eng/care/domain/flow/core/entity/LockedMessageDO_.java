package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LockedMessageDO.class)
public abstract class LockedMessageDO_ {

	public static volatile SingularAttribute<LockedMessageDO, String> extractionId;
	public static volatile SingularAttribute<LockedMessageDO, String> codiceAzienda;
	public static volatile SingularAttribute<LockedMessageDO, String> extrId;
	public static volatile SingularAttribute<LockedMessageDO, Date> dateProcessed;
	public static volatile SingularAttribute<LockedMessageDO, String> id;
	public static volatile SingularAttribute<LockedMessageDO, String> keyMessage;
	public static volatile SingularAttribute<LockedMessageDO, Date> creationDate;
	public static volatile SingularAttribute<LockedMessageDO, String> message;
	public static volatile SingularAttribute<LockedMessageDO, FlowDO> flowId;
	public static volatile SingularAttribute<LockedMessageDO, String> flowName;
	public static volatile SingularAttribute<LockedMessageDO, String> status;

	public static final String EXTRACTION_ID = "extractionId";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String EXTR_ID = "extrId";
	public static final String DATE_PROCESSED = "dateProcessed";
	public static final String ID = "id";
	public static final String KEY_MESSAGE = "keyMessage";
	public static final String CREATION_DATE = "creationDate";
	public static final String MESSAGE = "message";
	public static final String FLOW_ID = "flowId";
	public static final String FLOW_NAME = "flowName";
	public static final String STATUS = "status";

}

