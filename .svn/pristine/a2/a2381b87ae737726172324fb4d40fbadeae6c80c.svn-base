package it.eng.care.domain.flow.jobs.service;

import java.util.Date;
import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowExportRequestDTO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.drools.model.status.ValidationResult;

public interface JobService {
	
	public List<FlowImportRequestDO> getImportRequestToValidate();
	
	public List<FlowExportRequestDO> getExportRequestToValidate();
	
	public List<FlowExportRequestDTO> getExportRequestToExecute();
	
	void markImportRequestAsValidated(String id, ValidationResult validationResult);

	void deleteOldResults(String id);

	void createNextSchedulation(String exportId, Date nextDate);

	void markExportRequestAsElaborated(String id, String status);

	void markImportRequest(String id, String status);
	
}
