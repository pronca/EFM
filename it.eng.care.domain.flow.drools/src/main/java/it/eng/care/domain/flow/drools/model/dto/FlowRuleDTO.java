package it.eng.care.domain.flow.drools.model.dto;

import java.io.Serializable;

public class FlowRuleDTO implements Serializable {

	private static final long serialVersionUID = -1269433244560044839L;

	private String id;

	private String flowName;

	private String flowId;

	private String flowDescription;

	private String flowType;

	private String versionName;

	private String versionDescription;

	private String versionId;

	private Integer numberOfRules;

	public FlowRuleDTO() {
		this.numberOfRules = 0;
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

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowDescription() {
		return flowDescription;
	}

	public void setFlowDescription(String flowDescription) {
		this.flowDescription = flowDescription;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public Integer getNumberOfRules() {
		return numberOfRules;
	}

	public void setNumberOfRules(Integer numberOfRules) {
		this.numberOfRules = numberOfRules;
	}

}
