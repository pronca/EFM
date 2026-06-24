package it.eng.care.domain.flow.drools.service.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kie.api.runtime.KieSession;

import it.eng.care.domain.flow.core.entity.SecondLevelValidationRequestDO;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.model.rule.KieSessionWrapper;

public interface DroolsService {

	public List<ValidationBean> validateBeans(List<ValidationBean> beans, KieSession kieSession) throws Exception;

	void resetLookupMap();

	List<String> checkRule(File file, String filename, String flowId, String versionId) throws IOException;

	List<String> checkSingleFile(File file, String filename);

	public KieSessionWrapper createKieSession(String flowId, String versionId, String ruleType,
			SecondLevelValidationRequestDO secLevReq) throws Exception;

}
