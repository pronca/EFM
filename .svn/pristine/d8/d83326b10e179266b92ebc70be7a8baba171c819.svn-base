package it.eng.care.domain.flow.core.converter.FlowImportExportRequest;

import it.eng.care.domain.flow.core.dto.FlowImportRequestDTO;
import it.eng.care.domain.flow.core.dto.FlowImportRequestFieldDateDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestDO;
import it.eng.care.domain.flow.core.entity.FlowImportRequestFieldDateDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class FlowImportRequestDTOtoFlowImportRequestDO implements Converter<FlowImportRequestDTO, FlowImportRequestDO> {


    @Autowired
    private LoggedUserService loggUserService;


    @Override
    public void convert(FlowImportRequestDTO fromObject, FlowImportRequestDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        try {
        	intoObject.setFlow(ctx.convertTo(fromObject.getFlow(), FlowDO.class));
        } catch (Exception e) {
        	intoObject.setFlow(null);
        }
        try {
        	intoObject.setVersion(ctx.convertTo(fromObject.getVersion(), VersionDO.class));
        } catch (Exception e) {
        	intoObject.setVersion(null);
        }
        Set<FlowImportRequestFieldDateDO> listDataFields = new HashSet<>();
        for (FlowImportRequestFieldDateDTO flowImportRequestFieldDateDTO : fromObject.getDateFields()) {
            listDataFields.add(ctx.convertTo(flowImportRequestFieldDateDTO, FlowImportRequestFieldDateDO.class));
        }
        intoObject.setDateFields(listDataFields);
        intoObject.setImportingUser(loggUserService.getCurrentUser().getUsername());
        intoObject.setRequestDate(fromObject.getRequestDate());
        intoObject.setRecord(fromObject.getRecord());
        intoObject.setStatus(fromObject.getStatus());
        intoObject.setEndExtractionDate(fromObject.getEndExtractionDate());

    }

}
