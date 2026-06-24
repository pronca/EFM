package it.eng.care.domain.flow.core.converter.Dashboard;

import it.eng.care.domain.flow.core.dto.DashboardDTO;
import it.eng.care.domain.flow.core.entity.DashboardDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class DashboardDTOtoDashboardDO implements Converter<DashboardDTO, DashboardDO> {

    @Override
    public void convert(DashboardDTO fromObject, DashboardDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setLabel(fromObject.getLabel());
        intoObject.setMonth(fromObject.getMonth());
        intoObject.setYear(fromObject.getYear());
        intoObject.setValue(fromObject.getValue());
        intoObject.setWidgetName(fromObject.getWidgetName());
        intoObject.setLastUpdate(fromObject.getLastUpdate());
        intoObject.setFlow(fromObject.getFlow());
        intoObject.setTooltip(fromObject.getTooltip());
        intoObject.setDay(fromObject.getDay());
    }

}
