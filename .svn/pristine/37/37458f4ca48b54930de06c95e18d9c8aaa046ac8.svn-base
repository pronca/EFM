package it.eng.care.domain.flow.core.converter.FlowConfigurationFilter;

import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.VersionDAO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.service.FlowConfigurationFilterFieldService;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlowConfigurationFilterDTOtoFlowConfigurationFilterDO implements Converter<FlowConfigurationFilterDTO, FlowConfigurationFilterDO> {

    @Autowired
    private FlowDAO flowDAO;
    @Autowired
    private VersionDAO versionDAO;

    @Autowired
    private FlowConfigurationFilterFieldService flowConfigurationFilterFieldService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private FlowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO flowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO;


    @Override
    public void convert(FlowConfigurationFilterDTO fromObject, FlowConfigurationFilterDO intoObject, ConversionContext ctx) {

        conversionService.registerConverter(FlowConfigurationFilterFieldDTO.class, FlowConfigurationFilterFieldDO.class, flowConfigurationFilterFieldDTOtoFlowConfigurationFilterFieldDO);
        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setFlow(fromObject.getFlow().getId());
        intoObject.setVersion(fromObject.getVersion().getId());
        intoObject.setType(fromObject.getType());
        Set<FlowConfigurationFilterFieldDO> list = new HashSet<>();
        for (List<FlowConfigurationFilterFieldDTO> flowConfigurationFilterFieldDTOlist : fromObject.getFields()) {
            for (FlowConfigurationFilterFieldDTO flowConfigurationFilterFieldDTO : flowConfigurationFilterFieldDTOlist) {
                list.add(ctx.convertTo(flowConfigurationFilterFieldDTO, FlowConfigurationFilterFieldDO.class));

            }
        }
        intoObject.setFilterFields(list);
    }
}
