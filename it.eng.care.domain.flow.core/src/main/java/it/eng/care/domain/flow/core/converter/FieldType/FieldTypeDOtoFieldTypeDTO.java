package it.eng.care.domain.flow.core.converter.FieldType;

import it.eng.care.domain.flow.core.dto.FieldTypeDTO;
import it.eng.care.domain.flow.core.entity.FieldTypeDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FieldTypeDOtoFieldTypeDTO implements Converter<FieldTypeDO, FieldTypeDTO> {

    @Override
    public void convert(FieldTypeDO fromObject, FieldTypeDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());

    }

}