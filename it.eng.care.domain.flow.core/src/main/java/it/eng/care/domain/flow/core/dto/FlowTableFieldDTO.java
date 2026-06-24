package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class FlowTableFieldDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id; //
    private String name;
    private String description;
    private String descriptionsm;
    private String fieldType;
    private Integer position;
    private Boolean isPk;
    private FlowTableDTO flowTable;
    private String flowTableFieldId; // Campo di partenza
    private Integer length;
    private Boolean mandatory;
    private String regExp;
    private Boolean isReferenceDate;
    private Boolean groups;
    private Boolean crypto;
    private Boolean active;
    private String physicalSize;

    public FlowTableFieldDTO() {
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

    public String getDescriptionsm() {
        return descriptionsm;
    }

    public void setDescriptionsm(String descriptionsm) {
        this.descriptionsm = descriptionsm;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getPk() {
        return isPk;
    }

    public void setPk(Boolean pk) {
        isPk = pk;
    }

    public FlowTableDTO getFlowTable() {
        return flowTable;
    }

    public void setFlowTable(FlowTableDTO flowTable) {
        this.flowTable = flowTable;
    }

    public String getFlowTableFieldId() {
        return flowTableFieldId;
    }

    public void setFlowTableFieldId(String flowTableFieldId) {
        this.flowTableFieldId = flowTableFieldId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public Boolean getReferenceDate() {
        return isReferenceDate;
    }

    public void setReferenceDate(Boolean referenceDate) {
        isReferenceDate = referenceDate;
    }

    public Boolean getGroups() {
        return groups;
    }

    public void setGroups(Boolean groups) {
        this.groups = groups;
    }

	public Boolean getCrypto() {
		return crypto;
	}

	public void setCrypto(Boolean crypto) {
		this.crypto = crypto;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public String getPhysicalSize() {
		return physicalSize;
	}
	
	public void setPhysicalSize(String physicalSize) {
		this.physicalSize = physicalSize;
	}
	
	
    

}
