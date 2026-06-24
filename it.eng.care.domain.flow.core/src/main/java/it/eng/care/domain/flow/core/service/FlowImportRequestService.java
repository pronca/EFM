package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.ExtractionFilter.ExtractionFilterDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface FlowImportRequestService {

    /**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowImportRequestDO createEntity(FlowImportRequestDO dto, Boolean useFlowProfile);

    /**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
    FlowImportRequestDO updateEntity(FlowImportRequestDO dto, Boolean useFlowProfile);


    /**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
    Pair<List<FlowImportRequestDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile);

    /**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
    FlowImportRequestDO retrieveOne(String id);

    /**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    Integer deleteEntity(FlowImportRequestDO flowImportRequestDO, Boolean useFlowProfile);


    // HashMap<String, Object> getFieldsByFlowAndVersion(String version, String flow);

    Integer cancelExtraction(FlowImportRequestDO flowImportRequestDO);

    //int cancelExtraction(BaseSearchInput data, int i);

    //int updateStatus(String id, String value);


    //TODO rimuovere star import ed aggiungere Extraction
    void startImport(FlowImportRequestDTO dto, Boolean useFlowProfile);

    void extraction(ExtractionFilterDTO extractionFilterDTO);


}
