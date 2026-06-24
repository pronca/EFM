package it.eng.care.domain.flow.core.dao.querySearch;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.dao.ErrorViewDAO;
import it.eng.care.domain.flow.core.dto.MonitorSdoXlErrorDTO;
import it.eng.care.domain.flow.core.utility.LogUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorFlowErrorByQuerySearchInput implements ErrorViewDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorFlowErrorByQuerySearchInput.class);
	
	@Autowired
	private EntityManager entityManager;
	
	private final String tablePrefix = "_REG_SCARTI_REGIONE"; 
	
	@Override
	public List<MonitorSdoXlErrorDTO> searchErrorsByFlowAndNosologico(String flow, String nosologico, String presidio, String idEstrazione, String nosologicoConditional){	
		List<MonitorSdoXlErrorDTO> errorDataListDto = new ArrayList<MonitorSdoXlErrorDTO>();
		List<Object[]> errorDataList = new ArrayList<>();
		try {

			Query query = entityManager.createNativeQuery(createQuery(flow,nosologico, presidio, idEstrazione, nosologicoConditional));
			errorDataList = query.getResultList();
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
	    }
		
		for(Object[] monitorSdoXlErrorDTO:errorDataList) {
			MonitorSdoXlErrorDTO dto = new MonitorSdoXlErrorDTO();
			dto.setCodice((String)monitorSdoXlErrorDTO[0]);
			dto.setDescrizione((String)monitorSdoXlErrorDTO[1]);
//			dto.setValoreErato((String)monitorSdoXlErrorDTO[2]);
//			dto.setCampo((String)monitorSdoXlErrorDTO[3]);
			dto.setDateValidazione((Date)monitorSdoXlErrorDTO[2]);
			dto.setGravita((String)monitorSdoXlErrorDTO[3]);
			errorDataListDto.add(dto);
		}
		return errorDataListDto;
	}
	
	private String createQuery(String flowName,String nosologico, String presidio, String idEstrazione, String nosologicoConditional) {
			
		String selectSQL = "SELECT ";
		String columns = "CODICEERRORE,DESCRIZIONEERRORE,RECEIVING_DATE,SEVERITY ";
		String fromSQl = "FROM ";
		String whereSQL = "WHERE ";		
		String tableScarti = flowName.trim().concat(tablePrefix);
		if (nosologicoConditional==null) {
			nosologicoConditional="KPROGRESSIVOSDO";
		}
		
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append(selectSQL)
				   .append(columns)
				   .append(fromSQl)
				   .append(" ")
				   .append(tableScarti)
				   .append(" ")
				   .append(whereSQL)
				   .append(nosologicoConditional + " = '"+nosologico+"'")
				   .append(" AND ")
				   .append("CODICEPRESIDIO = '" + presidio + "'")
				   .append(" AND ")
				   .append("EXTRACTION_ID = '" + idEstrazione + "'");
				  
		return queryBuffer.toString();
	}

}
