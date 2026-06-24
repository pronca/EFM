package it.eng.care.domain.flow.flowupload.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;

public class FlowFileUploadRequest implements Serializable {
	
	private static final long serialVersionUID = -2521481067953700505L;

	private String id;
	
	private FlowDTO flow;
	
	private VersionDTO version;
	
	private String username;

	private Date creationDate;
	
	private Date validationDate;

	private String status;
	
	private List<FlowSectionFile> files;
	
	private List<FlowFileUploadRequestError> errors;
	
	private String aziendeProfiloFlussi;
	
    private String aziendeLoadedInFile;
	
	public FlowFileUploadRequest() {
		
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

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<FlowFileUploadRequestError> getErrors() {
		return errors;
	}

	public void setErrors(List<FlowFileUploadRequestError> errors) {
		this.errors = errors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FlowDTO getFlow() {
		return flow;
	}

	public void setFlow(FlowDTO flow) {
		this.flow = flow;
	}

	public VersionDTO getVersion() {
		return version;
	}

	public void setVersion(VersionDTO version) {
		this.version = version;
	}

	public List<FlowSectionFile> getFiles() {
		return files;
	}

	public void setFiles(List<FlowSectionFile> files) {
		this.files = files;
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
