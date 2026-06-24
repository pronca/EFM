package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VersionDO.class)
public abstract class VersionDO_ {

	public static volatile SingularAttribute<VersionDO, Date> endDate;
	public static volatile SetAttribute<VersionDO, FlowVersionDO> flows;
	public static volatile SingularAttribute<VersionDO, String> description;
	public static volatile SingularAttribute<VersionDO, String> id;
	public static volatile SingularAttribute<VersionDO, Date> creationDate;
	public static volatile SingularAttribute<VersionDO, String> version;
	public static volatile SingularAttribute<VersionDO, Date> startDate;

	public static final String END_DATE = "endDate";
	public static final String FLOWS = "flows";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String VERSION = "version";
	public static final String START_DATE = "startDate";

}

