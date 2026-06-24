package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardErrorsFilterDO.class)
public abstract class DashboardErrorsFilterDO_ {

	public static volatile SingularAttribute<DashboardErrorsFilterDO, String> filterValue;
	public static volatile SingularAttribute<DashboardErrorsFilterDO, ErrorsFilterKey> key;

	public static final String FILTER_VALUE = "filterValue";
	public static final String KEY = "key";

}

