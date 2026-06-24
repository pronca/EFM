package it.eng.care.domain.flow.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.FlowVersionDAO;
import it.eng.care.domain.flow.core.dao.VersionDAO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.service.VersionService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VersionServiceImpl implements VersionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VersionServiceImpl.class);
	
    @Autowired
    private VersionDAO versionDAO;
    
    @Autowired
    private FlowVersionDAO flowVersionDAO;

    @Override
    public VersionDO createEntity(VersionDO dto) {
        return versionDAO.save(dto);
    }

    @Override
    public VersionDO updateEntity(VersionDO dto) {
        return versionDAO.save(dto);
    }

    @Override
    public Pair<List<VersionDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
        List<VersionDO> loList = versionDAO.cerca(searchInput);
        return Pair.of(loList, SearchInfos.create());
    }

    public List<VersionDO> findAll() {
        try {
            return versionDAO.findAll();
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
        }
        return null;
    }

    @Override
    public VersionDO retrieveOne(String id) {
        return (id == null || id.isBlank())
                ? null
                : versionDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteEntity(String id) {
        versionDAO.deleteById(id);
    }
    
    @Override
    public FlowVersionDO findByFlow(FlowDO flow) {
    	return flowVersionDAO.findByFlow(flow);
    }

}
