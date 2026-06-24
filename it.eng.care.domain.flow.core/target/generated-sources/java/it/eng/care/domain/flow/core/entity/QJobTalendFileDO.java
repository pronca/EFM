package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobTalendFileDO is a Querydsl query type for JobTalendFileDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobTalendFileDO extends EntityPathBase<JobTalendFileDO> {

    private static final long serialVersionUID = 613473572L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobTalendFileDO jobTalendFileDO = new QJobTalendFileDO("jobTalendFileDO");

    public final StringPath id = createString("id");

    public final ArrayPath<byte[], Byte> job = createArray("job", byte[].class);

    protected QJobTalendDO jobTalend;

    public QJobTalendFileDO(String variable) {
        this(JobTalendFileDO.class, forVariable(variable), INITS);
    }

    public QJobTalendFileDO(Path<? extends JobTalendFileDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobTalendFileDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobTalendFileDO(PathMetadata metadata, PathInits inits) {
        this(JobTalendFileDO.class, metadata, inits);
    }

    public QJobTalendFileDO(Class<? extends JobTalendFileDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobTalend = inits.isInitialized("jobTalend") ? new QJobTalendDO(forProperty("jobTalend"), inits.get("jobTalend")) : null;
    }

    public QJobTalendDO jobTalend() {
        if (jobTalend == null) {
            jobTalend = new QJobTalendDO(forProperty("jobTalend"));
        }
        return jobTalend;
    }

}

