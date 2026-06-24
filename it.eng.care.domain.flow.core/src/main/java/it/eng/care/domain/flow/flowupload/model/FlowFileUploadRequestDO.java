package it.eng.care.domain.flow.flowupload.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;

@Entity
@Table(name = "FM_FLOW_FILE_UPLOAD_REQUEST")
public class FlowFileUploadRequestDO {

	@Id
	@Column(name = "ID")
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FLOW_ID")
	private FlowDO flow;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VERSION_ID")
	private VersionDO version;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "VALIDATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validationDate;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = { CascadeType.ALL, CascadeType.REMOVE }, mappedBy = "request")
	private List<FlowSectionFileDO> files;

	@OneToMany(cascade = { CascadeType.ALL, CascadeType.REMOVE }, mappedBy = "request", orphanRemoval = true)
	private List<FlowFileUploadRequestErrorDO> errors;
	
	@Column(name = "AZIENDEPROFILOFLUSSI")
    private String aziendeProfiloFlussi;
	
	@Column(name = "AZIENDELOADEDINFILE")
    private String aziendeLoadedInFile;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FlowDO getFlow() {
		return flow;
	}

	public void setFlow(FlowDO flow) {
		this.flow = flow;
	}

	public VersionDO getVersion() {
		return version;
	}

	public void setVersion(VersionDO version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<FlowSectionFileDO> getFiles() {
		return files;
	}

	public void setFiles(List<FlowSectionFileDO> files) {
		this.files = files;
	}

	public List<FlowFileUploadRequestErrorDO> getErrors() {
		return errors;
	}

	public void setErrors(List<FlowFileUploadRequestErrorDO> errors) {
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	public String getAziendeProfiloFlussi() {
		return aziendeProfiloFlussi;
	}

	public void setAziendeProfiloFlussi(String aziendeProfiloFlussi) {
		this.aziendeProfiloFlussi = aziendeProfiloFlussi;
	}

	public String getAziendeLoadedInFile() {
		return aziendeLoadedInFile;
	}

	public void setAziendeLoadedInFile(String aziendeLoadedInFile) {
		this.aziendeLoadedInFile = aziendeLoadedInFile;
	}
	
	
}
