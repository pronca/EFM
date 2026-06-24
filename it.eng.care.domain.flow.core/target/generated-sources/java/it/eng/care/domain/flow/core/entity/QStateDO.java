package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStateDO is a Querydsl query type for StateDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStateDO extends EntityPathBase<StateDO> {

    private static final long serialVersionUID = -1833141600L;

    public static final QStateDO stateDO = new QStateDO("stateDO");

    public final StringPath id = createString("id");

    public final StringPath initialActionId = createString("initialActionId");

    public final StringPath initialState = createString("initialState");

    public final StringPath kind = createString("kind");

    public final StringPath machineId = createString("machineId");

    public final StringPath parentStateId = createString("parentStateId");

    public final StringPath region = createString("region");

    public final StringPath state = createString("state");

    public final StringPath subMachineId = createString("subMachineId");

    public QStateDO(String variable) {
        super(StateDO.class, forVariable(variable));
    }

    public QStateDO(Path<? extends StateDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStateDO(PathMetadata metadata) {
        super(StateDO.class, metadata);
    }

}

