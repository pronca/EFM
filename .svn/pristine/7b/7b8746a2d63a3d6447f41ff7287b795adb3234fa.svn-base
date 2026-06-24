package it.eng.care.domain.flow.core.service.impl;

import it.eng.care.domain.flow.core.dao.AppIdentityDAO;
import it.eng.care.domain.flow.core.entity.AppIdentityDO;
import it.eng.care.domain.flow.core.service.AppIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppIdentityServiceImpl implements AppIdentityService {

    @Autowired
    AppIdentityDAO appIdentityDAO;

    @Override
    public AppIdentityDO searchApp(String flowName) {

        return appIdentityDAO.findByFlowName(flowName);
    }

    @Override
    public AppIdentityDO searchApp(String flowName,String codiceAzienda) {

        return appIdentityDAO.findByFlowNameAndCodiceAzienda(flowName,codiceAzienda);
    }
}
