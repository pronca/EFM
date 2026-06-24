package org.it.eng.care.domain.flow.FlowManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.care.domain.flow.core.config.C3P0DataSource;
import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.dto.DriverDTO;
import it.eng.care.domain.flow.core.dto.ExtractionFilter.ExtractionFilterDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.service.FlowExtractionService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import jakarta.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FlowManagerTestConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlowManagerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowManagerTest.class);
	
	@Autowired
	private FormFlowService formFlowService;

	@Autowired
	private FlowExtractionService flowExtractionService;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void extractionTest() {
		ExtractionFilterDTO extractionFilterDTO = new ExtractionFilterDTO();
		BaseSearchInput baseSearchInput = new BaseSearchInput();
		// 390c8220-88e5-4fb4-b18b-20b0ffe3ceb4
		baseSearchInput.setValue("flowId", "c299ad8c-012e-471c-8c0c-ac721fe242cf");
		// baseSearchInput.setValue("flowId","bd0ca8b0-ce4a-45a0-ad62-37765e53d179");
		Pair<List<FormFlowDTO>, SearchInfo> formFlowPair = formFlowService.retrieveAllFiltered(baseSearchInput);
		FormFlowDTO formFlowDTO = formFlowPair.getFirst().get(0);
		extractionFilterDTO.setFormFlow(formFlowDTO);
		flowExtractionService.extraction(extractionFilterDTO);
	}
	
	@Test
	@Transactional
	@Rollback(false) // se vuoi vedere i dati inseriti; altrimenti metti true
	public void insertRecordDatasource() {

	    DriverDTO driverDTO = new DriverDTO();
	    driverDTO.setId("0");
	    driverDTO.setName("oracle");
	    driverDTO.setDescription("Driver Oracle");

	    DataSourceDTO dataSourceDTO = new DataSourceDTO();
	    dataSourceDTO.setId("123456");
	    dataSourceDTO.setName("mysql");
	    dataSourceDTO.setDriver(driverDTO);
	    dataSourceDTO.setEnabled(true);
	    dataSourceDTO.setUsername("BDSAV");
	    dataSourceDTO.setPassword("BDSAV");
	    dataSourceDTO.setPort("3306");
	    dataSourceDTO.setHostname("localhost");
	    dataSourceDTO.setServiceName("flow_manager");

	    // ✅ invece di creare e gestire commit manuale, prendiamo la connessione “transazionale” di Spring
	    Connection con = DataSourceUtils.getConnection(dataSource);
	    // NB: niente setAutoCommit(false)

	    String sql1 = "INSERT INTO FM_FLOW_EXTEST_0 (ID,STATUS,VERSION,STATUS_PROCESS,DATE_PROCESSING,CMP1,CMP2) VALUES (?,?,?,?,?,?,?)";
	    String sql2 = "INSERT INTO FM_FLOW_EXTEST_1 (ID,STATUS,VERSION,STATUS_PROCESS,DATE_PROCESSING,CMP1,CMP2) VALUES (?,?,?,?,?,?,?)";

	    PreparedStatement preparedStatement1 = null;
	    PreparedStatement preparedStatement2 = null;

	    try {
	        preparedStatement1 = con.prepareStatement(sql1);
	        preparedStatement2 = con.prepareStatement(sql2);

	        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        long start = System.currentTimeMillis();

	        for (int i = 0; i <= 1_000_000; i++) {

	            preparedStatement1.setObject(1, i);
	            preparedStatement1.setObject(2, "01010101");
	            preparedStatement1.setObject(3, "0101010101010");
	            preparedStatement1.setObject(4, "01010");
	            preparedStatement1.setObject(5, date);
	            preparedStatement1.setObject(6, i + "aqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedw");
	            preparedStatement1.setObject(7, i + "aqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedw");

	            preparedStatement2.setObject(1, i);
	            preparedStatement2.setObject(2, "01");
	            preparedStatement2.setObject(3, "01");
	            preparedStatement2.setObject(4, "01");
	            preparedStatement2.setObject(5, date);
	            preparedStatement2.setObject(6, i + "aqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedw");
	            preparedStatement2.setObject(7, i + "aqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedwaqswdefr123wsedw");

	            preparedStatement1.addBatch();
	            preparedStatement2.addBatch();

	            if (i % 5000 == 0) {
	                preparedStatement1.executeBatch();
	                long end1 = System.currentTimeMillis();
	                System.out.println("ExecuteBatch1:" + ((end1 - start) / 1000F) + " seconds");

	                preparedStatement2.executeBatch();
	                long end2 = System.currentTimeMillis();
	                System.out.println("ExecuteBatch2:" + ((end2 - start) / 1000F) + " seconds");

	                // ✅ niente con.commit(): farà tutto Spring a fine test (o rollback se @Rollback(true))
	            }
	        }

	        // flush finale
	        preparedStatement1.executeBatch();
	        preparedStatement2.executeBatch();

	    } catch (SQLException e) {
	    	LogUtil.logException(LOGGER, "", e);
	    	
	        throw new RuntimeException(e);
	    } finally {
	        try { if (preparedStatement1 != null) preparedStatement1.close(); } catch (Exception ignore) {}
	        try { if (preparedStatement2 != null) preparedStatement2.close(); } catch (Exception ignore) {}

	        // ✅ IMPORTANTISSIMO: rilascia la connessione gestita da Spring
	        DataSourceUtils.releaseConnection(con, dataSource);
	    }
	}

	
	@Test
	public void insertFormFlow() throws SQLException {

		DriverDTO driverDTO = new DriverDTO();
		driverDTO.setId("0");
		driverDTO.setName("oracle");
		driverDTO.setDescription("Driver Oracle");

		DataSourceDTO dataSourceDTO = new DataSourceDTO();
		dataSourceDTO.setId("123456");
		dataSourceDTO.setName("orcale");
		dataSourceDTO.setDriver(driverDTO);
		dataSourceDTO.setEnabled(true);
		dataSourceDTO.setUsername("WEB118_CLU_BO");
		dataSourceDTO.setPassword("web118_clu");
		dataSourceDTO.setPort("1561");
		dataSourceDTO.setHostname("161.27.202.59");
		dataSourceDTO.setServiceName("DB118");

		try (Connection con = C3P0DataSource.getInstance(dataSourceDTO).getConnection()) {
			Statement stmt = con.createStatement();
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM PMLINK_CONFIG");
				ResultSetMetaData metadata = rs.getMetaData();
				int cols = metadata.getColumnCount();
				System.out.println("\n-----------------------------" + "--------------------------------");
				for (int i = 0; i < cols; i++) {
					System.out.printf("%-20s\t", metadata.getColumnName(i + 1).toUpperCase());
				}
				System.out.println("\n-----------------------------" + "--------------------------------");
				while (rs.next()) {
					for (int i = 0; i < cols; i++)
						System.out.printf("%-20s\t", rs.getObject(i + 1));
					System.out.println();
				}
				System.out.println("-------------------------------" + "--------------------------------");
	
			} catch (SQLException e) {
				LogUtil.logException(LOGGER, "", e);
				try { con.rollback(); } catch (Exception ignore) {}
//				e.printStackTrace();
			} finally {
				if (stmt != null) try { stmt.close(); } catch (Exception ignore) {}
			}
		}

	}

}
