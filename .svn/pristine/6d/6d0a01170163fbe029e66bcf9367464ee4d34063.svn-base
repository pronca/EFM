package it.eng.care.domain.flow.drools.utility.impl;

import java.util.ArrayList;
import java.util.List;

import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.model.row.Row;
import it.eng.care.domain.flow.drools.model.row.Section;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.utility.api.FlowRowUtility;

public class FlowRowUtilityImpl implements FlowRowUtility {

	@Override
	public Row getRow(ValidationBean bean, Integer rowIndex, Integer sectionIndex) {
		if (bean != null) {
			Section section = bean.getSection(sectionIndex);
				
			if(section != null) {
				if(rowIndex == null || rowIndex < 0) {
					rowIndex = 0;
				}
					
				if(rowIndex.intValue() >= section.getRows().size()) {
					rowIndex = section.getRows().size() - 1;
				}
				
				if (section.getRows() != null && section.getRows().size() > rowIndex.intValue()) {
					Row row = section.getRows().get(rowIndex);
					return row;
				}
			}
		}
		return null;
	}

	@Override
	public Object getFieldValue(ValidationBean bean, Integer rowIndex, Integer sectionIndex, String fieldName) {
		Object value = null;
		Field field = getField(bean, rowIndex, sectionIndex, fieldName);
		if (field != null) {
			value = field.getValue();
		}
		return value;
	}
	
	@Override
	public Object nvlFieldValue(ValidationBean bean, Integer rowIndex, Integer sectionIndex, String fieldName, Object replacement) {
		Object value = getFieldValue(bean, rowIndex, sectionIndex, fieldName);
		if(value == null || value instanceof String && ((String)value).isEmpty()) {
			value = replacement;
		}
		return value;
	}
	
	@Override
	public Object getFieldValue(ValidationBean bean, Integer rowIndex, String sectionName, String fieldName) {
		Object value = null;
		Field field = getField(bean, rowIndex, sectionName, fieldName);
		if (field != null) {
			value = field.getValue();
		}
		return value;
	}
	
	@Override
	public Object nvlFieldValue(ValidationBean bean, Integer rowIndex, String sectionName, String fieldName, Object replacement) {
		Object value = getFieldValue(bean, rowIndex, sectionName, fieldName);
		if(value == null || value instanceof String && ((String)value).isEmpty()) {
			value = replacement;
		}
		return value;
	}

	@Override
	public Field getField(ValidationBean bean, Integer rowIndex, Integer sectionIndex, String fieldName) {
		Field value = null;
		if(bean != null) {
			Section sec = bean.getSection(sectionIndex);
			if (sec != null) {
				if(rowIndex == null || rowIndex < 0) {
					rowIndex = 0;
				}
				value = sec.getField(fieldName, rowIndex);
			}
		}
		return value;
	}
	
	@Override
	public Field getField(ValidationBean bean, Integer rowIndex, String sectionName, String fieldName) {
		Field value = null;
		if(bean != null) {
			Section sec = bean.getSection(sectionName);
			if (sec != null) {
				if(rowIndex == null || rowIndex < 0) {
					rowIndex = 0;
				}
				value = sec.getField(fieldName, rowIndex);
			}
		}
		return value;
	}

	@Override
	public List<Integer> findRowIndexByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex) {
		return findRowIndexByFields(bean, fields, sectionIndex, false);
	}

	@Override
	public Integer findFirstRowIndexByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex) {
		List<Integer> result = findRowIndexByFields(bean, fields, sectionIndex, true);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	private List<Integer> findRowIndexByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex,
			Boolean onyFirst) {
		List<Integer> result = new ArrayList<Integer>();

		if (bean != null) {
			Section sec = bean.getSection(sectionIndex);
			if(sec != null && sec.getRows() != null && !sec.getRows().isEmpty()) {
				Integer index = 0;
				for (Row row : sec.getRows()) {
					if (checkRow(row, fields)) {
						result.add(index);
					}
					index++;
				}
			}
		}

		return result;
	}

	@Override
	public List<Row> findRowByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex) {
		List<Row> rows = findRowsByFields(bean, fields, sectionIndex, false);
		return rows;
	}

	@Override
	public Row findFirstRowByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex) {
		List<Row> rows = findRowsByFields(bean, fields, sectionIndex, true);
		if (rows != null && !rows.isEmpty()) {
			return rows.get(0);
		}

		return null;
	}

	private List<Row> findRowsByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex,
			Boolean onlyFirst) {
		List<Row> result = new ArrayList<Row>();

		if (bean != null) {
			Section section = bean.getSection(sectionIndex);
			if(section != null) {
				List<Row> rows = section.getRows();
				for (Row row : rows) {
					if (checkRow(row, fields)) {
						result.add(row);
						if (onlyFirst) {
							break;
						}
					}
				}
			}
		}

		return result;
	}

	private Boolean checkRow(Row row, List<Field> fields) {
		if(fields != null && !fields.isEmpty()) {
			for (Field field : fields) {
				if(field.getName() != null && !field.getName().isEmpty()) {
					Object rowValue = row.getFieldValue(field.getName());
					if (rowValue == null && field.getValue() != null) {
						return false;
					} else if (rowValue != null && field.getValue() == null) {
						return false;
					} else if (rowValue !=null && field.getValue() != null && !rowValue.equals(field.getValue())) {
						return false;
					}
				}
			}
		}

		return true;
	}

}
