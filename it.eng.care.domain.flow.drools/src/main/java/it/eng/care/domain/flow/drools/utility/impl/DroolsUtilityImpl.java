package it.eng.care.domain.flow.drools.utility.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.care.domain.flow.core.entity.ErrorMessageDO;
import it.eng.care.domain.flow.drools.model.row.DroolsError;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.model.row.Row;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.model.status.BeanValidationStatusEnum;
import it.eng.care.domain.flow.drools.utility.api.DroolsUtility;

public class DroolsUtilityImpl implements DroolsUtility {

	private Map<String, ErrorMessageDO> msgMap;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LookupUtilityImpl.class);

	public DroolsUtilityImpl(Map<String, ErrorMessageDO> msgMap) {
		this.msgMap = msgMap;
	}
	
	@Override
	@Deprecated
	public void addErrorMessage(ValidationBean bean, Row row, String messageId, String fieldName, Object fieldValue) {
		Field field = new Field(fieldName, fieldValue);
		addErrorMessage(bean, row, messageId, field, field.getValue());
	}
	
	@Override
	public void addErrorMessage(ValidationBean bean, Row row, String messageId, Field field) {
		addErrorMessage(bean, row, messageId, field, field.getValue());
	}

	@Override
	public void addErrorMessage(ValidationBean bean, Row row, String messageId, Field field, Object fieldValue) {
		ErrorMessageDO msg = getMessage(messageId);
		
		if(msg == null) {
			LOGGER.error("<<<<<<<<<<<<<<<<<<<<<<<< MESSAGGIO DI ERRORE NON PRESENTE IN ANAGRAFICA : " + messageId + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			return;
		}
		
		String msgSeverity = msg.getSeverity();
		Integer msgSeverityW = BeanValidationStatusEnum.valueOf(msgSeverity).getWeight();

		Integer rowSeverityW = null;
		if (row.getSeverity() != null) {
			rowSeverityW = BeanValidationStatusEnum.valueOf(row.getSeverity()).getWeight();
		}

		// aggiorna lo stato di validazione del record assegnando la severity più alta
		// tra tutte quelle associate ai messaggi di errore segnalati dalle regole di
		// validazione
		if (rowSeverityW == null || msgSeverityW.intValue() > rowSeverityW.intValue()) {
			row.setSeverity(msgSeverity);
		}

		// aggiorna lo stato di validazione della pratica assegnando la severity più
		// alta tra tutte quelle associate ai record della pratica
		if (bean.getValidationStatus() != null) {
			BeanValidationStatusEnum rowSeverityB = BeanValidationStatusEnum.valueOf(row.getSeverity());
			if (bean.getValidationStatus().getWeight().intValue() < rowSeverityB.getWeight().intValue()) {
				bean.setValidationStatus(rowSeverityB);
			}
		} else {
			bean.setValidationStatus(BeanValidationStatusEnum.valueOf(row.getSeverity()));
		}

		row.setIsValid(false);

		addErrorMessage(bean, new DroolsError(row, msg, field.getName(), fieldValue, msgSeverity,
				bean.getRowGroupMap()));
	}

	@Override
	public void addErrorMessage(ValidationBean bean, DroolsError error) {
		bean.addErrorMessage(error);
	}

	public ErrorMessageDO getMessage(String messageId) {
		if (this.msgMap != null) {
			ErrorMessageDO msg = this.msgMap.get(messageId);
			if(msg != null) {
				return msg;
			}
		}
		
		ErrorMessageDO msg = new ErrorMessageDO();
		msg.setId(messageId);
		msg.setSeverity(BeanValidationStatusEnum.ERROR.name());
		msg.setDescription(messageId);
		
		return msg;
		/*ErrorMessageDO msg = new ErrorMessageDO();
		msg.setId("INVALID" + UUID.randomUUID().toString());
		msg.setSeverity(BeanValidationStatusEnum.VALID.name());
		msg.setDescription("Errore segnalato inesistente");
		return msg;*/
	}

}
