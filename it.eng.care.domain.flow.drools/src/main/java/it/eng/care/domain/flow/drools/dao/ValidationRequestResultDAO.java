package it.eng.care.domain.flow.drools.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.eng.care.domain.flow.drools.entity.ValidationRequestResultDO;
import jakarta.transaction.Transactional;

public interface ValidationRequestResultDAO extends JpaRepository<ValidationRequestResultDO, String> {
	
	@Modifying
    @Transactional
    @Query("delete from ValidationRequestResultDO v where v.importingRequestId = :id")
    int deleteByImportingRequestId(@Param("id") String id);
	
}
