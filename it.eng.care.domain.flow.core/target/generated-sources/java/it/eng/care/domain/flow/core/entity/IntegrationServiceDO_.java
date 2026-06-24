package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IntegrationServiceDO.class)
public abstract class IntegrationServiceDO_ {

	public static volatile SingularAttribute<IntegrationServiceDO, Integer> abilitazione;
	public static volatile SingularAttribute<IntegrationServiceDO, String> codiceAzienda;
	public static volatile SingularAttribute<IntegrationServiceDO, String> flusso;
	public static volatile SingularAttribute<IntegrationServiceDO, String> nome;
	public static volatile SingularAttribute<IntegrationServiceDO, String> id;
	public static volatile SingularAttribute<IntegrationServiceDO, String> url;
	public static volatile SingularAttribute<IntegrationServiceDO, String> verticale;

	public static final String ABILITAZIONE = "abilitazione";
	public static final String CODICE_AZIENDA = "codiceAzienda";
	public static final String FLUSSO = "flusso";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String URL = "url";
	public static final String VERTICALE = "verticale";

}

