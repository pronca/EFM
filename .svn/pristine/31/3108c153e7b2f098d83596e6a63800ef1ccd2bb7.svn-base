package it.eng.care.domain.flow.drools.controller.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.entity.ResetValidationRequestDO;
import it.eng.care.domain.flow.core.entity.SecondLevelValidationRequestDO;
import it.eng.care.domain.flow.core.enumeration.JobExecutionStatus;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.drools.controller.ValidatorController;
import it.eng.care.domain.flow.drools.model.dto.FlowRuleDTO;
import it.eng.care.domain.flow.drools.model.rule.RuleType;
import it.eng.care.domain.flow.drools.model.rule.RulesDownloadRequest;
import it.eng.care.domain.flow.drools.model.rule.RulesUploadResponse;
import it.eng.care.domain.flow.drools.model.status.BeanValidationStatusEnum;
import it.eng.care.domain.flow.drools.model.status.ValidationResultWrapper;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;
import it.eng.care.domain.flow.drools.service.api.RuleManagerService;
import it.eng.care.domain.flow.drools.service.api.UtilityService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfoImpl;

@RestController
@RequestMapping("/fm/ValidatorController")
public class ValidatorControllerImpl implements ValidatorController {

	@Autowired
	private RuleManagerService ruleManagerService;
	
	@Autowired
	private FlowManagerProfileService flowManagerProfileService;
	
	@Autowired
	private FlowDAO flowDAO;
	
	@Autowired
	private FlowValidator flowValidator;
	
	@Autowired
	private UtilityService utilityService;
	
	@Autowired
    private LogAccessiPMConfig logAccessiPMConfig;
	
