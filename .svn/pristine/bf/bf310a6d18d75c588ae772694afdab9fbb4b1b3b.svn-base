package it.eng.care.domain.flow.drools.service.api;

import java.sql.SQLException;
import java.util.List;

import it.eng.care.domain.flow.b2b.model.ErrorModelDTO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.entity.ResetValidationRequestDO;
import it.eng.care.domain.flow.core.entity.SecondLevelValidationRequestDO;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.model.status.ValidationResultWrapper;

public interface FlowValidator {

	/**
	 * Esegue la validazione di un flusso/record/estrazione
	 * 
	 * @param request
	 * @return
	 */
	public ValidationResultWrapper executeValidation(ValidationFilter request, Boolean sendResult, String ruleType);

	/**
	 * Annulla la validazione di un flusso eliminando i messaggi di errore e resettando lo stato di validazione di un flusso
	 * 
	 * @param request
	 * @throws SQLException 
	 */
	public void resetValidation(ValidationFilter request);

	/**
	 * Invia l'esito di una validazione al verticale che ha inviato le pratiche
	 * 
	 * @param validatedBeanList
	 * @param flowName
	 * @param extractionId
	 */
	public void sendResults(List<ValidationBean> validatedBeanList, String flowName);

	/**
	 * Recupera gli errori di validazione di una richiesta di estrazione
	 * 
	 * @param extractionId
	 * @param flowName
	 * @param versionName
	 * @return
	 */
	public ErrorModelDTO buidErrorsToSend(String extractionId, String flowName, String versionName);

	/**
	 * Crea una richiesta di annullamento validazione che verrà gestita dal relativo job
	 * @param flowId
	 * @param month
	 * @param year
	 */
	public String createResetValidationRequest(String flowId, String month, String year);
	
	public List<ResetValidationRequestDO> getResetValidationRequests();
	
	public List<SecondLevelValidationRequestDO> getCrossValidationRequests();
	
	public String createSecondLevelValidationRequest(String flowId, String month, String year);

	public Boolean resetValidationByMonthAndYear(String requestId, Boolean crossValidation);

	public void changeResetValidationRequestStatus(String id, String status);

	public Boolean secondLevelValidationByMonthAndYear(String requestId);

	public ValidationResultWrapper executeValidation(ValidationFilter request, Boolean sendResult, String ruleType,
			SecondLevelValidationRequestDO secLevReq);

	public void changeSecondLevelValidationRequestStatus(String id, String status);

	public Boolean isTerminatedResetValidationRequests(String id);

	public Boolean isTerminatedSecondLevelValidationRequests(String id);

	public ResetValidationRequestDO getResetValidationRequest(String id);

	public SecondLevelValidationRequestDO getCrossValidationRequest(String id);

	public Boolean resetValidationByMonthAndYear(String flowId, String versionId, String month, String year,
			Boolean crossValidation) throws SQLException;
	
	public FlowOperationResult<?> sendAuditAvviaValidazioneToPM (ValidationFilter validationFilter, FlowOperationResult<?> result);
	
}
