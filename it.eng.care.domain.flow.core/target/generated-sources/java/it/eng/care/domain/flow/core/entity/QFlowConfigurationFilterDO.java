package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowConfigurationFilterDO is a Querydsl query type for FlowConfigurationFilterDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowConfigurationFilterDO extends EntityPathBase<FlowConfigurationFilterDO> {

    private static final long serialVersionUID = -5376689L;

    public static final QFlowConfigurationFilterDO flowConfigurationFilterDO = new QFlowConfigurationFilterDO("flowConfigurationFilterDO");

    public final SetPath<FlowExportRequestDO, QFlowExportRequestDO> exportRequest = this.<FlowExportRequestDO, QFlowExportRequestDO>createSet("exportRequest", FlowExportRequestDO.class, QFlowExportRequestDO.class, PathInits.DIRECT2);

    public final SetPath<FlowConfigurationFilterFieldDO, QFlowConfigurationFilterFieldDO> filterFields = this.<FlowConfigurationFilterFieldDO, QFlowConfigurationFilterFieldDO>createSet("filterFields", FlowConfigurationFilterFieldDO.class, QFlowConfigurationFilterFieldDO.class, PathInits.DIRECT2);

    public final StringPath flow = createString("flow");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath version = createString("version");

    public QFlowConfigurationFilterDO(String variable) {
        super(FlowConfigurationFilterDO.class, forVariable(variable));
    }

    public QFlowConfigurationFilterDO(Path<? extends FlowConfigurationFilterDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlowConfigurationFilterDO(PathMetadata metadata) {
        super(FlowConfigurationFilterDO.class, metadata);
    }

}

