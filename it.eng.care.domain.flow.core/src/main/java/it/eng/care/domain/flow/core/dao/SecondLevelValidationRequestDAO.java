package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.core.entity.SecondLevelValidationRequestDO;

public interface SecondLevelValidationRequestDAO extends JpaRepository<SecondLevelValidationRequestDO, String> {

	public List<SecondLevelValidationRequestDO> findAllByStatus(String status);
	
}
