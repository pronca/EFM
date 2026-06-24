package it.eng.care.domain.flow.core.dto.FormFlowImportExportFilter;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;

import java.util.List;

public class FormFlowBaseFilterDTO {

    private String id;
    private String extraction_id; // id estrazione
    private String name;
    private String importingUser;
    private String importingStatus; //machine_status
    private String recordStatus; // state_record
    private List<FormFlowBaseFilterDateDTO> baseFiltersDate;
    private List<FlowDTO> flows;
    private List<VersionDTO> versions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtraction_id() {
        return extraction_id;
    }

    public void setExtraction_id(String extraction_id) {
        this.extraction_id = extraction_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImportingUser() {
        return importingUser;
    }

    public void setImportingUser(String importingUser) {
        this.importingUser = importingUser;
    }

    public String getImportingStatus() {
        return importingStatus;
    }

    public void setImportingStatus(String importingStatus) {
        this.importingStatus = importingStatus;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public List<FormFlowBaseFilterDateDTO> getBaseFiltersDate() {
        return baseFiltersDate;
    }

    public void setBaseFiltersDate(List<FormFlowBaseFilterDateDTO> baseFiltersDate) {
        this.baseFiltersDate = baseFiltersDate;
    }

    public List<FlowDTO> getFlows() {
        return flows;
    }

    public void setFlows(List<FlowDTO> flows) {
        this.flows = flows;
    }

    public List<VersionDTO> getVersions() {
        return versions;
    }

    public void setVersions(List<VersionDTO> versions) {
        this.versions = versions;
    }
}
