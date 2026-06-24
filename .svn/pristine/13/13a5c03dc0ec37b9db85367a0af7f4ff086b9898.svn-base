package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.VersionDAOueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VersionDAO extends JpaRepository<VersionDO, String> {

    @QueryByCriteria(VersionDAOueryByBaseSearchInput.class)
    List<VersionDO> cerca(BaseSearchInput poInput);
    
    @Query("""
    		select fv.version.id
    		from FlowVersionDO fv
    		where fv.flow.id = :flowId
    		order by fv.version.creationDate desc
    		""")
    		List<String> findVersionIdsOrderByCreationDateDesc(@Param("flowId") String flowId);


}
