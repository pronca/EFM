package it.eng.care.domain.flow.core.service.impl;

import it.eng.care.domain.flow.core.dao.FlowRegionUnionDAO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlowRegionUnionServiceImpl implements FlowRegionUnionService {

    @Autowired
    private FlowRegionUnionDAO flowRegionUnionDAO;

    @Override
    public FlowRegionUnionDO createEntity(FlowRegionUnionDO dto) {
        return flowRegionUnionDAO.save(dto);
    }

    @Override
    public FlowRegionUnionDO updateEntity(FlowRegionUnionDO dto) {
        return null;
    }

    @Override
    public Pair<List<FlowRegionUnionDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
        List<FlowRegionUnionDO> loList = flowRegionUnionDAO.cerca(searchInput);
        return Pair.of(loList, SearchInfos.create());
    }

    @Override
    public FlowRegionUnionDO retrieveOne(String id) {
        BaseSearchInput input = new BaseSearchInput();
        input.setValue("flow", id);
        List<FlowRegionUnionDO> loList = flowRegionUnionDAO.cerca(input);
        return loList.get(0);

    }

    @Override
    public void deleteEntity(String id) {

    }

    @Override
    public List<FlowRegionUnionDO> findAll() {
        return null;
    }
}
