package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonitorSdoXlFileDO.class)
public abstract class MonitorSdoXlFileDO_ {

	public static volatile SingularAttribute<MonitorSdoXlFileDO, String> nosologico;
	public static volatile SingularAttribute<MonitorSdoXlFileDO, String> idEstrazione;
	public static volatile SingularAttribute<MonitorSdoXlFileDO, byte[]> xml;
	public static volatile SingularAttribute<MonitorSdoXlFileDO, String> presidio;
	public static volatile SingularAttribute<MonitorSdoXlFileDO, String> id;
	public static volatile SingularAttribute<MonitorSdoXlFileDO, String> idFlusso;

	public static final String NOSOLOGICO = "nosologico";
	public static final String ID_ESTRAZIONE = "idEstrazione";
	public static final String XML = "xml";
	public static final String PRESIDIO = "presidio";
	public static final String ID = "id";
	public static final String ID_FLUSSO = "idFlusso";

}

