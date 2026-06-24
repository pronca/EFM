package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobTalendDO.class)
public abstract class JobTalendDO_ {

	public static volatile SingularAttribute<JobTalendDO, Boolean> deleted;
	public static volatile SingularAttribute<JobTalendDO, Date> lastUpdateDate;
	public static volatile SingularAttribute<JobTalendDO, String> name;
	public static volatile SingularAttribute<JobTalendDO, String> packageJob;
	public static volatile SingularAttribute<JobTalendDO, String> description;
	public static volatile SingularAttribute<JobTalendDO, String> lastUpdateUser;
	public static volatile SingularAttribute<JobTalendDO, String> className;
	public static volatile SingularAttribute<JobTalendDO, String> id;
	public static volatile SingularAttribute<JobTalendDO, String> type;
	public static volatile SingularAttribute<JobTalendDO, VersionDO> version;
	public static volatile SingularAttribute<JobTalendDO, FlowDO> flow;

	public static final String DELETED = "deleted";
	public static final String LAST_UPDATE_DATE = "lastUpdateDate";
	public static final String NAME = "name";
	public static final String PACKAGE_JOB = "packageJob";
	public static final String DESCRIPTION = "description";
	public static final String LAST_UPDATE_USER = "lastUpdateUser";
	public static final String CLASS_NAME = "className";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String VERSION = "version";
	public static final String FLOW = "flow";

}

