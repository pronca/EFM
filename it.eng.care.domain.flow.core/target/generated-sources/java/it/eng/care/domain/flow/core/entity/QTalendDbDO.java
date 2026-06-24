package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTalendDbDO is a Querydsl query type for TalendDbDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTalendDbDO extends EntityPathBase<TalendDbDO> {

    private static final long serialVersionUID = 1023520833L;

    public static final QTalendDbDO talendDbDO = new QTalendDbDO("talendDbDO");

    public final StringPath database = createString("database");

    public final StringPath host = createString("host");

    public final StringPath id = createString("id");

    public final StringPath password = createString("password");

    public final StringPath port = createString("port");

    public final StringPath username = createString("username");

    public QTalendDbDO(String variable) {
        super(TalendDbDO.class, forVariable(variable));
    }

    public QTalendDbDO(Path<? extends TalendDbDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTalendDbDO(PathMetadata metadata) {
        super(TalendDbDO.class, metadata);
    }

}

