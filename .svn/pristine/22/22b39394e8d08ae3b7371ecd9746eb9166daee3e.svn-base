package it.eng.care.domain.flow.core.controller.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.anagraficaassistito.utility.AnagraficaAssistitoUtility;
import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.controller.AnagraficaAssistitoController;
import it.eng.care.domain.flow.core.converter.AnagraficaAssistito.AnagraficaAssistitoDOtoAnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.converter.AnagraficaAssistito.AnagraficaAssistitoDTOtoAnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.dto.AnagrafcaAssistitoPaginationDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDownloadDTO;
import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.service.AnagraficaAssistitoService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.platform.tool.transport.conversion.ConversionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

@RestController
@RequestMapping("/fm/AnagraficaAssistitoDTO")
public class AnagraficaAssistitoControllerImpl implements AnagraficaAssistitoController {
	
	private static final Logger logger = LoggerFactory.getLogger(AnagraficaAssistitoUtility.class);
	
	@Autowired
	private AnagraficaAssistitoService anagraficaAssistatoService;
	
	@Autowired
    private ConversionService conversionService;
	
	@Autowired
	private AnagraficaAssistitoDOtoAnagraficaAssistitoDTO anagraficaAssistitoDOtoAnagraficaAssistitoDTO;
	
	@Autowired
	private AnagraficaAssistitoDTOtoAnagraficaAssistitoDO anagraficaAssistitoDTOtoAnagraficaAssistitoDO;
	
	@Autowired
	private TabgenService tabgenService;
	
	@Autowired
	private LogAccessiPMConfig logAccessiPMConfig;
	
	@PostConstruct
	public void post() {
		conversionService.registerConverter(AnagraficaAssistitoDO.class, AnagraficaAssistitoDTO.class, anagraficaAssistitoDOtoAnagraficaAssistitoDTO);
		conversionService.registerConverter(AnagraficaAssistitoDTO.class, AnagraficaAssistitoDO.class, anagraficaAssistitoDTOtoAnagraficaAssistitoDO);
	}
	
