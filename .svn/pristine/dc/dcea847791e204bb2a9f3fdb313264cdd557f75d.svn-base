package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface DataSourceService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    DataSourceDO createEntity(DataSourceDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    DataSourceDO updateEntity(DataSourceDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<DataSourceDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    DataSourceDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);

}
