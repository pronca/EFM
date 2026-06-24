package it.eng.care.domain.flow.flowupload.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FlowSectionFileDO.class)
public abstract class FlowSectionFileDO_ {

	public static volatile SingularAttribute<FlowSectionFileDO, FlowFileUploadRequestDO> request;
	public static volatile SingularAttribute<FlowSectionFileDO, Date> uploadDate;
	public static volatile ListAttribute<FlowSectionFileDO, SectionFileDO> sectionFileList;
	public static volatile SingularAttribute<FlowSectionFileDO, String> uploadUsername;
	public static volatile SingularAttribute<FlowSectionFileDO, Integer> section;
	public static volatile SingularAttribute<FlowSectionFileDO, String> id;

	public static final String REQUEST = "request";
	public static final String UPLOAD_DATE = "uploadDate";
	public static final String SECTION_FILE_LIST = "sectionFileList";
	public static final String UPLOAD_USERNAME = "uploadUsername";
	public static final String SECTION = "section";
	public static final String ID = "id";

}

