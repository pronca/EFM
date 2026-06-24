package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppIdentityDO is a Querydsl query type for AppIdentityDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppIdentityDO extends EntityPathBase<AppIdentityDO> {

    private static final long serialVersionUID = 780488078L;

    public static final QAppIdentityDO appIdentityDO = new QAppIdentityDO("appIdentityDO");

    public final StringPath app = createString("app");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath flowName = createString("flowName");

    public final StringPath id = createString("id");

    public final StringPath identity = createString("identity");

    public final StringPath identityRegErrors = createString("identityRegErrors");

    public final StringPath version = createString("version");

    public QAppIdentityDO(String variable) {
        super(AppIdentityDO.class, forVariable(variable));
    }

    public QAppIdentityDO(Path<? extends AppIdentityDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppIdentityDO(PathMetadata metadata) {
        super(AppIdentityDO.class, metadata);
    }

}

