package it.eng.care.domain.flow.flowupload.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSectionFileDO is a Querydsl query type for SectionFileDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSectionFileDO extends EntityPathBase<SectionFileDO> {

    private static final long serialVersionUID = -34866776L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSectionFileDO sectionFileDO = new QSectionFileDO("sectionFileDO");

    public final StringPath extension = createString("extension");

    public final ArrayPath<byte[], Byte> file = createArray("file", byte[].class);

    public final StringPath id = createString("id");

    protected QFlowSectionFileDO section;

    public final ArrayPath<byte[], Byte> wellFormedFile = createArray("wellFormedFile", byte[].class);

    public QSectionFileDO(String variable) {
        this(SectionFileDO.class, forVariable(variable), INITS);
    }

    public QSectionFileDO(Path<? extends SectionFileDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSectionFileDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSectionFileDO(PathMetadata metadata, PathInits inits) {
        this(SectionFileDO.class, metadata, inits);
    }

    public QSectionFileDO(Class<? extends SectionFileDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.section = inits.isInitialized("section") ? new QFlowSectionFileDO(forProperty("section"), inits.get("section")) : null;
    }

    public QFlowSectionFileDO section() {
        if (section == null) {
            section = new QFlowSectionFileDO(forProperty("section"));
        }
        return section;
    }

}

