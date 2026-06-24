package it.eng.care.domain.flow.core.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;

import it.eng.care.domain.flow.core.dto.MonitorSdoPaginDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoXlErrorDTO;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.domain.flow.ws.results.Error;
import it.eng.care.domain.flow.ws.results.ResultRequest;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public interface MonitorSdoXlService {

	Page<MonitorSdoXlDO> findAll(BaseSearchInput searchInput);
	Page<MonitorSdoXlDO> filterAll(BaseSearchInput searchInput);
	Page<MonitorSdoXlErrorDTO>  reatriveAllErrorsByFlussoAndNosologico(BaseSearchInput searchInput);
	Integer xmlStatus(String flussId,String nosologico,String presidio);
	byte[] xmlExport(BaseSearchInput searchInput);
	
	/**
	 * Adds on monitor the external errors (sent by ws)
	 *   
	 * @param resultRequest
	 * @throws Exception
	 */
	void addResults(ResultRequest resultRequest) throws Exception;
	
	/**
	 * Invia i risultati al verticale
	 * 
	 * @param results
	 * @param flowName
	 * @throws SQLException
	 * @throws Exception 
	 */
	void sendResults(List<Error> results) throws Exception;
	
	void delete(String codiceazienda, String codicepresidio, String progressivosdo);
	
	byte[] sendAuditDownMonitorSDOToPM(BaseSearchInput baseSearchInput, byte[] zipbytes);
	
	MonitorSdoPaginDTO sendAuditVisuaMonitorSDOToPM(BaseSearchInput searchInput, MonitorSdoPaginDTO pagination );
	
}
