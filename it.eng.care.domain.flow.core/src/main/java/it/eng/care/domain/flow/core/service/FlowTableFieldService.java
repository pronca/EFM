package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowTableFieldService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowTableFieldDO createEntity(FlowTableFieldDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowTableFieldDO updateEntity(FlowTableFieldDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FlowTableFieldDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FlowTableFieldDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);


}
