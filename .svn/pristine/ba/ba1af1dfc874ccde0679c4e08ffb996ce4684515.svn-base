package it.eng.care.domain.flow.jobs.jobs.secondLevelValidation;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.jobs.look.LockJob;

@DisallowConcurrentExecution
public class SecondLevelValidationJob implements Job {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecondLevelValidationJob.class);
	
	@Autowired
	private CrossValidationJob crossValidationJob;
	
	@Autowired
	private ExecuteDrgJob executeDrgJob;
	
	@Autowired
	private ExecuteFlowExportRequestJob executeFlowExportRequestJob;
	
	@Autowired
	private ValidateFlowExportRequestJob validateFlowExportRequestJob;
	
	@Override
	@LockJob(entity = "SecondLevelValidationJob", untilCfg = "SecondLevelValidationJob.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			executeFlowExportRequestJob.execute(null);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
		}
		
		try {
			validateFlowExportRequestJob.execute(null);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
		}
		
		/*try {
			crossValidationJob.execute(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		
		try {
			executeDrgJob.execute(null);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
		}
		
	}

}
