package it.eng.care.domain.flow.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.FlowTableFieldDAO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowTableFieldService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlowTableFieldServiceImpl implements FlowTableFieldService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowTableFieldServiceImpl.class);
	
    @Autowired
    private FlowTableFieldDAO flowTableFiledDAO;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    public FlowTableFieldDO createEntity(FlowTableFieldDO poValue) {
        return flowTableFiledDAO.save(poValue);
    }

    public FlowTableFieldDO updateEntity(FlowTableFieldDO poValue) {
        return flowTableFiledDAO.save(poValue);
    }

    public Pair<List<FlowTableFieldDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile) {
//		int startIndex = searchInput.getParam("startIndex");
//		int limit = searchInput.getParam("limit");
//		Pageable page = new PageRequest(new Integer(startIndex / limit), limit);

        List<FlowTableFieldDO> loList = flowTableFiledDAO.cerca(searchInput, useFlowProfile);
        return Pair.of(loList, SearchInfos.create());
    }

    public List<FlowTableFieldDO> findAll() {
        try {
            return flowTableFiledDAO.findAll();
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
        }
        return null;
    }

    public FlowTableFieldDO retrieveOne(String id) {
        return (id == null || id.isBlank())
                ? null
                : flowTableFiledDAO.findById(id).orElse(null);
    }

    public void deleteEntity(String id) {
        String iid = id.substring(1, id.length() - 1);
        FlowTableFieldDO field = flowTableFiledDAO.getOne(iid);
        String flowName = field.getFlowTable().getFlowDO().getName();
        if(flowManagerProfileService.checkFlowByName(flowName)) {
        	flowTableFiledDAO.deleteById(iid);
        }
    }

}
