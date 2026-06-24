package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowTableDO is a Querydsl query type for FlowTableDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowTableDO extends EntityPathBase<FlowTableDO> {

    private static final long serialVersionUID = 1705515919L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowTableDO flowTableDO = new QFlowTableDO("flowTableDO");

    protected QDataSourceDO dataSource;

    public final StringPath description = createString("description");

    public final SetPath<FlowTableFieldDO, QFlowTableFieldDO> fields = this.<FlowTableFieldDO, QFlowTableFieldDO>createSet("fields", FlowTableFieldDO.class, QFlowTableFieldDO.class, PathInits.DIRECT2);

    protected QFlowDO flowDO;

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final BooleanPath required = createBoolean("required");

    public final NumberPath<Integer> section = createNumber("section", Integer.class);

    public final SetPath<FlowForeignKeyDO, QFlowForeignKeyDO> table = this.<FlowForeignKeyDO, QFlowForeignKeyDO>createSet("table", FlowForeignKeyDO.class, QFlowForeignKeyDO.class, PathInits.DIRECT2);

    public final SetPath<FlowForeignKeyDO, QFlowForeignKeyDO> tableReferenced = this.<FlowForeignKeyDO, QFlowForeignKeyDO>createSet("tableReferenced", FlowForeignKeyDO.class, QFlowForeignKeyDO.class, PathInits.DIRECT2);

    protected QVersionDO versionDO;

    public final ArrayPath<byte[], Byte> xsd = createArray("xsd", byte[].class);

    public QFlowTableDO(String variable) {
        this(FlowTableDO.class, forVariable(variable), INITS);
    }

    public QFlowTableDO(Path<? extends FlowTableDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowTableDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowTableDO(PathMetadata metadata, PathInits inits) {
        this(FlowTableDO.class, metadata, inits);
    }

    public QFlowTableDO(Class<? extends FlowTableDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dataSource = inits.isInitialized("dataSource") ? new QDataSourceDO(forProperty("dataSource"), inits.get("dataSource")) : null;
        this.flowDO = inits.isInitialized("flowDO") ? new QFlowDO(forProperty("flowDO")) : null;
        this.versionDO = inits.isInitialized("versionDO") ? new QVersionDO(forProperty("versionDO")) : null;
    }

    public QDataSourceDO dataSource() {
        if (dataSource == null) {
            dataSource = new QDataSourceDO(forProperty("dataSource"));
        }
        return dataSource;
    }

    public QFlowDO flowDO() {
        if (flowDO == null) {
            flowDO = new QFlowDO(forProperty("flowDO"));
        }
        return flowDO;
    }

    public QVersionDO versionDO() {
        if (versionDO == null) {
            versionDO = new QVersionDO(forProperty("versionDO"));
        }
        return versionDO;
    }

}

