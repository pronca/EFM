package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.FlowExportMessageDetailsDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;

@Repository
public interface FlowExportMessageDetailsDAO extends JpaRepository<FlowExportMessageDetailsDO, String> {
	
	List<FlowExportMessageDetailsDO> findAllByFlowExportRequest(FlowExportRequestDO flowExportRequest);
		
}
