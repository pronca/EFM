package it.eng.care.domain.flow.drools.utility.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.utility.api.DroolsDateUtility;

public class DroolsDateUtilityImpl implements DroolsDateUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsDateUtilityImpl.class);
	
	@Autowired
	private DroolsStringUtilityImpl droolsStringUtils;
	
	private String defaultFormat = "yyyyMMdd";
	
	@Override
	public Date toDate(Field field, String format) {
		Date date = null;
		format = format == null || format.isEmpty() ? defaultFormat : format;

		if (!droolsStringUtils.isEmpty(field) && field.getValue() instanceof String) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(format);
				date = formatter.parse((String) field.getValue());
			} catch (Exception ex) {
				LogUtil.logException(LOGGER, "", ex);
			}
		} else if (!droolsStringUtils.isEmpty(field) && field.getValue() instanceof Date) {
			date = (Date) field.getValue();
		}

		return date;
	}

	@Override
	public Integer compareFieldDate(Field fieldA, Field fieldB, String format) {
		Date dateA = toDate(fieldA, format);
		Date dateB = toDate(fieldB, format);

		if (dateA == null && dateB == null) {
			return 0;
		}

		if (dateA == null && dateB != null) {
			return 1;
		}

		if (dateA != null && dateB == null) {
			return -1;
		}

		return dateA.compareTo(dateB);
	}

	@Override
	public Boolean betweenFieldDate(Field field, Date from, Date to, String format) {
		Date fieldDate = toDate(field, format);
		Boolean geFrom = true;
		Boolean leTo = true;

		if (fieldDate == null) {
			return false;
		}

		if (from != null) {
			geFrom = fieldDate.compareTo(from) >= 0;
		}

		if (to != null) {
			leTo = fieldDate.compareTo(to) <= 0;
		}

		return geFrom && leTo;
	}
	
	@Override
	public Boolean betweenFieldDate(Field field, Field fromField, Field toField, String format) {
		Date fieldDate = toDate(field, format);
		Date from = toDate(fromField, format);
		Date to = toDate(toField, format);
		Boolean geFrom = true;
		Boolean leTo = true;

		if (fieldDate == null) {
			return false;
		}

		if (from != null) {
			geFrom = fieldDate.compareTo(from) >= 0;
		}

		if (to != null) {
			leTo = fieldDate.compareTo(to) <= 0;
		}

		return geFrom && leTo;
	}

	@Override
	public Boolean isDate(Field field, String format) {
		return toDate(field, format) != null;
	}

	@Override
	public Integer diffDate(Field fieldA, Field fieldB, String format) {
		return diffDate(fieldA, fieldB, format, false);
	}
	
	@Override
	public Integer diffDateInHours(Field fieldA, Field fieldB, String format) {
		return diffDate(fieldA, fieldB, format, true);
	}
	
	private Integer diffDate(Field fieldA, Field fieldB, String format, Boolean hours) {
		Date dateA = toDate(fieldA, format);
		Date dateB = toDate(fieldB, format);
		Integer diffI = null;

		if (dateA == null || dateB == null) {
			diffI = 0;
		} else {
			Long diffL = (dateB.getTime() - dateA.getTime()) / ( ( hours ? 1 : 24 ) * 60 * 60 * 1000);
			diffI = diffL.intValue();
		}

		return Math.abs(diffI);
	}
	
	@Override
	public String dateToString(Field field, String format) {
		String value = "";
		format = format == null || format.isEmpty() ? defaultFormat : format;
		if(isDate(field, format)) {
			Date date = (Date)field.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			value = sdf.format(date);
		}
		
		return value;
	}
	
	@Override
	public String dateToString(Field field, String formatIn, String formatOut) {
		String value = "";
		
		formatOut = formatOut == null || formatOut.isEmpty() ? defaultFormat : formatOut;
		
		if(isDate(field, formatIn)) {
			Date date = (Date)field.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat(formatOut);
			value = sdf.format(date);
		}
		
		return value;
	}

}
