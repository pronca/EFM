package it.eng.care.domain.flow.drools.utility.api;

import it.eng.care.domain.flow.drools.model.row.Field;

public interface DroolsStringUtility {
	
	/**
	 * Estrae una sottostringa dal valore di un campo
	 * 
	 * @param field      campo
	 * @param startIndex posizione di partenza
	 * @param size       numero di caratteri da estrarre
	 * @return sottostringa
	 */
	public String substring(Field field, Integer beginIndex, Integer endIndex);

	/**
	 * Verifica se il valore di un campo rispetta un formato
	 * 
	 * @param field  campo da valutare
	 * @param regexp formato del campo (regular expression)
	 * @return true se il valore del campo rispetta il formato desiderato, false
	 *         altrimenti
	 */
	public Boolean matches(Field field, String regexp);

	/**
	 * Verifica se il valore di un campo è assente
	 * 
	 * @param field campo da verificare
	 * @return restituisce true se il campo è vuoto, false altrimenti
	 */
	public Boolean isEmpty(Field field);

	/**
	 * Confronta i valori di due campi.
	 * 
	 * @param fieldA
	 * @param fieldB
	 * @return restituisce true se i valori sono uguali, false altrimenti
	 */
	public Boolean equals(Field fieldA, Field fieldB);
	
	/**
	 * Confronta i valori di due campi. Il confronto è CASE INSENSITIVE
	 * 
	 * @param fieldA
	 * @param fieldB
	 * @param ignoreCase
	 * @return restituisce true se i valori sono uguali, false altrimenti
	 */
	public Boolean equalsIgnoreCase(Field fieldA, Field fieldB);
	
	/**
	 * Confronta il valore di fieldA con una stringa.
	 * @param fieldA
	 * @param string
	 * @return
	 */
	public Boolean equals(Field fieldA, String string);
	
	/**
	 * Confronta il valore di fieldA con una stringa. Il confronto è CASE INSENSITIVE
	 * @param fieldA
	 * @param string
	 * @param ignoreCase
	 * @return
	 */
	public Boolean equalsIgnoreCase(Field fieldA, String string);

	/**
	 * Verifica se il valore di un campo è contenuto in un insieme di valori
	 * 
	 * @param field  campo da verificare
	 * @param values insieme dei valori
	 * @return restituisce true se il valore del campo appartiene all'insieme di
	 *         riferimento, false altrimenti
	 */
	public Boolean in(Field field, String[] values);

	/**
	 * Verifica se il valore di un campo inizia con il prefisso desiderato
	 * 
	 * @param field  campo da verificare
	 * @param prefix prefisso da utilizzare
	 * @return restituisce true se il campo inizia con il prefiso desiderato, false
	 *         altrimenti
	 */
	public Boolean startWith(Field field, String prefix);
	
	/**
	 * Converte il valore di un campo in un intero
	 * @param field
	 * @return
	 */
	public Integer toInt(Field field);
	
	/**
	 * Converte il valore di un campo in un intero
	 * @param field
	 * @return
	 */
	public Integer toInt(String field);
	
	/**
	 * Converte il valore di un campo in un double. La virgola viene sostituita con il punto
	 * @param field
	 * @return
	 */
	public Double toDouble(Field field);
	
	/**
	 * Converte il valore di un campo in un double. La virgola viene sostituita con il punto
	 * @param field
	 * @return
	 */
	public Double toDouble(String field);
	
	/**
	 * Verifica se il valore di un campo rappresenta un intero
	 * @return
	 */
	public Boolean isInt(Field field);
	
	/**
	 * Verifica se il valore di un campo rappresenta un intero
	 * @return
	 */
	public Boolean isInt(String field);
	
	/**
	 * Verifica se il valore di un campo rappresenta un double. La virgola viene sostituita con il punto
	 * @param field
	 * @return
	 */
	public Boolean isDouble(Field field);
	
	/**
	 * Verifica se il valore di un campo rappresenta un double. La virgola viene sostituita con il punto
	 * @param field
	 * @return
	 */
	public Boolean isDouble(String field);

}
