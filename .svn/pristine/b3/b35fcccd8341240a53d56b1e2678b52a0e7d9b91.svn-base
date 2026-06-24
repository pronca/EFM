package it.eng.care.domain.flow.drools.utility.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.utility.api.DroolsStringUtility;

public class DroolsStringUtilityImpl implements DroolsStringUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(LookupUtilityImpl.class);

	@Override
	public String substring(Field field, Integer beginIndex, Integer endIndex) {
		String substring = "";
		if (!isEmpty(field) && field.getValue() instanceof String) {
			String value = (String) field.getValue();
			endIndex = (endIndex == null || endIndex > value.length()) ? value.length() : endIndex;
			beginIndex = beginIndex == null || beginIndex < 0 ? 0 : beginIndex;
			beginIndex = beginIndex > endIndex ? endIndex - 1 : beginIndex;
			substring = value.substring(beginIndex, endIndex);
		}

		return substring;
	}

	@Override
	public Boolean matches(Field field, String regexp) {
		if (isEmpty(field) || !(field.getValue() instanceof String)) {
			return false;
		}
		if(regexp == null || regexp.isEmpty()) {
			return true;
		}
		String valueToCheck = isEmpty(field) ? "" : (String) field.getValue();
		return valueToCheck.matches(regexp);
	}

	@Override
	public Boolean isEmpty(Field field) {
		if (field == null) {
			return true;
		} else {
			Object value = field.getValue();
			return value == null || value instanceof String && ((String) value).trim().isEmpty();
		}
	}

	@Override
	public Boolean equals(Field fieldA, Field fieldB) {
		return equals(fieldA, fieldB, false);
	}
	
	@Override
	public Boolean equalsIgnoreCase(Field fieldA, Field fieldB) {
		return equals(fieldA, fieldB, true);
	}
	
	private Boolean equals(Field fieldA, Field fieldB, Boolean ignoreCase) {
		if (isEmpty(fieldA) && !isEmpty(fieldB)) {
			return false;
		}

		if (!isEmpty(fieldA) && isEmpty(fieldB)) {
			return false;
		}

		if (isEmpty(fieldA) && isEmpty(fieldB)) {
			return true;
		}

		if(ignoreCase) {
			return fieldA.getValue().toString().equalsIgnoreCase(fieldB.getValue().toString());			
		} else {
			return fieldA.getValue().toString().equals(fieldB.getValue().toString());	
		}
		
	}

	@Override
	public Boolean equals(Field fieldA, String string) {
		return equals(fieldA, string, false);
	}
	
	@Override
	public Boolean equalsIgnoreCase(Field fieldA, String string) {
		return equals(fieldA, string, true);
	}
	
	private Boolean equals(Field fieldA, String string, Boolean ignoreCase) {
		if (isEmpty(fieldA) && (string == null || string.isEmpty())) {
			return true;
		}

		if (isEmpty(fieldA) && string != null && !string.isEmpty()) {
			return false;
		}
		
		if(ignoreCase) {
			return fieldA.getValue().toString().equalsIgnoreCase(string + "");
		} else {
			return fieldA.getValue().equals(string + "");						
		}
	}

	@Override
	public Boolean in(Field field, String[] values) {
		Boolean in = false;

		if (!isEmpty(field) && field.getValue() instanceof String) {
			String valueToCheck = (String) field.getValue();

			if (values != null & values.length > 0) {
				in = Arrays.asList(values).contains(valueToCheck);
			}
		}

		return in;
	}

	@Override
	public Boolean startWith(Field field, String prefix) {
		if (!isEmpty(field) && field.getValue() instanceof String) {
			String valueToCheck = isEmpty(field) ? "" : (String) field.getValue();
			prefix = prefix == null || prefix.isEmpty() ? "" : prefix;
			return valueToCheck.startsWith(prefix);
		} else {
			return false;
		}
	}

	@Override
	public Integer toInt(Field field) {
		Integer res = null;
		if (!isEmpty(field)) {
			Object value = field.getValue();
			if (value instanceof String) {
				String valueString = (String) value;
				try {
					res = Integer.parseInt(valueString);
				} catch (Exception ex) {
					LogUtil.logException(LOGGER, "", ex);
				}
			}
		}
		return res;
	}
	
	@Override
	public Integer toInt(String field) {
		try {
			return Integer.parseInt(field);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
		}
		return null;
	}
	
	@Override
	public Double toDouble(String field) {
		try {
			return Double.parseDouble(field.replace(",", "."));
		} catch (Exception ex) {}
		return null;
	}

	@Override
	public Double toDouble(Field field) {
		Double db = null;
		if (!isEmpty(field)) {
			Object value = field.getValue();
			if (value instanceof String) {
				String valueString = (String) value;
				try {
					db = Double.parseDouble(valueString.replace(",", "."));
				} catch (Exception ex) {
					LogUtil.logException(LOGGER, "", ex);
				}
			}
		}
		return db;
	}

	@Override
	public Boolean isInt(Field field) {
		Integer res = toInt(field);
		return res != null;
	}
	
	@Override
	public Boolean isInt(String field) {
		try {
			return Integer.parseInt(field) != 0;
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
		}
		return false;
	}
	
	@Override
	public Boolean isDouble(String field) {
		try {
			return Double.parseDouble(field.replace(",", ".")) != 0;
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
		}
		return false;
	}

	@Override
	public Boolean isDouble(Field field) {
		Double res = toDouble(field);
		return res != null;
	}

}
