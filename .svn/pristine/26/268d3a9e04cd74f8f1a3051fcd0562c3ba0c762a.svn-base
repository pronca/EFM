package it.eng.care.domain.flow.core.service.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.state.State;
import org.springframework.transaction.annotation.Transactional;

import it.eng.care.domain.flow.core.config.C3P0DataSource;
import it.eng.care.domain.flow.core.dao.FlowImportRequestDAO;
import it.eng.care.domain.flow.core.dao.FlowTableDAO;
import it.eng.care.domain.flow.core.dao.FlowTableFieldDAO;
import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.dto.ExtractionFilter.ExtractionFilterDTO;
import it.eng.care.domain.flow.core.dto.ExtractionFilter.Field;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;
import it.eng.care.domain.flow.core.enumeration.MachineEvent;
import it.eng.care.domain.flow.core.service.DataSourceService;
import it.eng.care.domain.flow.core.service.FlowExtractionService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.spring.statemachine.StateMachineFlow;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import jakarta.persistence.EntityManager;

public class FlowExtractionServiceImpl implements FlowExtractionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowExtractionServiceImpl.class);

    private static String TABLE_PREFIX = "FM_FLOW_";
    private static String TABLE_NAME_SEP = "_";

    @Autowired
    private DataSource dataSource;
    @Autowired(required=false)
    StateMachineFlow stateMachineFlow;
    @Autowired
    ConversionService conversionService;
    @Autowired
    DataSourceService datasourceService;
    @Autowired
    EntityManager entityManager;
    @Autowired
    FlowTableFieldDAO flowTableFieldDAO;
    @Autowired
    FlowTableDAO flowTableDAO;
    @Autowired
    FormFlowService formFlowService;
    @Autowired
    FlowImportRequestDAO flowImportRequestDAO;
    @Autowired
	FlowManagerProfileService flowManagerProfileService;
    
    @Transactional
    public void changeStateRecord(String extractionId, State<String, String> state) {
        //Cambio stato record
        FlowImportRequestDO flowImportRequestDO = (extractionId== null || extractionId.isBlank()) ? null : flowImportRequestDAO.findById(extractionId).orElse(null);
        flowImportRequestDO.setStatus(state.getId());
        flowImportRequestDAO.save(flowImportRequestDO);
    }
 
    @Transactional
    public State<String, String> extraction(ExtractionFilterDTO extractionFilterDTO) {
        State<String, String> state = null;

        try {
            state = stateMachineFlow.execute(
                    extractionFilterDTO.getExtractionId(),
                    "ESTRAZIONE",
                    MachineEvent.ESEGUI_ESTRAZIONE.getEvent()
            );
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
        }
        changeStateRecord(extractionFilterDTO.getExtractionId(), state);

        FormFlowDTO formFlowDTO = formFlowService.retrieveOne(
                extractionFilterDTO.getFormFlow().getId(),
                extractionFilterDTO.getFormFlow().getVersion()
        );

        List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
        DataSourceDTO dataSourceDTO = this.getDataSource(formFlowDTO);

        try (Connection connectionSource = C3P0DataSource.getInstance(dataSourceDTO).getConnection()) {
            connectionSource.setAutoCommit(false);

            Session session = entityManager.unwrap(Session.class);
            session.doWork(connectionLocal -> {

                // ✅ TUTTE le variabili "mutabili" dentro la lambda
                final String selectSQL = "SELECT ";
                final String insertSQL = "INSERT INTO ";
                final String whereSQL  = " WHERE ";

                String fromSQL = " FROM ";
                String campiSelect = "";
                String rootTable = "";
                String rootTable2 = "";
                String filterSQL = "";

                PreparedStatement preparedStatementSelect = null;
                ResultSet resultSet = null;

                List<PreparedStatement> preparedStaInsert = new ArrayList<>();
                List<PreparedStatement> preparedStaUpdate = new ArrayList<>();
                List<PreparedStatement> preparedStaUpdateSource = new ArrayList<>();

                List<PreparedStatement> preparedStaInsertOk = new ArrayList<>();
                List<PreparedStatement> preparedStaUpdateOk = new ArrayList<>();
                List<PreparedStatement> preparedStaUpdateSourceOk = new ArrayList<>();

                List<List<String>> fieldNames = new ArrayList<>();
                HashMap<Integer, List<Integer>> primaryKeyHashMap = new HashMap<>();
                HashMap<Integer, HashMap<String, String>> recordHashMap = new HashMap<>();

                try {
                    // 1) filtro date (ora dentro lambda → ok)
                    if (extractionFilterDTO.getFields() != null && !extractionFilterDTO.getFields().isEmpty()) {
                        for (Field field : extractionFilterDTO.getFields()) {
                            String fieldId = field.getFieldId();
                            if (fieldId == null || fieldId.trim().isBlank()) continue;

                            FlowTableFieldDO fFilter = flowTableFieldDAO.findById(fieldId.trim()).orElse(null);
                            if (fFilter == null) continue;

                            String flowTableId = fFilter.getFlowTable() != null ? fFilter.getFlowTable().getId() : null;
                            if (flowTableId == null || flowTableId.isBlank()) continue;

                            FlowTableDO tFilter = flowTableDAO.findById(flowTableId).orElse(null);
                            if (tFilter == null) continue;

                            filterSQL += TABLE_PREFIX + formFlowDTO.getName() + TABLE_NAME_SEP + tFilter.getSection() + "."
                                    + fFilter.getName() + " BETWEEN ? AND ? AND ";
                        }
                        if (filterSQL.endsWith("AND ")) {
                            filterSQL = filterSQL.substring(0, filterSQL.length() - 4);
                        }
                    }

                    int countFieldPk = 1;

                    // 2) costruzione statement per sezioni
                    for (FormFlowTableDTO formFlowTableDTO : formFlowDTO.getFlowTableList()) {

                        if (formFlowTableDTO.getSection() == 0) {
                            rootTable = TABLE_PREFIX + formFlowDTO.getName() + TABLE_NAME_SEP + formFlowTableDTO.getSection();
                            rootTable2 = TABLE_PREFIX + formFlowDTO.getName() + TABLE_NAME_SEP;
                        }

                        String tableName = TABLE_PREFIX + formFlowDTO.getName() + TABLE_NAME_SEP + formFlowTableDTO.getSection();

                        String insertSection = insertSQL + tableName;
                        String updateSection = "UPDATE " + tableName + " SET EXPORTED = ?, EXTRACTION_ID = ? WHERE ";

                        String campiInsert = "";
                        String insertSectionI = "";

                        List<String> fields = new ArrayList<>();
                        List<Integer> isPkList = new ArrayList<>();

                        for (FormFlowTableFieldDTO field : formFlowTableDTO.getFlowTableFieldList()) {
                            fields.add(tableName + "." + field.getName());
                            campiSelect += tableName + "." + field.getName() + " , ";
                            campiInsert += field.getName() + " , ";
                            insertSectionI += "?, ";

                            if (field.isPk()) {
                                updateSection += field.getName() + "=? AND ";
                                isPkList.add(countFieldPk);
                                primaryKeyHashMap.put(formFlowTableDTO.getSection(), isPkList);

                                // filtro aziende (anche questo modifica filterSQL → ok perché è dentro lambda)
                                if (formFlowTableDTO.getSection() == 0
                                        && aziende != null && !aziende.isEmpty()
                                        && "CODICEAZIENDA".equalsIgnoreCase(field.getName())) {

                                    String inAziende = " IN ('" + String.join("','", aziende) + "')";
                                    filterSQL += (filterSQL.isBlank() ? "" : " AND ")
                                            + tableName + "." + field.getName() + inAziende;
                                }
                            }

                            countFieldPk++;
                        }

                        fieldNames.add(fields);

                        insertSectionI += "?, ?, ?, ?"; // EXPORTED, EXTRACTION_ID, UUID, IMPORT_TYPE

                        if (campiInsert.endsWith(", ")) campiInsert = campiInsert.substring(0, campiInsert.length() - 2);
                        insertSection += "(" + campiInsert + ", EXPORTED, EXTRACTION_ID, UUID, IMPORT_TYPE) VALUES (" + insertSectionI + ")";

                        if (formFlowTableDTO.getSection() == 0) {
                            fromSQL += rootTable + " ";
                        } else if (formFlowTableDTO.getListFk() != null && !formFlowTableDTO.getListFk().isEmpty()) {
                            fromSQL += "LEFT JOIN " + tableName + " ON (";
                            for (FormFlowTableLinkDTO link : formFlowTableDTO.getListFk()) {
                                FlowTableDO t = (link.getIdTable() == null || link.getIdTable().isBlank())
                                        ? null : flowTableDAO.findById(link.getIdTable()).orElse(null);
                                FlowTableFieldDO ft = (link.getIdFieldTable() == null || link.getIdFieldTable().isBlank())
                                        ? null : flowTableFieldDAO.findById(link.getIdFieldTable()).orElse(null);
                                FlowTableDO tr = (link.getIdTableReferenced() == null || link.getIdTableReferenced().isBlank())
                                        ? null : flowTableDAO.findById(link.getIdTableReferenced()).orElse(null);
                                FlowTableFieldDO ftr = (link.getIdFieldTableReferenced() == null || link.getIdFieldTableReferenced().isBlank())
                                        ? null : flowTableFieldDAO.findById(link.getIdFieldTableReferenced()).orElse(null);

                                if (t == null || ft == null || tr == null || ftr == null) continue;

                                fromSQL += rootTable2 + t.getSection() + "." + ft.getName()
                                        + " = " + rootTable2 + tr.getSection() + "." + ftr.getName()
                                        + " AND ";
                            }
                            if (fromSQL.endsWith(" AND ")) {
                                fromSQL = fromSQL.substring(0, fromSQL.length() - 5) + ") ";
                            }
                        }

                        if (updateSection.endsWith("AND ")) updateSection = updateSection.substring(0, updateSection.length() - 4);

                        preparedStaUpdate.add(connectionLocal.prepareStatement(updateSection));
                        preparedStaInsert.add(connectionLocal.prepareStatement(insertSection));
                        preparedStaUpdateSource.add(connectionSource.prepareStatement(updateSection));
                    }

                    if (campiSelect.endsWith(", ")) campiSelect = campiSelect.substring(0, campiSelect.length() - 2);

                    final String select = filterSQL.isBlank()
                            ? (selectSQL + campiSelect + fromSQL)
                            : (selectSQL + campiSelect + fromSQL + whereSQL + filterSQL);

                    preparedStatementSelect = connectionSource.prepareStatement(
                            select,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                    );

                    // parametri date: i += 2
                    if (extractionFilterDTO.getFields() != null) {
                        int i = 1;
                        for (Field field : extractionFilterDTO.getFields()) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date fromDate = format.parse(format.format(field.getForm()));
                            java.util.Date toDate = format.parse(format.format(field.getTo()));
                            preparedStatementSelect.setDate(i, new java.sql.Date(fromDate.getTime()));
                            preparedStatementSelect.setDate(i + 1, new java.sql.Date(toDate.getTime()));
                            i += 2;
                        }
                    }

                    preparedStatementSelect.setFetchSize(5000);
                    resultSet = preparedStatementSelect.executeQuery();

                    int whileIndex = 0;
                    int rowsNum = getRowCount(resultSet);
                    int insertIndexOk = 0;

                    while (resultSet.next()) {
                        int insertIndex = 0;
                        int indexSelect = 1;
                        boolean add = false;

                        for (List<String> sectionFields : fieldNames) {
                            List<Integer> pkList = primaryKeyHashMap.get(insertIndex);
                            int fieldIndex = 1;

                            boolean flag = checkPk(resultSet, insertIndex, recordHashMap, primaryKeyHashMap);

                            for (int k = 0; k < sectionFields.size(); k++) {
                                if (flag && recordIsNull(resultSet, pkList)) {
                                    add = true;
                                    preparedStaInsert.get(insertIndex).setObject(fieldIndex, resultSet.getObject(indexSelect));
                                } else {
                                    add = false;
                                }
                                indexSelect++;
                                fieldIndex++;
                            }

                            if (flag && recordIsNull(resultSet, pkList)) {
                                preparedStaInsert.get(insertIndex).setObject(fieldIndex, "1");
                                preparedStaInsert.get(insertIndex).setObject(fieldIndex + 1, extractionFilterDTO.getExtractionId());
                                preparedStaInsert.get(insertIndex).setObject(fieldIndex + 2, UUID.randomUUID().toString());
                                preparedStaInsert.get(insertIndex).setObject(fieldIndex + 3, ImportTypeEnum.FROM_TABLE.name());

                                preparedStaUpdate.get(insertIndex).setObject(1, 1);
                                preparedStaUpdate.get(insertIndex).setObject(2, extractionFilterDTO.getExtractionId());

                                preparedStaUpdateSource.get(insertIndex).setObject(1, 1);
                                preparedStaUpdateSource.get(insertIndex).setObject(2, extractionFilterDTO.getExtractionId());

                                int i = 3;
                                for (Integer pk : pkList) {
                                    Object pkVal = resultSet.getObject(pk);
                                    preparedStaUpdate.get(insertIndex).setObject(i, pkVal);
                                    preparedStaUpdateSource.get(insertIndex).setObject(i, pkVal);
                                    i++;
                                }
                            }

                            if (add) {
                                preparedStaUpdateOk.add(preparedStaUpdate.get(insertIndex));
                                preparedStaUpdateOk.get(insertIndexOk).addBatch();
                                preparedStaUpdate.get(insertIndex).clearParameters();

                                preparedStaUpdateSourceOk.add(preparedStaUpdateSource.get(insertIndex));
                                preparedStaUpdateSourceOk.get(insertIndexOk).addBatch();
                                preparedStaUpdateSource.get(insertIndex).clearParameters();

                                preparedStaInsertOk.add(preparedStaInsert.get(insertIndex));
                                preparedStaInsertOk.get(insertIndexOk).addBatch();
                                preparedStaInsert.get(insertIndex).clearParameters();

                                insertIndexOk++;
                            }

                            insertIndex++;
                        }

                        if (whileIndex % 1000 == 0 || rowsNum - 1 == whileIndex) {
                            for (PreparedStatement ps : preparedStaInsertOk) { ps.executeBatch(); ps.clearBatch(); ps.clearParameters(); }
                            for (PreparedStatement ps : preparedStaUpdateOk) { ps.executeBatch(); ps.clearBatch(); ps.clearParameters(); }
                            for (PreparedStatement ps : preparedStaUpdateSourceOk) { ps.executeBatch(); ps.clearBatch(); ps.clearParameters(); }

                            // commit SOLO sul SOURCE (DB esterno)
                            connectionSource.commit();

                            preparedStaInsertOk.clear();
                            preparedStaUpdateOk.clear();
                            preparedStaUpdateSourceOk.clear();
                            insertIndexOk = 0;
                        }

                        whileIndex++;
                    }

                } catch (Exception e) {
                	LogUtil.logException(LOGGER, "", e);
                    try { connectionSource.rollback(); } catch (Exception ignore) {}
                    throw new RuntimeException(e);
                } finally {
                    try { if (resultSet != null) resultSet.close(); } catch (Exception ignore) {}
                    try { if (preparedStatementSelect != null) preparedStatementSelect.close(); } catch (Exception ignore) {}

                    closeAllStatements(preparedStaInsert);
                    closeAllStatements(preparedStaUpdate);
                    closeAllStatements(preparedStaUpdateSource);
                }
            });

        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // stato SUCCESS
        try {
            state = stateMachineFlow.execute(
                    extractionFilterDTO.getExtractionId(),
                    "ESTRAZIONE",
                    MachineEvent.ESTRAZIONE_SUCCESS.getEvent()
            );
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
        }
        changeStateRecord(extractionFilterDTO.getExtractionId(), state);

        return state;
    }
    
    private static void closeAllStatements(List<PreparedStatement> list) {
        if (list == null) return;
        for (PreparedStatement ps : list) {
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
        }
        list.clear();
    }
    
    
