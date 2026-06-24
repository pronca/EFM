package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfiloFlussiDO is a Querydsl query type for ProfiloFlussiDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfiloFlussiDO extends EntityPathBase<ProfiloFlussiDO> {

    private static final long serialVersionUID = -1156817444L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfiloFlussiDO profiloFlussiDO = new QProfiloFlussiDO("profiloFlussiDO");

    public final StringPath consolidamento = createString("consolidamento");

    protected QFlowDO flow;

    protected QProfiloFlussiPrimaryKeyDO id;

    public final StringPath organization = createString("organization");

    public final StringPath role = createString("role");

    public final StringPath site = createString("site");

    public QProfiloFlussiDO(String variable) {
        this(ProfiloFlussiDO.class, forVariable(variable), INITS);
    }

    public QProfiloFlussiDO(Path<? extends ProfiloFlussiDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfiloFlussiDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfiloFlussiDO(PathMetadata metadata, PathInits inits) {
        this(ProfiloFlussiDO.class, metadata, inits);
    }

    public QProfiloFlussiDO(Class<? extends ProfiloFlussiDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
        this.id = inits.isInitialized("id") ? new QProfiloFlussiPrimaryKeyDO(forProperty("id")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public QProfiloFlussiPrimaryKeyDO id() {
        if (id == null) {
            id = new QProfiloFlussiPrimaryKeyDO(forProperty("id"));
        }
        return id;
    }

}

