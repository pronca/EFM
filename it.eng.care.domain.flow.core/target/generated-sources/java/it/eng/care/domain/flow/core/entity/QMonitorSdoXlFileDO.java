package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonitorSdoXlFileDO is a Querydsl query type for MonitorSdoXlFileDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMonitorSdoXlFileDO extends EntityPathBase<MonitorSdoXlFileDO> {

    private static final long serialVersionUID = 1520132987L;

    public static final QMonitorSdoXlFileDO monitorSdoXlFileDO = new QMonitorSdoXlFileDO("monitorSdoXlFileDO");

    public final StringPath id = createString("id");

    public final StringPath idEstrazione = createString("idEstrazione");

    public final StringPath idFlusso = createString("idFlusso");

    public final StringPath nosologico = createString("nosologico");

    public final StringPath presidio = createString("presidio");

    public final ArrayPath<byte[], Byte> xml = createArray("xml", byte[].class);

    public QMonitorSdoXlFileDO(String variable) {
        super(MonitorSdoXlFileDO.class, forVariable(variable));
    }

    public QMonitorSdoXlFileDO(Path<? extends MonitorSdoXlFileDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitorSdoXlFileDO(PathMetadata metadata) {
        super(MonitorSdoXlFileDO.class, metadata);
    }

}

