package it.eng.care.domain.flow.core.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.eng.care.domain.flow.core.b2b.converter.UserToUserDTO;
import it.eng.care.domain.flow.core.converter.LinkedHashMapToFormFlowDTO;
import it.eng.care.domain.flow.core.converter.AnagraficaAssistito.AnagraficaAssistitoDOtoAnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.converter.AnagraficaAssistito.AnagraficaAssistitoDTOtoAnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.converter.Dashboard.DashboardConfigDOtoDashboardConfigDTO;
import it.eng.care.domain.flow.core.converter.Dashboard.DashboardDOtoDashboardDTO;
import it.eng.care.domain.flow.core.converter.Dashboard.DashboardDTOtoDashboardDO;
import it.eng.care.domain.flow.core.converter.DataSource.DataSourceDOtoDataSourceDTO;
import it.eng.care.domain.flow.core.converter.DataSource.DataSourceDTOtoDataSourceDO;
import it.eng.care.domain.flow.core.converter.Driver.DriverDOtoDriverDTO;
import it.eng.care.domain.flow.core.converter.Driver.DriverDTOtoDriverDO;
import it.eng.care.domain.flow.core.converter.ExtractionFilter.FlowImportRequestDTOtoExtractionDTO;
import it.eng.care.domain.flow.core.converter.ExtractionFilter.FlowImportRequestFieldDateDTOtoField;
import it.eng.care.domain.flow.core.converter.FieldType.FieldTypeDOtoFieldTypeDTO;
import it.eng.care.domain.flow.core.converter.FieldType.FieldTypeDTOtoFieldTypeDO;
import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFlowDTO;
import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFormFlowDTO;
import it.eng.care.domain.flow.core.converter.Flow.FlowDTOtoFlowDO;
import it.eng.care.domain.flow.core.converter.Flow.FlowDTOtoFormFlowDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterDOtoFlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterDTOtoFlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO;
import it.eng.care.domain.flow.core.converter.FlowDrg.FlowDrgDOtoFlowDrgDTO;
import it.eng.care.domain.flow.core.converter.FlowDrg.FlowDrgDTOtoFlowDrgDO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FlowForeignKeyDOtoFlowForeignKeyDTO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FlowForeignKeyDOtoFormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FlowForeignKeyDTOtoFlowForeignKeyDO;
import it.eng.care.domain.flow.core.converter.FlowForeignKey.FormFlowTableLinkDTOtoFlowForeignKeyDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowExportRequestDOtoFlowExportRequestDO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowExportRequestDOtoFlowExportRequestDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowExportRequestDTOtoFlowExportRequestDO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestDOtoFlowImportRequestDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestDTOtoFlowImportRequestDO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDO;
import it.eng.care.domain.flow.core.converter.FlowRegionUnion.FlowRegionUnionDOtoFlowRegionUnionDTO;
import it.eng.care.domain.flow.core.converter.FlowRegionUnion.FlowRegionUnionDTOtoFlowRegionUnionDO;
import it.eng.care.domain.flow.core.converter.FlowTable.FlowTableDOtoFlowTableDTO;
import it.eng.care.domain.flow.core.converter.FlowTable.FlowTableDOtoFormFlowTableDTO;
import it.eng.care.domain.flow.core.converter.FlowTable.FlowTableDTOtoFlowTableDO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDOtoFlowTableFieldDTO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDOtoFormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDTOtoFlowTableFieldDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowDTOtoFlowDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableDTOtoFlowTableDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableFieldDTOtoFlowTableFieldDO;
import it.eng.care.domain.flow.core.converter.FormFlow.FormFlowTableLinkDTOtoFlowForeignKeyDO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDOtoJobTalendDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDTOtoJobTalendDO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDipendencyDOtoJobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDipendencyDTOtoJobTalendDipendencyDO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendFileDOtoJobTalendFileDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendFileDTOtoJobTalendFileDO;
import it.eng.care.domain.flow.core.converter.MonitorSdoXl.MonitorSdoXlDOtoMonitorSdoXlDTO;
import it.eng.care.domain.flow.core.converter.MonitorSdoXl.MonitorSdoXlDTOtoMonitorSdoXlDO;
import it.eng.care.domain.flow.core.converter.ProfiloFlussi.ProfiloFlussiDOtoProfiloFlussiDTO;
import it.eng.care.domain.flow.core.converter.ProfiloFlussi.ProfiloFlussiDTOtoProfiloFlussiDO;
import it.eng.care.domain.flow.core.converter.StateMachine.StateDOtoStateDTO;
import it.eng.care.domain.flow.core.converter.StateMachine.StateDTOtoStateDO;
import it.eng.care.domain.flow.core.converter.UploadReturnsRequest.UploadReturnsRequestDOtoUploadReturnsRequestDTO;
import it.eng.care.domain.flow.core.converter.UploadReturnsRequest.UploadReturnsRequestDTOtoUploadReturnsRequestDO;
import it.eng.care.domain.flow.core.converter.Version.VersionDOtoVersionDTO;
import it.eng.care.domain.flow.core.converter.Version.VersionDTOtoVersionDO;
import it.eng.care.domain.flow.core.dao.querySearch.FlowConfigurationFilterFieldDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.dto.AnagrafcaAssistitoPaginationDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDownloadDTO;
import it.eng.care.domain.flow.core.dto.UploadReturnsRequestDTO;
import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;
import it.eng.care.domain.flow.flowupload.converter.FlowFileUploadRequestDOtoFlowFileUploadRequestDTO;
import it.eng.care.domain.flow.flowupload.converter.FlowFileUploadRequestDTOtoFlowFileUploadRequestDO;
import it.eng.care.platform.common.dozer.converter.DozerMappingFileBean;

