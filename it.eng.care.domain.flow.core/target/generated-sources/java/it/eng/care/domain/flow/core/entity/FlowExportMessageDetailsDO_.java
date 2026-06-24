package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowExportMessageDetailsDO.class)
public abstract class FlowExportMessageDetailsDO_ {

	public static volatile SingularAttribute<FlowExportMessageDetailsDO, FlowExportRequestDO> flowExportRequest;
	public static volatile SingularAttribute<FlowExportMessageDetailsDO, String> errorValue;
	public static volatile SingularAttribute<FlowExportMessageDetailsDO, String> id;

	public static final String FLOW_EXPORT_REQUEST = "flowExportRequest";
	public static final String ERROR_VALUE = "errorValue";
	public static final String ID = "id";

}

