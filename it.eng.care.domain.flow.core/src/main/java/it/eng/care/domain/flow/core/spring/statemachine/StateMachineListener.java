package it.eng.care.domain.flow.core.spring.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;

import it.eng.care.domain.flow.core.controller.impl.DataSourceControllerImpl;
import it.eng.care.domain.flow.core.utility.LogUtil;

public class StateMachineListener extends StateMachineListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceControllerImpl.class);

    StateMachinePersister<String, String, String> persistence;
    StateMachine<String, String> stateMachine;
    String id;

    public StateMachineListener(StateMachinePersister<String, String, String> persister) {
        this.persistence = persister;
    }

    @Override
    public void stateChanged(State from, State to) {
        LOGGER.info("Transitioned from ", from == null ?
                "none" : from.getId(), to.getId());


        try {
            persistence.persist(stateMachine, id);
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
        }

    }

    public void setMachine(StateMachine<String, String> stateMachine, String id) {

        this.stateMachine = stateMachine;
        this.id = id;
    }

}
