package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TalendDbDO.class)
public abstract class TalendDbDO_ {

	public static volatile SingularAttribute<TalendDbDO, String> database;
	public static volatile SingularAttribute<TalendDbDO, String> password;
	public static volatile SingularAttribute<TalendDbDO, String> port;
	public static volatile SingularAttribute<TalendDbDO, String> host;
	public static volatile SingularAttribute<TalendDbDO, String> id;
	public static volatile SingularAttribute<TalendDbDO, String> username;

	public static final String DATABASE = "database";
	public static final String PASSWORD = "password";
	public static final String PORT = "port";
	public static final String HOST = "host";
	public static final String ID = "id";
	public static final String USERNAME = "username";

}

