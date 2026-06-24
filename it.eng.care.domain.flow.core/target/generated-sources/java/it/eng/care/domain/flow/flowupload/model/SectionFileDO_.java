package it.eng.care.domain.flow.flowupload.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SectionFileDO.class)
public abstract class SectionFileDO_ {

	public static volatile SingularAttribute<SectionFileDO, String> extension;
	public static volatile SingularAttribute<SectionFileDO, byte[]> file;
	public static volatile SingularAttribute<SectionFileDO, FlowSectionFileDO> section;
	public static volatile SingularAttribute<SectionFileDO, String> id;
	public static volatile SingularAttribute<SectionFileDO, byte[]> wellFormedFile;

	public static final String EXTENSION = "extension";
	public static final String FILE = "file";
	public static final String SECTION = "section";
	public static final String ID = "id";
	public static final String WELL_FORMED_FILE = "wellFormedFile";

}

