package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.ExtractionFilter.ExtractionFilterDTO;
import org.springframework.statemachine.state.State;

public interface FlowExtractionService {

    State<String, String> extraction(ExtractionFilterDTO extractionFilterDTO);

}