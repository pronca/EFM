package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardDO.class)
public abstract class DashboardDO_ {

	public static volatile SingularAttribute<DashboardDO, String> widgetName;
	public static volatile SingularAttribute<DashboardDO, String> codiceAzienda;
	public static volatile SingularAttribute<DashboardDO, Integer> month;
	public static volatile SingularAttribute<DashboardDO, Integer> year;
	public static volatile SingularAttribute<DashboardDO, Date> lastUpdate;
	public static volatile SetAttribute<DashboardDO, DashboardFilterDO> dashboardFilter;
	public static volatile SingularAttribute<DashboardDO, String> tooltip;
	public static volatile SingularAttribute<DashboardDO, String> id;
	public static volatile SingularAttribute<DashboardDO, String> label;
	public static volatile SingularAttribute<DashboardDO, String> value;
	public static volatile SingularAttribute<DashboardDO, Date> day;
	public static volatile SingularAttribute<DashboardDO, String> flow;

	public static final String WIDGET_NAME = "widgetName";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String LAST_UPDATE = "lastUpdate";
	public static final String DASHBOARD_FILTER = "dashboardFilter";
	public static final String TOOLTIP = "tooltip";
	public static final String ID = "id";
	public static final String LABEL = "label";
	public static final String VALUE = "value";
	public static final String DAY = "day";
	public static final String FLOW = "flow";

}

