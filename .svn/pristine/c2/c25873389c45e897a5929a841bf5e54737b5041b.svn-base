package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(
//	    generator = ObjectIdGenerators.PropertyGenerator.class,
//	    property = "id"
//	)
public class FlowDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String code;
    private String name;
    private String description;
    private String descrb;
    private Boolean status;
    private Boolean remoteSend;
    private Boolean uniqueness;
    private Boolean scheduling;
    private String periodicy;
    private String monthlyDeadline;
    private String userCreation;
    private Date dataCreation;
    private String commProt;
    private String yearlyDeadline;
    private String flowType;

//    @JsonManagedReference
//    @JsonIgnore
    private Set<VersionDTO> versions = new HashSet<>();
    private Set<ProfiloFlussiDTO> profiloFlussi = new HashSet<>();

    public String getDescrb() {
        return descrb;
    }

    public void setDescrb(String descrb) {
        this.descrb = descrb;
    }

    public FlowDTO() {
    }

    public String getId() {
        return id;
    }

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

    public Set<VersionDTO> getVersions() {
        return versions;
    }

    public void setVersions(Set<VersionDTO> versions) {
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

	public Set<ProfiloFlussiDTO> getProfiloFlussi() {
		return profiloFlussi;
	}

	public void setProfiloFlussi(Set<ProfiloFlussiDTO> profiloFlussi) {
		this.profiloFlussi = profiloFlussi;
	}

}
