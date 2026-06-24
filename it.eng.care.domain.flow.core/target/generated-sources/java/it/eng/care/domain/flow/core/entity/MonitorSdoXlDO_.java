package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonitorSdoXlDO.class)
public abstract class MonitorSdoXlDO_ {

	public static volatile SingularAttribute<MonitorSdoXlDO, String> nosologico;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> idEstrazione;
	public static volatile SingularAttribute<MonitorSdoXlDO, Date> dataRicovero;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> operazione;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> trasmissione;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> flussoId;
	public static volatile SingularAttribute<MonitorSdoXlDO, Date> dataRicezione;
	public static volatile SingularAttribute<MonitorSdoXlDO, Date> dataInvio;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> esito;
	public static volatile SingularAttribute<MonitorSdoXlDO, Boolean> deleted;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> azienda;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> nominativo;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> flusso;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> presidio;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> protocolloSio;
	public static volatile SingularAttribute<MonitorSdoXlDO, Integer> id;
	public static volatile SingularAttribute<MonitorSdoXlDO, Date> dataDimissione;
	public static volatile SingularAttribute<MonitorSdoXlDO, String> reparto;

	public static final String NOSOLOGICO = "nosologico";
	public static final String ID_ESTRAZIONE = "idEstrazione";
	public static final String DATA_RICOVERO = "dataRicovero";
	public static final String OPERAZIONE = "operazione";
	public static final String TRASMISSIONE = "trasmissione";
	public static final String FLUSSO_ID = "flussoId";
	public static final String DATA_RICEZIONE = "dataRicezione";
	public static final String DATA_INVIO = "dataInvio";
	public static final String ESITO = "esito";
	public static final String DELETED = "deleted";
	public static final String AZIENDA = "azienda";
	public static final String NOMINATIVO = "nominativo";
	public static final String FLUSSO = "flusso";
	public static final String PRESIDIO = "presidio";
	public static final String PROTOCOLLO_SIO = "protocolloSio";
	public static final String ID = "id";
	public static final String DATA_DIMISSIONE = "dataDimissione";
	public static final String REPARTO = "reparto";

}

