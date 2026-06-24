package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowTableFieldDO is a Querydsl query type for FlowTableFieldDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowTableFieldDO extends EntityPathBase<FlowTableFieldDO> {

    private static final long serialVersionUID = -2044585375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowTableFieldDO flowTableFieldDO = new QFlowTableFieldDO("flowTableFieldDO");

    public final BooleanPath active = createBoolean("active");

    public final StringPath description = createString("description");

    public final StringPath descriptionsm = createString("descriptionsm");

    public final BooleanPath enableCrypt = createBoolean("enableCrypt");

    public final SetPath<FlowForeignKeyDO, QFlowForeignKeyDO> fieldableReferenced = this.<FlowForeignKeyDO, QFlowForeignKeyDO>createSet("fieldableReferenced", FlowForeignKeyDO.class, QFlowForeignKeyDO.class, PathInits.DIRECT2);

    public final SetPath<FlowForeignKeyDO, QFlowForeignKeyDO> fieldTable = this.<FlowForeignKeyDO, QFlowForeignKeyDO>createSet("fieldTable", FlowForeignKeyDO.class, QFlowForeignKeyDO.class, PathInits.DIRECT2);

    public final StringPath fieldType = createString("fieldType");

    protected QFlowTableDO flowTable;

    public final StringPath flowTableFieldId = createString("flowTableFieldId");

    public final BooleanPath groups = createBoolean("groups");

    public final StringPath id = createString("id");

    public final BooleanPath isPk = createBoolean("isPk");

    public final BooleanPath isReferenceDate = createBoolean("isReferenceDate");

    public final NumberPath<Integer> length = createNumber("length", Integer.class);

    public final BooleanPath mandatory = createBoolean("mandatory");

    public final StringPath name = createString("name");

    public final StringPath physicalSize = createString("physicalSize");

    public final NumberPath<Integer> position = createNumber("position", Integer.class);

    public final StringPath regExp = createString("regExp");

    public QFlowTableFieldDO(String variable) {
        this(FlowTableFieldDO.class, forVariable(variable), INITS);
    }

    public QFlowTableFieldDO(Path<? extends FlowTableFieldDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowTableFieldDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowTableFieldDO(PathMetadata metadata, PathInits inits) {
        this(FlowTableFieldDO.class, metadata, inits);
    }

    public QFlowTableFieldDO(Class<? extends FlowTableFieldDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flowTable = inits.isInitialized("flowTable") ? new QFlowTableDO(forProperty("flowTable"), inits.get("flowTable")) : null;
    }

    public QFlowTableDO flowTable() {
        if (flowTable == null) {
            flowTable = new QFlowTableDO(forProperty("flowTable"));
        }
        return flowTable;
    }

}

