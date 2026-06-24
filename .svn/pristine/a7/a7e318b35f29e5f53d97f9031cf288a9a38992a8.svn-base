package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProfiloFlussiPrimaryKeyDO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "role")
    protected String role;
    @Column(name = "organization")
    protected String organization;
    @Column(name = "site")
    protected String site;
    @Column(name = "flow")
    protected String flow;
    
    public ProfiloFlussiPrimaryKeyDO(String role, String organization, String site, String flow) {
        super();
        this.role = role;
        this.organization = organization;
        this.site = site;
        this.flow = flow;
    }

    public ProfiloFlussiPrimaryKeyDO() {
        super();
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

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

    
}
