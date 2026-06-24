package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobTalendDO is a Querydsl query type for JobTalendDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobTalendDO extends EntityPathBase<JobTalendDO> {

    private static final long serialVersionUID = -1653214968L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobTalendDO jobTalendDO = new QJobTalendDO("jobTalendDO");

    public final StringPath className = createString("className");

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath description = createString("description");

    protected QFlowDO flow;

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> lastUpdateDate = createDateTime("lastUpdateDate", java.util.Date.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath name = createString("name");

    public final StringPath packageJob = createString("packageJob");

    public final StringPath type = createString("type");

    protected QVersionDO version;

    public QJobTalendDO(String variable) {
        this(JobTalendDO.class, forVariable(variable), INITS);
    }

    public QJobTalendDO(Path<? extends JobTalendDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobTalendDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobTalendDO(PathMetadata metadata, PathInits inits) {
        this(JobTalendDO.class, metadata, inits);
    }

    public QJobTalendDO(Class<? extends JobTalendDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
        this.version = inits.isInitialized("version") ? new QVersionDO(forProperty("version")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public QVersionDO version() {
        if (version == null) {
            version = new QVersionDO(forProperty("version"));
        }
        return version;
    }

}

