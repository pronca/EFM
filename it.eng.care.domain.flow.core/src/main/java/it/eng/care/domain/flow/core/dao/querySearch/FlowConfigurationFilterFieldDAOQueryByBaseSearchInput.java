package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class FlowConfigurationFilterFieldDAOQueryByBaseSearchInput {
    @Autowired
    private EntityManager entityManager;

    public CriteriaQuery<FlowConfigurationFilterFieldDO> cerca(BaseSearchInput poInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowConfigurationFilterFieldDO> criteriaQuery = builder.createQuery(FlowConfigurationFilterFieldDO.class);
        Root<FlowConfigurationFilterFieldDO> rootTabella = criteriaQuery.from(FlowConfigurationFilterFieldDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        //JOIN

        String idFlowConfigurationField = poInput.getValue("idFlowConfigurationField");

        if (!StringUtils.isEmpty(idFlowConfigurationField)) {
            Join<FlowConfigurationFilterDO, FlowConfigurationFilterFieldDO> join = rootTabella.join("configurationFilter");
            Path<String> text = join.get("id");
            restrictions.add(builder.equal(text, idFlowConfigurationField));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }
}

