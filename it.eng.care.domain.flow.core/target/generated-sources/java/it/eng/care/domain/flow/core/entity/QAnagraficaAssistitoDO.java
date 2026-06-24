package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnagraficaAssistitoDO is a Querydsl query type for AnagraficaAssistitoDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnagraficaAssistitoDO extends EntityPathBase<AnagraficaAssistitoDO> {

    private static final long serialVersionUID = 1541519431L;

    public static final QAnagraficaAssistitoDO anagraficaAssistitoDO = new QAnagraficaAssistitoDO("anagraficaAssistitoDO");

    public final NumberPath<Byte> abilitazione = createNumber("abilitazione", Byte.class);

    public final StringPath aslResidenza = createString("aslResidenza");

    public final StringPath codiceFiscale = createString("codiceFiscale");

    public final StringPath codicePaziente = createString("codicePaziente");

    public final StringPath cognome = createString("cognome");

    public final StringPath comunenascita = createString("comunenascita");

    public final StringPath comuneResidenza = createString("comuneResidenza");

    public final DateTimePath<java.util.Date> datanascita = createDateTime("datanascita", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath nazionalita = createString("nazionalita");

    public final StringPath nome = createString("nome");

    public final StringPath sesso = createString("sesso");

    public QAnagraficaAssistitoDO(String variable) {
        super(AnagraficaAssistitoDO.class, forVariable(variable));
    }

    public QAnagraficaAssistitoDO(Path<? extends AnagraficaAssistitoDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnagraficaAssistitoDO(PathMetadata metadata) {
        super(AnagraficaAssistitoDO.class, metadata);
    }

}

