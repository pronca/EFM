package it.eng.care.domain.flow.drools.model.row;

import java.util.List;

/**
 * Sezione di un flusso
 */
public class Section {

	private Integer index;

	private String name;

	private String description;

	private List<Row> rows;

	public Section(Integer index, String name, String description) {
		super();
		this.index = index;
		this.name = name;
		this.description = description;
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	/**
	 * Recupera il valore di un campo
	 * 
	 * @param fieldName nome del campo
	 * @param rowIndex  indice della riga
	 * @return
	 */
	public Object getFieldValue(String fieldName, Integer rowIndex) {
		Object value = null;
		Field field = getField(fieldName, rowIndex);
		if (field != null) {
			value = field.getValue();
		}
		return value;
	}

	/**
	 * Recupera un campo
	 * 
	 * @param fieldName nome del campo
	 * @param rowIndex  indice della riga
	 * @return
	 */
	public Field getField(String fieldName, Integer rowIndex) {
		Field value = null;

		if (rows != null && !rows.isEmpty() && fieldName != null && rows.size() > rowIndex.intValue()) {
			Row row = rows.get(rowIndex);
			value = row.getField(fieldName);
		}

		return value;
	}

}
