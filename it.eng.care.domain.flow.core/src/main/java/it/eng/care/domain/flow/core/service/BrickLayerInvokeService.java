package it.eng.care.domain.flow.core.service;

public interface BrickLayerInvokeService {

	 Boolean invokeExternalService(String numExtr) throws Exception;

	 Boolean existMicroService() throws Exception;

	Boolean invokeExternalServiceKillJob(String numExtr) throws Exception;
	
}
