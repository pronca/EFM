package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResetValidationRequestDO is a Querydsl query type for ResetValidationRequestDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResetValidationRequestDO extends EntityPathBase<ResetValidationRequestDO> {

    private static final long serialVersionUID = -101529650L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResetValidationRequestDO resetValidationRequestDO = new QResetValidationRequestDO("resetValidationRequestDO");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    protected QFlowDO flow;

    public final StringPath id = createString("id");

    public final StringPath month = createString("month");

    public final DateTimePath<java.util.Date> processingDate = createDateTime("processingDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public final StringPath userId = createString("userId");

    public final StringPath year = createString("year");

    public QResetValidationRequestDO(String variable) {
        this(ResetValidationRequestDO.class, forVariable(variable), INITS);
    }

    public QResetValidationRequestDO(Path<? extends ResetValidationRequestDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResetValidationRequestDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResetValidationRequestDO(PathMetadata metadata, PathInits inits) {
        this(ResetValidationRequestDO.class, metadata, inits);
    }

    public QResetValidationRequestDO(Class<? extends ResetValidationRequestDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

}

