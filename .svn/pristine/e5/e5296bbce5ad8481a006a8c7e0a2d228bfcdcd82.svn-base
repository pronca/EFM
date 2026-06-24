package it.eng.care.domain.flow.drools.controller;

import org.springframework.http.HttpEntity;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.drools.model.dto.FlowRuleDTO;
import it.eng.care.domain.flow.drools.model.rule.RulesDownloadRequest;
import it.eng.care.domain.flow.drools.model.rule.RulesUploadResponse;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface ValidatorController {

	public FlowOperationResult<?> validateRecord(ValidationFilter validationFilter);

	public HttpEntity<byte[]> downloadRules(RulesDownloadRequest filter);

	public SearchOperationResult<FlowRuleDTO> search(BaseSearchInput searchInput);

	public RulesUploadResponse uploadRule(String flowId, String versionId, String ruleType, String fileName, String fileType, byte[] bytes);

	public OperationResult<String> deleteAllRules(RulesDownloadRequest request);

	public RulesUploadResponse uploadFunctions(String fileName, String fileType, byte[] bytes);

	public HttpEntity<byte[]> downloadFunctions();

	public FlowOperationResult<?> resetValidation(ValidationFilter request);

	public FlowOperationResult<?> crossValidation(ValidationFilter request);

	public FlowOperationResult<?> validateRecords(ValidationFilter validationFilter);

	public FlowOperationResult<Boolean> checkValidation(BaseSearchInput searchInput);

	public FlowOperationResult<Boolean> checkResetJob(BaseSearchInput searchInput);

	public FlowOperationResult<Boolean> checkSecondLevelJob(BaseSearchInput searchInput);
	
}
