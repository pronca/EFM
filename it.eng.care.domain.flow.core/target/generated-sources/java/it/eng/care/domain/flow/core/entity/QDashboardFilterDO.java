package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardFilterDO is a Querydsl query type for DashboardFilterDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardFilterDO extends EntityPathBase<DashboardFilterDO> {

    private static final long serialVersionUID = 718043515L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDashboardFilterDO dashboardFilterDO = new QDashboardFilterDO("dashboardFilterDO");

    public final StringPath filterValue = createString("filterValue");

    protected QDashboardFilterKey key;

    public QDashboardFilterDO(String variable) {
        this(DashboardFilterDO.class, forVariable(variable), INITS);
    }

    public QDashboardFilterDO(Path<? extends DashboardFilterDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDashboardFilterDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDashboardFilterDO(PathMetadata metadata, PathInits inits) {
        this(DashboardFilterDO.class, metadata, inits);
    }

    public QDashboardFilterDO(Class<? extends DashboardFilterDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QDashboardFilterKey(forProperty("key")) : null;
    }

    public QDashboardFilterKey key() {
        if (key == null) {
            key = new QDashboardFilterKey(forProperty("key"));
        }
        return key;
    }

}

