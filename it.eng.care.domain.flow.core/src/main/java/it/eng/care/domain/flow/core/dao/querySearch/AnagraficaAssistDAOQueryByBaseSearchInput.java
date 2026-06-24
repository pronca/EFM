package it.eng.care.domain.flow.core.dao.querySearch;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class AnagraficaAssistDAOQueryByBaseSearchInput {

	@Autowired
	private EntityManager entityManager;

	public CriteriaQuery<AnagraficaAssistitoDO> cercaInProfile(BaseSearchInput poInput){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AnagraficaAssistitoDO> query = builder.createQuery(AnagraficaAssistitoDO.class);
		Root<AnagraficaAssistitoDO> rootTabella = query.from(AnagraficaAssistitoDO.class);
		Set<Predicate> restrictions = Sets.newHashSet();
		
		String selectedColumn = poInput.getValue("selectedOption");
		if(poInput.getValue("filterValue") instanceof Date) {
			Date pattern = poInput.getValue("filterValue");
			Predicate datePred = builder.equal(rootTabella.<Date>get("datanascita"), pattern);
			restrictions.add(datePred);
			return query
	                .select(rootTabella)
	                .where(restrictions.toArray(new Predicate[restrictions.size()]));
		}else {
			Predicate likePred;
			if(selectedColumn.equals("abilitazione")) {
				Byte pattern = Byte.valueOf(poInput.getValue("filterValue"));
				likePred = builder.equal(rootTabella.<Byte>get("abilitazione"), pattern);
				restrictions.add(likePred);
			}else {
				String pattern = poInput.getValue("filterValue");
				System.out.println("Cerca:: "+selectedColumn+" "+pattern);

				if(selectedColumn!=null && pattern != null) {
					likePred = builder.like(builder.lower(rootTabella.<String>get(selectedColumn)), "%".concat(pattern).concat("%").toLowerCase());
					restrictions.add(likePred);
				}
			}
		
			return query
	                .select(rootTabella)
	                .where(restrictions.toArray(new Predicate[restrictions.size()]));
		}
		
		
	}

}
