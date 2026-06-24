package it.eng.care.domain.flow.drools.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.TemporalType;

import org.hibernate.query.NativeQuery;

import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.drools.model.lookup.ColumnType;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;

public class UtilityDAO {

	@Autowired
	private EntityManager entityManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityDAO.class);

	public List<Object> executeQuery(String sqlString, Object[] parameters, ColumnType[] types) {
		List<Object> result = (List<Object>) executeQuery(sqlString, parameters, 0, false, types);
		return result;
	}

	public Object executeUniqueQuery(String sqlString, Object[] parameters, ColumnType[] types) {
		Object result = null;
		List<Object> list = (List<Object>) executeQuery(sqlString, parameters, 1, false, types);
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}

		return result;
	}

	public Integer executeCount(String sqlString, Object[] parameters, ColumnType[] types) {
		Integer count = (Integer) executeQuery(sqlString, parameters, 0, true, types);
		return count;
	}

	public Boolean executeExists(String sqlString, Object[] parameters, ColumnType[] types) {
		Object result = executeUniqueQuery(sqlString, parameters,types);
		return result != null;
	}

	public List loadLookup(String lookupName, String[][] filter, Date referenceDate, List<TabgenFieldDO> fields) {
		lookupName = cleanString(lookupName);

		String select = "";
		for (TabgenFieldDO field : fields) {
			select += cleanString(field.getDescription()) + " , ";
		}

		select += " DT_ENABLE , DT_DISABLE ";

		String sqlString = "select " + select + " from " + lookupName + " ";
		List<Object> parameters = new ArrayList<Object>();

		boolean filtered = false;
		
		String whereCondition = "";
		if (filter != null && filter != null && filter.length > 0) {
			for (String[] field : filter) {
				if (field != null && field.length > 0 && field[0] != null && !field[0].isEmpty()) {
					filtered = true;
					String name = field[0];
					name = cleanString(name);
					if(field[1] != null) {
						whereCondition += name + " = ? and ";
						parameters.add(field[1]);
					} else {
						whereCondition += name + " is null and ";
					}
				}
			}
			if (filtered) {
				whereCondition = whereCondition.substring(0, whereCondition.length() - 4);
			}
		}
		
		if(filtered) {
			sqlString += " where " + whereCondition;
		}

		if (referenceDate != null) {
			String str = filtered ? " and " : " where ";
			sqlString += str + " ? between DT_ENABLE and DT_DISABLE ";
			parameters.add(referenceDate);
		}
		NativeQuery query = entityManager.createNativeQuery(sqlString).unwrap(NativeQuery.class);
		query = setParameters(query, parameters.toArray(), null);

		List<?> result = query.getResultList();
		return result;
	}

	private Object executeQuery(String sqlString, Object[] parameters, Integer maxResults, Boolean isCount, ColumnType[] types) {
		if(parameters != null && parameters.length > 0) {
			if(types == null || types.length == 0) {
				LOGGER.error("Definizione del tipo dei parametri assente. La query non verrà eseguita");
				if(isCount) {
					return -1;
				} else {
					return null;
				}
			}
		}
		NativeQuery query = entityManager.createNativeQuery(sqlString).unwrap(NativeQuery.class);

		int occ = countOccurrencesOf(sqlString, "?");
		if(occ > 0) {
			// se il numero di parametri è maggiore delle occorenze di ? allora taglia i parametri, se invece è minore riempi con null
			if(parameters == null) {
				parameters = new Object[0];				
			}
			
			if(parameters.length > occ) {
				LOGGER.warn("Numero di parametri forniti (" + parameters.length + ")  maggiore del numero di occorrenze della stringa '?' (" + occ + ") . I parametri superflui verranno ignorati");
			} else if(parameters.length < occ) {
				LOGGER.error("Numero di parametri forniti (" + parameters.length + ")  minore del numero di occorrenze della stringa '?' (" + occ + ") . La query non verrà eseguita");
				if(isCount) {
					return -1;
				} else {
					return null;
				}
			}
			
			if(types.length < occ) {
				LOGGER.error("Per alcuni parametri non è stato definito il tipo. La query non verrà eseguita");
				if(isCount) {
					return -1;
				} else {
					return null;
				}
			}
			
			parameters = Arrays.copyOf(parameters, occ);
			types = Arrays.copyOf(types, occ);
			
			
			query = setParameters(query, parameters, types);
		}
		
		if (maxResults != null && maxResults > 0) {
			query.setMaxResults(1);
		}

		if (isCount) {
			Integer count = ((Number) query.getSingleResult()).intValue();
			return count;
		} else {
			List<Object> list = query.getResultList();
			return list;
		}

	}

	private NativeQuery setParameters(NativeQuery query, Object[] parameters, ColumnType[] types) {
		if (parameters != null && parameters.length > 0) {
			int position = 1;
			for (int paramIndex = 0; paramIndex < parameters.length; paramIndex++) {
				Object value = parameters[paramIndex];
				
				ColumnType columnType = null;
				if(types != null && paramIndex < types.length) {
					columnType = types[paramIndex];
				}
				
				if(value == null) {
					if(columnType != null) {
						switch (columnType) {
							case BOOLEAN:
								query.setParameter(position, null, StandardBasicTypes.BOOLEAN);
								break;
							default: 
								query.setParameter(position, value);
								break;
						}
					} else {
						query.setParameter(position, value);
					}
				} else if (value instanceof Date) {
					query.setParameter(position, (Date) value, TemporalType.DATE);
				} else {
					query.setParameter(position, value);
				}
				position++;
			}
		}

		return query;
	}

	private String cleanString(String string) {
		string = string.replaceAll(" ", "");
		return string;
	}
	
	public static int countOccurrencesOf(String str, String sub) {
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

}
