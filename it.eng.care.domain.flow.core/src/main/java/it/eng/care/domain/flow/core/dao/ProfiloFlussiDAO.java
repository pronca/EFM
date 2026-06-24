package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiDO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiPrimaryKeyDO;

@Repository
public interface ProfiloFlussiDAO extends JpaRepository<ProfiloFlussiDO, ProfiloFlussiPrimaryKeyDO> {

	List<ProfiloFlussiDO> findByFlow(FlowDO flow);
	
}



