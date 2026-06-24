package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.DriverDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface DriverController {

    SaveOperationResult<DriverDTO> save(DriverDTO inputDTO);

    OperationResult<DriverDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<DriverDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);
 
}
