package it.eng.care.domain.flow.flowupload.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowSectionFileDO is a Querydsl query type for FlowSectionFileDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowSectionFileDO extends EntityPathBase<FlowSectionFileDO> {

    private static final long serialVersionUID = -2001210086L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowSectionFileDO flowSectionFileDO = new QFlowSectionFileDO("flowSectionFileDO");

    public final StringPath id = createString("id");

    protected QFlowFileUploadRequestDO request;

    public final NumberPath<Integer> section = createNumber("section", Integer.class);

    public final ListPath<SectionFileDO, QSectionFileDO> sectionFileList = this.<SectionFileDO, QSectionFileDO>createList("sectionFileList", SectionFileDO.class, QSectionFileDO.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> uploadDate = createDateTime("uploadDate", java.util.Date.class);

    public final StringPath uploadUsername = createString("uploadUsername");

    public QFlowSectionFileDO(String variable) {
        this(FlowSectionFileDO.class, forVariable(variable), INITS);
    }

    public QFlowSectionFileDO(Path<? extends FlowSectionFileDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowSectionFileDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowSectionFileDO(PathMetadata metadata, PathInits inits) {
        this(FlowSectionFileDO.class, metadata, inits);
    }

    public QFlowSectionFileDO(Class<? extends FlowSectionFileDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.request = inits.isInitialized("request") ? new QFlowFileUploadRequestDO(forProperty("request"), inits.get("request")) : null;
    }

    public QFlowFileUploadRequestDO request() {
        if (request == null) {
            request = new QFlowFileUploadRequestDO(forProperty("request"));
        }
        return request;
    }

}

