package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowConfigurationFilterFieldDO.class)
public abstract class FlowConfigurationFilterFieldDO_ {

	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, String> filterTable;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, String> name;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, Boolean> range;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, FlowConfigurationFilterDO> configurationFilter;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, String> id;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, String> position;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, String> filterType;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, String> filterField;
	public static volatile SingularAttribute<FlowConfigurationFilterFieldDO, Boolean> mandatory;

	public static final String FILTER_TABLE = "filterTable";
	public static final String NAME = "name";
	public static final String RANGE = "range";
	public static final String CONFIGURATION_FILTER = "configurationFilter";
	public static final String ID = "id";
	public static final String POSITION = "position";
	public static final String FILTER_TYPE = "filterType";
	public static final String FILTER_FIELD = "filterField";
	public static final String MANDATORY = "mandatory";

}

