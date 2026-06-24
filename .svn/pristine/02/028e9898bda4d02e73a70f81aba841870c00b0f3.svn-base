package it.eng.care.domain.flow.core.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import it.eng.care.domain.flow.b2b.utils.EurekaClientService;
import it.eng.care.domain.flow.b2b.utils.JWTAuth;
import it.eng.care.domain.flow.core.service.BrickLayerInvokeService;
import it.eng.care.domain.flow.core.utility.LogUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrickLayerInvokeServiceImpl implements BrickLayerInvokeService {
	
	private static Logger logger = LoggerFactory.getLogger(BrickLayerInvokeServiceImpl.class);
	
	private URI url;
	
	private String token;
	
	@Autowired
	protected JWTAuth auth;

	@Autowired
	protected EurekaClientService eureka;
	
	@Override
	public Boolean invokeExternalService(String numExtr) throws Exception {
	
		HttpEntity reqEntity = null;

		try {
			reqEntity = new StringEntity(numExtr);
		} catch (UnsupportedEncodingException e) {
			LogUtil.logException(logger, "", e);
//			e.printStackTrace();
		}

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url + "/api/bl/talend/start");

		RequestConfig.Builder requestConfig = RequestConfig.custom();
		requestConfig.setConnectTimeout(10 * 1000);
		requestConfig.setConnectionRequestTimeout(10 * 1000);
		requestConfig.setSocketTimeout(10 * 1000);
		post.setHeader("Authorization", "Bearer" + " " + token);
		post.setEntity(reqEntity);
		post.setConfig(requestConfig.build());

		try {
			HttpResponse response = client.execute(post);
			HttpEntity respEntity = response.getEntity();
			String responseB = EntityUtils.toString(respEntity);
			if (response.getStatusLine().getStatusCode() == 200) {
				logger.info("Servizio BrickLayer terminato con successo" + responseB);
			} else {
				logger.info("Servizio BrickLayer fallito" + response.toString());

			}

			return Boolean.parseBoolean(responseB);

		} catch (NoHttpResponseException nhre) {
			LogUtil.logException(logger, "", nhre);
			
			throw new Exception("ERROR : RETRIEVING RESPONSE: " + nhre.getMessage());
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			
			throw new Exception("ERROR : RETRIEVING RESPONSE: " + e.getMessage());
		}

	}

	// metodo per esistenza microservizio
	@Override
	public Boolean existMicroService() {
		url = eureka.retriveBalanceBaseURI("HC40-FM-BRICKLAYER");
		
		if(url == null) {
			return false;
		}
	
		HttpGet request = new HttpGet(url + "/api/bl/talend/exist");

		try {
			token = auth.loginRestCall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.logException(logger, "", e);
//			e.printStackTrace();
			return false;
		}

		request.setHeader("Authorization", "Bearer" + " " + token);

		HttpClient client = new DefaultHttpClient();

		HttpResponse response;
		try {
			response = client.execute(request);
			HttpEntity respEntity = response.getEntity();
			String responseB = EntityUtils.toString(respEntity);

			if (response.getStatusLine().getStatusCode() == 200) {
				logger.info("Il microservizio BrickLayer è presente");
			} else {
				logger.info("Il microservizio BrickLayer non è presente o è malfunzionante");

				return false;

			}

			return Boolean.parseBoolean(responseB);
		} catch (NoHttpResponseException nhre) {
			LogUtil.logException(logger, "Il microservizio BrickLayer non  è presente", nhre);

			return false;
		} catch (Exception e) {
			LogUtil.logException(logger, "Invocazione mciroservizio BrickLayer fallita", e);

			return false;
		}

	}
	
	@Override
	public Boolean invokeExternalServiceKillJob(String numExtr) throws Exception {
		String token = null;

		try {
			// MP: autenticazione remota

			token = auth.loginRestCall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.logException(logger, "", e);
//			e.printStackTrace();
		}

		HttpEntity reqEntity = null;

		try {
			reqEntity = new StringEntity(numExtr);
		} catch (UnsupportedEncodingException e) {
			LogUtil.logException(logger, "", e);
//			e.printStackTrace();
		}

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url + "/api/bl/talend/kill");

		RequestConfig.Builder requestConfig = RequestConfig.custom();
		requestConfig.setConnectTimeout(10 * 1000);
		requestConfig.setConnectionRequestTimeout(10 * 1000);
		requestConfig.setSocketTimeout(10 * 1000);
		post.setHeader("Authorization", "Bearer" + " " + token);
		post.setEntity(reqEntity);
		post.setConfig(requestConfig.build());

		try {
			HttpResponse response = client.execute(post);
			HttpEntity respEntity = response.getEntity();
			String responseB = EntityUtils.toString(respEntity);
			if (response.getStatusLine().getStatusCode() == 200) {
				logger.info("Servizio BrickLayer kill job terminato con successo" + responseB);
			} else {
				logger.info("Servizio BrickLayer kill job fallito" + response.toString());

			}

			return Boolean.parseBoolean(responseB);

		} catch (NoHttpResponseException nhre) {
			LogUtil.logException(logger, "", nhre);
			
			throw new Exception("ERROR : RETRIEVING RESPONSE: " + nhre.getMessage());
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			
			throw new Exception("ERROR : RETRIEVING RESPONSE: " + e.getMessage());
		}

	}

}
