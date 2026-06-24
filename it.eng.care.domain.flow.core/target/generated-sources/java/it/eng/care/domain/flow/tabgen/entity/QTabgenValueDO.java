package it.eng.care.domain.flow.tabgen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTabgenValueDO is a Querydsl query type for TabgenValueDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTabgenValueDO extends EntityPathBase<TabgenValueDO> {

    private static final long serialVersionUID = -1069756951L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTabgenValueDO tabgenValueDO = new QTabgenValueDO("tabgenValueDO");

    public final it.eng.care.platform.persistence.impl.jpa.QAbstractEntity _super = new it.eng.care.platform.persistence.impl.jpa.QAbstractEntity(this);

    public final DatePath<java.util.Date> disabledDate = createDate("disabledDate", java.util.Date.class);

    public final DatePath<java.util.Date> enabledDate = createDate("enabledDate", java.util.Date.class);

    public final StringPath field1 = createString("field1");

    public final StringPath field10 = createString("field10");

    public final StringPath field11 = createString("field11");

    public final StringPath field12 = createString("field12");

    public final StringPath field13 = createString("field13");

    public final StringPath field14 = createString("field14");

    public final StringPath field15 = createString("field15");

    public final StringPath field16 = createString("field16");

    public final StringPath field17 = createString("field17");

    public final StringPath field18 = createString("field18");

    public final StringPath field19 = createString("field19");

    public final StringPath field2 = createString("field2");

    public final StringPath field20 = createString("field20");

    public final StringPath field21 = createString("field21");

    public final StringPath field22 = createString("field22");

    public final StringPath field23 = createString("field23");

    public final StringPath field24 = createString("field24");

    public final StringPath field25 = createString("field25");

    public final StringPath field26 = createString("field26");

    public final StringPath field27 = createString("field27");

    public final StringPath field28 = createString("field28");

    public final StringPath field29 = createString("field29");

    public final StringPath field3 = createString("field3");

    public final StringPath field30 = createString("field30");

    public final StringPath field31 = createString("field31");

    public final StringPath field32 = createString("field32");

    public final StringPath field33 = createString("field33");

    public final StringPath field34 = createString("field34");

    public final StringPath field35 = createString("field35");

    public final StringPath field36 = createString("field36");

    public final StringPath field37 = createString("field37");

    public final StringPath field38 = createString("field38");

    public final StringPath field39 = createString("field39");

    public final StringPath field4 = createString("field4");

    public final StringPath field40 = createString("field40");

    public final StringPath field5 = createString("field5");

    public final StringPath field6 = createString("field6");

    public final StringPath field7 = createString("field7");

    public final StringPath field8 = createString("field8");

    public final StringPath field9 = createString("field9");

    //inherited
    public final StringPath id = _super.id;

    public final DateTimePath<java.util.Date> operationDate = createDateTime("operationDate", java.util.Date.class);

    protected QTabgenDO tabgen;

    public QTabgenValueDO(String variable) {
        this(TabgenValueDO.class, forVariable(variable), INITS);
    }

    public QTabgenValueDO(Path<? extends TabgenValueDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTabgenValueDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTabgenValueDO(PathMetadata metadata, PathInits inits) {
        this(TabgenValueDO.class, metadata, inits);
    }

    public QTabgenValueDO(Class<? extends TabgenValueDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tabgen = inits.isInitialized("tabgen") ? new QTabgenDO(forProperty("tabgen")) : null;
    }

    public QTabgenDO tabgen() {
        if (tabgen == null) {
            tabgen = new QTabgenDO(forProperty("tabgen"));
        }
        return tabgen;
    }

}

