package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStateMachinePersistDO is a Querydsl query type for StateMachinePersistDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStateMachinePersistDO extends EntityPathBase<StateMachinePersistDO> {

    private static final long serialVersionUID = 2116372045L;

    public static final QStateMachinePersistDO stateMachinePersistDO = new QStateMachinePersistDO("stateMachinePersistDO");

    public final DateTimePath<java.util.Date> dateStatus = createDateTime("dateStatus", java.util.Date.class);

    public final StringPath event = createString("event");

    public final StringPath id = createString("id");

    public final StringPath state = createString("state");

    public final StringPath user = createString("user");

    public QStateMachinePersistDO(String variable) {
        super(StateMachinePersistDO.class, forVariable(variable));
    }

    public QStateMachinePersistDO(Path<? extends StateMachinePersistDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStateMachinePersistDO(PathMetadata metadata) {
        super(StateMachinePersistDO.class, metadata);
    }

}

