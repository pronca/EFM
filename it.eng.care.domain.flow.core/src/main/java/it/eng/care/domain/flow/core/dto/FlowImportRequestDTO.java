package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class FlowImportRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;
    private FlowDTO flow;
    private VersionDTO version;
    private Set<FlowImportRequestFieldDateDTO> dateFields;
    private String importingUser;
    private String record;
    private String status;
    private Date requestDate;
    private Date endExtractionDate;

    public FlowImportRequestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlowDTO getFlow() {
        return flow;
    }

    public void setFlow(FlowDTO flow) {
        this.flow = flow;
    }

    public VersionDTO getVersion() {
        return version;
    }

    public void setVersion(VersionDTO version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<FlowImportRequestFieldDateDTO> getDateFields() {
        return dateFields;
    }

    public void setDateFields(Set<FlowImportRequestFieldDateDTO> dateFields) {
        this.dateFields = dateFields;
    }

    public String getImportingUser() {
        return importingUser;
    }

    public void setImportingUser(String importingUser) {
        this.importingUser = importingUser;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getEndExtractionDate() {
        return endExtractionDate;
    }

    public void setEndExtractionDate(Date endExtractionDate) {
        this.endExtractionDate = endExtractionDate;
    }

}
