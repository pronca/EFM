package it.eng.care.domain.flow.b2b.utils;

import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class EurekaClientService {
	  
	
	@Autowired
	    private LoadBalancerClient loadBalancer;
	
		private HashMap<String,URI> application;
	
		public EurekaClientService(){
			application = new HashMap<String,URI>();
		}
		
		
		/**
	     * Retrive URI from LoadBalancer Service
	     * @param applicationName
	     * @return
	     */
	
		public URI retriveBalanceBaseURI(String applicationName) {
	    	
	    	if(application.containsKey(applicationName)) {	
			return application.get(applicationName);
	    	}
	    	else {
	        ServiceInstance serviceInstance = loadBalancer.choose(applicationName);
	        if (serviceInstance == null){
	        	return null;
	        }else {
	        application.put(applicationName, serviceInstance.getUri());

	        return application.get(applicationName);
	        }
	    
	    }
		}
}
