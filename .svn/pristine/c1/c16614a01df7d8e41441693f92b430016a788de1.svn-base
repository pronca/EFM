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

import it.eng.care.domain.flow.core.controller.FlowImportRequestController;
import it.eng.care.domain.flow.core.converter.ExtractionFilter.FlowImportRequestFieldDateDTOtoField;
import it.eng.care.domain.flow.core.converter.Flow.FlowDTOtoFormFlowDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestDOtoFlowImportRequestDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestDTOtoFlowImportRequestDO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.converter.FlowImportExportRequest.FlowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDO;
import it.eng.care.domain.flow.core.converter.FlowTableField.FlowTableFieldDOtoFormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.dto.ExtractionFilter.Field;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestFieldDateDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.service.FlowImportRequestService;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowTableFieldService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/FlowImportRequestDTO")
public class FlowImportRequestControllerImpl implements FlowImportRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowImportRequestControllerImpl.class);

    @Autowired
    private FlowImportRequestService flowImportService;
    @Autowired
    private FlowTableFieldService flowTableFieldService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private FlowImportRequestDOtoFlowImportRequestDTO flowImportRequestDOtoFlowImportRequestDTOConverter;

    @Autowired
    private FlowImportRequestDTOtoFlowImportRequestDO flowImportRequestDTOtoFlowImportRequestDOConverter;

    @Autowired
    private FlowTableFieldDOtoFormFlowTableFieldDTO flowTableFieldDOtoFormFlowTableFieldDTO;

    @Autowired
    private FlowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDO flowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDOConverter;

    @Autowired
    private FlowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTO flowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTOConverter;

    @Autowired
    private FlowDTOtoFormFlowDTO flowDTOtoFormFlowDTOConverter;

    @Autowired
    private FlowImportRequestFieldDateDTOtoField flowImportRequestFieldDateDTOtoField;
    
    @Autowired
	private FlowManagerProfileService flowManagerProfileService;

    @PostConstruct
    public void post() {
        conversionService.registerConverter(FlowImportRequestDO.class, FlowImportRequestDTO.class, flowImportRequestDOtoFlowImportRequestDTOConverter);
        conversionService.registerConverter(FlowImportRequestDTO.class, FlowImportRequestDO.class, flowImportRequestDTOtoFlowImportRequestDOConverter);
        conversionService.registerConverter(FlowTableFieldDO.class, FormFlowTableFieldDTO.class, flowTableFieldDOtoFormFlowTableFieldDTO);
        conversionService.registerConverter(FlowImportRequestFieldDateDTO.class, FlowImportRequestFieldDateDO.class, flowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDOConverter);
        conversionService.registerConverter(FlowImportRequestFieldDateDO.class, FlowImportRequestFieldDateDTO.class, flowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTOConverter);
        conversionService.registerConverter(FlowDTO.class, FormFlowDTO.class, flowDTOtoFormFlowDTOConverter);
        conversionService.registerConverter(FlowImportRequestFieldDateDTO.class, Field.class, flowImportRequestFieldDateDTOtoField);
    }


    @Override
    @PostMapping("/save")
    public SaveOperationResult<FlowImportRequestDTO> save(@RequestBody FlowImportRequestDTO flowImportRequestDTO) {
    	//caricamento lista aziende visibili dall'utente
    	List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
    			
        FlowImportRequestDO flowImportRequestDO =
                conversionService.convertTo(flowImportRequestDTO, FlowImportRequestDO.class);
        if (!aziende.isEmpty()) {
        	flowImportRequestDO.setAziendeProfiloFlussi(String.join(",",aziende));
		}
        if (flowImportRequestDO.getId() == null || flowImportRequestDO.getId().trim().equals("")) {
            flowImportRequestDO = flowImportService.createEntity(flowImportRequestDO, true);
        } else {
            flowImportRequestDO = flowImportService.updateEntity(flowImportRequestDO, true);
        }

        FlowImportRequestDTO flowImportRequestDTOresult =
                conversionService.convertTo(flowImportRequestDO, FlowImportRequestDTO.class);

        return SaveOperationResult.success(flowImportRequestDTOresult);
    }

    @Override
    @PostMapping("/delete")
    public Integer deleteEntityBy(@RequestBody FlowImportRequestDTO flowImportRequestDTO) {
        //Logical delete
        //return 1 OK, return 2 KO (stato non cambiato)
        FlowImportRequestDO flowImportRequestDO =
                conversionService.convertTo(flowImportRequestDTO, FlowImportRequestDO.class);
        return flowImportService.deleteEntity(flowImportRequestDO, true);
    }

    @Override
    @PostMapping("/getDataFields")
    @ResponseBody
    public SearchOperationResult<FormFlowTableFieldDTO> getDataFields(@RequestBody BaseSearchInput searchInput) {
        searchInput.setValue("isReferenceDate", true);
        Pair<List<FlowTableFieldDO>, SearchInfo> searchResults = flowTableFieldService.retrieveAllFiltered(searchInput, true);
        List<FormFlowTableFieldDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FormFlowTableFieldDTO.class);
        return SearchOperationResult.success(dtos, searchResults.getSecond());

    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<FlowImportRequestDTO> search(@RequestBody BaseSearchInput searchInput) {
    	//caricamento lista aziende visibili dall'utente
		List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
		searchInput.setParam("aziende", aziende);
		
        Pair<List<FlowImportRequestDO>, SearchInfo> searchResults = flowImportService.retrieveAllFiltered(searchInput, true);
        List<FlowImportRequestDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), FlowImportRequestDTO.class);
        return SearchOperationResult.success(dtos, searchResults.getSecond());

    }

    @Override
    @PostMapping("/start")
    public SaveOperationResult<FlowImportRequestDTO> startImport(@RequestBody FlowImportRequestDTO flowExtractDTO) {
    	
        flowImportService.startImport(flowExtractDTO, true);
        return SaveOperationResult.success(flowExtractDTO);
    }


}
