package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardPraticheDO is a Querydsl query type for DashboardPraticheDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardPraticheDO extends EntityPathBase<DashboardPraticheDO> {

    private static final long serialVersionUID = -96590385L;

    public static final QDashboardPraticheDO dashboardPraticheDO = new QDashboardPraticheDO("dashboardPraticheDO");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final SetPath<DashboardPraticheFilterDO, QDashboardPraticheFilterDO> dashboardPraticheFilter = this.<DashboardPraticheFilterDO, QDashboardPraticheFilterDO>createSet("dashboardPraticheFilter", DashboardPraticheFilterDO.class, QDashboardPraticheFilterDO.class, PathInits.DIRECT2);

    public final NumberPath<Integer> day1 = createNumber("day1", Integer.class);

    public final NumberPath<Integer> day2 = createNumber("day2", Integer.class);

    public final NumberPath<Integer> day3 = createNumber("day3", Integer.class);

    public final NumberPath<Integer> day4 = createNumber("day4", Integer.class);

    public final NumberPath<Integer> day5 = createNumber("day5", Integer.class);

    public final NumberPath<Integer> day6 = createNumber("day6", Integer.class);

    public final NumberPath<Integer> day7 = createNumber("day7", Integer.class);

    public final StringPath flowName = createString("flowName");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> lastUpdate = createDateTime("lastUpdate", java.util.Date.class);

    public QDashboardPraticheDO(String variable) {
        super(DashboardPraticheDO.class, forVariable(variable));
    }

    public QDashboardPraticheDO(Path<? extends DashboardPraticheDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDashboardPraticheDO(PathMetadata metadata) {
        super(DashboardPraticheDO.class, metadata);
    }

}