@Configuration
public class FlowDozerConfig {

	@Bean
	public DozerMappingFileBean dozerMappingFileBeanBaseAdmin() {
		DozerMappingFileBean dozerMappingFileBean = new DozerMappingFileBean();
		return dozerMappingFileBean;
	}

	@Bean
	public Mapper mapper() {
		return DozerBeanMapperBuilder.create().build();
	}

	@Bean
	public FieldTypeDOtoFieldTypeDTO fieldTypeDOtoFieldTypeDTOConverter() {
		return new FieldTypeDOtoFieldTypeDTO();
	}

	@Bean
	public FieldTypeDTOtoFieldTypeDO fieldTypeDTOtoFieldTypeDOConverter() {
		return new FieldTypeDTOtoFieldTypeDO();
	}

	@Bean
	public FlowDOtoFlowDTO flowDOtoFlowDTOConverter() {
		return new FlowDOtoFlowDTO();
	}

	@Bean
	public FlowDTOtoFlowDO flowDTOtoFlowDOConverter() {
		return new FlowDTOtoFlowDO();
	}

	@Bean
	public DriverDOtoDriverDTO driverDOtoDriverDTOConverter() {
		return new DriverDOtoDriverDTO();
	}

	@Bean
	public DriverDTOtoDriverDO driverDTOtoDriverDOConverter() {
		return new DriverDTOtoDriverDO();
	}

	@Bean
	public DataSourceDOtoDataSourceDTO dataSourceDTOConverter() {
		return new DataSourceDOtoDataSourceDTO();
	}

	@Bean
	public DataSourceDTOtoDataSourceDO dataSourceDOConverter() {
		return new DataSourceDTOtoDataSourceDO();
	}

	@Bean
	public VersionDOtoVersionDTO versionDTOConverter() {
		return new VersionDOtoVersionDTO();
	}

	@Bean
	public VersionDTOtoVersionDO versionDOConverter() {
		return new VersionDTOtoVersionDO();
	}

	@Bean
	public FlowTableDOtoFlowTableDTO flowTableDTOConverter() {
		return new FlowTableDOtoFlowTableDTO();
	}

	@Bean
	public FlowTableDTOtoFlowTableDO flowTableDOConverter() {
		return new FlowTableDTOtoFlowTableDO();
	}

