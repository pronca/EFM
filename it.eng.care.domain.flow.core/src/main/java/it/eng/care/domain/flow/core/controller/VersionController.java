package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface VersionController {

    SaveOperationResult<VersionDTO> save(VersionDTO inputDTO);

    OperationResult<VersionDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<VersionDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);

}
