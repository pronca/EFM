package it.eng.care.domain.flow.core.converter.Version;

import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFlowDTO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

import java.util.HashSet;
import java.util.Set;

public class VersionDOtoVersionDTO implements Converter<VersionDO, VersionDTO> {

    @Override
    public void convert(VersionDO fromObject, VersionDTO intoObject, ConversionContext ctx) {

        bare(fromObject, intoObject);
        
        Set<FlowDTO> flows = new HashSet<>();
        FlowDTO flowDTO;

        for (FlowVersionDO objectDO : fromObject.getFlows()) {
        	
        	flowDTO = FlowDOtoFlowDTO.bare(objectDO.getFlow(), new FlowDTO());
        	
        	if(objectDO.getFlow() != null && objectDO.getFlow().getVersions() != null) {
        		Set<FlowVersionDO> flowversionsDO = objectDO.getFlow().getVersions();
            	if(flowversionsDO != null) {
            		Set<VersionDTO> versions = new HashSet<>();
            		for (FlowVersionDO vflow : flowversionsDO) {
            			VersionDTO versionDTO = bare(vflow.getVersion(), new VersionDTO());
            			versions.add(versionDTO);
    				}
            		flowDTO.setVersions(versions);
            	}
        	}
        	
            //flowDTO = ctx.convertTo(objectDO.getFlow(), FlowDTO.class);
            flows.add(flowDTO);
        }
        intoObject.setFlows(flows);
        // intoObject.setFlowTables(fromObject.getFlowTables());

    }
    
    public static VersionDTO bare(VersionDO fromObject, VersionDTO intoObject) {
    	intoObject.setId(fromObject.getId());
        intoObject.setVersion(fromObject.getVersion());
        intoObject.setName(fromObject.getVersion());
        intoObject.setDescription(fromObject.getDescription());
        intoObject.setStartDate(fromObject.getStartDate());
        intoObject.setEndDate(fromObject.getEndDate());
        intoObject.setCreationDate(fromObject.getCreationDate());
    	return intoObject;
    }

}