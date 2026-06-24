package it.eng.care.domain.flow.flowupload.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface FlowFileUploadRequestDAO extends JpaRepository<FlowFileUploadRequestDO, String> {
	
	@QueryByCriteria(FlowFileUploadRequestDAObyBaseSearchInput.class)
    List<FlowFileUploadRequestDO> cerca(BaseSearchInput poInput, Pageable pageable);
	
	@QueryByCriteria(FlowFileUploadRequestDAObyBaseSearchInput.class)
    Long countFlowExportByFilter(BaseSearchInput poInput);

}
