package it.eng.care.domain.flow.core.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import it.eng.care.domain.flow.b2b.model.SendDrgDTO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;
import it.eng.care.domain.flow.tabgen.dto.TabgenMap;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;

public interface TransactionalTwoLevelResultsService {

	public FlowOperationResult<Boolean> updateRecordsFromFile(FormFlowDTO configuration, String extractionId, String flowId,
			String versionId, File file, TabgenMap tabgenUpdateMap, boolean produzioneTot, TabgenValue tabGenValueFlowUploadConf) throws SQLException;

	public void deleteReturnsFromExtraction(String tableName, String extractionId) throws SQLException;

	public FlowOperationResult<Boolean> insertRecordsFromFile(FormFlowDTO configuration, File file, TabgenMap tabgenMap,
			String extractionId, String errorType, TabgenValue tabGenValueFlowUploadConf, List<TabgenValue>  tabGenValueFlowTipologiaRitorni) throws SQLException;
	
	public void saveRequest(UploadReturnsRequestDO request);

	Boolean updateSendRegionStatus(String extractionId, FormFlowDTO regConfiguration, Boolean checkErrorsAndWarnings,
			TabgenMap tracciatoSegnalazioniRegionali, boolean consolidata) throws SQLException;

	void generateJsonDrg(List<SendDrgDTO> listSendDrg, String extractionId, String flowName);

}
