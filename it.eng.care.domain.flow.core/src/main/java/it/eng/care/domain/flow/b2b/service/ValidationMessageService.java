package it.eng.care.domain.flow.b2b.service;

import java.util.List;

import it.eng.care.domain.flow.b2b.model.ErrorModelDTO;
import it.eng.care.domain.flow.core.entity.ValidationErrorsDO;

public interface ValidationMessageService {

	public void insertEvent(ErrorModelDTO model, String flowName, String jsonString);
		
	public void saveOrUpdateEvent(ValidationErrorsDO error);

	List<ValidationErrorsDO> findAllPendingErrors(int numErr);
	
	
	
}
