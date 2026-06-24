package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.entity.ErrorMessageDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorMessageDAO extends JpaRepository<ErrorMessageDO, String> {

}
