package it.eng.care.domain.flow.tabgen.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.List;

//@JsonIdentityInfo(
//	    generator = ObjectIdGenerators.PropertyGenerator.class,
//	    property = "id"
//	)
public class TabgenField implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String description;

	private String format;

	private Boolean nullable = true;

	private Boolean pk = false;

	private Integer progressive;

	private String tabgenValueColumn;

	private String type;

	private Boolean visible = true;

//	@JsonBackReference
	private Tabgen tabgen;

//	@JsonIgnore
	private TabgenField tabgenField;

//	@JsonIgnore
	private List<TabgenField> tabgenFields;

	public TabgenField() {
	}

	public TabgenField(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getProgressive() {
		return this.progressive;
	}

	public void setProgressive(Integer progressive) {
		this.progressive = progressive;
	}

	public String getTabgenValueColumn() {
		return this.tabgenValueColumn;
	}

	public void setTabgenValueColumn(String tabgenValueColumn) {
		this.tabgenValueColumn = tabgenValueColumn;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Boolean getPk() {
		return pk;
	}

	public void setPk(Boolean pk) {
		this.pk = pk;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Tabgen getTabgen() {
		return this.tabgen;
	}

	public void setTabgen(Tabgen tabgen) {
		this.tabgen = tabgen;
	}

	public TabgenField getTabgenField() {
		return this.tabgenField;
	}

	public void setTabgenField(TabgenField tabgenField) {
		this.tabgenField = tabgenField;
	}

	public List<TabgenField> getTabgenFields() {
		return this.tabgenFields;
	}

	public void setTabgenFields(List<TabgenField> tabgenFields) {
		this.tabgenFields = tabgenFields;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nullable == null) ? 0 : nullable.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((progressive == null) ? 0 : progressive.hashCode());
		result = prime * result + ((tabgenValueColumn == null) ? 0 : tabgenValueColumn.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	// non controllo i campi description, visible, nullable, progressive e
	// tabgenField (fk)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabgenField other = (TabgenField) obj;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (tabgenValueColumn == null) {
			if (other.tabgenValueColumn != null)
				return false;
		} else if (!tabgenValueColumn.equals(other.tabgenValueColumn))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (tabgen == null) {
			if (other.tabgen != null)
				return false;
		} else if (tabgen != null && other.getTabgen() != null && !tabgen.getId().equals(other.tabgen.getId()))
			return false;
		return true;
	}

}