	@Bean
	public FlowTableFieldDOtoFlowTableFieldDTO flowTableFieldDTOConverter() {
		return new FlowTableFieldDOtoFlowTableFieldDTO();
	}

	@Bean
	public FlowTableFieldDTOtoFlowTableFieldDO flowTableFieldDOConverter() {
		return new FlowTableFieldDTOtoFlowTableFieldDO();
	}

	@Bean
	public FlowTableFieldDOtoFormFlowTableFieldDTO flowTableFieldDOtoFormFlowTableFieldDTOConverter() {
		return new FlowTableFieldDOtoFormFlowTableFieldDTO();
	}

	@Bean
	public FlowDOtoFormFlowDTO flowDOtoFormFlowDTOConverter() {
		return new FlowDOtoFormFlowDTO();
	}

	@Bean
	public FlowTableDOtoFormFlowTableDTO flowTableDOtoFormFlowTableDTOConverter() {
		return new FlowTableDOtoFormFlowTableDTO();
	}

	@Bean
	public FlowImportRequestDOtoFlowImportRequestDTO flowImportRequestDTOConverter() {
		return new FlowImportRequestDOtoFlowImportRequestDTO();
	}

	@Bean
	public FlowImportRequestDTOtoFlowImportRequestDO flowImportRequestDOConverter() {
		return new FlowImportRequestDTOtoFlowImportRequestDO();
	}

	@Bean
	public FlowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDO flowImportRequestFieldDateDOConverter() {
		return new FlowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDO();
	}

	@Bean
	public FlowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTO flowImportRequestFieldDateDTOConverter() {
		return new FlowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTO();
	}

	@Bean
	public FlowExportRequestDOtoFlowExportRequestDO FlowExportRequestDOtoDOConverter() {
		return new FlowExportRequestDOtoFlowExportRequestDO();
	}

	@Bean
	public FlowExportRequestDOtoFlowExportRequestDTO FlowExportRequestDTOConverter() {
		return new FlowExportRequestDOtoFlowExportRequestDTO();
	}

	@Bean
	public FlowExportRequestDTOtoFlowExportRequestDO FlowExportRequestDOConverter() {
		return new FlowExportRequestDTOtoFlowExportRequestDO();
	}

	@Bean
	public JobTalendDOtoJobTalendDTO JobTalendDTOConverter() {
		return new JobTalendDOtoJobTalendDTO();
	}

	@Bean
	public JobTalendDTOtoJobTalendDO JobTalendDOConverter() {
		return new JobTalendDTOtoJobTalendDO();
	}

	@Bean
	public StateDOtoStateDTO StateDTOConverter() {
		return new StateDOtoStateDTO();
	}

	@Bean
	public FormFlowTableLinkDTOtoFlowForeignKeyDTO formFlowTableLinkDTOtoFlowForeignKeyDTOConverter() {
		return new FormFlowTableLinkDTOtoFlowForeignKeyDTO();
	}

	@Bean
	public FlowForeignKeyDTOtoFlowForeignKeyDO flowForeignKeyDTOtoFlowForeignKeyDOConverter() {
		return new FlowForeignKeyDTOtoFlowForeignKeyDO();
	}

	@Bean
	public FlowForeignKeyDOtoFlowForeignKeyDTO flowForeignKeyDOtoFlowForeignKeyDTOConverter() {
		return new FlowForeignKeyDOtoFlowForeignKeyDTO();
	}
	
	@Bean
	public FlowForeignKeyDOtoFormFlowTableLinkDTO flowForeignKeyDOtoFormFlowTableLinkDTO() {
		return new FlowForeignKeyDOtoFormFlowTableLinkDTO();
	}

	@Bean
	public StateDTOtoStateDO StateDOConverter() {
		return new StateDTOtoStateDO();
	}

	@Bean
	public FormFlowDTOtoFlowDO formFlowDTOtoFlowDOConverter() {
		return new FormFlowDTOtoFlowDO();
	}

	@Bean
	public FormFlowTableDTOtoFlowTableDO formFlowTableDTOtoFlowTableDOConverter() {
		return new FormFlowTableDTOtoFlowTableDO();
	}

