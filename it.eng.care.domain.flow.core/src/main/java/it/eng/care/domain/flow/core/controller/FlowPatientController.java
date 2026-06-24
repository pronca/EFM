package it.eng.care.domain.flow.core.controller;

import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.SearchFlowPatientFilter;
import it.eng.care.domain.flow.core.dto.SearchPatientDTO;

public interface FlowPatientController {
	
	public FlowOperationResult<List<SearchPatientDTO>> search(SearchPatientDTO patient);

	public FlowOperationResult<SearchPatientDTO> searchFlowPatient(SearchFlowPatientFilter patient);

}
