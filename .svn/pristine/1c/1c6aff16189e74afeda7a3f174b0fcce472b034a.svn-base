package it.eng.care.domain.flow.core.converter.FlowConfigurationFilter;

import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO implements Converter<FlowConfigurationFilterFieldDO, FlowConfigurationFilterFieldDTO> {

    @Override
    public void convert(FlowConfigurationFilterFieldDO fromObject, FlowConfigurationFilterFieldDTO intoObject, ConversionContext ctx) {
        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setMandatory(fromObject.isMandatory());
        intoObject.setPosition(fromObject.getPosition());
        intoObject.setRange(fromObject.isRange());
        intoObject.setFilterField(fromObject.getFilterField());
        intoObject.setFilterTable(fromObject.getFilterTable());
        intoObject.setFilterType(fromObject.getFilterType());
        intoObject.setConfigurationId(fromObject.getConfigurationFilter().getId());
    }
}
