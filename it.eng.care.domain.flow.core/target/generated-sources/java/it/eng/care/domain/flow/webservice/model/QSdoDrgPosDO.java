package it.eng.care.domain.flow.webservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSdoDrgPosDO is a Querydsl query type for SdoDrgPosDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSdoDrgPosDO extends EntityPathBase<SdoDrgPosDO> {

    private static final long serialVersionUID = -607406030L;

    public static final QSdoDrgPosDO sdoDrgPosDO = new QSdoDrgPosDO("sdoDrgPosDO");

    public final StringPath campoPk = createString("campoPk");

    public final StringPath lunghezza = createString("lunghezza");

    public final StringPath nomeCampo = createString("nomeCampo");

    public final StringPath posizione = createString("posizione");

    public QSdoDrgPosDO(String variable) {
        super(SdoDrgPosDO.class, forVariable(variable));
    }

    public QSdoDrgPosDO(Path<? extends SdoDrgPosDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSdoDrgPosDO(PathMetadata metadata) {
        super(SdoDrgPosDO.class, metadata);
    }

}

