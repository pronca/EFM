package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowForeignKeyDO.class)
public abstract class FlowForeignKeyDO_ {

	public static volatile SingularAttribute<FlowForeignKeyDO, FlowTableFieldDO> idFieldTableReferenced;
	public static volatile SingularAttribute<FlowForeignKeyDO, FlowTableDO> idTable;
	public static volatile SingularAttribute<FlowForeignKeyDO, String> jsonField;
	public static volatile SingularAttribute<FlowForeignKeyDO, String> id;
	public static volatile SingularAttribute<FlowForeignKeyDO, FlowTableFieldDO> idFieldTable;
	public static volatile SingularAttribute<FlowForeignKeyDO, String> jsonFieldType;
	public static volatile SingularAttribute<FlowForeignKeyDO, Boolean> mandatory;
	public static volatile SingularAttribute<FlowForeignKeyDO, FlowTableDO> idTableReferenced;

	public static final String ID_FIELD_TABLE_REFERENCED = "idFieldTableReferenced";
	public static final String ID_TABLE = "idTable";
	public static final String JSON_FIELD = "jsonField";
	public static final String ID = "id";
	public static final String ID_FIELD_TABLE = "idFieldTable";
	public static final String JSON_FIELD_TYPE = "jsonFieldType";
	public static final String MANDATORY = "mandatory";
	public static final String ID_TABLE_REFERENCED = "idTableReferenced";

}

