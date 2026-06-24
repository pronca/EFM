package it.eng.care.domain.flow.b2b.service;

import java.util.List;

import it.eng.care.domain.flow.b2b.model.RegionErrorModelDTO;
import it.eng.care.domain.flow.core.entity.RegionErrorsDO;

public interface RegionMessageService {

	void insertEvent(RegionErrorModelDTO model, String flowName, String jsonString, String keyMessage);

    void saveOrUpdateEvent(RegionErrorsDO error);

    List<RegionErrorsDO> findAllPendingErrors(int numErr);
    
    void insertOrUpdatePendingEvent(RegionErrorModelDTO model, String flowName, String jsonString, String keyMessage);
}