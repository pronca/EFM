package it.eng.care.domain.flow.drools.model.lookup;

import java.util.Date;
import java.util.Map;

public class LookupRow {

	private Map<String, LookupField> fields;

	private Date dtEnable;

	private Date dtDisable;

	public Map<String, LookupField> getFields() {
		return fields;
	}

	public void setFields(Map<String, LookupField> fields) {
		this.fields = fields;
	}
	
	public Boolean fieldExists(String fieldName) {
		if (fields != null) {
			LookupField field = fields.get(fieldName.toUpperCase());
			if (field != null) {
				return true;
			}
		}
		return false;
	}

	public Object getFieldValue(String fieldName) {
		if (fields != null) {
			LookupField field = fields.get(fieldName.toUpperCase());
			if (field != null) {
				return field.getValue();
			}
		}
		return null;
	}

	public Date getDtEnable() {
		return dtEnable;
	}

	public void setDtEnable(Date dtEnable) {
		this.dtEnable = dtEnable;
	}

	public Date getDtDisable() {
		return dtDisable;
	}

	public void setDtDisable(Date dtDisable) {
		this.dtDisable = dtDisable;
	}

}
