package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.dao.querySearch.AnagraficaAssistDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.dao.querySearch.AnagraficaAssistitoDAOQueryByCriteria;
import it.eng.care.domain.flow.core.dao.querySearch.AnagraficaAssistitoQueryPaginated;
import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface AnagraficaAssistitoDAO extends JpaRepository<AnagraficaAssistitoDO, String>{
	
	 @QueryByCriteria(AnagraficaAssistDAOQueryByBaseSearchInput.class)
	 List<AnagraficaAssistitoDO> cercaInProfile(BaseSearchInput poInput);
	 
	 @QueryByCriteria(AnagraficaAssistitoDAOQueryByCriteria.class)
	 List<AnagraficaAssistitoDO> searchAnagrafica(BaseSearchInput poInput);
	 
	 Page<AnagraficaAssistitoDO> findAll(Pageable pageable);	 
	
	 @QueryByCriteria(AnagraficaAssistitoQueryPaginated.class)
	 List<AnagraficaAssistitoDO> findAllPaginated(BaseSearchInput poInput,Pageable pageable);
	 	 	 
}
