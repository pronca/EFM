package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlowReceiverLogDO is a Querydsl query type for FlowReceiverLogDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowReceiverLogDO extends EntityPathBase<FlowReceiverLogDO> {

    private static final long serialVersionUID = 1927749718L;

    public static final QFlowReceiverLogDO flowReceiverLogDO = new QFlowReceiverLogDO("flowReceiverLogDO");

    public final StringPath flowName = createString("flowName");

    public final StringPath id = createString("id");

    public final StringPath operation = createString("operation");

    public final StringPath pk = createString("pk");

    public final DateTimePath<java.util.Date> sendingDate = createDateTime("sendingDate", java.util.Date.class);

    public final StringPath versionName = createString("versionName");

    public QFlowReceiverLogDO(String variable) {
        super(FlowReceiverLogDO.class, forVariable(variable));
    }

    public QFlowReceiverLogDO(Path<? extends FlowReceiverLogDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlowReceiverLogDO(PathMetadata metadata) {
        super(FlowReceiverLogDO.class, metadata);
    }

}

