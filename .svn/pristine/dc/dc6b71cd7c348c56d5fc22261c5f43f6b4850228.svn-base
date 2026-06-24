package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.dao.FlowTableDAOCustom;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowTableDAOCustomImpl implements FlowTableDAOCustom{

	
    @Autowired
    private EntityManager entityManager;

	@Override
	public List<FlowTableDO> cerca(BaseSearchInput poInput) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowTableDO> criteriaQuery = builder.createQuery(FlowTableDO.class);
        Root<FlowTableDO> rootTabella = criteriaQuery.from(FlowTableDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        // Filtri Ricerca
        String flowLike = poInput.getValue("flowNameLike");

        if (!StringUtils.isEmpty(flowLike)) {
            Join<FlowTableDO, FlowDO> flows = rootTabella.join("flowDO");
            Path<String> text = flows.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + flowLike.toUpperCase() + "%"));
        }

        String flowEqual = poInput.getValue("flowNameEqual");
        if (!StringUtils.isEmpty(flowEqual)) {
            Join<FlowTableDO, FlowDO> flows = rootTabella.join("flowDO");
            Path<String> text = flows.get("name");
            restrictions.add(builder.equal(text, flowEqual));
        }

        String versionNameEqual = poInput.getValue("versionNameEqual");
        if (!StringUtils.isEmpty(versionNameEqual)) {
            Join<FlowTableDO, VersionDO> version = rootTabella.join("versionDO");
            Path<String> text = version.get("version");
            restrictions.add(builder.equal(text, versionNameEqual));
        }
        
        String sectionEqual = poInput.getValue("section");
        if (!StringUtils.isEmpty(sectionEqual)) {
        	Path<Integer> text = rootTabella.get("section");
        	restrictions.add(builder.equal(text, sectionEqual));
        }

        criteriaQuery.select(rootTabella).where(restrictions.toArray(new Predicate[restrictions.size()]));
        
        TypedQuery<FlowTableDO> t = entityManager.createQuery(criteriaQuery);
		List<FlowTableDO> resultList = t.getResultList();
		
		return resultList;
        
    }

		
		
		
		
	

}
