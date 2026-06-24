package it.eng.care.domain.flow.drools.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import it.eng.care.domain.flow.core.service.QueryGenerator;
import it.eng.care.domain.flow.drools.dao.UtilityDAO;
import it.eng.care.domain.flow.drools.model.lookup.ColumnType;
import it.eng.care.domain.flow.drools.model.lookup.LookupField;
import it.eng.care.domain.flow.drools.model.lookup.LookupRow;
import it.eng.care.domain.flow.drools.model.lookup.LookupTable;
import it.eng.care.domain.flow.drools.service.api.UtilityService;
import it.eng.care.domain.flow.tabgen.dao.TabgenFieldDAO;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class UtilityServiceImpl implements UtilityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityServiceImpl.class);

	@Autowired
	private UtilityDAO utilityDAO;

	@Autowired
	private TabgenFieldDAO tabgenFieldDAO;

	@Override
	public List<Object> executeQuery(String sqlString, Object[] parameters, ColumnType[] types) {
		List<Object> result = utilityDAO.executeQuery(sqlString, parameters, types);
		return result;
	}

	@Override
	public Object executeUniqueQuery(String sqlString, Object[] parameters, ColumnType[] types) {
		Object result = utilityDAO.executeUniqueQuery(sqlString, parameters, types);
		return result;
	}

	@Override
	public Integer executeCount(String sqlString, Object[] parameters, ColumnType[] types) {
		Integer result = utilityDAO.executeCount(sqlString, parameters, types);
		return result;
	}

	@Override
	public Boolean executeExists(String sqlString, Object[] parameters, ColumnType[] types) {
		Boolean result = false;
		result = utilityDAO.executeExists(sqlString, parameters, types);
		return result;
	}
	
	@Override
	public LookupTable loadLookupBySql(String selectFields, String table, String[] filterFields, String[] filterValues, String alias) {
		LOGGER.info("**** LOAD LOOKUP BY SQL : " + alias + " ****");
		
		String sql = QueryGenerator.generateLookupQuery(selectFields, table, filterFields);
		
		ColumnType[] types = new ColumnType[filterValues.length];
		for(int i = 0; i < filterValues.length; i++) {
			types[i] = ColumnType.STRING;
		}
		
		List<Object> dbRows = utilityDAO.executeQuery(sql, filterValues, types);
		
		LOGGER.info("**** LOAD LOOKUP BY SQL : " + sql + " fine query " + filterValues +" ****");
		for(String s : filterValues) {
			LOGGER.info("**** LOAD LOOKUP BY SQL : param query " + s +" ****");	
		}
		
		if (dbRows != null && !dbRows.isEmpty()) {
			String[] fields = selectFields.split(",");
			
			LookupTable lookupTable = new LookupTable();
			lookupTable.setName(alias);
			lookupTable.setAlias(alias);
			Map<String, LookupRow> lookupRows = new HashMap<String, LookupRow>();
			lookupTable.setRowMap(lookupRows);
			for (Object dbrow_ : dbRows) {
				Object[] dbrow = (Object[]) dbrow_;
				LookupRow lookupRow = new LookupRow();
				Map<String, LookupField> lookupFields = new HashMap<String, LookupField>();
				lookupRow.setFields(lookupFields);
				String key = "";
				for (int col = 0; col < dbrow.length; col++) {
					LookupField lookupField = new LookupField();
					lookupField.setName(fields[col].trim().toUpperCase());
					lookupField.setValue(dbrow[col]);
					lookupFields.put(lookupField.getName(), lookupField);
					key += (lookupField.getValue() != null ? lookupField.getValue() : "") + ":";
				}

				lookupRows.put(key, lookupRow);
			}
			
			LOGGER.info("**** LOAD LOOKUP BY SQL : " + alias + " return ****");
			
			return lookupTable;
		}
		
		LOGGER.info("**** LOAD LOOKUP BY SQL : " + alias + " null ****");
		
		return null;
	}
	
	@CacheEvict(value = "lookupCache", allEntries = true)
	@Override
	public void evictAllCacheValues() {}

	@Cacheable(value = "lookupCache", keyGenerator = "lookupCacheKeyGenerator")
	@Override
	public LookupTable loadLookup(String tableName, String tableAlias, String[][] filter, Date referenceDate) {
		LOGGER.info("**** LOAD LOOKUP : " + tableName + " ****");

		BaseSearchInput tabgenfilter = new BaseSearchInput();
		tabgenfilter.setValue("tabgenId", tableName);
		List<TabgenFieldDO> tabgenFieldList = tabgenFieldDAO.searchTabgenFieldByFilter(tabgenfilter);

		if(tabgenFieldList != null && !tabgenFieldList.isEmpty()) {
			List<Object[]> dbRows = utilityDAO.loadLookup(tableName, filter, referenceDate, tabgenFieldList);
	
			if (dbRows != null && !dbRows.isEmpty()) {
				LookupTable lookupTable = new LookupTable();
				lookupTable.setName(tableName);
				lookupTable.setAlias(tableAlias);
				List<LookupRow> lookupRows = new ArrayList<LookupRow>();
				lookupTable.setRows(lookupRows);
				for (Object[] dbrow : dbRows) {
					LookupRow lookupRow = new LookupRow();
					lookupRows.add(lookupRow);
					Map<String, LookupField> lookupFields = new HashMap<String, LookupField>();
					lookupRow.setFields(lookupFields);
					int i = 0, size = dbrow.length;
					for (Object dbcolumn : dbrow) {
						if (i == size - 2) {
							lookupRow.setDtEnable((Date) dbcolumn);
						} else if (i == size - 1) {
							lookupRow.setDtDisable((Date) dbcolumn);
						} else {
							LookupField lookupField = new LookupField();
							String columnName = tabgenFieldList.get(i).getDescription();
							lookupField.setName(columnName);
							lookupField.setValue(dbcolumn);
							lookupFields.put(columnName.toUpperCase(), lookupField);
						}
	
						i++;
					}
				}
	
				LOGGER.info("**** LOAD LOOKUP - SIZE " + dbRows.size() + " ****");
	
				return lookupTable;
			}
		}
		return null;
	}

}
