package it.eng.care.domain.flow.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;

import it.eng.care.domain.flow.core.dto.ExtractionErrorMessage;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.PrivacyManagerDTO.PMFlowView;

public interface FlowViewService {

    String getVersion(String version);

    PMFlowView executeQuery(FlowViewFilter flowViewFilter, HashMap<String, String> campiPraticaSubjectGen);

    List<Map<String, Object>> searchForValidation(ValidationFilter request, FormFlowDTO formFlowDTO, List<FormFlowTableFieldDTO> groupListCfg);

    ScrollableResults scrollForValidation(ValidationFilter request, FormFlowDTO formFlowDTO, List<FormFlowTableFieldDTO> groupListCfg);

    List<ExtractionErrorMessage> getExtractionErrors(FormFlowDTO configuration, String extractionId);

    byte[] downloadFlowViewXlsx(FlowViewFilter flowViewFilter);

	List<Object> executeQueryCount(FlowViewFilter flowViewFilter);
	
	PMFlowView sendAuditVisuaFlussiToPM (FlowViewFilter flowViewFilter, HashMap<String,String> campiPraticaSubjectGen, PMFlowView pMresults);
	
	byte[] sendAuditDownDatiFlussiToPM(FlowViewFilter flowViewFilter, byte[] bytes);
}
