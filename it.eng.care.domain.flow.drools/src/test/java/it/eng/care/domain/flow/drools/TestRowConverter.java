package it.eng.care.domain.flow.drools;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.service.FlowViewService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.utility.api.RowConverter;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TestRowConverter {

	@Autowired
	private RowConverter rowConverter;
	
	@Autowired
	private FlowViewService flowViewService;
	
	@Autowired
	private FormFlowService formFlowService;

	@Test
	public void testConveterRow() {
		ValidationBean bean = loadRows();
	}
	
	
	private ValidationBean loadRows() {
		String flowId = "c299ad8c-012e-471c-8c0c-ac721fe242cf";
		String versionId = "f5115d5e-9a53-4125-bba5-cb133ce7417c";
        
		ValidationFilter request = new ValidationFilter();
		request.setFlowId(flowId);
		request.setVersionId(versionId);
		//request.setImportId("c3baf31f-84d9-4219-99e9-e955000a2d7b");
		FlowViewFilterField[] fields = new FlowViewFilterField[3];

		FlowViewFilterField f1 = new FlowViewFilterField();
		f1.setName("codiceazienda");
		f1.setSection(0);
		f1.setParam("codiceAzienda");
		fields[0] = f1;
		
		FlowViewFilterField f2 = new FlowViewFilterField();
		f2.setName("codicepresidio");
		f2.setSection(0);
		f2.setParam("codicePresidio");
		fields[1] = f2;
		
		FlowViewFilterField f3 = new FlowViewFilterField();
		f3.setName("progressivosdo");
		f3.setSection(0);
		f3.setParam("2");
		fields[2] = f3;
		
		request.setField(fields);
		
		FormFlowDTO configuration = retrieveConfiguration(request.getFlowId(), request.getVersionId());
		List<FormFlowTableFieldDTO> pkListCfg = new ArrayList<FormFlowTableFieldDTO>();
		List<FormFlowTableFieldDTO> groupListCfg = new ArrayList<FormFlowTableFieldDTO>();
		String flowPrefix = "fm_flow_" + configuration.getName() + "_";
		getPkAndGroupFields(configuration, pkListCfg, groupListCfg, flowPrefix);
		
		List<Map<String, Object>> rows = flowViewService.searchForValidation(request, configuration, groupListCfg);
		ValidationBean bean = rowConverter.convertRows(rows, configuration, flowPrefix, pkListCfg);
		
		return bean;
	}
	
	private FormFlowDTO retrieveConfiguration(String flowId, String versionId) {
		BaseSearchInput searchInput = new BaseSearchInput();
		searchInput.setValue("flowId", flowId);
		searchInput.setValue("versionId", versionId);
		searchInput.setValue("isReferenceDate", false);
		Pair<List<FormFlowDTO>, SearchInfo> searchResults = formFlowService.retrieveAllFiltered(searchInput);
		FormFlowDTO configuration = searchResults.getFirst().get(0);
		return configuration;
	}
	
	private void getPkAndGroupFields(FormFlowDTO configuration, List<FormFlowTableFieldDTO> pkListCfg,
			List<FormFlowTableFieldDTO> groupListCfg, String flowPrefix) {
		List<FormFlowTableDTO> tableList = configuration.getFlowTableList();
		for (FormFlowTableDTO table : tableList) {
			for (FormFlowTableFieldDTO field : table.getFlowTableFieldList()) {
				field.setSectionName(flowPrefix + table.getSection());
				field.setSection(table.getSection());
				if (field.isPk()) {
					pkListCfg.add(field);
				}
				if (field.getGroups()) {
					groupListCfg.add(field);
				}
			}
		}

		pkListCfg.sort(new Comparator<FormFlowTableFieldDTO>() {

			@Override
			public int compare(FormFlowTableFieldDTO o1, FormFlowTableFieldDTO o2) {
				int c1 = o1.getSection().compareTo(o2.getSection());
				if (c1 != 0) {
					return c1;
				}

				return o1.getPosition().compareTo(o2.getPosition());
			}
		});

		groupListCfg.sort(new Comparator<FormFlowTableFieldDTO>() {

			@Override
			public int compare(FormFlowTableFieldDTO o1, FormFlowTableFieldDTO o2) {
				int c1 = o1.getSection().compareTo(o2.getSection());
				if (c1 != 0) {
					return c1;
				}

				return o1.getPosition().compareTo(o2.getPosition());
			}
		});

	}

}
