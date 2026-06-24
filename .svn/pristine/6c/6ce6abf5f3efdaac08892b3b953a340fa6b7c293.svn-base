package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FM_FLOW_TABLE_FIELD")
public class FlowTableFieldDO implements IBaseEntity {
	
	@Id
	@GeneratedValue(generator = "care-uuid")
	@GenericGenerator(name = "care-uuid", strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator")
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DESCRIPTIONSM")
	private String descriptionsm;
	@Column(name = "FIELD_TYPE")
	private String fieldType;
	@Column(name = "POSITION")
	private Integer position;
	@Column(name = "IS_PK")
	private Boolean isPk;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FLOW_TABLE")
	private FlowTableDO flowTable;
	@Column(name = "FLOW_TABLE_FIELD_ID")
	private String flowTableFieldId;
	@Column(name = "LENGTH")
	private Integer length;
	@Column(name = "MANDATORY")
	private Boolean mandatory;
	@Column(name = "REG_EXP")
	private String regExp;
	@Column(name = "IS_REFERENCE_DATE")
	private Boolean isReferenceDate;
	@Column(name = "GROUPS")
	private Boolean groups;
	@Column(name = "ENABLE_CRYPT")
	private Boolean enableCrypt;
	@Column(name = "ACTIVE")
	private Boolean active;
	@Column(name = "PHYSICAL_SIZE")
	private String physicalSize;
	

	@OneToMany(mappedBy = "idFieldTable", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<FlowForeignKeyDO> fieldTable;

	@OneToMany(mappedBy = "idFieldTableReferenced", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<FlowForeignKeyDO> fieldableReferenced;

	public String getId() {
		return id;
	}

	@Override
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

	public FlowTableDO getFlowTable() {
		return flowTable;
	}

	public void setFlowTable(FlowTableDO flowTable) {
		this.flowTable = flowTable;
	}

	public Boolean getGroups() {
		return groups;
	}

	public void setGroups(Boolean groups) {
		this.groups = groups;
	}

	public Set<FlowForeignKeyDO> getFieldTable() {
		return fieldTable;
	}

	public void setFieldTable(Set<FlowForeignKeyDO> fieldTable) {
		this.fieldTable = fieldTable;
	}

	public Set<FlowForeignKeyDO> getFieldableReferenced() {
		return fieldableReferenced;
	}

	public void setFieldableReferenced(Set<FlowForeignKeyDO> fieldableReferenced) {
		this.fieldableReferenced = fieldableReferenced;
	}

	public Boolean getEnableCrypt() {
		return enableCrypt;
	}

	public void setEnableCrypt(Boolean enableCrypt) {
		this.enableCrypt = enableCrypt;
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
