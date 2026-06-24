package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFmSequenceDO is a Querydsl query type for FmSequenceDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFmSequenceDO extends EntityPathBase<FmSequenceDO> {

    private static final long serialVersionUID = -1423909649L;

    public static final QFmSequenceDO fmSequenceDO = new QFmSequenceDO("fmSequenceDO");

    public final NumberPath<Integer> currentValue = createNumber("currentValue", Integer.class);

    public final StringPath id = createString("id");

    public QFmSequenceDO(String variable) {
        super(FmSequenceDO.class, forVariable(variable));
    }

    public QFmSequenceDO(Path<? extends FmSequenceDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFmSequenceDO(PathMetadata metadata) {
        super(FmSequenceDO.class, metadata);
    }

}

