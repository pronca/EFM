package it.eng.care.domain.flow.jobs.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.email.service.MailSender;
import it.eng.care.domain.flow.jobs.look.LockJob;

@DisallowConcurrentExecution
public class SendEmailsForErrors implements Job{

@Autowired
 MailSender mailSender;

private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailsForErrors.class);

	@Override
	@LockJob(entity = "SendEmailsForErrors", untilCfg = "SendEmailsForErrors.locktimeout")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
		try {
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  SENDING ERROR EMAILS >>>>>>>>>>>>>>>>>>>>>>>");

			try {
				mailSender.sendEmailErrors();
			} catch (jakarta.mail.MessagingException e) {
				LogUtil.logException(LOGGER, "", e);
//				e.printStackTrace();
			}
			
			LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<  ERROR EMAILS SENDEND >>>>>>>>>>>>>>>>>>>>>>>");

		} catch (Exception e) {
			LogUtil.logException(LOGGER, "<<<<<<<<<<<<<<<<<<<<<<< EXCEPTION ON ERROR EMAILS >>>>>>>>>>>>>>>>>>>>>>>", e);
//			e.printStackTrace();
		}
	}

}
