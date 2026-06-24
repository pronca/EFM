package it.eng.care.domain.flow.tabgen.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TabgenFieldDO.class)
public abstract class TabgenFieldDO_ extends it.eng.care.platform.persistence.impl.jpa.AbstractEntity_ {

	public static volatile SingularAttribute<TabgenFieldDO, Integer> visible;
	public static volatile SingularAttribute<TabgenFieldDO, Integer> nullable;
	public static volatile SingularAttribute<TabgenFieldDO, String> tabgenValueColumn;
	public static volatile SingularAttribute<TabgenFieldDO, String> format;
	public static volatile SingularAttribute<TabgenFieldDO, String> description;
	public static volatile SingularAttribute<TabgenFieldDO, Integer> progressive;
	public static volatile SingularAttribute<TabgenFieldDO, TabgenFieldDO> tabgenField;
	public static volatile SingularAttribute<TabgenFieldDO, Integer> pk;
	public static volatile SingularAttribute<TabgenFieldDO, String> type;
	public static volatile SingularAttribute<TabgenFieldDO, TabgenDO> tabgen;
	public static volatile SetAttribute<TabgenFieldDO, TabgenFieldDO> tabgenFields;

	public static final String VISIBLE = "visible";
	public static final String NULLABLE = "nullable";
	public static final String TABGEN_VALUE_COLUMN = "tabgenValueColumn";
	public static final String FORMAT = "format";
	public static final String DESCRIPTION = "description";
	public static final String PROGRESSIVE = "progressive";
	public static final String TABGEN_FIELD = "tabgenField";
	public static final String PK = "pk";
	public static final String TYPE = "type";
	public static final String TABGEN = "tabgen";
	public static final String TABGEN_FIELDS = "tabgenFields";

}

