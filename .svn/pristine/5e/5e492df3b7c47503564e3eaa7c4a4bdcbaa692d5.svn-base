package it.eng.care.domain.flow.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.FieldTypeDAO;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.domain.flow.core.service.FieldTypeService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FieldTypeServiceImpl implements FieldTypeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FieldTypeServiceImpl.class);
	
    @Autowired
    private FieldTypeDAO fieldTypeDAO;

    public FieldTypeDO createEntity(FieldTypeDO poValue) {
        return fieldTypeDAO.save(poValue);
    }

    public FieldTypeDO updateEntity(FieldTypeDO poValue) {
        return fieldTypeDAO.save(poValue);
    }

    public Pair<List<FieldTypeDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
        List<FieldTypeDO> loList = fieldTypeDAO.cerca(searchInput);
        return Pair.of(loList, SearchInfos.create());
    }

    public List<FieldTypeDO> findAll() {
        try {
            return fieldTypeDAO.findAll();
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
        }
        return null;
    }

    public FieldTypeDO retrieveOne(String id) {
        return (id == null || id.isBlank())
                ? null
                : fieldTypeDAO.findById(id).orElse(null);
    }

    public void deleteEntity(String id) {
        fieldTypeDAO.deleteById(id);
    }

}
