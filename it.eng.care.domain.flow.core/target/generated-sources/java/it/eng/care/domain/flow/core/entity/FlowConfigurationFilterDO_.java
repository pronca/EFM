package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowConfigurationFilterDO.class)
public abstract class FlowConfigurationFilterDO_ {

	public static volatile SingularAttribute<FlowConfigurationFilterDO, String> name;
	public static volatile SingularAttribute<FlowConfigurationFilterDO, String> id;
	public static volatile SetAttribute<FlowConfigurationFilterDO, FlowConfigurationFilterFieldDO> filterFields;
	public static volatile SingularAttribute<FlowConfigurationFilterDO, Integer> type;
	public static volatile SingularAttribute<FlowConfigurationFilterDO, String> version;
	public static volatile SetAttribute<FlowConfigurationFilterDO, FlowExportRequestDO> exportRequest;
	public static volatile SingularAttribute<FlowConfigurationFilterDO, String> flow;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String FILTER_FIELDS = "filterFields";
	public static final String TYPE = "type";
	public static final String VERSION = "version";
	public static final String EXPORT_REQUEST = "exportRequest";
	public static final String FLOW = "flow";

}

