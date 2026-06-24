package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowForeignKeyDO is a Querydsl query type for FlowForeignKeyDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowForeignKeyDO extends EntityPathBase<FlowForeignKeyDO> {

    private static final long serialVersionUID = -303157824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowForeignKeyDO flowForeignKeyDO = new QFlowForeignKeyDO("flowForeignKeyDO");

    public final StringPath id = createString("id");

    protected QFlowTableFieldDO idFieldTable;

    protected QFlowTableFieldDO idFieldTableReferenced;

    protected QFlowTableDO idTable;

    protected QFlowTableDO idTableReferenced;

    public final StringPath jsonField = createString("jsonField");

    public final StringPath jsonFieldType = createString("jsonFieldType");

    public final BooleanPath mandatory = createBoolean("mandatory");

    public QFlowForeignKeyDO(String variable) {
        this(FlowForeignKeyDO.class, forVariable(variable), INITS);
    }

    public QFlowForeignKeyDO(Path<? extends FlowForeignKeyDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowForeignKeyDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowForeignKeyDO(PathMetadata metadata, PathInits inits) {
        this(FlowForeignKeyDO.class, metadata, inits);
    }

    public QFlowForeignKeyDO(Class<? extends FlowForeignKeyDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idFieldTable = inits.isInitialized("idFieldTable") ? new QFlowTableFieldDO(forProperty("idFieldTable"), inits.get("idFieldTable")) : null;
        this.idFieldTableReferenced = inits.isInitialized("idFieldTableReferenced") ? new QFlowTableFieldDO(forProperty("idFieldTableReferenced"), inits.get("idFieldTableReferenced")) : null;
        this.idTable = inits.isInitialized("idTable") ? new QFlowTableDO(forProperty("idTable"), inits.get("idTable")) : null;
        this.idTableReferenced = inits.isInitialized("idTableReferenced") ? new QFlowTableDO(forProperty("idTableReferenced"), inits.get("idTableReferenced")) : null;
    }

    public QFlowTableFieldDO idFieldTable() {
        if (idFieldTable == null) {
            idFieldTable = new QFlowTableFieldDO(forProperty("idFieldTable"));
        }
        return idFieldTable;
    }

    public QFlowTableFieldDO idFieldTableReferenced() {
        if (idFieldTableReferenced == null) {
            idFieldTableReferenced = new QFlowTableFieldDO(forProperty("idFieldTableReferenced"));
        }
        return idFieldTableReferenced;
    }

    public QFlowTableDO idTable() {
        if (idTable == null) {
            idTable = new QFlowTableDO(forProperty("idTable"));
        }
        return idTable;
    }

    public QFlowTableDO idTableReferenced() {
        if (idTableReferenced == null) {
            idTableReferenced = new QFlowTableDO(forProperty("idTableReferenced"));
        }
        return idTableReferenced;
    }

}

