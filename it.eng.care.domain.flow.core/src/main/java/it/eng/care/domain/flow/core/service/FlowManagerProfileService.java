package it.eng.care.domain.flow.core.service;

import java.util.List;

public interface FlowManagerProfileService {
	
	public List<String> getFlows();

	public Boolean checkFlowByName(String flowName);
	
	public Boolean checkFlowById(String flowName);

	public List<String> getFlowIds();
	
	public List<String> getAziendeForUserProfile();

}
