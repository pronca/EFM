package it.eng.care.domain.flow.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.DriverDAO;
import it.eng.care.domain.flow.core.entity.DriverDO;
import it.eng.care.domain.flow.core.service.DriverService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
	
    @Autowired
    private DriverDAO driverDAO;

    @Override
    public DriverDO createEntity(DriverDO dto) {
        return driverDAO.save(dto);
    }

    @Override
    public DriverDO updateEntity(DriverDO dto) {
        return driverDAO.save(dto);
    }

    @Override
    public Pair<List<DriverDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput) {
        return null;
    }

    public List<DriverDO> findAll() {
        try {
            return driverDAO.findAll();
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
        }
        return null;
    }

    @Override
    public DriverDO retrieveOne(String id) {
        return (id == null || id.isBlank())
                ? null
                : driverDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteEntity(String id) {
        driverDAO.deleteById(id);
    }

}
