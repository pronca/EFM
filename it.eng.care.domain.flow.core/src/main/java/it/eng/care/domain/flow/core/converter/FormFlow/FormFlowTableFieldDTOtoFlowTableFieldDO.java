package it.eng.care.domain.flow.core.converter.FormFlow;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FormFlowTableFieldDTOtoFlowTableFieldDO implements Converter<FormFlowTableFieldDTO, FlowTableFieldDO> {

    @Override
    public void convert(FormFlowTableFieldDTO formFlowTableFieldDTO, FlowTableFieldDO flowTableFieldDO, ConversionContext conversionContext) {

        if (formFlowTableFieldDTO.getId() != null) {
            flowTableFieldDO.setId(formFlowTableFieldDTO.getId());
        }
        flowTableFieldDO.setName(formFlowTableFieldDTO.getName().toLowerCase());
        flowTableFieldDO.setDescription(formFlowTableFieldDTO.getDescription());
        flowTableFieldDO.setDescriptionsm(formFlowTableFieldDTO.getDescriptionsm());
        flowTableFieldDO.setPosition(formFlowTableFieldDTO.getPosition());
        flowTableFieldDO.setPk(formFlowTableFieldDTO.isPk());
        if (formFlowTableFieldDTO.getFieldType().equals("String")
                || formFlowTableFieldDTO.getFieldType().equals("Integer")
                || formFlowTableFieldDTO.getFieldType().equals("1")) {
            flowTableFieldDO.setLength(Integer.parseInt(formFlowTableFieldDTO.getLength()));
        }
        else
            flowTableFieldDO.setLength(null);
        flowTableFieldDO.setMandatory(formFlowTableFieldDTO.isMandatory());
        flowTableFieldDO.setRegExp(formFlowTableFieldDTO.getRegExp());
        flowTableFieldDO.setReferenceDate(formFlowTableFieldDTO.isReferenceDate());
        flowTableFieldDO.setGroups(formFlowTableFieldDTO.getGroups());
        flowTableFieldDO.setFieldType(formFlowTableFieldDTO.getFieldType());
        flowTableFieldDO.setEnableCrypt(formFlowTableFieldDTO.isCrypto());
        flowTableFieldDO.setActive(formFlowTableFieldDTO.getActive());
        flowTableFieldDO.setPhysicalSize(formFlowTableFieldDTO.getPhysicalSize());
        flowTableFieldDO.setLength(formFlowTableFieldDTO.getLength() != null ? Integer.valueOf(formFlowTableFieldDTO.getLength()) : null);
    }
}