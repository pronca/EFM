package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailMessageConfigDO is a Querydsl query type for EmailMessageConfigDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmailMessageConfigDO extends EntityPathBase<EmailMessageConfigDO> {

    private static final long serialVersionUID = 1150247412L;

    public static final QEmailMessageConfigDO emailMessageConfigDO = new QEmailMessageConfigDO("emailMessageConfigDO");

    public final StringPath flow = createString("flow");

    public final StringPath id = createString("id");

    public final StringPath subject = createString("subject");

    public final StringPath text = createString("text");

    public QEmailMessageConfigDO(String variable) {
        super(EmailMessageConfigDO.class, forVariable(variable));
    }

    public QEmailMessageConfigDO(Path<? extends EmailMessageConfigDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailMessageConfigDO(PathMetadata metadata) {
        super(EmailMessageConfigDO.class, metadata);
    }

}

