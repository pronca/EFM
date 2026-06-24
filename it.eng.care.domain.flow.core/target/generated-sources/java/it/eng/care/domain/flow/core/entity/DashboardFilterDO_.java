package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardFilterDO.class)
public abstract class DashboardFilterDO_ {

	public static volatile SingularAttribute<DashboardFilterDO, String> filterValue;
	public static volatile SingularAttribute<DashboardFilterDO, DashboardFilterKey> key;

	public static final String FILTER_VALUE = "filterValue";
	public static final String KEY = "key";

}

