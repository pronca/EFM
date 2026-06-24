package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.StateMachinePersistHistoryDO;

public interface StateMachinePersistHistoryService {

    StateMachinePersistHistoryDO createEntity(StateMachinePersistHistoryDO dto);


    StateMachinePersistHistoryDO updateEntity(StateMachinePersistHistoryDO dto);

}
