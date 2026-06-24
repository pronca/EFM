package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.FlowImportRequestDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowImportRequestDAO extends JpaRepository<FlowImportRequestDO, String> {

	@QueryByCriteria(FlowImportRequestDAOQueryByBaseSearchInput.class)
    List<FlowImportRequestDO> cerca(BaseSearchInput poInput, Pageable pageable);
	
	@QueryByCriteria(FlowImportRequestDAOQueryByBaseSearchInput.class)
    List<FlowImportRequestDO> cerca(BaseSearchInput poInput, Pageable pageable, Boolean useFlowProfile);

    @QueryByCriteria(FlowImportRequestDAOQueryByBaseSearchInput.class)
    List<FlowImportRequestDO> getRequestToValidate();
}
