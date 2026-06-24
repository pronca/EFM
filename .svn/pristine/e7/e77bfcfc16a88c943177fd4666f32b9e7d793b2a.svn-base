package it.eng.care.domain.flow.core.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;

import it.eng.care.domain.flow.core.dto.ExtractionErrorMessage;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;

public interface FlowViewDAO {

    List<Object> search(FlowViewFilter flowViewFilter);

    List<Map<String, Object>> searchForValidation(ValidationFilter request, FormFlowDTO formFlowDTO, List<FormFlowTableFieldDTO> groupListCfg, Integer fetchsize);

    ScrollableResults scrollForValidation(ValidationFilter request, FormFlowDTO formFlowDTO, List<FormFlowTableFieldDTO> groupListCfg, Integer fetchsize);

    List<ExtractionErrorMessage> getExtractionErrors(FormFlowDTO configuration, String extractionId);
    
    List<Object[]> executeSearchQuery(String sql, List<Object> params);


}
