package it.eng.care.domain.flow.tabgen.entity;

import it.eng.care.platform.common.lang.StringUtils;
import it.eng.care.platform.persistence.api.TableNamingStrategy;
import it.eng.care.platform.persistence.impl.jpa.AbstractEntity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="FM_TABGEN")
@TableNamingStrategy(fieldPrefix = "")
public class TabgenDO extends AbstractEntity {
	
	@Column(name = "description")
	private String description;

	@Column(name = "FIELD_NUM")
	private Integer fieldNum;

	@Column(name = "visible")
	private Integer visible;

	@OneToMany(mappedBy = "tabgen", fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<TabgenFieldDO> tabgenFields;

	@OneToMany(mappedBy = "tabgen", fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<TabgenValueDO> tabgenValues;

	@Column(name = "type")
	private String type;

	public TabgenDO() {
	}

	public TabgenDO(String id) {
		this.id = id;
	}

	public void setId(String id) {
		if (!StringUtils.isEmpty(id)) {
			id = id.trim();
			id = replace(id);
			id = id.toUpperCase();
		}
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFieldNum() {
		return this.fieldNum;
	}

	public void setFieldNum(Integer fieldNum) {
		this.fieldNum = fieldNum;
	}

	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public Set<TabgenFieldDO> getTabgenFields() {
		return this.tabgenFields;
	}

	public void setTabgenFields(Set<TabgenFieldDO> tabgenFields) {
		this.tabgenFields = tabgenFields;
	}

	public Set<TabgenValueDO> getTabgenValues() {
		return this.tabgenValues;
	}

	public void setTabgenValues(Set<TabgenValueDO> tabgenValues) {
		this.tabgenValues = tabgenValues;
	}
	
	

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabgenDO other = (TabgenDO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (fieldNum == null) {
			if (other.fieldNum != null)
				return false;
		} else if (!fieldNum.equals(other.fieldNum))
			return false;
		if (tabgenFields == null) {
			if (other.tabgenFields != null)
				return false;
		} else {
			if (!tabgenFields.equals(other.tabgenFields))
				return false;
		}
		if (tabgenValues == null) {
			if (other.tabgenValues != null)
				return false;
		} else if (!tabgenValues.equals(other.tabgenValues))
			return false;
		return true;
	}

	private String replace(String id) {
		if (!StringUtils.isEmpty(id)) {
			id = id.replace(" ", "_");
			id = id.replace("Ã ", "a'");
			id = id.replace("Ã¨", "e'");
			id = id.replace("Ã¬", "i'");
			id = id.replace("Ã²", "o'");
			id = id.replace("Ã¹", "u'");
			id = id.replace("Ã€", "A'");
			id = id.replace("Ãˆ", "E'");
			id = id.replace("ÃŒ", "I'");
			id = id.replace("Ã’", "O'");
			id = id.replace("Ã™", "U'");
			id = id.replace(".", "");
			id = id.replace(",", "");
			id = id.replace("'", "");
			id = id.replace("&", "e");
		}
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
