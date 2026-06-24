package it.eng.care.domain.flow.core.dto.FlowConfigurationFilter;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;

import java.util.List;

public class FlowConfigurationFilterDTO {

    String id;
    String name;
    VersionDTO version;
    FlowDTO flow;
    Integer type;
    List<List<FlowConfigurationFilterFieldDTO>> fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VersionDTO getVersion() {
        return version;
    }

    public void setVersion(VersionDTO version) {
        this.version = version;
    }

    public FlowDTO getFlow() {
        return flow;
    }

    public void setFlow(FlowDTO flow) {
        this.flow = flow;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<List<FlowConfigurationFilterFieldDTO>> getFields() {
        return fields;
    }

    public void setFields(List<List<FlowConfigurationFilterFieldDTO>> fields) {
        this.fields = fields;
    }
}
