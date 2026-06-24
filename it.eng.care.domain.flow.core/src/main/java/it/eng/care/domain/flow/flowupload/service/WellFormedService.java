package it.eng.care.domain.flow.flowupload.service;

import java.io.IOException;
import java.sql.SQLException;

import it.eng.care.domain.flow.flowupload.bean.WellFormedStatusEnum;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;

public interface WellFormedService {
	
	public WellFormedStatusEnum checkFiles(FlowFileUploadRequestDO request) throws IOException, SQLException;

	public WellFormedStatusEnum checkRequiredAndEmptySections(FlowFileUploadRequestDO request);

}
