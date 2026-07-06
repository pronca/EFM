package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadReturnsRequestDO is a Querydsl query type for UploadReturnsRequestDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUploadReturnsRequestDO extends EntityPathBase<UploadReturnsRequestDO> {

    private static final long serialVersionUID = -1581521100L;

    public static final QUploadReturnsRequestDO uploadReturnsRequestDO = new QUploadReturnsRequestDO("uploadReturnsRequestDO");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> endProcessDate = createDateTime("endProcessDate", java.util.Date.class);

    public final ArrayPath<byte[], Byte> errorFile = createArray("errorFile", byte[].class);

    public final StringPath extractionId = createString("extractionId");

    public final ArrayPath<byte[], Byte> file = createArray("file", byte[].class);

    public final StringPath fileNameOrigin = createString("fileNameOrigin");

    public final StringPath flowId = createString("flowId");

    public final BooleanPath hasErrors = createBoolean("hasErrors");

    public final StringPath id = createString("id");

    public final StringPath tipoValidazioneReg = createString("tipoValidazioneReg");

    public final StringPath userId = createString("userId");

    public QUploadReturnsRequestDO(String variable) {
        super(UploadReturnsRequestDO.class, forVariable(variable));
    }

    public QUploadReturnsRequestDO(Path<? extends UploadReturnsRequestDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadReturnsRequestDO(PathMetadata metadata) {
        super(UploadReturnsRequestDO.class, metadata);
    }

}

