package it.eng.care.domain.flow.core.service;

import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowDrgDTO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;

public interface FlowDrgService {
	
	public List<FlowDrgDTO> searchDTOByExtractionId(String extractionId);

	public void createDrgInvocationResults(String extractionId);

	public void saveDrgResult(FlowDrgDO drgResult);

	public List<FlowDrgDO> searchByExtractionId(String extractionId);

	void startDrgInvocationResults(FlowDrgDO drg, boolean valid);

}
