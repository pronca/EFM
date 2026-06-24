package it.eng.care.domain.flow.core.converter.FlowImportExportRequest;

import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestFieldDateDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowImportRequestFieldDateDOtoFlowImportRequestFieldDateDTO implements Converter<FlowImportRequestFieldDateDO, FlowImportRequestFieldDateDTO> {

    @Override
    public void convert(FlowImportRequestFieldDateDO fromObject, FlowImportRequestFieldDateDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setId_field(fromObject.getId_field());
        intoObject.setDate_to(fromObject.getDate_to());
        intoObject.setDate_from(fromObject.getDate_from());
        intoObject.setFlowImportRequest(ctx.convertTo(fromObject.getFlowImportRequest(), FlowImportRequestDTO.class));

    }

}
