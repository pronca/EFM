package it.eng.care.domain.flow.core.service.impl;

import it.eng.care.domain.flow.core.dao.StateDAO;
import it.eng.care.domain.flow.core.dao.querySearch.StateDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.domain.flow.core.service.StateService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    @Autowired
    private StateDAO stateDAO;

    @Autowired
    private StateDAOQueryByBaseSearchInput stateDAOQueryByBaseSearchInput;

    public Pair<List<StateDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {

        List<StateDO> loList = stateDAO.cerca(searchInput);
        return Pair.of(loList, SearchInfos.create());

    }

    public String searchState(String id) {

        String result = stateDAOQueryByBaseSearchInput.searchState(id);

        return result;
    }

}
