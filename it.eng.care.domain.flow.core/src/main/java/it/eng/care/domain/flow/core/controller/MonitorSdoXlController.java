package it.eng.care.domain.flow.core.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpEntity;

import it.eng.care.domain.flow.core.dto.MonitorSdoErrorPaginDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoPaginDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface MonitorSdoXlController {
	
	public OperationResult<MonitorSdoPaginDTO> getPaginatedData(BaseSearchInput baseSearchInput);
	public OperationResult<MonitorSdoPaginDTO> filterData(BaseSearchInput searchInput);
	public OperationResult<MonitorSdoErrorPaginDTO> getAllErrors(BaseSearchInput searchInput) throws SQLException;
	public SearchOperationResult<FormFlowDTO> getAllFlusso(BaseSearchInput searchInput);
	public HttpEntity<byte[]> downloadXML(BaseSearchInput searchInput)  throws IOException;
}
