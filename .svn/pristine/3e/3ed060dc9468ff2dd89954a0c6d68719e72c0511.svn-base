package it.eng.care.domain.flow.core.converter.FieldType;

import it.eng.care.domain.flow.core.dto.FieldTypeDTO;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FieldTypeDTOtoFieldTypeDO implements Converter<FieldTypeDTO, FieldTypeDO> {

    @Override
    public void convert(FieldTypeDTO fromObject, FieldTypeDO intoObject, ConversionContext ctx) {
        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
    }
}