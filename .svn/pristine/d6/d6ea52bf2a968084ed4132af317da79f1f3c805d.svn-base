package org.it.eng.care.domain.flow.FlowManager;

import com.querydsl.apt.jpa.JPAConfiguration;
import it.eng.care.domain.flow.core.config.*;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.authentication.impl.persist.util.DummyUserService;
import it.eng.care.platform.common.dozer.config.DozerCommonConfig;
import it.eng.care.platform.persistence.api.factory.CareRepositoryFactory;
import it.eng.care.platform.persistence.impl.config.PersistenceConfig;
import it.eng.care.platform.persistence.impl.jpa.carerepository.CareRepositoryFactoryImpl;
import it.eng.care.platform.tool.transport.conversion.ConversionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@Import({
        FlowDozerConfig.class,
        FlowRepositoryConfig.class,
        FlowServiceConfig.class,
        FlowPersistConfig.class,
        PersistenceConfig.class,
        DozerCommonConfig.class,
        ConversionConfig.class,
        ConfiguratorMachine.class,
        JPAConfiguration.class
})
public class FlowManagerTestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        Resource resource1 = new ClassPathResource("encrypted.properties");
        Resource resource2 = new ClassPathResource("datasourcePostgreSQL.properties");
        Resource resource3 = new ClassPathResource("application.properties");
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocations(resource1, resource2, resource3);

        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public CareRepositoryFactory careRepositoryFactory() {
        return new CareRepositoryFactoryImpl();
    }

    @Bean
    public LoggedUserService loggedUserService() {
        return new DummyUserService();
    }

//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }

}
