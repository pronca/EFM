package it.eng.care.domain.flow.core.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"flows"})
//@JsonIdentityInfo(
//	    generator = ObjectIdGenerators.PropertyGenerator.class,
//	    property = "id"
//	)
public class VersionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
//    @JsonIgnore
    private String name;
    private String version;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date creationDate;
//    @JsonBackReference
//    @JsonIgnore
    private Set<FlowDTO> flows = new HashSet<>();

    public VersionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<FlowDTO> getFlows() {
        return flows;
    }

    public void setFlows(Set<FlowDTO> flows) {
        this.flows = flows;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

//    @JsonIgnore
    public void setName(String name) {
        this.name = name;
    }

}