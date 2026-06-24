package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardFilterKey.class)
public abstract class DashboardFilterKey_ {

	public static volatile SingularAttribute<DashboardFilterKey, String> dashBoard;
	public static volatile SingularAttribute<DashboardFilterKey, String> codiceAzienda;
	public static volatile SingularAttribute<DashboardFilterKey, String> filterName;

	public static final String DASH_BOARD = "dashBoard";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String FILTER_NAME = "filterName";

}

