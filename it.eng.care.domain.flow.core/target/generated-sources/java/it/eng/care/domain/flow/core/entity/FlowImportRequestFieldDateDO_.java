package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowImportRequestFieldDateDO.class)
public abstract class FlowImportRequestFieldDateDO_ {

	public static volatile SingularAttribute<FlowImportRequestFieldDateDO, String> id_field;
	public static volatile SingularAttribute<FlowImportRequestFieldDateDO, String> id;
	public static volatile SingularAttribute<FlowImportRequestFieldDateDO, Date> date_to;
	public static volatile SingularAttribute<FlowImportRequestFieldDateDO, Date> date_from;
	public static volatile SingularAttribute<FlowImportRequestFieldDateDO, FlowImportRequestDO> flowImportRequest;

	public static final String ID_FIELD = "id_field";
	public static final String ID = "id";
	public static final String DATE_TO = "date_to";
	public static final String DATE_FROM = "date_from";
	public static final String FLOW_IMPORT_REQUEST = "flowImportRequest";

}

