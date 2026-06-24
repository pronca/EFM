package it.eng.care.domain.flow.core.converter.FlowDrg;

import it.eng.care.domain.flow.core.dto.FlowDrgDTO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowDrgDTOtoFlowDrgDO implements Converter<FlowDrgDTO, FlowDrgDO> {
	
	@Override
    public void convert(FlowDrgDTO fromObject, FlowDrgDO intoObject, ConversionContext ctx) {
    	    	
    	intoObject.setExtraction(ctx.convertTo(fromObject.getExtraction(), FlowExportRequestDO.class));
        intoObject.setId(fromObject.getId());
        intoObject.setSendDate(fromObject.getSendDate());
        intoObject.setError(fromObject.getError());
        intoObject.setReturnDate(fromObject.getReturnDate());
        intoObject.setState(fromObject.getState());
        intoObject.setNumPratiche(fromObject.getNumPratiche());
       
    }

}
