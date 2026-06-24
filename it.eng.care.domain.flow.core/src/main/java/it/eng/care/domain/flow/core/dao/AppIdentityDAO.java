package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.entity.AppIdentityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppIdentityDAO extends JpaRepository<AppIdentityDO, String> {

    AppIdentityDO findByFlowName(String flowName);

	AppIdentityDO findByFlowNameAndCodiceAzienda(String flowName, String codiceAzienda);

}
