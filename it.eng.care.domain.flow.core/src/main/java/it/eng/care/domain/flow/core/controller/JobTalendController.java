package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.JobTalendDipendencyDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface JobTalendController {

    SaveOperationResult<JobTalendDTO> save(JobTalendDTO jobTalendDTO);

    SearchOperationResult<JobTalendDTO> search(BaseSearchInput baseSearchInput);

    int deleteJobTalend(JobTalendDTO jobTalendDTO);

    OperationResult<String> handleFileUpload(String jobTalendId, String fileName, byte[] bytes);

	OperationResult<String> handleDipUpload(String jobTalendName, String fileName, String fileType, String dependencyType, byte[] bytes);

	SearchOperationResult<JobTalendDipendencyDTO> searchDipendency(JobTalendDTO jobTalend);

	int deleteDipendency(JobTalendDipendencyDTO jobTalendDipendencyDTO);

	OperationResult<String> handleXsdUpload(String fileType, String flowName, String sez, byte[] bytes);

	int deleteXsd(BaseSearchInput searchInput);

}
