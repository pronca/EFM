package it.eng.care.domain.flow.tabgen.dao.querySearch;

import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.tabgen.dto.TabgenField;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.platform.common.lang.StringUtils;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TabgenValueDAOImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;

    public List<TabgenValueDO> searchTabgenValueByFilter(TabgenValueFilter filter) throws DataAccessException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TabgenValueDO> cq = cb.createQuery(TabgenValueDO.class);
        Root<TabgenValueDO> root = cq.from(TabgenValueDO.class);

        // fetch join equivalent (optional)
        root.fetch("tabgen", JoinType.LEFT);

        List<Predicate> predicates = buildPredicates(cb, root, filter);
        if (!predicates.isEmpty()) cq.where(predicates.toArray(new Predicate[0]));

        // sorting
        if (!StringUtils.isEmpty(filter.getSortField()) && !StringUtils.isEmpty(filter.getSortDir())) {
            if ("ASC".equalsIgnoreCase(filter.getSortDir())) {
                cq.orderBy(cb.asc(root.get(filter.getSortField())));
            } else {
                cq.orderBy(cb.desc(root.get(filter.getSortField())));
            }
        }

        TypedQuery<TabgenValueDO> query = entityManager.createQuery(cq);

        if (filter.getLimit() > 0) {
            query.setFirstResult(filter.getOffset());
            query.setMaxResults(filter.getLimit());
        }

        return query.getResultList();
    }

    public ScrollableResults scrollTabgenValueByFilter(TabgenValueFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TabgenValueDO> cq = cb.createQuery(TabgenValueDO.class);
        Root<TabgenValueDO> root = cq.from(TabgenValueDO.class);

        List<Predicate> predicates = buildPredicates(cb, root, filter);
        if (!predicates.isEmpty()) cq.where(predicates.toArray(new Predicate[0]));

        cq.orderBy(cb.asc(root.get("id")));

        Session session = entityManager.unwrap(Session.class);
        org.hibernate.query.Query<TabgenValueDO> hQuery = session.createQuery(cq);
        hQuery.setReadOnly(true);
        hQuery.setFetchSize(0);

        if (filter.getLimit() > 0) {
            int totalFields = countFields(filter.getTabgenId());
            hQuery.setFirstResult(filter.getOffset());
            hQuery.setMaxResults(filter.getLimit() * totalFields);
        }

        return hQuery.scroll(ScrollMode.FORWARD_ONLY);
    }

    private int countFields(String tabgenId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TabgenFieldDO> root = cq.from(TabgenFieldDO.class);
        cq.select(cb.countDistinct(root.get("id")));
        if (tabgenId != null) {
            cq.where(cb.equal(root.get("tabgen").get("id"), tabgenId));
        }
        Long result = entityManager.createQuery(cq).getSingleResult();
        return result == null ? 0 : result.intValue();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<TabgenValueDO> root, TabgenValueFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (!StringUtils.isEmpty(filter.getId())) {
            predicates.add(cb.equal(root.get("id"), filter.getId()));
        }
        if (!StringUtils.isEmpty(filter.getNotId())) {
            predicates.add(cb.notEqual(root.get("id"), filter.getNotId()));
        }

        // fields 1..20
        for (int i = 1; i <= 20; i++) {
            String value = getFieldByIndex(filter, i);
            if (!StringUtils.isEmpty(value)) {
                addRestriction(cb, root, predicates, "field" + i, value);
            }
        }

        if (!StringUtils.isEmpty(String.valueOf(filter.getTabgenId()))) {
            predicates.add(cb.equal(root.get("tabgen").get("id"), filter.getTabgenId()));
        }

        // columnNames / columnValues equal pairs
        if (filter.getColumnNames() != null && filter.getColumnValues() != null
                && filter.getColumnNames().size() == filter.getColumnValues().size()) {
            for (int i = 0; i < filter.getColumnNames().size(); i++) {
                String name = filter.getColumnNames().get(i);
                Object val = filter.getColumnValues().get(i);
                predicates.add(cb.equal(root.get(name), val));
            }
        }

        // notNull columns
        if (filter.getNotNullColumnNames() != null) {
            for (String c : filter.getNotNullColumnNames()) {
                predicates.add(root.get(c).isNotNull());
            }
        }

        // null columns
        if (filter.getNullColumnNames() != null) {
            for (String c : filter.getNullColumnNames()) {
                predicates.add(root.get(c).isNull());
            }
        }

        // overlap logic
        if (filter.isCheckOverlap()) {
            Date startToOverlap = filter.getEnabledDateToOverlap();
            Date endToOverlap = filter.getDisabledDateToOverlap();
            if (startToOverlap == null) {
                try { startToOverlap = dateFormat.parse("01/01/1900"); } catch (ParseException ignored) {}
            }
            if (endToOverlap == null) {
                try { endToOverlap = dateFormat.parse("31/12/2099"); } catch (ParseException ignored) {}
            }

            Predicate crit1 = cb.lessThanOrEqualTo(root.get("enabledDate"), startToOverlap);
            Predicate crit2 = cb.greaterThan(root.get("disabledDate"), startToOverlap);
            Predicate andExp = cb.and(crit1, crit2);

            Predicate crit3 = cb.lessThan(root.get("enabledDate"), endToOverlap);
            Predicate crit4 = cb.greaterThanOrEqualTo(root.get("disabledDate"), endToOverlap);
            Predicate andExp2 = cb.and(crit3, crit4);

            Predicate crit5 = cb.greaterThanOrEqualTo(root.get("enabledDate"), startToOverlap);
            Predicate crit6 = cb.lessThanOrEqualTo(root.get("disabledDate"), endToOverlap);
            Predicate andExp3 = cb.and(crit5, crit6);

            predicates.add(cb.or(andExp, andExp2, andExp3));
        }

        // inclusion logic
        if (filter.isCheckInclusion()) {
            Date includedStart = filter.getIncludedEnabledDate();
            Date includedEnd = filter.getIncludedDisabledDate();
            if (includedStart == null) {
                try { includedStart = dateFormat.parse("01/01/1900"); } catch (ParseException ignored) {}
            }
            if (includedEnd == null) {
                try { includedEnd = dateFormat.parse("31/12/2099"); } catch (ParseException ignored) {}
            }
            if (includedStart != null && includedEnd != null && includedStart.before(includedEnd)) {
                Predicate p1 = cb.lessThanOrEqualTo(root.get("enabledDate"), includedStart);
                Predicate p2 = cb.greaterThanOrEqualTo(root.get("disabledDate"), includedEnd);
                predicates.add(cb.and(p1, p2));
            }
        }

        // exclusion logic
        if (filter.isCheckExclusion()) {
            Date excludedStart = filter.getExcludedEnabledDate();
            Date excludedEnd = filter.getExcludedDisabledDate();
            if (excludedStart == null) {
                try { excludedStart = dateFormat.parse("01/01/1900"); } catch (ParseException ignored) {}
            }
            if (excludedEnd == null) {
                try { excludedEnd = dateFormat.parse("31/12/2099"); } catch (ParseException ignored) {}
            }
            if (excludedStart != null && excludedEnd != null && excludedStart.before(excludedEnd)) {
                Predicate p1 = cb.greaterThanOrEqualTo(root.get("enabledDate"), excludedStart);
                Predicate p2 = cb.lessThanOrEqualTo(root.get("disabledDate"), excludedEnd);
                predicates.add(cb.and(p1, p2));
            }
        }

        // enabled/disabled date direct filters
        if (filter.getEnabledDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("enabledDate"), filter.getEnabledDate()));
        }
        if (filter.getDisabledDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("disabledDate"), filter.getDisabledDate()));
        }

        return predicates;
    }

    private void addRestriction(CriteriaBuilder cb, Root<TabgenValueDO> root, List<Predicate> predicates, String field, String value) {
        if (value.contains("%")) {
            predicates.add(cb.like(root.get(field), value));
        } else {
            predicates.add(cb.equal(root.get(field), value));
        }
    }

    private String getFieldByIndex(TabgenValueFilter filter, int index) {
        switch (index) {
            case 1: return filter.getField1();
            case 2: return filter.getField2();
            case 3: return filter.getField3();
            case 4: return filter.getField4();
            case 5: return filter.getField5();
            case 6: return filter.getField6();
            case 7: return filter.getField7();
            case 8: return filter.getField8();
            case 9: return filter.getField9();
            case 10: return filter.getField10();
            case 11: return filter.getField11();
            case 12: return filter.getField12();
            case 13: return filter.getField13();
            case 14: return filter.getField14();
            case 15: return filter.getField15();
            case 16: return filter.getField16();
            case 17: return filter.getField17();
            case 18: return filter.getField18();
            case 19: return filter.getField19();
            case 20: return filter.getField20();
            default: return null;
        }
    }

    public void evictObj(Object obj) {
        entityManager.unwrap(Session.class).evict(obj);
    }

    public Long countTabgenValueByFilter(TabgenValueFilter filter) throws DataAccessException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TabgenValueDO> root = cq.from(TabgenValueDO.class);
        cq.select(cb.count(root));
        List<Predicate> predicates = buildPredicates(cb, root, filter);
        if (!predicates.isEmpty()) cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public List<String> retriveColumnValueByColumnName(BaseSearchInput searchInput) {
        String table = searchInput.getValue("table");
        List<String> result = new ArrayList<>();
        if (table == null || table.isEmpty()) return result;

        List<TabgenField> fields = tabgenDelegate.searchFieldsByTabgenId(table);
        List<String> aziende = new ArrayList<>();
        boolean fieldCodiceAzienda = false;
        for (TabgenField f : fields) {
            if ("CODICEAZIENDA".equals(f.getDescription())) {
                fieldCodiceAzienda = true;
                aziende = flowManagerProfileService.getAziendeForUserProfile();
                if (aziende.isEmpty()) fieldCodiceAzienda = false;
            }
        }

        String queryStr = "SELECT " + searchInput.getValue("column") + " FROM " + table;
        if (fieldCodiceAzienda) {
            queryStr += " WHERE CODICEAZIENDA IN ('" + String.join("','", aziende) + "')";
        }
        Query q = entityManager.createNativeQuery(queryStr);
        List<?> rows = q.getResultList();
        for (Object row : rows) {
            result.add(row != null ? row.toString() : null);
        }
        return result;
    }
	
}
