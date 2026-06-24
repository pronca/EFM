package it.eng.care.domain.flow.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.core.dao.JobTalendDAO;
import it.eng.care.domain.flow.core.dao.JobTalendDipendencyDAO;
import it.eng.care.domain.flow.core.dao.JobTalendFileDAO;
import it.eng.care.domain.flow.core.dto.JobTalendDTO;
import it.eng.care.domain.flow.core.dto.JobTalendDipendencyDTO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.JobTalendDO;
import it.eng.care.domain.flow.core.entity.JobTalendDipendencyDO;
import it.eng.care.domain.flow.core.entity.JobTalendFileDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowTableService;
import it.eng.care.domain.flow.core.service.JobTalendService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import it.eng.care.platform.tool.transport.service.SearchInfos;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobTalendServiceImpl implements JobTalendService {

    @Autowired
    private JobTalendDAO jobTalendDAO;

    @Autowired
    private JobTalendFileDAO jobTalendFileDAO;
    
    @Autowired
    private JobTalendDipendencyDAO jobTalendDipendencyDAO;

    @Autowired
    private LoggedUserService loggUserService;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;
    
    @Autowired
    private FlowTableService flowTableService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowExportRequestServiceImpl.class);
    
    @Override
    public JobTalendDO saveEntity(JobTalendDO jobTalendDO) {
        Date date = new Date();
        jobTalendDO.setLastUpdateDate(date);
        jobTalendDO.setDeleted(false);
        jobTalendDO.setLastUpdateUser(loggUserService.getCurrentUser().getUsername());
        jobTalendDAO.save(jobTalendDO);
        return jobTalendDO;
    }

    @Override
    public Pair<List<JobTalendDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput, Boolean useFlowProfile) {
    	List<JobTalendDO> loList = new ArrayList<>();
        try {
            Integer limit = searchInput.getParam("limit");
            Integer offset = searchInput.getParam("startIndex");
            
            Pageable page = null; 
            if(limit != null && offset != null) {
            	page =  PageRequest.of((offset / limit), limit);
            }
            
            loList = jobTalendDAO.cerca(searchInput, page, useFlowProfile);
            
            return Pair.of(loList, SearchInfos.create());
        } catch (Exception e) {
        	try {
        		loList = jobTalendDAO.cerca(searchInput, null, useFlowProfile);
        		return Pair.of(loList, SearchInfos.create());
        	} catch (Exception e2) {
        		LogUtil.logException(LOGGER, "", e2);
        	}
        }
        return Pair.of(loList, SearchInfos.create());

    }

