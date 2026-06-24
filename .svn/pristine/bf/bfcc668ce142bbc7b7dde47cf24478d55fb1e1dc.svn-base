package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.service.AnagraficaAssistitoService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class AnagraficaAssistitoDAOQueryByCriteria {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private AnagraficaAssistitoService anagraficaAssistatoService;
	
	public CriteriaQuery<AnagraficaAssistitoDO> searchAnagrafica(BaseSearchInput poInput) {

	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<AnagraficaAssistitoDO> query = builder.createQuery(AnagraficaAssistitoDO.class);
	    Root<AnagraficaAssistitoDO> root = query.from(AnagraficaAssistitoDO.class);

	    List<Predicate> andList = new ArrayList<>();   // <--- PREDICATI DINAMICI
	    Predicate predicateCodiceFiscale = null;
	    Predicate predicateCodicePaziente = null;

	    // Recupero eventuale entità esistente
	    String id = isNullOrEmpty(poInput.getValue("id"));
	    AnagraficaAssistitoDO anaAssEdit =
	            (id != null && !id.isEmpty()) ? anagraficaAssistatoService.getEntityById(id) : new AnagraficaAssistitoDO();

	    // Valori input
	    String nome            = isNullOrEmpty(poInput.getValue("nome"));
	    String cognome         = isNullOrEmpty(poInput.getValue("cognome"));
	    String comunenascita   = isNullOrEmpty(poInput.getValue("comunenascita"));
	    String sesso           = isNullOrEmpty(poInput.getValue("sesso"));
	    String codiceFiscale   = isNullOrEmpty(poInput.getValue("codiceFiscale"));
	    String codicePaziente  = isNullOrEmpty(poInput.getValue("codicePaziente"));

	    // === POPOLAMENTO DINAMICO AND-PREDICATE ===
	    if (nome != null && !nome.isEmpty()) {
	        andList.add(builder.equal(builder.upper(root.get("nome")), nome.toUpperCase().trim()));
	    }

	    if (cognome != null && !cognome.isEmpty()) {
	        andList.add(builder.equal(builder.upper(root.get("cognome")), cognome.toUpperCase().trim()));
	    }

	    if (comunenascita != null && !comunenascita.isEmpty()) {
	        andList.add(builder.equal(root.get("comunenascita"), comunenascita.trim()));
	    }

	    if (sesso != null && !sesso.isEmpty()) {
	        andList.add(builder.equal(root.get("sesso"), sesso.trim()));
	    }

	    if (codiceFiscale != null && !codiceFiscale.isEmpty()) {
	        predicateCodiceFiscale = builder.equal(root.get("codiceFiscale"), codiceFiscale.trim());
	        andList.add(predicateCodiceFiscale);
	    }

	    if (codicePaziente != null && !codicePaziente.isEmpty()) {
	        predicateCodicePaziente = builder.equal(root.get("codicePaziente"), codicePaziente.trim());
	        //andList.add(predicateCodicePaziente);
	    }

	    // === DATA DI NASCITA SE PRESENTE ===
	    Predicate predicateCodiceDatanascita = null;

	    if (poInput.getValue("datanascita") != null) {
	        Path<Date> pathData = root.get("datanascita");
	        Date datanascita = poInput.getValue("datanascita");
	        predicateCodiceDatanascita = builder.equal(pathData, datanascita);
	        andList.add(predicateCodiceDatanascita);
	    }

	    // AND finale dinamico
	    Predicate andPred = builder.and(andList.toArray(new Predicate[0]));


	    // ======================
	    // OR CHECK SU CF E CODICE PAZIENTE
	    // ======================
	    Predicate firstCheck = null;
	    Predicate secondCheck = null;

	    if (predicateCodiceFiscale != null) {
	        if (anaAssEdit.getId() == null ||
	            (anaAssEdit.getId() != null &&
	             !codiceFiscale.isEmpty() &&
	             !codiceFiscale.equals(anaAssEdit.getCodiceFiscale()))) {

	            firstCheck = builder.or(predicateCodiceFiscale);
	        }
	    }

	    if (predicateCodicePaziente != null) {
	        if (anaAssEdit.getId() == null ||
	            (anaAssEdit.getId() != null &&
	             !codicePaziente.isEmpty() &&
	             !codicePaziente.equals(anaAssEdit.getCodicePaziente()))) {

	            secondCheck = builder.or(predicateCodicePaziente);
	        }
	    }

	    // ======================
	    // COSTRUZIONE FINAL-PREDICATE
	    // ======================
	    Predicate finalPredicate;

	    if (anaAssEdit.getId() == null) {
	        if (firstCheck != null && secondCheck != null)
	            finalPredicate = builder.or(firstCheck, secondCheck, andPred);
	        else
	            finalPredicate = builder.or(andPred);
	    } else {
	        if (firstCheck != null && secondCheck != null)
	            finalPredicate = builder.or(firstCheck, secondCheck, andPred);
	        else if (firstCheck != null)
	            finalPredicate = builder.or(firstCheck, andPred);
	        else if (secondCheck != null)
	            finalPredicate = builder.or(secondCheck, andPred);
	        else
	            finalPredicate = builder.or(andPred);
	    }

	    return query.select(root).where(finalPredicate);
	}
	
	private String isNullOrEmpty(String data) {
		String dataTmp = data;
		if(dataTmp == null || dataTmp.isEmpty()) {
			return "";
		}
		return dataTmp;
	}	
}
