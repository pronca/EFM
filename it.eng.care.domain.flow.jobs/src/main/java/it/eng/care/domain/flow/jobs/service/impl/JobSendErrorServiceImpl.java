package it.eng.care.domain.flow.jobs.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import it.eng.care.domain.flow.b2b.service.ValidationMessageService;
import it.eng.care.domain.flow.b2b.utils.ErrorService;
import it.eng.care.domain.flow.core.entity.AppIdentityDO;
import it.eng.care.domain.flow.core.entity.ValidationErrorsDO;
import it.eng.care.domain.flow.core.enumeration.ErrorServiceStatusEnum;
import it.eng.care.domain.flow.core.service.AppIdentityService;
import it.eng.care.domain.flow.core.service.impl.TwoLevelResultsServiceImpl;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.jobs.service.JobSendErrorService;


public class JobSendErrorServiceImpl implements JobSendErrorService{

	
	@Autowired
	protected ValidationMessageService validationMessageService;
	
	@Autowired
	protected ErrorService errorService;
	
	@Autowired
	private AppIdentityService appIdentityService;
	
	@Value("${flow.job.error.retrynum:5}")
	private int numError; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobSendErrorServiceImpl.class);
	
	@Override
	public void getPendingErrorAndSend() throws Exception {
		
		List<ValidationErrorsDO> errors = validationMessageService.findAllPendingErrors(numError);
		
		for(ValidationErrorsDO error : errors) {
			try {
				Integer currentRetry = error.getNretry() != null ? error.getNretry() : 0;

	            if (!isIdentityConfigured(error)) {
	                if (currentRetry != numError) {
	                    error.setStatus(ErrorServiceStatusEnum.ERROR.getStatus());
	                    error.setNretry(0);
	                    validationMessageService.saveOrUpdateEvent(error);
	                }
	                continue;
	            }
	            
				String status = errorService.restCall(error.getJson(), error.getFlowId().getName(), error.getCodiceAzienda());
				//in base al risultato prodotto dal servizio restCall bisogna o meno aggiornare il record in modo da effettuare un retry consistente e aumentare il numero di try per ogni fallimento 
			
				if(status.equals(ErrorServiceStatusEnum.ERROR.getStatus()) && error.getNretry() < numError) {
					error.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());
					error.setNretry(error.getNretry()+1);
					validationMessageService.saveOrUpdateEvent(error);
					
				} else if(status.equals(ErrorServiceStatusEnum.SENT.getStatus())) {
					error.setDateSent(new Date());
					error.setStatus(ErrorServiceStatusEnum.SENT.getStatus());
					validationMessageService.saveOrUpdateEvent(error);
				}
			} catch (Exception e) {
                if (error.getNretry() < numError) {
                    error.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());
                    error.setNretry(error.getNretry() + 1);
                } else {
                    error.setStatus(ErrorServiceStatusEnum.ERROR.getStatus());
                }

                validationMessageService.saveOrUpdateEvent(error);
            }
		}
		
	}
	
	private boolean isIdentityConfigured(ValidationErrorsDO error) {
	    if (error == null || error.getFlowId() == null || error.getFlowId().getName() == null) {
	        return false;
	    }

	    String flowName = error.getFlowId().getName();
	    String codiceAzienda = error.getCodiceAzienda();

	    try {
	        // 1) provo prima la configurazione specifica flow + codice azienda
	        if (codiceAzienda != null && !codiceAzienda.isBlank()) {
	            AppIdentityDO appIdentity = appIdentityService.searchApp(flowName, codiceAzienda);
	            if (appIdentity != null
	                    && appIdentity.getIdentity() != null
	                    && !appIdentity.getIdentity().isBlank()) {
	                return true;
	            }
	        }

	        // 2) fallback generico flow + codiceAzienda null
	        AppIdentityDO genericAppIdentity = appIdentityService.searchApp(flowName);
	        return genericAppIdentity != null
	                && genericAppIdentity.getIdentity() != null
	                && !genericAppIdentity.getIdentity().isBlank();

	    } catch (Exception e) {
	        LogUtil.logException(LOGGER, "Errore nel recupero di FM_APP_IDENTITY.IDENTITY per flowName=" + flowName
	                        + " codiceAzienda=" + codiceAzienda,e);
	        return false;
	    }
	}

}
