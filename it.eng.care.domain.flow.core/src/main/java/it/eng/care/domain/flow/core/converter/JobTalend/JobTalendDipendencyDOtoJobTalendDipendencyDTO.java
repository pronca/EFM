package it.eng.care.domain.flow.core.converter.JobTalend;

import it.eng.care.domain.flow.core.dto.JobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.dto.JobTalendFileDTO;
import it.eng.care.domain.flow.core.entity.JobTalendDipendencyDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class JobTalendDipendencyDOtoJobTalendDipendencyDTO implements Converter<JobTalendDipendencyDO, JobTalendDipendencyDTO> {

    @Override
    public void convert(JobTalendDipendencyDO fromObject, JobTalendDipendencyDTO intoObject, ConversionContext ctx) {

    	intoObject.setId(fromObject.getId());
        intoObject.setName(fromObject.getName());
        intoObject.setDependencyType(fromObject.getDependencyType());
        intoObject.setJobTalendFile(ctx.convertTo(fromObject.getJobTalendFile(), JobTalendFileDTO.class));
        intoObject.setJar(fromObject.getJar());
    }

}