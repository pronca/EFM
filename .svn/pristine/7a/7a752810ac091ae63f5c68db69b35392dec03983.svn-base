package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.core.entity.ResetValidationRequestDO;

public interface ResetValidationRequestDAO extends JpaRepository<ResetValidationRequestDO, String> {
	
	public List<ResetValidationRequestDO> findAllByStatus(String status);
	
}
