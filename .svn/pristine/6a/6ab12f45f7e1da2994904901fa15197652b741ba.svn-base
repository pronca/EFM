package it.eng.care.domain.flow.core.converter.FlowForeignKey;

import it.eng.care.domain.flow.core.dto.FlowForeignKeyDTO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowForeignKeyDOtoFlowForeignKeyDTO implements Converter<FlowForeignKeyDO, FlowForeignKeyDTO> {

    @Override
    public void convert(FlowForeignKeyDO flowForeignKeyDO, FlowForeignKeyDTO flowForeignKeyDTO, ConversionContext ctx) {

        flowForeignKeyDTO.setId(flowForeignKeyDO.getId());
        flowForeignKeyDTO.setIdTable(flowForeignKeyDO.getIdTable());
        flowForeignKeyDTO.setIdFieldTable(flowForeignKeyDO.getIdFieldTable());
        flowForeignKeyDTO.setIdTableReferenced(flowForeignKeyDO.getIdTableReferenced());
        flowForeignKeyDTO.setIdFieldTableReferenced(flowForeignKeyDO.getIdFieldTableReferenced());
        flowForeignKeyDTO.setJsonField(flowForeignKeyDO.getJsonField());
        flowForeignKeyDTO.setJsonFieldType(flowForeignKeyDO.getJsonFieldType());


    }

}