package it.eng.care.domain.flow.core.dto.FormFlowConfig;

public class FormFlowTableFieldDTO {

    private String id;
    private String name;
    private String description;
    private String descriptionsm;
    private Integer position;
    private boolean pk;
    private String fieldType;
    private String flowTableFieldId;
    private boolean mandatory;
    private String length;
    private String regExp;
    private boolean referenceDate;
    private Boolean groups;
    private String sectionName;
    private Integer section;
    private boolean crypto;
    private Boolean active;
    private String physicalSize;
    
        
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public String getFlowTableFieldId() {
        return flowTableFieldId;
    }

    public void setFlowTableFieldId(String flowTableFieldId) {
        this.flowTableFieldId = flowTableFieldId;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }


    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public boolean isReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(boolean referenceDate) {
        this.referenceDate = referenceDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Boolean getGroups() {
        return groups;
    }

    public void setGroups(Boolean groups) {
        this.groups = groups;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

	public boolean isCrypto() {
		return crypto;
	}

	public void setCrypto(boolean crypto) {
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
