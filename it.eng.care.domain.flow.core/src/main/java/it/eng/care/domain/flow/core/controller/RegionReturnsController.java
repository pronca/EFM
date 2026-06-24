package it.eng.care.domain.flow.core.controller;

import org.springframework.web.bind.annotation.RequestBody;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.RegionReturnsDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.platform.tool.transport.operations.OperationResult;

public interface RegionReturnsController {

	OperationResult<RegionReturnsDTO> getReturns(@RequestBody FlowViewFilterError FlowViewFilterError);

	FlowOperationResult<Boolean> fileUpload(String extractionId, String fileName, byte[] bytes);

	FlowOperationResult<Boolean> fileUploadTot(String year, String flowId, String versionId, String fileName,
			byte[] bytes);
}
