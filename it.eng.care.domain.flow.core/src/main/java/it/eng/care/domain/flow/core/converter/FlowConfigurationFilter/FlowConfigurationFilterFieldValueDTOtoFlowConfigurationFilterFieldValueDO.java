package it.eng.care.domain.flow.core.converter.FlowConfigurationFilter;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldValueDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldValueDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;


public class FlowConfigurationFilterFieldValueDTOtoFlowConfigurationFilterFieldValueDO implements Converter<FlowConfigurationFilterFieldValueDTO, FlowConfigurationFilterFieldValueDO> {

    @Override
    public void convert(FlowConfigurationFilterFieldValueDTO fromObject, FlowConfigurationFilterFieldValueDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
//        intoObject.setValue(fromObject.getValue());


    }

}
