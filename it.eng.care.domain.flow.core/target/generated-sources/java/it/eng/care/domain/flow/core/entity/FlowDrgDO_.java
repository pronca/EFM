package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowDrgDO.class)
public abstract class FlowDrgDO_ {

	public static volatile SingularAttribute<FlowDrgDO, Date> returnDate;
	public static volatile SingularAttribute<FlowDrgDO, Date> sendDate;
	public static volatile SingularAttribute<FlowDrgDO, String> numPratiche;
	public static volatile SingularAttribute<FlowDrgDO, String> id;
	public static volatile SingularAttribute<FlowDrgDO, String> state;
	public static volatile SingularAttribute<FlowDrgDO, FlowExportRequestDO> extraction;
	public static volatile SingularAttribute<FlowDrgDO, String> error;

	public static final String RETURN_DATE = "returnDate";
	public static final String SEND_DATE = "sendDate";
	public static final String NUM_PRATICHE = "numPratiche";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String EXTRACTION = "extraction";
	public static final String ERROR = "error";

}

