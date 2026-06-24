package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFmFlowExtstdConfigDO is a Querydsl query type for FmFlowExtstdConfigDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFmFlowExtstdConfigDO extends EntityPathBase<FmFlowExtstdConfigDO> {

    private static final long serialVersionUID = -2053933952L;

    public static final QFmFlowExtstdConfigDO fmFlowExtstdConfigDO = new QFmFlowExtstdConfigDO("fmFlowExtstdConfigDO");

    public final StringPath datafineP1 = createString("datafineP1");

    public final StringPath datafineP2 = createString("datafineP2");

    public final StringPath datafineP3 = createString("datafineP3");

    public final StringPath datainizioP1 = createString("datainizioP1");

    public final StringPath datainizioP2 = createString("datainizioP2");

    public final StringPath datainizioP3 = createString("datainizioP3");

    public final StringPath flusso = createString("flusso");

    public final StringPath numinvio = createString("numinvio");

    public final StringPath statoP1 = createString("statoP1");

    public final StringPath statoP2 = createString("statoP2");

    public final StringPath statoP3 = createString("statoP3");

    public QFmFlowExtstdConfigDO(String variable) {
        super(FmFlowExtstdConfigDO.class, forVariable(variable));
    }

    public QFmFlowExtstdConfigDO(Path<? extends FmFlowExtstdConfigDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFmFlowExtstdConfigDO(PathMetadata metadata) {
        super(FmFlowExtstdConfigDO.class, metadata);
    }

}

