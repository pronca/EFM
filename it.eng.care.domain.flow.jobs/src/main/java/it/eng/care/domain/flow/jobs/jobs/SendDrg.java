package it.eng.care.domain.flow.jobs.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.b2b.service.SendDrgService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.jobs.look.LockJob;

@DisallowConcurrentExecution
public class SendDrg implements Job{

	@Autowired
	SendDrgService sendDrgService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowExportDelete.class);
	
	@Override
	@LockJob(entity = "sendDrgJob", untilCfg = "SendDrg.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try {
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< START SENDING DRG >>>>>>>>>>>>>>>>>>>>>>>");
			sendDrgService.getPendingDrgAndSend();
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< END SENDING DRG >>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}
		
	}
	
}