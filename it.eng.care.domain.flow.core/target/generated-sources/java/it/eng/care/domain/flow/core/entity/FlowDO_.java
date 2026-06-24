package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowDO.class)
public abstract class FlowDO_ {

	public static volatile SingularAttribute<FlowDO, String> yearlyDeadline;
	public static volatile SingularAttribute<FlowDO, String> code;
	public static volatile SingularAttribute<FlowDO, String> descrb;
	public static volatile SingularAttribute<FlowDO, String> description;
	public static volatile SingularAttribute<FlowDO, String> userCreation;
	public static volatile SingularAttribute<FlowDO, Date> dataCreation;
	public static volatile SingularAttribute<FlowDO, Boolean> uniqueness;
	public static volatile SetAttribute<FlowDO, FlowVersionDO> versions;
	public static volatile SetAttribute<FlowDO, ProfiloFlussiDO> profiloFlussi;
	public static volatile SingularAttribute<FlowDO, String> name;
	public static volatile SingularAttribute<FlowDO, Boolean> scheduling;
	public static volatile SingularAttribute<FlowDO, String> id;
	public static volatile SingularAttribute<FlowDO, String> periodicy;
	public static volatile SingularAttribute<FlowDO, String> monthlyDeadline;
	public static volatile SingularAttribute<FlowDO, String> commProt;
	public static volatile SingularAttribute<FlowDO, Boolean> remoteSend;
	public static volatile SingularAttribute<FlowDO, Boolean> status;
	public static volatile SingularAttribute<FlowDO, String> flowType;

	public static final String YEARLY_DEADLINE = "yearlyDeadline";
	public static final String CODE = "code";
	public static final String DESCRB = "descrb";
	public static final String DESCRIPTION = "description";
	public static final String USER_CREATION = "userCreation";
	public static final String DATA_CREATION = "dataCreation";
	public static final String UNIQUENESS = "uniqueness";
	public static final String VERSIONS = "versions";
	public static final String PROFILO_FLUSSI = "profiloFlussi";
	public static final String NAME = "name";
	public static final String SCHEDULING = "scheduling";
	public static final String ID = "id";
	public static final String PERIODICY = "periodicy";
	public static final String MONTHLY_DEADLINE = "monthlyDeadline";
	public static final String COMM_PROT = "commProt";
	public static final String REMOTE_SEND = "remoteSend";
	public static final String STATUS = "status";
	public static final String FLOW_TYPE = "flowType";

}

