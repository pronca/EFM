package it.eng.care.domain.flow.core.converter.ExtractionFilter;

import it.eng.care.domain.flow.core.dto.ExtractionFilter.ExtractionFilterDTO;
import it.eng.care.domain.flow.core.dto.ExtractionFilter.Field;
import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

import java.util.ArrayList;
import java.util.List;

public class FlowImportRequestDTOtoExtractionDTO implements Converter<FlowImportRequestDTO, ExtractionFilterDTO> {

    @Override
    public void convert(FlowImportRequestDTO fromObject, ExtractionFilterDTO intoObject, ConversionContext ctx) {

        intoObject.setExtractionId(fromObject.getId());
        intoObject.setFormFlow(ctx.convertTo(fromObject.getFlow(), FormFlowDTO.class));
        intoObject.setStatus(true);
        List<Field> listDataFields = new ArrayList<>();
        for (FlowImportRequestFieldDateDTO flowImportRequestFieldDateDTO : fromObject.getDateFields()) {
            listDataFields.add(ctx.convertTo(flowImportRequestFieldDateDTO, Field.class));
        }
        intoObject.setFields(listDataFields);
    }

}
