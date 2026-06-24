package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowDO is a Querydsl query type for FlowDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowDO extends EntityPathBase<FlowDO> {

    private static final long serialVersionUID = 1224286197L;

    public static final QFlowDO flowDO = new QFlowDO("flowDO");

    public final StringPath code = createString("code");

    public final StringPath commProt = createString("commProt");

    public final DateTimePath<java.util.Date> dataCreation = createDateTime("dataCreation", java.util.Date.class);

    public final StringPath descrb = createString("descrb");

    public final StringPath description = createString("description");

    public final StringPath flowType = createString("flowType");

    public final StringPath id = createString("id");

    public final StringPath monthlyDeadline = createString("monthlyDeadline");

    public final StringPath name = createString("name");

    public final StringPath periodicy = createString("periodicy");

    public final SetPath<ProfiloFlussiDO, QProfiloFlussiDO> profiloFlussi = this.<ProfiloFlussiDO, QProfiloFlussiDO>createSet("profiloFlussi", ProfiloFlussiDO.class, QProfiloFlussiDO.class, PathInits.DIRECT2);

    public final BooleanPath remoteSend = createBoolean("remoteSend");

    public final BooleanPath scheduling = createBoolean("scheduling");

    public final BooleanPath status = createBoolean("status");

    public final BooleanPath uniqueness = createBoolean("uniqueness");

    public final StringPath userCreation = createString("userCreation");

    public final SetPath<FlowVersionDO, QFlowVersionDO> versions = this.<FlowVersionDO, QFlowVersionDO>createSet("versions", FlowVersionDO.class, QFlowVersionDO.class, PathInits.DIRECT2);

    public final StringPath yearlyDeadline = createString("yearlyDeadline");

    public QFlowDO(String variable) {
        super(FlowDO.class, forVariable(variable));
    }

    public QFlowDO(Path<? extends FlowDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlowDO(PathMetadata metadata) {
        super(FlowDO.class, metadata);
    }

}

