package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLockedMessageDO is a Querydsl query type for LockedMessageDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLockedMessageDO extends EntityPathBase<LockedMessageDO> {

    private static final long serialVersionUID = 25650348L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLockedMessageDO lockedMessageDO = new QLockedMessageDO("lockedMessageDO");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateProcessed = createDateTime("dateProcessed", java.util.Date.class);

    public final StringPath extractionId = createString("extractionId");

    public final StringPath extrId = createString("extrId");

    protected QFlowDO flowId;

    public final StringPath flowName = createString("flowName");

    public final StringPath id = createString("id");

    public final StringPath keyMessage = createString("keyMessage");

    public final StringPath message = createString("message");

    public final StringPath status = createString("status");

    public QLockedMessageDO(String variable) {
        this(LockedMessageDO.class, forVariable(variable), INITS);
    }

    public QLockedMessageDO(Path<? extends LockedMessageDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLockedMessageDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLockedMessageDO(PathMetadata metadata, PathInits inits) {
        this(LockedMessageDO.class, metadata, inits);
    }

    public QLockedMessageDO(Class<? extends LockedMessageDO> type, PathMetadata metadata, PathInits inits) {
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

