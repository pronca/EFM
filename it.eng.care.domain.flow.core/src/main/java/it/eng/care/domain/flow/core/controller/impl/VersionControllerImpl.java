package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.VersionController;
import it.eng.care.domain.flow.core.converter.Version.VersionDOtoVersionDTO;
import it.eng.care.domain.flow.core.converter.Version.VersionDTOtoVersionDO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.core.service.VersionService;
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
@RequestMapping("/fm/VersionDTO")
public class VersionControllerImpl implements VersionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionControllerImpl.class);

    @Autowired
    private VersionService versionService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private VersionDOtoVersionDTO versionDOtoVersionDTOConvert;

    @Autowired
    private VersionDTOtoVersionDO versionDOTtoVersionDOConvert;


    public VersionControllerImpl() {
        LOGGER.info("VersionControllerImpl");
    }

    @PostConstruct
    public void post() {
        LOGGER.info("DriverControllerImpl.post");
        conversionService.registerConverter(VersionDO.class, VersionDTO.class, versionDOtoVersionDTOConvert);
        conversionService.registerConverter(VersionDTO.class, VersionDO.class, versionDOTtoVersionDOConvert);

    }

    @Override
    @PostMapping
    public SaveOperationResult<VersionDTO> save(@RequestBody VersionDTO inputDTO) {

        VersionDO loValue = conversionService.convertTo(inputDTO, VersionDO.class);

        if (loValue.getId() == null) {
            loValue = versionService.createEntity(loValue);
        } else {
            loValue = versionService.updateEntity(loValue);
        }


        VersionDTO outputDTO = conversionService.convertTo(loValue, VersionDTO.class);
        return SaveOperationResult.success(outputDTO);
    }

    @Override
    @GetMapping("/{entityKeyName}/{entityKeyValue}")
    public OperationResult<VersionDTO> getEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {
        VersionDO loValue = versionService.retrieveOne(entityKeyValue);
        VersionDTO loDto = conversionService.convertTo(loValue, VersionDTO.class);
        return OperationResult.success(loDto);
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<VersionDTO> search(@RequestBody BaseSearchInput searchInput) {

        Pair<List<VersionDO>, SearchInfo> searchResultsVersion = versionService.retrieveAllFiltered(searchInput);
        List<VersionDTO> dtosVersion = conversionService.convertAllTo(searchResultsVersion.getFirst(), VersionDTO.class);

        return SearchOperationResult.success(dtosVersion, searchResultsVersion.getSecond());

    }

    @Override
    @DeleteMapping("/{entityKeyName}/{entityKeyValue}")
    public void deleteEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {
        versionService.deleteEntity(entityKeyValue);
    }
}
