package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardConfigDO is a Querydsl query type for DashboardConfigDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardConfigDO extends EntityPathBase<DashboardConfigDO> {

    private static final long serialVersionUID = 859072197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDashboardConfigDO dashboardConfigDO = new QDashboardConfigDO("dashboardConfigDO");

    public final StringPath activity = createString("activity");

    public final StringPath badge = createString("badge");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath color = createString("color");

    public final StringPath fieldAcceptedQueryDetail = createString("fieldAcceptedQueryDetail");

    protected QFlowDO flow;

    public final StringPath footer = createString("footer");

    public final StringPath icon = createString("icon");

    public final StringPath id = createString("id");

    public final StringPath label = createString("label");

    public final StringPath name = createString("name");

    public final StringPath query = createString("query");

    public final StringPath queryDetail = createString("queryDetail");

    public final StringPath queryDetailExp = createString("queryDetailExp");

    public final StringPath queryDetailHeader = createString("queryDetailHeader");

    public final StringPath tipo = createString("tipo");

    public final StringPath tooltip = createString("tooltip");

    public QDashboardConfigDO(String variable) {
        this(DashboardConfigDO.class, forVariable(variable), INITS);
    }

    public QDashboardConfigDO(Path<? extends DashboardConfigDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDashboardConfigDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDashboardConfigDO(PathMetadata metadata, PathInits inits) {
        this(DashboardConfigDO.class, metadata, inits);
    }

    public QDashboardConfigDO(Class<? extends DashboardConfigDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

}

