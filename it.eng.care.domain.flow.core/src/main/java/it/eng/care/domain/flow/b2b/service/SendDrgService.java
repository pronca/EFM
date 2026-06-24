package it.eng.care.domain.flow.b2b.service;

import java.util.List;

import it.eng.care.domain.flow.core.entity.SendDrgDO;

public interface SendDrgService {

	List<SendDrgDO> findAllPendingDrgs(int numErr);
	
	public void saveOrUpdateEvent(SendDrgDO error);

	String restCall(String jsonString, String flowName) throws Exception;

	void getPendingDrgAndSend() throws Exception;
}
