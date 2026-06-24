package it.eng.care.domain.flow.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;

import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.domain.flow.core.entity.DriverDO;
import it.eng.care.domain.flow.core.entity.ErrorMessageDO;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;
import it.eng.care.domain.flow.core.entity.FlowExportReqFileTalendDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestFieldDateDO;
import it.eng.care.domain.flow.core.entity.FlowReceiverLogDO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.entity.FmSequenceDO;
import it.eng.care.domain.flow.core.entity.IntegrationServiceDO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlFileDO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiDO;
import it.eng.care.domain.flow.core.entity.ResetValidationRequestDO;
import it.eng.care.domain.flow.core.entity.SecondLevelValidationRequestDO;
import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.domain.flow.core.entity.StateMachinePersistDO;
import it.eng.care.domain.flow.core.entity.StateMachinePersistHistoryDO;
import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestErrorDO;
import it.eng.care.domain.flow.flowupload.model.FlowSectionFileDO;
import it.eng.care.domain.flow.flowupload.model.SectionFileDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.webservice.model.CodiciRegioneDO;
import it.eng.care.domain.flow.webservice.model.SdoDrgPosDO;
import it.eng.care.platform.attach.model.AttachInfo;
import it.eng.care.platform.persistence.impl.config.PersistenceConfigurer;

@Configuration
public class FlowPersistConfig {

    @Bean
    public PersistenceConfigurer pPDPersistenceConfigurer() {
        return new PersistenceConfigurer() {
        }
                .addPackage(FlowDO.class)
                .addPackage(FlowTableDO.class)
                .addPackage(FlowTableFieldDO.class)
                .addPackage(FieldTypeDO.class)
                .addPackage(DriverDO.class)
                .addPackage(DataSourceDO.class)
                .addPackage(VersionDO.class)
                .addPackage(FlowExportRequestDO.class)
                .addPackage(FlowImportRequestDO.class)
                .addPackage(FlowImportRequestFieldDateDO.class)
                .addPackage(FlowConfigurationFilterDO.class)
                .addPackage(FlowConfigurationFilterFieldDO.class)
                .addPackage(FlowConfigurationFilterFieldValueDO.class)
                .addPackage(FlowForeignKeyDO.class)
                .addPackage(JobTalendDO.class)
                .addPackage(JobTalendFileDO.class)
                .addPackage(TabgenDO.class)
                .addPackage(TabgenFieldDO.class)
                .addPackage(TabgenValueDO.class)
                .addPackage(StateMachinePersistDO.class)
                .addPackage(StateMachinePersistHistoryDO.class)
                .addPackage(StateDO.class)
                .addPackage(AttachInfo.class)
                .addPackage(JpaRepositoryState.class)
                .addPackage(JpaRepositoryGuard.class)
                .addPackage(JpaRepositoryAction.class)
                .addPackage(JpaRepositoryTransition.class)
                .addPackage(JpaRepositoryState.class)
                .addPackage(JpaRepositoryGuard.class)
                .addPackage(JpaRepositoryAction.class)
                .addPackage(JpaRepositoryTransition.class)
                .addPackage(FlowRegionUnionDO.class)
                .addPackage(ErrorMessageDO.class)
                .addPackage(FlowExportReqFileTalendDO.class)
                .addPackage(FmSequenceDO.class)
                .addPackage(ResetValidationRequestDO.class)
                .addPackage(SecondLevelValidationRequestDO.class)
                .addPackage(CodiciRegioneDO.class)
                .addPackage(SdoDrgPosDO.class)
                .addPackage(UploadReturnsRequestDO.class)
                .addPackage(FlowFileUploadRequestDO.class)
                .addPackage(FlowFileUploadRequestErrorDO.class)
                .addPackage(FlowSectionFileDO.class)
                .addPackage(SectionFileDO.class)
                .addPackage(FlowDrgDO.class)
                .addPackage(IntegrationServiceDO.class)
                .addPackage(AnagraficaAssistitoDO.class)
                .addPackage(MonitorSdoXlFileDO.class)
                .addPackage(MonitorSdoXlDO.class)
                .addPackage(FlowReceiverLogDO.class)
                .addPackage(ProfiloFlussiDO.class);
                
              
    }
}
