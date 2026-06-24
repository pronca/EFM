package it.eng.care.domain.flow.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.dao.querySearch.FlowForeignKeyDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Repository
public interface FlowForeignKeyDAO extends JpaRepository<FlowForeignKeyDO, String> {

    FlowForeignKeyDO findByIdTableReferencedId(String id);

    @QueryByCriteria(FlowForeignKeyDAOQueryByBaseSearchInput.class)
    List<FlowForeignKeyDO> cerca(BaseSearchInput poInput);
    
    @Modifying
    @Query(value = "delete from FM_FLOW_FOREIGNKEY where id = ?1",nativeQuery = true)
    void deleteById( String id);
    
    @Modifying
    @Query(value = "delete from FM_FLOW_FOREIGNKEY where id_Table in (select id from FM_FLOW_TABLE where flow = :id)",nativeQuery = true)
    void deleteByFlow(@Param("id") String flowId);

}
