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
public class DashboardJobYear implements Job {

	@Autowired
	private DashboardJobService dashboardJobService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardJobYear.class);
	
	@Override
	@LockJob(entity = "DashboardJobYear", untilCfg = "DashboardJobYear.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  DASHBOARD YEAR COUNT JOB - START >>>>>>>>>>>>>>>>>>>>>>>");
    	dashboardJobService.dashboardCount(true);
    	LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  DASHBOARD YEAR COUNT JOB - STOP >>>>>>>>>>>>>>>>>>>>>>>");
	}

}