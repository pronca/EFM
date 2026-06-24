package it.eng.care.domain.flow.core.converter.FlowConfigurationFilter;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;


public class FlowConfigurationFilterFieldValueDOtoFlowConfigurationFilterFieldValueDTO implements Converter<FlowConfigurationFilterFieldValueDO, FlowConfigurationFilterFieldValueDTO> {

    @Override
    public void convert(FlowConfigurationFilterFieldValueDO fromObject, FlowConfigurationFilterFieldValueDTO intoObject, ConversionContext ctx) {
        intoObject.setId(fromObject.getId());
        if (fromObject.getValue() != null) {
            intoObject.setSingleValue(fromObject.getValue());
        }
        //intoObject.setMultiValues();
//        List<String> multiValues;
//        Date dateFromValue;
//        Date dateToValue;
//        String numFromValue;
//        String numToValue;

    }

}
