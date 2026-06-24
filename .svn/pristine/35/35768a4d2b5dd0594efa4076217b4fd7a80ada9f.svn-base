package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.ReferenceDateFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowDO createEntity(FlowDO dto);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowDO updateEntity(FlowDO dto);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FlowDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FlowDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);

    List<FlowDO> findAll();

    List<FlowDO> getAllWithVersions(String flowType);

    /**
     * Crea un log di ricezione di una pratica inviata da un verticale
     * 
     * @param flowname
     * @param version
     * @param pk
     */
	void logFlowSending(String flowname, String version, String operation, String pk);
	
	/**
     * Delete flow.
     *
     * @param id the dto
     * @return the e
     */
	void deleteFlow(FlowDO flowDO);
	
	void renameTableFlow(FlowDO flowDO);
	
	public List<ReferenceDateFieldDTO> findReferenceDateFieldsByFlowName(String flowName);
	
}
