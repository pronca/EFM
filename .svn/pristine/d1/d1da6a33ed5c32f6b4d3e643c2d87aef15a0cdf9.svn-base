package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FieldTypeService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    FieldTypeDO createEntity(FieldTypeDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FieldTypeDO updateEntity(FieldTypeDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FieldTypeDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FieldTypeDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);

    /**
     * Retrieve all filtered.
     *
     * @return the all
     */
    List<FieldTypeDO> findAll();

}
