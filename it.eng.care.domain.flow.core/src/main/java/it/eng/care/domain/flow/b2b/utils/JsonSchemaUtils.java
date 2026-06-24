package it.eng.care.domain.flow.b2b.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.eng.care.domain.flow.core.dao.FlowTableDAO;
import it.eng.care.domain.flow.core.dao.FlowTableFieldDAO;
import it.eng.care.domain.flow.core.dao.FlowVersionDAO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.domain.flow.core.entity.FlowTableDO;
import it.eng.care.domain.flow.core.entity.FlowTableFieldDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.service.FlowForeignKeyService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.FormFlowService;
import it.eng.care.domain.flow.core.service.VersionService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author mpirozzi Classe utile per la generazione del Json Shema
 * 
 *         dear programmer, when i wrote this code, only god and i knew how it
 *         worked. now, only god know is. therefore, if you are trying to
 *         optimize the code, please increse this counter as a warning for the
 *         next person:
 * 
 *         total_hour_wasted_here: 36
 */

@Transactional
public class JsonSchemaUtils {

	@Autowired
	VersionService version;
	@Autowired
	FlowTableFieldDAO flowTableFieldDAO;
	@Autowired
	FlowTableDAO flowTableDAO;
	@Autowired
	FormFlowService formFlowService;
	@Autowired
	FlowService flow;
	@Autowired
	FlowVersionDAO flowVersionDAO;
	@Autowired
	FlowForeignKeyService flowForeignKeyService;

