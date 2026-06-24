package it.eng.care.domain.flow.core.service.impl;

import it.eng.care.domain.flow.core.dao.StateMachinePersistHistoryDAO;
import it.eng.care.domain.flow.core.entity.StateMachinePersistHistoryDO;
import it.eng.care.domain.flow.core.service.StateMachinePersistHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class StateMachinePersistHistoryServiceImpl implements StateMachinePersistHistoryService {


    @Autowired
    StateMachinePersistHistoryDAO stateMachinePersistHistoryDAO;


    @Override
    public StateMachinePersistHistoryDO createEntity(StateMachinePersistHistoryDO dto) {
        return stateMachinePersistHistoryDAO.save(dto);
    }

    @Override
    public StateMachinePersistHistoryDO updateEntity(StateMachinePersistHistoryDO dto) {
        return stateMachinePersistHistoryDAO.save(dto);
    }
}
