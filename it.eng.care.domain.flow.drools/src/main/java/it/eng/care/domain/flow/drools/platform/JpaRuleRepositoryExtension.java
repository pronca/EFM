package it.eng.care.domain.flow.drools.platform;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import it.eng.care.platform.rules.impl.persist.model.RuleDO;
import it.eng.care.platform.rules.persist.repository.impl.JpaRuleRepository;

public class JpaRuleRepositoryExtension extends JpaRuleRepository implements RuleRepositoryExtension {

	public JpaRuleRepositoryExtension(Class<RuleDO> entityClass) {
		super(entityClass);
	}
	
	@Override
	public List<RuleDO> findByNames(List<String> ruleNameList) {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RuleDO> criteriaQuery = criteriaBuilder.createQuery(RuleDO.class);
		Root<RuleDO> root = criteriaQuery.from(RuleDO.class);
		List<Predicate> where = new ArrayList<>();
		
		List<Predicate> ps = new ArrayList<Predicate>();
		
		for(String ruleName : ruleNameList) {
			Predicate p1 = criteriaBuilder.equal(root.get("name"), ruleName);
			ps.add(p1);
		}
		
		criteriaBuilder.or((Predicate[]) ps.toArray());
		
		where.addAll(createDatePredicate(new Date(), criteriaBuilder, root));
		criteriaQuery.where(where.toArray(new Predicate[] {}));
		TypedQuery<RuleDO> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
	/**
	 * @deprecated usare il metodo della platform
	 * @param date
	 * @param criteriaBuilder
	 * @param from
	 * @return
	 */
	private List<Predicate> createDatePredicate(Date date, CriteriaBuilder criteriaBuilder, From<?,?> from) {
		if (date == null)
			date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Predicate> datePredicates = new ArrayList<>();
		datePredicates.add(criteriaBuilder.lessThanOrEqualTo(from.get("period").<Date>get("startDate"), date));
		List<Predicate> finalDatePredicate = new ArrayList<>();
		finalDatePredicate.add(criteriaBuilder.greaterThan(from.get("period").<Date>get("endDate"), date));
		finalDatePredicate.add(criteriaBuilder.isNull(from.get("period").get("endDate")));
		datePredicates.add(criteriaBuilder.or(finalDatePredicate.toArray(new Predicate[] {})));
		return datePredicates;
	}

}
