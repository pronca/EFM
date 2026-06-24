package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlowVersionPrimaryKeyDO is a Querydsl query type for FlowVersionPrimaryKeyDO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFlowVersionPrimaryKeyDO extends BeanPath<FlowVersionPrimaryKeyDO> {

    private static final long serialVersionUID = 428678390L;

    public static final QFlowVersionPrimaryKeyDO flowVersionPrimaryKeyDO = new QFlowVersionPrimaryKeyDO("flowVersionPrimaryKeyDO");

    public final StringPath flow = createString("flow");

    public final StringPath version = createString("version");

    public QFlowVersionPrimaryKeyDO(String variable) {
        super(FlowVersionPrimaryKeyDO.class, forVariable(variable));
    }

    public QFlowVersionPrimaryKeyDO(Path<? extends FlowVersionPrimaryKeyDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlowVersionPrimaryKeyDO(PathMetadata metadata) {
        super(FlowVersionPrimaryKeyDO.class, metadata);
    }

}

