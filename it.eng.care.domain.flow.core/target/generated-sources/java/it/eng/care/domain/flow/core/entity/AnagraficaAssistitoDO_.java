package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AnagraficaAssistitoDO.class)
public abstract class AnagraficaAssistitoDO_ {

	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> comunenascita;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, Byte> abilitazione;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> codicePaziente;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> cognome;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> aslResidenza;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> sesso;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> comuneResidenza;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> nazionalita;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> nome;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> id;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, Date> datanascita;
	public static volatile SingularAttribute<AnagraficaAssistitoDO, String> codiceFiscale;

	public static final String COMUNENASCITA = "comunenascita";
	public static final String ABILITAZIONE = "abilitazione";
	public static final String CODICE_PAZIENTE = "codicePaziente";
	public static final String COGNOME = "cognome";
	public static final String ASL_RESIDENZA = "aslResidenza";
	public static final String SESSO = "sesso";
	public static final String COMUNE_RESIDENZA = "comuneResidenza";
	public static final String NAZIONALITA = "nazionalita";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String DATANASCITA = "datanascita";
	public static final String CODICE_FISCALE = "codiceFiscale";

}

