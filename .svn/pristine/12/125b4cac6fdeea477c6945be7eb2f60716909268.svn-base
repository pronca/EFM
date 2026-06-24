package it.eng.care.domain.flow.drools.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import it.eng.care.domain.flow.core.config.FlowDozerConfig;
import it.eng.care.domain.flow.core.config.FlowRepositoryConfig;
import it.eng.care.domain.flow.core.config.FlowServiceConfig;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.drools.dao.FlowRuleDAO;
import it.eng.care.domain.flow.drools.dao.FlowRuleFileDAO;
import it.eng.care.domain.flow.drools.dao.UtilityDAO;
import it.eng.care.domain.flow.drools.dao.ValidationRequestResultDAO;
import it.eng.care.domain.flow.drools.entity.FlowRuleDO;
import it.eng.care.domain.flow.drools.entity.FlowRuleFileDO;
import it.eng.care.domain.flow.drools.entity.ValidationRequestResultDO;
import it.eng.care.domain.flow.drools.model.lookup.KeyGeneratorLookupCache;
import it.eng.care.domain.flow.drools.platform.JpaRuleRepositoryExtension;
import it.eng.care.domain.flow.drools.platform.RuleRepositoryExtension;
import it.eng.care.domain.flow.drools.service.api.DroolsService;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;
import it.eng.care.domain.flow.drools.service.api.RuleManagerService;
import it.eng.care.domain.flow.drools.service.api.UtilityService;
import it.eng.care.domain.flow.drools.service.impl.DroolsServiceImpl;
import it.eng.care.domain.flow.drools.service.impl.FlowValidatorImpl;
import it.eng.care.domain.flow.drools.service.impl.RuleManagerServiceImpl;
import it.eng.care.domain.flow.drools.service.impl.UtilityServiceImpl;
import it.eng.care.domain.flow.drools.utility.api.DroolsDateUtility;
import it.eng.care.domain.flow.drools.utility.api.DroolsQueryUtility;
import it.eng.care.domain.flow.drools.utility.api.FlowRowUtility;
import it.eng.care.domain.flow.drools.utility.api.LookupUtility;
import it.eng.care.domain.flow.drools.utility.api.RowConverter;
import it.eng.care.domain.flow.drools.utility.impl.DroolsDateUtilityImpl;
import it.eng.care.domain.flow.drools.utility.impl.DroolsQueryUtilityImpl;
import it.eng.care.domain.flow.drools.utility.impl.DroolsStringUtilityImpl;
import it.eng.care.domain.flow.drools.utility.impl.FlowRowUtilityImpl;
import it.eng.care.domain.flow.drools.utility.impl.LookupUtilityImpl;
import it.eng.care.domain.flow.drools.utility.impl.RowConverterImpl;
import it.eng.care.domain.flow.tabgen.config.TabgenDozerConfig;
import it.eng.care.domain.flow.tabgen.config.TabgenRepositoryConfig;
import it.eng.care.domain.flow.tabgen.config.TabgenServiceConfig;
import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.webservice.dao.CodiciRegioneDAO;
import it.eng.care.domain.flow.webservice.model.CodiciRegioneDO;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.authentication.impl.persist.util.DummyUserService;
import it.eng.care.platform.common.dozer.config.DozerCommonConfig;
import it.eng.care.platform.persistence.api.factory.CareRepositoryFactory;
import it.eng.care.platform.persistence.impl.config.PersistenceConfig;
import it.eng.care.platform.persistence.impl.config.PersistenceConfigurer;
import it.eng.care.platform.persistence.impl.jpa.carerepository.CareRepositoryFactoryImpl;
import it.eng.care.platform.rules.impl.config.RulesRepositoryConfig;
import it.eng.care.platform.rules.impl.config.RulesServiceConfig;
import it.eng.care.platform.rules.impl.config.RulesTransportConfig;
import it.eng.care.platform.rules.impl.config.RulesXmlConfig;
import it.eng.care.platform.rules.impl.persist.model.RuleBeanSchemaDO;
import it.eng.care.platform.rules.impl.persist.model.RuleDO;
import it.eng.care.platform.rules.impl.persist.model.RuleInfoDO;
import it.eng.care.platform.rules.impl.persist.model.RuleStatsDO;
import it.eng.care.platform.tool.transport.conversion.ConversionConfig;