	@Bean
	public FormFlowTableFieldDTOtoFlowTableFieldDO formFlowTableFieldDTOtoFlowTableFieldDOConverter() {
		return new FormFlowTableFieldDTOtoFlowTableFieldDO();
	}

	@Bean
	public FormFlowTableLinkDTOtoFlowForeignKeyDO formFlowTableLinkDTOtoFlowForeignKeyDOConverter() {
		return new FormFlowTableLinkDTOtoFlowForeignKeyDO();
	}

	@Bean
	public FlowImportRequestDTOtoExtractionDTO flowImportRequestDTOtoExtractionDTOConverter() {
		return new FlowImportRequestDTOtoExtractionDTO();
	}

	@Bean
	public FlowDTOtoFormFlowDTO flowDTOtoFormFlowDTOConverter() {
		return new FlowDTOtoFormFlowDTO();
	}

	@Bean
	public FlowImportRequestFieldDateDTOtoField flowImportRequestFieldDateDTOtoField() {
		return new FlowImportRequestFieldDateDTOtoField();
	}

	@Bean
	public FlowConfigurationFilterDOtoFlowConfigurationFilterDTO flowConfigurationFilterDOtoFlowConfigurationFilterDTO() {
		return new FlowConfigurationFilterDOtoFlowConfigurationFilterDTO();
	}

	@Bean
	public FlowConfigurationFilterDTOtoFlowConfigurationFilterDO flowConfigurationFilterDTOtoFlowConfigurationFilterDO() {
		return new FlowConfigurationFilterDTOtoFlowConfigurationFilterDO();
	}

	@Bean
	public FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO flowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO() {
		return new FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO();
	}

	@Bean
	public FlowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO flowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO() {
		return new FlowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO();
	}

	@Bean
	public FlowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO flowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO() {
		return new FlowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO();
	}

	@Bean
	public FlowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO flowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO() {
		return new FlowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO();
	}

	@Bean
	public FlowConfigurationFilterFieldDAOQueryByBaseSearchInput flowConfigurationFilterFieldDAOQueryByBaseSearchInput() {
		return new FlowConfigurationFilterFieldDAOQueryByBaseSearchInput();
	}

	@Bean
	public DashboardDOtoDashboardDTO dashboardDOtoDashboardDTOConverter() {
		return new DashboardDOtoDashboardDTO();
	}

	@Bean
	public DashboardDTOtoDashboardDO dashboardDTOtoDashboardDOConverter() {
		return new DashboardDTOtoDashboardDO();
	}

	@Bean
	public FlowRegionUnionDOtoFlowRegionUnionDTO flowRegionUnionDOtoFlowRegionUnionDTO() {
		return new FlowRegionUnionDOtoFlowRegionUnionDTO();
	}

	@Bean
	public FlowRegionUnionDTOtoFlowRegionUnionDO flowRegionUnionDTOtoFlowRegionUnionDO() {
		return new FlowRegionUnionDTOtoFlowRegionUnionDO();
	}

	@Bean
	public LinkedHashMapToFormFlowDTO linkedHashMapToFormFlowDTO() {
		return new LinkedHashMapToFormFlowDTO();
	}

	@Bean
	public DashboardConfigDOtoDashboardConfigDTO dashboardConfigDOtoDashboardConfigDTO() {
		return new DashboardConfigDOtoDashboardConfigDTO();
	}

	@Bean
	public JobTalendDipendencyDOtoJobTalendDipendencyDTO jobTalendDipendencyDOtoJobTalendDipendencyDTO() {
		return new JobTalendDipendencyDOtoJobTalendDipendencyDTO();
	}

	@Bean
	public JobTalendDipendencyDTOtoJobTalendDipendencyDO jobTalendDipendencyDTOtoJobTalendDipendencyDO() {
		return new JobTalendDipendencyDTOtoJobTalendDipendencyDO();
	}

	@Bean
	public JobTalendFileDOtoJobTalendFileDTO jobTalendFileDOtoJobTalendFileDTO() {
		return new JobTalendFileDOtoJobTalendFileDTO();
	}

