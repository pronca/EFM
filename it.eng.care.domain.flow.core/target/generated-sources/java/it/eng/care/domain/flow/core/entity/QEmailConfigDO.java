package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailConfigDO is a Querydsl query type for EmailConfigDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmailConfigDO extends EntityPathBase<EmailConfigDO> {

    private static final long serialVersionUID = -1577500211L;

    public static final QEmailConfigDO emailConfigDO = new QEmailConfigDO("emailConfigDO");

    public final StringPath email = createString("email");

    public final StringPath enabled = createString("enabled");

    public final StringPath flow = createString("flow");

    public final StringPath id = createString("id");

    public QEmailConfigDO(String variable) {
        super(EmailConfigDO.class, forVariable(variable));
    }

    public QEmailConfigDO(Path<? extends EmailConfigDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailConfigDO(PathMetadata metadata) {
        super(EmailConfigDO.class, metadata);
    }

}

