package it.eng.care.domain.flow.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;

@Repository
public interface UploadReturnsRequestDAO extends JpaRepository<UploadReturnsRequestDO, String> {
	
	UploadReturnsRequestDO findByExtractionId(String extractionId);
	
	UploadReturnsRequestDO findByExtractionIdAndFlowId(String extractionId, String flowId);
	
	void deleteByExtractionId(String extractionId);
	
	void deleteByExtractionIdAndFlowId(String extractionId, String flowId);
	
}
