package it.eng.care.domain.flow.core.controller.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.Files;

import it.eng.care.domain.flow.anagraficaassistito.utility.AnagraficaAssistitoUtility;
import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.controller.MonitorSdoXlController;
import it.eng.care.domain.flow.core.converter.Flow.FlowDOtoFlowDTO;
import it.eng.care.domain.flow.core.converter.MonitorSdoXl.MonitorSdoXlDOtoMonitorSdoXlDTO;
import it.eng.care.domain.flow.core.converter.MonitorSdoXl.MonitorSdoXlDTOtoMonitorSdoXlDO;
import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoErrorPaginDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoPaginDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoXlDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoXlErrorDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.MonitorSdoXlService;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RestController
@RequestMapping("/fm/MonitorSdoXlDTO")
public class MonitorSdoControllerImpl implements MonitorSdoXlController {
	
	@Autowired
	private MonitorSdoXlService monitorSdoXlService;

	@Autowired
    private ConversionService conversionService;
	
	@Autowired
	private MonitorSdoXlDOtoMonitorSdoXlDTO monitorSdoXlDOtoMonitorSdoXlDTO;
	
	@Autowired
	private MonitorSdoXlDTOtoMonitorSdoXlDO monitorSdoXlDTOtoMonitorSdoXlDO;
	
	@Autowired
    private FormFlowService formFlowService;
	
	@Autowired
    private FlowDOtoFlowDTO flowDOToFlowDTO;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@Autowired
	private LogAccessiPMConfig logAccessiPMConfig;
	
	@PostConstruct
	public void post() {
		conversionService.registerConverter(FlowDO.class, FlowDTO.class, flowDOToFlowDTO);
		conversionService.registerConverter(MonitorSdoXlDO.class, MonitorSdoXlDTO.class, monitorSdoXlDOtoMonitorSdoXlDTO);
		conversionService.registerConverter(MonitorSdoXlDTO.class, MonitorSdoXlDO.class, monitorSdoXlDTOtoMonitorSdoXlDO);
	}
	
	@Override
	@PostMapping("/pagin")
	@ResponseBody
	public OperationResult<MonitorSdoPaginDTO> getPaginatedData(@RequestBody BaseSearchInput searchInput){
		Page<MonitorSdoXlDO> pagedResult = monitorSdoXlService.findAll(searchInput);
		List<MonitorSdoXlDO> monitorSdoList = pagedResult.getContent();				
		List<MonitorSdoXlDTO> dtos = conversionService.convertAllTo(monitorSdoList, MonitorSdoXlDTO.class);
		MonitorSdoPaginDTO pagination = new MonitorSdoPaginDTO();
		pagination.setData(dtos);
		pagination.setTotalPages(pagedResult.getTotalPages());
		pagination.setCount(pagedResult.getTotalElements());
		return OperationResult.success(pagination);		
	}
	
