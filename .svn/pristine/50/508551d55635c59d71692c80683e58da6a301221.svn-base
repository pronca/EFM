package it.eng.care.domain.flow.core.converter.Flow;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowDOtoFormFlowDTO implements Converter<FlowDO, FormFlowDTO> {

    @Override
    public void convert(FlowDO flowDO, FormFlowDTO formFlowDTO, ConversionContext conversionContext) {

        formFlowDTO.setId(flowDO.getId());
        formFlowDTO.setName(flowDO.getName());
        formFlowDTO.setDescription(flowDO.getDescription());
        formFlowDTO.setDescrb(flowDO.getDescrb());
        formFlowDTO.setCode(flowDO.getCode());
        formFlowDTO.setStatus(flowDO.getStatus());
        formFlowDTO.setUniqueness(flowDO.getUniqueness());
        formFlowDTO.setPeriodicy(flowDO.getPeriodicy());
        formFlowDTO.setRemoteSend(flowDO.getRemoteSend());
        formFlowDTO.setScheduling(flowDO.getScheduling());
        formFlowDTO.setMonthlyDeadline(flowDO.getMonthlyDeadline());
        formFlowDTO.setCommProt(flowDO.getCommProt());
        formFlowDTO.setYearlyDeadline(flowDO.getYearlyDeadline());
        formFlowDTO.setFlowType(flowDO.getFlowType());
        // TODO Manca versione
        // formFlowDTO.setVersion("prova");
    }
}