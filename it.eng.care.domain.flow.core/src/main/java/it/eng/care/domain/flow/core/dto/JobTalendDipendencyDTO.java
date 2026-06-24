package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class JobTalendDipendencyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private JobTalendFileDTO jobTalendFile;
    private String dependencyType;
    private byte[] jar;

    public JobTalendDipendencyDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public JobTalendFileDTO getJobTalendFile() {
		return jobTalendFile;
	}

	public void setJobTalendFile(JobTalendFileDTO jobTalendFile) {
		this.jobTalendFile = jobTalendFile;
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

}