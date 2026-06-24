package it.eng.care.domain.flow.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.LockedMessageDO;

@Repository
public interface LockedMessageDAO extends JpaRepository<LockedMessageDO, String> {
	
	@Query("""
	        select l
	        from LockedMessageDO l
	        where l.flowId.id = :flowId
	          and l.keyMessage = :keyMessage
	          and l.extrId = :extrId
	          and l.status = :status
	    """)
		List<LockedMessageDO> findPendingLockedMessage(
	            @Param("flowId") String flowId,
	            @Param("keyMessage") String keyMessage,
	            @Param("extrId") String extrId,
	            @Param("status") String status
	    );

    @Query("""
            select l
            from LockedMessageDO l
            where l.flowId.id = :flowId
              and l.extrId = :extrId
              and l.status = :status
            order by l.creationDate asc
        """)
        List<LockedMessageDO> findPendingLockedMessages(
                @Param("flowId") String flowId,
                @Param("extrId") String extrId,
                @Param("status") String status
        );
    
    @Modifying
    @Query("""
        update LockedMessageDO lm
           set lm.status = :processedStatus,
               lm.extractionId = :extractionId,
               lm.dateProcessed = :dateProcessed
         where lm.flowId.id = :flowId
           and lm.keyMessage = :keyMessage
           and lm.status = :pendingStatus
    """)
    int markMessagesProcessedByFlowAndKeyUpToCreationDate(
            @Param("flowId") String flowId,
            @Param("keyMessage") String keyMessage,
            @Param("pendingStatus") String pendingStatus,
            @Param("processedStatus") String processedStatus,
            @Param("extractionId") String extractionId,
            @Param("dateProcessed") Date dateProcessed,
            @Param("creationDate") Date creationDate
    );
}