package it.eng.care.domain.flow.drools.model.lookup;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cache.interceptor.KeyGenerator;

public class KeyGeneratorLookupCache implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder sb = new StringBuilder();

		String tableName = (String) params[0];
		String tableAlias = (String) params[1];
		String[][] filters = (String[][]) params[2];
		Date referenceDate = (Date) params[3];

		sb.append(tableName + "_");
		sb.append(tableAlias + "_");

		if (filters != null && filters.length > 0) {
			for (String[] afilter : filters) {
				if (afilter != null && afilter.length > 0) {
					sb.append(afilter[0] + ":" + afilter[1] + ";");
				}
			}
		}

		if (referenceDate != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmyyyy");
			String refDateString = dateFormat.format(referenceDate);
			sb.append(refDateString);
		}

		return sb.toString();
	}

}
