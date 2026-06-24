package it.eng.care.domain.flow.tabgen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTabgenFieldDO is a Querydsl query type for TabgenFieldDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTabgenFieldDO extends EntityPathBase<TabgenFieldDO> {

    private static final long serialVersionUID = 2132352818L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTabgenFieldDO tabgenFieldDO = new QTabgenFieldDO("tabgenFieldDO");

    public final it.eng.care.platform.persistence.impl.jpa.QAbstractEntity _super = new it.eng.care.platform.persistence.impl.jpa.QAbstractEntity(this);

    public final StringPath description = createString("description");

    public final StringPath format = createString("format");

    //inherited
    public final StringPath id = _super.id;

    public final NumberPath<Integer> nullable = createNumber("nullable", Integer.class);

    public final NumberPath<Integer> pk = createNumber("pk", Integer.class);

    public final NumberPath<Integer> progressive = createNumber("progressive", Integer.class);

    protected QTabgenDO tabgen;

    protected QTabgenFieldDO tabgenField;

    public final SetPath<TabgenFieldDO, QTabgenFieldDO> tabgenFields = this.<TabgenFieldDO, QTabgenFieldDO>createSet("tabgenFields", TabgenFieldDO.class, QTabgenFieldDO.class, PathInits.DIRECT2);

    public final StringPath tabgenValueColumn = createString("tabgenValueColumn");

    public final StringPath type = createString("type");

    public final NumberPath<Integer> visible = createNumber("visible", Integer.class);

    public QTabgenFieldDO(String variable) {
        this(TabgenFieldDO.class, forVariable(variable), INITS);
    }

    public QTabgenFieldDO(Path<? extends TabgenFieldDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTabgenFieldDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTabgenFieldDO(PathMetadata metadata, PathInits inits) {
        this(TabgenFieldDO.class, metadata, inits);
    }

    public QTabgenFieldDO(Class<? extends TabgenFieldDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tabgen = inits.isInitialized("tabgen") ? new QTabgenDO(forProperty("tabgen")) : null;
        this.tabgenField = inits.isInitialized("tabgenField") ? new QTabgenFieldDO(forProperty("tabgenField"), inits.get("tabgenField")) : null;
    }

    public QTabgenDO tabgen() {
        if (tabgen == null) {
            tabgen = new QTabgenDO(forProperty("tabgen"));
        }
        return tabgen;
    }

    public QTabgenFieldDO tabgenField() {
        if (tabgenField == null) {
            tabgenField = new QTabgenFieldDO(forProperty("tabgenField"));
        }
        return tabgenField;
    }

}

