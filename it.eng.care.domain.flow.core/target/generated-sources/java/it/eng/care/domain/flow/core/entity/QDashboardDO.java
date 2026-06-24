package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardDO is a Querydsl query type for DashboardDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardDO extends EntityPathBase<DashboardDO> {

    private static final long serialVersionUID = -200889885L;

    public static final QDashboardDO dashboardDO = new QDashboardDO("dashboardDO");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final SetPath<DashboardFilterDO, QDashboardFilterDO> dashboardFilter = this.<DashboardFilterDO, QDashboardFilterDO>createSet("dashboardFilter", DashboardFilterDO.class, QDashboardFilterDO.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> day = createDateTime("day", java.util.Date.class);

    public final StringPath flow = createString("flow");

    public final StringPath id = createString("id");

    public final StringPath label = createString("label");

    public final DateTimePath<java.util.Date> lastUpdate = createDateTime("lastUpdate", java.util.Date.class);

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final StringPath tooltip = createString("tooltip");

    public final StringPath value = createString("value");

    public final StringPath widgetName = createString("widgetName");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QDashboardDO(String variable) {
        super(DashboardDO.class, forVariable(variable));
    }

    public QDashboardDO(Path<? extends DashboardDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDashboardDO(PathMetadata metadata) {
        super(DashboardDO.class, metadata);
    }

}

