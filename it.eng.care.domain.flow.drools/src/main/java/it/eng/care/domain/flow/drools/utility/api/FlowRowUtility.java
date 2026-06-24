package it.eng.care.domain.flow.drools.utility.api;

import java.util.List;

import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.model.row.Row;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;

/**
 * Utility di interrogazione dei record da validare
 *
 */
public interface FlowRowUtility {

	/**
	 * Restituisce la riga {rowIndex} della sezione {sectionIndex} 
	 * 
	 * @param bean
	 * @param rowIndex
	 * @param sectionIndex
	 * @return
	 */
	public Row getRow(ValidationBean bean, Integer rowIndex, Integer sectionIndex);

	/**
	 * Restituisce il valore del campo
	 * 
	 * @param bean         Contiene i record da validare
	 * @param rowIndex     indice della riga
	 * @param sectionIndex indice della sezione che contiene il campo
	 * @param fieldName    nome del campo
	 * @return
	 */
	public Object getFieldValue(ValidationBean bean, Integer rowIndex, Integer sectionIndex, String fieldName);
	
	/**
	 * Restituisce il valore del campo. Se il il valore è null o il campo non esiste, restituisce il parametro @replacement
	 * 
	 * @param bean         Contiene i record da validare
	 * @param rowIndex     indice della riga
	 * @param sectionIndex indice della sezione che contiene il campo
	 * @param fieldName    nome del campo
	 * @return
	 */
	public Object nvlFieldValue(ValidationBean bean, Integer rowIndex, Integer sectionIndex, String fieldName, Object replacement);
	
	/**
	 * Restituisce il valore del campo
	 * 
	 * @param bean         Contiene i record da validare
	 * @param rowIndex     indice della riga
	 * @param sectionIndex nome della sezione che contiene il campo
	 * @param fieldName    nome del campo
	 * @return
	 */
	public Object getFieldValue(ValidationBean bean, Integer rowIndex, String sectionName, String fieldName);
	
	/**
	 * Restituisce il valore del campo. Se il il valore è null o il campo non esiste, restituisce il parametro @replacement
	 * 
	 * @param bean         Contiene i record da validare
	 * @param rowIndex     indice della riga
	 * @param sectionIndex nome della sezione che contiene il campo
	 * @param fieldName    nome del campo
	 * @return
	 */
	public Object nvlFieldValue(ValidationBean bean, Integer rowIndex, String sectionName, String fieldName, Object replacement);

	/**
	 * Restituisce un campo
	 * 
	 * @param bean         Contiene i record da validare
	 * @param rowIndex     indice della riga
	 * @param sectionIndex indice della sezione che contiene il campo
	 * @param fieldName    nome del campo
	 * @return
	 */
	public Field getField(ValidationBean bean, Integer rowIndex, Integer sectionIndex, String fieldName);
	
	/**
	 * Restituisce un campo
	 * 
	 * @param bean         Contiene i record da validare
	 * @param rowIndex     indice della riga
	 * @param sectionIndex nome della sezione che contiene il campo
	 * @param fieldName    nome del campo
	 * @return
	 */
	public Field getField(ValidationBean bean, Integer rowIndex, String sectionName, String fieldName);

	/**
	 * Restituisce gli indici delle righe aderenti al filtro di ricerca
	 * 
	 * @param bean         Contiene i record da validare
	 * @param fields       filtri di ricerca (Field -> (nome campo, valore campo))
	 * @param sectionIndex indice della sezione
	 * @return
	 */
	public List<Integer> findRowIndexByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex);

	/**
	 * Restituisce le righe della sezione indicata aderenti al filtro di
	 * ricerca
	 * 
	 * @param bean         Contiene i record da validare
	 * @param fields       filtri di ricerca (Field -> (nome campo, valore campo))
	 * @param sectionIndex indice della sezione
	 * @return
	 */
	public List<Row> findRowByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex);

	/**
	 * Restituisce il primo degli indici delle righe della sezione indicata aderenti al filtro di ricerca
	 * 
	 * @param bean         Contiene i record da validare
	 * @param fields       filtri di ricerca (Field -> (nome campo, valore campo))
	 * @param sectionIndex indice della sezione
	 * @return
	 */
	public Integer findFirstRowIndexByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex);

	/**
	 * Restituisce la prima riga della sezione indicata aderente al filtro di
	 * ricerca
	 * 
	 * @param bean         Contiene i record da validare
	 * @param fields       filtri di ricerca (Field -> (nome campo, valore campo))
	 * @param sectionIndex indice della sezione
	 * @return
	 */
	public Row findFirstRowByFields(ValidationBean bean, List<Field> fields, Integer sectionIndex);

}
