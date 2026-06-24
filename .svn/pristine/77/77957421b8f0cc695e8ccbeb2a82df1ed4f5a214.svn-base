package it.eng.care.domain.flow.drools.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.persistence.impl.jpa.AbstractEntity;

@Entity
@Table(name = "FM_FLOW_RULE")
public class FlowRuleDO extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLOW")
	private FlowDO flow;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VERSION")
	private VersionDO version;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flowRule", cascade = { CascadeType.ALL, CascadeType.REMOVE })
	private List<FlowRuleFileDO> files;

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

	public List<FlowRuleFileDO> getFiles() {
		return files;
	}

	public void setFiles(List<FlowRuleFileDO> files) {
		this.files = files;
	}

}
