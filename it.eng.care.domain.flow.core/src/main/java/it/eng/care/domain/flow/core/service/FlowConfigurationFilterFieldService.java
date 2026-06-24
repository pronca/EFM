package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowConfigurationFilterFieldService {


    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowConfigurationFilterFieldDO createEntity(FlowConfigurationFilterFieldDTO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowConfigurationFilterFieldDO updateEntity(FlowConfigurationFilterFieldDTO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FlowConfigurationFilterFieldDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FlowConfigurationFilterFieldDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(FlowConfigurationFilterFieldDO dto);

    /**
     * Retrieve all filtered.
     *
     * @return the all
     */
    List<FlowConfigurationFilterFieldDO> findAll();

}