package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class AnagraficaAssistitoQueryPaginated {
	
	@Autowired
	private EntityManager entityManager;
	
	public CriteriaQuery<AnagraficaAssistitoDO> findAllPaginated(BaseSearchInput poInput,Pageable pageable){
	

	Predicate predicateCodiceFiscale = null,
	predicateCodicePaziente = null,
	predicateCodiceNome = null,
	predicateCodiceCognome = null,
	predicateCodiceDatanascita = null,
	predicateCodiceComunenascita = null,
	predicateCodiceSesso = null,
	predicateComuneResidenza = null,
	predicateNazionalita = null,
	predicateAbilitazione = null,
	predicateAslResidenza = null;
	

	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<AnagraficaAssistitoDO> query = builder.createQuery(AnagraficaAssistitoDO.class);
	Root<AnagraficaAssistitoDO> rootTabella = query.from(AnagraficaAssistitoDO.class);
	Set<Predicate> restrictions = Sets.newHashSet();
	
	String nome = 			poInput.getValue("nome".trim());
	String cognome = 		poInput.getValue("cognome".trim());		
	String comunenascita =  poInput.getValue("comunenascita".trim());
	String sesso = 			poInput.getValue("sesso".trim());
	String codiceFiscale =  poInput.getValue("codiceFiscale".trim());
	String codicePaziente = poInput.getValue("codicePaziente".trim());
	String comuneResidenza = poInput.getValue("comuneResidenza".trim());
	String nazionalita = poInput.getValue("nazionalita".trim());
	String abilitazione = poInput.getValue("abilitazione".trim());
	String aslResidenza = poInput.getValue("aslResidenza".trim());
	boolean isCrudCheck = false;//poInput.getValue("isCrudCheck".trim());
	
	if(!nome.isEmpty()) {
		Path<String> pathNome = rootTabella.get("nome".trim());
		predicateCodiceNome = builder.equal(pathNome,nome.trim());
		restrictions.add(predicateCodiceNome);
	}
	if(!cognome.isEmpty()) {
		Path<String> pathCognome = rootTabella.get("cognome".trim());
	    predicateCodiceCognome = builder.equal(pathCognome,cognome.trim());  
	    restrictions.add(predicateCodiceCognome);
	}	
	if(!comunenascita.isEmpty()) {
	    Path<String> pathComunenascita = rootTabella.get("comunenascita".trim());
	    predicateCodiceComunenascita = builder.equal(pathComunenascita,comunenascita.trim());	
	    restrictions.add(predicateCodiceComunenascita);
	}
	if(!sesso.isEmpty()) {
		Path<String> pathSesso = rootTabella.get("sesso".trim());
		predicateCodiceSesso = builder.equal(pathSesso,sesso.trim()); 
		restrictions.add(predicateCodiceSesso);
	}
	if(!codiceFiscale.isEmpty()) {
		Path<String> pathFiscale = rootTabella.get("codiceFiscale".trim());
		predicateCodiceFiscale = builder.equal(pathFiscale,codiceFiscale.trim());
		restrictions.add(predicateCodiceFiscale);
	}  
	if(!codicePaziente.isEmpty()) {
		Path<String> pathPaziente = rootTabella.get("codicePaziente".trim());    	
		predicateCodicePaziente = builder.equal(pathPaziente, codicePaziente);
		restrictions.add(predicateCodicePaziente);
	}
	if(!comuneResidenza.isEmpty()) {
		Path<String> pathResidenza = rootTabella.get("comuneResidenza".trim());    	
		predicateComuneResidenza = builder.equal(pathResidenza, comuneResidenza);
		restrictions.add(predicateComuneResidenza);
	}
	if(!nazionalita.isEmpty()) {
		Path<String> pathNazionalita = rootTabella.get("nazionalita".trim());    	
		predicateNazionalita = builder.equal(pathNazionalita, nazionalita);
		restrictions.add(predicateNazionalita);
	}
	if(!abilitazione.isEmpty()) {
		Path<String> pathAbilitacione = rootTabella.get("abilitazione".trim());    	
		predicateAbilitazione = builder.equal(pathAbilitacione, abilitazione);
		restrictions.add(predicateAbilitazione);
	}
	if(!aslResidenza.isEmpty()) {
		Path<String> pathAslResidenza = rootTabella.get("aslResidenza".trim());    	
		predicateAslResidenza = builder.equal(pathAslResidenza, aslResidenza);
		restrictions.add(predicateAslResidenza);
	}     
    Predicate andPred;
    if(poInput.getValue("datanascita") == null) { 	
    	andPred = builder.and(predicateCodiceNome,predicateCodiceCognome,predicateCodiceComunenascita,predicateCodiceSesso);
    }else {
    	Date   datanascita =   poInput.getValue("datanascita");
    	Path<Date> pathDatanascita = rootTabella.get("datanascita".trim());
    	predicateCodiceDatanascita = builder.equal(pathDatanascita,datanascita); 
    	andPred = builder.and(predicateCodiceNome,predicateCodiceCognome,predicateCodiceDatanascita,predicateCodiceComunenascita,predicateCodiceSesso);
    }
    
    
	    Predicate firstCheck =  builder.or(predicateCodiceFiscale); 
	    Predicate secondCheck = builder.or(predicateCodicePaziente);
	    	
	    Predicate finalPredicate = builder.or(firstCheck,secondCheck,andPred);
	    restrictions.add(finalPredicate);
   
    System.out.println("Condition== "+codicePaziente+" "+" "+codicePaziente.equals("sdsd".trim()));	
    
	return query.select(rootTabella)
			.where(restrictions.toArray(new Predicate[restrictions.size()]));	
	}
}
