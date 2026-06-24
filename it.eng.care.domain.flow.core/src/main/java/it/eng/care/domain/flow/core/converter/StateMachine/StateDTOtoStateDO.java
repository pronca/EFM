package it.eng.care.domain.flow.core.converter.StateMachine;

import it.eng.care.domain.flow.core.dto.StateDTO;
import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class StateDTOtoStateDO implements Converter<StateDTO, StateDO> {

    @Override
    public void convert(StateDTO fromObject, StateDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setInitialState(fromObject.getInitialState());
        intoObject.setKind(fromObject.getKind());
        intoObject.setMachineId(fromObject.getMachineId());
        intoObject.setRegion(fromObject.getRegion());
        intoObject.setState(fromObject.getState());
        intoObject.setSubMachineId(fromObject.getSubMachineId());
        intoObject.setInitialActionId(fromObject.getInitialActionId());
        intoObject.setParentStateId(fromObject.getParentStateId());

    }

}
