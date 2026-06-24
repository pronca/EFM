package it.eng.care.domain.flow.drools.service.api;

import java.util.Date;
import java.util.List;

import it.eng.care.domain.flow.drools.model.lookup.ColumnType;
import it.eng.care.domain.flow.drools.model.lookup.LookupTable;

public interface UtilityService {

	public List<Object> executeQuery(String sql, Object[] parameters, ColumnType[] type);

	public Object executeUniqueQuery(String sql, Object[] parameters, ColumnType[] type);

	public Integer executeCount(String sql, Object[] parameters, ColumnType[] type);

	public Boolean executeExists(String sql, Object[] parameters, ColumnType[] type);

	public LookupTable loadLookup(String tableName, String tableAlias, String[][] filter, Date referenceDate);

	public LookupTable loadLookupBySql(String fields, String table, String[] filterFields, String[] filterValues, String alias);

	void evictAllCacheValues();

}
