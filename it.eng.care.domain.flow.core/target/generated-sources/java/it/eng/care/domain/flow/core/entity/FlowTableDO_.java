package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowTableDO.class)
public abstract class FlowTableDO_ {

	public static volatile SingularAttribute<FlowTableDO, VersionDO> versionDO;
	public static volatile SingularAttribute<FlowTableDO, String> name;
	public static volatile SingularAttribute<FlowTableDO, byte[]> xsd;
	public static volatile SingularAttribute<FlowTableDO, String> description;
	public static volatile SingularAttribute<FlowTableDO, Integer> section;
	public static volatile SingularAttribute<FlowTableDO, String> id;
	public static volatile SetAttribute<FlowTableDO, FlowTableFieldDO> fields;
	public static volatile SingularAttribute<FlowTableDO, FlowDO> flowDO;
	public static volatile SingularAttribute<FlowTableDO, DataSourceDO> dataSource;
	public static volatile SetAttribute<FlowTableDO, FlowForeignKeyDO> table;
	public static volatile SingularAttribute<FlowTableDO, Boolean> required;
	public static volatile SetAttribute<FlowTableDO, FlowForeignKeyDO> tableReferenced;

	public static final String VERSION_DO = "versionDO";
	public static final String NAME = "name";
	public static final String XSD = "xsd";
	public static final String DESCRIPTION = "description";
	public static final String SECTION = "section";
	public static final String ID = "id";
	public static final String FIELDS = "fields";
	public static final String FLOW_DO = "flowDO";
	public static final String DATA_SOURCE = "dataSource";
	public static final String TABLE = "table";
	public static final String REQUIRED = "required";
	public static final String TABLE_REFERENCED = "tableReferenced";

}

