package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowRegionUnionDAOQueryByBaseSearchInput {
    @Autowired
    private EntityManager entityManager;

    public CriteriaQuery<FlowRegionUnionDO> cerca(BaseSearchInput poInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowRegionUnionDO> criteriaQuery = builder.createQuery(FlowRegionUnionDO.class);
        Root<FlowRegionUnionDO> rootTabella = criteriaQuery.from(FlowRegionUnionDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String id = poInput.getValue("id");
        String flowLocal = poInput.getValue("flow");
        String flowRegion = poInput.getValue("flowRegion");

        if (!StringUtils.isEmpty(id)) {
            //Join<FlowConfigurationFilterDO, FlowDO> join = rootTabella.join("flow");
            Path<String> text = rootTabella.get("id");
            restrictions.add(builder.equal(text, id));
        }

        if (!StringUtils.isEmpty(flowLocal)) {
            Join<FlowRegionUnionDO, FlowDO> join = rootTabella.join("flowLocal");
            Path<String> text = join.get("id");
            restrictions.add(builder.equal(text, flowLocal));
        }
        if (!StringUtils.isEmpty(flowRegion)) {
            Join<FlowRegionUnionDO, FlowDO> join = rootTabella.join("flowRegion");
            Path<String> text = join.get("id");
            restrictions.add(builder.equal(text, flowRegion));
        }
        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }
}

