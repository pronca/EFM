package it.eng.care.domain.flow.core.converter.FlowTableField;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;


public class FlowTableFieldDOtoFormFlowTableFieldDTO implements Converter<FlowTableFieldDO, FormFlowTableFieldDTO> {


    @Override
    public void convert(FlowTableFieldDO flowTableFieldDO, FormFlowTableFieldDTO formFlowTableFieldDTO, ConversionContext conversionContext) {


        formFlowTableFieldDTO.setId(flowTableFieldDO.getId());
        formFlowTableFieldDTO.setName(flowTableFieldDO.getName());
        formFlowTableFieldDTO.setDescription(flowTableFieldDO.getDescription());
        formFlowTableFieldDTO.setDescriptionsm(flowTableFieldDO.getDescriptionsm());
        formFlowTableFieldDTO.setFieldType(flowTableFieldDO.getFieldType());
        formFlowTableFieldDTO.setPosition(flowTableFieldDO.getPosition());
        formFlowTableFieldDTO.setPk(flowTableFieldDO.getPk());
        if (flowTableFieldDO.getLength() != null)
            formFlowTableFieldDTO.setLength(Integer.toString(flowTableFieldDO.getLength()));
        formFlowTableFieldDTO.setMandatory(flowTableFieldDO.getMandatory());
        formFlowTableFieldDTO.setRegExp(flowTableFieldDO.getRegExp());
        formFlowTableFieldDTO.setReferenceDate(flowTableFieldDO.getReferenceDate());
        formFlowTableFieldDTO.setGroups(flowTableFieldDO.getGroups());
        formFlowTableFieldDTO.setCrypto(flowTableFieldDO.getEnableCrypt());
        formFlowTableFieldDTO.setActive(flowTableFieldDO.getActive());
        formFlowTableFieldDTO.setPhysicalSize(flowTableFieldDO.getPhysicalSize());
    }
}