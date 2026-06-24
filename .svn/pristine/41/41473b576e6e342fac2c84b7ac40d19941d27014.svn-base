package it.eng.care.domain.flow.core.converter.FlowImportExportRequest;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestFieldDateDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

import java.util.HashSet;
import java.util.Set;

public class FlowImportRequestDOtoFlowImportRequestDTO implements Converter<FlowImportRequestDO, FlowImportRequestDTO> {

    @Override
    public void convert(FlowImportRequestDO fromObject, FlowImportRequestDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());

        intoObject.setFlow(ctx.convertTo(fromObject.getFlow(), FlowDTO.class));
        intoObject.setVersion(ctx.convertTo(fromObject.getVersion(), VersionDTO.class));
        Set<FlowImportRequestFieldDateDTO> listDataFields = new HashSet<>();
        for (FlowImportRequestFieldDateDO flowImportRequestFieldDateDO : fromObject.getDateFields()) {
            listDataFields.add(ctx.convertTo(flowImportRequestFieldDateDO, FlowImportRequestFieldDateDTO.class));
        }
        intoObject.setDateFields(listDataFields);
        intoObject.setImportingUser(fromObject.getImportingUser());
        intoObject.setStatus(fromObject.getStatus());
        intoObject.setRequestDate(fromObject.getRequestDate());
        intoObject.setRecord(fromObject.getRecord());
        intoObject.setEndExtractionDate(fromObject.getEndExtractionDate());

    }

}