	private static Map<String, Boolean> validationMap = new HashMap<String, Boolean>();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorControllerImpl.class);
	
	public ValidatorControllerImpl() {
		LOGGER.info("ValidatorControllerImpl");
	}
	
	@Override
	@PostMapping("/crossValidation")
	@ResponseBody
	public FlowOperationResult<String> crossValidation(@RequestBody ValidationFilter request) {
		try {
			
			if(request.getFlowId() == null) {
				return FlowOperationResult.failure("Flusso assente");
			}
			
			if(request.getMonth() == null) {
				return FlowOperationResult.failure("Specificare il mese di riferimento");
			}
			
			if(request.getYear() == null) {
				return FlowOperationResult.failure("Specificare l'anno di riferimento");
			}
			
			Boolean check = flowManagerProfileService.checkFlowById(request.getFlowId());
			if(!check) {
				return null;
			}
			
			if(request.getMonth() != null && request.getMonth().length() == 1) {
				request.setMonth("0" + request.getMonth());
			}
			
			String id = flowValidator.createSecondLevelValidationRequest(request.getFlowId(), request.getMonth(), request.getYear());
			
			ExecutorService exceturExportService = Executors.newFixedThreadPool(1);
			
			exceturExportService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					SecondLevelValidationRequestDO req = flowValidator.getCrossValidationRequest(id);

					if (req != null) {
						try {
							flowValidator.changeSecondLevelValidationRequestStatus(req.getId(), JobExecutionStatus.VALIDATING.name());
							Boolean result = executeCrossValidation(req);
							if (result) {
								flowValidator.changeSecondLevelValidationRequestStatus(req.getId(), JobExecutionStatus.FINISHED.name());
							} else {
								flowValidator.changeSecondLevelValidationRequestStatus(req.getId(), JobExecutionStatus.ERROR.name());
							}
						} catch (Exception ex) {
							LogUtil.logException(LOGGER, "", ex);
//							ex.printStackTrace();
							flowValidator.changeSecondLevelValidationRequestStatus(req.getId(), JobExecutionStatus.ERROR.name());
						}
					}
					
					return "";
				}
			});
			
			return FlowOperationResult.success(id);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
			return FlowOperationResult.failure();
		}

	}
	
	@Override
	@PostMapping("/resetValidation")
	@ResponseBody
	public FlowOperationResult<String> resetValidation(@RequestBody ValidationFilter request) {
		try {
			
			if(request.getFlowId() == null) {
				return FlowOperationResult.failure("Flusso assente");
			}
			
			if(request.getMonth() == null) {
				return FlowOperationResult.failure("Specificare il mese di riferimento");
			}
			
			if(request.getYear() == null) {
				return FlowOperationResult.failure("Specificare l'anno di riferimento");
			}
			
			Boolean check = flowManagerProfileService.checkFlowById(request.getFlowId());
			if(!check) {
				return null;
			}
			
			String id = flowValidator.createResetValidationRequest(request.getFlowId(), request.getMonth(), request.getYear());
			
			ExecutorService exceturExportService = Executors.newFixedThreadPool(1);
			
			exceturExportService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					ResetValidationRequestDO req = flowValidator.getResetValidationRequest(id);
					if (req != null) {
						try {
							flowValidator.changeResetValidationRequestStatus(req.getId(), JobExecutionStatus.RESETTING.name());
							Boolean result = flowValidator.resetValidationByMonthAndYear(req.getId(), false);
							if(result) {
								ValidationFilter filter = new ValidationFilter();
								filter.setRowStatus(BeanValidationStatusEnum.RESET.name());
								filter.setFlowId(req.getFlow().getId());
								
								Set<FlowVersionDO> fvList = req.getFlow().getVersions();
								if (fvList != null && !fvList.isEmpty()) {
									for (FlowVersionDO fv : fvList) {
										String versionId = fv.getVersion().getId();
										filter.setVersionId(versionId);
										flowValidator.executeValidation(filter, false, RuleType.FLOW.name()); // non inviare l'esito al verticale
									}
								}
								flowValidator.changeResetValidationRequestStatus(req.getId(), JobExecutionStatus.FINISHED.name());
							} else {
								flowValidator.changeResetValidationRequestStatus(req.getId(), JobExecutionStatus.ERROR.name());
							}
							
							
						} catch (Exception ex) {
							LogUtil.logException(LOGGER, "", ex);
//							ex.printStackTrace();
						}
					}
					
					return "";
				}
			});
			
			return FlowOperationResult.success(id);
		} catch (Exception ex) {
			LogUtil.logException(LOGGER, "", ex);
//			ex.printStackTrace();
			return FlowOperationResult.failure();
		}
	}
	
	@Override
	@PostMapping("/validateRecords")
	@ResponseBody
	public FlowOperationResult<String> validateRecords(@RequestBody ValidationFilter validationFilter) {
		
		if(validationFilter.getField() != null) {
			for(FlowViewFilterField field : validationFilter.getField()) {
				if(field.getName() != null && field.getName().equals("MONTH_RIF")) {
					if(field.getParam() != null) {
						String monthRif = field.getParam();
						if(monthRif != null && monthRif.length() == 1) {
							monthRif = "0" + monthRif;
							field.setParam(monthRif);
						}
					}
				}
			}
		}
		
		if(validationFilter.getFlowId() == null) {
			return null;
		}
		
		Boolean check = flowManagerProfileService.checkFlowById(validationFilter.getFlowId());
		if(!check) {
			return null;
		}
		
		String validationId = UUID.randomUUID().toString();
		ValidatorControllerImpl.validationMap.put(validationId, false);
		
		ExecutorService exceturExportService = Executors.newFixedThreadPool(1);
		
		exceturExportService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				try {
					FlowDO flow = flowDAO.getFlowWithVersions(validationFilter.getFlowId());
					Set<FlowVersionDO> fvList = flow.getVersions();
					if (fvList != null && !fvList.isEmpty()) {
						for (FlowVersionDO fv : fvList) {
							String versionId = fv.getVersion().getId();
							validationFilter.setVersionId(versionId);
							flowValidator.resetValidation(validationFilter);
							ValidationResultWrapper wrapper = flowValidator.executeValidation(validationFilter, true, RuleType.FLOW.name());
						}
					}
						
				} catch (Exception ex) {
					LogUtil.logException(LOGGER, "", ex);
//					ex.printStackTrace();
				} finally {
					ValidatorControllerImpl.validationMap.remove(validationId);
				}
				return "";
			}

		});
		
		return FlowOperationResult.success(validationId);
	}
	
	@Override
	@PostMapping("/checkValidation")
	@ResponseBody
	public FlowOperationResult<Boolean> checkValidation(@RequestBody BaseSearchInput searchInput) {
		return FlowOperationResult.success(!ValidatorControllerImpl.validationMap.containsKey(searchInput.getValue("validationId")));
	}
	
	@Override
	@PostMapping("/checkResetJob")
	@ResponseBody
	public FlowOperationResult<Boolean> checkResetJob(@RequestBody BaseSearchInput searchInput) {
		Boolean terminated = flowValidator.isTerminatedResetValidationRequests((String)searchInput.getValue("validationId"));
		return FlowOperationResult.success(terminated);
	}
	
	@Override
	@PostMapping("/checkSecondLevelJob")
	@ResponseBody
	public FlowOperationResult<Boolean> checkSecondLevelJob(@RequestBody BaseSearchInput searchInput) {
		Boolean terminated = flowValidator.isTerminatedSecondLevelValidationRequests((String)searchInput.getValue("validationId"));
		return FlowOperationResult.success(terminated);
	}

	@Override
	@PostMapping("/validateRecord")
	@ResponseBody
	public FlowOperationResult<?> validateRecord(@RequestBody ValidationFilter validationFilter) {
		
		FlowOperationResult<?> result = new FlowOperationResult<>();
		
		if(validationFilter.getFlowId() == null) {
			return null;
		}
		
		Boolean check = flowManagerProfileService.checkFlowById(validationFilter.getFlowId());
		if(!check) {
			return null;
		}
		
		if(validationFilter.getMonth() != null && validationFilter.getMonth().length() == 1) {
			validationFilter.setMonth("0" + validationFilter.getMonth());
		}
		
		flowValidator.resetValidation(validationFilter);
		ValidationResultWrapper wrapper = flowValidator.executeValidation(validationFilter, true, RuleType.FLOW.name());
		if(wrapper != null) {
			
			if(wrapper.getAbsentRules()) {
				result = FlowOperationResult.failure("Validazione non eseguibile: nessuna regola caricata");
			} else if(wrapper.getUnvalidable()) {
				result = FlowOperationResult.failure();
			} else {
				result = FlowOperationResult.success();
			}
			
			if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogValidazioneAvvio())) {
				FlowDO flow = flowDAO.getFlowWithVersions(validationFilter.getFlowId());
				validationFilter.setFlowId(flow.getName());
				FlowOperationResult<?> resultLogAccessi = flowValidator.sendAuditAvviaValidazioneToPM(validationFilter, result);
	        }
			
			return result;
		}
		
		return FlowOperationResult.failure();
	}

	@Override
	@PostMapping(path = "/_downloadRules", produces = "application/zip")
	@ResponseBody
	public HttpEntity<byte[]> downloadRules(@RequestBody RulesDownloadRequest request) {
		try {
			
			Boolean check = flowManagerProfileService.checkFlowById(request.getFlowId());
			if(!check) {
				return null;
			}
			
			File[] files = ruleManagerService.getRules(request.getFlowId(), request.getVersionId());
			if (files != null && files.length > 0) {
				String[] fileNames = new String[files.length];
				int i = 0;
				for (File file : files) {
					fileNames[i] = file.getName();
					i++;
				}

				byte[] byt = FileUtility.compressingFiles(files[0].getParent(), files[0].getParent() + "/rules.zip",
						fileNames);
				HttpHeaders header = new HttpHeaders();
				header.set("Content-Disposition", "attachment; filename=" + "rules.zip");

				return new HttpEntity<byte[]>(byt, header);
			}
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	@PostMapping(path = "/_downloadFunctions", produces = "application/zip")
	@ResponseBody
	public HttpEntity<byte[]> downloadFunctions() {
		try {
			File file = ruleManagerService.getFunctions();
			if (file != null) {
				byte[] byt = FileUtility.compressingFiles(file.getParent(), file.getParent() + "/functions.zip", file.getName());
				HttpHeaders header = new HttpHeaders();
				header.set("Content-Disposition", "attachment; filename=" + "functions.zip");
				return new HttpEntity<byte[]>(byt, header);
			}
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

		return null;
	}

	@Override
	@PostMapping("/_search")
	@ResponseBody
	public SearchOperationResult<FlowRuleDTO> search(@RequestBody BaseSearchInput searchInput) {
		List<FlowRuleDTO> result = ruleManagerService.searchRules(searchInput.getValue("flow"),
				searchInput.getValue("version"), true, searchInput.getValue("ruleType"));
		return SearchOperationResult.success(result, new SearchInfoImpl(result.size()));
	}

	@Override
	@PostMapping(path = "/_import")
	@ResponseBody
	public RulesUploadResponse uploadRule(
			@RequestHeader(name = "flowId", defaultValue = "unknown") String flowId,
			@RequestHeader(name = "versionId", defaultValue = "unknown") String versionId,
			@RequestHeader(name = "ruleType", defaultValue = "unknown") String ruleType,
			@RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
			@RequestHeader(name = "fileType", defaultValue = MediaType.APPLICATION_OCTET_STREAM_VALUE) String fileType,
			@RequestBody byte[] bytes) {

		try {
			
			Boolean check = flowManagerProfileService.checkFlowById(flowId);
			if(!check) {
				return null;
			}
			
			List<String> result = ruleManagerService.addRule(versionId, flowId, bytes, fileName, ruleType);
			if (result != null && !result.isEmpty()) {
				return RulesUploadResponse.failure(result);
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

		return RulesUploadResponse.success();
	}

	@Override
	@PostMapping("delete")
	@ResponseBody
	public OperationResult<String> deleteAllRules(@RequestBody RulesDownloadRequest request) {
		String results = "OK";
		
		if(request.getFlowId() == null && request.getFlowId().isEmpty()) {
			return OperationResult.failure();
		}
		
		Boolean check = flowManagerProfileService.checkFlowById(request.getFlowId());
		if(!check) {
			return SearchOperationResult.failure();
		}
		
		try {
			ruleManagerService.deleteAllRules(request.getVersionId(), request.getFlowId());
		} catch (IOException e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
			return OperationResult.failure();
		}

		return OperationResult.success(results);
	}
	
	
	@Override
	@PostMapping(path = "/_functions")
	@ResponseBody
	public RulesUploadResponse uploadFunctions(
			@RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
			@RequestHeader(name = "fileType", defaultValue = MediaType.APPLICATION_OCTET_STREAM_VALUE) String fileType,
			@RequestBody byte[] bytes) {

		try {
			List<String> result = ruleManagerService.addFunctions(bytes, fileName);
			if (result != null && !result.isEmpty()) {
				return RulesUploadResponse.failure(result);
			}
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}

		return RulesUploadResponse.success();
	}
	
	
	private Boolean executeCrossValidation(SecondLevelValidationRequestDO request) throws SQLException {
		BaseSearchInput searchInput = new BaseSearchInput();
		searchInput.setValue("flow", request.getFlow().getId());
		//Pair<List<FlowRegionUnionDO>, SearchInfo> unionList = flowRegionUnionService.retrieveAllFiltered(searchInput);
		//if (unionList != null && unionList.getFirst() != null && !unionList.getFirst().isEmpty()) {
			//FlowRegionUnionDO union = unionList.getFirst().get(0);
			//FlowDO flowRegion = union.getFlowRegion();
			//String flowRegionId = flowRegion.getId();

			ValidationFilter filter = new ValidationFilter();
			filter.setRowStatus(BeanValidationStatusEnum.RESET.name());
			//filter.setFlowId(flowRegionId);
			filter.setFlowId(request.getFlow().getId());
//			filter.setParam(new String[] {request.getMonth(),request.getYear()});
				
			FlowViewFilterField[] fields = new FlowViewFilterField[2];
			FlowViewFilterField month = new FlowViewFilterField();
			month.setName("MONTH_RIF");
			month.setSection(0);
			month.setParam(request.getMonth());
			fields[0] = month;
				
			FlowViewFilterField year = new FlowViewFilterField();
			year.setName("YEAR_RIF");
			year.setSection(0);
			year.setParam(request.getYear());
			fields[1] = year;
				
			filter.setField(fields);
				
			Set<FlowVersionDO> fvList = request.getFlow().getVersions();
			if (fvList != null && !fvList.isEmpty()) {
				for (FlowVersionDO fv : fvList) {
					String versionId = fv.getVersion().getId();
					filter.setVersionId(versionId);
					Boolean res = flowValidator.resetValidationByMonthAndYear(filter.getFlowId(), versionId, request.getMonth(), request.getYear(), true);
					if(res) {
						ValidationResultWrapper result = flowValidator.executeValidation(filter, false, RuleType.CROSS.name(), request);
						if(result == null || result.getUnvalidable()) {
							LOGGER.error(">>>>>>>>>>>>>>>>>>> SECOND LEVEL VALIDATION JOB: version:" + versionId + " flow: " + filter.getFlowId() + "  <<<<<<<<<<<<<<<<<<<<<");
							return false;
						} else if(result.getAbsentRules()) {
							LOGGER.warn(">>>>>>>>>>>>>>>>>>> SECOND LEVEL VALIDATION JOB REGOLE ASSENTI : version:" + versionId + " flow: " + filter.getFlowId() + "  <<<<<<<<<<<<<<<<<<<<<");
						}
					} else {
						LOGGER.error(">>>>>>>>>>>>>>>>>>> SECOND LEVEL VALIDATION JOB: version:" + versionId + " flow: " + filter.getFlowId() + " ERRORE ANNULLAMENTO VALIDAZIONE CROSS <<<<<<<<<<<<<<<<<<<<<");
					}
				}
			}
			
			return true;
			
		//} else {
		//	return false;
		//}

	}
	
	@GetMapping("/evictLookupCache")
	public void evictLookupCache() {
		utilityService.evictAllCacheValues();	
	}

}
