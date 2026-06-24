package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;


public interface FlowImportRequestController {

    SearchOperationResult<FormFlowTableFieldDTO> getDataFields(BaseSearchInput searchInput);

    SearchOperationResult<FlowImportRequestDTO> search(BaseSearchInput baseSearchInput);

    SaveOperationResult<FlowImportRequestDTO> save(FlowImportRequestDTO flowExtractDTO);

    Integer deleteEntityBy(FlowImportRequestDTO flowImportRequestDTO);

    //TODO annulla imporetazione 

    SaveOperationResult<FlowImportRequestDTO> startImport(FlowImportRequestDTO flowExtractDTO);

}
