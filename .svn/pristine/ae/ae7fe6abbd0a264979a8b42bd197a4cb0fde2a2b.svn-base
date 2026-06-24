package it.eng.care.domain.flow.jobs.job;

import it.eng.care.domain.flow.jobs.look.LockJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.eng.care.domain.flow.jobs.service.api.DashboardJobService;


@Component
@DisallowConcurrentExecution
public class DashboardJob implements Job {

	@Autowired
	private DashboardJobService dashboardJobService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardJob.class);
	
	@Override
	@LockJob(entity = "DashboardJob", untilCfg = "DashboardJob.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  DASHBOARD COUNT JOB - START >>>>>>>>>>>>>>>>>>>>>>>");
    	dashboardJobService.dashboardCount(false);
    	LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  DASHBOARD COUNT JOB - STOP >>>>>>>>>>>>>>>>>>>>>>>");
	}

}