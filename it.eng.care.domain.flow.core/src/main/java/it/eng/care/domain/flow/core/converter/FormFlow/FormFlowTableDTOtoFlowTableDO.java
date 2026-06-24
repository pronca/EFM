package it.eng.care.domain.flow.core.converter.FormFlow;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.service.DataSourceService;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

public class FormFlowTableDTOtoFlowTableDO implements Converter<FormFlowTableDTO, FlowTableDO> {

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public void convert(FormFlowTableDTO formFlowTableDTO, FlowTableDO flowTableDO, ConversionContext conversionContext) {

        if (formFlowTableDTO.getId() != null) {
            flowTableDO.setId(formFlowTableDTO.getId());
        }
        flowTableDO.setName(formFlowTableDTO.getName().toLowerCase());
        flowTableDO.setDescription(formFlowTableDTO.getDescription());
        flowTableDO.setSection(formFlowTableDTO.getSection());
        flowTableDO.setRequired(formFlowTableDTO.getRequired());
        DataSourceDO dataSourceDO = dataSourceService.retrieveOne(formFlowTableDTO.getDatasource());
        flowTableDO.setDataSource(dataSourceDO);
        flowTableDO.setXsd(formFlowTableDTO.getXsd());

    }
}