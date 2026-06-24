package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.eng.care.domain.flow.core.entity.JobTalendDipendencyDO;

public interface JobTalendDipendencyDAO extends JpaRepository<JobTalendDipendencyDO, String> {

    @Query("""
        select d
        from JobTalendDipendencyDO d
        where d.jobTalendFile.id = :fileId
          and d.name = :name
          and d.dependencyType = :type
    """)
    List<JobTalendDipendencyDO> findByFileIdAndNameAndType(
            @Param("fileId") String fileId,
            @Param("name") String name,
            @Param("type") String type
    );
    
    @Query("""
            select d
            from JobTalendDipendencyDO d
            where d.jobTalendFile.id = :fileId
        """)
        List<JobTalendDipendencyDO> findByJobTalendFileId(@Param("fileId") String fileId);
}

