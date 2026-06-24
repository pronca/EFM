package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobTalendDipendencyDO.class)
public abstract class JobTalendDipendencyDO_ {

	public static volatile SingularAttribute<JobTalendDipendencyDO, String> dependencyType;
	public static volatile SingularAttribute<JobTalendDipendencyDO, JobTalendFileDO> jobTalendFile;
	public static volatile SingularAttribute<JobTalendDipendencyDO, String> name;
	public static volatile SingularAttribute<JobTalendDipendencyDO, byte[]> jar;
	public static volatile SingularAttribute<JobTalendDipendencyDO, String> id;

	public static final String DEPENDENCY_TYPE = "dependencyType";
	public static final String JOB_TALEND_FILE = "jobTalendFile";
	public static final String NAME = "name";
	public static final String JAR = "jar";
	public static final String ID = "id";

}

