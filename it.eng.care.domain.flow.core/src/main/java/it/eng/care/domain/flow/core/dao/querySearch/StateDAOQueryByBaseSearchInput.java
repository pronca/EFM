package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import java.util.List;
import java.util.Set;

public class StateDAOQueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;

    public CriteriaQuery<StateDO> cerca(BaseSearchInput poInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StateDO> criteriaQuery = builder.createQuery(StateDO.class);
        Root<StateDO> rootTabella = criteriaQuery.from(StateDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String machineId = poInput.getValue("machineId");

        if (!StringUtils.isEmpty(machineId)) {
            Path<String> text = rootTabella.get("machineId");
            restrictions.add(builder.equal(text, machineId));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));

    }

    public String searchState(String id) {

        Query query = null;
        query = entityManager.createNativeQuery("Select state from state where id = '" + id + "'");

        List<String> result = query.getResultList();

        return result.get(0);

    }

}
