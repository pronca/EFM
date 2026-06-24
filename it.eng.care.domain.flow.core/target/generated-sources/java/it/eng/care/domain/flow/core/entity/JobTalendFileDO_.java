package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobTalendFileDO.class)
public abstract class JobTalendFileDO_ {

	public static volatile SingularAttribute<JobTalendFileDO, JobTalendDO> jobTalend;
	public static volatile SingularAttribute<JobTalendFileDO, String> id;
	public static volatile SingularAttribute<JobTalendFileDO, byte[]> job;

	public static final String JOB_TALEND = "jobTalend";
	public static final String ID = "id";
	public static final String JOB = "job";

}

