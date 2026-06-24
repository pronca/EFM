package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContextConfigurationDO is a Querydsl query type for ContextConfigurationDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContextConfigurationDO extends EntityPathBase<ContextConfigurationDO> {

    private static final long serialVersionUID = -304261106L;

    public static final QContextConfigurationDO contextConfigurationDO = new QContextConfigurationDO("contextConfigurationDO");

    public final StringPath activity = createString("activity");

    public final StringPath flow = createString("flow");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath param = createString("param");

    public final StringPath section = createString("section");

    public QContextConfigurationDO(String variable) {
        super(ContextConfigurationDO.class, forVariable(variable));
    }

    public QContextConfigurationDO(Path<? extends ContextConfigurationDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContextConfigurationDO(PathMetadata metadata) {
        super(ContextConfigurationDO.class, metadata);
    }

}

