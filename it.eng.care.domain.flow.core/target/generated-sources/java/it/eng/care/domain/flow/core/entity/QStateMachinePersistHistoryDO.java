package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStateMachinePersistHistoryDO is a Querydsl query type for StateMachinePersistHistoryDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStateMachinePersistHistoryDO extends EntityPathBase<StateMachinePersistHistoryDO> {

    private static final long serialVersionUID = 2094750717L;

    public static final QStateMachinePersistHistoryDO stateMachinePersistHistoryDO = new QStateMachinePersistHistoryDO("stateMachinePersistHistoryDO");

    public final DateTimePath<java.util.Date> dateStatus = createDateTime("dateStatus", java.util.Date.class);

    public final StringPath entityId = createString("entityId");

    public final StringPath event = createString("event");

    public final StringPath id = createString("id");

    public final StringPath state = createString("state");

    public final StringPath user = createString("user");

    public QStateMachinePersistHistoryDO(String variable) {
        super(StateMachinePersistHistoryDO.class, forVariable(variable));
    }

    public QStateMachinePersistHistoryDO(Path<? extends StateMachinePersistHistoryDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStateMachinePersistHistoryDO(PathMetadata metadata) {
        super(StateMachinePersistHistoryDO.class, metadata);
    }

}

