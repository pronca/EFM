package it.eng.care.domain.flow.drools.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.drools.dao.FlowRuleDAO;
import it.eng.care.domain.flow.drools.dao.FlowRuleFileDAO;
import it.eng.care.domain.flow.drools.entity.FlowRuleDO;
import it.eng.care.domain.flow.drools.entity.FlowRuleFileDO;
import it.eng.care.domain.flow.drools.model.dto.FlowRuleDTO;
import it.eng.care.domain.flow.drools.model.rule.RuleType;
import it.eng.care.domain.flow.drools.service.api.DroolsService;
import it.eng.care.domain.flow.drools.service.api.RuleManagerService;

@Service
@Transactional
public class RuleManagerServiceImpl implements RuleManagerService {

	@Autowired
	private FlowRuleDAO flowRuleDAO;

	@Autowired
	private FlowRuleFileDAO flowRuleFileDAO;

	@Autowired
	private FlowDAO flowDAO;

	@Autowired
	private DroolsService droolsService;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;
	
	@Override
	public List<String> addRule(String versionId, String flowId, File file, String filename, String ruleType) throws IOException {
		return addRule(versionId, flowId, FileUtils.readFileToByteArray(file), filename, ruleType);
	}
	
	@Override
	public List<String> addRule(String versionId, String flowId, byte[] byt, String filename, String ruleType) throws IOException {

	    File tmp = File.createTempFile(filename.replace(".drl", ""), ".drl");
	    List<String> checkResults;
	    try {
	        FileUtils.writeByteArrayToFile(tmp, byt);
	        checkResults = droolsService.checkRule(tmp, filename, flowId, versionId);
	    } finally {
	        tmp.delete();
	    }

	    if (checkResults != null && !checkResults.isEmpty()) {
	        return checkResults;
	    }

	    FlowRuleDO flowRule = flowRuleDAO
	    	    .findFirstByFlow_IdAndVersion_Id(flowId, versionId)
	    	    .orElseGet(() -> {
	    	        FlowRuleDO fr = new FlowRuleDO();

	    	        FlowDO flow = new FlowDO();
	    	        flow.setId(flowId);

	    	        VersionDO version = new VersionDO();
	    	        version.setId(versionId);

	    	        fr.setFlow(flow);
	    	        fr.setVersion(version);
	    	        fr.setFiles(new ArrayList<>());
	    	        return fr;
	    	    });


	    if (flowRule.getFiles() == null) {
	        flowRule.setFiles(new ArrayList<>());
	    }

	    FlowRuleFileDO ruleFile = new FlowRuleFileDO();
	    ruleFile.setId(UUID.randomUUID().toString());
	    ruleFile.setRule(byt);
	    ruleFile.setFilename(filename.replace(".drl", ""));
	    ruleFile.setFlowRule(flowRule);

	    if ("1".equals(ruleType)) {
	        ruleFile.setRuleType(RuleType.FLOW.name());
	    } else if ("2".equals(ruleType)) {
	        ruleFile.setRuleType(RuleType.CROSS.name());
	    }

	    flowRule.getFiles().add(ruleFile);

	    flowRuleDAO.save(flowRule);

	    return checkResults; // che è vuota o null a questo punto
	}

	@Override
	public List<String> addFunctions(byte[] byt, String filename) throws IOException {
		File tmp = File.createTempFile(filename.replace(".drl", ""), "drl");
		FileUtils.writeByteArrayToFile(tmp, byt);
		List<String> checkResults = droolsService.checkSingleFile(tmp, filename);
		tmp.delete();
		
		if(checkResults != null && !checkResults.isEmpty()) {
			return checkResults;
		}
		
		List<FlowRuleDO> ruleFunctions = flowRuleFileDAO.searchFiles(null, null, RuleType.FUNCTION.name());
		FlowRuleDO ruleFunction = null;
		
		if (ruleFunctions == null || ruleFunctions.isEmpty()) {
			ruleFunction = new FlowRuleDO();
		} else {
			ruleFunction = ruleFunctions.get(0);
			flowRuleFileDAO.deleteById(ruleFunction.getId());
		}
		
		ruleFunction.setFiles(new ArrayList<FlowRuleFileDO>());
		
		FlowRuleFileDO ruleFile = new FlowRuleFileDO();
		ruleFile.setId(UUID.randomUUID().toString());
		ruleFile.setRule(byt);
		ruleFile.setFilename(filename.replace(".drl", ""));
		ruleFile.setFlowRule(ruleFunction);
		ruleFile.setRuleType(RuleType.FUNCTION.name());
		
		ruleFunction.getFiles().add(ruleFile);

		ruleFunction = flowRuleDAO.save(ruleFunction);
		
		return null;
		
	}
	
