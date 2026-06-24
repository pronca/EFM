package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSendDrgDO is a Querydsl query type for SendDrgDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSendDrgDO extends EntityPathBase<SendDrgDO> {

    private static final long serialVersionUID = 1375628800L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSendDrgDO sendDrgDO = new QSendDrgDO("sendDrgDO");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateSent = createDateTime("dateSent", java.util.Date.class);

    public final StringPath extrId = createString("extrId");

    protected QFlowDO flowId;

    public final StringPath id = createString("id");

    public final StringPath json = createString("json");

    public final NumberPath<Integer> nretry = createNumber("nretry", Integer.class);

    public final StringPath status = createString("status");

    public QSendDrgDO(String variable) {
        this(SendDrgDO.class, forVariable(variable), INITS);
    }

    public QSendDrgDO(Path<? extends SendDrgDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSendDrgDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSendDrgDO(PathMetadata metadata, PathInits inits) {
        this(SendDrgDO.class, metadata, inits);
    }

    public QSendDrgDO(Class<? extends SendDrgDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flowId = inits.isInitialized("flowId") ? new QFlowDO(forProperty("flowId")) : null;
    }

    public QFlowDO flowId() {
        if (flowId == null) {
            flowId = new QFlowDO(forProperty("flowId"));
        }
        return flowId;
    }

}

