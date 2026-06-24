package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowDAOQueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;
    
    private CriteriaQuery<FlowDO> cercaInProfile(BaseSearchInput poInput, List<String> flows) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowDO> criteriaQuery = builder.createQuery(FlowDO.class);
        Root<FlowDO> rootTabella = criteriaQuery.from(FlowDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        
        String id = poInput.getValue("id");
        String code = poInput.getValue("code");
        String codeNoLike = poInput.getValue("codeNoLike");
        String nameNoLike = poInput.getValue("nameNoLike");
        String name = poInput.getValue("name");
        Boolean status = poInput.getValue("status");
        String flowType = poInput.getValue("flowType");
        List<String> flowNameList = poInput.getValue("flowNameList");

        if (!StringUtils.isEmpty(id)) {
            Path<String> text = rootTabella.get("id");
            restrictions.add(builder.equal(text, id));
        }

        if (!StringUtils.isEmpty(name)) {
            Path<String> text = rootTabella.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + name.toUpperCase() + "%"));
        }

        if (!StringUtils.isEmpty(nameNoLike)) {
            Path<String> text = rootTabella.get("name");
            restrictions.add(builder.equal(text, nameNoLike));
        }

        if (!StringUtils.isEmpty(codeNoLike)) {
            Path<String> text = rootTabella.get("code");
            restrictions.add(builder.equal(text, codeNoLike));
        }

        if (!StringUtils.isEmpty(code)) {
            Path<String> text = rootTabella.get("code");
            restrictions.add(builder.like(builder.upper(text), code.toUpperCase()));
        }
        
        if (status != null) {
            Path<String> text = rootTabella.get("status");
            restrictions.add(builder.equal(text, status));
        }
        
        
        if (flowType != null) {
            Path<String> text = rootTabella.get("flowType");
            restrictions.add(builder.equal(text, flowType));
        }
        
        if(flowNameList != null && !flowNameList.isEmpty()) {
        	Path<String> text = rootTabella.get("name");
            restrictions.add(text.in(flowNameList));
        }
        
        if(flows != null) {
        	Path<String> text = rootTabella.get("name");
            restrictions.add(text.in(flows));
        }
        
        
        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
    }

    public CriteriaQuery<FlowDO> cerca(BaseSearchInput poInput) {
        return cercaInProfile(poInput, null);
    }
    
    public CriteriaQuery<FlowDO> cercaInProfile(BaseSearchInput poInput) {
        return cercaInProfile(poInput, flowManagerProfileService.getFlows());
    }
    
    public  CriteriaQuery<FlowDO> getAllWithVersions(String flowType) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowDO> criteriaQuery = builder.createQuery(FlowDO.class);
        Root<FlowDO> rootTabella = criteriaQuery.from(FlowDO.class);
        Fetch<FlowDO, FlowVersionDO> joinVersions = rootTabella.fetch("versions");
        joinVersions.fetch("flow");
        joinVersions.fetch("version");
        
        Predicate restriction = builder.equal(rootTabella.get("flowType"), flowType);
        
        return criteriaQuery
        		.select(rootTabella)
        		.where(restriction);
    }
    
    public  CriteriaQuery<FlowDO> getFlowWithVersions(String id) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowDO> criteriaQuery = builder.createQuery(FlowDO.class);
        Root<FlowDO> rootTabella = criteriaQuery.from(FlowDO.class);
        Fetch<FlowDO, FlowVersionDO> joinVersions = rootTabella.fetch("versions");
        joinVersions.fetch("flow");
        joinVersions.fetch("version");
        
        Predicate restriction = builder.equal(rootTabella.get("id"), id);
        
        return criteriaQuery
        		.select(rootTabella)
        		.where(restriction);
    }

}