package org.it.eng.care.domain.flow.tabgen;

import it.eng.care.domain.flow.tabgen.config.TabgenDozerConfig;
import it.eng.care.domain.flow.tabgen.config.TabgenPersistConfig;
import it.eng.care.domain.flow.tabgen.config.TabgenRepositoryConfig;
import it.eng.care.domain.flow.tabgen.config.TabgenServiceConfig;
import it.eng.care.platform.common.dozer.config.DozerCommonConfig;
import it.eng.care.platform.persistence.api.factory.CareRepositoryFactory;
import it.eng.care.platform.persistence.impl.config.PersistenceConfig;
import it.eng.care.platform.persistence.impl.jpa.carerepository.CareRepositoryFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@Import({
	TabgenDozerConfig.class,
	TabgenRepositoryConfig.class,
	TabgenServiceConfig.class,
	TabgenPersistConfig.class,
	PersistenceConfig.class,
	DozerCommonConfig.class
})
public class TabgenTestConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		Resource resource1 = new ClassPathResource("encrypted.properties");
		Resource resource2 = new ClassPathResource("datasourcePostgreSQL.properties");
		Resource resource3 = new ClassPathResource("application.properties");
		Resource resource4 = new ClassPathResource("tabgen.properties");

		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setLocations(resource1, resource2, resource3, resource4);

		return propertySourcesPlaceholderConfigurer;
	}
	
	@Bean
	public CareRepositoryFactory careRepositoryFactory() {
		return new CareRepositoryFactoryImpl();
	}
	

}
