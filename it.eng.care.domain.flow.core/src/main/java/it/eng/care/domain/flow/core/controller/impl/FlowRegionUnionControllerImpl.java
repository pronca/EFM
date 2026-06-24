package it.eng.care.domain.flow.core.controller.impl;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.controller.FlowRegionUnionController;
import it.eng.care.domain.flow.core.converter.FlowRegionUnion.FlowRegionUnionDOtoFlowRegionUnionDTO;
import it.eng.care.domain.flow.core.converter.FlowRegionUnion.FlowRegionUnionDTOtoFlowRegionUnionDO;
import it.eng.care.domain.flow.core.dto.FlowRegionUnionDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowRegionUnionService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/FlowRegionUnionDTO")
public class FlowRegionUnionControllerImpl implements FlowRegionUnionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowRegionUnionControllerImpl.class);

    @Autowired
    FormFlowService formFlowService;

    @Autowired
    private FlowRegionUnionService flowRegionUnionService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private FlowRegionUnionDOtoFlowRegionUnionDTO flowRegionUnionDOtoFlowRegionUnionDTO;

    @Autowired
    private FlowRegionUnionDTOtoFlowRegionUnionDO flowRegionUnionDTOtoFlowRegionUnionDO;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;

    @PostConstruct
    public void post() {
        LOGGER.info("FlowControllerImpl.post");
        conversionService.registerConverter(FlowRegionUnionDO.class, FlowRegionUnionDTO.class, flowRegionUnionDOtoFlowRegionUnionDTO);
        conversionService.registerConverter(FlowRegionUnionDTO.class, FlowRegionUnionDO.class, flowRegionUnionDTOtoFlowRegionUnionDO);

    }

    /*
    @Autowired
    private FlowService flowService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private FlowDOtoFlowDTO flowDOToFlowDTO;

    @Autowired
    private FlowDTOtoFlowDO FlowDTOtoFlowDO;


    public FlowRegionUnionControllerImpl() {
        LOGGER.info("FlowControllerImpl");
    }

    @PostConstruct
    public void post() {
        LOGGER.info("FlowControllerImpl.post");
        conversionService.registerConverter(FlowDO.class, FlowDTO.class, flowDOToFlowDTO);
        conversionService.registerConverter(FlowDTO.class, FlowDO.class, FlowDTOtoFlowDO);

    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<FlowDTO> search(@RequestBody BaseSearchInput searchInput) {


        Pair<List<FlowDO>, SearchInfo> searchResults = flowService.retrieveAllFiltered(searchInput);
        List<FlowDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FlowDTO.class);

        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    @GetMapping("/{entityKeyName}/{entityKeyValue}")
    public OperationResult<FlowDTO> getEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {


        LOGGER.info("{} , {}", entityKeyName, entityKeyValue);

        FlowDO loValue = flowService.retrieveOne(entityKeyValue);

        FlowDTO loDto = conversionService.convertTo(loValue, FlowDTO.class);
        return OperationResult.success(loDto);
    }

    @Override
    @PostMapping
    public SaveOperationResult<FlowDTO> save(@RequestBody FlowDTO inputDTO) {

        FlowDO loValue = conversionService.convertTo(inputDTO, FlowDO.class);

        if (loValue.getId() == null) {
            loValue = flowService.createEntity(loValue);
        } else {
            loValue = flowService.updateEntity(loValue);
        }

        FlowDTO outputDTO = conversionService.convertTo(loValue, FlowDTO.class);
        return SaveOperationResult.success(outputDTO);
    }


    @Override
    @DeleteMapping("/{entityKeyName}/{entityKeyValue}")
    public void deleteEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {


        LOGGER.info("{} , {}", entityKeyName, entityKeyValue);

        flowService.deleteEntity(entityKeyValue);

    }*/

    @Override
    public SaveOperationResult<FlowRegionUnionDTO> save(FlowRegionUnionDTO inputDTO) {
        return null;
    }

    @Override
    public OperationResult<FlowRegionUnionDTO> getEntityBy(String entityKeyName, String entityKeyValue) {
        return null;
    }

    @Override
    public SearchOperationResult<FlowRegionUnionDTO> search(BaseSearchInput searchInput) {

        Pair<List<FlowRegionUnionDO>, SearchInfo> searchResults = flowRegionUnionService.retrieveAllFiltered(searchInput);
        List<FlowRegionUnionDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FlowRegionUnionDTO.class);
        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    public void deleteEntityBy(String entityKeyName, String entityKeyValue) {

    }

    @Override
    @PostMapping("/getFmFlowByFlowAzienda")
    @ResponseBody
    public FormFlowDTO getFmFlowByFlowAzienda(@RequestBody BaseSearchInput baseSearchInput) {
//        BaseSearchInput baseSearchInput = new BaseSearchInput();
//        baseSearchInput.setValue("flowLocal", idAzienda);
    	
        FlowRegionUnionDO flowRegionUnionDO = flowRegionUnionService.retrieveAllFiltered(baseSearchInput).getFirst().get(0);
        
        if(!flowManagerProfileService.checkFlowById(flowRegionUnionDO.getFlowRegion().getId())) {
    		return null;
    	}
        
        BaseSearchInput baseSearchInput2 = new BaseSearchInput();
        baseSearchInput2.setValue("flowId", flowRegionUnionDO.getFlowRegion().getId());
        baseSearchInput2.setValue("versionId",baseSearchInput.getValue("version"));
        FormFlowDTO formFolowDO = formFlowService.retrieveAllFiltered(baseSearchInput2).getFirst().get(0);
        return formFolowDO;
    }
}
