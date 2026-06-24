package it.eng.care.domain.flow.core.service;

import java.sql.SQLException;

import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;

public interface TalendThreadService {

	void save(FlowExportRequestDO flowExportRequestDO);

	int checkValidation(String exportId);

	Integer countExtractionRecord(String exportId) throws SQLException;
	
	void deleteErrors(String exportId);
	
}
