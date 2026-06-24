package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;

@Repository
public interface JobTalendFileDAO extends JpaRepository<JobTalendFileDO, String> {

	List<JobTalendFileDO> findByJobTalend(JobTalendDO jobTalend);
}
