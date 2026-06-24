package it.eng.care.domain.flow.core.converter.FlowForeignKey;

import it.eng.care.domain.flow.core.dto.FlowForeignKeyDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FormFlowTableLinkDTOtoFlowForeignKeyDTO implements Converter<FormFlowTableLinkDTO, FlowForeignKeyDTO> {

    @Override
    public void convert(FormFlowTableLinkDTO formFlowTableLinkDTO, FlowForeignKeyDTO flowForeignKeyDTO, ConversionContext ctx) {

        flowForeignKeyDTO.setId(flowForeignKeyDTO.getId());
        flowForeignKeyDTO.setIdTable(flowForeignKeyDTO.getIdTable());
        flowForeignKeyDTO.setIdFieldTable(flowForeignKeyDTO.getIdFieldTable());
        flowForeignKeyDTO.setIdTableReferenced(flowForeignKeyDTO.getIdTableReferenced());
        flowForeignKeyDTO.setIdFieldTableReferenced(flowForeignKeyDTO.getIdFieldTableReferenced());

    }

}