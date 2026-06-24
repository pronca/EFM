package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowTableService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowTableDO createEntity(FlowTableDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowTableDO updateEntity(FlowTableDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FlowTableDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FlowTableDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);
    
    FlowTableDO findByName(String name);


}
