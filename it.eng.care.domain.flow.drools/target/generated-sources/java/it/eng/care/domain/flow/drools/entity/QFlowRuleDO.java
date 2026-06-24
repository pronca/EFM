package it.eng.care.domain.flow.drools.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowRuleDO is a Querydsl query type for FlowRuleDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowRuleDO extends EntityPathBase<FlowRuleDO> {

    private static final long serialVersionUID = 1884020455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowRuleDO flowRuleDO = new QFlowRuleDO("flowRuleDO");

    public final it.eng.care.platform.persistence.impl.jpa.QAbstractEntity _super = new it.eng.care.platform.persistence.impl.jpa.QAbstractEntity(this);

    public final ListPath<FlowRuleFileDO, QFlowRuleFileDO> files = this.<FlowRuleFileDO, QFlowRuleFileDO>createList("files", FlowRuleFileDO.class, QFlowRuleFileDO.class, PathInits.DIRECT2);

    protected it.eng.care.domain.flow.core.entity.QFlowDO flow;

    //inherited
    public final StringPath id = _super.id;

    protected it.eng.care.domain.flow.core.entity.QVersionDO version;

    public QFlowRuleDO(String variable) {
        this(FlowRuleDO.class, forVariable(variable), INITS);
    }

    public QFlowRuleDO(Path<? extends FlowRuleDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowRuleDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowRuleDO(PathMetadata metadata, PathInits inits) {
        this(FlowRuleDO.class, metadata, inits);
    }

    public QFlowRuleDO(Class<? extends FlowRuleDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new it.eng.care.domain.flow.core.entity.QFlowDO(forProperty("flow")) : null;
        this.version = inits.isInitialized("version") ? new it.eng.care.domain.flow.core.entity.QVersionDO(forProperty("version")) : null;
    }

    public it.eng.care.domain.flow.core.entity.QFlowDO flow() {
        if (flow == null) {
            flow = new it.eng.care.domain.flow.core.entity.QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public it.eng.care.domain.flow.core.entity.QVersionDO version() {
        if (version == null) {
            version = new it.eng.care.domain.flow.core.entity.QVersionDO(forProperty("version"));
        }
        return version;
    }

}

