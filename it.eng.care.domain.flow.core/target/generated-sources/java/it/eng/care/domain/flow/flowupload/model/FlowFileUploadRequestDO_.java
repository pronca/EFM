package it.eng.care.domain.flow.flowupload.model;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowFileUploadRequestDO.class)
public abstract class FlowFileUploadRequestDO_ {

	public static volatile SingularAttribute<FlowFileUploadRequestDO, String> aziendeLoadedInFile;
	public static volatile ListAttribute<FlowFileUploadRequestDO, FlowSectionFileDO> files;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, String> id;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, Date> validationDate;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, Date> creationDate;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, VersionDO> version;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, FlowDO> flow;
	public static volatile ListAttribute<FlowFileUploadRequestDO, FlowFileUploadRequestErrorDO> errors;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, String> aziendeProfiloFlussi;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, String> username;
	public static volatile SingularAttribute<FlowFileUploadRequestDO, String> status;

	public static final String AZIENDE_LOADED_IN_FILE = "aziendeLoadedInFile";
	public static final String FILES = "files";
	public static final String ID = "id";
	public static final String VALIDATION_DATE = "validationDate";
	public static final String CREATION_DATE = "creationDate";
	public static final String VERSION = "version";
	public static final String FLOW = "flow";
	public static final String ERRORS = "errors";
	public static final String AZIENDE_PROFILO_FLUSSI = "aziendeProfiloFlussi";
	public static final String USERNAME = "username";
	public static final String STATUS = "status";

}

