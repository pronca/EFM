package it.eng.care.domain.flow.drools.utility.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.service.impl.DashboardServiceImpl;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.crypt.CryptoManager;
import it.eng.care.domain.flow.drools.model.row.Row;
import it.eng.care.domain.flow.drools.model.row.Section;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.utility.api.RowConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowConverterImpl implements RowConverter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RowConverterImpl.class);
	
	@Autowired
	private CryptoManager cryptoManager;

	@Override
	public ValidationBean convertRows(List<Map<String, Object>> rowsMap, FormFlowDTO configuration, String flowPrefix,
			List<FormFlowTableFieldDTO> pkListCfg) {
		ValidationBean bean = new ValidationBean();
		List<Section> secs = new ArrayList<Section>();
		bean.setSections(secs);
		
		Map<String, Object> rowGroupMap = new HashMap<String, Object>(); // campi pratica con valore
		bean.setRowGroupMap(rowGroupMap);

		Map<Integer, Map<String, String>> elaboratedRows = new HashMap<Integer, Map<String, String>>();

		List<FormFlowTableDTO> sectionsCfg = configuration.getFlowTableList(); // sezioni del flusso
		for (FormFlowTableDTO sectionCfg : sectionsCfg) {
			String sectionName = flowPrefix + sectionCfg.getSection();
			Section section = new Section(sectionCfg.getSection(), sectionName, sectionCfg.getDescription());
			List<Row> rows = new ArrayList<Row>();
			section.setRows(rows);
			secs.add(section);
			elaboratedRows.put(section.getIndex(), new HashMap<String, String>());
		}

		if (rowsMap != null && !rowsMap.isEmpty()) {
			for (Map<String, Object> rowMap : rowsMap) {
				for (Section section : secs) {
					// recupera tutti i campi della sezione corrente e crea un oggetto di tipo Row //
					Map<String, Object> rowIdMap = new HashMap<String, Object>();  // campi chiave con valore
					Row row = new Row();
					
					// le chiavi della mappa sono gli alias delle colonne restituite dalla query. //
					// il patter degli alias è il seguente: "s" + {INDICE SEZIONE} + "_" + {POSIZIONE DEL CAMPO} + "_" + prime lettere del nome della colonna //
					Set<String> columnAliasList = rowMap.keySet();
					for (String columnAlias : columnAliasList) {
						if(columnAlias.equals("extractionId")) {
							String extractionId = (String)rowMap.get(columnAlias);
							bean.setExtractionId(extractionId);
						} else {
							// verifica se il campo appartiene alla sezione corrente. //
							// la ricerca avviene tenendo conto dell'algoritmo di generazione degli alias delle colonne descritto sopra //
							if(columnAlias.toLowerCase().startsWith("s" + section.getIndex())) {
								if(columnAlias.equals("S1_0_CODICEAZIE")) {
									String codiceAzienda = (String)rowMap.get(columnAlias);
									bean.setCodiceAzienda(codiceAzienda);
								}
								// recupera il valore del campo dalla mappa del record
								Object fieldValue = rowMap.get(columnAlias); 
								// recupera la definizione del campo dalla configurazione del flusso
								FormFlowTableFieldDTO field = getFieldNameFromAlias(columnAlias, configuration);
								String fieldName = field !=null ? field.getName() : "";
								if(field!=null && field.isCrypto()) {
									try {
										fieldValue = cryptoManager.decryptObject(fieldValue);
									} catch (RuntimeException ex) {

							            String v = (fieldValue == null ? null : String.valueOf(fieldValue));
							            int len = (v == null ? 0 : v.length());
							            boolean hexLike = v != null && v.matches("(?i)^[0-9a-f]+$");
							            boolean lenOk = (len % 32 == 0);
							            
							            LogUtil.logException(LOGGER, "Decrypt failed. fieldValue="+fieldValue+" flowField="+field.getName()+" section="+field.getSection()+" alias="+columnAlias+" valueLen="+len+" hexLike="+hexLike+" lenMultipleOf32="+lenOk+"", ex);
							            
							            throw ex;
							        }
								}
								
								row.addField(fieldName, fieldValue);
								
								if (field != null && field.isPk()) {
								    // inserisco nella rowIdMap solo PK valorizzate
								    if (fieldValue != null && !(fieldValue instanceof String && ((String) fieldValue).trim().isEmpty())) {
								        rowIdMap.put(fieldName, fieldValue);
								    }

								    // i campi chiave della prima sezione sono anche i campi chiave della pratica
								    if (field.getSection() != null
								            && field.getSection().intValue() == 0
								            && fieldValue != null
								            && !(fieldValue instanceof String && ((String) fieldValue).trim().isEmpty())) {
								        rowGroupMap.put(fieldName, fieldValue);
								    }
								}
								
								// recupera la data di riferimento del flusso e i campi pratica
								if(field.isReferenceDate()) {
									
									if(fieldValue != null && fieldValue instanceof Date) {
										// la data di riferimento della pratica è il max tra tutte le date di riferimento dei singoli record
										if(bean.getReferenceDate() == null || bean.getReferenceDate().compareTo((Date)fieldValue) < 0) {
											bean.setReferenceDate((Date)fieldValue);
										}
									}
									
								}
								
								// recupera i campi pratica
								//if(field.getGroups() && fieldValue != null) {
								//	rowGroupMap.put(fieldName, fieldValue);
								//}
							}
						}
					}
					
					row.setRowIdMap(rowIdMap);
					row.setSection(section);
					
					if (!elaboratedRows.get(section.getIndex()).containsKey(row.toString())) {
						if(!row.isEmpty()) {
							section.getRows().add(row);
							elaboratedRows.get(section.getIndex()).put(row.toString(), "");
						}
					}
				}

			}
		}
		
		elaboratedRows.clear();
		elaboratedRows = null;

		return bean;
	}
	
	/**
	 * Restituisce il nome del campo cercando la sua definizione nella configurazione del flusso.
	 * La ricerca avviene sfruttando il pattern rispettato dagli alias delle colonne restituite dalla query di ricerca.
	 * Il pattern degli alias è il seguente: "s" + {INDICE SEZIONE FLUSSO} + "_" + {POSIZIONE DEL CAMPO} + "_" + prime lettere del nome del campo.
	 * L'individuazione del campo avviene sfruttando l'univicità della coppia (INDICE_SEZIONE;POSIZIONE_CAMPO)
	 *  
	 * @param key
	 * @param configuration
	 * @return
	 */
	private FormFlowTableFieldDTO getFieldNameFromAlias(String key, FormFlowDTO configuration) {
		for(FormFlowTableDTO table : configuration.getFlowTableList()) {
			if(key.toLowerCase().startsWith("s" + table.getSection())) {
				for(FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
					if(key.toLowerCase().startsWith("s" + table.getSection() + "_" + field.getPosition() + "_")) {
						return field;
					}
				}
			}
		}
		
		return null;
	}
	
}
