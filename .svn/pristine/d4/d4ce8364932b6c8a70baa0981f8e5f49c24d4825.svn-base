package it.eng.care.domain.flow.core.converter.Flow;

import java.util.HashSet;
import java.util.Set;

import it.eng.care.domain.flow.core.converter.ProfiloFlussi.ProfiloFlussiDOtoProfiloFlussiDTO;
import it.eng.care.domain.flow.core.converter.Version.VersionDOtoVersionDTO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.ProfiloFlussiDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowDOtoFlowDTO implements Converter<FlowDO, FlowDTO> {

    @Override
    public void convert(FlowDO fromObject, FlowDTO intoObject, ConversionContext ctx) {

    	intoObject = bare(fromObject, intoObject);
        
        Set<VersionDTO> versions = new HashSet<>();
        for (FlowVersionDO objectDO : fromObject.getVersions()) {
            //versionDTO = ctx.convertTo(objectDO.getVersion(), VersionDTO.class);
        	
        	VersionDTO versionDTO = VersionDOtoVersionDTO.bare(objectDO.getVersion(), new VersionDTO());        	
        	Set<FlowVersionDO> flowsDO = objectDO.getVersion().getFlows();
        	if(flowsDO != null) {
        		Set<FlowDTO> flows = new HashSet<>();
        		for (FlowVersionDO vflow : flowsDO) {
        			FlowDTO flowDTO = bare(vflow.getFlow(), new FlowDTO());
        			flows.add(flowDTO);
				}
        		versionDTO.setFlows(flows);
        	}
        	
        	
            versions.add(versionDTO);
        }
        intoObject.setVersions(versions);
        
        Set<ProfiloFlussiDTO> profiloFlussi = new HashSet<>();
        for (ProfiloFlussiDO objectDO : fromObject.getProfiloFlussi()) {
        	ProfiloFlussiDTO profiloFlussiDTO = ProfiloFlussiDOtoProfiloFlussiDTO.bare(objectDO, new ProfiloFlussiDTO());
        	profiloFlussi.add(profiloFlussiDTO);
        }
        intoObject.setProfiloFlussi(profiloFlussi);
    }
    
    public static FlowDTO bare(FlowDO fromObject, FlowDTO intoObject) {
    	intoObject.setId(fromObject.getId());
        intoObject.setCode(fromObject.getCode());
        intoObject.setName(fromObject.getName());
        intoObject.setDescrb(fromObject.getDescrb());
        intoObject.setDescription(fromObject.getDescription());
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
        return intoObject;
    }

}