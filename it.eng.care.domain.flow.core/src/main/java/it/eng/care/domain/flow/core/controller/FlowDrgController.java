package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.FlowDrgDTO;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FlowDrgController {

	public SearchOperationResult<FlowDrgDTO> searchByExtractionId(String extractionId);

}
