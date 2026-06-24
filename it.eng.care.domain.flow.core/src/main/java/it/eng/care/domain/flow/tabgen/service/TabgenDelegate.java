package it.eng.care.domain.flow.tabgen.service;

import it.eng.care.domain.flow.tabgen.dto.*;

import java.util.List;

public interface TabgenDelegate {
	
	public TabgenResultBean saveTable(Tabgen table);

	public TabgenResultBean deleteTable(String id, boolean deleteAll, boolean deleteField, boolean deleteValue);

	public List<Tabgen> searchTable(TabgenFilter filter);

	public TabgenResultBean saveValue(TabgenValue value);

	public TabgenResultBean deleteValue(String valueId, boolean force);
	
	public String exportTable(TabgenValueFilter filter);

	public boolean checkExport(String id);
	
	public BasePagingLoadResult<Tabgen> searchValue(TabgenValueFilter filter);

	public TabgenResultBean saveFields(List<TabgenField> fields, Boolean aggiornaProgressivo);

	public TabgenResultBean deleteField(String id, boolean deleteValuesAndConstraint);

	public TabgenResultBean deleteFields(String tabgenid, boolean deleteValuesAndConstraint);

	public TabgenResultBean deleteAllValues(String tabgenid, boolean deleteConstrains);
	
	public byte[] downloadExportedTable(String exportId);
	
	public List<TabgenField> searchFieldsByTabgenId(String tabgenId);

}
