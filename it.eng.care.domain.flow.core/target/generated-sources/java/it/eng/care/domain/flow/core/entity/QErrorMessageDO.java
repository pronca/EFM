package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QErrorMessageDO is a Querydsl query type for ErrorMessageDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QErrorMessageDO extends EntityPathBase<ErrorMessageDO> {

    private static final long serialVersionUID = -641898650L;

    public static final QErrorMessageDO errorMessageDO = new QErrorMessageDO("errorMessageDO");

    public final it.eng.care.platform.persistence.impl.jpa.QAbstractEntity _super = new it.eng.care.platform.persistence.impl.jpa.QAbstractEntity(this);

    public final StringPath description = createString("description");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath severity = createString("severity");

    public final StringPath type = createString("type");

    public QErrorMessageDO(String variable) {
        super(ErrorMessageDO.class, forVariable(variable));
    }

    public QErrorMessageDO(Path<? extends ErrorMessageDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QErrorMessageDO(PathMetadata metadata) {
        super(ErrorMessageDO.class, metadata);
    }

}

