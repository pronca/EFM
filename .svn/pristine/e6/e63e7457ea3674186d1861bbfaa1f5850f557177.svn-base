package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Set;

public class JobTalendDAOQueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    public CriteriaQuery<JobTalendDO> cerca(BaseSearchInput poInput, Boolean useFlowProfile) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobTalendDO> criteriaQuery = builder.createQuery(JobTalendDO.class);
        Root<JobTalendDO> rootTabella = criteriaQuery.from(JobTalendDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String flowid = poInput.getValue("flowId");
        String flow = poInput.getValue("flow");
        String version = poInput.getValue("version");
        String name = poInput.getValue("name");
        String description = poInput.getValue("description");
        String type = poInput.getValue("type");
        String className = poInput.getValue("className");
        String id = poInput.getValue("id");

        if (!StringUtils.isEmpty(flowid)) {
            Join<FlowDO, JobTalendDO> flowJoin = rootTabella.join("flow");
            Path<String> text = flowJoin.get("id");
            restrictions.add(builder.equal(text, flowid));
        }

        if (!StringUtils.isEmpty(flow)) {
            Join<FlowDO, JobTalendDO> flowJoin = rootTabella.join("flow");
            Path<String> text = flowJoin.get("code");
            restrictions.add(builder.equal(text, flow));
            
            if(useFlowProfile) {
            	restrictions.add(text.in(flowManagerProfileService.getFlows()));
            }
            
        } else {
        	if(useFlowProfile) {
        		Join<FlowDO, JobTalendDO> flowJoin = rootTabella.join("flow");
                Path<String> text = flowJoin.get("code");
            	restrictions.add(text.in(flowManagerProfileService.getFlows()));
            }
        }

        if (!StringUtils.isEmpty(version)) {
            Join<FlowDO, JobTalendDO> versionJoin = rootTabella.join("version");
            Path<String> text = versionJoin.get("id");
            restrictions.add(builder.equal(text, version));
        }

        if (!StringUtils.isEmpty(name)) {
            Path<String> text = rootTabella.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + name.toUpperCase() + "%"));
        }

        if (!StringUtils.isEmpty(id)) {
            Path<String> text = rootTabella.get("id");
            restrictions.add(builder.equal(text, id));
        }

        if (!StringUtils.isEmpty(type)) {
            Path<String> text = rootTabella.get("type");
            restrictions.add(builder.equal(text, poInput.getValue("type")));
        }

        if (!StringUtils.isEmpty(description)) {
            Path<String> text = rootTabella.get("description");
            restrictions.add(builder.like(builder.upper(text), "%" + description.toUpperCase() + "%"));
        }

        if (!StringUtils.isEmpty(className)) {
            Path<String> text = rootTabella.get("className");
            restrictions.add(builder.equal(text, poInput.getValue("className")));
        }

        restrictions.add(builder.equal(rootTabella.get("deleted"), false));

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
    }
    
    public CriteriaQuery<JobTalendDO> cerca(BaseSearchInput poInput) {
        return cerca(poInput, false);
    }

}
