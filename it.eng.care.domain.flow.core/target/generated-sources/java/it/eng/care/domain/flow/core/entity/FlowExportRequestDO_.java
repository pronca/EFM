package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowExportRequestDO.class)
public abstract class FlowExportRequestDO_ {

	public static volatile SingularAttribute<FlowExportRequestDO, Date> schedulingStartingTime;
	public static volatile SetAttribute<FlowExportRequestDO, FlowConfigurationFilterFieldValueDO> values;
	public static volatile SetAttribute<FlowExportRequestDO, FlowConfigurationFilterDO> configurationFilters;
	public static volatile SingularAttribute<FlowExportRequestDO, String> userCons;
	public static volatile SingularAttribute<FlowExportRequestDO, String> aziendeProfiloFlussi;
	public static volatile SingularAttribute<FlowExportRequestDO, String> randomId;
	public static volatile SingularAttribute<FlowExportRequestDO, Integer> schedulingIntervalMinutes;
	public static volatile SingularAttribute<FlowExportRequestDO, String> record;
	public static volatile SingularAttribute<FlowExportRequestDO, Date> requestDate;
	public static volatile SingularAttribute<FlowExportRequestDO, Boolean> drg;
	public static volatile SingularAttribute<FlowExportRequestDO, String> id;
	public static volatile SetAttribute<FlowExportRequestDO, UploadReturnsRequestDO> uploadReturnRequest;
	public static volatile SingularAttribute<FlowExportRequestDO, JobTalendDO> jobTalendId;
	public static volatile SingularAttribute<FlowExportRequestDO, String> validationStatus;
	public static volatile SingularAttribute<FlowExportRequestDO, FlowDO> flow;
	public static volatile SingularAttribute<FlowExportRequestDO, String> validationStatusDrl;
	public static volatile SingularAttribute<FlowExportRequestDO, String> requester;
	public static volatile SingularAttribute<FlowExportRequestDO, String> regionValidationStatus;
	public static volatile SingularAttribute<FlowExportRequestDO, Boolean> consolidata;
	public static volatile SingularAttribute<FlowExportRequestDO, Date> dateCons;
	public static volatile SingularAttribute<FlowExportRequestDO, VersionDO> version;
	public static volatile SingularAttribute<FlowExportRequestDO, Integer> schedulingIntervalSeconds;
	public static volatile SingularAttribute<FlowExportRequestDO, String> schedulingType;
	public static volatile SingularAttribute<FlowExportRequestDO, String> fileTalend;
	public static volatile SingularAttribute<FlowExportRequestDO, Date> startExtractionDate;
	public static volatile SingularAttribute<FlowExportRequestDO, Integer> deleted;
	public static volatile SetAttribute<FlowExportRequestDO, FlowExportMessageDetailsDO> flowExportMessageDetails;
	public static volatile SingularAttribute<FlowExportRequestDO, String> schedulingInterval;
	public static volatile SingularAttribute<FlowExportRequestDO, Date> endExtractionDate;
	public static volatile SingularAttribute<FlowExportRequestDO, Date> schedulingNextTime;
	public static volatile SingularAttribute<FlowExportRequestDO, String> status;

	public static final String SCHEDULING_STARTING_TIME = "schedulingStartingTime";
	public static final String VALUES = "values";
	public static final String CONFIGURATION_FILTERS = "configurationFilters";
	public static final String USER_CONS = "userCons";
	public static final String AZIENDE_PROFILO_FLUSSI = "aziendeProfiloFlussi";
	public static final String RANDOM_ID = "randomId";
	public static final String SCHEDULING_INTERVAL_MINUTES = "schedulingIntervalMinutes";
	public static final String RECORD = "record";
	public static final String REQUEST_DATE = "requestDate";
	public static final String DRG = "drg";
	public static final String ID = "id";
	public static final String UPLOAD_RETURN_REQUEST = "uploadReturnRequest";
	public static final String JOB_TALEND_ID = "jobTalendId";
	public static final String VALIDATION_STATUS = "validationStatus";
	public static final String FLOW = "flow";
	public static final String VALIDATION_STATUS_DRL = "validationStatusDrl";
	public static final String REQUESTER = "requester";
	public static final String REGION_VALIDATION_STATUS = "regionValidationStatus";
	public static final String CONSOLIDATA = "consolidata";
	public static final String DATE_CONS = "dateCons";
	public static final String VERSION = "version";
	public static final String SCHEDULING_INTERVAL_SECONDS = "schedulingIntervalSeconds";
	public static final String SCHEDULING_TYPE = "schedulingType";
	public static final String FILE_TALEND = "fileTalend";
	public static final String START_EXTRACTION_DATE = "startExtractionDate";
	public static final String DELETED = "deleted";
	public static final String FLOW_EXPORT_MESSAGE_DETAILS = "flowExportMessageDetails";
	public static final String SCHEDULING_INTERVAL = "schedulingInterval";
	public static final String END_EXTRACTION_DATE = "endExtractionDate";
	public static final String SCHEDULING_NEXT_TIME = "schedulingNextTime";
	public static final String STATUS = "status";

}

