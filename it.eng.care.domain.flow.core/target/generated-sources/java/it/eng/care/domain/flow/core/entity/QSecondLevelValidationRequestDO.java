package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSecondLevelValidationRequestDO is a Querydsl query type for SecondLevelValidationRequestDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSecondLevelValidationRequestDO extends EntityPathBase<SecondLevelValidationRequestDO> {

    private static final long serialVersionUID = 2066889485L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSecondLevelValidationRequestDO secondLevelValidationRequestDO = new QSecondLevelValidationRequestDO("secondLevelValidationRequestDO");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    protected QFlowDO flow;

    public final StringPath id = createString("id");

    public final StringPath month = createString("month");

    public final DateTimePath<java.util.Date> processingDate = createDateTime("processingDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public final StringPath userId = createString("userId");

    public final StringPath year = createString("year");

    public QSecondLevelValidationRequestDO(String variable) {
        this(SecondLevelValidationRequestDO.class, forVariable(variable), INITS);
    }

    public QSecondLevelValidationRequestDO(Path<? extends SecondLevelValidationRequestDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSecondLevelValidationRequestDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSecondLevelValidationRequestDO(PathMetadata metadata, PathInits inits) {
        this(SecondLevelValidationRequestDO.class, metadata, inits);
    }

    public QSecondLevelValidationRequestDO(Class<? extends SecondLevelValidationRequestDO> type, PathMetadata metadata, PathInits inits) {
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

