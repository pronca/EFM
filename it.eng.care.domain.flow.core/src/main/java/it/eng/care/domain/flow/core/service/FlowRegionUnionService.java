package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowRegionUnionService {

    /**
     * Creates the entity.
     *
     * @param poValue the dto
     * @return the e
     */
    FlowRegionUnionDO createEntity(FlowRegionUnionDO poValue);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowRegionUnionDO updateEntity(FlowRegionUnionDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FlowRegionUnionDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FlowRegionUnionDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);

    List<FlowRegionUnionDO> findAll();


}
