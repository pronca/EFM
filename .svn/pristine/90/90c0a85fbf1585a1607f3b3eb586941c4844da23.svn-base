package it.eng.care.domain.flow.tabgen.dto;

import it.eng.care.domain.flow.tabgen.exception.InvalidTabgenOperationException;

import java.io.Serializable;
import java.util.List;

public class TabgenResultBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean deletable;

	private String valueId;

	private TabgenValue value;

	private Tabgen table;

	private InvalidTabgenOperationException error;

	private List<TabgenField> fields;

	public TabgenResultBean() {
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	public InvalidTabgenOperationException getError() {
		return error;
	}

	public void setError(InvalidTabgenOperationException error) {
		this.error = error;
	}

	public TabgenValue getValue() {
		return value;
	}

	public void setValue(TabgenValue value) {
		this.value = value;
	}

	public Tabgen getTable() {
		return table;
	}

	public void setTable(Tabgen table) {
		this.table = table;
	}

	public void setFields(List<TabgenField> fields) {
		this.fields = fields;
	}

	public List<TabgenField> getFields() {
		return fields;
	}

}
