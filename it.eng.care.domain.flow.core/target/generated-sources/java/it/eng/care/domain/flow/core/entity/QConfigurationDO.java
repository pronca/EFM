package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConfigurationDO is a Querydsl query type for ConfigurationDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConfigurationDO extends EntityPathBase<ConfigurationDO> {

    private static final long serialVersionUID = -578641467L;

    public static final QConfigurationDO configurationDO = new QConfigurationDO("configurationDO");

    public final StringPath descrizione = createString("descrizione");

    public final StringPath keyId = createString("keyId");

    public final StringPath value = createString("value");

    public QConfigurationDO(String variable) {
        super(ConfigurationDO.class, forVariable(variable));
    }

    public QConfigurationDO(Path<? extends ConfigurationDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConfigurationDO(PathMetadata metadata) {
        super(ConfigurationDO.class, metadata);
    }

}

