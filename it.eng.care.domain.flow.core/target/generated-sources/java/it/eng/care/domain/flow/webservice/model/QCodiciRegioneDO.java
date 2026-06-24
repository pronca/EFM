package it.eng.care.domain.flow.webservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodiciRegioneDO is a Querydsl query type for CodiciRegioneDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCodiciRegioneDO extends EntityPathBase<CodiciRegioneDO> {

    private static final long serialVersionUID = 621115123L;

    public static final QCodiciRegioneDO codiciRegioneDO = new QCodiciRegioneDO("codiciRegioneDO");

    public final StringPath chiave = createString("chiave");

    public final StringPath valore = createString("valore");

    public QCodiciRegioneDO(String variable) {
        super(CodiciRegioneDO.class, forVariable(variable));
    }

    public QCodiciRegioneDO(Path<? extends CodiciRegioneDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodiciRegioneDO(PathMetadata metadata) {
        super(CodiciRegioneDO.class, metadata);
    }

}

