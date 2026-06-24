package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.entity.DashboardPraticheDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardPraticheDAO extends JpaRepository<DashboardPraticheDO, String> {

    List<DashboardPraticheDO> findAllByFlowName(String flowName);
    
    List<DashboardPraticheDO> findAllByFlowNameAndCodiceAziendaIsNull(String flowName);
    
    List<DashboardPraticheDO> findAllByFlowNameAndCodiceAziendaIn(String flowName, List<String> aziende);
    
    Optional<DashboardPraticheDO> findFirstByFlowNameAndCodiceAzienda(String flowName, String codiceAzienda);

    boolean existsByFlowNameAndCodiceAzienda(String flowName, String codiceAzienda);
    
}
