package it.eng.care.domain.flow.core.controller.impl;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.controller.FlowDrgController;
import it.eng.care.domain.flow.core.converter.FlowDrg.FlowDrgDOtoFlowDrgDTO;
import it.eng.care.domain.flow.core.converter.FlowDrg.FlowDrgDTOtoFlowDrgDO;
import it.eng.care.domain.flow.core.dto.FlowDrgDTO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;
import it.eng.care.domain.flow.core.service.FlowDrgService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

@RestController
@RequestMapping("/fm/FlowDrgDTO")
public class FlowDrgControllerImpl implements FlowDrgController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowDrgControllerImpl.class);
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private FlowDrgDOtoFlowDrgDTO flowDrgDOtoFlowDrgDTOConverter;
	
	@Autowired
	private FlowDrgDTOtoFlowDrgDO flowDrgDTOtoFlowDrgDOConverter;
	
	@Autowired
	private FlowDrgService flowDrgService;
	
	@PostConstruct
	public void post() {
		conversionService.registerConverter(FlowDrgDO.class, FlowDrgDTO.class,
				flowDrgDOtoFlowDrgDTOConverter);
		conversionService.registerConverter(FlowDrgDTO.class, FlowDrgDO.class,
				flowDrgDTOtoFlowDrgDOConverter);

	}

	@PostMapping("/_searchByExtractionId")
    @ResponseBody
	@Override
	public SearchOperationResult<FlowDrgDTO> searchByExtractionId(@RequestBody String extractionId) {
		List<FlowDrgDTO> res = flowDrgService.searchDTOByExtractionId(extractionId);
		return SearchOperationResult.success(res);
	}
	

}
