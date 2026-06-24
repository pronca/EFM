package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowExportReqFileTalendDO.class)
public abstract class FlowExportReqFileTalendDO_ {

	public static volatile SingularAttribute<FlowExportReqFileTalendDO, String> nomeFile;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, FlowExportRequestDO> flowExportRequest;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, byte[]> log;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, byte[]> xml;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, String> codReg;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, String> id;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, String> fileExt;
	public static volatile SingularAttribute<FlowExportReqFileTalendDO, String> sezApp;

	public static final String NOME_FILE = "nomeFile";
	public static final String FLOW_EXPORT_REQUEST = "flowExportRequest";
	public static final String LOG = "log";
	public static final String XML = "xml";
	public static final String COD_REG = "codReg";
	public static final String ID = "id";
	public static final String FILE_EXT = "fileExt";
	public static final String SEZ_APP = "sezApp";

}

