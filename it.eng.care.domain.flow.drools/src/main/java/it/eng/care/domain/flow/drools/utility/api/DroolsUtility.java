package it.eng.care.domain.flow.drools.utility.api;

import it.eng.care.domain.flow.drools.model.row.DroolsError;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.model.row.Row;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;

public interface DroolsUtility {

	/**
	 * Aggiunge un messaggio di errore
	 * 
	 * @param bean       righe in validazione
	 * @param rowId      id della riga validata
	 * @param messageId  id del messaggio di errore
	 * @param fieldName  nome del campo errato
	 * @param fieldValue valore del campo errato
	 * @param section
	 */
	public void addErrorMessage(ValidationBean bean, Row row, String messageId, Field field, Object fieldValue);

	/**
	 * Aggiunge un messaggio di errore
	 * 
	 * @param bean  righe in validazione
	 * @param error contiene rowId, messageId, fieldName e fieldValue
	 */
	public void addErrorMessage(ValidationBean bean, DroolsError error);

	public void addErrorMessage(ValidationBean bean, Row row, String messageId, Field field);

	@Deprecated
	public void addErrorMessage(ValidationBean bean, Row row, String messageId, String fieldName, Object fieldValue);
	
}
