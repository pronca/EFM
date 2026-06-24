package it.eng.care.domain.flow.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.care.domain.flow.core.entity.IntegrationServiceDO;

public interface IntegrationServiceDAO extends JpaRepository<IntegrationServiceDO, String> {
	
	IntegrationServiceDO findByFlussoAndNomeAndAbilitazione(String flowCode, String name, int abilitazione);
	
	IntegrationServiceDO findByNomeAndAbilitazione(String name, int abilitazione);
	
	IntegrationServiceDO findByFlussoAndNomeAndAbilitazioneAndCodiceAzienda(String flowCode, String name, int abilitazione, String codiceAzienda);

}
