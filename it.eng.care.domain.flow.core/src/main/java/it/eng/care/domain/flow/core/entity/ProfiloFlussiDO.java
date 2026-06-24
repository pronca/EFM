package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_PROFILO_FLUSSI")
public class ProfiloFlussiDO {
	
	@EmbeddedId
    private ProfiloFlussiPrimaryKeyDO id;
	
    @Column(name = "ROLE")
    private String role;

    @Column(name = "ORGANIZATION")
    private String organization;

    @Column(name = "SITE")
    private String site;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flow", insertable = false, updatable = false)
    private FlowDO flow;

    @Column(name = "CONSOLIDAMENTO")
    private String consolidamento;
    
    public ProfiloFlussiDO() {
        super();
        id = new ProfiloFlussiPrimaryKeyDO();
    }
    
    public ProfiloFlussiDO(FlowDO flow) {
        super();
        assert flow != null;
        this.id = new ProfiloFlussiPrimaryKeyDO(role, organization, site, flow.getId());
        this.flow = flow;
    }
    
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	public String getConsolidamento() {
		return consolidamento;
	}

	public FlowDO getFlow() {
		return flow;
	}

	public void setFlow(FlowDO flow) {
		this.flow = flow;
	}

	public void setConsolidamento(String consolidamento) {
		this.consolidamento = consolidamento;
	}

	public ProfiloFlussiPrimaryKeyDO getId() {
		return id;
	}

	public void setId(ProfiloFlussiPrimaryKeyDO id) {
		this.id = id;
	}
	
	
}
