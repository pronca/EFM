package it.eng.care.domain.flow.jobs.jobs.fistLevelValidation;

import java.util.List;
import java.util.Set;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.ResetValidationRequestDO;
import it.eng.care.domain.flow.core.enumeration.JobExecutionStatus;
import it.eng.care.domain.flow.drools.model.rule.RuleType;
import it.eng.care.domain.flow.drools.model.status.BeanValidationStatusEnum;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;

@DisallowConcurrentExecution
public class ResetValidationJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResetValidationJob.class);

	@Autowired
	private FlowValidator flowValidator;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	}

}