@Configuration
@EnableCaching
@Import(value = {
		PersistenceConfig.class,
		RulesRepositoryConfig.class, 
		RulesServiceConfig.class,
		RulesTransportConfig.class,
		RulesXmlConfig.class,
		FlowRepositoryConfig.class,
		FlowDozerConfig.class,
		TabgenDozerConfig.class,
		TabgenRepositoryConfig.class,
		TabgenServiceConfig.class,
		FlowServiceConfig.class,
		DozerCommonConfig.class,
		ConversionConfig.class})
public class DroolsConfigTest {
	
	@Bean
    public PersistenceConfigurer pPDPersistenceConfigurer() {
        return new PersistenceConfigurer()
				.addPackage(RuleDO.class)
				.addPackage(RuleInfoDO.class)
                .addPackage(RuleBeanSchemaDO.class)
                .addPackage(RuleStatsDO.class)
                .addPackage(TabgenDO.class)
                .addPackage(TabgenFieldDO.class)
                .addPackage(TabgenValueDO.class)
                .addPackage(FlowRuleDO.class)
                .addPackage(FlowRuleFileDO.class)
                .addPackage(FlowDO.class)
                .addPackage(VersionDO.class)
                .addPackage(DataSourceDO.class)
                .addPackage(ValidationRequestResultDO.class)
                .addPackage(CodiciRegioneDO.class);
    }
	
	@Bean
	public CareRepositoryFactory careRepositoryFactory() {
		return new CareRepositoryFactoryImpl();
	}
	
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
    public ValidationRequestResultDAO validationRequestResultDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(ValidationRequestResultDAO.class);
    }
	
	@Bean
    public FlowRuleDAO FlowRuleDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(FlowRuleDAO.class);
    }
	
	@Bean
    public FlowRuleFileDAO FlowRuleFileDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(FlowRuleFileDAO.class);
    }
	
	@Bean
    public CodiciRegioneDAO codiciRegioneDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(CodiciRegioneDAO.class);

    }

	@Bean
	public DroolsService createDroolsService() {
		return new DroolsServiceImpl();
	}

	/*@Bean
	public DroolsUtility droolsUtility() {
		return new DroolsUtilityImpl();
	}*/
	
	@Bean
	public DroolsQueryUtility DroolsQueryUtility() {
		return new DroolsQueryUtilityImpl();
	}
	
	@Bean
	public DroolsStringUtilityImpl droolsStringUtils() {
		return new DroolsStringUtilityImpl();
	}
	
	@Bean
	public DroolsDateUtility droolsDateUtility() {
		return new DroolsDateUtilityImpl();
	}

	@Bean
	public FlowRowUtility flowRowUtility() {
		return new FlowRowUtilityImpl();
	}

	@Bean
	public LookupUtility lookupUtility() {
		return new LookupUtilityImpl();
	}

	@Bean
	public RowConverter rowConverter() {
		return new RowConverterImpl();
	}

	@Bean
	public UtilityService utilityService() {
		return new UtilityServiceImpl();
	}

	@Bean
	public UtilityDAO utilityDAO() {
		return new UtilityDAO();
	}
	
	@Bean
	public RuleRepositoryExtension ruleRepositoryExtension() {
		return new JpaRuleRepositoryExtension(RuleDO.class);
	}
	
	@Bean("lookupCacheKeyGenerator")
	public KeyGenerator lookupCacheKeyGenerator() {
		return new KeyGeneratorLookupCache();
	}
	
	@Bean
	public FlowValidator flowValidator() {
		return new FlowValidatorImpl();
	}
	
	@Bean
	public RuleManagerService ruleManagerService() {
		return new RuleManagerServiceImpl();
	}
	
    @Bean
	public LoggedUserService loggedUserService() {
		return new DummyUserService();
	}
    
    
}
