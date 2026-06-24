package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.SearchPatientFieldDO;

@Repository
public interface SearchPatientFieldDAO extends JpaRepository<SearchPatientFieldDO, String> {

	List<SearchPatientFieldDO> findAllByFlusso(String flusso);
	
	List<SearchPatientFieldDO> findAllByFlussoAndFiltroRicerca(String flusso, String filtroRicerca);
	
	List<SearchPatientFieldDO> findAllByFlussoAndFiltroRicercaAndCodiceAziendaIn(String flusso, String filtroRicerca, List<String> aziende);
	
	List<SearchPatientFieldDO> findAllByFlussoAndFiltroRicercaAndCodiceAziendaIsNull(String flusso, String filtroRicerca);
	
	List<SearchPatientFieldDO> findAllByCodiceAziendaIn (List<String> aziende);
	
	List<SearchPatientFieldDO> findAllByCodiceAziendaIsNull();
}
