package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

import java.util.Set;

public interface FlowConfigurationFilterController {

    SaveOperationResult<FlowConfigurationFilterDTO> save(FlowConfigurationFilterDTO inputDTO);

    OperationResult<FlowConfigurationFilterDTO> getEntityBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FlowConfigurationFilterDTO> search(BaseSearchInput searchInput);

    SearchOperationResult<FormFlowTableFieldDTO> getAllFlowFields(BaseSearchInput searchInput);

    OperationResult<Set<String>> deleteEntityBy(FlowConfigurationFilterDTO dto);
}