	@Override
	@PostMapping("/search")
	@ResponseBody
	public OperationResult<MonitorSdoPaginDTO> filterData(@RequestBody BaseSearchInput searchInput) {
		Page<MonitorSdoXlDO> pagedResult = monitorSdoXlService.filterAll(searchInput);
		List<MonitorSdoXlDO> monitorSdoList = pagedResult.getContent();	
		List<MonitorSdoXlDTO> dtos = conversionService.convertAllTo(monitorSdoList, MonitorSdoXlDTO.class);
		
		if(dtos != null && !dtos.isEmpty()) {
			List<TabgenValue> esiti = getTabgenValues("FLXSDO_ERRORE");
			List<TabgenValue> trasmissioni = getTabgenValues("FLXSDO_STATO_TRAS");
			
			for (MonitorSdoXlDTO dto : dtos) {
				dto.setEsito(esiti.stream().filter(esito -> esito.getField2().equals(dto.getEsito() + "")).findFirst().orElse(new TabgenValue()).getField3());
				dto.setTrasmissione(trasmissioni.stream().filter(trasmissione -> trasmissione.getField2().equals(dto.getTrasmissione() + "")).findFirst().orElse(new TabgenValue()).getField3());
			}
		}
		
		MonitorSdoPaginDTO pagination = new MonitorSdoPaginDTO();
		pagination.setData(dtos);
		pagination.setTotalPages(pagedResult.getTotalPages());
		pagination.setCount(pagedResult.getTotalElements());
		
		if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogVisuaMonitorSDO())) {
    		MonitorSdoPaginDTO resultsLogAccessi = monitorSdoXlService.sendAuditVisuaMonitorSDOToPM(searchInput, pagination);
        }
    	
		return OperationResult.success(pagination);		
	}
	
	private List<TabgenValue> getTabgenValues(String id) {
		TabgenValueFilter tabGenValueFilter = new TabgenValueFilter();	
		tabGenValueFilter.setTabgenId(id);
		BasePagingLoadResult<Tabgen> results = tabgenDelegate.searchValue(tabGenValueFilter);
		List<TabgenValue> values = new ArrayList<TabgenValue>();
		if(results != null && results.getList() != null && !results.getList().isEmpty()) {
			values = results != null && results.getList() != null && !results.getList().isEmpty() ? results.getList().get(0).getTabgenValues() : new ArrayList<TabgenValue>();
			if(values == null) {
				values = new ArrayList<TabgenValue>();
			}
		}
		return values;
	}
	
	@Override
	@PostMapping("/errors")
	@ResponseBody
	public OperationResult<MonitorSdoErrorPaginDTO> getAllErrors(@RequestBody BaseSearchInput searchInput) throws SQLException{
		Page<MonitorSdoXlErrorDTO> monitorSdoErrorPage = monitorSdoXlService.reatriveAllErrorsByFlussoAndNosologico(searchInput);
		List<MonitorSdoXlErrorDTO> monitorSdoErrorList = monitorSdoErrorPage.getContent();	
		MonitorSdoErrorPaginDTO pagination = new MonitorSdoErrorPaginDTO();
		pagination.setData(monitorSdoErrorList);
		pagination.setTotalPages(monitorSdoErrorPage.getTotalPages());
		pagination.setCount(monitorSdoErrorPage.getTotalElements());
		return OperationResult.success(pagination);		
	}
	
	@PostMapping("/getAllFlusso")
	@ResponseBody
	@Override
	public SearchOperationResult<FormFlowDTO> getAllFlusso(@RequestBody BaseSearchInput searchInput) {	
		searchInput.setValue("type", "AZIENDA");		
        Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFiltered(searchInput, true);
        return SearchOperationResult.success(searchResults.getFirst(), searchResults.getSecond());	
	}
	
	@Override
	@PostMapping(value = "/_downloadXml", produces = "application/zip", consumes = "application/json")
	@ResponseBody
	public HttpEntity<byte[]> downloadXML(@RequestBody BaseSearchInput searchInput) throws IOException{
		String fileName = "nosologico";
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Disposition", "attachment; filename=" + searchInput.getParam("nosologico") + ".zip");
		byte[] xmlExport = monitorSdoXlService.xmlExport(searchInput);
		
		File file = File.createTempFile(fileName + "_","FILE");
		File file2 = new File(file.getParent()+"\\"+fileName+"_"+searchInput.getParam("nosologico"));
		file.renameTo(file2);
		Files.write(xmlExport, file2);
		byte[] zipbytes = AnagraficaAssistitoUtility.zipBytes(file2.getName()+".xml", xmlExport);
		
		if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogDownMonitorSDO())) {
			byte[] resultsLogAccessi = monitorSdoXlService.sendAuditDownMonitorSDOToPM(searchInput, zipbytes);
        }
    	
		return new HttpEntity<byte[]>( zipbytes, header);
	}
	
	
}
