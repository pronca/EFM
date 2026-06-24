package it.eng.care.domain.flow.core.converter.JobTalend;

import it.eng.care.domain.flow.core.dto.JobTalendFileDTO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class JobTalendFileDTOtoJobTalendFileDO implements Converter<JobTalendFileDTO, JobTalendFileDO> {

    @Override
    public void convert(JobTalendFileDTO fromObject, JobTalendFileDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setJobTalend(ctx.convertTo(fromObject.getJobTalend(), JobTalendDO.class));
        intoObject.setJob(fromObject.getJob());
    }

}