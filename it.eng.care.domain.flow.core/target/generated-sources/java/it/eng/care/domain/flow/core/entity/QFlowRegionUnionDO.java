package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowRegionUnionDO is a Querydsl query type for FlowRegionUnionDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowRegionUnionDO extends EntityPathBase<FlowRegionUnionDO> {

    private static final long serialVersionUID = 2123534396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowRegionUnionDO flowRegionUnionDO = new QFlowRegionUnionDO("flowRegionUnionDO");

    protected QFlowDO flowLocal;

    protected QFlowDO flowRegion;

    public final StringPath id = createString("id");

    public QFlowRegionUnionDO(String variable) {
        this(FlowRegionUnionDO.class, forVariable(variable), INITS);
    }

    public QFlowRegionUnionDO(Path<? extends FlowRegionUnionDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowRegionUnionDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowRegionUnionDO(PathMetadata metadata, PathInits inits) {
        this(FlowRegionUnionDO.class, metadata, inits);
    }

    public QFlowRegionUnionDO(Class<? extends FlowRegionUnionDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flowLocal = inits.isInitialized("flowLocal") ? new QFlowDO(forProperty("flowLocal")) : null;
        this.flowRegion = inits.isInitialized("flowRegion") ? new QFlowDO(forProperty("flowRegion")) : null;
    }

    public QFlowDO flowLocal() {
        if (flowLocal == null) {
            flowLocal = new QFlowDO(forProperty("flowLocal"));
        }
        return flowLocal;
    }

    public QFlowDO flowRegion() {
        if (flowRegion == null) {
            flowRegion = new QFlowDO(forProperty("flowRegion"));
        }
        return flowRegion;
    }

}

