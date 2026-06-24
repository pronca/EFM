package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class DataSourceDAOueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;


    public CriteriaQuery<DataSourceDO> cerca(BaseSearchInput poInput) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DataSourceDO> criteriaQuery = builder.createQuery(DataSourceDO.class);
        Root<DataSourceDO> rootTabella = criteriaQuery.from(DataSourceDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String dataSourceNameLike = poInput.getValue("dataSourceNameLike");

        if (!StringUtils.isEmpty(dataSourceNameLike)) {
            Path<String> text = rootTabella.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + dataSourceNameLike.toUpperCase() + "%"));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }


}
