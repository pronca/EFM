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
import it.eng.care.domain.flow.jobs.service.JobSendRegionErrorService;

@DisallowConcurrentExecution
public class SendRegionRestErrors implements Job {

    @Autowired
    JobSendRegionErrorService sendRegionError;

    private static final Logger LOGGER = LoggerFactory.getLogger(SendRegionRestErrors.class);

    @Override
    @LockJob(entity = "SendRegionRestErrors", untilCfg = "SendRegionRestErrors.locktimeout")
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< START SENDING REGION ERROR >>>>>>>>>>>>>>>>>>>>>>>");
            sendRegionError.getPendingErrorAndSend();
            LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<< END SENDING REGION ERROR >>>>>>>>>>>>>>>>>>>>>>>");
        } catch (Exception e) {
            LogUtil.logException(LOGGER, "", e);
        }
    }
}