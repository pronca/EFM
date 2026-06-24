package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.FieldTypeController;
import it.eng.care.domain.flow.core.converter.FieldType.FieldTypeDOtoFieldTypeDTO;
import it.eng.care.domain.flow.core.converter.FieldType.FieldTypeDTOtoFieldTypeDO;
import it.eng.care.domain.flow.core.dto.FieldTypeDTO;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.domain.flow.core.service.FieldTypeService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/fm/FieldTypeDTO")
public class FieldTypeControllerImpl implements FieldTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldTypeControllerImpl.class);

    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private FieldTypeDOtoFieldTypeDTO fieldTypeDOtoFieldTypeDTOConvert;

    @Autowired
    private FieldTypeDTOtoFieldTypeDO fieldTypeDOTtoFieldTypeDOConvert;


    public FieldTypeControllerImpl() {
    }

    @PostConstruct
    public void post() {
        conversionService.registerConverter(FieldTypeDO.class, FieldTypeDTO.class, fieldTypeDOtoFieldTypeDTOConvert);
        conversionService.registerConverter(FieldTypeDTO.class, FieldTypeDO.class, fieldTypeDOTtoFieldTypeDOConvert);
    }

    @Override
    public SaveOperationResult<FieldTypeDTO> save(FieldTypeDTO inputDTO) {
        return null;
    }

    @Override
    public OperationResult<FieldTypeDTO> getEntityBy(String entityKeyName, String entityKeyValue) {
        return null;
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<FieldTypeDTO> search(BaseSearchInput searchInput) {
        List<FieldTypeDO> searchResults = fieldTypeService.findAll();
        List<FieldTypeDTO> dtos = conversionService.convertAllTo(searchResults, FieldTypeDTO.class);
        return SearchOperationResult.success(dtos);
    }

    @Override
    public void deleteEntityBy(String entityKeyName, String entityKeyValue) {

    }

}