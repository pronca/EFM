package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.dao.FlowConfigurationFilterDAO;
import it.eng.care.domain.flow.core.dto.FlowExportRequestDTO;
import it.eng.care.domain.flow.core.entity.*;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import java.util.List;
import java.util.Set;

public class FlowConfigurationFilterDAOQueryByBaseSearchInput {
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    FlowManagerProfileService flowManagerProfileService;

    @Autowired
    FlowService flowService;

    public CriteriaQuery<FlowConfigurationFilterDO> cerca(BaseSearchInput poInput, Boolean useFlowProfile) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowConfigurationFilterDO> criteriaQuery = builder.createQuery(FlowConfigurationFilterDO.class);
        Root<FlowConfigurationFilterDO> rootTabella = criteriaQuery.from(FlowConfigurationFilterDO.class);
        //Root<FlowExportRequestDO> rootMany = criteriaQuery.from(FlowExportRequestDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String name = poInput.getValue("nameFlowConfFilter");
        //FIX PROBLEMA COMBO ENGAPP
        Integer type = null;
        if (poInput.getValue("type") instanceof java.util.LinkedHashMap) {
            type = 0;
        } else {
            type = poInput.getValue("type");
        }
        String flow = poInput.getValue("flow");
        String flowName = poInput.getValue("flowName");
        String version = poInput.getValue("version");

        if (!StringUtils.isEmpty(flowName)) {
            //Eliminare servizio con SUBQUERY o JOIN
            BaseSearchInput baseSearchInput = new BaseSearchInput();
            baseSearchInput.setValue("name",flowName);
            Pair<List<FlowDO>, SearchInfo> flowDO = flowService.retrieveAllFiltered(baseSearchInput, true);
            String flowId = flowDO.getFirst().get(0).getId();
            Path<String> text = rootTabella.get("flow");
            restrictions.add(builder.equal(text, flowId))
            ;
        }
        
        if(useFlowProfile) {
            Path<String> text = rootTabella.get("flow");
        	restrictions.add(text.in(flowManagerProfileService.getFlowIds()));
        }

        if (!StringUtils.isEmpty(name)) {
            Path<String> text = rootTabella.get("name");
            restrictions.add(builder.like(builder.upper(text), "%" + name.toUpperCase() + "%"));
        }
        if (!StringUtils.isEmpty(flow)) {
            Path<String> text = rootTabella.get("flow");
            restrictions.add(builder.equal(text, flow));
        }
        if (!StringUtils.isEmpty(version)) {
            Path<String> text = rootTabella.get("version");
            restrictions.add(builder.equal(text, version));
        }

        if (type != null) {
            Path<String> inp = rootTabella.get("type");
            restrictions.add(builder.equal(inp, type));
        }
        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }
    
    public CriteriaQuery<FlowConfigurationFilterDO> cerca(BaseSearchInput poInput) {
        return cerca(poInput, false);
    }

}

