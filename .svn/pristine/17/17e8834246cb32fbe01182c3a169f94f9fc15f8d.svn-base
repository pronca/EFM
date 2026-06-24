package it.eng.care.domain.flow.jobs.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.service.FlowExportRequestService;
import it.eng.care.domain.flow.core.utility.LogUtil;

@DisallowConcurrentExecution
public class FlowExportDelete implements Job{

@Autowired
FlowExportRequestService flowExportRequestService;	

private static final Logger LOGGER = LoggerFactory.getLogger(FlowExportDelete.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< START DELETE Flow Export >>>>>>>>>>>>>>>>>>>>>>>");
			flowExportRequestService.delete();
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< END DELETE Flow Export >>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

	}

}
