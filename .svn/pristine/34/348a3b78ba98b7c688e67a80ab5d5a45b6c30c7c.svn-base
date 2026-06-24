package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.FlowExportReqFileTalendDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;

@Repository
public interface FlowExportReqFileTalendDAO extends JpaRepository<FlowExportReqFileTalendDO, String> {

	List<FlowExportReqFileTalendDO> findAllByflowExportRequest(FlowExportRequestDO extractionId);		
	
//    @QueryByCriteria(FlowExportRequestDAOQueryByBaseSearchInput.class)
//    List<FlowExportRequestDO> cerca(BaseSearchInput poInput, Pageable pageable);
//}
}
