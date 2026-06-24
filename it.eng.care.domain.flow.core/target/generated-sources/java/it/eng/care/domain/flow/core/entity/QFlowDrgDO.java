package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowDrgDO is a Querydsl query type for FlowDrgDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowDrgDO extends EntityPathBase<FlowDrgDO> {

    private static final long serialVersionUID = -151038950L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowDrgDO flowDrgDO = new QFlowDrgDO("flowDrgDO");

    public final StringPath error = createString("error");

    protected QFlowExportRequestDO extraction;

    public final StringPath id = createString("id");

    public final StringPath numPratiche = createString("numPratiche");

    public final DatePath<java.util.Date> returnDate = createDate("returnDate", java.util.Date.class);

    public final DatePath<java.util.Date> sendDate = createDate("sendDate", java.util.Date.class);

    public final StringPath state = createString("state");

    public QFlowDrgDO(String variable) {
        this(FlowDrgDO.class, forVariable(variable), INITS);
    }

    public QFlowDrgDO(Path<? extends FlowDrgDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowDrgDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowDrgDO(PathMetadata metadata, PathInits inits) {
        this(FlowDrgDO.class, metadata, inits);
    }

    public QFlowDrgDO(Class<? extends FlowDrgDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.extraction = inits.isInitialized("extraction") ? new QFlowExportRequestDO(forProperty("extraction"), inits.get("extraction")) : null;
    }

    public QFlowExportRequestDO extraction() {
        if (extraction == null) {
            extraction = new QFlowExportRequestDO(forProperty("extraction"));
        }
        return extraction;
    }

}

