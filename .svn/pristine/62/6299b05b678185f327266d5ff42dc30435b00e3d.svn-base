package it.eng.care.domain.flow.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.FlowConfigurationFilterFieldDAO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.service.FlowConfigurationFilterFieldService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlowConfigurationFilterFieldServiceImpl implements FlowConfigurationFilterFieldService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowConfigurationFilterFieldServiceImpl.class);

    @Autowired
    public FlowConfigurationFilterFieldDAO flowConfigurationFilterFieldDAO;

    @Override
    public FlowConfigurationFilterFieldDO createEntity(FlowConfigurationFilterFieldDTO dto) {
        FlowConfigurationFilterFieldDO flowConfigurationFilterFieldDO;
        if (dto.getId() != null) {
            flowConfigurationFilterFieldDO = (dto.getId()== null || dto.getId().isBlank()) ? null : flowConfigurationFilterFieldDAO.findById(dto.getId()).orElse(null);
        } else
            flowConfigurationFilterFieldDO = new FlowConfigurationFilterFieldDO();
        flowConfigurationFilterFieldDO.setName(dto.getName());
        flowConfigurationFilterFieldDO.setFilterField(dto.getFilterField());
        flowConfigurationFilterFieldDO.setFilterType(dto.getFilterType());
        flowConfigurationFilterFieldDO.setFilterTable(dto.getFilterTable());
        flowConfigurationFilterFieldDO.setMandatory(dto.isMandatory());
        flowConfigurationFilterFieldDO.setPosition(dto.getPosition());
        flowConfigurationFilterFieldDO.setRange(dto.isRange());
        flowConfigurationFilterFieldDAO.save(flowConfigurationFilterFieldDO);


        return flowConfigurationFilterFieldDO;
    }

    @Override
    public FlowConfigurationFilterFieldDO updateEntity(FlowConfigurationFilterFieldDTO dto) {
        return null;
    }

    @Override
    public Pair<List<FlowConfigurationFilterFieldDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
        List<FlowConfigurationFilterFieldDO> loList = flowConfigurationFilterFieldDAO.cerca(searchInput);
        return Pair.of(loList, SearchInfos.create());
    }

    public List<FlowConfigurationFilterFieldDO> findAll() {
        try {
            return flowConfigurationFilterFieldDAO.findAll();
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
        }
        return null;
    }

    public FlowConfigurationFilterFieldDO retrieveOne(String id) {
        return (id == null || id.isBlank())
                ? null
                : flowConfigurationFilterFieldDAO.findById(id).orElse(null);
    }

    public void deleteEntity(FlowConfigurationFilterFieldDO flowConfigurationFilterFieldDO) {
        flowConfigurationFilterFieldDAO.delete(flowConfigurationFilterFieldDO);
    }
}
