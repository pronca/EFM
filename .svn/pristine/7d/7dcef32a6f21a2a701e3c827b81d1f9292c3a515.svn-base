package it.eng.care.domain.flow.drools.utility.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.drools.model.lookup.LookupRow;
import it.eng.care.domain.flow.drools.model.lookup.LookupTable;
import it.eng.care.domain.flow.drools.service.api.UtilityService;
import it.eng.care.domain.flow.drools.utility.api.LookupUtility;

public class LookupUtilityImpl implements LookupUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(LookupUtilityImpl.class);

	@Autowired
	private UtilityService service;

	// private Map<String, LookupTable> lookupMap = new HashMap<String, LookupTable>();
	
	private static final ThreadLocal<HashMap<String, LookupTable>> lookupMap = new ThreadLocal<HashMap<String,LookupTable>>() {
		protected HashMap<String,LookupTable> initialValue() {
			return new HashMap<String, LookupTable>();
		};
	};
	
	@Override
	public Boolean loadLookupBySql(String fields, String table, String[] filterFields, String[] filterValues, String alias) {
		LookupTable lookup = service.loadLookupBySql(fields, table, filterFields, filterValues, alias);
		if(lookup != null) {
			putLookup(alias, alias, lookup);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean loadLookup(String tableName) {
		return loadLookup(tableName, tableName, null);
	}

	@Override
	public Boolean loadLookup(String tableName, String tableAlias, Date referenceDate) {
		return loadLookup(tableName, tableAlias, null, referenceDate);
	}

	@Override
	public Boolean loadLookup(String tableName, String tableAlias, String[][] filter, Date referenceDate) {
		if(tableName != null && !tableName.isEmpty()) {
			LookupTable lookup = service.loadLookup(tableName, tableAlias, filter, referenceDate);
			if(lookup != null) {
				putLookup(tableName, tableAlias, lookup);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean lookupExists(String lookupName, String[][] filter, Date referenceDate) {
		return lookupSelect(lookupName, filter, referenceDate, true).size() > 0;
	}
	
	@Override
	public Boolean lookupExists(String lookupName, String[][] filter) {
		return lookupExists(lookupName, filter, null);
	}

	@Override
	public List<LookupRow> lookupSelect(String lookupName, String[][] filter, Date referenceDate) {
		return lookupSelect(lookupName, filter, referenceDate, false);
	}

	@Override
	public LookupRow lookupSelectFirst(String lookupName, String[][] filter, Date referenceDate) {
		List<LookupRow> list = lookupSelect(lookupName, filter, referenceDate, true);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public void resetLookupMap() {
		if(LookupUtilityImpl.lookupMap != null) {
			LookupUtilityImpl.lookupMap.get().clear();
		}
	}
	
	@Override
	public Boolean sqlLookupExists(String lookupName, String[] selectFields) {
		
		if(selectFields == null || selectFields.length == 0) {
			return true;
		}
		
		if(lookupName == null || lookupName.isEmpty()) {
			return false;
		}
		
		LookupTable lookup = LookupUtilityImpl.lookupMap.get().get(lookupName);
		
		if (lookup != null && lookup.getRowMap() != null && !lookup.getRowMap().isEmpty()) {
			String key = "";
			for(int i = 0; i < selectFields.length; i++) {
				String field = selectFields[i];
				field = field != null ? field : "";
				key += field + ":";
			}

			return lookup.getRowMap().containsKey(key);
		} 
		
		return false;
	}

	private List<LookupRow> lookupSelect(String lookupName, String[][] filter, Date referenceDate, Boolean onlyFirst) {
		List<LookupRow> foundedRows = new ArrayList<LookupRow>();

		if(lookupName == null || lookupName.isEmpty()) {
			return foundedRows;
		}
		
		LookupTable lookup = LookupUtilityImpl.lookupMap.get().get(lookupName);
		
		if (lookup != null && lookup.getRows() != null && !lookup.getRows().isEmpty()) {

			List<LookupRow> rows = lookup.getRows();

			for (LookupRow lookupRow : rows) {
				
				Boolean rowExists = true;

				if (referenceDate != null) {
					Date dtenable = lookupRow.getDtEnable();
					Date dtdisable = lookupRow.getDtDisable();
					rowExists = !(referenceDate.before(dtenable) || referenceDate.after(dtdisable));
				}
				
				if (rowExists && filter != null && filter.length > 0) {
					for (String[] filterField : filter) {
						if(filterField != null && filterField.length > 0) {
							String filterName = filterField[0];
							String filterValue = filterField[1];
							
							if(filterName != null && !filterName.isEmpty()) {
								if(!lookupRow.fieldExists(filterName)) {
									return new ArrayList<LookupRow>();
								}
								Object lookupField = lookupRow.getFieldValue(filterName);
								
								if(lookupField == null || filterValue == null || filterValue.isEmpty()) {
									if(lookupField != null || filterValue != null && filterValue.isEmpty()) {
										rowExists = false;
										break;
									}
								} else if (!lookupField.equals(filterValue)) {
									rowExists = false;
									break;
								}
							}
						}
					}
				}
				
				if (rowExists) {
					foundedRows.add(lookupRow);
					if (onlyFirst) {
						break;
					}
				}

			}

		}

		return foundedRows;
	}

	private void putLookup(String tableName, String tableAlias, LookupTable lookup) {
		if (tableAlias != null && !tableAlias.trim().isEmpty()) {
			tableName = tableAlias;
		}

		if (lookup == null) {
			LookupUtilityImpl.lookupMap.get().remove(tableName);
		} else {
			LookupUtilityImpl.lookupMap.get().put(tableName, lookup);
		}
	}

}
