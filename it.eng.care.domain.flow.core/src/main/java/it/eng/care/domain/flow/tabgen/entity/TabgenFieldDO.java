package it.eng.care.domain.flow.tabgen.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import it.eng.care.platform.persistence.impl.jpa.AbstractEntity;

/**
 * The persistent class for the TABGEN_FIELD database table.
 * 
 */
@Entity
@Table(name = "FM_TABGEN_FIELD")
public class TabgenFieldDO extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "description")
	private String description;

	@Column(name = "format")
	private String format;

	@Column(name = "nullable")
	private Integer nullable = 1;

	@Column(name = "pk")
	private Integer pk = 0;

	@Column(name = "progressive")
	private Integer progressive;

	@Column(name = "TABGEN_VALUE_COLUMN")
	private String tabgenValueColumn;

	@Column(name = "type")
	private String type;

	@Column(name = "visible")
	private Integer visible;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABGEN_ID")
	private TabgenDO tabgen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABGEN_FIELD_ID")
	private TabgenFieldDO tabgenField;

	@OneToMany(mappedBy = "tabgenField", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<TabgenFieldDO> tabgenFields;

	public TabgenFieldDO() {
	}

	public TabgenFieldDO(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		if (this.description != null && !this.description.isEmpty())
			return this.description.toUpperCase();
		return this.description;
	}

	public void setDescription(String description) {
		// elimino eventuali caratteri (.,')
		if (description != null && !description.isEmpty()) {
			description = description.trim();
			description = replace(description);
			description = description.toUpperCase();
		}
		this.description = description;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getNullable() {
		return this.nullable;
	}

	public void setNullable(Integer nullable) {
		this.nullable = nullable;
	}

	public Integer getPk() {
		return this.pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
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
		if (tabgenValueColumn != null && !tabgenValueColumn.isEmpty()) {
			tabgenValueColumn = tabgenValueColumn.toUpperCase();
		}
		this.tabgenValueColumn = tabgenValueColumn;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public TabgenDO getTabgen() {
		return this.tabgen;
	}

	public void setTabgen(TabgenDO tabgen) {
		this.tabgen = tabgen;
	}

	public TabgenFieldDO getTabgenField() {
		return this.tabgenField;
	}

	public void setTabgenField(TabgenFieldDO tabgenField) {
		this.tabgenField = tabgenField;
	}

	public Set<TabgenFieldDO> getTabgenFields() {
		return this.tabgenFields;
	}

	public void setTabgenFields(Set<TabgenFieldDO> tabgenFields) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabgenFieldDO other = (TabgenFieldDO) obj;
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
		} else if (!tabgen.getId().equals(other.tabgen.getId()))
			return false;
		return true;
	}

	private String replace(String field) {
		if (field != null && !field.isEmpty()) {
			field = field.replace(" ", "_");
			field = field.replace("Ã ", "a'");
			field = field.replace("Ã¨", "e'");
			field = field.replace("Ã¬", "i'");
			field = field.replace("Ã²", "o'");
			field = field.replace("Ã¹", "u'");
			field = field.replace("Ã€", "A'");
			field = field.replace("Ãˆ", "E'");
			field = field.replace("ÃŒ", "I'");
			field = field.replace("Ã’", "O'");
			field = field.replace("Ã™", "U'");
			field = field.replace(".", "");
			field = field.replace(",", "");
			field = field.replace("'", "");
			field = field.replace("&", "e");
		}
		return field;
	}

}