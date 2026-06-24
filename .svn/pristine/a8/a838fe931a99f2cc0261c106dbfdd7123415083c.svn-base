package it.eng.care.domain.flow.core.converter.Flow;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.ProfiloFlussiDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

import java.util.HashSet;
import java.util.Set;

public class FlowDTOtoFlowDO implements Converter<FlowDTO, FlowDO> {

    @Override
    public void convert(FlowDTO fromObject, FlowDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setCode(fromObject.getCode());
        intoObject.setName(fromObject.getName());
        intoObject.setDescription(fromObject.getDescription());
        intoObject.setDescrb(fromObject.getDescrb());
        intoObject.setStatus(fromObject.getStatus());
        intoObject.setRemoteSend(fromObject.getRemoteSend());
        intoObject.setUniqueness(fromObject.getUniqueness());
        intoObject.setScheduling(fromObject.getScheduling());
        intoObject.setPeriodicy(fromObject.getPeriodicy());
        intoObject.setMonthlyDeadline(fromObject.getMonthlyDeadline());
        intoObject.setUserCreation(fromObject.getUserCreation());
        intoObject.setDataCreation(fromObject.getDataCreation());
        intoObject.setCommProt(fromObject.getCommProt());
        intoObject.setYearlyDeadline(fromObject.getYearlyDeadline());
        intoObject.setFlowType(fromObject.getFlowType());
        Set<FlowVersionDO> versions = new HashSet<>();
        VersionDO versionDO;
        for (VersionDTO objectDTO : fromObject.getVersions()) {
            versionDO = ctx.convertTo(objectDTO, VersionDO.class);
            versions.add(new FlowVersionDO(intoObject, versionDO));
        }
        intoObject.setVersions(versions);
        
        Set<ProfiloFlussiDO> profiloFlussi = new HashSet<>();
        ProfiloFlussiDO profiloFlussiDO = new ProfiloFlussiDO();
        for (ProfiloFlussiDTO objectDTO : fromObject.getProfiloFlussi()) {
        	profiloFlussiDO.setSite(objectDTO.getSite());
        	profiloFlussiDO.setOrganization(objectDTO.getOrganization());
        	profiloFlussiDO.setRole(objectDTO.getRole());
        	profiloFlussiDO.setConsolidamento(objectDTO.getConsolidamento());
        	profiloFlussi.add(profiloFlussiDO);
        }
        intoObject.setProfiloFlussi(profiloFlussi);
    }

}