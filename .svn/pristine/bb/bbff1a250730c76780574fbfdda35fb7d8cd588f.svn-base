package it.eng.care.domain.flow.core.service;

import java.io.File;
import java.util.List;

import org.springframework.data.domain.Page;

import it.eng.care.domain.flow.core.dto.AnagrafcaAssistitoPaginationDTO;
import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;


public interface AnagraficaAssistitoService {
	
	/**
     * Creates the entity.
     *
     * @param dto the dto
     * @return the e
     */
	AnagraficaAssistitoDO createEntity(AnagraficaAssistitoDO dto);
	
	List<AnagraficaAssistitoDO> checkAnagraficaAssistitoData(BaseSearchInput searchInput);
	
	void saveAllEntities(List<AnagraficaAssistitoDO> allAnagaraficaAss);
	
	/**
     * Retrieve all filtered.
     *
     * @param searchInput the search input
     * @return the pair
     */
	Page<AnagraficaAssistitoDO> retrieveAllFiltered(BaseSearchInput searchInput);
	
	/**
     * Update entity.
     *
     * @param dto the dto
     * @return the e
     */
	AnagraficaAssistitoDO updateEntity(AnagraficaAssistitoDO dto);
	
	/**
     * Retrieve one.
     *
     * @param id the id
     * @return the e
     */
	AnagraficaAssistitoDO getEntityById(String id);
	
	/**
     * Delete entity.
     *
     * @param id the dto
     * @return the e
     */
    void deleteEntity(String id);
    
    
    /**
     * Retrieve entity's limited pagination.
     *
     * @param searchInput the search input
     * @return the List
     */
    Page<AnagraficaAssistitoDO> findAll(BaseSearchInput searchInput);
    
    
    /**
     * Handle file upload.
     *
     * @param file
     * @return the String
     */
    String handleExcelUpload(File file);
    
    List<AnagraficaAssistitoDO> exportAllData(BaseSearchInput searchInput);
    
	byte[] downloadAnagraficaAssistitoXlsx(List<AnagraficaAssistitoDO> dtos);
    
	AnagrafcaAssistitoPaginationDTO sendAuditRicAnaAssToPM(BaseSearchInput searchInput, AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO);
    
	AnagrafcaAssistitoPaginationDTO sendAuditVisuaAnaAssToPM(BaseSearchInput searchInput, AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO);
	
	byte[] sendAuditDownAnaAssFileUplToPM(List<AnagraficaAssistitoDO> anagraficaAssistitoDOs, String selectedOption, String filterValue, byte[] byt);
	
	void loadTabgenInAnagrafica(AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO);
	
	List<String> loadTabgen(String valueId, String valueDescr, String tabgenId, String[] codici, String[] descrizioni);
		
}
