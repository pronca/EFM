package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.FlowConfigurationFilterFieldController;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.domain.flow.core.service.FlowConfigurationFilterFieldService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
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
@RequestMapping("/fm/FlowConfigurationFilterField")
public class FlowConfigurationFilterFieldControllerImpl implements FlowConfigurationFilterFieldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowConfigurationFilterFieldControllerImpl.class);

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private FlowConfigurationFilterFieldService flowConfigurationFilterFieldService;
    @Autowired
    private FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO flowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO;
    @Autowired
    private FlowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO flowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO;
    @Autowired
    private FlowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO flowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO;
    @Autowired
    private FlowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO flowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    @PostConstruct
    public void post() {
        LOGGER.info("FlowControllerImpl.post");
        conversionService.registerConverter(FlowConfigurationFilterFieldDO.class, FlowConfigurationFilterFieldDTO.class, flowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO);
        conversionService.registerConverter(FlowConfigurationFilterFieldDTO.class, FlowConfigurationFilterFieldDO.class, flowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO);
        conversionService.registerConverter(FlowConfigurationFilterFieldValueDO.class, FlowConfigurationFilterFieldValueDTO.class, flowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO);
        conversionService.registerConverter(FlowConfigurationFilterFieldValueDTO.class, FlowConfigurationFilterFieldValueDO.class, flowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO);

    }

    @Override
    @PostMapping("/save")
    public SaveOperationResult<FlowConfigurationFilterFieldDTO> save(@RequestBody FlowConfigurationFilterFieldDTO inputDTO) {
        if (inputDTO.getId() == null || inputDTO.getId().equals("") || inputDTO.getId() == "")
            flowConfigurationFilterFieldService.createEntity(inputDTO);
        else
            flowConfigurationFilterFieldService.updateEntity(inputDTO);
        return SaveOperationResult.success(inputDTO);
    }

    @Override
    public OperationResult<FlowConfigurationFilterFieldDTO> getEntityBy(String entityKeyName, String entityKeyValue) {
        FlowConfigurationFilterFieldDO loValue = flowConfigurationFilterFieldService.retrieveOne(entityKeyValue);
        FlowConfigurationFilterFieldDTO loDto = conversionService.convertTo(loValue, FlowConfigurationFilterFieldDTO.class);
        return OperationResult.success(loDto);
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<FlowConfigurationFilterFieldDTO> search(@RequestBody BaseSearchInput searchInput) {

        Pair<List<FlowConfigurationFilterFieldDO>, SearchInfo> searchResults = flowConfigurationFilterFieldService.retrieveAllFiltered(searchInput);
        List<FlowConfigurationFilterFieldDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FlowConfigurationFilterFieldDTO.class);

        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    @PostMapping("/delete")
    public void deleteEntityBy(@RequestBody FlowConfigurationFilterFieldDTO dto) {
        FlowConfigurationFilterFieldDO flowConfigurationFilterFieldDO = conversionService.convertTo(dto, FlowConfigurationFilterFieldDO.class);
        
        Boolean check = flowManagerProfileService.checkFlowById(
        		flowConfigurationFilterFieldDO.getConfigurationFilter().getFlow());
        if(!check) {
        	return;
        }
        
        flowConfigurationFilterFieldService.deleteEntity(flowConfigurationFilterFieldDO);
    }
}
