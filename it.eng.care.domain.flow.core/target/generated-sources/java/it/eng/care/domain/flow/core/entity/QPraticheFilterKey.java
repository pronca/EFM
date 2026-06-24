package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPraticheFilterKey is a Querydsl query type for PraticheFilterKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPraticheFilterKey extends BeanPath<PraticheFilterKey> {

    private static final long serialVersionUID = -2055095521L;

    public static final QPraticheFilterKey praticheFilterKey = new QPraticheFilterKey("praticheFilterKey");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath dashBoard = createString("dashBoard");

    public final StringPath filterName = createString("filterName");

    public QPraticheFilterKey(String variable) {
        super(PraticheFilterKey.class, forVariable(variable));
    }

    public QPraticheFilterKey(Path<? extends PraticheFilterKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPraticheFilterKey(PathMetadata metadata) {
        super(PraticheFilterKey.class, metadata);
    }

}

