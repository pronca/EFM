package it.eng.care.domain.flow.drools.utility.api;

import java.util.List;
import java.util.Map;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;

public interface RowConverter {

	public ValidationBean convertRows(List<Map<String, Object>> rowsMap, FormFlowDTO configuration, String flowPrefix, List<FormFlowTableFieldDTO> pkListCfg);

}
