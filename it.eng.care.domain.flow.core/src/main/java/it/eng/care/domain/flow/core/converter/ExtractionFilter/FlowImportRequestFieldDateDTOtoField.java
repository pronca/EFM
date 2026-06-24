package it.eng.care.domain.flow.core.converter.ExtractionFilter;

import it.eng.care.domain.flow.core.dto.ExtractionFilter.Field;
import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowImportRequestFieldDateDTOtoField implements Converter<FlowImportRequestFieldDateDTO, Field> {

    @Override
    public void convert(FlowImportRequestFieldDateDTO fromObject, Field intoObject, ConversionContext ctx) {

        intoObject.setFieldId(fromObject.getId_field());
        intoObject.setTo(fromObject.getDate_to());
        intoObject.setForm(fromObject.getDate_from());

    }

}
