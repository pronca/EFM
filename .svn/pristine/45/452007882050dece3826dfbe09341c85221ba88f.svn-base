package it.eng.care.domain.flow.core.converter.JobTalend;

import it.eng.care.domain.flow.core.dto.JobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.entity.JobTalendDipendencyDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class JobTalendDipendencyDTOtoJobTalendDipendencyDO implements Converter<JobTalendDipendencyDTO, JobTalendDipendencyDO> {

    @Override
    public void convert(JobTalendDipendencyDTO fromObject, JobTalendDipendencyDO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setJobTalendFile(ctx.convertTo(fromObject.getJobTalendFile(), JobTalendFileDO.class));
        intoObject.setJar(fromObject.getJar());
    }

}