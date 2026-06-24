package it.eng.care.domain.flow.core.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.controller.FlowPatientController;
import it.eng.care.domain.flow.core.dao.SearchPatientFieldDAO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.SearchFlowPatientFilter;
import it.eng.care.domain.flow.core.dto.SearchPatientDTO;
import it.eng.care.domain.flow.core.entity.SearchPatientFieldDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowPatientService;

@RestController
@RequestMapping("/fm/FlowPatientDTO")
public class FlowPatientControllerImpl implements FlowPatientController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowPatientControllerImpl.class);
	
	@Autowired
	private FlowPatientService flowPatientService;
	
	@Autowired
	private SearchPatientFieldDAO searchPatientFieldDAO;
	
	@Autowired
	private LogAccessiPMConfig logAccessiPMConfig;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;

	@Override
    @PostMapping("/searchPatient")
	@ResponseBody
	public FlowOperationResult<List<SearchPatientDTO>> search(@RequestBody SearchPatientDTO patient) {
		
		if(patient.getTaxCode() == null || patient.getTaxCode().isEmpty()) {
			if(patient.getName() == null || patient.getName().isEmpty()) {
					return FlowOperationResult.failure("Valorizzare il nome o il codice fiscale dell'assistito");
			}
			
			if(patient.getSurname() == null || patient.getSurname().isEmpty()) {
					return FlowOperationResult.failure("Valorizzare il cognome o il codice fiscale dell'assistito");
			}
			
			if(patient.getBirthDate() == null) {
				return FlowOperationResult.failure("Valorizzare la data di nascita o il codice fiscale dell'assistito");
			}
		}
		
		List<SearchPatientDTO> list = flowPatientService.searchPatientOccurrences(patient);
		if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogRicAssistito())) {
    		List<SearchPatientDTO> resultsLogAccessi = flowPatientService.sendAuditRicAssToPM(patient, list);
        }
		
		return FlowOperationResult.success(list);
	}
	
	@Override
    @PostMapping("/searchFlowPatient")
	@ResponseBody
	public FlowOperationResult<SearchPatientDTO> searchFlowPatient(@RequestBody SearchFlowPatientFilter patient) {
		
		HashMap<String,String> campiPraticaSubjectGen = new HashMap<String,String>();
    	
		//caricamento lista aziende visibili dall'utente
    	List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
    	List<SearchPatientFieldDO> fmSearchPatientFieldsTabgen = new ArrayList<>();
    	if (!aziende.isEmpty()) {
			fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByFlussoAndFiltroRicercaAndCodiceAziendaIn(patient.getFlow().getName(), "1", aziende);
			//se non è stata fatta una configurazione specifica per azienda allora prendo la configurazione generica del flusso con azienda a null e che quindi vale per tutte le aziende
			if (fmSearchPatientFieldsTabgen.isEmpty()) {
				fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByFlussoAndFiltroRicercaAndCodiceAziendaIsNull(patient.getFlow().getName(), "1");
			}
    	} else {
    		fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByFlussoAndFiltroRicerca(patient.getFlow().getName(), "1");
    	}
		for (SearchPatientFieldDO patientFields : fmSearchPatientFieldsTabgen) {
				campiPraticaSubjectGen.put(patientFields.getCampoFunzione(), patientFields.getCampoTracciato());
		}
		
		SearchPatientDTO res = flowPatientService.searchFlowPatient(patient, campiPraticaSubjectGen);
		if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogVisuaPraAssistito())) {
    		SearchPatientDTO resultsLogAccessi = flowPatientService.sendAuditVisuaPraAssToPM(patient, campiPraticaSubjectGen, res);
        }
		return FlowOperationResult.success(res);
	}

}
