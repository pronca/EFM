package it.eng.care.domain.flow.core.dao;

import it.eng.care.domain.flow.core.dao.querySearch.FieldTypeDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.platform.persistence.api.QueryByCriteria;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldTypeDAO extends JpaRepository<FieldTypeDO, String> {

    @QueryByCriteria(FieldTypeDAOQueryByBaseSearchInput.class)
    List<FieldTypeDO> cerca(BaseSearchInput poInput);

}
