package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVersionDO is a Querydsl query type for VersionDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVersionDO extends EntityPathBase<VersionDO> {

    private static final long serialVersionUID = -33194969L;

    public static final QVersionDO versionDO = new QVersionDO("versionDO");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final SetPath<FlowVersionDO, QFlowVersionDO> flows = this.<FlowVersionDO, QFlowVersionDO>createSet("flows", FlowVersionDO.class, QFlowVersionDO.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath version = createString("version");

    public QVersionDO(String variable) {
        super(VersionDO.class, forVariable(variable));
    }

    public QVersionDO(Path<? extends VersionDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVersionDO(PathMetadata metadata) {
        super(VersionDO.class, metadata);
    }

}

