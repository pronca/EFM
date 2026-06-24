package it.eng.care.domain.flow.flowupload.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowFileUploadRequestDO is a Querydsl query type for FlowFileUploadRequestDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowFileUploadRequestDO extends EntityPathBase<FlowFileUploadRequestDO> {

    private static final long serialVersionUID = 21240203L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowFileUploadRequestDO flowFileUploadRequestDO = new QFlowFileUploadRequestDO("flowFileUploadRequestDO");

    public final StringPath aziendeLoadedInFile = createString("aziendeLoadedInFile");

    public final StringPath aziendeProfiloFlussi = createString("aziendeProfiloFlussi");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final ListPath<FlowFileUploadRequestErrorDO, QFlowFileUploadRequestErrorDO> errors = this.<FlowFileUploadRequestErrorDO, QFlowFileUploadRequestErrorDO>createList("errors", FlowFileUploadRequestErrorDO.class, QFlowFileUploadRequestErrorDO.class, PathInits.DIRECT2);

    public final ListPath<FlowSectionFileDO, QFlowSectionFileDO> files = this.<FlowSectionFileDO, QFlowSectionFileDO>createList("files", FlowSectionFileDO.class, QFlowSectionFileDO.class, PathInits.DIRECT2);

    protected it.eng.care.domain.flow.core.entity.QFlowDO flow;

    public final StringPath id = createString("id");

    public final StringPath status = createString("status");

    public final StringPath username = createString("username");

    public final DateTimePath<java.util.Date> validationDate = createDateTime("validationDate", java.util.Date.class);

    protected it.eng.care.domain.flow.core.entity.QVersionDO version;

    public QFlowFileUploadRequestDO(String variable) {
        this(FlowFileUploadRequestDO.class, forVariable(variable), INITS);
    }

    public QFlowFileUploadRequestDO(Path<? extends FlowFileUploadRequestDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowFileUploadRequestDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowFileUploadRequestDO(PathMetadata metadata, PathInits inits) {
        this(FlowFileUploadRequestDO.class, metadata, inits);
    }

    public QFlowFileUploadRequestDO(Class<? extends FlowFileUploadRequestDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new it.eng.care.domain.flow.core.entity.QFlowDO(forProperty("flow")) : null;
        this.version = inits.isInitialized("version") ? new it.eng.care.domain.flow.core.entity.QVersionDO(forProperty("version")) : null;
    }

    public it.eng.care.domain.flow.core.entity.QFlowDO flow() {
        if (flow == null) {
            flow = new it.eng.care.domain.flow.core.entity.QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public it.eng.care.domain.flow.core.entity.QVersionDO version() {
        if (version == null) {
            version = new it.eng.care.domain.flow.core.entity.QVersionDO(forProperty("version"));
        }
        return version;
    }

}

