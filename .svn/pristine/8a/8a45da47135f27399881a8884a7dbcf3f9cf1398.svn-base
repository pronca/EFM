package it.eng.care.domain.flow.core.service;

import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.JobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.entity.JobTalendDipendencyDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface JobTalendService {

    Pair<List<JobTalendDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile);

    JobTalendDO saveEntity(JobTalendDO jobTalendDO);

    int deleteEntity(JobTalendDTO jobTalendDTO);

    void uploadFile(String jobTalendId, byte[] file);

	void uploadDipendences(String jobTalendId, byte[] file, String fileName, String dependencyType);

	Pair<List<JobTalendDipendencyDO>, SearchInfo> retrieveAllDipendency(JobTalendFileDO jobTalendFile);

	Pair<List<JobTalendFileDO>, SearchInfo> retrieveTalendFile(JobTalendDO jobTalend);

	int deleteDipendency(JobTalendDipendencyDTO jobTalendDipendencyDTO);

	void uploadXsd(String flowName, String sez, byte[] file);

	int deleteXsd(String flowName, String sez);

}
