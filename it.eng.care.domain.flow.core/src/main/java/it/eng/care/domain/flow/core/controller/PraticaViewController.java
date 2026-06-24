package it.eng.care.domain.flow.core.controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;

import it.eng.care.domain.flow.b2b.exception.ValidationFlowException;
import it.eng.care.domain.flow.core.dto.ContextParamDTO;
import it.eng.care.domain.flow.core.dto.PaginatedPraticaDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;

public interface PraticaViewController {

    OperationResult<PaginatedPraticaDTO> searchTablesError(FlowViewFilterError flowViewFilterError) throws ValidationFlowException;

    OperationResult<PaginatedPraticaDTO> searchErrors(FlowViewFilterError flowViewFilterError);

    HttpEntity<byte[]> downloadFlowViewXlsx(BaseSearchInput searchInput) throws IOException;

    HttpEntity<byte[]> downloadFlowViewXlsx2(@RequestBody BaseSearchInput searchInput) throws IOException;

    OperationResult<ContextParamDTO> searchContextParam(BaseSearchInput input);

}
