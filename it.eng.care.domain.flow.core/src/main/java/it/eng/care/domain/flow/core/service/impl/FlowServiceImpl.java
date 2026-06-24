package it.eng.care.domain.flow.core.service.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.FlowReceiverLogDAO;
import it.eng.care.domain.flow.core.dao.FlowVersionDAO;
import it.eng.care.domain.flow.core.dto.ReferenceDateFieldDTO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowReceiverLogDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowDAO flowDAO;
    
    @Autowired
    private FlowReceiverLogDAO flowReceiverLogDAO;
    
    @Autowired
    private ConfigurationDAO configurationDAO;
    
    @Autowired
    private FlowVersionDAO flowVersionDAO;
    
    @Autowired
	private EntityManager entityManager;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowServiceImpl.class);

    public FlowDO createEntity(FlowDO poValue) {
        return flowDAO.save(poValue);
    }

    public FlowDO updateEntity(FlowDO poValue) {
//    	String schema = flowDAO.findById(poValue.getId()).getJson_schema().orElse(null);
//    	poValue.setJson_schema(schema);
        return flowDAO.save(poValue);
    }

    public Pair<List<FlowDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile) {
    	List<FlowDO> loList = null;
    	if(useFlowProfile) {
    		loList = flowDAO.cercaInProfile(searchInput);
    	} else {
    		loList = flowDAO.cerca(searchInput);
    	}
        return Pair.of(loList, SearchInfos.create());
    }

    @Override
    public List<FlowDO> findAll() {
        try {
            return flowDAO.findAll();
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
        }
        return null;
    }
    
    public FlowDO retrieveOne(String id) {
        return (id == null || id.isBlank())
                ? null
                : flowDAO.findById(id).orElse(null);
    }

    public void deleteEntity(String id) {
        flowDAO.deleteById(id);
    }

    @Override
    public List<FlowDO> getAllWithVersions(String flowType) {
        return flowDAO.getAllWithVersions(flowType);
    }
    
    @Override
    public void logFlowSending(String flowname, String version, String operation, String pk) {
    	ConfigurationDO cfg = configurationDAO.findByKeyId("FM_RECEIVER_LOG");
    	if(cfg != null && "true".equals(cfg.getValue() + "")) {
    		FlowReceiverLogDO log = new FlowReceiverLogDO(flowname, version, operation,pk,  new Date());
    		log.setId(UUID.randomUUID().toString());
    		flowReceiverLogDAO.save(log);
    	}
    }

    @Override
	public void deleteFlow(FlowDO flowDo) {
		try {
			FlowVersionDO flowVersion = new FlowVersionDO();
			try {
				flowVersion = flowVersionDAO.findByFlow(flowDo);
			} catch (Exception e) {
				LogUtil.logException(LOGGER, "", e);
				flowVersion = null;
			}
			entityManager.createNativeQuery("delete from FM_FLOW_FOREIGNKEY where ID_TABLE IN (SELECT ID FROM FM_FLOW_TABLE WHERE FLOW = '"+ flowDo.getId() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_TABLE_FIELD where FLOW_TABLE IN (SELECT ID FROM FM_FLOW_TABLE WHERE FLOW = '"+ flowDo.getId() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_TABLE where FLOW = '"+ flowDo.getId() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_RULE_FILE where FLOW_RULE_ID IN (SELECT FR_ID FROM FM_FLOW_RULE WHERE FLOW = '"+ flowDo.getId() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_RULE WHERE FLOW = '"+ flowDo.getId() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_REG_UNION WHERE FLOW_LOC = '"+ flowDo.getId() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_SECTION_FILE where REQUEST_ID IN (SELECT ID FROM FM_FLOW_FILE_UPLOAD_REQUEST WHERE FLOW_ID = '"+ flowDo.getId() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_FILE_UPLOAD_REQ_ERROR where REQUEST_ID IN (SELECT ID FROM FM_FLOW_FILE_UPLOAD_REQUEST WHERE FLOW_ID = '"+ flowDo.getId() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_TABGEN_VALUE WHERE TABGEN_ID = 'FM_DASHBOARD_CONFIG' AND FIELD2='"+ flowDo.getId() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_TABGEN_VALUE WHERE TABGEN_ID = 'FM_SEARCH_PATIENT_FIELDS' AND FIELD1='"+ flowDo.getName() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_TABGEN_VALUE WHERE TABGEN_ID = 'FM_EMAIL_MESSAGE_CONFIG' AND FIELD2='"+ flowDo.getName() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_FILE_UPLOAD_REQUEST WHERE FLOW_ID = '"+ flowDo.getId() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_FLOW_VERSION WHERE FLOW = '"+ flowDo.getId() +"'").executeUpdate();
			if (flowVersion!=null) {
				Query query = entityManager.createNativeQuery("SELECT COUNT(1) FROM FM_VERSION WHERE ID='"+ flowVersion.getId().getVersion() +"'");
				BigDecimal result = (BigDecimal) query.getSingleResult();
				if (result!=null && result.equals(BigDecimal.ZERO)) {
					entityManager.createNativeQuery("delete from FM_VERSION WHERE ID = '"+ flowVersion.getId().getVersion() +"'").executeUpdate();
				}
			}
			entityManager.createNativeQuery("delete from FM_FLOW WHERE ID = '"+ flowDo.getId() +"'").executeUpdate();
			
			entityManager.createNativeQuery("delete from FM_DASHBOARD_PRATICHE_FILTER_VALUE where DASHBOARD IN (SELECT ID FROM FM_DASHBOARD_PRATICHE WHERE FLOW_NAME = '"+ flowDo.getName() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_DASHBOARD_PRATICHE WHERE FLOW_NAME = '"+ flowDo.getName() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_DASHBOARD_ERRORS_FILTER_VALUE where DASHBOARD IN (SELECT ID FROM FM_DASHBOARD_ERRORS WHERE FLOW_NAME = '"+ flowDo.getName() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_DASHBOARD_ERRORS WHERE FLOW_NAME = '"+ flowDo.getName() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_DASHBOARD_FILTER_VALUE where DASHBOARD IN (SELECT ID FROM FM_DASHBOARD WHERE FLOW = '"+ flowDo.getName() +"')").executeUpdate();
			entityManager.createNativeQuery("delete from FM_DASHBOARD WHERE FLOW = '"+ flowDo.getName() +"'").executeUpdate();
			entityManager.createNativeQuery("delete from FM_TABGEN_VALUE WHERE TABGEN_ID='PROFILO_FLUSSI' AND FIELD4 = '"+ flowDo.getName() +"'").executeUpdate();
			
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "Errore nella eliminazione del flusso", e);
//			e.printStackTrace();
		}
		
	}
	
	@Override
	public void renameTableFlow(FlowDO flowDo) {
	    try {
	        String now = LocalDateTime.now()
	                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	        entityManager.unwrap(org.hibernate.Session.class).doWork(connection -> {

	            // 🔹 1. Recupero tutte le tabelle del flusso
	            String selectTablesSql = """
	                SELECT table_name
	                FROM user_tables
	                WHERE table_name LIKE ?
	                  AND table_name NOT LIKE '%_BCK_%'
	            """;

	            try (PreparedStatement psTables = connection.prepareStatement(
	                    selectTablesSql,
	                    ResultSet.TYPE_FORWARD_ONLY,
	                    ResultSet.CONCUR_READ_ONLY)) {

	                psTables.setString(
	                        1,
	                        "FM_FLOW_" + flowDo.getName().toUpperCase() + "_%"
	                );
	                psTables.setFetchSize(5000);

	                try (ResultSet rsTables = psTables.executeQuery()) {

	                    while (rsTables.next()) {
	                        String tableName = rsTables.getString("table_name");

	                        if (tableName == null || tableName.isBlank()) {
	                            continue;
	                        }

	                        // 🔹 2. Rinomino gli indici
	                        String selectIndexesSql = """
	                            SELECT index_name
	                            FROM user_indexes
	                            WHERE table_name = ?
	                        """;

	                        try (PreparedStatement psIndexes = connection.prepareStatement(selectIndexesSql)) {
	                            psIndexes.setString(1, tableName);

	                            try (ResultSet rsIndexes = psIndexes.executeQuery()) {
	                                while (rsIndexes.next()) {
	                                    String indexName = rsIndexes.getString("index_name");

	                                    entityManager.createNativeQuery(
	                                            "ALTER INDEX " + indexName +
	                                            " RENAME TO " + indexName + "_BCK_" + now
	                                    ).executeUpdate();
	                                }
	                            }
	                        }

	                        // 🔹 3. Rinomino le constraint
	                        String selectConstraintsSql = """
	                            SELECT constraint_name
	                            FROM user_constraints
	                            WHERE table_name = ?
	                        """;

	                        try (PreparedStatement psConstr = connection.prepareStatement(selectConstraintsSql)) {
	                            psConstr.setString(1, tableName);

	                            try (ResultSet rsConstr = psConstr.executeQuery()) {
	                                while (rsConstr.next()) {
	                                    String constraintName = rsConstr.getString("constraint_name");

	                                    entityManager.createNativeQuery(
	                                            "ALTER TABLE " + tableName +
	                                            " RENAME CONSTRAINT " + constraintName +
	                                            " TO " + constraintName + "_BCK_" + now
	                                    ).executeUpdate();
	                                }
	                            }
	                        }

	                        // 🔹 4. Rinomino la tabella
	                        entityManager.createNativeQuery(
	                                "ALTER TABLE " + tableName +
	                                " RENAME TO " + tableName + "_BCK_" + now
	                        ).executeUpdate();
	                    }
	                }
	            }
	        });

	    } catch (Exception e) {
	    	LogUtil.logException(LOGGER, "Errore nella rinomina delle tabelle del flusso "+flowDo.getName()+"", e);
	    }
	}
	
	public List<ReferenceDateFieldDTO> findReferenceDateFieldsByFlowName(String flowName) {
        if (flowName == null || flowName.isBlank()) {
            return List.of();
        }

        final String sql = """
            SELECT t.SECTION AS section, f.NAME AS field_name
            FROM FM_FLOW_TABLE_FIELD f
            JOIN FM_FLOW_TABLE t ON t.ID = f.FLOW_TABLE
            JOIN FM_FLOW fl ON fl.ID = t.FLOW
            WHERE fl.NAME = :flowName
              AND f.IS_REFERENCE_DATE = '1'
            ORDER BY t.SECTION, f.POSITION, f.NAME
        """;

        @SuppressWarnings("unchecked")
        List<Object[]> rows = entityManager
                .createNativeQuery(sql)
                .setParameter("flowName", flowName.trim())
                .getResultList();

        List<ReferenceDateFieldDTO> out = new ArrayList<>(rows.size());
        for (Object[] r : rows) {
            Integer section = (r[0] == null) ? null : ((Number) r[0]).intValue();
            String fieldName = (String) r[1];
            out.add(new ReferenceDateFieldDTO(section, fieldName));
        }
        return out;
    }
	
}