	@Override
	@PostMapping("/_search")
    @ResponseBody
	public OperationResult<AnagrafcaAssistitoPaginationDTO> search(@RequestBody BaseSearchInput searchInput) {
		String selectedOption = (String) searchInput.getParam("selectedOption");
		Object filterValueObj = searchInput.getParam("filterValue");
		String filterValue = "";
		if (filterValueObj instanceof String) {
			filterValue = (String) searchInput.getParam("filterValue");
		}
		
		TabgenValueFilter filter = new TabgenValueFilter();
		if (selectedOption != null && !selectedOption.isEmpty() && filterValue!=null && !filterValue.isEmpty()) {
			if (selectedOption.toUpperCase().equals("NAZIONALITA")) {
				filter.setTabgenId("NAZIONALITA");
				if (filterValue.indexOf("%") < 0) {
					filter.setField2(filterValue.toUpperCase()+"%");
				} else {
					filter.setField2(filterValue.toUpperCase());
				}
			} else if (selectedOption.toUpperCase().equals("COMUNERESIDENZA") || selectedOption.toUpperCase().equals("COMUNENASCITA")) {
				filter.setTabgenId("COMUNI");
				if (filterValue.indexOf("%") < 0) {
					filter.setField5(filterValue.toUpperCase()+"%");
				} else {
					filter.setField5(filterValue.toUpperCase());
				}
			} else if (selectedOption.toUpperCase().equals("ASLRESIDENZA")) {
				filter.setTabgenId("AZIENDE_SANITARIE");
				if (filterValue.indexOf("%") < 0) {
					filter.setField4(filterValue.toUpperCase()+"%");
				} else {
					filter.setField4(filterValue.toUpperCase());
				}
			} 
			if (filter.getTabgenId()!=null) {
				List<TabgenValueDO> tabgenValueFlussi = tabgenService.searchTabgenValueByFilter(filter);
				if(tabgenValueFlussi != null && !tabgenValueFlussi.isEmpty()) {
					searchInput.setParam("filterValue", tabgenValueFlussi.stream().map(TabgenValueDO::getId).collect(Collectors.toList()));
				}
			}
		}
		Page<AnagraficaAssistitoDO> pageTmp = anagraficaAssistatoService.retrieveAllFiltered(searchInput);
		List<AnagraficaAssistitoDTO> dtos = conversionService.convertAllTo(pageTmp.getContent(), AnagraficaAssistitoDTO.class);
		AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO = new AnagrafcaAssistitoPaginationDTO();
		anagrafcaAssistitoPaginationDTO.setAnagraficaData(dtos);		
		anagrafcaAssistitoPaginationDTO.setAllPages(pageTmp.getTotalPages());
		logger.info("TotalPages:: "+pageTmp.getTotalPages());
		
		anagraficaAssistatoService.loadTabgenInAnagrafica(anagrafcaAssistitoPaginationDTO);
		if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogRicAnagAssistito())) {
    		AnagrafcaAssistitoPaginationDTO resultsLogAccessi = anagraficaAssistatoService.sendAuditRicAnaAssToPM(searchInput, anagrafcaAssistitoPaginationDTO);
        }
		
		return OperationResult.success(anagrafcaAssistitoPaginationDTO);
	}
	
	
	
	@Override
	@PostMapping("/searchAnagrafica")
	@ResponseBody
	public SearchOperationResult<AnagraficaAssistitoDTO> checkAnagraficaAssistitoData(@RequestBody BaseSearchInput searchInput) {
		List<AnagraficaAssistitoDO> data = anagraficaAssistatoService.checkAnagraficaAssistitoData(searchInput);
		List<AnagraficaAssistitoDTO> dtos = conversionService.convertAllTo(data, AnagraficaAssistitoDTO.class);
		return SearchOperationResult.success(dtos);
	}
	
	@Override
	@PostMapping("/export")
	@ResponseBody
	public SearchOperationResult<AnagraficaAssistitoDTO> exportAnagraficaAssistitoData(@RequestBody BaseSearchInput searchInput) {
		
		List<AnagraficaAssistitoDO> data = anagraficaAssistatoService.exportAllData(searchInput);
		List<AnagraficaAssistitoDTO> dtos = conversionService.convertAllTo(data, AnagraficaAssistitoDTO.class);
		return SearchOperationResult.success(dtos);
	}
    	
	@Override
	@PostMapping
	public SaveOperationResult<AnagraficaAssistitoDTO> save(@RequestBody AnagraficaAssistitoDTO anagraficaAssistitoDTO) {		
		AnagraficaAssistitoDO loValue = conversionService.convertTo(anagraficaAssistitoDTO, AnagraficaAssistitoDO.class);		
		if(loValue.getId() == null) {
			loValue = anagraficaAssistatoService.createEntity(loValue);
		} else {
			loValue = anagraficaAssistatoService.updateEntity(loValue);
		}		
		AnagraficaAssistitoDTO outputDto = conversionService.convertTo(loValue, AnagraficaAssistitoDTO.class);
		return SaveOperationResult.success(outputDto);
	}

	@Override
	@GetMapping("/{entityKeyName}/{entityKeyValue}")
	public OperationResult<AnagraficaAssistitoDTO> getEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {		
		AnagraficaAssistitoDO loValue = anagraficaAssistatoService.getEntityById(entityKeyValue);	
		AnagraficaAssistitoDTO loDto = conversionService.convertTo(loValue, AnagraficaAssistitoDTO.class);
		
		return OperationResult.success(loDto);
	}

	@Override
	@DeleteMapping("/{entityKeyName}/{entityKeyValue}")
	public void deleteEntityBy(@PathVariable("entityKeyName") String entityKeyName, @PathVariable("entityKeyValue") String entityKeyValue) {	
		anagraficaAssistatoService.deleteEntity(entityKeyValue);		
	}

	@Override
	@PostMapping("/pagin")
	public OperationResult<AnagrafcaAssistitoPaginationDTO> loadAnagraficaAssistitoPage(@RequestBody BaseSearchInput searchInput) {
	
		Page<AnagraficaAssistitoDO> pageTmp = anagraficaAssistatoService.findAll(searchInput);	
		List<AnagraficaAssistitoDO> anagraficaList = pageTmp.getContent();				
		List<AnagraficaAssistitoDTO> dtos = conversionService.convertAllTo(anagraficaList, AnagraficaAssistitoDTO.class);
		
		AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO = new AnagrafcaAssistitoPaginationDTO();
		anagrafcaAssistitoPaginationDTO.setAnagraficaData(dtos);
		anagrafcaAssistitoPaginationDTO.setAllPages(pageTmp.getTotalPages());
		
		anagraficaAssistatoService.loadTabgenInAnagrafica(anagrafcaAssistitoPaginationDTO);
		String sortDir = searchInput.getValue("sortDir");
		String sortField = searchInput.getValue("sortField");
		if (sortDir != null && !sortDir.isEmpty() && sortField != null && !sortField.isEmpty() && anagrafcaAssistitoPaginationDTO.getAnagraficaData()!=null && !anagrafcaAssistitoPaginationDTO.getAnagraficaData().isEmpty()) {
			List<AnagraficaAssistitoDTO> anagraficaAssistitoDTOSorted = new ArrayList<AnagraficaAssistitoDTO>();
			Set<String> itemToSort = new HashSet<String>();
			itemToSort.add("comunenascita");
			itemToSort.add("comuneresidenza");
			itemToSort.add("nazionalita");
			itemToSort.add("aslresidenza");
			if (itemToSort.contains(sortField.toLowerCase())) {
				if (sortDir.toLowerCase().equals("desc")) {
					Comparator<AnagraficaAssistitoDTO> descComparator = null;
					if (sortField.toLowerCase().equals("comunenascita")) {
						descComparator = Comparator.comparing(AnagraficaAssistitoDTO::getComunenascitaDescriptionField, Comparator.nullsLast(Comparator.reverseOrder()));
					} else if (sortField.toLowerCase().equals("comuneresidenza")) {
						descComparator = Comparator.comparing(AnagraficaAssistitoDTO::getComuneResidenzaDescriptionField, Comparator.nullsLast(Comparator.reverseOrder()));
					} else if (sortField.toLowerCase().equals("nazionalita")) {
						descComparator = Comparator.comparing(AnagraficaAssistitoDTO::getNazionalitaDescriptionField, Comparator.nullsLast(Comparator.reverseOrder()));
					} else if (sortField.toLowerCase().equals("aslresidenza")) {
						descComparator = Comparator.comparing(AnagraficaAssistitoDTO::getAslResidenzaDescriptionField, Comparator.nullsLast(Comparator.reverseOrder()));
					}
					if (descComparator!=null) {
						anagraficaAssistitoDTOSorted = anagrafcaAssistitoPaginationDTO.getAnagraficaData().stream().sorted(descComparator).collect(Collectors.toList());
					}
				} else {
					Comparator<AnagraficaAssistitoDTO> ascComparator = null;
					if (sortField.toLowerCase().equals("comunenascita")) {
						ascComparator = Comparator.comparing(AnagraficaAssistitoDTO::getComunenascitaDescriptionField, Comparator.nullsLast(Comparator.naturalOrder()));
					} else if (sortField.toLowerCase().equals("comuneresidenza")) {
						ascComparator = Comparator.comparing(AnagraficaAssistitoDTO::getComuneResidenzaDescriptionField, Comparator.nullsLast(Comparator.naturalOrder()));
					} else if (sortField.toLowerCase().equals("nazionalita")) {
						ascComparator = Comparator.comparing(AnagraficaAssistitoDTO::getNazionalitaDescriptionField, Comparator.nullsLast(Comparator.naturalOrder()));
					} else if (sortField.toLowerCase().equals("aslresidenza")) {
						ascComparator = Comparator.comparing(AnagraficaAssistitoDTO::getAslResidenzaDescriptionField, Comparator.nullsLast(Comparator.naturalOrder()));
					}
					if (ascComparator!=null) {
						anagraficaAssistitoDTOSorted = anagrafcaAssistitoPaginationDTO.getAnagraficaData().stream().sorted(ascComparator).collect(Collectors.toList());
					}				
				}
				if (anagraficaAssistitoDTOSorted!=null && !anagraficaAssistitoDTOSorted.isEmpty()) {
					anagrafcaAssistitoPaginationDTO.setAnagraficaData(anagraficaAssistitoDTOSorted);
				}
			}
		}
		
		if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogVisuaAnagAssistito())) {
    		AnagrafcaAssistitoPaginationDTO resultsLogAccessi = anagraficaAssistatoService.sendAuditVisuaAnaAssToPM(searchInput, anagrafcaAssistitoPaginationDTO);
        }
		
		return OperationResult.success(anagrafcaAssistitoPaginationDTO);
	}

	@Override
	@PostMapping(path = "/_import")
    @ResponseBody
    public OperationResult<String> fileUploadAnagraficaAssistito(
    		@RequestHeader(name = "fileName", defaultValue = "unknown") String fileName,
            @RequestHeader(name = "fileType", defaultValue = MediaType.APPLICATION_OCTET_STREAM_VALUE) String fileType,
            @RequestBody byte[] bytes
            ){
		
		FileOutputStream fos = null;
		File file = null;
		String res = null;
		try{
			file = File.createTempFile(fileName, "");
			fos = new FileOutputStream(file);
			fos.write(bytes);
			res =  anagraficaAssistatoService.handleExcelUpload(file);
			
		}catch (Exception e) {
			LogUtil.logException(logger, "", e);
		}finally {
			try {
				if(fos!=null)
					fos.close();
				
				if(file!=null && file.exists())
					file.delete();
				
			} catch (IOException e) {
				LogUtil.logException(logger, "", e);
//				e.printStackTrace();
			}			
		}
	
		return OperationResult.success(res);	
	}

	@Override
	@PostMapping(value = "/_downloadXlsx", produces = "application/zip", consumes = "application/json")
    @ResponseBody
	public HttpEntity<byte[]> downloadFlowViewXlsx(@RequestBody AnagraficaAssistitoDownloadDTO anagraficaAssistitoDownloadDTO) throws IOException {
		List<AnagraficaAssistitoDTO> dtosTmp = Arrays.asList(anagraficaAssistitoDownloadDTO.getAnagraficaData());
		String selectedOption = anagraficaAssistitoDownloadDTO.getSelectedOption();
		String filterValue = anagraficaAssistitoDownloadDTO.getFilterValue();
		
		for(AnagraficaAssistitoDTO asDTO : dtosTmp) {
			asDTO.setNazionalita(asDTO.getNazionalitaDescriptionField());
			asDTO.setComunenascita(asDTO.getComunenascitaDescriptionField());
			asDTO.setComuneResidenza(asDTO.getComuneResidenzaDescriptionField());
			asDTO.setAslResidenza(asDTO.getAslResidenzaDescriptionField());
		}
		
		List<AnagraficaAssistitoDO> dtos = conversionService.convertAllTo(dtosTmp, AnagraficaAssistitoDO.class);
		
		String name =	"AnagraficaAssistito_Sheet";
		HttpHeaders header = new HttpHeaders();
        String filename =  name+".zip";
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
            .filename(filename)
            .build();
        header.setContentDisposition(contentDisposition);
        byte[] bytes = anagraficaAssistatoService.downloadAnagraficaAssistitoXlsx(dtos);
        byte[] zipbytes = AnagraficaAssistitoUtility.zipBytes("Anagrafica_Assistito_Data" + ".xls", bytes);
        
        if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogDownAnagAssistito())) {
    		byte[] resultsLogAccessi = anagraficaAssistatoService.sendAuditDownAnaAssFileUplToPM(dtos, selectedOption, filterValue, zipbytes);
        }
        
		return new HttpEntity<byte[]>( zipbytes, header);
	}
	
	/*
	 * public static byte[] zipBytes(String filename, byte[] input) throws
	 * IOException { ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * ZipOutputStream zos = new ZipOutputStream(baos); ZipEntry entry = new
	 * ZipEntry(filename); entry.setSize(input.length); zos.putNextEntry(entry);
	 * zos.write(input); zos.closeEntry(); zos.close(); return baos.toByteArray(); }
	 */
}
