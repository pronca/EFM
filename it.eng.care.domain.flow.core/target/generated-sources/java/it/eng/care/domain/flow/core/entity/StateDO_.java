package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StateDO.class)
public abstract class StateDO_ {

	public static volatile SingularAttribute<StateDO, String> subMachineId;
	public static volatile SingularAttribute<StateDO, String> initialState;
	public static volatile SingularAttribute<StateDO, String> machineId;
	public static volatile SingularAttribute<StateDO, String> kind;
	public static volatile SingularAttribute<StateDO, String> parentStateId;
	public static volatile SingularAttribute<StateDO, String> id;
	public static volatile SingularAttribute<StateDO, String> state;
	public static volatile SingularAttribute<StateDO, String> region;
	public static volatile SingularAttribute<StateDO, String> initialActionId;

	public static final String SUB_MACHINE_ID = "subMachineId";
	public static final String INITIAL_STATE = "initialState";
	public static final String MACHINE_ID = "machineId";
	public static final String KIND = "kind";
	public static final String PARENT_STATE_ID = "parentStateId";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String REGION = "region";
	public static final String INITIAL_ACTION_ID = "initialActionId";

}

