package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.entity.DriverDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverDAO extends JpaRepository<DriverDO, String> {


}
