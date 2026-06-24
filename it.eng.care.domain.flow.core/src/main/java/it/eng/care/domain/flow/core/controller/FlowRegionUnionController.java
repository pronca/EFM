package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FlowRegionUnionDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FlowRegionUnionController {

    SaveOperationResult<FlowRegionUnionDTO> save(FlowRegionUnionDTO inputDTO);

    OperationResult<FlowRegionUnionDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FlowRegionUnionDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);

    FormFlowDTO getFmFlowByFlowAzienda(BaseSearchInput baseSearchInput);
}
