package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowConfigurationFilterFieldValueDO is a Querydsl query type for FlowConfigurationFilterFieldValueDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowConfigurationFilterFieldValueDO extends EntityPathBase<FlowConfigurationFilterFieldValueDO> {

    private static final long serialVersionUID = -24844474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowConfigurationFilterFieldValueDO flowConfigurationFilterFieldValueDO = new QFlowConfigurationFilterFieldValueDO("flowConfigurationFilterFieldValueDO");

    protected QFlowConfigurationFilterFieldDO configurationFilterField;

    protected QFlowExportRequestDO flowExportRequest;

    public final StringPath id = createString("id");

    public final StringPath value = createString("value");

    public QFlowConfigurationFilterFieldValueDO(String variable) {
        this(FlowConfigurationFilterFieldValueDO.class, forVariable(variable), INITS);
    }

    public QFlowConfigurationFilterFieldValueDO(Path<? extends FlowConfigurationFilterFieldValueDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowConfigurationFilterFieldValueDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowConfigurationFilterFieldValueDO(PathMetadata metadata, PathInits inits) {
        this(FlowConfigurationFilterFieldValueDO.class, metadata, inits);
    }

    public QFlowConfigurationFilterFieldValueDO(Class<? extends FlowConfigurationFilterFieldValueDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.configurationFilterField = inits.isInitialized("configurationFilterField") ? new QFlowConfigurationFilterFieldDO(forProperty("configurationFilterField"), inits.get("configurationFilterField")) : null;
        this.flowExportRequest = inits.isInitialized("flowExportRequest") ? new QFlowExportRequestDO(forProperty("flowExportRequest"), inits.get("flowExportRequest")) : null;
    }

    public QFlowConfigurationFilterFieldDO configurationFilterField() {
        if (configurationFilterField == null) {
            configurationFilterField = new QFlowConfigurationFilterFieldDO(forProperty("configurationFilterField"));
        }
        return configurationFilterField;
    }

    public QFlowExportRequestDO flowExportRequest() {
        if (flowExportRequest == null) {
            flowExportRequest = new QFlowExportRequestDO(forProperty("flowExportRequest"));
        }
        return flowExportRequest;
    }

}

