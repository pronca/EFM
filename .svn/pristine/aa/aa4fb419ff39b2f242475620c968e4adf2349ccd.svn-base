package it.eng.care.domain.flow.core.converter.FlowDrg;

import it.eng.care.domain.flow.core.dto.FlowDrgDTO;
import it.eng.care.domain.flow.core.dto.FlowExportRequestDTO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowDrgDOtoFlowDrgDTO implements Converter<FlowDrgDO, FlowDrgDTO> {
	
    @Override
    public void convert(FlowDrgDO fromObject, FlowDrgDTO intoObject, ConversionContext ctx) {
    	    	
    	intoObject.setExtraction(ctx.convertTo(fromObject.getExtraction(), FlowExportRequestDTO.class));
        intoObject.setId(fromObject.getId());
        intoObject.setSendDate(fromObject.getSendDate());
        intoObject.setError(fromObject.getError());
        intoObject.setReturnDate(fromObject.getReturnDate());
        intoObject.setState(fromObject.getState());
        intoObject.setNumPratiche(fromObject.getNumPratiche());
       
    }
    
}