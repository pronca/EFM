package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowImportRequestFieldDateDO is a Querydsl query type for FlowImportRequestFieldDateDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowImportRequestFieldDateDO extends EntityPathBase<FlowImportRequestFieldDateDO> {

    private static final long serialVersionUID = 698541267L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowImportRequestFieldDateDO flowImportRequestFieldDateDO = new QFlowImportRequestFieldDateDO("flowImportRequestFieldDateDO");

    public final DateTimePath<java.util.Date> date_from = createDateTime("date_from", java.util.Date.class);

    public final DateTimePath<java.util.Date> date_to = createDateTime("date_to", java.util.Date.class);

    protected QFlowImportRequestDO flowImportRequest;

    public final StringPath id = createString("id");

    public final StringPath id_field = createString("id_field");

    public QFlowImportRequestFieldDateDO(String variable) {
        this(FlowImportRequestFieldDateDO.class, forVariable(variable), INITS);
    }

    public QFlowImportRequestFieldDateDO(Path<? extends FlowImportRequestFieldDateDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowImportRequestFieldDateDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowImportRequestFieldDateDO(PathMetadata metadata, PathInits inits) {
        this(FlowImportRequestFieldDateDO.class, metadata, inits);
    }

    public QFlowImportRequestFieldDateDO(Class<? extends FlowImportRequestFieldDateDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flowImportRequest = inits.isInitialized("flowImportRequest") ? new QFlowImportRequestDO(forProperty("flowImportRequest"), inits.get("flowImportRequest")) : null;
    }

    public QFlowImportRequestDO flowImportRequest() {
        if (flowImportRequest == null) {
            flowImportRequest = new QFlowImportRequestDO(forProperty("flowImportRequest"));
        }
        return flowImportRequest;
    }

}

