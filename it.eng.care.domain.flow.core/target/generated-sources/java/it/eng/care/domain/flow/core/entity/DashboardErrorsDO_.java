package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardErrorsDO.class)
public abstract class DashboardErrorsDO_ {

	public static volatile SetAttribute<DashboardErrorsDO, DashboardErrorsFilterDO> dashboardErrorsFilter;
	public static volatile SingularAttribute<DashboardErrorsDO, String> codiceAzienda;
	public static volatile SingularAttribute<DashboardErrorsDO, Integer> countError;
	public static volatile SingularAttribute<DashboardErrorsDO, String> month;
	public static volatile SingularAttribute<DashboardErrorsDO, String> year;
	public static volatile SingularAttribute<DashboardErrorsDO, Date> lastUpdate;
	public static volatile SingularAttribute<DashboardErrorsDO, String> description;
	public static volatile SingularAttribute<DashboardErrorsDO, String> id;
	public static volatile SingularAttribute<DashboardErrorsDO, String> error;
	public static volatile SingularAttribute<DashboardErrorsDO, String> flowName;
	public static volatile SingularAttribute<DashboardErrorsDO, Date> day;

	public static final String DASHBOARD_ERRORS_FILTER = "dashboardErrorsFilter";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String COUNT_ERROR = "countError";
	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String LAST_UPDATE = "lastUpdate";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String ERROR = "error";
	public static final String FLOW_NAME = "flowName";
	public static final String DAY = "day";

}

