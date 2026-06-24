package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DriverDO.class)
public abstract class DriverDO_ {

	public static volatile SingularAttribute<DriverDO, String> name;
	public static volatile SingularAttribute<DriverDO, String> description;
	public static volatile SingularAttribute<DriverDO, String> id;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

