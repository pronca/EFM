package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ErrorMessageDO.class)
public abstract class ErrorMessageDO_ extends it.eng.care.platform.persistence.impl.jpa.AbstractEntity_ {

	public static volatile SingularAttribute<ErrorMessageDO, String> severity;
	public static volatile SingularAttribute<ErrorMessageDO, String> description;
	public static volatile SingularAttribute<ErrorMessageDO, String> type;

	public static final String SEVERITY = "severity";
	public static final String DESCRIPTION = "description";
	public static final String TYPE = "type";

}

