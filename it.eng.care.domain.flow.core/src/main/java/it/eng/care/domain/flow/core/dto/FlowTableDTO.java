package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class FlowTableDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private Integer section;
    private FlowDTO flow;
    private VersionDTO version;
    private DataSourceDTO dataSource;
    private Boolean required;
    private byte[] xsd;

    public FlowTableDTO() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
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

    public DataSourceDTO getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceDTO dataSource) {
        this.dataSource = dataSource;
    }

    public byte[] getXsd() {
        return xsd;
    }

    public void setXsd(byte[] xsd) {
        this.xsd = xsd;
    }

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}
    
    
}
