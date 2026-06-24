package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StateMachinePersistHistoryDO.class)
public abstract class StateMachinePersistHistoryDO_ {

	public static volatile SingularAttribute<StateMachinePersistHistoryDO, String> entityId;
	public static volatile SingularAttribute<StateMachinePersistHistoryDO, String> id;
	public static volatile SingularAttribute<StateMachinePersistHistoryDO, String> state;
	public static volatile SingularAttribute<StateMachinePersistHistoryDO, String> event;
	public static volatile SingularAttribute<StateMachinePersistHistoryDO, String> user;
	public static volatile SingularAttribute<StateMachinePersistHistoryDO, Date> dateStatus;

	public static final String ENTITY_ID = "entityId";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String EVENT = "event";
	public static final String USER = "user";
	public static final String DATE_STATUS = "dateStatus";

}

