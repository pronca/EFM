package it.eng.care.domain.flow.drools.model.row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.eng.care.domain.flow.drools.model.status.BeanValidationStatusEnum;

/**
 * Riga di una sezione comprensiva di tutti i campi
 */
public class Row {

	private Map<String, Object> rowIdMap = new HashMap<String, Object>(); // campi chiave con valore
	
	private List<Field> fields;

	private Section section;

	private Boolean isValid = true; // aggiornato dal processo di validazione

	private String severity = BeanValidationStatusEnum.VALID.name(); // aggiornato dal processo di validazione

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * Recupera il valore di un campo
	 * 
	 * @param fieldName nome del campo
	 * @return
	 */
	public Object getFieldValue(String fieldName) {
		Object value = null;
		Field field = getField(fieldName);
		if (field != null) {
			value = field.getValue();
		}

		return value;
	}

	/**
	 * Recupera un campo
	 * 
	 * @param fieldName nome del campo
	 * @return
	 */
	public Field getField(String fieldName) {
		Field value = null;
		if (fields != null && !fields.isEmpty()) {
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase(fieldName)) {
					value = field;
					break;
				}
			}
		}

		return value;
	}

	public void addField(String fieldName, Object fieldValue) {
		Field field = new Field(fieldName, fieldValue);

		if (fields == null) {
			fields = new ArrayList<Field>();
		}
		fields.add(field);

	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Map<String, Object> getRowIdMap() {
		return rowIdMap;
	}

	public void setRowIdMap(Map<String, Object> rowIdMap) {
		this.rowIdMap = rowIdMap;
	}
	
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Override
	public String toString() {
		String toString = "";
		if (fields != null) {
			for (Field field : fields) {
				if (field.getValue() != null) {
					toString += field.getValue();
				}
			}
		}
		
		if(section != null) {
			toString += "_" + section.getIndex();
		}

		return toString;
	}
	
	public Boolean isEmpty() {
		String toString = "";
		if (fields != null) {
			for (Field field : fields) {
				if (field.getValue() != null) {
					toString += field.getValue().toString().trim();
				}
			}
		}
		
		return toString.isEmpty();
	}

}
