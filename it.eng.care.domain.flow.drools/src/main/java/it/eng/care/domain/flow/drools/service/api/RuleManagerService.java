package it.eng.care.domain.flow.drools.service.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import it.eng.care.domain.flow.drools.model.dto.FlowRuleDTO;

public interface RuleManagerService {
	
	public List<String> addRule(String versionId, String flowId, File file, String filename, String ruleType) throws IOException;

	public File[] getRules(String flowId, String versionId) throws IOException;
	
	public File[] getRules(String flowId, String versionId, String ruleType) throws IOException;

	public void deleteAllRules(String versionId, String flowId) throws IOException;

	public List<FlowRuleDTO> searchRules(String flowId, String versionId, Boolean useFlowProfile, String ruleType);

	void updateRuleFile(String ruleFileId, File file) throws IOException;

	void deleteRuleFile(String ruleFileId) throws IOException;

	public List<String> addRule(String versionId, String flowId, byte[] byt, String filename, String ruleType) throws IOException;
	
	public List<String> addFunctions(byte[] byt, String filename) throws IOException;

	public File getFunctions() throws IOException;

}