	@Bean
	public JobTalendFileDTOtoJobTalendFileDO jobTalendFileDTOtoJobTalendFileDO() {
		return new JobTalendFileDTOtoJobTalendFileDO();
	}
	
	@Bean
	public FlowFileUploadRequestDOtoFlowFileUploadRequestDTO flowFileUploadRequestDOtoFlowFileUploadRequestDTO() {
		return new FlowFileUploadRequestDOtoFlowFileUploadRequestDTO();
	}
	
	@Bean
	public FlowFileUploadRequestDTOtoFlowFileUploadRequestDO flowFileUploadRequestDTOtoFlowFileUploadRequestDO() {
		return new FlowFileUploadRequestDTOtoFlowFileUploadRequestDO();
	}
	
	@Bean
	public UserToUserDTO userToUserDTO() {
		return new UserToUserDTO();
	}
	
	@Bean
	public FlowDrgDOtoFlowDrgDTO flowDrgDOtoFlowDrgDTO() {
		return new FlowDrgDOtoFlowDrgDTO();
	}
	
	@Bean
	public FlowDrgDTOtoFlowDrgDO flowDrgDTOtoFlowDrgDO() {
		return new FlowDrgDTOtoFlowDrgDO();
	}
	
	
	 @Bean 
	 public AnagraficaAssistitoDTO anagraficaAssistitoDTO() { 
		 return new AnagraficaAssistitoDTO(); 
	 }
	 
	
	@Bean
    public AnagraficaAssistitoDOtoAnagraficaAssistitoDTO anagraficaAssistitoDOtoAnagraficaAssistitoDTO() {
        return new AnagraficaAssistitoDOtoAnagraficaAssistitoDTO();
    }
   
    @Bean
    public AnagraficaAssistitoDTOtoAnagraficaAssistitoDO anagraficaAssistitoDTOtoAnagraficaAssistitoDO() {
        return new AnagraficaAssistitoDTOtoAnagraficaAssistitoDO();
    }
    
    @Bean
	public AnagrafcaAssistitoPaginationDTO anagraficaAssistitoPaginationDTO() {
		return new AnagrafcaAssistitoPaginationDTO();
	}
    
    @Bean
    public MonitorSdoXlDOtoMonitorSdoXlDTO monitorSdoXlDOtoMonitorSdoXlDTO() {
    	return new MonitorSdoXlDOtoMonitorSdoXlDTO();
    }
    
    @Bean
    public MonitorSdoXlDTOtoMonitorSdoXlDO monitorSdoXlDTOtoMonitorSdoXlDO() {
    	return new MonitorSdoXlDTOtoMonitorSdoXlDO();
    }
    
    @Bean
	public UploadReturnsRequestDTO uploadReturnsRequestDTO() {
		return new UploadReturnsRequestDTO();
	}
	
	@Bean
	public UploadReturnsRequestDO uploadReturnsRequestDO() {
		return new UploadReturnsRequestDO();
	}
	
	@Bean
	public UploadReturnsRequestDOtoUploadReturnsRequestDTO uploadReturnsRequestDTOConverter() {
		return new UploadReturnsRequestDOtoUploadReturnsRequestDTO();
	}

	@Bean
	public UploadReturnsRequestDTOtoUploadReturnsRequestDO uploadReturnsRequestDOConverter() {
		return new UploadReturnsRequestDTOtoUploadReturnsRequestDO();
	}
	
	@Bean
	public ProfiloFlussiDOtoProfiloFlussiDTO profiloFlussiDTOConverter() {
		return new ProfiloFlussiDOtoProfiloFlussiDTO();
	}

	@Bean
	public ProfiloFlussiDTOtoProfiloFlussiDO profiloFlussiDOConverter() {
		return new ProfiloFlussiDTOtoProfiloFlussiDO();
	}
	
	@Bean 
	 public AnagraficaAssistitoDownloadDTO anagraficaAssistitoDownloadDTO() { 
		 return new AnagraficaAssistitoDownloadDTO(); 
	 }
	
}
