package it.eng.care.domain.flow.core.converter.Flow;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowDTOtoFormFlowDTO implements Converter<FlowDTO, FormFlowDTO> {

    @Override
    public void convert(FlowDTO flowDTO, FormFlowDTO formFlowDTO, ConversionContext conversionContext) {

        formFlowDTO.setId(flowDTO.getId());
        formFlowDTO.setName(flowDTO.getName());
        formFlowDTO.setDescription(flowDTO.getDescription());
        formFlowDTO.setDescrb(flowDTO.getDescrb());
        formFlowDTO.setCode(flowDTO.getCode());
        formFlowDTO.setStatus(flowDTO.getStatus());
        formFlowDTO.setUniqueness(flowDTO.getUniqueness());
        formFlowDTO.setPeriodicy(flowDTO.getPeriodicy());
        formFlowDTO.setRemoteSend(flowDTO.getRemoteSend());
        formFlowDTO.setScheduling(flowDTO.getScheduling());
        formFlowDTO.setMonthlyDeadline(flowDTO.getMonthlyDeadline());
        formFlowDTO.setCommProt(flowDTO.getCommProt());
        formFlowDTO.setYearlyDeadline(flowDTO.getYearlyDeadline());
        formFlowDTO.setFlowType(flowDTO.getFlowType());
        // TODO Manca versione
        // formFlowDTO.setVersion("prova");
    }
}