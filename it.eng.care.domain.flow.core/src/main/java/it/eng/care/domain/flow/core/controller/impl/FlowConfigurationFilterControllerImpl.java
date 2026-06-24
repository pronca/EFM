package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.FlowConfigurationFilterController;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterDOtoFlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterDTOtoFlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.converter.FlowConfigurationFilter.FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.service.FlowConfigurationFilterService;
import it.eng.care.domain.flow.core.service.FlowTableFieldService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/fm/FlowConfigurationFilter")
public class FlowConfigurationFilterControllerImpl implements FlowConfigurationFilterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowConfigurationFilterControllerImpl.class);

    @Autowired
    private FlowTableFieldService flowTableFieldService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private FlowConfigurationFilterService flowConfigurationFilterService;
    @Autowired
    private FlowConfigurationFilterDOtoFlowConfigurationFilterDTO flowConfigurationFilterDOtoFlowConfigurationFilterDTO;
    @Autowired
    private FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO flowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO;
    @Autowired
    private FlowConfigurationFilterDTOtoFlowConfigurationFilterDO flowConfigurationFilterDTOtoFlowConfigurationFilterDO;

    @PostConstruct
    public void post() {
        LOGGER.info("FlowControllerImpl.post");
        conversionService.registerConverter(FlowConfigurationFilterDO.class, FlowConfigurationFilterDTO.class, flowConfigurationFilterDOtoFlowConfigurationFilterDTO);
        conversionService.registerConverter(FlowConfigurationFilterDTO.class, FlowConfigurationFilterDO.class, flowConfigurationFilterDTOtoFlowConfigurationFilterDO);
    }

    @Override
    @PostMapping("/save")
    public SaveOperationResult<FlowConfigurationFilterDTO> save(@RequestBody FlowConfigurationFilterDTO inputDTO) {
        if (inputDTO.getId() == null || inputDTO.getId().equals("") || inputDTO.getId() == "")
            flowConfigurationFilterService.createEntity(inputDTO);
        else
            flowConfigurationFilterService.updateEntity(inputDTO);
        return SaveOperationResult.success(inputDTO);
    }

    @Override
    @PostMapping("/getAllFlowFields")
    @ResponseBody
    public SearchOperationResult<FormFlowTableFieldDTO> getAllFlowFields(@RequestBody BaseSearchInput searchInput) {
        Pair<List<FlowTableFieldDO>, SearchInfo> searchResults = flowTableFieldService.retrieveAllFiltered(searchInput, true);
        List<FormFlowTableFieldDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FormFlowTableFieldDTO.class);
        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    public OperationResult<FlowConfigurationFilterDTO> getEntityBy(String entityKeyName, String entityKeyValue) {
        return null;
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<FlowConfigurationFilterDTO> search(@RequestBody BaseSearchInput searchInput) {

        Pair<List<FlowConfigurationFilterDO>, SearchInfo> searchResults = flowConfigurationFilterService.retrieveAllFiltered(searchInput, true);
        List<FlowConfigurationFilterDTO> dtos = new ArrayList<>();
        for (FlowConfigurationFilterDO flowConfigurationFilterDO : searchResults.getFirst()) {
            dtos.add(conversionService.convertTo(flowConfigurationFilterDO, FlowConfigurationFilterDTO.class));
        }

        //List<FlowConfigurationFilterDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FlowConfigurationFilterDTO.class);


        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    @PostMapping("/delete")
    public OperationResult<Set<String>> deleteEntityBy(@RequestBody FlowConfigurationFilterDTO dto) {
        FlowConfigurationFilterDO flowConfigurationFilterDO = conversionService.convertTo(dto, FlowConfigurationFilterDO.class);
        Set<String> dos = flowConfigurationFilterService.deleteEntity(flowConfigurationFilterDO);
        return OperationResult.success(dos);
    }
}
