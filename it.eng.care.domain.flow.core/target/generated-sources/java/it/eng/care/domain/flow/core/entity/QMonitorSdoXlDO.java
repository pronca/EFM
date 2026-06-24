package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonitorSdoXlDO is a Querydsl query type for MonitorSdoXlDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMonitorSdoXlDO extends EntityPathBase<MonitorSdoXlDO> {

    private static final long serialVersionUID = -1418249505L;

    public static final QMonitorSdoXlDO monitorSdoXlDO = new QMonitorSdoXlDO("monitorSdoXlDO");

    public final StringPath azienda = createString("azienda");

    public final DateTimePath<java.util.Date> dataDimissione = createDateTime("dataDimissione", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataInvio = createDateTime("dataInvio", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataRicezione = createDateTime("dataRicezione", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataRicovero = createDateTime("dataRicovero", java.util.Date.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath esito = createString("esito");

    public final StringPath flusso = createString("flusso");

    public final StringPath flussoId = createString("flussoId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath idEstrazione = createString("idEstrazione");

    public final StringPath nominativo = createString("nominativo");

    public final StringPath nosologico = createString("nosologico");

    public final StringPath operazione = createString("operazione");

    public final StringPath presidio = createString("presidio");

    public final StringPath protocolloSio = createString("protocolloSio");

    public final StringPath reparto = createString("reparto");

    public final StringPath trasmissione = createString("trasmissione");

    public QMonitorSdoXlDO(String variable) {
        super(MonitorSdoXlDO.class, forVariable(variable));
    }

    public QMonitorSdoXlDO(Path<? extends MonitorSdoXlDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitorSdoXlDO(PathMetadata metadata) {
        super(MonitorSdoXlDO.class, metadata);
    }

}

