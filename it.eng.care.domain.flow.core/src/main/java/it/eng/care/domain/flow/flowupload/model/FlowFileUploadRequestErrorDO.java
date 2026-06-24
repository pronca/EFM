package it.eng.care.domain.flow.flowupload.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_FLOW_FILE_UPLOAD_REQ_ERROR")
public class FlowFileUploadRequestErrorDO {

	@Id
	@Column(name = "ID")
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REQUEST_ID")
	private FlowFileUploadRequestDO request;

	@Column(name = "ERROR")
	private String error;
	
	@Column(name = "SECTION")
	private Integer section;
	
	@Column(name = "INDEX_RECORD")
	private Integer indexRecord;

	public FlowFileUploadRequestErrorDO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FlowFileUploadRequestDO getRequest() {
		return request;
	}

	public void setRequest(FlowFileUploadRequestDO request) {
		this.request = request;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Integer getIndexRecord() {
		return indexRecord;
	}

	public void setIndexRecord(Integer indexRecord) {
		this.indexRecord = indexRecord;
	}
	
	
	
}
