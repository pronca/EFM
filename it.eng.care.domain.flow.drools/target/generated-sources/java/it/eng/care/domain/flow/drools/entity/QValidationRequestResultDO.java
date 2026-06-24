package it.eng.care.domain.flow.drools.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QValidationRequestResultDO is a Querydsl query type for ValidationRequestResultDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QValidationRequestResultDO extends EntityPathBase<ValidationRequestResultDO> {

    private static final long serialVersionUID = 1077137804L;

    public static final QValidationRequestResultDO validationRequestResultDO = new QValidationRequestResultDO("validationRequestResultDO");

    public final NumberPath<Integer> errors = createNumber("errors", Integer.class);

    public final StringPath importingRequestId = createString("importingRequestId");

    public final DateTimePath<java.util.Date> validationDate = createDateTime("validationDate", java.util.Date.class);

    public final NumberPath<Integer> valids = createNumber("valids", Integer.class);

    public final StringPath vrId = createString("vrId");

    public final NumberPath<Integer> warnings = createNumber("warnings", Integer.class);

    public QValidationRequestResultDO(String variable) {
        super(ValidationRequestResultDO.class, forVariable(variable));
    }

    public QValidationRequestResultDO(Path<? extends ValidationRequestResultDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QValidationRequestResultDO(PathMetadata metadata) {
        super(ValidationRequestResultDO.class, metadata);
    }

}

