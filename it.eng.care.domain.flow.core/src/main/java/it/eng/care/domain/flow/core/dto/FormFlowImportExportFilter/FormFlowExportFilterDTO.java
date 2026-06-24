package it.eng.care.domain.flow.core.dto.FormFlowImportExportFilter;

import it.eng.care.domain.flow.core.dto.JobTalendDTO;

public class FormFlowExportFilterDTO extends FormFlowBaseFilterDTO {

    private JobTalendDTO jobTalendId;

    public JobTalendDTO getJobTalendId() {
        return jobTalendId;
    }

    public void setJobTalendId(JobTalendDTO jobTalendId) {
        this.jobTalendId = jobTalendId;
    }
}
