package it.eng.care.domain.flow.jobs.jobs.fistLevelValidation;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.drools.model.rule.RuleType;
import it.eng.care.domain.flow.drools.model.status.ValidationResultWrapper;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;
import it.eng.care.domain.flow.jobs.service.JobService;

/**
 * Job di validazione dei flussi importati mediante tabelle trasversali
 * 
 * Il job recupera le richieste FM_FLOW_IMPORTING_REQUEST in stato=TERMINATA_OK.
 * Fissata la richiesta da gestire, i record verranno selezionati filtrando per import_id=id della richiesta.
 * Al termine della validazione la richiesta in FM_FLOW_IMPORTING_REQUEST verrà aggiornata con stato=VALIDATED.
 * Alcune statistiche di validazione verranno salvate nella tabella FM_VALIDATION_REQUEST_RESULT.
 * 
 * Filtro di validazione
 * FM_FLOW_IMPORTING_REQUEST.status=TERMINATA_OK AND FM_FLOW_IMPORTING_REQUEST.VALIDATION_STATUS = NULL AND RECORD.EXTRACTION_ID=FM_FLOW_IMPORTING_REQUEST.ID AND RECORD.IMPORT_TYPE=FROM_TABLE
 * 
 */
@Component 
@DisallowConcurrentExecution
public class ValidateFlowImportRequestJob implements Job {

	@Autowired
	private FlowValidator flowValidator;

	@Autowired
	private JobService jobService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateFlowImportRequestJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  VALIDATE IMPORTING REQUEST JOB - START >>>>>>>>>>>>>>>>>>>>>>>");
		
		
		List<FlowImportRequestDO> loList = jobService.getImportRequestToValidate();
		if (loList != null && !loList.isEmpty()) {
			FlowImportRequestDO request = loList.get(0);
			try {
				ValidationFilter filter = new ValidationFilter();
				filter.setImportType(ImportTypeEnum.FROM_TABLE);
				filter.setImportId(request.getId());
				filter.setFlowId(request.getFlow().getId());
				filter.setVersionId(request.getVersion().getId());
				ValidationResultWrapper wrapper = flowValidator.executeValidation(filter, false, RuleType.FLOW.name());
				if(wrapper != null && !wrapper.getUnvalidable()) {
					//jobService.deleteOldResults(request.getId());
					jobService.markImportRequestAsValidated(request.getId(), null);
				} else {
					jobService.markImportRequest(request.getId(), "NOT_VALIDATED");
					LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  VALIDATE IMPORTING REQUEST JOB - RISULTATI VALIDAZIONE ASSENTI >>>>>>>>>>>>>>>>>>>>>>>");
				}
			} catch (Exception ex) {
				jobService.markImportRequest(request.getId(), "UNVALIDABLE");
				LogUtil.logException(LOGGER, "<<<<<<<<<<<<<<<<<<<<<<<  VALIDATE IMPORTING REQUEST JOB - ERROR >>>>>>>>>>>>>>>>>>>>>>>", ex);
//				ex.printStackTrace();
			}
		}
		
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  VALIDATE IMPORTING REQUEST JOB - END >>>>>>>>>>>>>>>>>>>>>>>");
	}

}
