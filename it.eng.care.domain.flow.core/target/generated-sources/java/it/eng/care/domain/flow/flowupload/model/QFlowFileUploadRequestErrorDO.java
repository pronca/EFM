package it.eng.care.domain.flow.flowupload.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowFileUploadRequestErrorDO is a Querydsl query type for FlowFileUploadRequestErrorDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowFileUploadRequestErrorDO extends EntityPathBase<FlowFileUploadRequestErrorDO> {

    private static final long serialVersionUID = 917479283L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowFileUploadRequestErrorDO flowFileUploadRequestErrorDO = new QFlowFileUploadRequestErrorDO("flowFileUploadRequestErrorDO");

    public final StringPath error = createString("error");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> indexRecord = createNumber("indexRecord", Integer.class);

    protected QFlowFileUploadRequestDO request;

    public final NumberPath<Integer> section = createNumber("section", Integer.class);

    public QFlowFileUploadRequestErrorDO(String variable) {
        this(FlowFileUploadRequestErrorDO.class, forVariable(variable), INITS);
    }

    public QFlowFileUploadRequestErrorDO(Path<? extends FlowFileUploadRequestErrorDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowFileUploadRequestErrorDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowFileUploadRequestErrorDO(PathMetadata metadata, PathInits inits) {
        this(FlowFileUploadRequestErrorDO.class, metadata, inits);
    }

    public QFlowFileUploadRequestErrorDO(Class<? extends FlowFileUploadRequestErrorDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.request = inits.isInitialized("request") ? new QFlowFileUploadRequestDO(forProperty("request"), inits.get("request")) : null;
    }

    public QFlowFileUploadRequestDO request() {
        if (request == null) {
            request = new QFlowFileUploadRequestDO(forProperty("request"));
        }
        return request;
    }

}

