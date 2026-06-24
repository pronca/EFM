package it.eng.care.domain.flow.core.converter.FlowImportExportRequest;

import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowExportRequestDOtoFlowExportRequestDO implements Converter<FlowExportRequestDO, FlowExportRequestDO> {

    @Override
    public void convert(FlowExportRequestDO fromObject, FlowExportRequestDO toObject, ConversionContext ctx) {
		toObject.setEndExtractionDate(fromObject.getEndExtractionDate());
		toObject.setFileTalend(fromObject.getFileTalend());
		toObject.setRecord(fromObject.getRecord());
		toObject.setRequestDate(fromObject.getRequestDate());
		toObject.setRequester(fromObject.getRequester());
		toObject.setSchedulingInterval(fromObject.getSchedulingInterval());
		toObject.setSchedulingStartingTime(fromObject.getSchedulingStartingTime());
		toObject.setSchedulingType(fromObject.getSchedulingType());
		toObject.setSchedulingNextTime(fromObject.getSchedulingNextTime());
		toObject.setStartExtractionDate(fromObject.getStartExtractionDate());
		toObject.setStatus(fromObject.getStatus());
		toObject.setValidationStatus(fromObject.getValidationStatus());
		toObject.setValidationStatusDrl(fromObject.getValidationStatusDrl());
        toObject.setConsolidata(fromObject.getConsolidata());
        toObject.setDrg(fromObject.isDrg());
        toObject.setAziendeProfiloFlussi(fromObject.getAziendeProfiloFlussi());
        toObject.setSchedulingIntervalMinutes(fromObject.getSchedulingIntervalMinutes());
        toObject.setSchedulingIntervalSeconds(fromObject.getSchedulingIntervalSeconds());
    }

}
