package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Set;

public interface FlowConfigurationFilterService {


    Pair<List<FlowConfigurationFilterDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile);

    FlowConfigurationFilterDTO createEntity(FlowConfigurationFilterDTO dto);


    FlowConfigurationFilterDTO updateEntity(FlowConfigurationFilterDTO dto);

    Set<String> deleteEntity(FlowConfigurationFilterDO flowConfigurationFilterDO);


}
