package it.eng.care.domain.flow.tabgen.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.eng.care.domain.flow.tabgen.cache.SaveTabgenKeyGenerator;
import it.eng.care.domain.flow.tabgen.cache.TabgenConcurrentMapCache;
import it.eng.care.domain.flow.tabgen.controller.impl.ConfigFlussiControllerImpl;
import it.eng.care.domain.flow.tabgen.controller.impl.ProfilaturaFlussiControllerImpl;
import it.eng.care.domain.flow.tabgen.controller.impl.ProfilaturaValueFlussiControllerImpl;
import it.eng.care.domain.flow.tabgen.controller.impl.TabgenControllerImpl;
import it.eng.care.domain.flow.tabgen.controller.impl.TabgenValueControllerImpl;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.domain.flow.tabgen.service.impl.ImportTabgenService;
import it.eng.care.domain.flow.tabgen.service.impl.TabgenDelegateImpl;
import it.eng.care.domain.flow.tabgen.service.impl.TabgenServiceImpl;

@Configuration
public class TabgenServiceConfig {

    @Bean
    public TabgenService tabgenService() {
        return new TabgenServiceImpl();
    }

    @Bean
    public ImportTabgenService importTabgenService() {
        return new ImportTabgenService();
    }

    @Bean
    public TabgenDelegate delegate() {
        return new TabgenDelegateImpl();
    }
    
    @Bean
    public ProfilaturaValueFlussiControllerImpl ProfilaturaValueFlussiControllerImpl() {
    	return new ProfilaturaValueFlussiControllerImpl();
    }
    
    @Bean
    public ProfilaturaFlussiControllerImpl ProfilaturaFlussiControllerImpl() {
    	return new ProfilaturaFlussiControllerImpl();
    }
    
    @Bean
    public ConfigFlussiControllerImpl ConfigFlussiControllerImpl() {
    	return new ConfigFlussiControllerImpl();
    }


    @Bean
    public TabgenControllerImpl tabgenControllerImpl() {
        return new TabgenControllerImpl();
    }

    @Bean
    public TabgenValueControllerImpl tabgenValueControllerImpl() {
        return new TabgenValueControllerImpl();
    }

    @Bean("saveTabgenKeyGenerator")
    public KeyGenerator saveTabgenKeyGenerator() {
        return new SaveTabgenKeyGenerator();
    }

    /*@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new TabgenConcurrentMapCache("lookupCache", true)));
        return cacheManager;
    }*/


}
