package it.eng.care.domain.flow.tabgen.dto;

import java.util.ArrayList;
import java.util.List;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;

public class RegErrorFieldMap {
	private String flowName;
	private List<TabgenValueMap> fieldList;
	private FormFlowTableDTO sectionDef;

	public RegErrorFieldMap() {
		fieldList = new ArrayList<TabgenValueMap>();
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public List<TabgenValueMap> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<TabgenValueMap> fieldList) {
		this.fieldList = fieldList;
	}

	public FormFlowTableDTO getSectionDef() {
		return sectionDef;
	}

	public void setSectionDef(FormFlowTableDTO sectionDef) {
		this.sectionDef = sectionDef;
	}

}
