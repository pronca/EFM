package it.eng.care.domain.flow.core.converter.FlowRegionUnion;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FlowRegionUnionDTO;
import it.eng.care.domain.flow.core.entity.FlowRegionUnionDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowRegionUnionDOtoFlowRegionUnionDTO implements Converter<FlowRegionUnionDO, FlowRegionUnionDTO> {

    @Override
    public void convert(FlowRegionUnionDO fromObject, FlowRegionUnionDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setFlowLocal(ctx.convertTo(fromObject.getFlowLocal(), FlowDTO.class));
        intoObject.setFlowRegion(ctx.convertTo(fromObject.getFlowRegion(), FlowDTO.class));
    }

}