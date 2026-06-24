package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowExportMessageDetailsDO is a Querydsl query type for FlowExportMessageDetailsDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowExportMessageDetailsDO extends EntityPathBase<FlowExportMessageDetailsDO> {

    private static final long serialVersionUID = -397671324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowExportMessageDetailsDO flowExportMessageDetailsDO = new QFlowExportMessageDetailsDO("flowExportMessageDetailsDO");

    public final StringPath errorValue = createString("errorValue");

    protected QFlowExportRequestDO flowExportRequest;

    public final StringPath id = createString("id");

    public QFlowExportMessageDetailsDO(String variable) {
        this(FlowExportMessageDetailsDO.class, forVariable(variable), INITS);
    }

    public QFlowExportMessageDetailsDO(Path<? extends FlowExportMessageDetailsDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowExportMessageDetailsDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowExportMessageDetailsDO(PathMetadata metadata, PathInits inits) {
        this(FlowExportMessageDetailsDO.class, metadata, inits);
    }

    public QFlowExportMessageDetailsDO(Class<? extends FlowExportMessageDetailsDO> type, PathMetadata metadata, PathInits inits) {
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