//    @Override
//    public int deleteEntity(JobTalendDTO jobTalendDTO) {
//        JobTalendDO jobTalend = (jobTalendDTO.getId()== null || jobTalendDTO.getId().isBlank()) ? null : jobTalendDAO.findById(jobTalendDTO.getId()).orElse(null);
//        
//        List<String> flows = flowManagerProfileService.getFlows();
//        if(!flows.contains(jobTalend.getFlow().getName())) {
//        	return 0;
//        }
//        
//        jobTalend.setDeleted(true);
//        if (jobTalend.getFile() != null) {
//            jobTalendFileDAO.deleteById(jobTalend.getId());
//            jobTalend.setFile(null);
//        }
//        return 1;
//    }
    
    @Override
    public int deleteEntity(JobTalendDTO jobTalendDTO) {
        JobTalendDO jobTalend = (jobTalendDTO.getId() == null || jobTalendDTO.getId().isBlank())
                ? null
                : jobTalendDAO.findById(jobTalendDTO.getId()).orElse(null);

        if (jobTalend == null) {
            return 0;
        }

        List<String> flows = flowManagerProfileService.getFlows();
        if (!flows.contains(jobTalend.getFlow().getName())) {
            return 0;
        }

        jobTalend.setDeleted(true);
         
        if (jobTalendFileDAO.existsById(jobTalend.getId())) {
            jobTalendFileDAO.deleteById(jobTalend.getId());
        }

        return 1;
    }

    
    @Override
    public void uploadFile(String jobTalendId, byte[] file) {
        JobTalendFileDO jobFile = (jobTalendId== null || jobTalendId.isBlank()) ? null : jobTalendFileDAO.findById(jobTalendId).orElse(null);

        if (jobFile == null) {
            jobFile = new JobTalendFileDO();
            jobFile.setId(jobTalendId);
            JobTalendDO jobTalend = new JobTalendDO();
            jobTalend.setId(jobTalendId);
            jobFile.setJobTalend(jobTalend);
        }

        jobFile.setJob(file);

        jobTalendFileDAO.save(jobFile);
    }
    
    @Override
    public void uploadDipendences(String jobTalendId, byte[] file, String fileName, String dependencyType) {

        JobTalendFileDO jobFile = jobTalendFileDAO.findById(jobTalendId).orElse(null);
        if (jobFile == null) {
        	LOGGER.info("FILE JAR TALEND NON TROVATO !");
            return;
        }

        // Normalizzo nome file (tolgo .jar)
        if (fileName != null && fileName.endsWith(".jar")) {
            fileName = fileName.substring(0, fileName.length() - 4);
        }

        // Cerco eventuali record esistenti (SOLO su campi non-BLOB)
        List<JobTalendDipendencyDO> existing =
                jobTalendDipendencyDAO.findByFileIdAndNameAndType(jobFile.getId(), fileName, dependencyType);

        if ("ANNULLAMENTO".equals(String.valueOf(dependencyType))) {
            // Se ANNULLAMENTO: cancello eventuali precedenti e inserisco nuovo
            if (existing != null && !existing.isEmpty()) {
                jobTalendDipendencyDAO.deleteAll(existing);
            }

            JobTalendDipendencyDO dip = new JobTalendDipendencyDO();
            dip.setId(UUID.randomUUID().toString());
            dip.setName(fileName);
            dip.setDependencyType(dependencyType);
            dip.setJobTalendFile(jobFile);
            dip.setJar(file);

            jobTalendDipendencyDAO.save(dip);
            return;
        }

        // Altrimenti: update se esiste, insert se non esiste
        JobTalendDipendencyDO dip;
        if (existing != null && !existing.isEmpty()) {
            dip = existing.get(0); // update dello stesso record
        } else {
            dip = new JobTalendDipendencyDO();
            dip.setId(UUID.randomUUID().toString());
            dip.setName(fileName);
            dip.setDependencyType(dependencyType);
            dip.setJobTalendFile(jobFile);
        }

        dip.setJar(file);
        jobTalendDipendencyDAO.save(dip);
    }
    
    @Override
    public Pair<List<JobTalendFileDO>, SearchInfo> retrieveTalendFile(JobTalendDO jobTalend) {
    	
    	List<JobTalendFileDO> loList = jobTalendFileDAO.findByJobTalend(jobTalend);
        
        return Pair.of(loList, SearchInfos.create());

    }
    
    @Override
    public Pair<List<JobTalendDipendencyDO>, SearchInfo> retrieveAllDipendency(JobTalendFileDO jobTalendFile) {
    	
    	JobTalendDipendencyDO filter = new JobTalendDipendencyDO();
    	filter.setJobTalendFile(jobTalendFile);
    	List<JobTalendDipendencyDO> loList2 = jobTalendDipendencyDAO.findAll(Example.of(filter));
        
        return Pair.of(loList2, SearchInfos.create());

    }
    
    @Override
    public int deleteDipendency(JobTalendDipendencyDTO jobTalendDipendencyDTO) {
    	JobTalendDipendencyDO jobTalendDipendency = (jobTalendDipendencyDTO.getId()== null || jobTalendDipendencyDTO.getId().isBlank()) ? null : jobTalendDipendencyDAO.findById(jobTalendDipendencyDTO.getId()).orElse(null);

    	if(jobTalendDipendency != null) {        
    		jobTalendDipendencyDAO.deleteById(jobTalendDipendency.getId());
    	}
    	
        return 1;
    }

	@Override
	public void uploadXsd(String flowName, String sez, byte[] file) {
		
		BaseSearchInput filter = new BaseSearchInput();
		filter.setParam("flowNameLike", flowName);
		Pair<List<FlowTableDO>, SearchInfo> result = flowTableService.retrieveAllFiltered(filter);
		List<FlowTableDO> sezioni = result.getFirst();
		for(FlowTableDO sezione : sezioni) {
			if((sezione.getSection().toString()).equals(sez)) {
				sezione.setXsd(file);
				flowTableService.updateEntity(sezione);
			}
		}
		
	}
	
	@Override
    public int deleteXsd(String flowName, String sez) {
		
		BaseSearchInput filter = new BaseSearchInput();
		filter.setParam("flowNameEqual", flowName);
		Pair<List<FlowTableDO>, SearchInfo> result = flowTableService.retrieveAllFiltered(filter);
		List<FlowTableDO> sezioni = result.getFirst();
		for(FlowTableDO sezione : sezioni) {
			if((sezione.getSection().toString()).equals(sez)) {
				sezione.setXsd(null);
				flowTableService.updateEntity(sezione);
			}
		}
    	
        return 1;
    }

}
