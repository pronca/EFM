package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RegionErrorsDO.class)
public abstract class RegionErrorsDO_ {

	public static volatile SingularAttribute<RegionErrorsDO, String> regionStatus;
	public static volatile SingularAttribute<RegionErrorsDO, Integer> nretry;
	public static volatile SingularAttribute<RegionErrorsDO, String> codiceAzienda;
	public static volatile SingularAttribute<RegionErrorsDO, String> extrId;
	public static volatile SingularAttribute<RegionErrorsDO, String> json;
	public static volatile SingularAttribute<RegionErrorsDO, String> sendStatus;
	public static volatile SingularAttribute<RegionErrorsDO, String> id;
	public static volatile SingularAttribute<RegionErrorsDO, String> keyMessage;
	public static volatile SingularAttribute<RegionErrorsDO, Date> creationDate;
	public static volatile SingularAttribute<RegionErrorsDO, Date> dateSent;
	public static volatile SingularAttribute<RegionErrorsDO, FlowDO> flowId;

	public static final String REGION_STATUS = "regionStatus";
	public static final String NRETRY = "nretry";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String EXTR_ID = "extrId";
	public static final String JSON = "json";
	public static final String SEND_STATUS = "sendStatus";
	public static final String ID = "id";
	public static final String KEY_MESSAGE = "keyMessage";
	public static final String CREATION_DATE = "creationDate";
	public static final String DATE_SENT = "dateSent";
	public static final String FLOW_ID = "flowId";

}

