package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class MonitorSdoByQuerySearchInput {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;
	
	public CriteriaQuery<MonitorSdoXlDO> filterData(BaseSearchInput baseSearchInput) {

	    String sortDir = baseSearchInput.getValue("sortDir");
	    String sortField = baseSearchInput.getValue("sortField");

	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<MonitorSdoXlDO> query = builder.createQuery(MonitorSdoXlDO.class);
	    Root<MonitorSdoXlDO> rootTabella = query.from(MonitorSdoXlDO.class);
	    Set<Predicate> restrictions = Sets.newHashSet();

	    // caricamento lista aziende visibili dall'utente
	    List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();

	    String flussoId = baseSearchInput.getValue("flussoId");
	    String flusso = baseSearchInput.getValue("flusso");
	    String presidio = baseSearchInput.getValue("presidio");
	    String nominativo = baseSearchInput.getValue("nominativo");
	    String reparto = baseSearchInput.getValue("reparto");
	    String operazione = baseSearchInput.getValue("operazione");
	    String transmissione = baseSearchInput.getValue("trasmissione");
	    String protocolloSio = baseSearchInput.getValue("protocolloSio");
	    String esito = baseSearchInput.getValue("esito");
	    Date dataRicoveroFrom = baseSearchInput.getValue("dataRicoveroFrom");
	    Date dataRicoveroTo = baseSearchInput.getValue("dataRicoveroTo");
	    Date dataDimissioneFrom = baseSearchInput.getValue("dataDimissioneFrom");
	    Date dataDimissioneTo = baseSearchInput.getValue("dataDimissioneTo");
	    Date dataInvioFrom = baseSearchInput.getValue("dataInvioFrom");
	    Date dataInvioTo = baseSearchInput.getValue("dataInvioTo");
	    Date dataRicezioneFrom = baseSearchInput.getValue("dataRicezioneFrom");
	    Date dataRicezioneTo = baseSearchInput.getValue("dataRicezioneTo");
	    String nosologicoFrom = baseSearchInput.getValue("nosologicoFrom");
	    String nosologicoTo = baseSearchInput.getValue("nosologicoTo");
	    String idEstrazione = baseSearchInput.getValue("idEstrazione");

	    Predicate filterRestrictions;

	    Predicate deletedP = builder.or(
	            builder.isNull(rootTabella.get("deleted")),
	            builder.equal(rootTabella.get("deleted"), false)
	    );
	    restrictions.add(deletedP);

	    if (flussoId != null && !StringUtils.isEmpty(flussoId)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("flussoId")),
	                "%".concat(flussoId.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (flusso != null && !StringUtils.isEmpty(flusso)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("flusso")),
	                "%".concat(flusso.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (idEstrazione != null && !StringUtils.isEmpty(idEstrazione)) {
	        filterRestrictions = builder.equal(rootTabella.<String>get("idEstrazione"), idEstrazione);
	        restrictions.add(filterRestrictions);
	    }

	    if (presidio != null && !StringUtils.isEmpty(presidio)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("presidio")),
	                "%".concat(presidio.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (nominativo != null && !StringUtils.isEmpty(nominativo)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("nominativo")),
	                "%".concat(nominativo.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (reparto != null && !StringUtils.isEmpty(reparto)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("reparto")),
	                "%".concat(reparto.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (operazione != null && !StringUtils.isEmpty(operazione)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("operazione")),
	                "%".concat(operazione.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (transmissione != null && !StringUtils.isEmpty(transmissione)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("trasmissione")),
	                "%".concat(transmissione.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (protocolloSio != null && !StringUtils.isEmpty(protocolloSio)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("protocolloSio")),
	                "%".concat(protocolloSio.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (esito != null && !StringUtils.isEmpty(esito)) {
	        filterRestrictions = builder.like(
	                builder.lower(rootTabella.<String>get("esito")),
	                "%".concat(esito.trim()).concat("%").toLowerCase()
	        );
	        restrictions.add(filterRestrictions);
	    }

	    if (dataRicoveroFrom != null) {
	        restrictions.add(builder.greaterThanOrEqualTo(rootTabella.get("dataRicovero"), dataRicoveroFrom));
	    }
	    if (dataRicoveroTo != null) {
	        restrictions.add(builder.lessThanOrEqualTo(rootTabella.get("dataRicovero"), dataRicoveroTo));
	    }

	    if (dataDimissioneFrom != null) {
	        restrictions.add(builder.greaterThanOrEqualTo(rootTabella.get("dataDimissione"), dataDimissioneFrom));
	    }
	    if (dataDimissioneTo != null) {
	        restrictions.add(builder.lessThanOrEqualTo(rootTabella.get("dataDimissione"), dataDimissioneTo));
	    }

	    if (dataInvioFrom != null) {
	        restrictions.add(builder.greaterThanOrEqualTo(rootTabella.get("dataInvio"), dataInvioFrom));
	    }
	    if (dataInvioTo != null) {
	        restrictions.add(builder.lessThanOrEqualTo(rootTabella.get("dataInvio"), dataInvioTo));
	    }

	    if (dataRicezioneFrom != null) {
	        restrictions.add(builder.greaterThanOrEqualTo(rootTabella.get("dataRicezione"), dataRicezioneFrom));
	    }
	    if (dataRicezioneTo != null) {
	        restrictions.add(builder.lessThanOrEqualTo(rootTabella.get("dataRicezione"), dataRicezioneTo));
	    }

	    if (nosologicoFrom != null && !StringUtils.isEmpty(nosologicoFrom)
	            && nosologicoTo != null && !StringUtils.isEmpty(nosologicoTo)) {

	        Long fromNum = Long.valueOf(nosologicoFrom.trim());
	        Long toNum = Long.valueOf(nosologicoTo.trim());
	        Expression<Long> e1 = rootTabella.get("nosologico").as(Long.class);

	        restrictions.add(builder.greaterThanOrEqualTo(e1, fromNum));
	        restrictions.add(builder.lessThanOrEqualTo(e1, toNum));

	    } else if (nosologicoFrom != null && !StringUtils.isEmpty(nosologicoFrom)) {

	        Long fromNum = Long.valueOf(nosologicoFrom.trim());
	        Expression<Long> e1 = rootTabella.get("nosologico").as(Long.class);
	        restrictions.add(builder.equal(e1, fromNum));

	    } else if (nosologicoTo != null && !StringUtils.isEmpty(nosologicoTo)) {

	        Long toNum = Long.valueOf(nosologicoTo.trim());
	        Expression<Long> e1 = rootTabella.get("nosologico").as(Long.class);
	        restrictions.add(builder.lessThanOrEqualTo(e1, toNum));
	    }

	    if (aziende != null && !aziende.isEmpty()) {
	        restrictions.add(rootTabella.get("azienda").in(aziende));
	    }

	    List<Order> orderList = new ArrayList<>();
	    if (sortDir != null && sortField != null && sortDir.equals("asc")) {
	        orderList.add(builder.asc(rootTabella.get(sortField)));
	    } else if (sortDir != null && sortField != null && sortDir.equals("desc")) {
	        orderList.add(builder.desc(rootTabella.get(sortField)));
	    }

	    query.select(rootTabella)
	         .where(restrictions.toArray(new Predicate[0]))
	         .orderBy(orderList);

	    return query;
	}
	
	/*public CriteriaQuery<MonitorSdoXlDO> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MonitorSdoXlDO> query = builder.createQuery(MonitorSdoXlDO.class);
		Root<MonitorSdoXlDO> rootTabella = query.from(MonitorSdoXlDO.class);
		
		query = query.select(rootTabella)
				.where(builder.or(
						builder.isNull(rootTabella.get("deleted")), 
						builder.equal(rootTabella.get("deleted"), false)));
		
		entityManager.createQuery(query).setFirstResult(startPosition);
		
	}*/
	
	
}
