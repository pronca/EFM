package it.eng.care.domain.flow.core.controller.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.core.config.LogAccessiPMConfig;
import it.eng.care.domain.flow.core.controller.FlowViewController;
import it.eng.care.domain.flow.core.dao.SearchPatientFieldDAO;
import it.eng.care.domain.flow.core.dto.PaginatedPraticaDTO;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.PrivacyManagerDTO.PMFlowView;
import it.eng.care.domain.flow.core.entity.SearchPatientFieldDO;
import it.eng.care.domain.flow.core.service.FlowManagerProfileService;
import it.eng.care.domain.flow.core.service.FlowViewService;
import it.eng.care.domain.flow.core.service.PraticaViewService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;

@RestController
@RequestMapping("/fm/FlowViewDTO")
public class FlowViewControllerImpl implements FlowViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowViewControllerImpl.class);

    @Autowired
    private FlowViewService flowViewService;

    @Autowired
    private PraticaViewService praticaViewService;
    
    @Autowired
    private FlowManagerProfileService flowManagerProfileService;
    
    @Autowired
    private SearchPatientFieldDAO searchPatientFieldDAO;
    
    @Autowired
    private LogAccessiPMConfig logAccessiPMConfig;
    
    @Override
    @PostMapping("/searchTables")
    @ResponseBody
    public OperationResult<List<Object>> searchTables(@RequestBody FlowViewFilter flowViewFilter) {
    	    	
    	FormFlowDTO flow = flowViewFilter.getFlow();
    	if(!flowManagerProfileService.checkFlowById(flow.getId())) {
    		return OperationResult.failure();
    	}
    	
    	HashMap<String,String> campiPraticaSubjectGen = new HashMap<String,String>();
    	
    	//caricamento lista aziende visibili dall'utente
    	List<String> aziende = flowManagerProfileService.getAziendeForUserProfile();
    	List<SearchPatientFieldDO>  fmSearchPatientFieldsTabgen = new ArrayList<>();
    	if (!aziende.isEmpty()) {
	    	fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByFlussoAndFiltroRicercaAndCodiceAziendaIn(flowViewFilter.getFlow().getName(), "1", aziende);
	    	//se non è stata fatta una configurazione specifica per azienda allora prendo la configurazione generica del flusso con azienda a null e che quindi vale per tutte le aziende
			if (fmSearchPatientFieldsTabgen.isEmpty()) {
				fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByFlussoAndFiltroRicercaAndCodiceAziendaIsNull(flowViewFilter.getFlow().getName(), "1");
			}
    	} else {
    		fmSearchPatientFieldsTabgen = searchPatientFieldDAO.findAllByFlussoAndFiltroRicerca(flowViewFilter.getFlow().getName(), "1");
    	}
    	for (SearchPatientFieldDO patientFields : fmSearchPatientFieldsTabgen) {
			campiPraticaSubjectGen.put(patientFields.getCampoFunzione(), patientFields.getCampoTracciato());
	}
    	
        PMFlowView PMresults = flowViewService.executeQuery(flowViewFilter, campiPraticaSubjectGen);
        if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogVisuaFlusso())) {
    		PMFlowView resultsLogAccessi = flowViewService.sendAuditVisuaFlussiToPM(flowViewFilter, campiPraticaSubjectGen, PMresults);
        }
        
        List<Object> results = PMresults.getFlowViewReturn();
        
        if(results != null && !results.isEmpty()) {
        	if(results.get(0) instanceof HashMap) {
        		HashMap<String, List<Object>> hashMap = (HashMap<String, List<Object>>)results.get(0);
        		for(String sec : hashMap.keySet()) {
        			List<Object> rows = hashMap.get(sec);
        			if(rows != null && !rows.isEmpty()) {
        				for (Object row : rows) {
							if(row instanceof Object[]) {
								Object[] r = (Object[]) row;
								for(int i = 0; i < r.length; i++) {
									Object field = r[i];
									if(field instanceof String) {
										field = formatDate((String)field);
										r[i] = field;
									}
								}
							}
						}
        			}
        			
        		}
        	}
        }
        
        return OperationResult.success(results);

    }
    
    private String formatDate(String date) {
    	if(!StringUtils.isEmpty(date)) {
    		
    		String pattern = "";
        	String patternSrc = "";
        	String patternDest = "dd/MM/yyyy";
        	String hh = " HH:mm";
        	
    		if(Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}.*", date)) {
    			pattern = "yyyy-MM-dd";
    			patternSrc = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    		} else if(Pattern.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}.*", date)) {
    			pattern = "yyyy/MM/dd";
    			patternSrc = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
    		} else if(Pattern.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}.*", date)) {
    			pattern = "dd-MM-yyyy";
    			patternSrc = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
    		} else if(Pattern.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}.*", date)) {
    			pattern = "dd/MM/yyyy";
    			patternSrc = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    		}
    		
    		if(Pattern.matches(patternSrc + "T[0-9]{2}:[0-9]{2}.*Z", date)) {
    			pattern += "'T'HH:mm:ss.SSS'Z'";
    			patternDest += " HH:mm";
    		} else if(Pattern.matches(patternSrc + "T[0-9]{2}:[0-9]{2}.*", date)) {
    			pattern += "'T'HH:mm:ss.SSS";
    			patternDest += " HH:mm";
    		} else if(Pattern.matches(patternSrc + " [0-9]{2}:[0-9]{2}.*", date)) {
    			pattern += hh;
    			patternDest += hh;
    		}
    		
    		if(!pattern.equals("")) {
    			try {
					date = new SimpleDateFormat(patternDest).format(new SimpleDateFormat(pattern).parse(date));
				} catch (ParseException e) {}
    		}
    		
    	}
    	return date;
    }
    
    @Override
    @PostMapping("/countTables")
    @ResponseBody
    public OperationResult<List<Object>> countTables(@RequestBody FlowViewFilter flowViewFilter) {
    	
    	FormFlowDTO flow = flowViewFilter.getFlow();
    	if(!flowManagerProfileService.checkFlowById(flow.getId())) {
    		return OperationResult.failure();
    	}

    	List<Object> result = flowViewService.executeQueryCount(flowViewFilter);

        return OperationResult.success(result);

    }
    
    @Override
    @PostMapping("/getVersionNameById")
    @ResponseBody
    public OperationResult<String> getVersionNameById(@RequestBody BaseSearchInput searchInput) {

        String versionId = searchInput.getValue("version");

        String result = flowViewService.getVersion(versionId);

        return OperationResult.success(result);

    }
    
    @Override
    @PostMapping("/getErrors")
    @ResponseBody
    public OperationResult<PaginatedPraticaDTO> getErrors(@RequestBody FlowViewFilterError flowViewFilterError) {

    	PaginatedPraticaDTO results = praticaViewService.searchErrors(flowViewFilterError);
        return OperationResult.success(results);

    }

    @Override
    @PostMapping(value = "/_downloadXlsx", produces = "application/zip", consumes = "application/json")
    //@NoAuthenticationRequired
    @ResponseBody
    public HttpEntity<byte[]> downloadFlowViewXlsx(@RequestBody FlowViewFilter flowViewFilter) throws IOException {

    	
    	Boolean check = flowManagerProfileService.checkFlowByName(flowViewFilter.getFlow().getName());
    	if(!check) {
    		return null;
    	}
    	
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Disposition", "attachment; filename=" + flowViewFilter.getFlow().getName() + ".zip");
        byte[] bytes = flowViewService.downloadFlowViewXlsx(flowViewFilter);
        if (logAccessiPMConfig != null && "1".equals(logAccessiPMConfig.getAccessLogDownFlusso())) {
    		byte[] resultsLogAccessi = flowViewService.sendAuditDownDatiFlussiToPM(flowViewFilter, bytes);
        }
        
        byte[] zipbytes = zipBytes("Pratiche_Flusso" + flowViewFilter.getFlow().getName() + ".xlsx", bytes);
        return new HttpEntity<byte[]>(zipbytes, header);
    }

    public static byte[] zipBytes(String filename, byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry(filename);
        entry.setSize(input.length);
        zos.putNextEntry(entry);
        zos.write(input);
        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
    }

}
