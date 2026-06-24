package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.DriverController;
import it.eng.care.domain.flow.core.converter.Driver.DriverDOtoDriverDTO;
import it.eng.care.domain.flow.core.converter.Driver.DriverDTOtoDriverDO;
import it.eng.care.domain.flow.core.dto.DriverDTO;
import it.eng.care.domain.flow.core.entity.DriverDO;
import it.eng.care.domain.flow.core.service.DriverService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/fm/DriverDTO")
public class DriverControllerImpl implements DriverController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverControllerImpl.class);

    @Autowired
    private DriverService driverService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private DriverDOtoDriverDTO driverDOtoDriverDTOConvert;

    @Autowired
    private DriverDTOtoDriverDO driverDOTtoDriverDOConvert;


    public DriverControllerImpl() {
        LOGGER.info("DriverControllerImpl");
    }

    @PostConstruct
    public void post() {
        LOGGER.info("DriverControllerImpl.post");
        conversionService.registerConverter(DriverDO.class, DriverDTO.class, driverDOtoDriverDTOConvert);
        conversionService.registerConverter(DriverDTO.class, DriverDO.class, driverDOTtoDriverDOConvert);
    }

    @Override
    @PostMapping
    public SaveOperationResult<DriverDTO> save(@RequestBody DriverDTO inputDTO) {

        DriverDO loValue = conversionService.convertTo(inputDTO, DriverDO.class);

        if (loValue.getId() == null) {
            loValue = driverService.createEntity(loValue);
        } else {
            loValue = driverService.updateEntity(loValue);
        }

        DriverDTO outputDTO = conversionService.convertTo(loValue, DriverDTO.class);
        return SaveOperationResult.success(outputDTO);
    }

    @Override
    @GetMapping("/{entityKeyName}/{entityKeyValue}")
    public OperationResult<DriverDTO> getEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {
        DriverDO loValue = driverService.retrieveOne(entityKeyValue);
        DriverDTO loDto = conversionService.convertTo(loValue, DriverDTO.class);
        return OperationResult.success(loDto);
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<DriverDTO> search(BaseSearchInput searchInput) {
        List<DriverDO> searchResults = driverService.findAll();
        List<DriverDTO> dtos = conversionService.convertAllTo(searchResults, DriverDTO.class);
        return SearchOperationResult.success(dtos);
    }

    @Override
    @DeleteMapping("/{entityKeyName}/{entityKeyValue}")
    public void deleteEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {

        driverService.deleteEntity(entityKeyValue);
    }
}
