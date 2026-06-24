package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FormFlowRegionController {

    SaveOperationResult<FormFlowDTO> save(FormFlowDTO inputDTO);

    void createTable(FormFlowDTO inputDTO);

    OperationResult<Object> getSectionBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FormFlowDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);

  /*SaveOperationResult<FormFlowDTO> importFormFlowJson(Json formFlowJson);

    Json exportFormFlowJson(@RequestBody FormFlowDTO inputDTO);

    void extractTest(@RequestBody FormFlowDTO inputDTO) throws SQLException;*/

}
