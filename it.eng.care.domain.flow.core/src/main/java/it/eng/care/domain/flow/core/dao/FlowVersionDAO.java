package it.eng.care.domain.flow.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.FlowVersionPrimaryKeyDO;

@Repository
public interface FlowVersionDAO extends JpaRepository<FlowVersionDO, FlowVersionPrimaryKeyDO> {

    FlowVersionDO findByFlowNameAndVersionVersion(String flowName, String versionName);
    
    FlowVersionDO findByFlow(FlowDO flow);

}