	/**
	 * Generate schema
	 * 
	 * @param formFlow
	 * @return
	 */
	public JsonSchema generateSchema(FormFlowDTO formFlow) {

		HashMap<String, List<FlowTableDO>> tabMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		JsonSchema schema = new JsonSchema(mapper.createObjectNode());
//creazione schema
		schema.getSchemaNode().put("definitions", mapper.createObjectNode());
		schema.getSchemaNode().put("type", "object");
		schema.getSchemaNode().put("$schema", "http://json-schema.org/draft-07/schema#");
		schema.getSchemaNode().put("$id",
				"IOR_ID_" + formFlow.getName() + "_" + version.retrieveOne(formFlow.getVersion()).getVersion());
		schema.getSchemaNode().put("title", formFlow.getName());
		schema.getSchemaNode().putArray("required");
		ArrayNode nodeReq = (ArrayNode) schema.getSchemaNode().get("required");
		{
			nodeReq.add(formFlow.getFlowTableList().get(0).getName());
		}
		ObjectNode node = schema.getSchemaNode().putObject("properties");

		ObjectNode firstNode = node.putObject(formFlow.getFlowTableList().get(0).getName());
		firstNode.put("$id", "#/properties/" + formFlow.getFlowTableList().get(0).getName());
		firstNode.put("type", "object");
		ArrayNode firstArrayNode = firstNode.putArray("required");
		ObjectNode firstNodeProp = firstNode.putObject("properties");

		ObjectNode genChildNode = null;
		ObjectNode genNodeProp = null;
		ObjectNode genNode = null;
		boolean childOrNot = false;
		ObjectNode genChildNodeProp = null;
		ArrayNode genChildArrayNode = null;
		ArrayNode genArrayNode = null;

		for (FormFlowTableDTO formFlowTableDTO : formFlow.getFlowTableList()) {
			if (formFlowTableDTO.getListFk().isEmpty()) {

				for (int i = 0; i < formFlowTableDTO.getFlowTableFieldList().size(); i++) {

					// aggiunta a required
					if (formFlowTableDTO.getFlowTableFieldList().get(i).isMandatory()) {
						firstArrayNode.add(formFlowTableDTO.getFlowTableFieldList().get(i).getName());
					}

					ObjectNode n = firstNodeProp.putObject(formFlowTableDTO.getFlowTableFieldList().get(i).getName());

					n.put("$id", "#/properties/" + formFlowTableDTO.getName() + "/" + "properties/"
							+ formFlowTableDTO.getFlowTableFieldList().get(i).getName());

					if (formFlowTableDTO.getFlowTableFieldList().get(i).getFieldType().toLowerCase().equals("date")) {
						n.put("type", "string");
//						formFlowTableDTO.getFlowTableFieldList().get(i).setRegExp(
//								"\\b[0-9]{4}-[0-9]{2}-[0-9]{2}[tT][0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}[zZ]\\b");
					} else {
						n.put("type", formFlowTableDTO.getFlowTableFieldList().get(i).getFieldType().toLowerCase());
					}

					n.put("title", formFlowTableDTO.getFlowTableFieldList().get(i).getDescription());
					// utilizzo o meno della regex per un campo
					if (formFlowTableDTO.getFlowTableFieldList().get(i).getRegExp() != null) {
						n.put("pattern", formFlowTableDTO.getFlowTableFieldList().get(i).getRegExp());
					}

				}
			}
			// la gestione qui è errata, andrebbe rivista per gestire meglio padre/figlio
			// (funziona per flussi con al massimo 3 livelli)
			// si potrebbe vedere se a quel livello è presente il figlio interessato e
			// scendere o salire di conseguenza
			// questa è una "pezza" per fare in fretta
			for (FormFlowTableLinkDTO formFlowTableLinkDTO : formFlowTableDTO.getListFk()) {

				BaseSearchInput input = new BaseSearchInput();
				input.setParam("idTableReferenced", formFlowTableLinkDTO.getIdTableReferenced());
				Pair<List<FlowForeignKeyDO>, SearchInfo> flowReferenced = flowForeignKeyService
						.retrieveAllFiltered(input);

				List<FlowForeignKeyDO> childTable = flowReferenced.getFirst();

				for (FlowForeignKeyDO child : childTable) {

					if(child.getIdTableReferenced() == null) {
						continue;
					}

					if (child.getIdTableReferenced().getId().equals(formFlow.getFlowTableList().get(0).getId())) {
						genNode = firstNodeProp.putObject(child.getJsonField());
						genNode.put("$id", "#/properties/" + child.getIdTableReferenced().getName() + "/"
								+ "properties/" + child.getIdTable().getName());
						genNode.put("type", child.getJsonFieldType().toLowerCase());
						genNode.put("title", child.getIdTable().getDescription());

						if (child.getJsonFieldType().toLowerCase().equals("array")) {
							ObjectNode itemsNode = genNode.putObject("items");
							itemsNode.put("$id", "#/properties/" + child.getIdTableReferenced().getName() + "/"
									+ "properties/" + child.getIdTable().getName() + "/items");
							itemsNode.put("title", child.getIdTable().getDescription());
							genArrayNode = itemsNode.putArray("required");
							genNodeProp = itemsNode.putObject("properties");

						} else {
							genArrayNode = genNode.putArray("required");

							genNodeProp = genNode.putObject("properties");
						}
						childOrNot = false;

					} else {

						genChildNode = genNodeProp.putObject(child.getJsonField());

						genChildNode.put("$id", "#/properties/" + child.getIdTableReferenced().getName() + "/"
								+ "properties/" + child.getJsonField());
						genChildNode.put("type", child.getJsonFieldType().toLowerCase());
						genChildNode.put("title", child.getIdTable().getDescription());

						if (child.getJsonFieldType().toLowerCase().equals("array")) {
							ObjectNode itemsNode = genChildNode.putObject("items");
							itemsNode.put("$id", "#/properties/" + child.getIdTableReferenced().getName() + "/"
									+ "properties/" + child.getIdTable().getName() + "/items");
							itemsNode.put("title", child.getIdTable().getDescription());
							genChildArrayNode = itemsNode.putArray("required");
							genChildNodeProp = itemsNode.putObject("properties");
						} else {
							genChildArrayNode = genChildNode.putArray("required");
							genChildNodeProp = genChildNode.putObject("properties");
						}
						childOrNot = true;

					}
					BaseSearchInput poInput = new BaseSearchInput();
					poInput.setParam("idTable", child.getIdTable().getId());
					List<FlowTableFieldDO> childFields = flowTableFieldDAO.cerca(poInput);

					for (FlowTableFieldDO childField : childFields) {

						if (childOrNot == true) {

							ObjectNode m = genChildNodeProp.putObject(childField.getName());
							m.put("$id", "#/properties/" + child.getJsonField() + "/" + "properties/"
									+ childField.getName());
							if (childField.getFieldType().toLowerCase().equals("date")) {
								m.put("type", "string");
//								childField.setRegExp(
//										"\\b[0-9]{4}-[0-9]{2}-[0-9]{2}[tT][0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}[zZ]\\b");
							} else {
								m.put("type", childField.getFieldType().toLowerCase());
							}
							m.put("title", childField.getDescription());
							if (childField.getRegExp() != null) {
								m.put("pattern", childField.getRegExp());
							}
							if (childField.getMandatory()) {

								genChildArrayNode.add(childField.getName());
							}
						} else {

							ObjectNode m = genNodeProp.putObject(childField.getName());
							m.put("$id", "#/properties/" + child.getJsonField() + "/" + "properties/"
									+ childField.getName());
							if (childField.getFieldType().toLowerCase().equals("date")) {
								m.put("type", "string");
//								childField.setRegExp(
//										"\\b[0-9]{4}-[0-9]{2}-[0-9]{2}[tT][0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}[zZ]\\b");
							} else {
								m.put("type", childField.getFieldType().toLowerCase());
							}
							m.put("title", childField.getDescription());
							if (childField.getRegExp() != null) {
								m.put("pattern", childField.getRegExp());
							}

							if (childField.getMandatory()) {
								genArrayNode.add(childField.getName());
							}

						}

					}

				}

			}

		}

		FlowVersionDO flowVersion = flowVersionDAO.findByFlowNameAndVersionVersion(formFlow.getName(),
				version.retrieveOne(formFlow.getVersion()).getVersion());

		flowVersion.setJsonSchema(schema.toString());

		return schema;
	}

}
