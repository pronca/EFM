package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowExportReqFileTalendDO is a Querydsl query type for FlowExportReqFileTalendDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowExportReqFileTalendDO extends EntityPathBase<FlowExportReqFileTalendDO> {

    private static final long serialVersionUID = -999030365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowExportReqFileTalendDO flowExportReqFileTalendDO = new QFlowExportReqFileTalendDO("flowExportReqFileTalendDO");

    public final StringPath codReg = createString("codReg");

    public final StringPath fileExt = createString("fileExt");

    protected QFlowExportRequestDO flowExportRequest;

    public final StringPath id = createString("id");

    public final ArrayPath<byte[], Byte> log = createArray("log", byte[].class);

    public final StringPath nomeFile = createString("nomeFile");

    public final StringPath sezApp = createString("sezApp");

    public final ArrayPath<byte[], Byte> xml = createArray("xml", byte[].class);

    public QFlowExportReqFileTalendDO(String variable) {
        this(FlowExportReqFileTalendDO.class, forVariable(variable), INITS);
    }

    public QFlowExportReqFileTalendDO(Path<? extends FlowExportReqFileTalendDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowExportReqFileTalendDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowExportReqFileTalendDO(PathMetadata metadata, PathInits inits) {
        this(FlowExportReqFileTalendDO.class, metadata, inits);
    }

    public QFlowExportReqFileTalendDO(Class<? extends FlowExportReqFileTalendDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flowExportRequest = inits.isInitialized("flowExportRequest") ? new QFlowExportRequestDO(forProperty("flowExportRequest"), inits.get("flowExportRequest")) : null;
    }

    public QFlowExportRequestDO flowExportRequest() {
        if (flowExportRequest == null) {
            flowExportRequest = new QFlowExportRequestDO(forProperty("flowExportRequest"));
        }
        return flowExportRequest;
    }

}

