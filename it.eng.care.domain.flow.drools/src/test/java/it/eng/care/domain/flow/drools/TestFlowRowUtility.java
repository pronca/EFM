package it.eng.care.domain.flow.drools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.service.FlowViewService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.model.row.Row;
import it.eng.care.domain.flow.drools.model.row.ValidationBean;
import it.eng.care.domain.flow.drools.utility.api.FlowRowUtility;
import it.eng.care.domain.flow.drools.utility.api.RowConverter;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFlowRowUtility {
	
	@Autowired
	private FlowRowUtility flowRowUtility;
	
	@Autowired
	private FlowViewService flowViewService;
	
	@Autowired
	private FormFlowService formFlowService;
	
	@Autowired
	private RowConverter rowConverter;
	
	@Test
	public void testGetRow() {
		ValidationBean bean = loadRows();

		Row row = flowRowUtility.getRow(bean, 0, 4);
		assertTrue(row != null);
		
		row = flowRowUtility.getRow(bean, 0, 100);
		assertFalse(row != null);
		
		row = flowRowUtility.getRow(bean, 100, 100);
		assertFalse(row != null);
		
		row = flowRowUtility.getRow(bean, 100, 0);
		assertTrue(row != null);
		
		row = flowRowUtility.getRow(bean, 0, -1);
		assertFalse(row != null);
		
		row = flowRowUtility.getRow(bean, -1, 0);
		assertTrue(row != null);
		
		row = flowRowUtility.getRow(bean, null, null);
		assertFalse(row != null);
		
		row = flowRowUtility.getRow(new ValidationBean(), null, null);
		assertFalse(row != null);
		
		row = flowRowUtility.getRow(null, null, null);
		assertFalse(row != null);
		
	}

	@Test
	public void testGetFieldValue() {
		ValidationBean bean = loadRows();
		
		Object value = flowRowUtility.getFieldValue(bean, 0, 4, "ISTITUTODIMISSIONE");
		assertTrue(value != null);
		
		value = flowRowUtility.getFieldValue(bean, 0, 4, "ISTITUTODIMISSIONE1");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(bean, -1, 4, "ISTITUTODIMISSIONE");
		assertTrue(value != null);
		
		value = flowRowUtility.getFieldValue(bean, 0, -4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(bean, -1, -4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(bean, -1, -4, "ISTITUTODIMISSIONE1");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(bean, 0, 4, null);
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(null, 0, 4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(bean, null, 4, "ISTITUTODIMISSIONE");
		assertTrue(value != null);
		
		value = flowRowUtility.getFieldValue(bean, 0, -1, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(null, 0, 4, null);
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(new ValidationBean(), 0, 4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getFieldValue(new ValidationBean(), 0, 4, null);
		assertFalse(value != null);
	}

	@Test
	public void testGetField() {
		ValidationBean bean = loadRows();
		
		Object value = flowRowUtility.getField(bean, 0, 4, "ISTITUTODIMISSIONE");
		assertTrue(value != null);
		
		value = flowRowUtility.getField(bean, 0, 4, "ISTITUTODIMISSIONE1");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(bean, -1, 4, "ISTITUTODIMISSIONE");
		assertTrue(value != null);
		
		value = flowRowUtility.getField(bean, 0, -4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(bean, -1, -4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(bean, -1, -4, "ISTITUTODIMISSIONE1");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(bean, 0, 4, null);
		assertFalse(value != null);
		
		value = flowRowUtility.getField(null, 0, 4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(bean, null, 4, "ISTITUTODIMISSIONE");
		assertTrue(value != null);
		
		value = flowRowUtility.getField(bean, 0, -1, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(null, 0, 4, null);
		assertFalse(value != null);
		
		value = flowRowUtility.getField(new ValidationBean(), 0, 4, "ISTITUTODIMISSIONE");
		assertFalse(value != null);
		
		value = flowRowUtility.getField(new ValidationBean(), 0, 4, null);
		assertFalse(value != null);
	}

	@Test
	public void testFindRowIndexByFields() {
		ValidationBean bean = loadRows();
		
		List<Field> fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		
		List<Integer> indexList = flowRowUtility.findRowIndexByFields(bean, fields, 4);
		assertTrue(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowIndexByFields(bean, new ArrayList<Field>(), 4);
		assertTrue(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowIndexByFields(bean, new ArrayList<Field>(), 5);
		assertFalse(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowIndexByFields(bean, null, 4);
		assertTrue(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowIndexByFields(new ValidationBean(), null, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowIndexByFields(null, null, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		fields.add(new Field("NUMEROSCHEDA12121212", "17020493"));
		indexList = flowRowUtility.findRowIndexByFields(null, null, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowIndexByFields(bean, fields, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493XXX"));
		indexList = flowRowUtility.findRowIndexByFields(bean, fields, 2);
		assertFalse(indexList != null && indexList.size() > 0);
		
	}

	@Test
	public void testFindRowByFields() {
		ValidationBean bean = loadRows();
		
		List<Field> fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		
		List<Row> indexList = flowRowUtility.findRowByFields(bean, fields, 4);
		assertTrue(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowByFields(bean, new ArrayList<Field>(), 4);
		assertTrue(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowByFields(bean, new ArrayList<Field>(), 5);
		assertFalse(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowByFields(bean, null, 4);
		assertTrue(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowByFields(new ValidationBean(), null, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowByFields(null, null, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		fields.add(new Field("NUMEROSCHEDA12121212", "17020493"));
		indexList = flowRowUtility.findRowByFields(null, null, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		indexList = flowRowUtility.findRowByFields(bean, fields, 4);
		assertFalse(indexList != null && indexList.size() > 0);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493XXX"));
		indexList = flowRowUtility.findRowByFields(bean, fields, 2);
		assertFalse(indexList != null && indexList.size() > 0);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		fields.add(new Field("PROGRESSIVOINTERVENTO", "02"));
		indexList = flowRowUtility.findRowByFields(bean, fields, 4);
		assertTrue(indexList != null && indexList.size() > 0);
	}

	@Test
	public void testFindFirstRowByFields() {
		ValidationBean bean = loadRows();
		
		List<Field> fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		
		Row indexList = flowRowUtility.findFirstRowByFields(bean, fields, 4);
		assertTrue(indexList != null);
		
		indexList = flowRowUtility.findFirstRowByFields(bean, new ArrayList<Field>(), 4);
		assertTrue(indexList != null);
		
		indexList = flowRowUtility.findFirstRowByFields(bean, new ArrayList<Field>(), 5);
		assertFalse(indexList != null);
		
		indexList = flowRowUtility.findFirstRowByFields(bean, null, 4);
		assertTrue(indexList != null);
		
		indexList = flowRowUtility.findFirstRowByFields(new ValidationBean(), null, 4);
		assertFalse(indexList != null);
		
		indexList = flowRowUtility.findFirstRowByFields(null, null, 4);
		assertFalse(indexList != null);
		
		fields.add(new Field("NUMEROSCHEDA12121212", "17020493"));
		indexList = flowRowUtility.findFirstRowByFields(null, null, 4);
		assertFalse(indexList != null);
		
		indexList = flowRowUtility.findFirstRowByFields(bean, fields, 4);
		assertFalse(indexList != null);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493XXX"));
		indexList = flowRowUtility.findFirstRowByFields(bean, fields, 2);
		assertFalse(indexList != null);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		fields.add(new Field("PROGRESSIVOINTERVENTO", "02"));
		indexList = flowRowUtility.findFirstRowByFields(bean, fields, 4);
		assertTrue(indexList != null);
	}

	@Test
	public void testFindFirstRowIndexByFields() {
		ValidationBean bean = loadRows();
		
		List<Field> fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		
		Integer indexList = flowRowUtility.findFirstRowIndexByFields(bean, fields, 4);
		assertTrue(indexList != null);
		
		indexList = flowRowUtility.findFirstRowIndexByFields(bean, new ArrayList<Field>(), 4);
		assertTrue(indexList != null);
		
		indexList = flowRowUtility.findFirstRowIndexByFields(bean, new ArrayList<Field>(), 5);
		assertFalse(indexList != null);
		
		indexList = flowRowUtility.findFirstRowIndexByFields(bean, null, 4);
		assertTrue(indexList != null);
		
		indexList = flowRowUtility.findFirstRowIndexByFields(new ValidationBean(), null, 4);
		assertFalse(indexList != null);
		
		indexList = flowRowUtility.findFirstRowIndexByFields(null, null, 4);
		assertFalse(indexList != null);
		
		fields.add(new Field("NUMEROSCHEDA12121212", "17020493"));
		indexList = flowRowUtility.findFirstRowIndexByFields(null, null, 4);
		assertFalse(indexList != null);
		
		indexList = flowRowUtility.findFirstRowIndexByFields(bean, fields, 4);
		assertFalse(indexList != null);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493XXX"));
		indexList = flowRowUtility.findFirstRowIndexByFields(bean, fields, 2);
		assertFalse(indexList != null);
		
		fields = new ArrayList<Field>();
		fields.add(new Field("ISTITUTODIMISSIONE", "20003100"));
		fields.add(new Field("NUMEROSCHEDA", "17020493"));
		fields.add(new Field("PROGRESSIVOINTERVENTO", "02"));
		indexList = flowRowUtility.findFirstRowIndexByFields(bean, fields, 4);
		assertTrue(indexList != null);
	}
	
	private ValidationBean loadRows() {
		String flowId =  "390c8220-88e5-4fb4-b18b-20b0ffe3ceb4";
        String versionId = "0ebc5623-7044-4e5c-91b1-647a4141256d";
        
        ValidationFilter request = new ValidationFilter();
        request.setFlowId(flowId);
        request.setVersionId(versionId);
        FlowViewFilterField[] fields = new FlowViewFilterField[2];
        
        FlowViewFilterField f1 = new FlowViewFilterField();
        f1.setName("ISTITUTODIMISSIONE");
        f1.setSection(0);
        f1.setParam("20003100");
        fields[0]=f1;
        FlowViewFilterField f2 = new FlowViewFilterField();
        f2.setName("NUMEROSCHEDA");
        f2.setSection(0);
        f2.setParam("17020493");
        fields[1]=f2;
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
