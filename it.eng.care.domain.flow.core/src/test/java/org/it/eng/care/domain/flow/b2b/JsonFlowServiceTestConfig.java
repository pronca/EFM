package org.it.eng.care.domain.flow.b2b;

import it.eng.care.domain.flow.b2b.controller.FlowReceiverController;
import it.eng.care.domain.flow.b2b.controller.impl.FlowReceiverControllerImpl;
import it.eng.care.domain.flow.b2b.service.BatchFlowService;
import it.eng.care.domain.flow.b2b.service.JsonFlowService;
import it.eng.care.domain.flow.b2b.service.ValidationFlowService;
import it.eng.care.domain.flow.b2b.service.ZipService;
import it.eng.care.domain.flow.b2b.service.impl.BatchFlowServiceImpl;
import it.eng.care.domain.flow.b2b.service.impl.JsonFlowServiceImpl;
import it.eng.care.domain.flow.b2b.service.impl.ZipServiceImpl;
import it.eng.care.domain.flow.b2b.utils.FlowOperationResult;
import it.eng.care.domain.flow.core.config.FlowDozerConfig;
import it.eng.care.domain.flow.core.config.FlowPersistConfig;
import it.eng.care.domain.flow.core.config.FlowRepositoryConfig;
import it.eng.care.domain.flow.core.config.FlowServiceConfig;
import it.eng.care.domain.flow.core.controller.impl.*;
import it.eng.care.domain.flow.crypt.CryptoConfig;
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
@Import({ FlowDozerConfig.class, FlowRepositoryConfig.class, FlowServiceConfig.class, FlowPersistConfig.class,
		PersistenceConfig.class, DozerCommonConfig.class, ConversionConfig.class, CryptoConfig.class })
public class JsonFlowServiceTestConfig {

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
	public LoggedUserService loggedUserService() {
		return new DummyUserService();
	}

	@Bean
	public FlowControllerImpl crateFlowController() {
		return new FlowControllerImpl();
	}

	@Bean
	public DriverControllerImpl crateDriverController() {
		return new DriverControllerImpl();
	}

	@Bean
	public VersionControllerImpl crateVersionController() {
		return new VersionControllerImpl();
	}

	@Bean
	public DataSourceControllerImpl crateDataSourceController() {
		return new DataSourceControllerImpl();
	}

	@Bean
	public FormFlowControllerImpl crateFlowFormController() {
		return new FormFlowControllerImpl();
	}

	@Bean
	public FieldTypeControllerImpl CreateFieldTypeController() {
		return new FieldTypeControllerImpl();
	}

	@Bean
	public FlowViewControllerImpl createFlowViewController() {
		return new FlowViewControllerImpl();
	}

	@Bean
	public FlowImportRequestControllerImpl createFlowImportController() {
		return new FlowImportRequestControllerImpl();
	}

	@Bean
	public FlowExportRequestControllerImpl createFlowExportController() {
		return new FlowExportRequestControllerImpl();
	}

	@Bean
	public JobTalendControllerImpl createJobTalendController() {
		return new JobTalendControllerImpl();
	}

	@Bean
	public StateControllerImpl createStateController() {
		return new StateControllerImpl();
	}

	@Bean
	public State2ControllerImpl createState2Controller() {
		return new State2ControllerImpl();
	}

	@Bean
	public FlowConfigurationFilterControllerImpl flowConfiguratorFilterController() {
		return new FlowConfigurationFilterControllerImpl();
	}

	@Bean
	public FlowConfigurationFilterFieldControllerImpl flowConfiguratorFilterFieldController() {
		return new FlowConfigurationFilterFieldControllerImpl();
	}

	@Bean
	public PraticaViewControllerImpl createPraticaViewController() {
		return new PraticaViewControllerImpl();
	}

	@Bean
	public DashboardControllerImpl dashboardControllerImpl() {
		return new DashboardControllerImpl();
	}

	@Bean
	public FlowRegionUnionControllerImpl flowRegionUnionControllerImpl() {
		return new FlowRegionUnionControllerImpl();
	}

	@Bean
	public FormFlowRegionControllerImpl crateRegionFormFlowController() {
		return new FormFlowRegionControllerImpl();
	}

	@Bean
	public FlowOperationResult flowOperationResult() {
		return new FlowOperationResult();
	}

	@Bean
	public CareRepositoryFactory careRepositoryFactory() {
		return new CareRepositoryFactoryImpl();
	}

	@Bean
	FlowReceiverController getFlowReceiverController() {
		return new FlowReceiverControllerImpl();
	}

	@Bean
	JsonFlowService getJsonFlowService() {
		return new JsonFlowServiceImpl();
	}

	@Bean
	ValidationFlowService getValidationFlowService() {
		// Uso il MOCK
		return new ValidationFlowServiceMock();
	}

	@Bean
	BatchFlowService getBatchFlowService() {
		// Uso il MOCK
		// return new BatchFlowServiceMock();
		return new BatchFlowServiceImpl();
	}

	@Bean
	ZipService getZipService() {
		return new ZipServiceImpl();
	}
}
