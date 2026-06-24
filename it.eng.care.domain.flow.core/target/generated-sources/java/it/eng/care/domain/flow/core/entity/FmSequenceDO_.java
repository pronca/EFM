package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FmSequenceDO.class)
public abstract class FmSequenceDO_ {

	public static volatile SingularAttribute<FmSequenceDO, String> id;
	public static volatile SingularAttribute<FmSequenceDO, Integer> currentValue;

	public static final String ID = "id";
	public static final String CURRENT_VALUE = "currentValue";

}

