package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class VersionDAOueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;


    public CriteriaQuery<VersionDO> cerca(BaseSearchInput poInput) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VersionDO> criteriaQuery = builder.createQuery(VersionDO.class);
        Root<VersionDO> rootTabella = criteriaQuery.from(VersionDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String versionLike = poInput.getValue("versionLike");
        String flowLike = poInput.getValue("flowLike");
        String idFlusso = poInput.getValue("flusso");

        if (!StringUtils.isEmpty(flowLike)) {
            Join<VersionDO, FlowVersionDO> joinFlowVersion = rootTabella.join("flows");
            Join<FlowVersionDO, FlowDO> joinFlow = joinFlowVersion.join("flow");
            Path<String> text = joinFlow.get("name"); // oppure "id" se cerchi per id
            restrictions.add(builder.like(builder.upper(text), "%" + flowLike.toUpperCase() + "%"));
        }

        if (!StringUtils.isEmpty(idFlusso)) {
            Join<VersionDO, FlowVersionDO> joinFlowVersion = rootTabella.join("flows");
            Join<FlowVersionDO, FlowDO> joinFlow = joinFlowVersion.join("flow");
            restrictions.add(builder.equal(joinFlow.get("id"), idFlusso));
        }

        if (!StringUtils.isEmpty(versionLike)) {
            Path<String> text = rootTabella.get("version");
            restrictions.add(builder.like(builder.upper(text), "%" + versionLike.toUpperCase() + "%"));
        }


        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }

}
