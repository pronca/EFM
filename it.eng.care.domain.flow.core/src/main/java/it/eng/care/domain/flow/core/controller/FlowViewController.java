package it.eng.care.domain.flow.core.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;

import it.eng.care.domain.flow.core.dto.PaginatedPraticaDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;

public interface FlowViewController {

    OperationResult<String> getVersionNameById(BaseSearchInput searchInput);

    OperationResult<List<Object>> searchTables(FlowViewFilter flowViewFilter);

    OperationResult<PaginatedPraticaDTO> getErrors(FlowViewFilterError flowViewFilterError);

    HttpEntity<byte[]> downloadFlowViewXlsx(FlowViewFilter flowViewFilter) throws IOException;

	OperationResult<List<Object>> countTables(FlowViewFilter flowViewFilter);


}