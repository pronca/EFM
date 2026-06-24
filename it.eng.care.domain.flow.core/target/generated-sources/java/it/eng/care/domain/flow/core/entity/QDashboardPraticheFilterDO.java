package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboardPraticheFilterDO is a Querydsl query type for DashboardPraticheFilterDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDashboardPraticheFilterDO extends EntityPathBase<DashboardPraticheFilterDO> {

    private static final long serialVersionUID = -830843801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDashboardPraticheFilterDO dashboardPraticheFilterDO = new QDashboardPraticheFilterDO("dashboardPraticheFilterDO");

    public final StringPath filterValue = createString("filterValue");

    protected QPraticheFilterKey key;

    public QDashboardPraticheFilterDO(String variable) {
        this(DashboardPraticheFilterDO.class, forVariable(variable), INITS);
    }

    public QDashboardPraticheFilterDO(Path<? extends DashboardPraticheFilterDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDashboardPraticheFilterDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDashboardPraticheFilterDO(PathMetadata metadata, PathInits inits) {
        this(DashboardPraticheFilterDO.class, metadata, inits);
    }

    public QDashboardPraticheFilterDO(Class<? extends DashboardPraticheFilterDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QPraticheFilterKey(forProperty("key")) : null;
    }

    public QPraticheFilterKey key() {
        if (key == null) {
            key = new QPraticheFilterKey(forProperty("key"));
        }
        return key;
    }

}

