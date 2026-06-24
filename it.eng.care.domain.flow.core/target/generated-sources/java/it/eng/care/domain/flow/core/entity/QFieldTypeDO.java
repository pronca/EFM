package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFieldTypeDO is a Querydsl query type for FieldTypeDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFieldTypeDO extends EntityPathBase<FieldTypeDO> {

    private static final long serialVersionUID = -2131941725L;

    public static final QFieldTypeDO fieldTypeDO = new QFieldTypeDO("fieldTypeDO");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public QFieldTypeDO(String variable) {
        super(FieldTypeDO.class, forVariable(variable));
    }

    public QFieldTypeDO(Path<? extends FieldTypeDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFieldTypeDO(PathMetadata metadata) {
        super(FieldTypeDO.class, metadata);
    }

}

