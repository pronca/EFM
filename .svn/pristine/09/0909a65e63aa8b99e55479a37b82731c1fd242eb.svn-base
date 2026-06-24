package it.eng.care.domain.flow.core.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.controller.MonitorSdoXlController;
import it.eng.care.domain.flow.core.dao.FlowExportMessageDetailsDAO;
import it.eng.care.domain.flow.core.dao.FlowExportReqFileTalendDAO;
import it.eng.care.domain.flow.core.dao.FlowExportRequestDAO;
import it.eng.care.domain.flow.core.dto.MonitorSdoPaginDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowExportMessageDetailsDO;
import it.eng.care.domain.flow.core.entity.FlowExportReqFileTalendDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.TalendThreadService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class TalendThreadServiceImpl implements TalendThreadService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TalendThreadServiceImpl.class);
	
    @Autowired
    private DataSource dataSource;

	@Autowired
	private FlowExportRequestDAO flowExtractDAO;
	
	@Autowired
    private FormFlowService formFlowService;
	
	@Autowired
    EntityManager entityManager;
	
	@Autowired
	FlowExportReqFileTalendDAO flowExportReqFileTalendDAO;
	
	@Autowired
	private FlowExportMessageDetailsDAO flowExportMessageDetailsDAO;
	
	@Autowired
	private MonitorSdoXlController monitorSdoXlController;

	@Override
	public void save(FlowExportRequestDO flowExportRequestDO) {
		flowExtractDAO.save(flowExportRequestDO);		
	}
	
	@Override
	public Integer countExtractionRecord(String exportId) throws SQLException {

	    FlowExportRequestDO flowExportRequestDO = flowExtractDAO.findById(exportId).orElse(null);
	    if (flowExportRequestDO == null) {
	        return 0;
	    }

	    if (flowExportRequestDO.getFlow() != null
	            && "SDO_XL".equals(flowExportRequestDO.getFlow().getName())) {

	        BaseSearchInput searchInput = new BaseSearchInput();
	        searchInput.setValue("idEstrazione", flowExportRequestDO.getId());
	        searchInput.setValue("pageSize", 1);
	        searchInput.setValue("pageNo", 0);
	        searchInput.setValue("sortDir", "asc");
	        searchInput.setValue("sortField", "idEstrazione");

	        OperationResult<MonitorSdoPaginDTO> c = monitorSdoXlController.filterData(searchInput);
	        return c.getResult().getCount().intValue();
	    }

	    String version = flowExportRequestDO.getVersion().getId();
	    String flow = flowExportRequestDO.getFlow().getId();

	    FormFlowDTO formFlowDTOazi = formFlowService.retrieveOne(flow, version);

	    final String table = "FM_FLOW_" + formFlowDTOazi.getName() + "_REG_" + formFlowDTOazi.getFlowTableList().get(0).getSection();
	    final String sql = "SELECT COUNT(EXTRACTION_ID) FROM " + table + " WHERE EXTRACTION_ID = ?";
	    final String extractionId = flowExportRequestDO.getId();

	    try {
	        Session session = entityManager.unwrap(Session.class);
	        Integer records = session.doReturningWork((ReturningWork<Integer>) conn -> {
	            try (PreparedStatement ps = conn.prepareStatement(sql)) {
	                ps.setFetchSize(5000);
	                ps.setString(1, extractionId);

	                try (ResultSet rs = ps.executeQuery()) {
	                    if (rs.next()) {
	                        return rs.getInt(1);
	                    }
	                    return 0;
	                }
	            }
	        });

	        return records != null ? records : 0;

	    } catch (RuntimeException re) {
	        LogUtil.logException(LOGGER, "", re);
	        Throwable cause = re.getCause();
	        if (cause instanceof SQLException se) {
	            throw se;
	        }
	        throw new SQLException("Errore countExtractionRecord", re);
	    }
	}
	
	@Override
	public int checkValidation(String exportId) {
	    FlowExportRequestDO flowExportRequestDO = flowExtractDAO.findById(exportId).orElse(null);
	    if (flowExportRequestDO == null) {
	        return 0;
	    }

	    List<FlowExportReqFileTalendDO> extractions = flowExportReqFileTalendDAO.findAllByflowExportRequest(flowExportRequestDO);

	    for (FlowExportReqFileTalendDO extraction : extractions) {
	        if (extraction.getLog() != null) {
	            return 1;
	        }
	    }
	    return 0;
	}

	@Override
	public void deleteErrors(String exportId) {
		
		FlowExportRequestDO export = new FlowExportRequestDO();
		export.setId(exportId);
		List<FlowExportMessageDetailsDO> errorsList = flowExportMessageDetailsDAO.findAllByFlowExportRequest(export);
		if(errorsList!=null && !errorsList.isEmpty()) {
			for(FlowExportMessageDetailsDO error: errorsList) {
				if(!"Estrazione terminata dall'utente".equals(error.getErrorValue())) {
					flowExportMessageDetailsDAO.deleteById(error.getId());
				}
			}
		}
		
	}
	
}
