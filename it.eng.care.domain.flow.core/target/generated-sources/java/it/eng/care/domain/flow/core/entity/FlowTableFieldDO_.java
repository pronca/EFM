package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowTableFieldDO.class)
public abstract class FlowTableFieldDO_ {

	public static volatile SingularAttribute<FlowTableFieldDO, String> physicalSize;
	public static volatile SingularAttribute<FlowTableFieldDO, Boolean> enableCrypt;
	public static volatile SingularAttribute<FlowTableFieldDO, Integer> length;
	public static volatile SingularAttribute<FlowTableFieldDO, String> description;
	public static volatile SingularAttribute<FlowTableFieldDO, Boolean> groups;
	public static volatile SingularAttribute<FlowTableFieldDO, Boolean> active;
	public static volatile SetAttribute<FlowTableFieldDO, FlowForeignKeyDO> fieldTable;
	public static volatile SingularAttribute<FlowTableFieldDO, Boolean> isReferenceDate;
	public static volatile SingularAttribute<FlowTableFieldDO, Boolean> mandatory;
	public static volatile SingularAttribute<FlowTableFieldDO, String> regExp;
	public static volatile SingularAttribute<FlowTableFieldDO, String> descriptionsm;
	public static volatile SingularAttribute<FlowTableFieldDO, Boolean> isPk;
	public static volatile SingularAttribute<FlowTableFieldDO, FlowTableDO> flowTable;
	public static volatile SetAttribute<FlowTableFieldDO, FlowForeignKeyDO> fieldableReferenced;
	public static volatile SingularAttribute<FlowTableFieldDO, String> name;
	public static volatile SingularAttribute<FlowTableFieldDO, String> id;
	public static volatile SingularAttribute<FlowTableFieldDO, Integer> position;
	public static volatile SingularAttribute<FlowTableFieldDO, String> flowTableFieldId;
	public static volatile SingularAttribute<FlowTableFieldDO, String> fieldType;

	public static final String PHYSICAL_SIZE = "physicalSize";
	public static final String ENABLE_CRYPT = "enableCrypt";
	public static final String LENGTH = "length";
	public static final String DESCRIPTION = "description";
	public static final String GROUPS = "groups";
	public static final String ACTIVE = "active";
	public static final String FIELD_TABLE = "fieldTable";
	public static final String IS_REFERENCE_DATE = "isReferenceDate";
	public static final String MANDATORY = "mandatory";
	public static final String REG_EXP = "regExp";
	public static final String DESCRIPTIONSM = "descriptionsm";
	public static final String IS_PK = "isPk";
	public static final String FLOW_TABLE = "flowTable";
	public static final String FIELDABLE_REFERENCED = "fieldableReferenced";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String POSITION = "position";
	public static final String FLOW_TABLE_FIELD_ID = "flowTableFieldId";
	public static final String FIELD_TYPE = "fieldType";

}

