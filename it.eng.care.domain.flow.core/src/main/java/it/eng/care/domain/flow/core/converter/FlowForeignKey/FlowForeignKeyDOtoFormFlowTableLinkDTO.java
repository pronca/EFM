package it.eng.care.domain.flow.core.converter.FlowForeignKey;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowForeignKeyDOtoFormFlowTableLinkDTO implements Converter<FlowForeignKeyDO, FormFlowTableLinkDTO> {

    @Override
    public void convert(FlowForeignKeyDO flowForeignKeyDO,FormFlowTableLinkDTO formFlowTableLinkDTO, ConversionContext ctx) {

    	formFlowTableLinkDTO.setId(flowForeignKeyDO.getId());
    	formFlowTableLinkDTO.setIdTable(flowForeignKeyDO.getIdTable().getId());
    	formFlowTableLinkDTO.setIdFieldTable(flowForeignKeyDO.getIdFieldTable().getId());
    	formFlowTableLinkDTO.setIdTableReferenced(flowForeignKeyDO.getIdTableReferenced().getId());
    	formFlowTableLinkDTO.setIdFieldTableReferenced(flowForeignKeyDO.getIdFieldTableReferenced().getId());
    	formFlowTableLinkDTO.setJsonField(flowForeignKeyDO.getJsonField());
    	formFlowTableLinkDTO.setJsonFieldType(flowForeignKeyDO.getJsonFieldType());

    }

}