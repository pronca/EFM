package it.eng.care.domain.flow.flowupload.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpEntity;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequest;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequestError;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FlowFileUploadController {
		
	public SearchOperationResult<FlowFileUploadRequest> search (BaseSearchInput searchInput);

	HttpEntity<byte[]> downloadFiles(FlowFileUploadRequest flowUpload) throws IOException;

	OperationResult<Boolean> delete(List<FlowFileUploadRequest> flowFileUploadRequestDTOS);

	HttpEntity<byte[]> downloadLogs(FlowFileUploadRequest flowUpload) throws IOException;

	OperationResult<List<String>> getUploadFlowErrors(FlowFileUploadRequest flowFileUploadRequestDTOS);

	SaveOperationResult<String> save(FlowFileUploadRequest flowFileUploadRequest);

	OperationResult<Boolean> saveRequest(String idEstraz, String fileName, String section, String extension, String flowName, byte[] bytes);

	FlowOperationResult<Boolean> deleteFile(BaseSearchInput input);

	OperationResult<HashMap<String, Date>> validation(FlowFileUploadRequest flowFileUploadRequest);

	OperationResult<List<FlowFileUploadRequestError>> checkIncompleteErrors(
			FlowFileUploadRequest flowFileUploadRequest);
	
}
