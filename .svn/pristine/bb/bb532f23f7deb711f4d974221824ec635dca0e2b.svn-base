package it.eng.care.domain.flow.core.converter.ProfiloFlussi;

import it.eng.care.domain.flow.core.dto.ProfiloFlussiDTO;
import it.eng.care.domain.flow.core.entity.ProfiloFlussiDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class ProfiloFlussiDTOtoProfiloFlussiDO implements Converter<ProfiloFlussiDTO, ProfiloFlussiDO> {

    @Override
    public void convert(ProfiloFlussiDTO fromObject, ProfiloFlussiDO intoObject, ConversionContext ctx) {

        intoObject.setRole(fromObject.getRole());
        intoObject.setOrganization(fromObject.getOrganization());
        intoObject.setSite(fromObject.getSite());
        intoObject.setConsolidamento(fromObject.getConsolidamento());

    }

}