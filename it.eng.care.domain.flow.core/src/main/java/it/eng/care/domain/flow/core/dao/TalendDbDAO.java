package it.eng.care.domain.flow.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.TalendDbDO;

@Repository
public interface TalendDbDAO extends JpaRepository<TalendDbDO, String> {
}
