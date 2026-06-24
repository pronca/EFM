package it.eng.care.domain.flow.drools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.SearchFlowPatientFilter;
import it.eng.care.domain.flow.core.dto.SearchPatientDTO;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.service.FlowPatientService;
import it.eng.care.domain.flow.core.service.TwoLevelResultsService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;
import it.eng.care.domain.flow.drools.service.api.RuleManagerService;
import it.eng.care.domain.flow.drools.utility.api.LookupUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddRule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestAddRule.class);

	@Autowired
	private LookupUtility lookupUtility;

	@Autowired
	private FlowValidator flowValidator;
	
	@Autowired
	private RuleManagerService ruleManagerService;
	
	@Autowired
	private TwoLevelResultsService twoLevelResultsService;
	
	@Autowired
	private FlowPatientService flowPatientController;

	@Test
	public void loadLookup() {
		lookupUtility.loadLookup("TEST_TABGEN");
		lookupUtility.loadLookup("COMUNI", "COMUNI_LOMBARDIA", new String[][] { { "COM_COD_REG", "030" } }, null);
		lookupUtility.lookupExists("TEST_TABGEN", new String[][] { { "campo2", "CAMPO3_IN2" } }, null);

	}
	
	@Test
	public void loadLookupsQL() {
		lookupUtility.loadLookupBySql(	"CODICEAZIENDA,CODICEPRESIDIO,PROGRESSIVOSDO", 
				"FM_FLOW_SDOIOR_1", 
				new String[]{"MONTH_RIF","YEAR_RIF"},
				new String[]{"11","2019"},
				"SDOIOR");
		
		lookupUtility.sqlLookupExists("SDOIOR", new String[] {	"960","80960","18014832" });
		
		
	}
	
	@Test
	public void addRuleSDO() throws IOException {

		File file1 = new File("\\drools\\SDO\\20.10.11\\sdo_1.drl");
		if(file1.exists())
		ruleManagerService.addRule("0ebc5623-7044-4e5c-91b1-647a4141256d", "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4", file1, "sdo1.drl",null);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_2.drl");
		if(file1.exists())
		ruleManagerService.addRule("0ebc5623-7044-4e5c-91b1-647a4141256d", "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4", file1, "sdo1.drl",null);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_3.drl");
		if(file1.exists())
		ruleManagerService.addRule("0ebc5623-7044-4e5c-91b1-647a4141256d", "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4", file1, "sdo1.drl",null);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_4.drl");
		if(file1.exists())
		ruleManagerService.addRule("0ebc5623-7044-4e5c-91b1-647a4141256d", "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4", file1, "sdo1.drl",null);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_5.drl");
		if(file1.exists())
		ruleManagerService.addRule("0ebc5623-7044-4e5c-91b1-647a4141256d", "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4", file1, "sdo1.drl",null);

	}
	
	@Test
	public void updateRuleSDO() throws IOException {

		File file1 = new File("\\drools\\SDO\\20.10.11\\sdo_1.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("2aba0d75-9270-492f-9cc6-8fec5bc1664a", file1);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_2.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("55b62156-ca52-42e9-946e-c2432a3becef", file1);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_3.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("55b62156-ca52-42e9-946e-c2432a3becef", file1);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_4.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("55b62156-ca52-42e9-946e-c2432a3becef", file1);
		
		file1 = new File("\\drools\\SDO\\20.10.11\\sdo_5.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("d379cd56-247c-451b-98ec-3d8c5f90a85d", file1);

	}
	
	@Test
	public void addRuleSDOIor() throws IOException {

		//ruleManagerService.deleteAllRules("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf");
		
		ruleManagerService.addRule("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf", 
				new File("drools\\SDOIOR\\test2\\sdoior_1_FC.drl"), "sdo1.drl",null);
		
		
		//String pippo= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		//ruleManagerService.addRule("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf", pippo.getBytes());
		
		/*file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_2.drl");
		if(file1.exists())
		ruleManagerService.addRule("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_3.drl");
		if(file1.exists())
		ruleManagerService.addRule("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_4.drl");
		if(file1.exists())
		ruleManagerService.addRule("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_5.drl");
		if(file1.exists())
		ruleManagerService.addRule("f5115d5e-9a53-4125-bba5-cb133ce7417c", "c299ad8c-012e-471c-8c0c-ac721fe242cf", file1);*/

	}
	
	@Test
	public void updateRuleSDOIor() throws IOException {

		File file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_1.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("2aba0d75-9270-492f-9cc6-8fec5bc1664a", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_2.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("55b62156-ca52-42e9-946e-c2432a3becef", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_3.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("55b62156-ca52-42e9-946e-c2432a3becef", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_4.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("55b62156-ca52-42e9-946e-c2432a3becef", file1);
		
		file1 = new File("\\drools\\SDOIOR\\versioneditest\\sdoior_5.drl");
		if(file1.exists())
		ruleManagerService.updateRuleFile("d379cd56-247c-451b-98ec-3d8c5f90a85d", file1);

	}
	
	@Test
	public void getRules() throws IOException {
		String flowId = "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4";
		String versionId = "0ebc5623-7044-4e5c-91b1-647a4141256d";
		ruleManagerService.getRules(flowId, versionId);
	}


	@Test
	public void fireRule() throws Exception {

		String flowId = "c299ad8c-012e-471c-8c0c-ac721fe242cf";
		String versionId = "f5115d5e-9a53-4125-bba5-cb133ce7417c";

		ValidationFilter request = new ValidationFilter();
		request.setFlowId(flowId);
		request.setVersionId(versionId);
		//request.setImportId("c3baf31f-84d9-4219-99e9-e955000a2d7b");
		//request.setParam(new String[]{"108","100","17019890"});
		FlowViewFilterField[] fields = new FlowViewFilterField[3];

		FlowViewFilterField f1 = new FlowViewFilterField();
		f1.setName("codiceazienda");
		f1.setSection(0);
		fields[0] = f1;
		
		FlowViewFilterField f2 = new FlowViewFilterField();
		f2.setName("codicepresidio");
		f2.setSection(0);
		fields[1] = f2;
		
		FlowViewFilterField f3 = new FlowViewFilterField();
		f3.setName("progressivosdo");
		f3.setSection(0);
		fields[2] = f3;
		
		//request.setField(fields);

		flowValidator.resetValidation(request);
		flowValidator.executeValidation(request, false,null);
		System.out.println("");

	}

	@Test
	public void startupContext() throws InterruptedException {
		while (true)
			Thread.sleep(1000);
	}
	
	@Test
	public void testLoadFileRegionali() throws Exception {
		FlowOperationResult<Boolean> result = twoLevelResultsService.uploadResults(new File("ritorniregione.zip"), "6c3cc5c2-66d2-4692-8b7b-6140cfdde83c", "0ebc5623-7044-4e5c-91b1-647a4141256d", "", "",false,true);
		if(!result.getSuccess()) {
			System.out.println(result.getMessage());
		}
	}
	
	public static void main(String[] args) {
		File file  = new File("SDOIOR_REG_SCARTI_REGIONE.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < 40000; i++) {
		    writer.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		    writer.newLine();
			}
		    writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchPatient() {
		SearchPatientDTO patient = new SearchPatientDTO();
		patient.setName(" ");
		patient.setSurname(" ");
		patient.setTaxCode(" ");
		//patient.setBirthDate(new Date());
		List<SearchPatientDTO> list = flowPatientController.searchPatientOccurrences(patient);
	}
	
	@Test
	public void testSearchPatient2() {
		SearchFlowPatientFilter patient = new SearchFlowPatientFilter();
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("NOME", " ");
		filters.put("COGNOME", " ");
		filters.put("CODICEFISCALE", " ");
		filters.put("MESE", "01");
		filters.put("ANNO", "2018");
		filters.put("CODICEAZIENDA", "960");
		filters.put("CODICEPRESIDIO", "80960");
		patient.setFilters(filters);
//		patient.setFlowName("SDOIOR");
//		SearchPatientDTO list = flowPatientController.searchFlowPatient(patient);
	}

}
