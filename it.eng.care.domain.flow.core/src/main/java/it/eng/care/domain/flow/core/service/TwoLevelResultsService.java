package it.eng.care.domain.flow.core.service;

import java.io.File;
import java.util.Collection;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.tabgen.dto.TabgenMap;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

/**
 *
 * Gestione del caricamento degli errori/segnalazioni e correzioni generati
 * dalla regione in fase di validazione delle estrazioni prodotte dal flow
 * manager
 *
 */
public interface TwoLevelResultsService {
	
	public FlowOperationResult<Boolean> uploadResults(File file, String flowLocalId, String flowRegionId, String versionId,
			String extractionId, boolean produzioneTot, boolean consolidata) throws Exception;

	void updateExtractionsStatus(Collection<FlowExportRequestDO> collection) throws Exception;
	
	byte[] sendAuditUploadRitornoToPM(BaseSearchInput searchInput, byte[] bytes);
	
	public FlowOperationResult<TabgenMap> checkAndLoadTabgenErrors(String tableNameRegErrors, Boolean updateTable) throws Exception;
	
	void createTableIfNotExists(TabgenMap tabgen) throws Exception;
	
}
