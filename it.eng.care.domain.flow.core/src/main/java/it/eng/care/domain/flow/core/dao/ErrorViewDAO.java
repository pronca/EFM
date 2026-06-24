package it.eng.care.domain.flow.core.dao;

import java.util.List;

import it.eng.care.domain.flow.core.dto.MonitorSdoXlErrorDTO;


public interface ErrorViewDAO {
	
	List<MonitorSdoXlErrorDTO> searchErrorsByFlowAndNosologico(String flow, String nosologico, String presidio,
			String idEstrazione, String nosologicoConditional);
}
