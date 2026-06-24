package it.eng.care.domain.flow.core.converter.FlowImportExportRequest;

import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestFieldDateDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowImportRequestFieldDateDTOtoFlowImportRequestFieldDateDO implements Converter<FlowImportRequestFieldDateDTO, FlowImportRequestFieldDateDO> {

    @Override
    public void convert(FlowImportRequestFieldDateDTO fromObject, FlowImportRequestFieldDateDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setId_field(fromObject.getId_field());
        intoObject.setDate_to(fromObject.getDate_to());
        intoObject.setDate_from(fromObject.getDate_from());
        //  intoObject.setFlowImportRequest(ctx.convertTo(fromObject.getFlowImportRequest(), FlowImportRequestDO.class));
    }

}
