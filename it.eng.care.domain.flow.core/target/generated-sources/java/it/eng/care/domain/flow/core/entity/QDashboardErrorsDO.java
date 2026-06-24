package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardErrorsDO is a Querydsl query type for DashboardErrorsDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardErrorsDO extends EntityPathBase<DashboardErrorsDO> {

    private static final long serialVersionUID = -1459622738L;

    public static final QDashboardErrorsDO dashboardErrorsDO = new QDashboardErrorsDO("dashboardErrorsDO");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final NumberPath<Integer> countError = createNumber("countError", Integer.class);

    public final SetPath<DashboardErrorsFilterDO, QDashboardErrorsFilterDO> dashboardErrorsFilter = this.<DashboardErrorsFilterDO, QDashboardErrorsFilterDO>createSet("dashboardErrorsFilter", DashboardErrorsFilterDO.class, QDashboardErrorsFilterDO.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> day = createDateTime("day", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath error = createString("error");

    public final StringPath flowName = createString("flowName");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> lastUpdate = createDateTime("lastUpdate", java.util.Date.class);

    public final StringPath month = createString("month");

    public final StringPath year = createString("year");

    public QDashboardErrorsDO(String variable) {
        super(DashboardErrorsDO.class, forVariable(variable));
    }

    public QDashboardErrorsDO(Path<? extends DashboardErrorsDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDashboardErrorsDO(PathMetadata metadata) {
        super(DashboardErrorsDO.class, metadata);
    }

}

