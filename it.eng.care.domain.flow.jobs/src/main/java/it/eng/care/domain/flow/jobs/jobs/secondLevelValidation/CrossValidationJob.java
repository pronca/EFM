package it.eng.care.domain.flow.jobs.jobs.secondLevelValidation;

import java.util.List;
import java.util.Set;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.SecondLevelValidationRequestDO;
import it.eng.care.domain.flow.core.enumeration.ImportTypeEnum;
import it.eng.care.domain.flow.core.enumeration.JobExecutionStatus;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.domain.flow.drools.model.rule.RuleType;
import it.eng.care.domain.flow.drools.model.status.BeanValidationStatusEnum;
import it.eng.care.domain.flow.drools.model.status.ValidationResultWrapper;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@DisallowConcurrentExecution
public class CrossValidationJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrossValidationJob.class);

	@Autowired
	private FlowValidator flowValidator;
	
	@Autowired
	private FlowRegionUnionService flowRegionUnionService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	}

}
