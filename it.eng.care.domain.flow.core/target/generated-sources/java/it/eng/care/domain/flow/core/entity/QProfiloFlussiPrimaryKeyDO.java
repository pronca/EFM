package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfiloFlussiPrimaryKeyDO is a Querydsl query type for ProfiloFlussiPrimaryKeyDO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProfiloFlussiPrimaryKeyDO extends BeanPath<ProfiloFlussiPrimaryKeyDO> {

    private static final long serialVersionUID = 772011641L;

    public static final QProfiloFlussiPrimaryKeyDO profiloFlussiPrimaryKeyDO = new QProfiloFlussiPrimaryKeyDO("profiloFlussiPrimaryKeyDO");

    public final StringPath flow = createString("flow");

    public final StringPath organization = createString("organization");

    public final StringPath role = createString("role");

    public final StringPath site = createString("site");

    public QProfiloFlussiPrimaryKeyDO(String variable) {
        super(ProfiloFlussiPrimaryKeyDO.class, forVariable(variable));
    }

    public QProfiloFlussiPrimaryKeyDO(Path<? extends ProfiloFlussiPrimaryKeyDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfiloFlussiPrimaryKeyDO(PathMetadata metadata) {
        super(ProfiloFlussiPrimaryKeyDO.class, metadata);
    }

}

