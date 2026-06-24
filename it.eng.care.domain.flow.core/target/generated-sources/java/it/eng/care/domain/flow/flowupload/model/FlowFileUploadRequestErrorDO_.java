package it.eng.care.domain.flow.flowupload.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowFileUploadRequestErrorDO.class)
public abstract class FlowFileUploadRequestErrorDO_ {

	public static volatile SingularAttribute<FlowFileUploadRequestErrorDO, FlowFileUploadRequestDO> request;
	public static volatile SingularAttribute<FlowFileUploadRequestErrorDO, Integer> indexRecord;
	public static volatile SingularAttribute<FlowFileUploadRequestErrorDO, Integer> section;
	public static volatile SingularAttribute<FlowFileUploadRequestErrorDO, String> id;
	public static volatile SingularAttribute<FlowFileUploadRequestErrorDO, String> error;

	public static final String REQUEST = "request";
	public static final String INDEX_RECORD = "indexRecord";
	public static final String SECTION = "section";
	public static final String ID = "id";
	public static final String ERROR = "error";

}

