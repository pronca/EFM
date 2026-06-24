package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.entity.AppIdentityDO;

public interface AppIdentityService {

    AppIdentityDO searchApp(String flowName);
    AppIdentityDO searchApp(String flowName, String codiceAzienda);
}
