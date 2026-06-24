package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.State2Controller;
import it.eng.care.domain.flow.core.converter.StateMachine.StateDOtoStateDTO;
import it.eng.care.domain.flow.core.converter.StateMachine.StateDTOtoStateDO;
import it.eng.care.domain.flow.core.dto.StateDTO;
import it.eng.care.domain.flow.core.entity.StateDO;
import it.eng.care.domain.flow.core.service.StateService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/State2DTO")
public class State2ControllerImpl implements State2Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobTalendControllerImpl.class);

    @Autowired
    private StateService stateService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private StateDOtoStateDTO stateDOtoStateDTO;

    @Autowired
    private StateDTOtoStateDO stateDTOtoStateDO;

    @PostConstruct
    public void post() {
        conversionService.registerConverter(StateDO.class, StateDTO.class, stateDOtoStateDTO);
        conversionService.registerConverter(StateDTO.class, StateDO.class, stateDTOtoStateDO);
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<StateDTO> search(@RequestBody BaseSearchInput searchInput) {

        searchInput.setValue("machineId", "EXPORT");

        Pair<List<StateDO>, SearchInfo> searchResults = stateService.retrieveAllFiltered(searchInput);

        List<StateDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), StateDTO.class);

        for (int i = 0; i < dtos.size(); i++) {
            dtos.get(i).setName(dtos.get(i).getState());
        }

        return SearchOperationResult.success(dtos, searchResults.getSecond());

    }

}
