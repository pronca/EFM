package it.eng.care.domain.flow.core.converter.FormFlow;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class FormFlowDTOtoFlowDO implements Converter<FormFlowDTO, FlowDO> {

    @Autowired
    private LoggedUserService loggedUserService;

    @Override
    public void convert(FormFlowDTO formFlowDTO, FlowDO flowDO, ConversionContext conversionContext) {

        if (formFlowDTO.getId() != null) {
            flowDO.setId(formFlowDTO.getId());
        }
        flowDO.setName(formFlowDTO.getName());
        flowDO.setDescrb(formFlowDTO.getDescrb());
        flowDO.setDescription(formFlowDTO.getDescription());
        flowDO.setCode(formFlowDTO.getName());
        flowDO.setMonthlyDeadline(formFlowDTO.getMonthlyDeadline());
        flowDO.setPeriodicy(formFlowDTO.getPeriodicy());
        flowDO.setRemoteSend(formFlowDTO.isRemoteSend());
        flowDO.setScheduling(formFlowDTO.isScheduling());
        flowDO.setStatus(formFlowDTO.isStatus());
        flowDO.setUniqueness(formFlowDTO.isUniqueness());
        flowDO.setUserCreation(loggedUserService.getCurrentUser().getUsername());
        flowDO.setDataCreation(new Date());
        flowDO.setCommProt(formFlowDTO.getCommProt());
        flowDO.setYearlyDeadline(formFlowDTO.getYearlyDeadline());
        flowDO.setFlowType(formFlowDTO.getFlowType());
        //formFlowDTO.getVersion()
        //flowDO.setVersions();

    }
}