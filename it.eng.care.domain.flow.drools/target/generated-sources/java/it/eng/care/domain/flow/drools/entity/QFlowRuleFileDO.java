package it.eng.care.domain.flow.drools.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowRuleFileDO is a Querydsl query type for FlowRuleFileDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowRuleFileDO extends EntityPathBase<FlowRuleFileDO> {

    private static final long serialVersionUID = -1662073981L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowRuleFileDO flowRuleFileDO = new QFlowRuleFileDO("flowRuleFileDO");

    public final StringPath filename = createString("filename");

    protected QFlowRuleDO flowRule;

    public final StringPath id = createString("id");

    public final ArrayPath<byte[], Byte> rule = createArray("rule", byte[].class);

    public final StringPath ruleType = createString("ruleType");

    public QFlowRuleFileDO(String variable) {
        this(FlowRuleFileDO.class, forVariable(variable), INITS);
    }

    public QFlowRuleFileDO(Path<? extends FlowRuleFileDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowRuleFileDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowRuleFileDO(PathMetadata metadata, PathInits inits) {
        this(FlowRuleFileDO.class, metadata, inits);
    }

    public QFlowRuleFileDO(Class<? extends FlowRuleFileDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flowRule = inits.isInitialized("flowRule") ? new QFlowRuleDO(forProperty("flowRule"), inits.get("flowRule")) : null;
    }

    public QFlowRuleDO flowRule() {
        if (flowRule == null) {
            flowRule = new QFlowRuleDO(forProperty("flowRule"));
        }
        return flowRule;
    }

}

