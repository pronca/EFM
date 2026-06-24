package it.eng.care.domain.flow.flowupload.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.flowupload.bean.WellFormedStatusEnum;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowFileUploadRequestDAObyBaseSearchInput {
	
	@Autowired
    private EntityManager entityManager;
	
	public CriteriaQuery<FlowFileUploadRequestDO> cerca(BaseSearchInput poInput) {

	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<FlowFileUploadRequestDO> cq = builder.createQuery(FlowFileUploadRequestDO.class);
	    Root<FlowFileUploadRequestDO> root = cq.from(FlowFileUploadRequestDO.class);

	    // IMPORTANT: usa LEFT per non perdere righe
	    Join<Object, Object> versionJoin = root.join("version", JoinType.LEFT);
	    Join<Object, Object> flowJoin    = root.join("flow", JoinType.LEFT);

	    // Predicati in LIST (non Set) -> albero logico stabile in Hibernate 6
	    List<Predicate> restrictions = new ArrayList<>();

	    String flow = (String) poInput.getValue("flow");
	    String version = (String) poInput.getValue("version");
	    Date creationDate = (Date) poInput.getValue("creationDate");
	    Date validationDate = (Date) poInput.getValue("validationDate");
	    String status = (String) poInput.getValue("status");
	    String sort = (String) poInput.getValue("sort");
	    String sortfield = (String) poInput.getValue("sortfield");

	    // aziende le leggi dai param (come stai già facendo)
	    @SuppressWarnings("unchecked")
	    List<String> aziende = (List<String>) poInput.getParam("aziende");

	    String id = (String) poInput.getValue("id");
	    String codiceAzienda = (String) poInput.getValue("codiceazienda");

	    // ---- filtro aziendeProfiloFlussi: (IS NULL) OR (LIKE ... OR LIKE ...)
	    if (aziende != null && !aziende.isEmpty()) {
	        Path<String> text = root.get("aziendeProfiloFlussi");

	        List<Predicate> likes = new ArrayList<>();
	        for (String az : aziende) {
	            if (StringUtils.isNotBlank(az)) {
	                likes.add(builder.like(builder.upper(text), "%" + az.trim().toUpperCase() + "%"));
	            }
	        }

	        if (!likes.isEmpty()) {
	            Predicate likeOr = builder.or(likes.toArray(new Predicate[0]));
	            restrictions.add(builder.or(builder.isNull(text), likeOr));
	        } else {
	            // se la lista è piena di stringhe vuote, almeno non bloccare tutto
	            restrictions.add(builder.isNull(text));
	        }
	    }

	    // ---- status mapping descrizione -> enum name
	    if (StringUtils.isNotBlank(status)) {
	        for (WellFormedStatusEnum a : WellFormedStatusEnum.values()) {
	            if (a.getDescription().equals(status)) {
	                status = a.name();
	                break;
	            }
	        }
	    }

	    // ---- filtri standard
	    if (StringUtils.isNotBlank(flow)) {
	        restrictions.add(builder.equal(flowJoin.get("id"), flow));
	    }

	    if (StringUtils.isNotBlank(version)) {
	        restrictions.add(builder.equal(versionJoin.get("id"), version));
	    }

	    if (StringUtils.isNotBlank(status)) {
	        restrictions.add(builder.equal(root.get("status"), status));
	    }

	    if (StringUtils.isNotBlank(id)) {
	        restrictions.add(builder.equal(root.get("id"), id));
	    }

	    if (creationDate != null) {
	        restrictions.add(builder.equal(root.get("creationDate"), creationDate));
	    }

	    if (validationDate != null) {
	        restrictions.add(builder.equal(root.get("validationDate"), validationDate));
	    }

	    // ---- codiceazienda: AZIENDELOADEDINFILE è CSV -> LIKE, non EQUAL
	    if (StringUtils.isNotBlank(codiceAzienda)) {
	        Path<String> text = root.get("aziendeLoadedInFile");
	        restrictions.add(builder.like(builder.upper(text), "%" + codiceAzienda.trim().toUpperCase() + "%"));
	    }

	    // ---- ORDER BY
	    if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(sortfield)) {
	        if ("ASC".equalsIgnoreCase(sort)) {
	            cq.orderBy(builder.asc(root.get(sortfield)));
	        } else {
	            cq.orderBy(builder.desc(root.get(sortfield)));
	        }
	    } else {
	        cq.orderBy(builder.desc(root.get("creationDate")));
	    }

	    // ---- WHERE (and esplicito)
	    if (!restrictions.isEmpty()) {
	        cq.where(builder.and(restrictions.toArray(new Predicate[0])));
	    }

	    return cq.select(root);
	}
	
	public CriteriaQuery<Long> countFlowExportByFilter(BaseSearchInput filter) throws DataAccessException {

	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Long> cq = builder.createQuery(Long.class);
	    Root<FlowFileUploadRequestDO> root = cq.from(FlowFileUploadRequestDO.class);

	    // IMPORTANT: usa LEFT per non perdere righe
	    Join<Object, Object> versionJoin = root.join("version", JoinType.LEFT);
	    Join<Object, Object> flowJoin    = root.join("flow", JoinType.LEFT);

	    // Predicati in LIST (non Set) -> albero logico stabile in Hibernate 6
	    List<Predicate> restrictions = new ArrayList<>();

	    String flow = (String) filter.getValue("flow");
	    String version = (String) filter.getValue("version");
	    Date creationDate = (Date) filter.getValue("creationDate");
	    Date validationDate = (Date) filter.getValue("validationDate");
	    String status = (String) filter.getValue("status");
	    String id = (String) filter.getValue("id");
	    String codiceAzienda = (String) filter.getValue("codiceazienda");

	    @SuppressWarnings("unchecked")
	    List<String> aziende = (List<String>) filter.getParam("aziende"); // <- coerente col controller/DAO

	    // ---- filtro aziendeProfiloFlussi: (IS NULL) OR (LIKE ... OR LIKE ...)
	    if (aziende != null && !aziende.isEmpty()) {
	        Path<String> text = root.get("aziendeProfiloFlussi");

	        List<Predicate> likes = new ArrayList<>();
	        for (String az : aziende) {
	            if (StringUtils.isNotBlank(az)) {
	                likes.add(builder.like(builder.upper(text), "%" + az.trim().toUpperCase() + "%"));
	            }
	        }

	        if (!likes.isEmpty()) {
	            Predicate likeOr = builder.or(likes.toArray(new Predicate[0]));
	            restrictions.add(builder.or(builder.isNull(text), likeOr));
	        } else {
	            restrictions.add(builder.isNull(text));
	        }
	    }

	    // ---- status mapping descrizione -> enum name
	    if (StringUtils.isNotBlank(status)) {
	        for (WellFormedStatusEnum a : WellFormedStatusEnum.values()) {
	            if (a.getDescription().equals(status)) {
	                status = a.name();
	                break;
	            }
	        }
	    }

	    // ---- filtri standard
	    if (StringUtils.isNotBlank(flow)) {
	        restrictions.add(builder.equal(flowJoin.get("id"), flow));
	    }

	    if (StringUtils.isNotBlank(version)) {
	        restrictions.add(builder.equal(versionJoin.get("id"), version));
	    }

	    if (StringUtils.isNotBlank(status)) {
	        restrictions.add(builder.equal(root.get("status"), status));
	    }

	    if (StringUtils.isNotBlank(id)) {
	        restrictions.add(builder.equal(root.get("id"), id));
	    }

	    if (creationDate != null) {
	        restrictions.add(builder.equal(root.get("creationDate"), creationDate));
	    }

	    if (validationDate != null) {
	        restrictions.add(builder.equal(root.get("validationDate"), validationDate));
	    }

	    // ---- codiceazienda: AZIENDELOADEDINFILE è CSV -> LIKE, non EQUAL
	    if (StringUtils.isNotBlank(codiceAzienda)) {
	        Path<String> text = root.get("aziendeLoadedInFile");
	        restrictions.add(builder.like(builder.upper(text), "%" + codiceAzienda.trim().toUpperCase() + "%"));
	    }

	    // ---- COUNT (distinct consigliato per evitare sorprese con join future)
	    Expression<Long> count = builder.countDistinct(root);

	    if (!restrictions.isEmpty()) {
	        cq.where(builder.and(restrictions.toArray(new Predicate[0])));
	    }

	    return cq.select(count);
	}
	
}
