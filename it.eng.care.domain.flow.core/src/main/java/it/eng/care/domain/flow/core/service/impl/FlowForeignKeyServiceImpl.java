package it.eng.care.domain.flow.core.service.impl;

import it.eng.care.domain.flow.core.dao.FlowForeignKeyDAO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.service.FlowForeignKeyService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class FlowForeignKeyServiceImpl implements FlowForeignKeyService {

    @Autowired
    FlowForeignKeyDAO flowForeignKeyDAO;


    @Override
    public FlowForeignKeyDO createEntity(FlowForeignKeyDO dto) {
        return flowForeignKeyDAO.save(dto);
    }


    @Override
    public FlowForeignKeyDO updateEntity(FlowForeignKeyDO dto) {
        return flowForeignKeyDAO.save(dto);
    }

    @Override
    public Pair<List<FlowForeignKeyDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {

        List<FlowForeignKeyDO> loList = flowForeignKeyDAO.cerca(searchInput);
        return Pair.of(loList, SearchInfos.create());

    }
    
    @Override
    public void deleteLink(String id) {	
    	FlowForeignKeyDO fkDao = flowForeignKeyDAO.getOne(id);
    	flowForeignKeyDAO.deleteById(fkDao.getId());
    }


}
