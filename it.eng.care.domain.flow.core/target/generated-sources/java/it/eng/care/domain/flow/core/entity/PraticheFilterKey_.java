package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PraticheFilterKey.class)
public abstract class PraticheFilterKey_ {

	public static volatile SingularAttribute<PraticheFilterKey, String> dashBoard;
	public static volatile SingularAttribute<PraticheFilterKey, String> codiceAzienda;
	public static volatile SingularAttribute<PraticheFilterKey, String> filterName;

	public static final String DASH_BOARD = "dashBoard";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String FILTER_NAME = "filterName";

}

