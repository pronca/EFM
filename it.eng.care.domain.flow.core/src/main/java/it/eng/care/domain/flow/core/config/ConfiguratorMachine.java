package it.eng.care.domain.flow.core.config;

import it.eng.care.domain.flow.core.spring.statemachine.InMemoryStateMachinePersist;
import it.eng.care.domain.flow.core.spring.statemachine.StateMachineFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.data.RepositoryStateMachineModelFactory;
import org.springframework.statemachine.data.StateRepository;
import org.springframework.statemachine.data.TransitionRepository;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;
import org.springframework.statemachine.kryo.KryoStateMachineSerialisationService;
import org.springframework.statemachine.service.StateMachineSerialisationService;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.springframework.statemachine.data.jpa"})
@EnableJpaRepositories(basePackages = {"org.springframework.statemachine.data.jpa"})
public class ConfiguratorMachine {

    @Bean
    public StateMachineSerialisationService<String, String> serialize() {
        return new KryoStateMachineSerialisationService<String, String>();
    }

    @Bean
    public InMemoryStateMachinePersist persist() {
        return new InMemoryStateMachinePersist();
    }

    @Bean
    public StateMachineFlow stateMachineFlow() {
        return new StateMachineFlow();
    }

    @Configuration
    @EnableStateMachineFactory
    static class ConfigStateMachineTwo extends StateMachineConfigurerAdapter<String, String> {

        @Autowired
        private StateRepository<JpaRepositoryState> stateRepository;

        @Autowired
        private TransitionRepository<JpaRepositoryTransition> transitionRepository;


        @Override
        public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
            model.withModel().factory(modelFactory());
        }

        @Bean
        public StateMachineModelFactory<String, String> modelFactory() {
            return new RepositoryStateMachineModelFactory(stateRepository, transitionRepository);
        }

    }

}