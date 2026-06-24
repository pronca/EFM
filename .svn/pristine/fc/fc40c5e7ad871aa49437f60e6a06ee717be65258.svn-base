package it.eng.care.domain.flow.core.converter.Driver;

import it.eng.care.domain.flow.core.dto.DriverDTO;
import it.eng.care.domain.flow.core.entity.DriverDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class DriverDTOtoDriverDO implements Converter<DriverDTO, DriverDO> {

    @Override
    public void convert(DriverDTO fromObject, DriverDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setDescription(fromObject.getDescription());
    }

}