package it.eng.care.domain.flow.flowupload.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequest;
import it.eng.care.domain.flow.flowupload.bean.WellFormedStatusEnum;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestErrorDO;
import it.eng.care.domain.flow.flowupload.model.SectionFileDO;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventActionEnum;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

public interface FlowFileUploadService {

	public FlowOperationResult<String> upload(FlowFileUploadRequest request);

	public WellFormedStatusEnum checkUploadedFiles(String requestId) throws IOException, SQLException;

	public FlowOperationResult<Boolean> deleteFile(String fileId);

	public FlowOperationResult<Boolean> deleteRequest(String id);

	Pair<List<FlowFileUploadRequestDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);
	
	List<SectionFileDO> getFlowSectionFile(FlowFileUploadRequest flowUploadId);
	
	List<String> getFlowErrors(FlowFileUploadRequest flowUploadId);

	FlowOperationResult<Boolean> uploadFile(String requestId, byte[] bytes, int section, String extension);

	public FlowOperationResult<Boolean> checkSectionFile(String requestId, int sec);

	List<FlowFileUploadRequestErrorDO> checkIncompleteErrors(FlowFileUploadRequest flowUpload);
	
	public FlowOperationResult<Boolean> insertRecords(String requestId);

	public List<FlowFileUploadRequestDO> getRequestsToValidate();
	
	public void markAsValidated(String status, String extractionId);

	public String getFlowId(String requestId);
	
	byte[] sendAuditDownXMLFileUplToPM(FlowFileUploadRequest flowFileUploadRequest, byte[] byt);
	
	byte[] sendAuditDownLOGFileUplToPM(FlowFileUploadRequest flowFileUploadRequest, byte[] byt);
	
	byte[] sendAuditUploadCaricamentoFile(String idEstraz, String flowName, String fileName, AuditEventActionEnum typeOperation, byte[] byt);
	
	byte[] sendAuditUploadCaricamentoFileDel(String idEstraz, String flowName, String fileName, AuditEventActionEnum typeOperation, byte[] byt);
	
}
