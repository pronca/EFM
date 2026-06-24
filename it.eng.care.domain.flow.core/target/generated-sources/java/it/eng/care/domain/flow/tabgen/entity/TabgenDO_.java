package it.eng.care.domain.flow.tabgen.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TabgenDO.class)
public abstract class TabgenDO_ extends it.eng.care.platform.persistence.impl.jpa.AbstractEntity_ {

	public static volatile SingularAttribute<TabgenDO, Integer> visible;
	public static volatile SetAttribute<TabgenDO, TabgenValueDO> tabgenValues;
	public static volatile SingularAttribute<TabgenDO, String> description;
	public static volatile SingularAttribute<TabgenDO, Integer> fieldNum;
	public static volatile SingularAttribute<TabgenDO, String> type;
	public static volatile SetAttribute<TabgenDO, TabgenFieldDO> tabgenFields;

	public static final String VISIBLE = "visible";
	public static final String TABGEN_VALUES = "tabgenValues";
	public static final String DESCRIPTION = "description";
	public static final String FIELD_NUM = "fieldNum";
	public static final String TYPE = "type";
	public static final String TABGEN_FIELDS = "tabgenFields";

}

