package it.eng.care.domain.flow.core.spring.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.data.StateMachineRepository;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;

import it.eng.care.domain.flow.core.controller.impl.DataSourceControllerImpl;
import it.eng.care.domain.flow.core.entity.StateMachinePersistDO;
import it.eng.care.domain.flow.core.utility.LogUtil;

public class StateMachineFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceControllerImpl.class);
    @Autowired
    private InMemoryStateMachinePersist persist;
    @Autowired
    private StateMachineFactory<String, String> stateMachineFactory;
    @Autowired
    private StateMachineRepository<StateMachinePersistDO> stateMachineRepository;

    public State<String, String> execute(String id, String factoryModel, String event) throws Exception {

        StateMachinePersister<String, String, String> persister = new DefaultStateMachinePersister<>(persist);
        StateMachineListener list = new StateMachineListener(persister);
        StateMachinePersistDO machine = (id== null || id.isBlank()) ? null : stateMachineRepository.findById(id).orElse(null);

        if (machine != null) {
            StateMachine<String, String> stateMachine0 = persister.restore(stateMachineFactory.getStateMachine(factoryModel),
                    id);
            stateMachine0.addStateListener(list);
            list.setMachine(stateMachine0, id);
            if (stateMachine0.sendEvent(event) == false) {
                LOGGER.info("PASSAGGIO DI STATO NON PERMESSO");
                return stateMachine0.getState();
            }

            return stateMachine0.getState();

        } else {
            StateMachine<String, String> stateMachine1 = stateMachineFactory.getStateMachine(factoryModel);
            stateMachine1.addStateListener(list);
            list.setMachine(stateMachine1, id);

            stateMachine1.start();
            stateMachine1.sendEvent(event);
            if (stateMachine1.sendEvent(event) == false) {
                LOGGER.info("PASSAGGIO DI STATO NON PERMESSO");
                return stateMachine1.getState();
            }
            return stateMachine1.getState();
        }


    }


    public State<String, String> createStateMachine(String id, String factoryModel) {

        StateMachinePersistDO machine = (id== null || id.isBlank()) ? null : stateMachineRepository.findById(id).orElse(null);

        if (machine != null) {
            return null;
        } else {
            try {
                StateMachinePersister<String, String, String> persister = new DefaultStateMachinePersister<>(persist);
                StateMachineListener list = new StateMachineListener(persister);

                StateMachine<String, String> stateMachine1 = stateMachineFactory.getStateMachine(factoryModel);
                stateMachine1.addStateListener(list);
                list.setMachine(stateMachine1, id);

                stateMachine1.start();

                return stateMachine1.getState();
            } catch (Exception e) {
            	LogUtil.logException(LOGGER, "***************** ERROR CREATING STATE MACHINE ************************", e);
//            	e.printStackTrace();
                return null;
            }
        }
    }

    public StateMachinePersistDO getStateMachine(String id) {

        StateMachinePersistDO machine = (id== null || id.isBlank()) ? null : stateMachineRepository.findById(id).orElse(null);

        return machine;


    }


}
