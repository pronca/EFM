package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.core.entity.EmailConfigDO;

public interface EmailConfigDAO  extends JpaRepository<EmailConfigDO, String> {

	List<EmailConfigDO> findAllByFlowAndEnabled(String flow, String enabled);


}