package it.eng.care.domain.flow.ws.results;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.care.domain.flow.core.service.MonitorSdoXlService;
import it.eng.care.domain.flow.core.utility.LogUtil;

@Endpoint
public class WsResultEndpoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WsResultEndpoint.class);
	
	private static final String NAMESPACE_URI = "http://www.eng.it/fm/ws/results";
	
	@Autowired
	private MonitorSdoXlService monitorSdoXlService;
	
	public WsResultEndpoint() {
		// TODO Auto-generated constructor stub
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "resultRequest")
    @ResponsePayload
    public ResultResponse resultRequest(@RequestPayload ResultRequest request) {
		
		LOGGER.info("MONITOR WS - REQUEST IN");
		
		ResultResponse response = new ResultResponse();
		Result opresult = new Result();
		opresult.setCode("00");
		opresult.setMessage("OK");
		response.setResponse(opresult);
		
		try {
			monitorSdoXlService.addResults(request);
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
			opresult.setCode("99");
			opresult.setMessage(e.getMessage());
		}
		
		try {
			monitorSdoXlService.sendResults(request.getSet().getFIAdtAdtLErroreSDO());
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "Errore invio risultati SDOXL", e);
		}
		
        return response;
    }

}
