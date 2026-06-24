package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QValidationErrorsDO is a Querydsl query type for ValidationErrorsDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QValidationErrorsDO extends EntityPathBase<ValidationErrorsDO> {

    private static final long serialVersionUID = -2024711029L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QValidationErrorsDO validationErrorsDO = new QValidationErrorsDO("validationErrorsDO");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateSent = createDateTime("dateSent", java.util.Date.class);

    public final StringPath extrId = createString("extrId");

    protected QFlowDO flowId;

    public final StringPath id = createString("id");

    public final StringPath json = createString("json");

    public final NumberPath<Integer> nretry = createNumber("nretry", Integer.class);

    public final StringPath status = createString("status");

    public QValidationErrorsDO(String variable) {
        this(ValidationErrorsDO.class, forVariable(variable), INITS);
    }

    public QValidationErrorsDO(Path<? extends ValidationErrorsDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QValidationErrorsDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QValidationErrorsDO(PathMetadata metadata, PathInits inits) {
        this(ValidationErrorsDO.class, metadata, inits);
    }

    public QValidationErrorsDO(Class<? extends ValidationErrorsDO> type, PathMetadata metadata, PathInits inits) {
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

