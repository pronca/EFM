package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FM_FLOW")
public class FlowDO implements IBaseEntity {

    @Id
    @GeneratedValue(generator = "care-uuid")
    @GenericGenerator(name = "care-uuid", //
            strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DESCRB")
    private String descrb;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "REMOTE_SEND")
    private Boolean remoteSend;

    @Column(name = "UNIQUENESS")
    private Boolean uniqueness;

    @Column(name = "SCHEDULING")
    private Boolean scheduling;

    @Column(name = "PERIODICITY")
    private String periodicy;

    @Column(name = "MONTHLY_DEADLINE")
    private String monthlyDeadline;

    @Column(name = "USER_CREATION")
    private String userCreation;

    @Column(name = "DATA_CREATION")
    private Date dataCreation;

    @Column(name = "COMM_PROT")
    private String commProt;

    @Column(name = "YEARLY_DEADLINE")
    private String yearlyDeadline;

    @Column(name = "TYPE")
    private String flowType;

    @OneToMany(mappedBy = "flow", cascade = {CascadeType.ALL})
    private Set<FlowVersionDO> versions = new HashSet<>();
    
    @OneToMany(mappedBy = "flow", cascade = {CascadeType.ALL})
    private Set<ProfiloFlussiDO> profiloFlussi = new HashSet<>();
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescrb() { return descrb; }

    public void setDescrb(String descrb) { this.descrb = descrb; }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getRemoteSend() {
        return remoteSend;
    }

    public void setRemoteSend(Boolean remoteSend) {
        this.remoteSend = remoteSend;
    }

    public Boolean getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(Boolean uniqueness) {
        this.uniqueness = uniqueness;
    }

    public Boolean getScheduling() {
        return scheduling;
    }

    public void setScheduling(Boolean scheduling) {
        this.scheduling = scheduling;
    }

    public String getPeriodicy() {
        return periodicy;
    }

    public void setPeriodicy(String periodicy) {
        this.periodicy = periodicy;
    }

    public String getMonthlyDeadline() {
        return monthlyDeadline;
    }

    public void setMonthlyDeadline(String monthlyDeadline) {
        this.monthlyDeadline = monthlyDeadline;
    }

    public Set<FlowVersionDO> getVersions() {
        return versions;
    }

    public void setVersions(Set<FlowVersionDO> versions) {
        this.versions = versions;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public Date getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(Date dataCreation) {
        this.dataCreation = dataCreation;
    }

    public String getCommProt() {
        return commProt;
    }

    public void setCommProt(String commProt) {
        this.commProt = commProt;
    }

    public String getYearlyDeadline() {
        return yearlyDeadline;
    }

    public void setYearlyDeadline(String yearlyDeadline) {
        this.yearlyDeadline = yearlyDeadline;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

	public Set<ProfiloFlussiDO> getProfiloFlussi() {
		return profiloFlussi;
	}

	public void setProfiloFlussi(Set<ProfiloFlussiDO> profiloFlussi) {
		this.profiloFlussi = profiloFlussi;
	}

}
