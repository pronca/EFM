package it.eng.care.domain.flow.core.converter.FlowTable;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;


public class FlowTableDOtoFormFlowTableDTO implements Converter<FlowTableDO, FormFlowTableDTO> {


    @Override
    public void convert(FlowTableDO flowTableDO, FormFlowTableDTO formFlowTableDTO, ConversionContext conversionContext) {

        formFlowTableDTO.setId(flowTableDO.getId());
        formFlowTableDTO.setName(flowTableDO.getName());
        formFlowTableDTO.setDescription(flowTableDO.getDescription());
        formFlowTableDTO.setSection(flowTableDO.getSection());
        formFlowTableDTO.setRequired(flowTableDO.getRequired());
        //Del datasource restituisco id
        if (flowTableDO.getDataSource() != null) {
            formFlowTableDTO.setDatasource(flowTableDO.getDataSource().getId());
        }
        formFlowTableDTO.setXsd(flowTableDO.getXsd());

    }
}