package it.eng.care.domain.flow.flowupload.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "FM_FLOW_SECTION_FILE")
public class FlowSectionFileDO implements Serializable {

	private static final long serialVersionUID = -9037525865518388412L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "SECTION")
	private Integer section;

	@Column(name = "UPLOAD_USERNAME")
	private String uploadUsername;

	@Column(name = "UPLOAD_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REQUEST_ID")
	private FlowFileUploadRequestDO request;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="section", cascade = {CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval = true)
	private List<SectionFileDO> sectionFileList;

	public FlowSectionFileDO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public FlowFileUploadRequestDO getRequest() {
		return request;
	}

	public void setRequest(FlowFileUploadRequestDO request) {
		this.request = request;
	}

	public String getUploadUsername() {
		return uploadUsername;
	}

	public void setUploadUsername(String uploadUsername) {
		this.uploadUsername = uploadUsername;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public List<SectionFileDO> getSectionFileList() {
		return sectionFileList;
	}

	public void setSectionFileList(List<SectionFileDO> sectionFileList) {
		this.sectionFileList = sectionFileList;
	}

}
