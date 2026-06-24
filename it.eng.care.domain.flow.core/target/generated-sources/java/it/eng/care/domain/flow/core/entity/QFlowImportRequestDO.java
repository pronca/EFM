package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowImportRequestDO is a Querydsl query type for FlowImportRequestDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowImportRequestDO extends EntityPathBase<FlowImportRequestDO> {

    private static final long serialVersionUID = 1625359851L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowImportRequestDO flowImportRequestDO = new QFlowImportRequestDO("flowImportRequestDO");

    public final StringPath aziendeProfiloFlussi = createString("aziendeProfiloFlussi");

    public final SetPath<FlowImportRequestFieldDateDO, QFlowImportRequestFieldDateDO> dateFields = this.<FlowImportRequestFieldDateDO, QFlowImportRequestFieldDateDO>createSet("dateFields", FlowImportRequestFieldDateDO.class, QFlowImportRequestFieldDateDO.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> endExtractionDate = createDateTime("endExtractionDate", java.util.Date.class);

    protected QFlowDO flow;

    public final StringPath id = createString("id");

    public final StringPath importingUser = createString("importingUser");

    public final StringPath record = createString("record");

    public final DateTimePath<java.util.Date> requestDate = createDateTime("requestDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> startExtractionDate = createDateTime("startExtractionDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public final StringPath validationStatus = createString("validationStatus");

    protected QVersionDO version;

    public QFlowImportRequestDO(String variable) {
        this(FlowImportRequestDO.class, forVariable(variable), INITS);
    }

    public QFlowImportRequestDO(Path<? extends FlowImportRequestDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowImportRequestDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowImportRequestDO(PathMetadata metadata, PathInits inits) {
        this(FlowImportRequestDO.class, metadata, inits);
    }

    public QFlowImportRequestDO(Class<? extends FlowImportRequestDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
        this.version = inits.isInitialized("version") ? new QVersionDO(forProperty("version")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public QVersionDO version() {
        if (version == null) {
            version = new QVersionDO(forProperty("version"));
        }
        return version;
    }

}

