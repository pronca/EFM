package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowConfigurationFilterFieldDO is a Querydsl query type for FlowConfigurationFilterFieldDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowConfigurationFilterFieldDO extends EntityPathBase<FlowConfigurationFilterFieldDO> {

    private static final long serialVersionUID = -92383071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowConfigurationFilterFieldDO flowConfigurationFilterFieldDO = new QFlowConfigurationFilterFieldDO("flowConfigurationFilterFieldDO");

    protected QFlowConfigurationFilterDO configurationFilter;

    public final StringPath filterField = createString("filterField");

    public final StringPath filterTable = createString("filterTable");

    public final StringPath filterType = createString("filterType");

    public final StringPath id = createString("id");

    public final BooleanPath mandatory = createBoolean("mandatory");

    public final StringPath name = createString("name");

    public final StringPath position = createString("position");

    public final BooleanPath range = createBoolean("range");

    public QFlowConfigurationFilterFieldDO(String variable) {
        this(FlowConfigurationFilterFieldDO.class, forVariable(variable), INITS);
    }

    public QFlowConfigurationFilterFieldDO(Path<? extends FlowConfigurationFilterFieldDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowConfigurationFilterFieldDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowConfigurationFilterFieldDO(PathMetadata metadata, PathInits inits) {
        this(FlowConfigurationFilterFieldDO.class, metadata, inits);
    }

    public QFlowConfigurationFilterFieldDO(Class<? extends FlowConfigurationFilterFieldDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.configurationFilter = inits.isInitialized("configurationFilter") ? new QFlowConfigurationFilterDO(forProperty("configurationFilter")) : null;
    }

    public QFlowConfigurationFilterDO configurationFilter() {
        if (configurationFilter == null) {
            configurationFilter = new QFlowConfigurationFilterDO(forProperty("configurationFilter"));
        }
        return configurationFilter;
    }

}

