package it.eng.care.domain.flow.flowupload.dao.QuerySearch;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.flowupload.model.FlowSectionFileDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowSectionFileDAOQueryByBaseSearchInput {
	
	@Autowired
    private EntityManager entityManager;

    public CriteriaQuery<FlowSectionFileDO> cerca(BaseSearchInput poInput){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlowSectionFileDO> criteriaQuery = builder.createQuery(FlowSectionFileDO.class);
        Root<FlowSectionFileDO> rootTabella = criteriaQuery.from(FlowSectionFileDO.class);
        Set<Predicate> restrictions = Sets.newHashSet();

        Join<Object, Object> sectionFileJoin = rootTabella.join("sectionFileList");
        Join<Object, Object> flowFileUploadRequestJoin = rootTabella.join("request");

        String id = (String) poInput.getValue("id");
        String section = (String) poInput.getValue("section");
        String uploadUsername = (String) poInput.getValue("uploadUsername");
        
        Date uploadDate = (Date) poInput.getValue("uploadDate");
        
        String request = (String) poInput.getValue("request");
        String sectionFileList = (String) poInput.getValue("sectionFileList");
        
        if (!StringUtils.isEmpty(id)) {
            Path<String> text = rootTabella.get("id");
            restrictions.add(builder.equal(text, id));
        }
        
        if (!StringUtils.isEmpty(section)) {
            Path<String> text = rootTabella.get("section");
            restrictions.add(builder.equal(text, section));
        }
        
        if (!StringUtils.isEmpty(uploadUsername)) {
            Path<String> text = rootTabella.get("uploadUsername");
            restrictions.add(builder.equal(text, uploadUsername));
        }
        
        if (uploadDate != null) {
        	restrictions.add(builder.equal(rootTabella.get("uploadDate"), uploadDate));
        }

        if (!StringUtils.isEmpty(request)) {
            Path<String> text = flowFileUploadRequestJoin.get("id");
            restrictions.add(builder.equal(text, request));
        }
        
        if (!StringUtils.isEmpty(sectionFileList)) {
            Path<String> text = sectionFileJoin.get("id");
            restrictions.add(builder.equal(text, sectionFileList));
        }

        return criteriaQuery
                .select(rootTabella)
                .where(restrictions.toArray(new Predicate[restrictions.size()]));
        //.orderBy(builder.desc(clinicalNotes.get("date")));
    }

}
