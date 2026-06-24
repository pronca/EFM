package it.eng.care.domain.flow.webservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.webservice.model.CodiciRegioneDO;


@Repository
public interface CodiciRegioneDAO extends JpaRepository<CodiciRegioneDO, String>{
	
	CodiciRegioneDO findByChiave(String chiave);

}


