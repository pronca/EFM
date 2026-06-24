package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardErrorsFilterDO is a Querydsl query type for DashboardErrorsFilterDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardErrorsFilterDO extends EntityPathBase<DashboardErrorsFilterDO> {

    private static final long serialVersionUID = -824363002L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDashboardErrorsFilterDO dashboardErrorsFilterDO = new QDashboardErrorsFilterDO("dashboardErrorsFilterDO");

    public final StringPath filterValue = createString("filterValue");

    protected QErrorsFilterKey key;

    public QDashboardErrorsFilterDO(String variable) {
        this(DashboardErrorsFilterDO.class, forVariable(variable), INITS);
    }

    public QDashboardErrorsFilterDO(Path<? extends DashboardErrorsFilterDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDashboardErrorsFilterDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDashboardErrorsFilterDO(PathMetadata metadata, PathInits inits) {
        this(DashboardErrorsFilterDO.class, metadata, inits);
    }

    public QDashboardErrorsFilterDO(Class<? extends DashboardErrorsFilterDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QErrorsFilterKey(forProperty("key")) : null;
    }

    public QErrorsFilterKey key() {
        if (key == null) {
            key = new QErrorsFilterKey(forProperty("key"));
        }
        return key;
    }

}

