package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDashboardFilterKey is a Querydsl query type for DashboardFilterKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDashboardFilterKey extends BeanPath<DashboardFilterKey> {

    private static final long serialVersionUID = 784520015L;

    public static final QDashboardFilterKey dashboardFilterKey = new QDashboardFilterKey("dashboardFilterKey");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath dashBoard = createString("dashBoard");

    public final StringPath filterName = createString("filterName");

    public QDashboardFilterKey(String variable) {
        super(DashboardFilterKey.class, forVariable(variable));
    }

    public QDashboardFilterKey(Path<? extends DashboardFilterKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDashboardFilterKey(PathMetadata metadata) {
        super(DashboardFilterKey.class, metadata);
    }

}

