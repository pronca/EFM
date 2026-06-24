package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIntegrationServiceDO is a Querydsl query type for IntegrationServiceDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIntegrationServiceDO extends EntityPathBase<IntegrationServiceDO> {

    private static final long serialVersionUID = -1845305528L;

    public static final QIntegrationServiceDO integrationServiceDO = new QIntegrationServiceDO("integrationServiceDO");

    public final NumberPath<Integer> abilitazione = createNumber("abilitazione", Integer.class);

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath flusso = createString("flusso");

    public final StringPath id = createString("id");

    public final StringPath nome = createString("nome");

    public final StringPath url = createString("url");

    public final StringPath verticale = createString("verticale");

    public QIntegrationServiceDO(String variable) {
        super(IntegrationServiceDO.class, forVariable(variable));
    }

    public QIntegrationServiceDO(Path<? extends IntegrationServiceDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIntegrationServiceDO(PathMetadata metadata) {
        super(IntegrationServiceDO.class, metadata);
    }

}

