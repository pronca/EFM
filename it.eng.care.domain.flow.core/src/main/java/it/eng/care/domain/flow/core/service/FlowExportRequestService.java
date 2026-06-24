package it.eng.care.domain.flow.core.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.statemachine.state.State;

import it.eng.care.domain.flow.core.dto.FlowExportRequestDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.tabgen.dto.TabgenMap;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

public interface FlowExportRequestService {
    //TODO aggiornare a versione successiva

    Pair<List<FlowExportRequestDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile);

    FlowExportRequestDO updateEntity(FlowExportRequestDO flowExportRequestDO);

    HashMap<String, Object> getFieldsByFlowAndVersion(String version, String flow);

    FlowExportRequestDO createEntity(FlowExportRequestDO flowExportDO);

    State<String, String> executeStateMachine(String id, String factoryModel, String event) throws Exception;

    List<Object[]> checkExtractObsoleta(FlowExportRequestDTO flowExportDTO) throws SQLException;

    Integer consolidaPratiche(FlowExportRequestDTO flowExportDTO, String status);

    FlowExportRequestDO retriveOne(String id);

    Boolean delete();

    Boolean logicDelete(List<FlowExportRequestDO> flowExportRequestDOS);

	FlowExportRequestDO updateEntityRow(FlowExportRequestDO flowExportDO);

	Boolean jobTalendJar(String exportId);

	boolean killJob(String exportId);

    Boolean deletedByExtractionIds(String extractionId);

	
	HttpEntity<byte[]> downloadXML(String exportId);
	
	HttpEntity<byte[]> downloadLog(String exportId);

	Boolean calculateDrg(FlowDrgDO resDrg);

	void calculateDrgStatistics(FlowDrgDO resDrg);

	Integer consolidaRitorni(FormFlowDTO configurationRegion, FlowExportRequestDTO flowExportDTO, String status,
			Boolean joinScartoRegionale, TabgenMap tracciatoSegnalazioniRegionali);

	void invocaJobTalendAnnullaPratica(String flowId, String versionId, Map<String, String> pk);
	
	byte[] sendAuditDownXMLEstrFlussiToPM(String exportId, byte[] byt);
	
	byte[] sendAuditDownLOGEstrFlussiToPM(String exportId, byte[] byt);
	
	List<Object[]> checkExtractGiaConsolidataInviata(FlowExportRequestDTO flowExportDTO) throws SQLException;
	
	public void ensureRegionErrorTableExists(FlowExportRequestDTO flowExportDTO) throws Exception;
	
}
