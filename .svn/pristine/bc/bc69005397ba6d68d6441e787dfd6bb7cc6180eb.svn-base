package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface VersionService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    VersionDO createEntity(VersionDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    VersionDO updateEntity(VersionDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<VersionDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    /**
     * Retrieve all filtered.
     *
     * @return the all
     */
    List<VersionDO> findAll();

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    VersionDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);

	FlowVersionDO findByFlow(FlowDO flow);

//	/**
//	 * Delete entity.
//	 *
//	 * @param dto the dto
//	 * @return the e
//	 */
//	void deleteEntity(FlowDO dto);

}
