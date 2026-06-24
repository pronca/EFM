package it.eng.care.domain.flow.core.converter.DataSource;

import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.dto.DriverDTO;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class DataSourceDOtoDataSourceDTO implements Converter<DataSourceDO, DataSourceDTO> {

    @Override
    public void convert(DataSourceDO fromObject, DataSourceDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setHostname(fromObject.getHostname());
        intoObject.setPort(fromObject.getPort());
        intoObject.setServiceName(fromObject.getServiceName());
        intoObject.setDriver(ctx.convertTo(fromObject.getDriver(), DriverDTO.class));
        intoObject.setUsername(fromObject.getUsername());
        intoObject.setPassword(fromObject.getPassword());
        intoObject.setEnabled(fromObject.isEnabled());
    }

}