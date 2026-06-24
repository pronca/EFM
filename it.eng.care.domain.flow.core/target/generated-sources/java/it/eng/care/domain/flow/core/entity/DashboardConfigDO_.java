package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardConfigDO.class)
public abstract class DashboardConfigDO_ {

	public static volatile SingularAttribute<DashboardConfigDO, String> tipo;
	public static volatile SingularAttribute<DashboardConfigDO, String> color;
	public static volatile SingularAttribute<DashboardConfigDO, String> activity;
	public static volatile SingularAttribute<DashboardConfigDO, String> footer;
	public static volatile SingularAttribute<DashboardConfigDO, String> query;
	public static volatile SingularAttribute<DashboardConfigDO, String> queryDetailExp;
	public static volatile SingularAttribute<DashboardConfigDO, String> icon;
	public static volatile SingularAttribute<DashboardConfigDO, String> tooltip;
	public static volatile SingularAttribute<DashboardConfigDO, String> label;
	public static volatile SingularAttribute<DashboardConfigDO, String> badge;
	public static volatile SingularAttribute<DashboardConfigDO, String> queryDetail;
	public static volatile SingularAttribute<DashboardConfigDO, String> codiceAzienda;
	public static volatile SingularAttribute<DashboardConfigDO, String> fieldAcceptedQueryDetail;
	public static volatile SingularAttribute<DashboardConfigDO, String> name;
	public static volatile SingularAttribute<DashboardConfigDO, String> queryDetailHeader;
	public static volatile SingularAttribute<DashboardConfigDO, String> id;
	public static volatile SingularAttribute<DashboardConfigDO, FlowDO> flow;

	public static final String TIPO = "tipo";
	public static final String COLOR = "color";
	public static final String ACTIVITY = "activity";
	public static final String FOOTER = "footer";
	public static final String QUERY = "query";
	public static final String QUERY_DETAIL_EXP = "queryDetailExp";
	public static final String ICON = "icon";
	public static final String TOOLTIP = "tooltip";
	public static final String LABEL = "label";
	public static final String BADGE = "badge";
	public static final String QUERY_DETAIL = "queryDetail";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String FIELD_ACCEPTED_QUERY_DETAIL = "fieldAcceptedQueryDetail";
	public static final String NAME = "name";
	public static final String QUERY_DETAIL_HEADER = "queryDetailHeader";
	public static final String ID = "id";
	public static final String FLOW = "flow";

}

