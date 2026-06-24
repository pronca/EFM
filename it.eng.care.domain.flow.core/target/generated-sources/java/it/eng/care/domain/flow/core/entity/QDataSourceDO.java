package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDataSourceDO is a Querydsl query type for DataSourceDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDataSourceDO extends EntityPathBase<DataSourceDO> {

    private static final long serialVersionUID = 2133347436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDataSourceDO dataSourceDO = new QDataSourceDO("dataSourceDO");

    protected QDriverDO driver;

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath hostname = createString("hostname");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath port = createString("port");

    public final StringPath serviceName = createString("serviceName");

    public final StringPath username = createString("username");

    public QDataSourceDO(String variable) {
        this(DataSourceDO.class, forVariable(variable), INITS);
    }

    public QDataSourceDO(Path<? extends DataSourceDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDataSourceDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDataSourceDO(PathMetadata metadata, PathInits inits) {
        this(DataSourceDO.class, metadata, inits);
    }

    public QDataSourceDO(Class<? extends DataSourceDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.driver = inits.isInitialized("driver") ? new QDriverDO(forProperty("driver")) : null;
    }

    public QDriverDO driver() {
        if (driver == null) {
            driver = new QDriverDO(forProperty("driver"));
        }
        return driver;
    }

}

