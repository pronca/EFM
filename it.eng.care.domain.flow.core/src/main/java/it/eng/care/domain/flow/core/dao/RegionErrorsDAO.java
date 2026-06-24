package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.RegionErrorsDO;

@Repository
public interface RegionErrorsDAO extends JpaRepository<RegionErrorsDO, String> {

    List<RegionErrorsDO> findTopBySendStatusAndNretryLessThan(String sendStatus, int trys);
    
    RegionErrorsDO findByExtrIdAndKeyMessageAndFlowId_Id(String extrId, String keyMessage, String flowId);
    
    RegionErrorsDO findTopByFlowId_IdAndKeyMessageAndSendStatusOrderByCreationDateDesc(
            String flowId,
            String keyMessage,
            String sendStatus
    );
    
}