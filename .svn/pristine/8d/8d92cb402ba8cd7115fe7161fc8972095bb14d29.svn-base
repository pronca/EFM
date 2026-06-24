package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FlowConfigurationFilterFieldController {

    SaveOperationResult<FlowConfigurationFilterFieldDTO> save(FlowConfigurationFilterFieldDTO inputDTO);

    OperationResult<FlowConfigurationFilterFieldDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FlowConfigurationFilterFieldDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(FlowConfigurationFilterFieldDTO dto);


}
