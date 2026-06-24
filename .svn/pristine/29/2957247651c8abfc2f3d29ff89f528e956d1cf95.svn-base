package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.CheckExtractResultDTO;
import it.eng.care.domain.flow.core.dto.FlowExportRequestDTO;
import it.eng.care.domain.flow.webservice.model.SimulateFlow;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface FlowExportRequestController {

    SearchOperationResult<FlowExportRequestDTO> search(BaseSearchInput baseSearchInput);

    SaveOperationResult<FlowExportRequestDTO> save(FlowExportRequestDTO flowExtractDTO);

    OperationResult<Boolean> delete(List<FlowExportRequestDTO> flowExportRequestDTOS);

    SaveOperationResult<FlowExportRequestDTO> startExtraction(FlowExportRequestDTO flowExportDTO);

    OperationResult<HashMap<String, Object>> getFlowTableFieldByFlowAndVersion(@RequestBody BaseSearchInput searchInput);

	HttpEntity<byte[]> downloadXML(String exportId) throws IOException;

	HttpEntity<byte[]> downloadLOG(String exportId) throws IOException;

	SearchOperationResult<FlowExportRequestDTO> count(BaseSearchInput searchInput);

	OperationResult<CheckExtractResultDTO> checkExtractObsoleta(FlowExportRequestDTO flowExportDTO) throws SQLException;

	SaveOperationResult<FlowExportRequestDTO> startExtractionIn(FlowExportRequestDTO flowExportDTO, Boolean checkFlow);

	OperationResult<List<FlowExportRequestDTO>> pingServiceExtraction(List<FlowExportRequestDTO> flowExportDTOList);

	OperationResult<String> killExtraction(String exportId);

	OperationResult<String> sconsolidaPratiche(FlowExportRequestDTO flowExportDTO);

	HttpEntity<byte[]> downloadSimulation(SimulateFlow sim) throws IOException;

	OperationResult<String> simulateFlowDrg(SimulateFlow sim) throws IOException;

	void createDrgRequest(String id);
}
