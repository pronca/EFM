package it.eng.care.domain.flow.core.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.dao.querySearch.FlowExportRequestDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.enumeration.MachineState;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface FlowExportRequestDAO extends JpaRepository<FlowExportRequestDO, String> {

    @QueryByCriteria(FlowExportRequestDAOQueryByBaseSearchInput.class)
    List<FlowExportRequestDO> cerca(BaseSearchInput poInput, Pageable pageable, Boolean useFlowProfile);
    
    @QueryByCriteria(FlowExportRequestDAOQueryByBaseSearchInput.class)
    List<FlowExportRequestDO> getRequestToManage(MachineState status, String validationStatus);
    
    @QueryByCriteria(FlowExportRequestDAOQueryByBaseSearchInput.class)
    Long countFlowExportByFilter(BaseSearchInput poInput, Boolean useFlowProfile);
    
    List<FlowExportRequestDO> findByDrgAndValidationStatusInAndDeleted(boolean drg, Collection<String> validationStatus, Integer deleted);
    
}
