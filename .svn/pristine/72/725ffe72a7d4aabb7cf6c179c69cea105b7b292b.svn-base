package it.eng.care.domain.flow.b2b.service.impl;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.b2b.service.SendDrgService;
import it.eng.care.domain.flow.b2b.utils.EurekaClientService;
import it.eng.care.domain.flow.b2b.utils.JWTAuth;
import it.eng.care.domain.flow.core.dao.IntegrationServiceDAO;
import it.eng.care.domain.flow.core.dao.SendDrgDAO;
import it.eng.care.domain.flow.core.entity.IntegrationServiceDO;
import it.eng.care.domain.flow.core.entity.SendDrgDO;
import it.eng.care.domain.flow.core.enumeration.ErrorServiceStatusEnum;
import it.eng.care.domain.flow.core.utility.LogUtil;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SendDrgServiceImpl implements SendDrgService {
	
	@Autowired
	private SendDrgDAO sendDrgDao;
	
	@Autowired
	private EurekaClientService eurekaClientService;
	
	@Autowired
	protected JWTAuth auth;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SendDrgServiceImpl.class);
    
    private String token;
    
	@Value("${flow.job.error.retrynum:5}")
	private int numError; 
	
	@Autowired
	private IntegrationServiceDAO integrationServiceDAO;

	@Override
	public List<SendDrgDO> findAllPendingDrgs(int numErr) {
		List<SendDrgDO> errors = sendDrgDao.findTopByStatusAndNretryLessThan(ErrorServiceStatusEnum.PENDING.getStatus(),numErr);
		return errors;
	}
	
	@Override
	public void saveOrUpdateEvent(SendDrgDO error) {
		sendDrgDao.save(error);
	}
	
	@Override
	public void getPendingDrgAndSend() throws Exception {
		
		List<SendDrgDO> drgToSend = findAllPendingDrgs(numError);
		
		for(SendDrgDO drg : drgToSend) {
			String status = restCall(drg.getJson(), drg.getFlowId().getName());
			//in base al risultato prodotto dal servizio restCall bisogna o meno aggiornare il record in modo da effettuare un retry consistente e aumentare il numero di try per ogni fallimento 
		
			if(status.equals(ErrorServiceStatusEnum.ERROR.getStatus()) && drg.getNretry() < numError) {
				drg.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());
				drg.setNretry(drg.getNretry()+1);
				saveOrUpdateEvent(drg);
				
			}
			else if(status.equals(ErrorServiceStatusEnum.SENT.getStatus())) {
				drg.setDateSent(new Date());
				drg.setStatus(ErrorServiceStatusEnum.SENT.getStatus());
				saveOrUpdateEvent(drg);
			}
		
		}
		
	}
	
	@Override
	public String restCall(String jsonString, String flowName) throws Exception {
		
		IntegrationServiceDO integration = integrationServiceDAO.findByNomeAndAbilitazione("INVIO_DRG", 1);
		
		if (integration!=null) {
			String appName = integration.getVerticale();
			URI eurekaName = eurekaClientService.retriveBalanceBaseURI(appName);
			String url = (eurekaName!=null ? eurekaName.toString() : "")+integration.getUrl();

			if (token == null) {
				token = auth.loginRestCall();
			}

			HttpEntity reqEntity = null;

			try {
				reqEntity = new StringEntity(jsonString);
			} catch (Exception e) {
				LogUtil.logException(LOGGER, "", e);
				return ErrorServiceStatusEnum.ERROR.getStatus();
			}

			LOGGER.info("Tentativo di invio drg...");

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			RequestConfig.Builder requestConfig = RequestConfig.custom();
			requestConfig.setConnectTimeout(10 * 1000);
			requestConfig.setConnectionRequestTimeout(10 * 1000);
			requestConfig.setSocketTimeout(10 * 1000);

			// add header
			post.setHeader("Authorization", "Bearer" + " " + token);
			post.setHeader("Accpet", "application/json");
			post.setHeader("content-type", "application/json");
			post.setEntity(reqEntity);
			post.setConfig(requestConfig.build());

			try {
				HttpResponse response = client.execute(post);
				HttpEntity respEntity = response.getEntity();
				String responseString = EntityUtils.toString(respEntity);
				if (response.getStatusLine().getStatusCode() == 200) {
					LOGGER.info("Drg inviati con successo: " + responseString);
					return ErrorServiceStatusEnum.SENT.getStatus();

					
				} else if (response.getStatusLine().getStatusCode() == 401) {
					LOGGER.info("refresh token" + response.toString());
					token = auth.loginRestCall();
					// richiamo la funzione per invio errori
					restCall(jsonString, flowName);

				} else {
					LOGGER.info("1 - Errore nell'invio dei dati: " + response.toString());
					return ErrorServiceStatusEnum.ERROR.getStatus();

				}

			} catch (NoHttpResponseException nhre) {
				LogUtil.logException(LOGGER, "2 - Errore nell'invio dei dati", nhre);

				return ErrorServiceStatusEnum.ERROR.getStatus();
			} catch (Exception e) {
				LogUtil.logException(LOGGER, "3 - Errore nell'invio dei dati", e);
				return ErrorServiceStatusEnum.ERROR.getStatus();

			}
		} else {
			LOGGER.info("Parametrizzazione FM_INTEGRATION_SERVICE mancante !");
		}
		
		return ErrorServiceStatusEnum.ERROR.getStatus();

	}


}
