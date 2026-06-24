package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.enumeration.MachineState;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowExportRequestDAOQueryByBaseSearchInput {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    public CriteriaQuery<FlowExportRequestDO> cerca(BaseSearchInput poInput, Boolean useFlowProfile){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowExportRequestDO> criteriaQuery = builder.createQuery(FlowExportRequestDO.class);
        Root<FlowExportRequestDO> rootTabella = criteriaQuery.from(FlowExportRequestDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        Join<Object, Object> versionJoin = rootTabella.join("version");
        Join<Object, Object> flowJoin = rootTabella.join("flow");
//        Join<Object, Object> confJoin = rootTabella.join("configurationFilters");


        String flowid = (String) poInput.getValue("flowid");
        String versionid = (String) poInput.getValue("versionid");
        String nameLike = (String) poInput.getValue("flow");
        String status = (String) poInput.getValue("status");
        String version = (String) poInput.getValue("version");
        String id = (String) poInput.getValue("id");
        String schedulingType = (String) poInput.getValue("schedulingType");
        String sort = (String) poInput.getValue("sort");
        String sortfield = (String) poInput.getValue("sortfield");
        String validationStatus = (String) poInput.getValue("validationStatus");
        String record = (String) poInput.getValue("record");
        Integer deleted = (Integer) poInput.getValue("deleted");
        Boolean drg = (Boolean) poInput.getValue("drg");
        List<String> extractIds = (List<String>) poInput.getValue("extractIds");
        List<String> aziende = (List<String>) poInput.getValue("aziende");
        
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
        
        if(extractIds != null){
            Path<String> text = rootTabella.get("id");
            restrictions.add(text.in(extractIds));
        }
        
        if(drg != null){
            Path<String> text = rootTabella.get("drg");
            restrictions.add(builder.equal(text, drg));
        }
        //Gestione Estrazioni CANCELLATE
        if (deleted != null) {
            //Restituisce tutte le estrazioni [deleted = 1]
            Path<String> text = rootTabella.get("deleted");
            restrictions.add(builder.equal(text, deleted));
        }
        else{
            //Restituisce tutte le estrazioni diverse da [deleted = 1]
            Path<String> text = rootTabella.get("deleted");
            restrictions.add(builder.notEqual(text, 1));
        }

        if (!StringUtils.isEmpty(id)) {
            Path<String> text = rootTabella.get("id");
            restrictions.add(builder.equal(text, id));
        }

        if (!StringUtils.isEmpty(flowid)) {
            Path<String> text = flowJoin.get("id");
            restrictions.add(builder.equal(text, flowid));
        }
        if (!StringUtils.isEmpty(versionid) || !StringUtils.isEmpty(version)) {
            Path<String> text = versionJoin.get("id");
            restrictions.add(builder.equal(text, versionid));
        }

//        if (!StringUtils.isEmpty(config)) {
//            Path<String> text = confJoin.get("id");
//            restrictions.add(builder.equal(text, config));
//        }

        if (!StringUtils.isEmpty(nameLike)) {
            Path<String> text = flowJoin.get("code");
            restrictions.add(builder.equal(text, nameLike));
        }

        if (!StringUtils.isEmpty(status)) {
            Path<String> text = rootTabella.get("status");
            restrictions.add(builder.equal(text, status));
        } else {
            Path<String> text = rootTabella.get("status");
            restrictions.add(builder.notEqual(text, "CANCELLATA"));
        }
        if (!StringUtils.isEmpty(schedulingType)) {
            Path<String> text = rootTabella.get("schedulingType");
            restrictions.add(builder.equal(text, schedulingType));
        }
        if (!StringUtils.isEmpty(validationStatus)) {
            Path<String> text = rootTabella.get("validationStatus");
            restrictions.add(builder.equal(text, validationStatus));
        }
        
        if(useFlowProfile) {
        	Path<String> name = flowJoin.get("name");
        	restrictions.add(name.in(flowManagerProfileService.getFlows()));
        }


        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals("ASC")) {
                if(sortfield.equals("id") || sortfield.equals("record")){
                    criteriaQuery.orderBy(builder.asc(rootTabella.get(sortfield).as(Integer.class)));
                }
                else {
                    if(sortfield.equals("consol")){
                        criteriaQuery.orderBy(builder.asc(rootTabella.get("consolidata").as(Integer.class)));
                    }
                    else{
                        criteriaQuery.orderBy(builder.asc(rootTabella.get(sortfield)));
                    }
                }
            } else {
                if(sortfield.equals("id")|| sortfield.equals("record")){
                    criteriaQuery.orderBy(builder.desc(rootTabella.get(sortfield).as(Integer.class)));
                }
                else {
                    if(sortfield.equals("consol")){
                        criteriaQuery.orderBy(builder.desc(rootTabella.get("consolidata").as(Integer.class)));
                    }
                    else{
                        criteriaQuery.orderBy(builder.desc(rootTabella.get(sortfield)));
                    }
                }
            }
        } else {
//            criteriaQuery.orderBy(builder.desc(rootTabella.get("requestDate")));
        	criteriaQuery.orderBy(builder.desc(rootTabella.get("id").as(Integer.class)));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }


    public HashMap<String, Object> searchFields(String version, String flow) {


        Query query = null;
        Query query2 = null;
        List<String> flowTables = new ArrayList<String>();
        HashMap<String, Object> hashMap = new HashMap<>();

        query = entityManager.createNativeQuery("Select id from fm_flow_table where version ='" + version + "'" + " and flow ='" + flow + "'");
        flowTables = query.getResultList();

        for (String section : flowTables) {
            query2 = entityManager.createNativeQuery("Select name, id from fm_flow_table_field where flow_table ='" + section + "' "
                    + "AND field_type ='Date' AND is_reference_date ='true'");
            hashMap.put(section, query2.getResultList());
        }

        return hashMap;
    }

    public CriteriaQuery<FlowExportRequestDO> getRequestToManage(MachineState status, String validationStatus) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowExportRequestDO> criteriaQuery = builder.createQuery(FlowExportRequestDO.class);
        Root<FlowExportRequestDO> rootTabella = criteriaQuery.from(FlowExportRequestDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        restrictions.add(builder.equal(rootTabella.get("status"), status.name()));
        restrictions.add(builder.equal(rootTabella.get("deleted"), 0));

        if (validationStatus != null && !validationStatus.isEmpty()) {
            restrictions.add(builder.equal(rootTabella.get("validationStatus"), validationStatus));
        } else {
        	restrictions.add(builder.isNull(rootTabella.get("validationStatus")));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));

    }
    
	public CriteriaQuery<Long> countFlowExportByFilter(BaseSearchInput filter, Boolean useFlowProfile) throws DataAccessException {
        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery=builder.createQuery(Long.class);
        
        Root<FlowExportRequestDO> rootTabella = criteriaQuery.from(FlowExportRequestDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();
        
        Join<Object, Object> versionJoin = rootTabella.join("version");
        Join<Object, Object> flowJoin = rootTabella.join("flow");
        
        String flowid = (String) filter.getValue("flowid");
        String versionid = (String) filter.getValue("versionid");
        String nameLike = (String) filter.getValue("flow");
        String status = (String) filter.getValue("status");
        String version = (String) filter.getValue("version");
        String id = (String) filter.getValue("id");
        String schedulingType = (String) filter.getValue("schedulingType");
        Integer deleted = (Integer) filter.getValue("deleted");
        Boolean drg = (Boolean) filter.getValue("drg");
        List<String> extractIds = (List<String>) filter.getValue("extractIds");
        List<String> aziende = (List<String>) filter.getValue("aziende");
        
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

        //Gestione Estrazioni CANCELLATE
        if (deleted != null) {
            //Restituisce tutte le estrazioni [deleted = 1]
            Path<String> text = rootTabella.get("deleted");
            restrictions.add(builder.equal(text, deleted));
        }
        else{
            //Restituisce tutte le estrazioni diverse da [deleted = 1]
            Path<String> text = rootTabella.get("deleted");
            restrictions.add(builder.notEqual(text, 1));
        }
        
        if(drg != null){
            Path<String> text = rootTabella.get("drg");
            restrictions.add(builder.equal(text, drg));
        }

        if(extractIds != null){
            Path<String> text = rootTabella.get("id");
            restrictions.add(text.in(extractIds));
        }
	        if (!StringUtils.isEmpty(id)) {
	            Path<String> text = rootTabella.get("id");
	            restrictions.add(builder.equal(text, id));
	        }

	        if (!StringUtils.isEmpty(flowid)) {
	            Path<String> text = flowJoin.get("id");
	            restrictions.add(builder.equal(text, flowid));
	        }
	        if (!StringUtils.isEmpty(versionid) || !StringUtils.isEmpty(version)) {
	            Path<String> text = versionJoin.get("id");
	            restrictions.add(builder.equal(text, versionid));
	        }

	        if (!StringUtils.isEmpty(nameLike)) {
	            Path<String> text = flowJoin.get("code");
	            restrictions.add(builder.equal(text, nameLike));
	        }

	        if (!StringUtils.isEmpty(status)) {
	            Path<String> text = rootTabella.get("status");
	            restrictions.add(builder.equal(text, status));
	        } else {
	            Path<String> text = rootTabella.get("status");
	            restrictions.add(builder.notEqual(text, "CANCELLATA"));
	        }
	        if (!StringUtils.isEmpty(schedulingType)) {
	            Path<String> text = rootTabella.get("schedulingType");
	            restrictions.add(builder.equal(text, schedulingType));
	        }

	        if(useFlowProfile) {
	        	Path<String> text = flowJoin.get("name");
	        	text.in(flowManagerProfileService.getFlows());
	        }

	        
	        Expression<Long> count=builder.count(rootTabella);
	        
	        return criteriaQuery.select(count).where(restrictions.toArray(new Predicate[restrictions.size()]));
	        
	
	}

}
