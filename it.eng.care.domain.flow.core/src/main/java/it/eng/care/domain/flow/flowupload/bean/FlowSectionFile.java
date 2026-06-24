package it.eng.care.domain.flow.flowupload.bean;

import java.io.Serializable;

public class FlowSectionFile implements Serializable {

	private static final long serialVersionUID = -4637217372853912227L;
	
	private String id;
	private Integer section;
	private SectionFile file;

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public SectionFile getFile() {
		return file;
	}

	public void setFile(SectionFile file) {
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
