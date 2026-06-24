package it.eng.care.domain.flow.tabgen.dto;

import java.util.List;

public class TabgenMap {

	private String id;
	private List<TabgenValueMap> tabgenValueList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TabgenValueMap> getTabgenValueList() {
		return tabgenValueList;
	}

	public void setTabgenValueList(List<TabgenValueMap> tabgenValueList) {
		this.tabgenValueList = tabgenValueList;
	}

}