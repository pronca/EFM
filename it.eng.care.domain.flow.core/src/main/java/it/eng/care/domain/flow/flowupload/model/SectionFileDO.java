package it.eng.care.domain.flow.flowupload.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_SECTION_FILE")
public class SectionFileDO {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "AFILE")
	@Lob()
	private byte[] file;

	@Column(name = "WELL_FORMED_FILE")
	@Lob
	private byte[] wellFormedFile;
	
	@ManyToOne
	@JoinColumn(name = "SECTION_ID")
	private FlowSectionFileDO section;
	
	@Column(name = "EXTENSION")
	private String extension;
	
	public SectionFileDO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getWellFormedFile() {
		return wellFormedFile;
	}

	public void setWellFormedFile(byte[] wellFormedFile) {
		this.wellFormedFile = wellFormedFile;
	}

	public FlowSectionFileDO getSection() {
		return section;
	}

	public void setSection(FlowSectionFileDO section) {
		this.section = section;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
