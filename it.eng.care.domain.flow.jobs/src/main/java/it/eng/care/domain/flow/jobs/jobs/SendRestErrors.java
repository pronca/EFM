package it.eng.care.domain.flow.jobs.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.jobs.look.LockJob;
import it.eng.care.domain.flow.jobs.service.JobSendErrorService;

@DisallowConcurrentExecution
public class SendRestErrors implements Job{

	@Autowired
	JobSendErrorService sendError;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendRestErrors.class);
	
	@Override
	@LockJob(entity = "SendRestErrors", untilCfg = "SendRestErrors.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try {
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< START SENDING ERROR >>>>>>>>>>>>>>>>>>>>>>>");
			sendError.getPendingErrorAndSend();
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< END SENDING ERROR >>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.logException(LOGGER, "", e);;
//			e.printStackTrace();
		}
		
	}

	
	
}
