package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegionErrorsDO is a Querydsl query type for RegionErrorsDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegionErrorsDO extends EntityPathBase<RegionErrorsDO> {

    private static final long serialVersionUID = 1901776390L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegionErrorsDO regionErrorsDO = new QRegionErrorsDO("regionErrorsDO");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateSent = createDateTime("dateSent", java.util.Date.class);

    public final StringPath extrId = createString("extrId");

    protected QFlowDO flowId;

    public final StringPath id = createString("id");

    public final StringPath json = createString("json");

    public final StringPath keyMessage = createString("keyMessage");

    public final NumberPath<Integer> nretry = createNumber("nretry", Integer.class);

    public final StringPath regionStatus = createString("regionStatus");

    public final StringPath sendStatus = createString("sendStatus");

    public QRegionErrorsDO(String variable) {
        this(RegionErrorsDO.class, forVariable(variable), INITS);
    }

    public QRegionErrorsDO(Path<? extends RegionErrorsDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegionErrorsDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegionErrorsDO(PathMetadata metadata, PathInits inits) {
        this(RegionErrorsDO.class, metadata, inits);
    }

    public QRegionErrorsDO(Class<? extends RegionErrorsDO> type, PathMetadata metadata, PathInits inits) {
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

