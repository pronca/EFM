package it.eng.care.domain.flow.core.converter.FlowConfigurationFilter;

import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.VersionDAO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterDTO;
import it.eng.care.domain.flow.core.dto.FlowConfigurationFilter.FlowConfigurationFilterFieldDTO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterDO;
import it.eng.care.domain.flow.core.entity.FlowConfigurationFilterFieldDO;
import it.eng.care.domain.flow.core.enumeration.FieldType;
import it.eng.care.domain.flow.core.service.FlowConfigurationFilterFieldService;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FlowConfigurationFilterDOtoFlowConfigurationFilterDTO implements Converter<FlowConfigurationFilterDO, FlowConfigurationFilterDTO> {

    @Autowired
    private FlowDAO flowDAO;
    @Autowired
    private VersionDAO versionDAO;
    @Autowired
    private FlowConfigurationFilterFieldService flowConfigurationFilterFieldService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private FlowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO flowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO;


    @Override
    public void convert(FlowConfigurationFilterDO fromObject, FlowConfigurationFilterDTO intoObject, ConversionContext ctx) {

        List<List<FlowConfigurationFilterFieldDTO>> flowConfigurationFilterFieldDOSS = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> dataFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> textFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> numericFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> comboFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> radioFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> multiFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> campiFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> lookFilters = new ArrayList<>();
        List<FlowConfigurationFilterFieldDTO> chipsFilters = new ArrayList<>();
        conversionService.registerConverter(FlowConfigurationFilterFieldDO.class, FlowConfigurationFilterFieldDTO.class, flowConfigurationFilterFieldDOtoFlowConfigurationFilterFieldDTO);
        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setFlow(ctx.convertTo(flowDAO.findById(fromObject.getFlow()).orElse(null), FlowDTO.class));
        intoObject.setVersion(ctx.convertTo(versionDAO.findById(fromObject.getVersion()).orElse(null), VersionDTO.class));
        intoObject.setType(fromObject.getType());
        for (FlowConfigurationFilterFieldDO flowConfigurationFilterFieldDOS : fromObject.getFilterFields()) {
            FlowConfigurationFilterFieldDTO flowConfigurationFilterFieldDTO = null;
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.CHIPS.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                chipsFilters.add(flowConfigurationFilterFieldDTO);
            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.TEXT.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                textFilters.add(flowConfigurationFilterFieldDTO);
            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.CAMPI.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                campiFilters.add(flowConfigurationFilterFieldDTO);

            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.NUMERIC.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                numericFilters.add(flowConfigurationFilterFieldDTO);

            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.DATA.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                dataFilters.add(flowConfigurationFilterFieldDTO);

            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.RADIO.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                radioFilters.add(flowConfigurationFilterFieldDTO);

            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.MULTISELECT.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                multiFilters.add(flowConfigurationFilterFieldDTO);

            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.COMBOBOX.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                comboFilters.add(flowConfigurationFilterFieldDTO);

            }
            if (flowConfigurationFilterFieldDOS.getFilterType().equals(FieldType.LOOKUP.getType())) {
                flowConfigurationFilterFieldDTO =
                        ctx.convertTo(flowConfigurationFilterFieldDOS, FlowConfigurationFilterFieldDTO.class);
                lookFilters.add(flowConfigurationFilterFieldDTO);

            }
        }
        if (textFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(textFilters);
        if (dataFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(dataFilters);
        if (numericFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(numericFilters);
        if (comboFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(comboFilters);
        if (radioFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(radioFilters);
        if (multiFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(multiFilters);
        if (campiFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(campiFilters);
        if (lookFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(lookFilters);
        if (chipsFilters.size() != 0)
            flowConfigurationFilterFieldDOSS.add(chipsFilters);

        intoObject.setFields(flowConfigurationFilterFieldDOSS);

    }

}
