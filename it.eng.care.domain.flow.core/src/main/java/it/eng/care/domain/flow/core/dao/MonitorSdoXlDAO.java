package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.core.dao.querySearch.MonitorSdoByQuerySearchInput;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public interface MonitorSdoXlDAO extends JpaRepository<MonitorSdoXlDO, String> {
	
	Page<MonitorSdoXlDO> findAll(Pageable pageable);
	
	Page<MonitorSdoXlDO> findAllByDeletedOrDeletedIsNull(Pageable pageable, Boolean deleted);
	
	@QueryByCriteria(MonitorSdoByQuerySearchInput.class)
	List<MonitorSdoXlDO> filterData(BaseSearchInput poInput);
	
}
