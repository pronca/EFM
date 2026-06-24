package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowVersionDO is a Querydsl query type for FlowVersionDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowVersionDO extends EntityPathBase<FlowVersionDO> {

    private static final long serialVersionUID = -1094775655L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowVersionDO flowVersionDO = new QFlowVersionDO("flowVersionDO");

    protected QFlowDO flow;

    protected QFlowVersionPrimaryKeyDO id;

    public final StringPath jsonSchema = createString("jsonSchema");

    protected QVersionDO version;

    public QFlowVersionDO(String variable) {
        this(FlowVersionDO.class, forVariable(variable), INITS);
    }

    public QFlowVersionDO(Path<? extends FlowVersionDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowVersionDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowVersionDO(PathMetadata metadata, PathInits inits) {
        this(FlowVersionDO.class, metadata, inits);
    }

    public QFlowVersionDO(Class<? extends FlowVersionDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
        this.id = inits.isInitialized("id") ? new QFlowVersionPrimaryKeyDO(forProperty("id")) : null;
        this.version = inits.isInitialized("version") ? new QVersionDO(forProperty("version")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public QFlowVersionPrimaryKeyDO id() {
        if (id == null) {
            id = new QFlowVersionPrimaryKeyDO(forProperty("id"));
        }
        return id;
    }

    public QVersionDO version() {
        if (version == null) {
            version = new QVersionDO(forProperty("version"));
        }
        return version;
    }

}

