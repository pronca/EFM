package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface DataSourceController {

    SaveOperationResult<DataSourceDTO> save(DataSourceDTO inputDTO);

    OperationResult<DataSourceDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<DataSourceDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);

}
