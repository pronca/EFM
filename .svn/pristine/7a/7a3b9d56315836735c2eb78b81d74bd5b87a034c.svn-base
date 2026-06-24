package it.eng.care.domain.flow.core.converter.FlowTableField;

import it.eng.care.domain.flow.core.dto.FlowTableDTO;
import it.eng.care.domain.flow.core.dto.FlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowTableFieldDOtoFlowTableFieldDTO implements Converter<FlowTableFieldDO, FlowTableFieldDTO> {

    @Override
    public void convert(FlowTableFieldDO fromObject, FlowTableFieldDTO intoObject, ConversionContext ctx) {


        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setDescription(fromObject.getDescription());
        intoObject.setDescriptionsm(fromObject.getDescriptionsm());
        intoObject.setFieldType(fromObject.getFieldType());
        intoObject.setPosition(fromObject.getPosition());
        intoObject.setPk(fromObject.getPk());
        intoObject.setFlowTable(ctx.convertTo(fromObject.getFlowTable(), FlowTableDTO.class));
        intoObject.setFlowTableFieldId(fromObject.getFlowTableFieldId());
        intoObject.setLength(fromObject.getLength());
        intoObject.setMandatory(fromObject.getMandatory());
        intoObject.setRegExp(fromObject.getRegExp());
        intoObject.setReferenceDate(fromObject.getReferenceDate());
        intoObject.setGroups(fromObject.getGroups());
        intoObject.setCrypto(fromObject.getEnableCrypt());
        intoObject.setActive(fromObject.getActive());
        intoObject.setPhysicalSize(fromObject.getPhysicalSize());
    }

}