package it.eng.care.domain.flow.flowupload.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(
//	    generator = ObjectIdGenerators.PropertyGenerator.class,
//	    property = "id"
//	)
public class FlowFileUploadRequestError {
	
	private String id;

//	@JsonBackReference
	private FlowFileUploadRequest request;

	private String error;
	
	private Integer section;
	
	private Integer indexRecord;

	public FlowFileUploadRequestError() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FlowFileUploadRequest getRequest() {
		return request;
	}

	public void setRequest(FlowFileUploadRequest request) {
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
