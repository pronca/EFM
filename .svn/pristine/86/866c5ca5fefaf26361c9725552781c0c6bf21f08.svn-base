package it.eng.care.domain.flow.drools.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_FLOW_RULE_FILE")
public class FlowRuleFileDO implements Serializable {

	private static final long serialVersionUID = 2374105059681049800L;

	@Id
	@Column(name = "id")
	private String id;

	@ManyToOne()
	@JoinColumn(name = "FLOW_RULE_ID")
	private FlowRuleDO flowRule;

	@Lob()
	@Column(name = "rule")
	private byte[] rule;

	@Column(name = "filename")
	private String filename;

	@Column(name = "RULE_TYPE")
	private String ruleType;

	public FlowRuleFileDO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FlowRuleDO getFlowRule() {
		return flowRule;
	}

	public void setFlowRule(FlowRuleDO flowRule) {
		this.flowRule = flowRule;
	}

	public byte[] getRule() {
		return rule;
	}

	public void setRule(byte[] rule) {
		this.rule = rule;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

}
