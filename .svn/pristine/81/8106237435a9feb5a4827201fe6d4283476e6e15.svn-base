package it.eng.care.domain.flow.b2b.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import org.json.JSONObject;

import it.eng.care.domain.flow.b2b.exception.ValidationFlowException;

public interface ValidationFlowService {

	public String loadSchema(String flowId, String version);

	public String loadCachedSchema(String flowId, String version) throws ExecutionException;

	public JsonObject validateSingleFlow(String flow, String flowId, String version)
			throws ValidationFlowException, ExecutionException;

	public JsonArray validateMultipleFlow(String flow, String flowId, String version) throws ValidationFlowException, ExecutionException;

	public Stream<JsonValue> validateHugeFlow(InputStream flow, String flowId, String version,
			Consumer<String> errorhandler) throws ExecutionException;

	
	HashMap<String, Object> checkRecordState(JSONObject object, String flow, String version);

}
