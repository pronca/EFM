package it.eng.care.domain.flow.flowupload.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestErrorDO;

@Repository
public interface FlowFileUploadRequestErrorDAO extends JpaRepository<FlowFileUploadRequestErrorDO, String> {
	
	List<FlowFileUploadRequestErrorDO>findByRequest(FlowFileUploadRequestDO request);

}
