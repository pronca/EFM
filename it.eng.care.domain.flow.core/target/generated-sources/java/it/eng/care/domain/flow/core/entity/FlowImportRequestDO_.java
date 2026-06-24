package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowImportRequestDO.class)
public abstract class FlowImportRequestDO_ {

	public static volatile SetAttribute<FlowImportRequestDO, FlowImportRequestFieldDateDO> dateFields;
	public static volatile SingularAttribute<FlowImportRequestDO, Date> startExtractionDate;
	public static volatile SingularAttribute<FlowImportRequestDO, String> record;
	public static volatile SingularAttribute<FlowImportRequestDO, String> importingUser;
	public static volatile SingularAttribute<FlowImportRequestDO, Date> requestDate;
	public static volatile SingularAttribute<FlowImportRequestDO, Date> endExtractionDate;
	public static volatile SingularAttribute<FlowImportRequestDO, String> id;
	public static volatile SingularAttribute<FlowImportRequestDO, VersionDO> version;
	public static volatile SingularAttribute<FlowImportRequestDO, String> validationStatus;
	public static volatile SingularAttribute<FlowImportRequestDO, FlowDO> flow;
	public static volatile SingularAttribute<FlowImportRequestDO, String> aziendeProfiloFlussi;
	public static volatile SingularAttribute<FlowImportRequestDO, String> status;

	public static final String DATE_FIELDS = "dateFields";
	public static final String START_EXTRACTION_DATE = "startExtractionDate";
	public static final String RECORD = "record";
	public static final String IMPORTING_USER = "importingUser";
	public static final String REQUEST_DATE = "requestDate";
	public static final String END_EXTRACTION_DATE = "endExtractionDate";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String VALIDATION_STATUS = "validationStatus";
	public static final String FLOW = "flow";
	public static final String AZIENDE_PROFILO_FLUSSI = "aziendeProfiloFlussi";
	public static final String STATUS = "status";

}

