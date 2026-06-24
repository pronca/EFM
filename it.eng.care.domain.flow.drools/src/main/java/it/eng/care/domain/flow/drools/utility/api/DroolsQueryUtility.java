package it.eng.care.domain.flow.drools.utility.api;

import java.util.List;

import it.eng.care.domain.flow.drools.model.lookup.ColumnType;

public interface DroolsQueryUtility {
	
	/**
	 * Esegue una query sql
	 * 
	 * @param sqlString  query sql
	 * @param parameters parametri della query
	 * @param type tipi dei parametri -> ColumnType(NUMBER, BOOLEAN, STRING, DATE)
	 * @return restituisce la lista dei record trovati; ogni elemento della lista è
	 *         un array contenente i campi selezionati
	 */
	public List<Object> executeQuery(String sqlString, Object[] parameters, ColumnType[] type);

	/**
	 * Esegue una query sql
	 * 
	 * @param sqlString query sql
	 * @param parametri della query
	 * @param type tipi dei parametri -> ColumnType(NUMBER, BOOLEAN, STRING, DATE)
	 * @return restituisce i campi selezionati della prima riga trovata
	 */
	public Object executeUniqueQuery(String sqlString, Object[] parameters, ColumnType[] type);

	/**
	 * Esegue una query sql; la query deve essere una COUNT >
	 * 
	 * @param sqlString  query sql
	 * @param parameters parametri della query
	 * @param type tipi dei parametri -> ColumnType(NUMBER, BOOLEAN, STRING, DATE)
	 * @return restituisce il risultato della count
	 */
	public Integer executeCount(String sqlString, Object[] parameters, ColumnType[] type);

	/**
	 * Esegue una query sql >
	 * 
	 * @param sqlString  query sql
	 * @param parameters parametri della query
	 * @param type tipi dei parametri -> ColumnType(NUMBER, BOOLEAN, STRING, DATE)
	 * @return restituisce true se il risultato della query non è vuoto, false
	 *         altrimenti
	 */
	public Boolean executeExists(String sqlString, Object[] parameters, ColumnType[] type);

}
