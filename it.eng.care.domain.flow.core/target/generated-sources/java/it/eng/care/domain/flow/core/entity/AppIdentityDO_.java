package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppIdentityDO.class)
public abstract class AppIdentityDO_ {

	public static volatile SingularAttribute<AppIdentityDO, String> app;
	public static volatile SingularAttribute<AppIdentityDO, String> codiceAzienda;
	public static volatile SingularAttribute<AppIdentityDO, String> identityRegErrors;
	public static volatile SingularAttribute<AppIdentityDO, String> identity;
	public static volatile SingularAttribute<AppIdentityDO, String> id;
	public static volatile SingularAttribute<AppIdentityDO, String> flowName;
	public static volatile SingularAttribute<AppIdentityDO, String> version;

	public static final String APP = "app";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String IDENTITY_REG_ERRORS = "identityRegErrors";
	public static final String IDENTITY = "identity";
	public static final String ID = "id";
	public static final String FLOW_NAME = "flowName";
	public static final String VERSION = "version";

}

