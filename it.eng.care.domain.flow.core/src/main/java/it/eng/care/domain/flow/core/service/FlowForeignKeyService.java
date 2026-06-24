package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowForeignKeyService {

    FlowForeignKeyDO createEntity(FlowForeignKeyDO dto);
    FlowForeignKeyDO updateEntity(FlowForeignKeyDO dto);
    Pair<List<FlowForeignKeyDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);
    public void deleteLink(String id);

}

	

