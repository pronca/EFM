package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowTableFieldDAOQueryByBaseSearchInput {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowDAOQueryByBaseSearchInput.class);

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    
    public CriteriaQuery<FlowTableFieldDO> cerca(BaseSearchInput poInput, Boolean userFlowProfile) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowTableFieldDO> criteriaQuery = builder.createQuery(FlowTableFieldDO.class);
        Root<FlowTableFieldDO> rootTabella = criteriaQuery.from(FlowTableFieldDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        
        //Filtri Ricerca
        String flowId = poInput.getValue("flowId");
        String type = poInput.getValue("type");
        String flowLike = poInput.getValue("name");
        String idTable = poInput.getValue("idTable");
        String versionId = poInput.getValue("versionId");
        String section = poInput.getValue("section");
    
        Boolean isReferenceDate = poInput.getValue("isReferenceDate");
        String status = poInput.getValue("status");

        if (!StringUtils.isEmpty(status)) {
            boolean stato = true;
            if ("disabilitato".equals(status)) {
                stato = false;
            }
            Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
            Join<FlowTableDO, FlowDO> flows = flowTables.join("flowDO");
            Path<String> text = flows.get("status");
            restrictions.add(builder.equal(text, stato));
        }

        if (!StringUtils.isEmpty(type)) {
            Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
            Join<FlowTableDO, FlowDO> flows = flowTables.join("flowDO");
            Path<String> text = flows.get("flowType");
            restrictions.add(builder.equal(text, type));
        }

        if (!StringUtils.isEmpty(idTable)) {
            Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
            Path<String> text = flowTables.get("id");
            restrictions.add(builder.equal(text, idTable));
        }

        if (!StringUtils.isEmpty(section)) {
            Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
            Path<Integer> num = flowTables.get("section");
            restrictions.add(builder.equal(num, Integer.valueOf(section)));
        }

        if (!StringUtils.isEmpty(flowId)) {
            Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
            Join<FlowTableDO, FlowDO> flows = flowTables.join("flowDO");
            Path<String> text = flows.get("id");
            restrictions.add(builder.equal(text, flowId));
            if (!StringUtils.isEmpty(versionId)) {
                Join<FlowTableDO, VersionDO> version = flowTables.join("versionDO");
                Path<String> versionId_ = version.get("id");
                restrictions.add(builder.equal(versionId_, versionId));
            }
            //search field date
            if (isReferenceDate != null)
                if (isReferenceDate) {
                    Path<String> isRefData = rootTabella.get("isReferenceDate");
                    restrictions.add(builder.equal(isRefData, isReferenceDate));
                }
        }

        if (!StringUtils.isEmpty(flowLike)) {
            Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
            Join<FlowTableDO, FlowDO> flows = flowTables.join("flowDO");
            Path<String> text = flows.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + flowLike.toUpperCase() + "%"));
            if (!StringUtils.isEmpty(versionId)) {
                Join<FlowTableDO, VersionDO> version = flowTables.join("versionDO");
                Path<String> versionId_ = version.get("id");
                restrictions.add(builder.equal(versionId_, versionId));
            }
            
            if(userFlowProfile) {
            	List<String> flowsProfile = flowManagerProfileService.getFlows();
            	restrictions.add(text.in(flowsProfile));
            }
            
        } else {
//        	if(userFlowProfile) {
//            	List<String> flowsProfile = flowManagerProfileService.getFlows();
//            	Join<FlowTableFieldDO, FlowTableDO> flowTables = rootTabella.join("flowTable");
//                Join<FlowTableDO, FlowDO> flows = flowTables.join("flowDO");
//                Path<String> text = flows.get("name");
//                restrictions.add(text.in(flowsProfile));
//            }        	
        }
        
        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
    }
    
    public CriteriaQuery<FlowTableFieldDO> cerca(BaseSearchInput poInput) {
    	return cerca(poInput, false);
    }
}
