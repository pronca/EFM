package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class FlowConfFilterFieldValueDAOQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowConfFilterFieldValueDAOQuery.class);

    @Autowired
    private EntityManager entityManager;

    public void deleteBy(BaseSearchInput searchInput) {

//        //todo TRASFORMARI IN CRITIRIADELETE (fix)
//        String sqlDelete = "DELETE " +
//                "FROM FM_FLOW_CFG_FIELD_VALUE " +
//                "WHERE ID_EXPORT_REQ = '" + searchInput.getValue("extractionID").toString() + "'";
//
//        Query query = entityManager.createNativeQuery(sqlDelete);
//        query.executeUpdate();

        //errore: java.lang.ClassCastException: org.hibernate.query.criteria.internal.CriteriaDeleteImpl cannot be cast to jakarta.persistence.criteria.CriteriaQuery
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        //Criteria Delete
        CriteriaDelete<FlowConfigurationFilterFieldValueDO> criteriaDelete = builder.createCriteriaDelete(FlowConfigurationFilterFieldValueDO.class);
        Root<FlowConfigurationFilterFieldValueDO> root = criteriaDelete.from(FlowConfigurationFilterFieldValueDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        //Filtri
        String extractionID = searchInput.getValue("extractionID");

        Subquery<FlowConfigurationFilterFieldValueDO> subquery = criteriaDelete.subquery(FlowConfigurationFilterFieldValueDO.class);
        Root<FlowConfigurationFilterFieldValueDO> root2 = subquery.from(FlowConfigurationFilterFieldValueDO.class);
        subquery.select(root2);
        /* below are narrowing criteria, based on root2*/
        Join<FlowConfigurationFilterFieldValueDO, FlowExportRequestDO> join = root2.join("flowExportRequest");
        subquery.where(builder.equal(join.get("id"), extractionID));

        criteriaDelete.where(root.in(subquery));
        Query query = entityManager.createQuery(criteriaDelete);
        query.executeUpdate();
//        return true;
//
//        return criteriaDelete
//                .where(root.in(subquery));
    }


    public CriteriaQuery<FlowConfigurationFilterFieldValueDO> cerca(BaseSearchInput searchInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowConfigurationFilterFieldValueDO> criteriaQuery = builder.createQuery(FlowConfigurationFilterFieldValueDO.class);
        Root<FlowConfigurationFilterFieldValueDO> root = criteriaQuery.from(FlowConfigurationFilterFieldValueDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String extractionID = searchInput.getValue("extractionID");

        if (!StringUtils.isEmpty(extractionID)) {
            Join<FlowConfigurationFilterDO, FlowConfigurationFilterFieldDO> join = root.join("flowExportRequest");
            Path<String> text = join.get("id");
            restrictions.add(builder.equal(text, extractionID));
        }

        return criteriaQuery
                .select(root)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
    }

}
