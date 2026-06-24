package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StateMachinePersistDO.class)
public abstract class StateMachinePersistDO_ {

	public static volatile SingularAttribute<StateMachinePersistDO, String> id;
	public static volatile SingularAttribute<StateMachinePersistDO, String> state;
	public static volatile SingularAttribute<StateMachinePersistDO, String> event;
	public static volatile SingularAttribute<StateMachinePersistDO, String> user;
	public static volatile SingularAttribute<StateMachinePersistDO, Date> dateStatus;

	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String EVENT = "event";
	public static final String USER = "user";
	public static final String DATE_STATUS = "dateStatus";

}

