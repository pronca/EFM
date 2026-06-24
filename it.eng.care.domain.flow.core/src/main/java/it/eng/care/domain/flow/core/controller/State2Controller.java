package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.StateDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface State2Controller {

    SearchOperationResult<StateDTO> search(BaseSearchInput baseSearchInput);

}
