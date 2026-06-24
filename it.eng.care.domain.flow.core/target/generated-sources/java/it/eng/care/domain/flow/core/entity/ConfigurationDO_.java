package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConfigurationDO.class)
public abstract class ConfigurationDO_ {

	public static volatile SingularAttribute<ConfigurationDO, String> descrizione;
	public static volatile SingularAttribute<ConfigurationDO, String> keyId;
	public static volatile SingularAttribute<ConfigurationDO, String> value;

	public static final String DESCRIZIONE = "descrizione";
	public static final String KEY_ID = "keyId";
	public static final String VALUE = "value";

}

