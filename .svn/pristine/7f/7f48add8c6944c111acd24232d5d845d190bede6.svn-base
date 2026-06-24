package it.eng.care.domain.flow.core.controller;

import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FlowController {

    SaveOperationResult<FlowDTO> save(FlowDTO inputDTO);

    OperationResult<FlowDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FlowDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);
    
    OperationResult<List<String>> searchCodiciPresidio(BaseSearchInput searchInput);
    
    OperationResult<List<String>> searchCodiciAzienda(BaseSearchInput searchInput);
    
    OperationResult<List<String>> searchCodAzCaricamentoFile(BaseSearchInput searchInput);

}
