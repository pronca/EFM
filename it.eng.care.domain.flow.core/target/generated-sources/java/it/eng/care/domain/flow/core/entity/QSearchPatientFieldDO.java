package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSearchPatientFieldDO is a Querydsl query type for SearchPatientFieldDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSearchPatientFieldDO extends EntityPathBase<SearchPatientFieldDO> {

    private static final long serialVersionUID = -184836924L;

    public static final QSearchPatientFieldDO searchPatientFieldDO = new QSearchPatientFieldDO("searchPatientFieldDO");

    public final StringPath campoFunzione = createString("campoFunzione");

    public final StringPath campoTracciato = createString("campoTracciato");

    public final StringPath codiceAzienda = createString("codiceAzienda");

    public final StringPath filtroRicerca = createString("filtroRicerca");

    public final StringPath flusso = createString("flusso");

    public final StringPath id = createString("id");

    public QSearchPatientFieldDO(String variable) {
        super(SearchPatientFieldDO.class, forVariable(variable));
    }

    public QSearchPatientFieldDO(Path<? extends SearchPatientFieldDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSearchPatientFieldDO(PathMetadata metadata) {
        super(SearchPatientFieldDO.class, metadata);
    }

}

