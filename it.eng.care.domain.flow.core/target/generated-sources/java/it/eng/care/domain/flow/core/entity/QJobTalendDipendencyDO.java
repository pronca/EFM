package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobTalendDipendencyDO is a Querydsl query type for JobTalendDipendencyDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobTalendDipendencyDO extends EntityPathBase<JobTalendDipendencyDO> {

    private static final long serialVersionUID = -2142993289L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobTalendDipendencyDO jobTalendDipendencyDO = new QJobTalendDipendencyDO("jobTalendDipendencyDO");

    public final StringPath dependencyType = createString("dependencyType");

    public final StringPath id = createString("id");

    public final ArrayPath<byte[], Byte> jar = createArray("jar", byte[].class);

    protected QJobTalendFileDO jobTalendFile;

    public final StringPath name = createString("name");

    public QJobTalendDipendencyDO(String variable) {
        this(JobTalendDipendencyDO.class, forVariable(variable), INITS);
    }

    public QJobTalendDipendencyDO(Path<? extends JobTalendDipendencyDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobTalendDipendencyDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobTalendDipendencyDO(PathMetadata metadata, PathInits inits) {
        this(JobTalendDipendencyDO.class, metadata, inits);
    }

    public QJobTalendDipendencyDO(Class<? extends JobTalendDipendencyDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobTalendFile = inits.isInitialized("jobTalendFile") ? new QJobTalendFileDO(forProperty("jobTalendFile"), inits.get("jobTalendFile")) : null;
    }

    public QJobTalendFileDO jobTalendFile() {
        if (jobTalendFile == null) {
            jobTalendFile = new QJobTalendFileDO(forProperty("jobTalendFile"));
        }
        return jobTalendFile;
    }

}

