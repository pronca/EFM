package it.eng.care.domain.flow.tabgen.controller;


import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenField;
import it.eng.care.domain.flow.tabgen.dto.TabgenResultBean;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import org.springframework.http.HttpEntity;

public interface TabgenController {

    SearchOperationResult<Tabgen> searchTable(BaseSearchInput searchInput);


    OperationResult<String> exportTable(BaseSearchInput searchInput);

    OperationResult<Boolean> checkExport(String id);


    OperationResult<String> handleFileUpload(String fileName, String fileType, String anagraficaTableId, byte[] bytes);


    SaveOperationResult<TabgenResultBean> save(Tabgen inputDTO);

    TabgenResultBean deleteTabgen(BaseSearchInput data);

    OperationResult<Tabgen> getEntityBy(String entityKeyName, String entityKeyValue);


    SearchOperationResult<TabgenField> searchFieldsByTabgenId(BaseSearchInput searchInput);

    TabgenResultBean deleteAllValues(Tabgen input);

    TabgenResultBean deleteField(BaseSearchInput data);


    HttpEntity<byte[]> downloadExportedTable(String exportId);


}
