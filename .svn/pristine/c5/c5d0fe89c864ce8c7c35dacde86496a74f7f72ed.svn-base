package it.eng.care.domain.flow.core.converter.DataSource;

import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.entity.DataSourceDO;
import it.eng.care.domain.flow.core.entity.DriverDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class DataSourceDTOtoDataSourceDO implements Converter<DataSourceDTO, DataSourceDO> {

    @Override
    public void convert(DataSourceDTO fromObject, DataSourceDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setHostname(fromObject.getHostname());
        intoObject.setPort(fromObject.getPort());
        intoObject.setServiceName(fromObject.getServiceName());
        intoObject.setDriver(ctx.convertTo(fromObject.getDriver(), DriverDO.class));
        intoObject.setUsername(fromObject.getUsername());
        intoObject.setPassword(fromObject.getPassword());
        intoObject.setEnabled(fromObject.isEnabled());

    }

}