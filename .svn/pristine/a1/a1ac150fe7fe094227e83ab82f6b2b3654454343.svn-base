package it.eng.care.domain.flow.core.dao.querySearch;

import com.google.common.collect.Sets;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.enumeration.MachineState;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class FlowImportRequestDAOQueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    public CriteriaQuery<FlowImportRequestDO> cerca(BaseSearchInput poInput, Boolean useFlowProfile) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowImportRequestDO> criteriaQuery = builder.createQuery(FlowImportRequestDO.class);
        Root<FlowImportRequestDO> rootTabella = criteriaQuery.from(FlowImportRequestDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        String id = poInput.getValue("id");
        String flow = poInput.getValue("flow");
        String status = poInput.getValue("status");
        String version = poInput.getValue("version");
        Date requestDateTo = poInput.getValue("requestDateTo");
        Date requestDateFrom = poInput.getValue("requestDateFrom");
        Date endExtractionDate = poInput.getValue("endExtractionDate");
        List<String> aziende = (List<String>) poInput.getValue("aziende");
        
        Join<FlowImportRequestDO, FlowDO> flows = rootTabella.join("flow");
        Join<FlowImportRequestDO, VersionDO> versions = rootTabella.join("version");
        
        if(aziende != null && !aziende.isEmpty()){
        	Path<String> text = rootTabella.get("aziendeProfiloFlussi");
        	Predicate predicate1 = builder.or(builder.isNull(text));
        	Predicate predicateOr = null;
        	Predicate predicateOrTot = null;
        	if (aziende.size()==1) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"));
        	} else if (aziende.size()==2) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"));
        	} else if (aziende.size()==3) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"));
        	} else if (aziende.size()==4) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"));
        	} else if (aziende.size()==5) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"));
        	} else if (aziende.size()==6) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"));
        	} else if (aziende.size()==7) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"));
        	} else if (aziende.size()==8) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(7).toUpperCase() + "%"));
        	} else if (aziende.size()==9) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(7).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(8).toUpperCase() + "%"));
        	} else if (aziende.size()==10) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(7).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(8).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(9).toUpperCase() + "%"));
        	}
        	predicateOrTot = builder.or(predicate1, predicateOr);
        	restrictions.add(predicateOrTot);
        }

        if (!StringUtils.isEmpty(status)) {
            restrictions.add(builder.equal(rootTabella.get("status"), status));
        } else {
        	restrictions.add(builder.notEqual(rootTabella.get("status"), "CANCELLATA"));
        }

        if (requestDateFrom != null) {
            restrictions.add(builder.greaterThanOrEqualTo(rootTabella.get("requestDate"), requestDateFrom));
        }
        
        if (requestDateTo != null) {
            restrictions.add(builder.lessThanOrEqualTo(rootTabella.get("requestDate"), requestDateTo));
        }
        
        if (endExtractionDate != null) {
            restrictions.add(builder.equal(rootTabella.get("endExtractionDate"), endExtractionDate));
        }
        if (!StringUtils.isEmpty(id)) {
            restrictions.add(builder.equal(rootTabella.get("id"), id));
        }

        if (!StringUtils.isEmpty(flow)) {
            restrictions.add(builder.equal(flows.get("name"), flow));
        }
       	
        if(useFlowProfile) {
        	restrictions.add(flows.get("name").in(flowManagerProfileService.getFlows()));
       	}
       	
        if (!StringUtils.isEmpty(version)) {
            restrictions.add(builder.equal(versions.get("id"), version));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }
    
    public CriteriaQuery<FlowImportRequestDO> cerca(BaseSearchInput poInput) {
        return cerca(poInput, false);
    }


    public CriteriaQuery<FlowImportRequestDO> getRequestToValidate() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowImportRequestDO> criteriaQuery = builder.createQuery(FlowImportRequestDO.class);
        Root<FlowImportRequestDO> rootTabella = criteriaQuery.from(FlowImportRequestDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
        
        restrictions.add(builder.equal(rootTabella.get("status"), MachineState.TERMINATA_OK.name()));
        restrictions.add(builder.isNull(rootTabella.get("validationStatus")));
        
        if(aziende != null && !aziende.isEmpty()){
        	Path<String> text = rootTabella.get("aziendeProfiloFlussi");
        	Predicate predicate1 = builder.or(builder.isNull(text));
        	Predicate predicateOr = null;
        	Predicate predicateOrTot = null;
        	if (aziende.size()==1) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"));
        	} else if (aziende.size()==2) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"));
        	} else if (aziende.size()==3) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"));
        	} else if (aziende.size()==4) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"));
        	} else if (aziende.size()==5) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"));
        	} else if (aziende.size()==6) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"));
        	} else if (aziende.size()==7) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"));
        	} else if (aziende.size()==8) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(7).toUpperCase() + "%"));
        	} else if (aziende.size()==9) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(7).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(8).toUpperCase() + "%"));
        	} else if (aziende.size()==10) {
        		predicateOr = builder.or(builder.like(builder.upper(text), "%" + aziende.get(0).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(1).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(2).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(3).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(4).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(5).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(6).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(7).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(8).toUpperCase() + "%"), builder.like(builder.upper(text), "%" + aziende.get(9).toUpperCase() + "%"));
        	}
        	predicateOrTot = builder.or(predicate1, predicateOr);
        	restrictions.add(predicateOrTot);
        }
        
        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));

    }

}
