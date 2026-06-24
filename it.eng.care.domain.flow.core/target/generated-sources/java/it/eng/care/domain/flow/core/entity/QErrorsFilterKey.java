package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QErrorsFilterKey is a Querydsl query type for ErrorsFilterKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QErrorsFilterKey extends BeanPath<ErrorsFilterKey> {

    private static final long serialVersionUID = 1271622240L;

    public static final QErrorsFilterKey errorsFilterKey = new QErrorsFilterKey("errorsFilterKey");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath dashBoard = createString("dashBoard");

    public final StringPath filterName = createString("filterName");

    public QErrorsFilterKey(String variable) {
        super(ErrorsFilterKey.class, forVariable(variable));
    }

    public QErrorsFilterKey(Path<? extends ErrorsFilterKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QErrorsFilterKey(PathMetadata metadata) {
        super(ErrorsFilterKey.class, metadata);
    }

}

