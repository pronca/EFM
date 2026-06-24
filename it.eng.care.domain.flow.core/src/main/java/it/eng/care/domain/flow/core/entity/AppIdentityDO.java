package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "FM_APP_IDENTITY")
public class AppIdentityDO {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "IDENTITY")
    private String identity;

    @Column(name = "FLOW_NAME")
    private String flowName;

    @Column(name = "APP")
    private String app;

    @Column(name = "VERSION")
    private String version;
    
    @Column(name = "CODICEAZIENDA")
    private String codiceAzienda;
    
    @Column(name = "IDENTITY_REG_ERRORS")
    private String identityRegErrors;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getIdentityRegErrors() {
		return identityRegErrors;
	}

	public void setIdentityRegErrors(String identityRegErrors) {
		this.identityRegErrors = identityRegErrors;
	}
    
}
