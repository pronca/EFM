package it.eng.care.domain.flow.core.converter.FlowTable;

import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FlowTableDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowTableDOtoFlowTableDTO implements Converter<FlowTableDO, FlowTableDTO> {

    @Override
    public void convert(FlowTableDO fromObject, FlowTableDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setDescription(fromObject.getDescription());
        intoObject.setSection(fromObject.getSection());
        intoObject.setDataSource(ctx.convertTo(fromObject.getDataSource(), DataSourceDTO.class));
        intoObject.setVersion(ctx.convertTo(fromObject.getVersionDO(), VersionDTO.class));
        intoObject.setFlow(ctx.convertTo(fromObject.getFlowDO(), FlowDTO.class));
        intoObject.setXsd(fromObject.getXsd());

    }
}