package it.eng.care.domain.flow.core.service;

import java.util.HashMap;
import java.util.List;

import it.eng.care.domain.flow.core.dto.SearchFlowPatientFilter;
import it.eng.care.domain.flow.core.dto.SearchPatientDTO;

public interface FlowPatientService {
	
	public List<SearchPatientDTO> searchPatientOccurrences(SearchPatientDTO patient);

	public SearchPatientDTO searchFlowPatient(SearchFlowPatientFilter filter, HashMap<String, String> campiPraticaSubjectGen);
	
	public List<SearchPatientDTO> sendAuditRicAssToPM(SearchPatientDTO patientFilter, List<SearchPatientDTO> list);
	
	public SearchPatientDTO sendAuditVisuaPraAssToPM(SearchFlowPatientFilter patientFilter, HashMap<String,String> campiPraticaSubjectGen, SearchPatientDTO searchPatientDTO);
	
}
