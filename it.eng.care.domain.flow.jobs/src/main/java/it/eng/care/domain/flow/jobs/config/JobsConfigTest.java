package it.eng.care.domain.flow.jobs.config;

import org.quartz.SimpleTrigger;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.jobs.dao.DashboardJobDAO;
import it.eng.care.domain.flow.jobs.dao.DashboardJobDAOImpl;
import it.eng.care.domain.flow.jobs.jobs.fistLevelValidation.ValidateFlowImportRequestJob;
import it.eng.care.domain.flow.jobs.jobs.fistLevelValidation.ValidateRestServiceFlowJob;
import it.eng.care.domain.flow.jobs.jobs.secondLevelValidation.ExecuteFlowExportRequestJob;
import it.eng.care.domain.flow.jobs.jobs.secondLevelValidation.ValidateFlowExportRequestJob;
import it.eng.care.domain.flow.jobs.service.JobService;
import it.eng.care.domain.flow.jobs.service.api.DashboardJobService;
import it.eng.care.domain.flow.jobs.service.impl.DashboardJobServiceImpl;
import it.eng.care.domain.flow.jobs.service.impl.JobServiceImpl;

@Configuration
@EnableCaching
@Import(value = {
		DroolsConfigTest.class } )
public class JobsConfigTest {
	
	@Bean
	public JobService jobService() {
		return new JobServiceImpl();
	}
	
	@Bean
    public ExecuteFlowExportRequestJob ExecuteFlowExportRequestJob() {
    	return new ExecuteFlowExportRequestJob();
    }
	
    @Bean
    public ValidateFlowImportRequestJob ValidateImportedFlowsJob() {
    	return new ValidateFlowImportRequestJob();
    }
    
    @Bean
    public ValidateRestServiceFlowJob ValidateSingleRowJob() {
    	return new ValidateRestServiceFlowJob();
    }
    
    @Bean
    public ValidateFlowExportRequestJob ValidateExportedFlowJob() {
    	return new ValidateFlowExportRequestJob();
    }
    
    @Bean
    public DashboardJobService DashboardJobService() {
    	return new DashboardJobServiceImpl();
    }
    
    @Bean
    public DashboardJobDAO DashboardJobDAO() {
    	return new DashboardJobDAOImpl();
    }
    
    @Bean(name = "ExecuteFlowExportRequestJobDetail")
    public JobDetailFactoryBean executeFlowExportRequestJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ExecuteFlowExportRequestJob.class);
        jobDetailFactory.setDescription("ExecuteFlowExportRequestJobDetail");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    
    @Bean(name = "flowValidationJobDetail")
    public JobDetailFactoryBean flowValidationJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ValidateFlowImportRequestJob.class);
        jobDetailFactory.setDescription("flowValidationJobDetail");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    
    @Bean(name = "flowValidationSingleRowJobDetail")
    public JobDetailFactoryBean flowValidationSingleRowJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ValidateRestServiceFlowJob.class);
        jobDetailFactory.setDescription("flowValidationSingleRowJobDetail");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    
    @Bean(name = "flowValidateExportedRowJobDetail")
    public JobDetailFactoryBean flowValidateExportedRowJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ValidateFlowExportRequestJob.class);
        jobDetailFactory.setDescription("flowValidateExportedRowJobDetail");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    /*
    @Bean
    public SimpleTriggerFactoryBean executeFlowExportRequesTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(executeFlowExportRequestJobDetail().getObject());
        trigger.setRepeatInterval(30000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
    
    @Bean
    public SimpleTriggerFactoryBean flowValidationTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(flowValidationJobDetail().getObject());
        trigger.setRepeatInterval(30000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
    
    @Bean
    public SimpleTriggerFactoryBean flowValidationSingleRowTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(flowValidationSingleRowJobDetail().getObject());
        trigger.setRepeatInterval(30000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
    
    @Bean
    public SimpleTriggerFactoryBean flowValidateExportedRowTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(flowValidateExportedRowJobDetail().getObject());
        trigger.setRepeatInterval(30000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
   
   @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobFactory(new SpringBeanJobFactory());
        schedulerFactory.setTriggers(
        		flowValidationTrigger().getObject(),
        		flowValidateExportedRowTrigger().getObject(),
        		flowValidationSingleRowTrigger().getObject());
        
        return schedulerFactory;
    }
*/        
	
}
