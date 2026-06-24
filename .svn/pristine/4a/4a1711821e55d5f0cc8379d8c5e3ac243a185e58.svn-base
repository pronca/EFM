package it.eng.care.domain.flow.core.converter.ProfiloFlussi;

import it.eng.care.domain.flow.core.dto.ProfiloFlussiDTO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class ProfiloFlussiDOtoProfiloFlussiDTO implements Converter<ProfiloFlussiDO, ProfiloFlussiDTO> {

    @Override
    public void convert(ProfiloFlussiDO fromObject, ProfiloFlussiDTO intoObject, ConversionContext ctx) {

        bare(fromObject, intoObject);
        
    }
    
    public static ProfiloFlussiDTO bare(ProfiloFlussiDO fromObject, ProfiloFlussiDTO intoObject) {
    	intoObject.setRole(fromObject.getRole());
        intoObject.setOrganization(fromObject.getOrganization());
        intoObject.setSite(fromObject.getSite());
        intoObject.setConsolidamento(fromObject.getConsolidamento());
    	return intoObject;
    }

}