	@Override
	public void deleteAllRules(String versionId, String flowId) {

	    flowRuleDAO.findFirstByFlow_IdAndVersion_Id(flowId, versionId)
	        .ifPresent(rule -> {
	            if (rule.getFiles() != null && !rule.getFiles().isEmpty()) {
	                flowRuleFileDAO.deleteAllInBatch(rule.getFiles()); // o deleteInBatch se lo hai
	            }
	        });
	}


	@Override
	public void updateRuleFile(String ruleFileId, File file) throws IOException {
		FlowRuleFileDO ruleFile = (ruleFileId== null || ruleFileId.isBlank()) ? null : flowRuleFileDAO.findById(ruleFileId).orElse(null);
		if (ruleFile != null) {
			ruleFile.setRule(FileUtils.readFileToByteArray(file));
		}
	}

	@Override
	public void deleteRuleFile(String ruleFileId) throws IOException {
		flowRuleFileDAO.deleteById(ruleFileId);
	}
	
	@Override
	public File[] getRules(String flowId, String versionId, String ruleType) throws IOException {
		FlowDO flow = (flowId== null || flowId.isBlank()) ? null : flowDAO.findById(flowId).orElse(null);
		List<File> droolsFiles = null;
		List<FlowRuleDO> frList = flowRuleFileDAO.searchFiles(flow.getCode(), versionId, ruleType);

		if (frList != null && !frList.isEmpty()) {
			int index = 0;
			for (FlowRuleDO flowRule : frList) {
				List<FlowRuleFileDO> files = flowRule.getFiles();
				if(files != null && !files.isEmpty()) {
					droolsFiles = new ArrayList();
					for(FlowRuleFileDO flowRuleFile : files) {
						if(StringUtils.isEmpty(ruleType) || flowRuleFile.getRuleType().equals(ruleType)) {
							byte[] byt = flowRuleFile.getRule();
							File droolsFile = File.createTempFile(
									"file " + index +  "_" + flowRuleFile.getFilename() , ".drl");
							FileUtils.writeByteArrayToFile(droolsFile, byt);
							droolsFiles.add(droolsFile);
							index++;
						}
					}
				}
			}
		}

		if(droolsFiles != null) {
			File[] files = new File[droolsFiles.size()];
			if(droolsFiles != null) {
				for(int i = 0; i < droolsFiles.size(); i++) {
					files[i] = droolsFiles.get(i);
				}
			}
			return files;
		}
		
		return null;
		
	}

	@Override
	public File[] getRules(String flowId, String versionId) throws IOException {
		return getRules(flowId, versionId, null);
	}
	
	@Override
	public File getFunctions() throws IOException {
		File droolsFile = null;

		FlowRuleFileDO file = new FlowRuleFileDO();
		file.setRuleType(RuleType.FUNCTION.name());
		
		FlowRuleFileDO flowRuleFile = (file.getId() == null || file.getId().isBlank()) ? null : flowRuleFileDAO.findById(file.getId()).orElse(null);

		if (flowRuleFile != null) {
			byte[] byt = flowRuleFile.getRule();
			droolsFile = File.createTempFile(flowRuleFile.getFilename() , ".drl");
			FileUtils.writeByteArrayToFile(droolsFile, byt);
		}
		
		return droolsFile;
	}

	@Override
	public List<FlowRuleDTO> searchRules(String flowCode, String versionId, Boolean useFlowProfile, String ruleType) {
		
		List<String> flows = new ArrayList<String>();
		if(useFlowProfile) {
			flows = flowManagerProfileService.getFlows();
		}
		
		List<FlowRuleDTO> result = new ArrayList<FlowRuleDTO>();
		List<FlowRuleDO> frList = flowRuleFileDAO.searchFiles(flowCode, versionId, ruleType);
		if (frList != null) {
			for (FlowRuleDO flowRuleDO : frList) {
				
				if(useFlowProfile && !flows.contains(flowRuleDO.getFlow().getName())) {
					continue;
				}
				
				FlowRuleDTO rule = new FlowRuleDTO();
				rule.setId(flowRuleDO.getId());
				rule.setFlowId(flowRuleDO.getFlow().getId());
				rule.setFlowName(flowRuleDO.getFlow().getName());
				rule.setFlowDescription(flowRuleDO.getFlow().getDescription());
				rule.setFlowType(flowRuleDO.getFlow().getFlowType());
				rule.setVersionId(flowRuleDO.getVersion().getId());
				rule.setVersionName(flowRuleDO.getVersion().getVersion());
				rule.setVersionDescription(flowRuleDO.getVersion().getDescription());
				if (flowRuleDO.getFiles() != null) {
					rule.setNumberOfRules(flowRuleDO.getFiles().size());
				}
				result.add(rule);
			}
		}
		return result;
	}

}
