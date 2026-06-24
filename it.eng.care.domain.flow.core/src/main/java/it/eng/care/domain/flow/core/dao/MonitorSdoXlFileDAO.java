package it.eng.care.domain.flow.core.dao;

import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public interface MonitorSdoXlFileDAO {
	
	Integer getStatusXml(String flussoId,String nosologico,String presidio);
	byte[] getXmlData(BaseSearchInput searchInput);
	
}
