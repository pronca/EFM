package it.eng.care.domain.flow.tabgen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTabgenDO is a Querydsl query type for TabgenDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTabgenDO extends EntityPathBase<TabgenDO> {

    private static final long serialVersionUID = -897345506L;

    public static final QTabgenDO tabgenDO = new QTabgenDO("tabgenDO");

    public final it.eng.care.platform.persistence.impl.jpa.QAbstractEntity _super = new it.eng.care.platform.persistence.impl.jpa.QAbstractEntity(this);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> fieldNum = createNumber("fieldNum", Integer.class);

    //inherited
    public final StringPath id = _super.id;

    public final SetPath<TabgenFieldDO, QTabgenFieldDO> tabgenFields = this.<TabgenFieldDO, QTabgenFieldDO>createSet("tabgenFields", TabgenFieldDO.class, QTabgenFieldDO.class, PathInits.DIRECT2);

    public final SetPath<TabgenValueDO, QTabgenValueDO> tabgenValues = this.<TabgenValueDO, QTabgenValueDO>createSet("tabgenValues", TabgenValueDO.class, QTabgenValueDO.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public final NumberPath<Integer> visible = createNumber("visible", Integer.class);

    public QTabgenDO(String variable) {
        super(TabgenDO.class, forVariable(variable));
    }

    public QTabgenDO(Path<? extends TabgenDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTabgenDO(PathMetadata metadata) {
        super(TabgenDO.class, metadata);
    }

}

