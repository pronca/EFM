package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface StateService {

    Pair<List<StateDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    String searchState(String id);

}
