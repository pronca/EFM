package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowConfigurationFilterFieldValueDO.class)
public abstract class FlowConfigurationFilterFieldValueDO_ {

	public static volatile SingularAttribute<FlowConfigurationFilterFieldValueDO, FlowExportRequestDO> flowExportRequest;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldValueDO, FlowConfigurationFilterFieldDO> configurationFilterField;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldValueDO, String> id;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldValueDO, String> value;

	public static final String FLOW_EXPORT_REQUEST = "flowExportRequest";
	public static final String CONFIGURATION_FILTER_FIELD = "configurationFilterField";
	public static final String ID = "id";
	public static final String VALUE = "value";

}

