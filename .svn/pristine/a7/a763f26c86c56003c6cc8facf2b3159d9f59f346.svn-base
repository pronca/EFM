package it.eng.care.domain.flow.core.converter.JobTalend;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class JobTalendDOtoJobTalendDTO implements Converter<JobTalendDO, JobTalendDTO> {

    @Override
    public void convert(JobTalendDO fromObject, JobTalendDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        try {
        	intoObject.setFlow(ctx.convertTo(fromObject.getFlow(), FlowDTO.class));
        } catch (Exception e) {
        	intoObject.setFlow(null);
        }
        try {
        	intoObject.setVersion(ctx.convertTo(fromObject.getVersion(), VersionDTO.class));
        } catch (Exception e) {
        	intoObject.setVersion(null);
        }
        intoObject.setName(fromObject.getName());
        intoObject.setDescription(fromObject.getDescription());
        intoObject.setClassName(fromObject.getClassName());
        intoObject.setType(fromObject.getType());
        intoObject.setPackageJob(fromObject.getPackageJob());
        intoObject.setDeleted(fromObject.getDeleted());
        intoObject.setLastUpdateDate(fromObject.getLastUpdateDate());
        intoObject.setLastUpdateUser(fromObject.getLastUpdateUser());
    }

}