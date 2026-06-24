package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FieldTypeDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FieldTypeController {

    SaveOperationResult<FieldTypeDTO> save(FieldTypeDTO inputDTO);

    OperationResult<FieldTypeDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FieldTypeDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);

}
