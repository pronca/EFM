package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "FM_JOB_TALEND_DEPENDENCIES")
public class JobTalendDipendencyDO implements IBaseEntity {

    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "TYPE")
    private String dependencyType;

//    @OneToOne()
//    @JoinColumn(name = "JOB_TALEND_FILE")
//    private JobTalendFileDO jobTalendFile;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "JOB_TALEND_FILE", nullable = false)
    private JobTalendFileDO jobTalendFile;

    @Lob()
    @Column(name = "JAR")
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] jar;

	@Column(name = "NAME")
    private String name;

    public JobTalendDipendencyDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getDependencyType() {
		return dependencyType;
	}

	public void setDependencyType(String dependencyType) {
		this.dependencyType = dependencyType;
	}

	public byte[] getJar() {
		return jar;
	}

	public void setJar(byte[] jar) {
		this.jar = jar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobTalendFileDO getJobTalendFile() {
		return jobTalendFile;
	}

	public void setJobTalendFile(JobTalendFileDO jobTalendFile) {
		this.jobTalendFile = jobTalendFile;
	}

}
