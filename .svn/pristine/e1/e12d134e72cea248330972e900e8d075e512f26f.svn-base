package it.eng.care.domain.flow.core.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table(name = "FM_FLOW_RECEIVER_LOG")
@Entity
public class FlowReceiverLogDO {
	
	@Id
	@Column(name= "ID")
	private String id;
	
	@Column(name= "FLOW_NAME")
	private String flowName;
	
	@Column(name= "VERSION_NAME")
	private String versionName;
	
	@Column(name= "OPERATION")
	private String operation;
	
	@Column(name= "PK")
	private String pk;
	
	@Column(name= "SENDING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendingDate;
	
	
	public FlowReceiverLogDO() {
		super();
	}

	public FlowReceiverLogDO(String flowName, String versionName, String operation, String pk,  Date sendingDate) {
		super();
		this.flowName = flowName;
		this.versionName = versionName;
		this.operation = operation;
		this.pk = pk;
		this.sendingDate = sendingDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	
}
