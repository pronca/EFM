package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.entity.FmFlowExtstdConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowExtstdConfigDAO extends JpaRepository<FmFlowExtstdConfigDO, String> {


    FmFlowExtstdConfigDO findByFlussoAndNuminvio(String flusso,String numInvio);
}



