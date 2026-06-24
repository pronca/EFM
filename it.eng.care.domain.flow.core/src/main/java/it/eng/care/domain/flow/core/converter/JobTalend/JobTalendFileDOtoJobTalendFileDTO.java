package it.eng.care.domain.flow.core.converter.JobTalend;

import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.JobTalendFileDTO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class JobTalendFileDOtoJobTalendFileDTO implements Converter<JobTalendFileDO, JobTalendFileDTO> {

    @Override
    public void convert(JobTalendFileDO fromObject, JobTalendFileDTO intoObject, ConversionContext ctx) {

    	intoObject.setId(fromObject.getId());
        intoObject.setJobTalend(ctx.convertTo(fromObject.getJobTalend(), JobTalendDTO.class));
        intoObject.setJob(fromObject.getJob());
    }

}