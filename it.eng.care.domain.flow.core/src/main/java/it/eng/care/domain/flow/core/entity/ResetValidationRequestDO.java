package it.eng.care.domain.flow.core.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "FM_RESET_VALIDATION_REQUEST")
public class ResetValidationRequestDO {
	
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "MONTH_REF")
	private String month;

	@Column(name = "YEAR_REF")
	private String year;

	@Column(name = "USER_ID")
	private String userId;

	@ManyToOne
    @JoinColumn(name = "FLOW_ID")
	private FlowDO flow;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "PROCESSING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingDate;

	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public FlowDO getFlow() {
		return flow;
	}

	public void setFlow(FlowDO flow) {
		this.flow = flow;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
