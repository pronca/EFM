package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardPraticheDO.class)
public abstract class DashboardPraticheDO_ {

	public static volatile SingularAttribute<DashboardPraticheDO, String> codiceAzienda;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day6;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day7;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day4;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day5;
	public static volatile SingularAttribute<DashboardPraticheDO, Date> lastUpdate;
	public static volatile SetAttribute<DashboardPraticheDO, DashboardPraticheFilterDO> dashboardPraticheFilter;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day2;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day3;
	public static volatile SingularAttribute<DashboardPraticheDO, String> id;
	public static volatile SingularAttribute<DashboardPraticheDO, Integer> day1;
	public static volatile SingularAttribute<DashboardPraticheDO, String> flowName;

	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String DAY6 = "day6";
	public static final String DAY7 = "day7";
	public static final String DAY4 = "day4";
	public static final String DAY5 = "day5";
	public static final String LAST_UPDATE = "lastUpdate";
	public static final String DASHBOARD_PRATICHE_FILTER = "dashboardPraticheFilter";
	public static final String DAY2 = "day2";
	public static final String DAY3 = "day3";
	public static final String ID = "id";
	public static final String DAY1 = "day1";
	public static final String FLOW_NAME = "flowName";

}

