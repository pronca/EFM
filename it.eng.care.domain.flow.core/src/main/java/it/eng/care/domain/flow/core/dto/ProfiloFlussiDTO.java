package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class ProfiloFlussiDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String role;
    private String organization;
    private String site;
    private String consolidamento;

    public ProfiloFlussiDTO() {
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


	public void setConsolidamento(String consolidamento) {
		this.consolidamento = consolidamento;
	}
    

}