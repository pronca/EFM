package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.JobTalendDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTalendDAO extends JpaRepository<JobTalendDO, String> {

	@QueryByCriteria(JobTalendDAOQueryByBaseSearchInput.class)
    List<JobTalendDO> cerca(BaseSearchInput poInput, Pageable pageable);
	
	@QueryByCriteria(JobTalendDAOQueryByBaseSearchInput.class)
    List<JobTalendDO> cerca(BaseSearchInput poInput, Pageable pageable, Boolean useFlowProfile);

}
