package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SearchPatientFieldDO.class)
public abstract class SearchPatientFieldDO_ {

	public static volatile SingularAttribute<SearchPatientFieldDO, String> filtroRicerca;
	public static volatile SingularAttribute<SearchPatientFieldDO, String> codiceAzienda;
	public static volatile SingularAttribute<SearchPatientFieldDO, String> campoTracciato;
	public static volatile SingularAttribute<SearchPatientFieldDO, String> campoFunzione;
	public static volatile SingularAttribute<SearchPatientFieldDO, String> flusso;
	public static volatile SingularAttribute<SearchPatientFieldDO, String> id;

	public static final String FILTRO_RICERCA = "filtroRicerca";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String CAMPO_TRACCIATO = "campoTracciato";
	public static final String CAMPO_FUNZIONE = "campoFunzione";
	public static final String FLUSSO = "flusso";
	public static final String ID = "id";

}

