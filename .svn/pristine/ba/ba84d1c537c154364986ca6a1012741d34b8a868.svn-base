package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class FlowTableDAOQueryByBaseSearchInput {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowDAOQueryByBaseSearchInput.class);

    @Autowired
    private EntityManager entityManager;

    public CriteriaQuery<FlowTableDO> cerca(BaseSearchInput poInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowTableDO> criteriaQuery = builder.createQuery(FlowTableDO.class);
        Root<FlowTableDO> rootTabella = criteriaQuery.from(FlowTableDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        // Filtri Ricerca
        String flowLike = poInput.getValue("flowNameLike");
        String flowId = poInput.getValue("flowId");
        
        if (!StringUtils.isEmpty(flowLike)) {
            Join<FlowTableDO, FlowDO> flows = rootTabella.join("flowDO");
            Path<String> text = flows.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + flowLike.toUpperCase() + "%"));
        }
        
        if (!StringUtils.isEmpty(flowId)) {
            Join<FlowTableDO, FlowDO> flows = rootTabella.join("flowDO");
            Path<String> text = flows.get("id");
            restrictions.add(builder.equal(text, flowId));
        }

        String flowEqual = poInput.getValue("flowNameEqual");
        if (!StringUtils.isEmpty(flowEqual)) {
            Join<FlowTableDO, FlowDO> flows = rootTabella.join("flowDO");
            Path<String> text = flows.get("name");
            restrictions.add(builder.equal(text, flowEqual));
        }

        String versionNameEqual = poInput.getValue("versionNameEqual");
        if (!StringUtils.isEmpty(flowEqual)) {
            Join<FlowTableDO, VersionDO> version = rootTabella.join("versionDO");
            Path<String> text = version.get("version");
            restrictions.add(builder.equal(text, versionNameEqual));
        }

        return criteriaQuery.select(rootTabella).where(restrictions.toArray(new Predicate[restrictions.size()]));
    }

}
