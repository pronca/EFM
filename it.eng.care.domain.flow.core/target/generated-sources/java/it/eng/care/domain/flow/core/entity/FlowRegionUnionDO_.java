package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowRegionUnionDO.class)
public abstract class FlowRegionUnionDO_ {

	public static volatile SingularAttribute<FlowRegionUnionDO, FlowDO> flowRegion;
	public static volatile SingularAttribute<FlowRegionUnionDO, FlowDO> flowLocal;
	public static volatile SingularAttribute<FlowRegionUnionDO, String> id;

	public static final String FLOW_REGION = "flowRegion";
	public static final String FLOW_LOCAL = "flowLocal";
	public static final String ID = "id";

}

