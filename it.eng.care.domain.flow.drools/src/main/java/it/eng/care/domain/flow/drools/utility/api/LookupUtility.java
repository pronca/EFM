package it.eng.care.domain.flow.drools.utility.api;

import java.util.Date;
import java.util.List;

import it.eng.care.domain.flow.drools.model.lookup.LookupRow;

/**
 * Utility di validazione dei record
 *
 */
public interface LookupUtility {

	/**
	 * Carica una tabella di anagrafica
	 * 
	 * @param tableName nome della tabella.
	 * @return esito del caricamento. Eventuali tabelle già caricate verranno
	 *         sostituite
	 */
	public Boolean loadLookup(String tableName);

	/**
	 * Carica una tabella di anagrafica
	 * 
	 * @param tableName  nome della tabella
	 * @param tableAlias alias della tabella da utilizzare nelle regole di
	 *                   validazione e come chiave identificativa della lookup. Per
	 *                   poter caricare più volte la stessa tabella, ma con filtri
	 *                   differenti, utilizzare tale parametro.
	 * @return esito del caricamento. Eventuali tabelle già caricate verranno
	 *         sostituite
	 */
	public Boolean loadLookup(String tableName, String tableAlias, Date referenceDate);

	/**
	 * Carica una tabella di anagrafica
	 * 
	 * @param tableName     nome della tabella
	 * @param tableAlias    alias della tabella da utilizzare nelle regole di
	 *                      validazione e come chiave identificativa della lookup.
	 *                      Per poter caricare più volte la stessa tabella, ma con
	 *                      filtri differenti, utilizzare tale parametro.
	 * @param filter        filtro da applicare alla tabella. L'oggetto contiene la
	 *                      lista dei campi della tabella con i rispettivi valore.
	 *                      Le singole condizioni verranno applicate in congiunzione
	 *                      tra loro
	 * @param referenceDate Data di riferimento della prestazione usata come filtro
	 *                      sui campi dt_enable e dt_disable della lookup
	 * @return esito del caricamento. Eventuali tabelle già caricate verranno
	 *         sostituite
	 */
	public Boolean loadLookup(String tableName, String tableAlias, String[][] filter, Date referenceDate);

	/**
	 * Verifica l'esistenza di record aderenti al filtro di ricerca
	 * 
	 * @param lookupName    nome della lookup (Nome tabella o alias impostato in
	 *                      fase di caricamento)
	 * @param filter        filtro da applicare alla tabella. L'oggetto contiene la
	 *                      lista dei campi della tabella con i rispettivi valore.
	 *                      Le singole condizioni verranno applicate in congiunzione
	 *                      tra loro
	 * @param referenceDate Data di riferimento della prestazione usata come filtro
	 *                      sui campi dt_enable e dt_disable della lookup
	 * @return true se esiste almeno un record aderente al filtro di ricerca, false
	 *         altrimenti
	 */
	public Boolean lookupExists(String lookupName, String[][] filter, Date referenceDate);

	/**
	 * Verifica l'esistenza di record aderenti al filtro di ricerca
	 * 
	 * @param lookupName nome della lookup (Nome tabella o alias impostato in fase
	 *                   di caricamento)
	 * @param filter     filtro da applicare alla tabella. L'oggetto contiene la
	 *                   lista dei campi della tabella con i rispettivi valore. Le
	 *                   singole condizioni verranno applicate in congiunzione tra
	 *                   loro
	 * @return true se esiste almeno un record aderente al filtro di ricerca, false
	 *         altrimenti
	 */
	public Boolean lookupExists(String lookupName, String[][] filter);

	/**
	 * Restituisce i record di una anagrafica aderenti al filtro di ricerca
	 * 
	 * @param lookupName nome della tabella
	 * @param filter     filtro da applicare alla tabella. L'oggetto contiene la
	 *                   lista dei campi della tabella con i rispettivi valore. Le
	 *                   singole condizioni verranno applicate in congiunzione tra
	 *                   loro
	 * @return restituisce una lista di righe contenente una lista di campi (nome
	 *         campo, valore)
	 */
	public List<LookupRow> lookupSelect(String lookupName, String[][] filter, Date referenceDate);

	/**
	 * Restituisce il primo record di una anagrafica aderente al filtro di ricerca
	 * 
	 * @param lookupName nome della tabella
	 * @param filter     filtro da applicare alla tabella. L'oggetto contiene la
	 *                   lista dei campi della tabella con i rispettivi valore. Le
	 *                   singole condizioni verranno applicate in congiunzione tra
	 *                   loro
	 * @return riga trovata
	 */
	public LookupRow lookupSelectFirst(String lookupName, String[][] filter, Date referenceDate);

	/**
	 * Svuota la mappa delle lookup
	 */
	public void resetLookupMap();

	/**
	 * Carica una tabella di anagrafica tramite query sql
	 * 
	 * @param fields campi da selezionare
	 * @param table tabella di riferimento
	 * @param filterFields campi usati nella where condition della query
	 * @param filterValues valore dei campi @filterFields
	 * @param alias della tabella da utilizzare nelle regole di
	 *                   validazione e come chiave identificativa della lookup.
	 * @return esito del caricamento. Eventuali tabelle già caricate verranno
	 *         sostituite
	 */
	public Boolean loadLookupBySql(String fields, String table, String[] filterFields, String[] filterValues, String alias);

	/**
	 * 
	 * Verifica l'esistenza di record in una lookup caricata tramite la funzione @loadLookupBySql
	 * 
	 * @param lookupName nome dela lookup
	 * @param selectFields elenco dei valori corrispondente ai campi @fields dichiarati 
	 * 		  in fase di caricamento tramite funzione @loadLookupBySql
	 * @return
	 */
	public Boolean sqlLookupExists(String lookupName, String[] selectFields);

}
