package it.eng.care.domain.flow.core.controller.impl;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.controller.JobTalendController;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDOtoJobTalendDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDTOtoJobTalendDO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDipendencyDOtoJobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendDipendencyDTOtoJobTalendDipendencyDO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendFileDOtoJobTalendFileDTO;
import it.eng.care.domain.flow.core.converter.JobTalend.JobTalendFileDTOtoJobTalendFileDO;
import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.JobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.dto.JobTalendFileDTO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.entity.JobTalendDipendencyDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.domain.flow.core.service.JobTalendService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/JobTalendDTO")
public class JobTalendControllerImpl implements JobTalendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobTalendControllerImpl.class);

    @Autowired
    private JobTalendService jobTalendService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private JobTalendDOtoJobTalendDTO jobTalendDOtoJobTalendDTO;

    @Autowired
    private JobTalendDTOtoJobTalendDO jobTalendDTOtoJobTalendDO;
    
    @Autowired
    private JobTalendDipendencyDOtoJobTalendDipendencyDTO jobTalendDipendencyDOtoJobTalendDipendencyDTO;

    @Autowired
    private JobTalendDipendencyDTOtoJobTalendDipendencyDO jobTalendDipendencyDTOtoJobTalendDipendencyDO;
    
    @Autowired
    private JobTalendFileDOtoJobTalendFileDTO jobTalendFileDOtoJobTalendFileDTO;

    @Autowired
    private JobTalendFileDTOtoJobTalendFileDO jobTalendFileDTOtoJobTalendFileDO;

    //@Value("${talend.jar.file.dir}")
    //private String pathTalendJarFile;

    @PostConstruct
    public void post() {
        conversionService.registerConverter(JobTalendDO.class, JobTalendDTO.class, jobTalendDOtoJobTalendDTO);
        conversionService.registerConverter(JobTalendDTO.class, JobTalendDO.class, jobTalendDTOtoJobTalendDO);
        conversionService.registerConverter(JobTalendDipendencyDO.class, JobTalendDipendencyDTO.class, jobTalendDipendencyDOtoJobTalendDipendencyDTO);
        conversionService.registerConverter(JobTalendDipendencyDTO.class, JobTalendDipendencyDO.class, jobTalendDipendencyDTOtoJobTalendDipendencyDO);
        conversionService.registerConverter(JobTalendFileDO.class, JobTalendFileDTO.class, jobTalendFileDOtoJobTalendFileDTO);
        conversionService.registerConverter(JobTalendFileDTO.class, JobTalendFileDO.class, jobTalendFileDTOtoJobTalendFileDO);
    }

    @Override
    @PostMapping("/save")
    public SaveOperationResult<JobTalendDTO> save(@RequestBody JobTalendDTO jobTalendDTO) {
        JobTalendDO jobTalendDO = conversionService.convertTo(jobTalendDTO, JobTalendDO.class);
        jobTalendDO = jobTalendService.saveEntity(jobTalendDO);
        JobTalendDTO jobTalendDTOresult = conversionService.convertTo(jobTalendDO, JobTalendDTO.class);
        return SaveOperationResult.success(jobTalendDTOresult);
    }

    @Override
    @PostMapping("/_search")
    @ResponseBody
    public SearchOperationResult<JobTalendDTO> search(@RequestBody BaseSearchInput searchInput) {
        Pair<List<JobTalendDO>, SearchInfo> searchResults = jobTalendService.retrieveAllFiltered(searchInput, true);
        List<JobTalendDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), JobTalendDTO.class);
        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }

    @Override
    @PostMapping("/delete")
    public int deleteJobTalend(@RequestBody JobTalendDTO jobTalendDTO) {
        int response = jobTalendService.deleteEntity(jobTalendDTO);
        return response;
    }

    @Override
    @PostMapping(path = "/_import")
    @ResponseBody
    public OperationResult<String> handleFileUpload(
            @RequestHeader(name = "jobTalendId", defaultValue = "unknown", required = false) String jobTalendId,
            @RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
            @RequestBody byte[] bytes) {

        jobTalendService.uploadFile(jobTalendId, bytes);
        String results = "ok";
        return OperationResult.success(results);

    }
    
    @Override
    @PostMapping(path = "/_dependency")
    @ResponseBody
    public OperationResult<String> handleDipUpload(
            @RequestHeader(name = "jobTalendId", defaultValue = "unknown", required = false) String jobTalendId,
            @RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
            @RequestHeader(name = "fileType", defaultValue = MediaType.APPLICATION_OCTET_STREAM_VALUE) String fileType,
            @RequestHeader(name = "dependencyType", defaultValue = MediaType.APPLICATION_OCTET_STREAM_VALUE) String dependencyType,
            @RequestBody byte[] bytes) {

        String results = "ok";
        jobTalendService.uploadDipendences(jobTalendId, bytes, fileName, dependencyType);
        return OperationResult.success(results);

    }
    
    @Override
    @PostMapping("/searchDipendency")
    @ResponseBody
    public SearchOperationResult<JobTalendDipendencyDTO> searchDipendency(@RequestBody JobTalendDTO jobTalend) {
    	JobTalendDO jobTalendDO = conversionService.convertTo(jobTalend, JobTalendDO.class);
    	Pair<List<JobTalendFileDO>, SearchInfo> searchResultsFile = jobTalendService.retrieveTalendFile(jobTalendDO);
        Pair<List<JobTalendDipendencyDO>, SearchInfo> searchResults = jobTalendService.retrieveAllDipendency(searchResultsFile.getFirst().get(0));
        List<JobTalendDipendencyDTO> dtos = conversionService.convertAllTo(searchResults.getFirst(), JobTalendDipendencyDTO.class);
        return SearchOperationResult.success(dtos, searchResults.getSecond());
    }
    
    @Override
    @PostMapping("/deleteDipendency")
    public int deleteDipendency(@RequestBody JobTalendDipendencyDTO jobTalendDipendencyDTO) {
        int response = jobTalendService.deleteDipendency(jobTalendDipendencyDTO);
        return response;
    }
    
    @Override
    @PostMapping(path = "/_xsd")
    @ResponseBody
    public OperationResult<String> handleXsdUpload(
            @RequestHeader(name = "fileType", defaultValue = MediaType.APPLICATION_OCTET_STREAM_VALUE) String fileType,
            @RequestHeader(name = "flowName", defaultValue = "unknown") String flowName,
            @RequestHeader(name = "sez", defaultValue = "unknown") String sez,
            @RequestBody byte[] bytes) {

        String results = "ok";
        jobTalendService.uploadXsd(flowName, sez, bytes);
        return OperationResult.success(results);

    }
    
    @Override
    @PostMapping("/deleteXsd")
    public int deleteXsd(@RequestBody BaseSearchInput searchInput) {
    	
    	String flowName = (String) searchInput.getParam("flowName");
    	String sez = (String) searchInput.getParam("sez");
        int response = jobTalendService.deleteXsd(flowName, sez);
        return response;
    }
    
}
