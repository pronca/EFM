package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class FieldTypeDAOQueryByBaseSearchInput {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowDAOQueryByBaseSearchInput.class);

    @Autowired
    private EntityManager entityManager;


    public CriteriaQuery<FieldTypeDO> cerca(BaseSearchInput poInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FieldTypeDO> criteriaQuery = builder.createQuery(FieldTypeDO.class);
        Root<FieldTypeDO> rootTabella = criteriaQuery.from(FieldTypeDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String lsTabellaDTOtlbIdLike = poInput.getValue("FlowDTOtlbIdLike");

        LOGGER.info("FlowDTOtlbIdLike={}", lsTabellaDTOtlbIdLike);

        if (!StringUtils.isEmpty(lsTabellaDTOtlbIdLike)) {
            Path<String> text = rootTabella.get("id");
            restrictions.add(builder.like(builder.upper(text), "%" + lsTabellaDTOtlbIdLike.toUpperCase() + "%"));
        }


        String lsTabellaDTOtlbDescLike = poInput.getValue("TabellaDTOtlbDescLike");

        if (!StringUtils.isEmpty(lsTabellaDTOtlbDescLike)) {
            Path<String> text = rootTabella.get("description");
            restrictions.add(builder.like(builder.upper(text), "%" + lsTabellaDTOtlbDescLike.toUpperCase() + "%"));
        }

        LOGGER.info("FlowDTOtlbDescLike={}", lsTabellaDTOtlbDescLike);

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }


}
