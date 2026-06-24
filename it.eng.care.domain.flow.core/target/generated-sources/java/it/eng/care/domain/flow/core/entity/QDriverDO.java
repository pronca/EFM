package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDriverDO is a Querydsl query type for DriverDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDriverDO extends EntityPathBase<DriverDO> {

    private static final long serialVersionUID = 1385718127L;

    public static final QDriverDO driverDO = new QDriverDO("driverDO");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public QDriverDO(String variable) {
        super(DriverDO.class, forVariable(variable));
    }

    public QDriverDO(Path<? extends DriverDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDriverDO(PathMetadata metadata) {
        super(DriverDO.class, metadata);
    }

}

