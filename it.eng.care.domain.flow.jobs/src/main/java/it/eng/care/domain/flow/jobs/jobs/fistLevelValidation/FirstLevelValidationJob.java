package it.eng.care.domain.flow.jobs.jobs.fistLevelValidation;

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
public class FirstLevelValidationJob implements Job {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FirstLevelValidationJob.class);
	
	@Autowired
	private ValidateRestServiceFlowJob restValidationJob;
	
	//@Autowired
	//private ResetValidationJob resetValidationJob;
	
//	@Autowired
//	private ValidateFlowImportRequestJob validateFlowImportRequestJob;
	
//	@Autowired
//	private ValidateUploadedWithFileFlowJob validateUploadedWithFileFlowJob;
	
	@Override
	@LockJob(entity = "FirstLevelJobDetail", untilCfg = "FirstLevelValidationJob.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			restValidationJob.execute(null);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
		}
		
//      creato un job a parte denominato tableValidationTimeUploadJob che punta su fm_configuration = table_validation_time per importazioni da caricamento file e importazione dati
//		try {
//			validateFlowImportRequestJob.execute(null);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
//		try {
//			validateUploadedWithFileFlowJob.execute(null);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
	}

}
