package it.eng.care.domain.flow.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import it.eng.care.domain.flow.b2b.controller.impl.FlowReceiverControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.AnagraficaAssistitoControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.DashboardControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.DataSourceControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.DriverControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FieldTypeControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowConfigurationFilterControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowConfigurationFilterFieldControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowDrgControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowExportRequestControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowImportRequestControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowPatientControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowRegionUnionControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FlowViewControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FormFlowControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.FormFlowRegionControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.JobTalendControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.MonitorSdoControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.PermissionControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.PraticaViewControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.RegionReturnsControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.State2ControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.StateControllerImpl;
import it.eng.care.domain.flow.core.controller.impl.VersionControllerImpl;
import it.eng.care.domain.flow.crypt.CryptoConfig;
import it.eng.care.domain.flow.email.config.JavaMailSenderConfig;
import it.eng.care.domain.flow.flowupload.controller.impl.FlowFileUploadControllerImpl;
import it.eng.care.domain.flow.tabgen.config.TabgenConfig;
import it.eng.care.domain.flow.ws.results.WsResultConfig;

@Configuration
@Import({ FlowDozerConfig.class, FlowPersistConfig.class, FlowRepositoryConfig.class, FlowServiceConfig.class,
		ConfiguratorMachine.class, TabgenConfig.class, JavaMailSenderConfig.class, CryptoConfig.class,
		WsResultConfig.class, LogAccessiPMConfig.class
		})
public class FlowCoreConfig {

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
	public FlowReceiverControllerImpl flowReceiverControllerImpl() {
		return new FlowReceiverControllerImpl();
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
	public RegionReturnsControllerImpl crateRegionReturnsController() {
		return new RegionReturnsControllerImpl();
	}
	
	@Bean
	public FlowPatientControllerImpl flowPatientController() {
		return new FlowPatientControllerImpl();
	}
	
	@Bean
	public PermissionControllerImpl permissionController() {
		return new PermissionControllerImpl();
	}
	
	@Bean
	public FlowFileUploadControllerImpl FlowFileUploadController() {
		return new FlowFileUploadControllerImpl();
	}
	
	@Bean
	public FlowDrgControllerImpl FlowDrgController() {
		return new FlowDrgControllerImpl();
	}
	
	@Bean
    public AnagraficaAssistitoControllerImpl AnagraficaAssistitoController() {
        return new AnagraficaAssistitoControllerImpl();
    }
	
	@Bean
    public MonitorSdoControllerImpl monitorSdoXlController() {
        return new MonitorSdoControllerImpl();
    }

}
