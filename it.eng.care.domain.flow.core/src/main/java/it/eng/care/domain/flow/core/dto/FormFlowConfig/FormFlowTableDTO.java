package it.eng.care.domain.flow.core.dto.FormFlowConfig;

import java.util.List;
import it.eng.care.domain.flow.core.entity.FlowDO;

public class FormFlowTableDTO {

    private String id;
    private String name;
    private String description;
    private int section;
    private String datasource;
    private List<FormFlowTableFieldDTO> flowTableFieldList;
    private List<FormFlowTableLinkDTO> listFk;
    private FlowDO flowDO;
    private byte[] xsd;
    
    private Boolean required;

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

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public List<FormFlowTableFieldDTO> getFlowTableFieldList() {
        return flowTableFieldList;
    }

    public void setFlowTableFieldList(List<FormFlowTableFieldDTO> flowTableFieldList) {
        this.flowTableFieldList = flowTableFieldList;
    }
   
    public List<FormFlowTableLinkDTO> getListFk() {
        return listFk;
    }

    public void setListFk(List<FormFlowTableLinkDTO> listFk) {
        this.listFk = listFk;
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
	public FlowDO getFlowDO() {
		return flowDO;
	}
	public void setFlowDO(FlowDO flowDO) {
		this.flowDO = flowDO;
	}
    
    
    
}
