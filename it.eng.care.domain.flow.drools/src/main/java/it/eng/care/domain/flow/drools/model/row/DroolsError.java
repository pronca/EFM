package it.eng.care.domain.flow.drools.model.row;

import java.util.HashMap;
import java.util.Map;

import it.eng.care.domain.flow.core.entity.ErrorMessageDO;

public class DroolsError {

	private Row row;

	private ErrorMessageDO message;

	private String fieldName;

	private Object fieldValue;

	private String severity;

	private Map<String, Object> rowGroupMap = new HashMap<String, Object>(); // campi pratica con valore

	public DroolsError(Row row, ErrorMessageDO message, String fieldName, Object fieldValue, String severity,
			Map<String, Object> rowGroupMap) {
		super();
		this.row = row;
		this.message = message;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.severity = severity;
		this.rowGroupMap = rowGroupMap;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public ErrorMessageDO getMessage() {
		return message;
	}

	public void setMessage(ErrorMessageDO message) {
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Map<String, Object> getRowGroupMap() {
		return rowGroupMap;
	}

	public void setRowGroupMap(Map<String, Object> rowGroupMap) {
		this.rowGroupMap = rowGroupMap;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DroolsError) {
			DroolsError err = (DroolsError) obj;
			return err.getMessage().getId().equals(this.getMessage().getId())
					&& err.getRow().getRowIdMap().equals(this.getRow().getRowIdMap());
		}
		return false;
	}

}
