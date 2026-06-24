package it.eng.care.domain.flow.jobs.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import it.eng.care.domain.flow.b2b.service.RegionMessageService;
import it.eng.care.domain.flow.b2b.utils.ErrorService;
import it.eng.care.domain.flow.core.entity.AppIdentityDO;
import it.eng.care.domain.flow.core.entity.RegionErrorsDO;
import it.eng.care.domain.flow.core.enumeration.ErrorServiceStatusEnum;
import it.eng.care.domain.flow.core.service.AppIdentityService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.jobs.service.JobSendRegionErrorService;

public class JobSendRegionErrorServiceImpl implements JobSendRegionErrorService {

    @Autowired
    protected RegionMessageService regionMessageService;

    @Autowired
    protected ErrorService errorService;
    
    @Autowired
    private AppIdentityService appIdentityService;

    @Value("${flow.job.error.retrynum:5}")
    private int numError;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JobSendRegionErrorServiceImpl.class);
    
    @Override
    public void getPendingErrorAndSend() throws Exception {

        List<RegionErrorsDO> errors = regionMessageService.findAllPendingErrors(numError);

        for (RegionErrorsDO error : errors) {
            try {
            	Integer currentRetry = error.getNretry() != null ? error.getNretry() : 0;

                // Se IDENTITY_REG_ERRORS non è configurato, non provo neppure l'invio
                if (!isIdentityRegErrorsConfigured(error)) {
                    if (currentRetry != numError) {
                        error.setSendStatus(ErrorServiceStatusEnum.ERROR.getStatus());
                        error.setNretry(0);
                        regionMessageService.saveOrUpdateEvent(error);
                    }
                    continue;
                }
                
                String status = errorService.sendRegionRestCall(
                        error.getJson(),
                        error.getFlowId().getName(),
                        error.getCodiceAzienda()
                );

                if (ErrorServiceStatusEnum.ERROR.getStatus().equals(status)) {
                    if (error.getNretry() < numError) {
                        error.setSendStatus(ErrorServiceStatusEnum.PENDING.getStatus());
                        error.setNretry(error.getNretry() + 1);
                    } else {
                        error.setSendStatus(ErrorServiceStatusEnum.ERROR.getStatus());
                    }
                    regionMessageService.saveOrUpdateEvent(error);

                } else if (ErrorServiceStatusEnum.SENT.getStatus().equals(status)) {
                    error.setDateSent(new Date());
                    error.setSendStatus(ErrorServiceStatusEnum.SENT.getStatus());
                    regionMessageService.saveOrUpdateEvent(error);
                }

            } catch (Exception e) {
                if (error.getNretry() < numError) {
                    error.setSendStatus(ErrorServiceStatusEnum.PENDING.getStatus());
                    error.setNretry(error.getNretry() + 1);
                } else {
                    error.setSendStatus(ErrorServiceStatusEnum.ERROR.getStatus());
                }

                regionMessageService.saveOrUpdateEvent(error);
            }
        }
    }
    
    private boolean isIdentityRegErrorsConfigured(RegionErrorsDO error) {
        if (error == null || error.getFlowId() == null || error.getFlowId().getName() == null) {
            return false;
        }

        String flowName = error.getFlowId().getName();
        String codiceAzienda = error.getCodiceAzienda();

        try {
            // 1) prima provo configurazione specifica flow + codice azienda
            if (codiceAzienda != null && !codiceAzienda.isBlank()) {
                AppIdentityDO appIdentity = appIdentityService.searchApp(flowName, codiceAzienda);
                if (appIdentity != null
                        && appIdentity.getIdentityRegErrors() != null
                        && !appIdentity.getIdentityRegErrors().isBlank()) {
                    return true;
                }
            }

            // 2) fallback generico flow + codice azienda null
            AppIdentityDO genericAppIdentity = appIdentityService.searchApp(flowName);
            return genericAppIdentity != null
                    && genericAppIdentity.getIdentityRegErrors() != null
                    && !genericAppIdentity.getIdentityRegErrors().isBlank();

        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Errore nel recupero di FM_APP_IDENTITY.IDENTITY_REG_ERRORS per flowName=" + flowName
                    + " codiceAzienda=" + codiceAzienda, e);
            return false;
        }
    }
    
}