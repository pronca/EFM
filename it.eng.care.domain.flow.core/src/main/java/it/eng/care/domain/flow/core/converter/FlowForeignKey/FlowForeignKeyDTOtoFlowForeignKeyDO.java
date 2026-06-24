package it.eng.care.domain.flow.core.converter.FlowForeignKey;

import it.eng.care.domain.flow.core.dto.FlowForeignKeyDTO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowForeignKeyDTOtoFlowForeignKeyDO implements Converter<FlowForeignKeyDTO, FlowForeignKeyDO> {

    @Override
    public void convert(FlowForeignKeyDTO flowForeignKeyDTO, FlowForeignKeyDO flowForeignKeyDO, ConversionContext ctx) {

        flowForeignKeyDO.setId(flowForeignKeyDTO.getId());
        flowForeignKeyDO.setIdTable(flowForeignKeyDTO.getIdTable());
        flowForeignKeyDO.setIdFieldTable(flowForeignKeyDTO.getIdFieldTable());
        flowForeignKeyDO.setIdTableReferenced(flowForeignKeyDTO.getIdTableReferenced());
        flowForeignKeyDO.setIdFieldTableReferenced(flowForeignKeyDTO.getIdFieldTableReferenced());

    }

}