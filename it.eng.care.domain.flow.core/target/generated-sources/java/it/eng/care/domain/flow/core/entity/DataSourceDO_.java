package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DataSourceDO.class)
public abstract class DataSourceDO_ {

	public static volatile SingularAttribute<DataSourceDO, String> hostname;
	public static volatile SingularAttribute<DataSourceDO, String> password;
	public static volatile SingularAttribute<DataSourceDO, DriverDO> driver;
	public static volatile SingularAttribute<DataSourceDO, String> port;
	public static volatile SingularAttribute<DataSourceDO, String> name;
	public static volatile SingularAttribute<DataSourceDO, String> id;
	public static volatile SingularAttribute<DataSourceDO, String> serviceName;
	public static volatile SingularAttribute<DataSourceDO, Boolean> enabled;
	public static volatile SingularAttribute<DataSourceDO, String> username;

	public static final String HOSTNAME = "hostname";
	public static final String PASSWORD = "password";
	public static final String DRIVER = "driver";
	public static final String PORT = "port";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String SERVICE_NAME = "serviceName";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

