package it.eng.care.domain.flow.core.controller.impl;

import it.eng.care.domain.flow.core.controller.DataSourceController;
import it.eng.care.domain.flow.core.converter.DataSource.DataSourceDOtoDataSourceDTO;
import it.eng.care.domain.flow.core.converter.DataSource.DataSourceDTOtoDataSourceDO;
import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.domain.flow.core.service.DataSourceService;
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
@RequestMapping("/fm/DataSourceDTO")
public class DataSourceControllerImpl implements DataSourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceControllerImpl.class);

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private DataSourceDOtoDataSourceDTO dataSourceDOtoDataSourceDTO;

    @Autowired
    private DataSourceDTOtoDataSourceDO dataSourceDTOtoDataSourceDO;


    public DataSourceControllerImpl() {
        LOGGER.info("DataSourceControllerImpl");
    }

    @PostConstruct
    public void post() {
        LOGGER.info("DataSourceControllerImpl.post");
        conversionService.registerConverter(DataSourceDO.class, DataSourceDTO.class, dataSourceDOtoDataSourceDTO);
        conversionService.registerConverter(DataSourceDTO.class, DataSourceDO.class, dataSourceDTOtoDataSourceDO);
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<DataSourceDTO> search(@RequestBody BaseSearchInput searchInput) {

        Pair<List<DataSourceDO>, SearchInfo> searchResults = dataSourceService.retrieveAllFiltered(searchInput);
        List<DataSourceDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), DataSourceDTO.class);

        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    @GetMapping("/{entityKeyName}/{entityKeyValue}")
    public OperationResult<DataSourceDTO> getEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {


        LOGGER.info("{} , {}", entityKeyName, entityKeyValue);

        DataSourceDO loValue = dataSourceService.retrieveOne(entityKeyValue);

        DataSourceDTO loDto = conversionService.convertTo(loValue, DataSourceDTO.class);
        return OperationResult.success(loDto);
    }

    @Override
    @PostMapping
    public SaveOperationResult<DataSourceDTO> save(@RequestBody DataSourceDTO inputDTO) {

        DataSourceDO loValue = conversionService.convertTo(inputDTO, DataSourceDO.class);

        if (loValue.getId() == null) {
            loValue = dataSourceService.createEntity(loValue);
        } else {
            loValue = dataSourceService.updateEntity(loValue);
        }

        DataSourceDTO outputDTO = conversionService.convertTo(loValue, DataSourceDTO.class);
        return SaveOperationResult.success(outputDTO);
    }

    @Override
    @DeleteMapping("/{entityKeyName}/{entityKeyValue}")
    public void deleteEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {


        LOGGER.info("{} , {}", entityKeyName, entityKeyValue);

        dataSourceService.deleteEntity(entityKeyValue);

    }
}