//    public boolean compareResultSet(Connection connectionLocal, Connection connectionSource,
//                                    String select, ExtractionFilterDTO extractionFilterDTO)
//            throws SQLException, ParseException {
//        PreparedStatement preparedStatement2 = connectionLocal.prepareStatement(select,
//                ResultSet.TYPE_FORWARD_ONLY,
//                ResultSet.CONCUR_UPDATABLE,
//                ResultSet.FETCH_FORWARD);
//        PreparedStatement preparedStatement = connectionSource.prepareStatement(select,
//                ResultSet.TYPE_FORWARD_ONLY,
//                ResultSet.CONCUR_UPDATABLE,
//                ResultSet.FETCH_FORWARD);
//        if (extractionFilterDTO.getFields() != null) {
//            int i = 1;
//            for (Field field : extractionFilterDTO.getFields()) {
//                SimpleDateFormat format =
//                        new SimpleDateFormat("yyyy-MM-dd");
//                String fromString = format.format(field.getForm());
//                java.util.Date fromDate = format.parse(fromString);
//                String toString = format.format(field.getTo());
//                java.util.Date toDate = format.parse(toString);
//                java.sql.Date sqlDateFrom = new java.sql.Date(fromDate.getTime());
//                java.sql.Date sqlDateTo = new java.sql.Date(toDate.getTime());
//                preparedStatement2.setDate(i, sqlDateFrom);
//                preparedStatement2.setDate(i + 1, sqlDateTo);
//                preparedStatement.setDate(i, sqlDateFrom);
//                preparedStatement.setDate(i + 1, sqlDateTo);
//                i++;
//            }
//        }
//
//        preparedStatement2.setFetchSize(5000);
//        ResultSet resultSet2 = preparedStatement2.executeQuery();
//
//        preparedStatement.setFetchSize(5000);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        int col = 1;
//        while (resultSet.next() && resultSet2.next()) {
//            //saltare i primi 5 cambpi del ResultsSet Locale.
//            if (!(col >= 1 && col <= 5)) {
//
//                final Object res1 = resultSet.getObject(col);
//                final Object res2 = resultSet2.getObject(col);
//                // Check values
//                if (!res1.equals(res2)) {
//                    return false;
//                    //throw new RuntimeException(String.format("%s and %s aren't equal at common position %d",
//                    //       res1, res2, col));
//                }
//
////            // rs1 and rs2 must reach last row in the same iteration
////            if ((resultSet.isLast() != resultSet2.isLast())) {
////                throw new RuntimeException("The two ResultSets contains different number of columns!");
////            }
//
//                col++;
//            }
//        }
//
//        return true;
//    }
    
    public boolean compareResultSet(Connection connectionLocal, Connection connectionSource,
            String select, ExtractionFilterDTO extractionFilterDTO)
            		throws SQLException, ParseException {
	
		try (PreparedStatement ps2 = connectionLocal.prepareStatement(select,
			ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, ResultSet.FETCH_FORWARD);
			PreparedStatement ps1 = connectionSource.prepareStatement(select,
			ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, ResultSet.FETCH_FORWARD)) {
		
			if (extractionFilterDTO.getFields() != null) {
				int i = 1;
				for (Field field : extractionFilterDTO.getFields()) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date fromDate = format.parse(format.format(field.getForm()));
					java.util.Date toDate = format.parse(format.format(field.getTo()));
					java.sql.Date sqlFrom = new java.sql.Date(fromDate.getTime());
					java.sql.Date sqlTo = new java.sql.Date(toDate.getTime());
					
					ps2.setDate(i, sqlFrom);
					ps2.setDate(i + 1, sqlTo);
					ps1.setDate(i, sqlFrom);
					ps1.setDate(i + 1, sqlTo);
					i += 2;
				}
			}
		
		ps2.setFetchSize(5000);
		ps1.setFetchSize(5000);
		
		try (ResultSet rs2 = ps2.executeQuery();
		ResultSet rs1 = ps1.executeQuery()) {
		
			int col = 1;
			while (rs1.next() && rs2.next()) {
				if (!(col >= 1 && col <= 5)) {
					Object res1 = rs1.getObject(col);
					Object res2 = rs2.getObject(col);
					if (!Objects.equals(res1, res2)) return false;
					col++;
					}
				}
				return true;
			}
		}
	}


    public boolean recordIsNull(ResultSet resultSet, List<Integer> listPK) throws SQLException {
        boolean flag = true;
        for (Integer pk : listPK) {
            if (resultSet.getObject(pk) == null) {
                flag = false;
            }
        }
        return flag;

    }


    public boolean checkPk(ResultSet resultSet,
                           Integer section,
                           HashMap<Integer, HashMap<String, String>> recordHashMap,
                           HashMap<Integer, List<Integer>> primaryKeyHashMap) throws SQLException {

        //get position number key section
        List<Integer> pkList = primaryKeyHashMap.get(section);
        //Build concat key
        String key = "";
        for (Integer pk : pkList) {
            key += resultSet.getObject(pk);
            if (key == null || key.isEmpty() || key.equals("null")) {
            	return false;
            }
        }
        if (recordHashMap.containsKey(section)) {
            if (!recordHashMap.get(section).containsKey(key)) {
                recordHashMap.get(section).put(key, "");
                return true;
            } else
                recordHashMap.get(section).put(key, "");
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(key, "");
            recordHashMap.put(section, hashMap);
            return true;
        }
        return false;
    }


    public DataSourceDTO getDataSource(FormFlowDTO formFlowDTO) {
    	DataSourceDTO retDataSource = new DataSourceDTO();
    	if (formFlowDTO!=null && formFlowDTO.getFlowTableList()!=null && !formFlowDTO.getFlowTableList().isEmpty()) {
    		retDataSource = conversionService.convertTo(datasourceService.retrieveOne(
    				formFlowDTO.getFlowTableList().get(0).getDatasource()), DataSourceDTO.class);
    	}
    	return retDataSource;
    }

    //TODO
    public void setFlagExtract(FormFlowDTO formFlowDTO) {

    }

    public void extractionInsert(DataSourceDTO dataSourceDTO, String select, List<String> fieldNames,
                                 List<PreparedStatement> preparedSta) {

    }

    public String updateRecordSQL(String table, HashMap<String, String> columnValue, String condition) {
        String scriptSQL = "UPDATE " + table + " SET ";
        for (Map.Entry<String, String> entry : columnValue.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            scriptSQL += key + "=" + value;
        }
        scriptSQL += " WHERE " + condition;
        return scriptSQL;
    }
    
    private static int getRowCount(ResultSet resultSet) throws SQLException {
        int rowCount = 0;
        if (resultSet != null && resultSet.last()) {
            rowCount = resultSet.getRow();
            resultSet.beforeFirst(); // Move cursor back to the beginning
        }
        return rowCount;
    }
    
}
