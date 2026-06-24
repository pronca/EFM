package it.eng.care.domain.flow.drools.utility.api;

import java.util.Date;

import it.eng.care.domain.flow.drools.model.row.Field;

public interface DroolsDateUtility {
	
	/**
	 * Converte il valore di un campo in un oggetto Date
	 * 
	 * @param field campo da convertire
	 * @return un oggetto Date
	 */
	public Date toDate(Field field, String format);

	/**
	 * Confronta due campi di tipo Date
	 * 
	 * @param fieldA
	 * @param fieldB
	 * @return restituisce 0 se le date coincidono, 1 se fieldB > fieldA, -1 se
	 *         fieldA > fieldB
	 */
	public Integer compareFieldDate(Field fieldA, Field fieldB, String format);

	/**
	 * Verifica se il valore di un campo di tipo Date è incluso nell'intervallo
	 * desiderato
	 * 
	 * @param field
	 * @param from
	 * @param to
	 * @return restituisce true se il campo è incluso nell'intervallo, false
	 *         altrimenti
	 */
	public Boolean betweenFieldDate(Field field, Date from, Date to, String format);
	
	/**
	 * Verifica se il valore di un campo di tipo Date è incluso nell'intervallo
	 * desiderato
	 * 
	 * @param field
	 * @param from
	 * @param to
	 * @return restituisce true se il campo è incluso nell'intervallo, false
	 *         altrimenti
	 */
	public Boolean betweenFieldDate(Field field, Field from, Field to, String format);

	/**
	 * Verifica se il valore di un campo rappresenta una data valida
	 * 
	 * @param field  campo da verificare
	 * @param format formato da utilizzare per la verifica del campo
	 * @return restituisce true se il campo rispetta il formato desiderato, false
	 *         altrimenti
	 */
	public Boolean isDate(Field field, String format);

	/**
	 * Calcola la differenza in giorni tra due campi data
	 * 
	 * @param fieldA
	 * @param fieldB
	 * @param format formato da utilizzare per il parse dei campi
	 * @return giorni di differenza tra le date
	 */
	public Integer diffDate(Field fieldA, Field fieldB, String format);
	
	/**
	 * Calcola la differenza in ore tra due campi data
	 * 
	 * @param fieldA
	 * @param fieldB
	 * @param format formato da utilizzare per il parse dei campi
	 * @return giorni di differenza tra le date
	 */
	public Integer diffDateInHours(Field fieldA, Field fieldB, String format);

	/**
	 * Converte un campo data in stringa nel formato indicato
	 * @param field campo data
	 * @param format formato del campo data
	 * @return
	 */
	public String dateToString(Field field, String format);
	
	/**
	 * Converte un campo data in stringa nel formato indicato
	 * @param field campo data
	 * @param formatIn formato del campo data
	 * @param formatOut formato di uscita
	 * @return
	 */
	public String dateToString(Field field, String formatIn, String formatOut);

}
