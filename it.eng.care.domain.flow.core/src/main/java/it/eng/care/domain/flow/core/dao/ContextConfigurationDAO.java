package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.ContextConfigurationDO;

@Repository
public interface ContextConfigurationDAO extends JpaRepository<ContextConfigurationDO, String> {

	List<ContextConfigurationDO> findAllByFlow(String flow);
		
}





