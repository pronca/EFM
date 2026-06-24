package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;

public interface FlowCacheService {

	 FormFlowDTO getCachedFormFlow(String flow, String version);

	FlowTableDO getRootTable(String flowName, String versionName);

	void evictCaches();
}
