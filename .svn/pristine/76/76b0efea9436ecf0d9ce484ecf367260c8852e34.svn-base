package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class FlowForeignKeyDAOQueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;

    public CriteriaQuery<FlowForeignKeyDO> cerca(BaseSearchInput poInput) {

        //DELETE

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowForeignKeyDO> criteriaQuery = builder.createQuery(FlowForeignKeyDO.class);
        Root<FlowForeignKeyDO> rootTabella = criteriaQuery.from(FlowForeignKeyDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String fieldTable = poInput.getValue("idFieldTable");

        if (!StringUtils.isEmpty(fieldTable)) {
            Path<String> text = rootTabella.get("idFieldTable");
            restrictions.add(builder.equal(text, fieldTable));
        }


        String fieldTableref = poInput.getValue("idTableReferenced");
        if (!StringUtils.isEmpty(fieldTableref)) {
            Path<String> textref = rootTabella.get("idTableReferenced").get("id");
            restrictions.add(builder.equal(textref, fieldTableref));
        }
        
        String table = poInput.getValue("idTable");
        if (!StringUtils.isEmpty(table)) {
            Path<String> textref = rootTabella.get("idTable").get("id");
            restrictions.add(builder.equal(textref, table));
        }


        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
    }

}
