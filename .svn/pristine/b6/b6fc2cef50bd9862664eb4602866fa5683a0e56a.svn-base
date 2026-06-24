package org.it.eng.care.domain.flow.b2b;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import jakarta.transaction.Transactional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.care.domain.flow.b2b.controller.FlowReceiverController;
import it.eng.care.domain.flow.b2b.model.ErrorWellFormedDTO;
import it.eng.care.domain.flow.b2b.service.JsonFlowService;
import it.eng.care.domain.flow.b2b.service.ZipService;
import it.eng.care.domain.flow.core.dao.FlowVersionDAO;
import it.eng.care.domain.flow.core.dao.FlowViewDAO;
import it.eng.care.domain.flow.core.dao.querySearch.FlowViewDAOQueryByBaseSearchInput;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JsonFlowServiceTestConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsonFlowServiceTest {

	@Autowired
	JsonFlowService jsonService;

	@Autowired
	FlowReceiverController controller;

	@Autowired
	ZipService zipService;

	@Autowired
	FlowVersionDAO flowVersionDAO;

	@Autowired
	FlowViewDAO flowViewDAOQueryByBaseSearchInput;

	@Autowired
	FormFlowService flowService;
	/*
	 * @Test public void makeHugeJson() throws Exception { InputStream jsonStream =
	 * Thread.currentThread().getContextClassLoader()
	 * .getResourceAsStream("json/flow-test.json");
	 * 
	 * URL url = Thread.currentThread().getContextClassLoader().getResource("json");
	 * 
	 * File outFile = new File(url.getPath() + "/flow-list.json");
	 * 
	 * System.out.println("make file : " + outFile.getAbsolutePath());
	 * 
	 * try (OutputStreamWriter writer = new OutputStreamWriter(new
	 * FileOutputStream(outFile))) {
	 * 
	 * JsonObject obj = Json.createParser(jsonStream).getObject();
	 * 
	 * writer.write("[");
	 * 
	 * for (int i = 0; i < 1000; i++) { writer.write(obj.toString());
	 * writer.write(",\n"); }
	 * 
	 * writer.write("]"); } }
	 */

	public ErrorWellFormedDTO startTestSingle(String type, String flow, String version) throws Exception {
		try (InputStream jsonStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("json/flow-single-" + flow + "-" + type + ".json");
				Scanner flowReader = new Scanner(jsonStream)) {
			StringBuilder builder = new StringBuilder();

			while (flowReader.hasNextLine()) {
				builder.append(flowReader.nextLine());
			}

			return controller.sendSingleFlow(builder.toString(), flow, version, null);

		}
	}

	@Test
	public void testSearch() {
	}

	@Test
	public void testUnzip() throws Exception {
		// scompatto il file zip e ricevo il file temporaneo
		File json = zipService.readFirstFile(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("json/flow-multiple-SDO-valid.zip"));

		try (InputStream jsonFile = new FileInputStream(json); Scanner scan = new Scanner(jsonFile)) {

			int count = 0;

			while (scan.hasNextLine()) {
				// leggo il file
				scan.nextLine();
				count++;
			}
//			System.out.println("Numero righe " + count);
			Assert.assertEquals(1001, count);
		}
		json.delete();

	}

	@Test
	@Transactional
	public void testJsonFlow() {
		FlowVersionDO flowVersion = flowVersionDAO.findByFlowNameAndVersionVersion("SDOIOR", "versioneditest");
		flowVersion.setJsonSchema("dsfsodkfosdk");
		flowVersionDAO.save(flowVersion);
	}

}
