package it.eng.care.domain.flow.core.service;

import java.util.HashMap;
import java.util.List;

import it.eng.care.domain.flow.b2b.exception.ValidationFlowException;
import it.eng.care.domain.flow.core.dto.PaginatedPraticaDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.domain.flow.core.dto.PrivacyManagerDTO.PMPraticaView;
import it.eng.care.domain.flow.core.entity.ContextConfigurationDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public interface PraticaViewService {

    PMPraticaView executeQuery(FlowViewFilterError flowViewFilterError) throws ValidationFlowException;

    byte[] downloadFlowViewXlsx(FlowViewFilterError flowViewFilterError);

    PaginatedPraticaDTO searchErrors(FlowViewFilterError flowViewFilterError);

	PMPraticaView executeQueryPM(FlowViewFilterError flowViewFilterError, HashMap<String,String> campiPraticaSubject) throws ValidationFlowException;

	HashMap<String, String> getContextValue(List<List<String>> keys, List<ContextConfigurationDO> config);
	
	PMPraticaView sendAuditVisuaPraDashToPM(FlowViewFilterError flowViewFilterError, HashMap<String,String> campiPraticaSubject, PMPraticaView pMPraticaView) throws ValidationFlowException;
	
	byte[] sendAuditDownPraErrToPM(FlowViewFilterError flowViewFilterError, byte[] bytes);
	
}
