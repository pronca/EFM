package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.DriverDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface DriverService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    DriverDO createEntity(DriverDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    DriverDO updateEntity(DriverDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<DriverDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve all filtered.
     *
     * @return the all
     */
    List<DriverDO> findAll();

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    DriverDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);

}
