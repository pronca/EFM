package it.eng.care.domain.flow.core.service.impl;

import it.eng.care.domain.flow.core.dao.FlowConfigurationFilterFieldValueDAO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.domain.flow.core.service.FlowConfigurationFilterFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlowConfigurationFilterFieldValueServiceImpl implements FlowConfigurationFilterFieldValueService {


    @Autowired
    public FlowConfigurationFilterFieldValueDAO flowConfigurationFilterFieldValueDAO;


    @Override
    public FlowConfigurationFilterFieldValueDO createEntity(FlowConfigurationFilterFieldValueDO dto) {
        return flowConfigurationFilterFieldValueDAO.save(dto);
    }

    @Override
    public FlowConfigurationFilterFieldValueDO updateEntity(FlowConfigurationFilterFieldValueDO dto) {
        return flowConfigurationFilterFieldValueDAO.save(dto);
    }


}
