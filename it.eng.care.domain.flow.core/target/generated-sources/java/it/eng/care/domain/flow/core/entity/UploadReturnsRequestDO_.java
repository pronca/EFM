package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UploadReturnsRequestDO.class)
public abstract class UploadReturnsRequestDO_ {

	public static volatile SingularAttribute<UploadReturnsRequestDO, String> extractionId;
	public static volatile SingularAttribute<UploadReturnsRequestDO, String> tipoValidazioneReg;
	public static volatile SingularAttribute<UploadReturnsRequestDO, byte[]> file;
	public static volatile SingularAttribute<UploadReturnsRequestDO, Boolean> hasErrors;
	public static volatile SingularAttribute<UploadReturnsRequestDO, Date> endProcessDate;
	public static volatile SingularAttribute<UploadReturnsRequestDO, byte[]> errorFile;
	public static volatile SingularAttribute<UploadReturnsRequestDO, String> id;
	public static volatile SingularAttribute<UploadReturnsRequestDO, Date> creationDate;
	public static volatile SingularAttribute<UploadReturnsRequestDO, String> userId;
	public static volatile SingularAttribute<UploadReturnsRequestDO, String> flowId;
	public static volatile SingularAttribute<UploadReturnsRequestDO, String> fileNameOrigin;

	public static final String EXTRACTION_ID = "extractionId";
	public static final String TIPO_VALIDAZIONE_REG = "tipoValidazioneReg";
	public static final String FILE = "file";
	public static final String HAS_ERRORS = "hasErrors";
	public static final String END_PROCESS_DATE = "endProcessDate";
	public static final String ERROR_FILE = "errorFile";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String USER_ID = "userId";
	public static final String FLOW_ID = "flowId";
	public static final String FILE_NAME_ORIGIN = "fileNameOrigin";

}

