package it.eng.care.domain.flow.b2b.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.commons.fileupload2.core.FileItemInput;
import org.apache.commons.fileupload2.core.FileItemInputIterator;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.care.domain.flow.b2b.controller.FlowReceiverController;
import it.eng.care.domain.flow.b2b.exception.ValidationFlowException;
import it.eng.care.domain.flow.b2b.model.ErrorWellFormedDTO;
import it.eng.care.domain.flow.b2b.model.FlowQueryBuilder;
import it.eng.care.domain.flow.b2b.model.FlowResult;
import it.eng.care.domain.flow.b2b.service.BatchFlowService;
import it.eng.care.domain.flow.b2b.service.JsonFlowService;
import it.eng.care.domain.flow.b2b.service.ValidationFlowService;
import it.eng.care.domain.flow.b2b.service.ZipService;
import it.eng.care.domain.flow.b2b.utils.FlowOperationResult;
import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.FlowVersionDO;
import it.eng.care.domain.flow.core.enumeration.StateReceviedAppEnum;
import it.eng.care.domain.flow.core.enumeration.StateSendRegionEnum;
import it.eng.care.domain.flow.core.service.FlowCacheService;
import it.eng.care.domain.flow.core.service.FlowExportRequestService;
import it.eng.care.domain.flow.core.service.FlowService;
import it.eng.care.domain.flow.core.service.VersionService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.service.SearchInfo;
import jakarta.annotation.PostConstruct;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fm/FlowReceiver")
public class FlowReceiverControllerImpl implements FlowReceiverController {

	private static Logger logger = LoggerFactory.getLogger(FlowReceiverControllerImpl.class);

	@Autowired
	protected ValidationFlowService validationFlowService;

	@Autowired
	protected JsonFlowService jsonFlowService;

	@Autowired
	protected BatchFlowService batchFlowService;

	@Autowired
	protected ZipService zipService;

	@Autowired
	protected FlowOperationResult flowOperationResult;
	
	@Autowired
    private ConfigurationDAO configuration;
	
	@Autowired
	private VersionService versionService;
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	FlowCacheService flowCacheService;
	
	@Autowired
	private TabgenService tabgenService;
	
	@Autowired
	private FlowExportRequestService flowExportRequestService;
	
	@Autowired
    private EntityManager entityManager;

	@PostConstruct
	public void init() {
		logger.info("FlowReceiverControllerImpl init");
	}

	@Override
	@GetMapping(path = "/getSchema/{flowName}/{version:.+}", //
			produces = { "application/json" })
	public OperationResult<String> getSchema(@PathVariable("flowName") String flowName,
			@PathVariable("version") String version) {
		try {
			return OperationResult.success(validationFlowService.loadSchema(flowName, version));
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
	}

	@Override
	@PostMapping(path = "/testValidateSingleFlow/{flowName}/{version:.+}")
	public OperationResult<FlowResult> testValidateSingleFlow(@RequestBody String flow,
			@PathVariable("flowName") String flowName, @PathVariable("version") String version) {
		try {

			String extractionId = UUID.randomUUID().toString();
			FlowResult result = new FlowResult(extractionId);
			// effettuo la validazione
			validationFlowService.validateSingleFlow(flow, flowName, version);
			// se tutto è andato bene restituisco l'id
			return OperationResult.success(result);
		} catch (ValidationFlowException ex) {
			LogUtil.logException(logger, "", ex);
			return OperationResult.failure(ex.getErrors());
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
	}

	/**
	 * Accetta in input uno o più flussi
	 * 
	 * Prima di acquisire il flussi, verifica il flag tipo trasmissione:
	 * 1. in caso di cancellazione (tipotrasmissione='A'), lo stato della pratica già presente su fm viene aggiornato a CANCELLATA
	 * 2. in caso di inserimento (tioptrasmissione=''I'), bisogna verificare la presenza eventuale della pratica su fm;
	 *    la ricerca viene fatta per chiave della sezione 0.
	 * 2.1 Se la pratica non esiste allora viene inserita con gli stati NUOVA/DA_INVIARE
	 * 2.2 Se la pratica esiste e risulta INVIATA alla regione, allora l'invio viene rifiutato
	 * 2.3 Se la pratica è stata inviata e la regione l'ha ACCETTATA oppure se è ancora DA_INVIARE allora
	 * 2.3.1 Acquisisci la pratica solo se ci sono variazioni tra i dati inviati e quelli già acquisiti.
	 * 2.3.2 Lo stato di trasmissione alla regione diventa DA_INVIARE
	 * 2.3.3 Lo stato di ricezione è NUOVO se la pratica non è mai stata inviata oppure risulta CANCELLATA, 
	 *                               VARIAZIONE se la pratica già esiste su fm
	 *     .
	 */
	@Override
	@PostMapping(path = "/sendSingleFlow/{flowName}/{version:.+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorWellFormedDTO sendSingleFlow(@RequestBody String flow,
	                                        @PathVariable String flowName,
	                                        @PathVariable String version,
	                                        HttpServletRequest request) {

		if (request != null) {
			String uri = request.getRequestURI();
			String qs = request.getQueryString();
			String url = (qs == null) ? uri : (uri + "?" + qs);

			logger.info("CALL {} method={} remote={} flowName={} version={} flow={}",
		            url,
		            request.getMethod(),
		            request.getRemoteAddr(),
		            flowName, 
		            version,
		            flow);
		}

		return jsonFlowService.processSingleFlow(flow, flowName, version, null).getResponse();
	}

	@Override
	@PostMapping(path = "/sendMultipleFlow/{flowName}/{version:.+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public OperationResult<FlowResult> sendMultipleFlow(@RequestBody String flow,
			@PathVariable("flowName") String flowName, @PathVariable("version") String version) {
		try {

			String extractionId = UUID.randomUUID().toString();
			FlowResult result = new FlowResult(extractionId);

			JsonArray array = validationFlowService.validateMultipleFlow(flow, flowName, version);
			// salvataggio su tabella
			List<FlowQueryBuilder> queryList = array.stream().map(v -> {
				try {

					return jsonFlowService.generateQueryFromJson(v.asJsonObject(), flowName, version, extractionId,
							StateReceviedAppEnum.NUOVA.toString(), StateSendRegionEnum.DA_INVIARE.toString());
				} catch (Exception e) {
					LogUtil.logException(logger, "", e);
					return new ArrayList<FlowQueryBuilder>(0);
				}
			}).flatMap(l -> l.stream()).collect(Collectors.toList());

			batchFlowService.executeBatch(queryList);
			// TODO gestire ritorno
			return OperationResult.success(result);

		} catch (ValidationFlowException ex) {
			LogUtil.logException(logger, "", ex);
			return OperationResult.failure(ex.getErrors());
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
	}

	@Override
	@PostMapping(path = "/sendCompressedFlow/{flowName}/{version:.+}", //
			consumes = { "application/x-www-form-urlencoded", "multipart/form-data" }, //
			produces = { "application/json" })
	public OperationResult<FlowResult> sendCompressedFlow(@PathVariable("flowName") String flowName,
			@PathVariable("version") String version, final HttpServletRequest request) {
		// salvo il file compresso, lo decomprimo e apro il file decompresso
		try {

			String exstractionId = UUID.randomUUID().toString();
			FlowResult result = new FlowResult(exstractionId);
//asincrono
			File tmpFile = getExtractedFile(request);
			if (tmpFile != null) {
				try (final InputStream is = new FileInputStream(tmpFile)) {
					parseJsonAndSave(is, flowName, version, exstractionId);
//
					return OperationResult.success(result);
				} finally {
					// cancello il file temporaneo
					tmpFile.delete();
				}
			} else {
				return OperationResult.failure();
			}
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
	}

	/**
	 * Salva il file sul disco e lo decomprime, restituendo lo stream al file
	 * decompresso
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws FileUploadException
	 */
	private File getExtractedFile(final HttpServletRequest request)
	        throws IOException, FileUploadException {

	    final JakartaServletFileUpload upload = new JakartaServletFileUpload();
	    final FileItemInputIterator iterator = upload.getItemIterator(request);

	    while (iterator.hasNext()) {
	        final FileItemInput item = iterator.next();
	        if (!item.isFormField()) {
	            try (InputStream in = item.getInputStream()) {
	                return zipService.readFirstFile(in);
	            }
	        }
	    }
	    return null;
	}

	
	private void parseJsonAndSave(InputStream is, String flowName, String version, String exstractionId)
			throws ExecutionException {

		int pageSize = 3;

		List<JsonObject> accumulator = new ArrayList<>();
		List<String> regionStates = new ArrayList<>();
		List<String> appStates = new ArrayList<>();

		// leggo l'array di elementi
		validationFlowService.validateHugeFlow(is, flowName, version, //
				err -> {
					logger.info("Errore nel parsing [" + err + "]");
				})//
				.forEach(j -> {
					try {
						accumulator.add(j.asJsonObject());
						regionStates.add(StateSendRegionEnum.DA_INVIARE.toString());
						appStates.add(StateReceviedAppEnum.NUOVA.toString());
						// accumulo un po di elementi prima di eseguire le query
						if (accumulator.size() % pageSize == 0) {
							List<FlowQueryBuilder> queryList = jsonFlowService.generateQueryFromJsonList(accumulator,
									flowName, version, exstractionId, appStates, regionStates);

							// mando tutto al servizio di salvataggio
							batchFlowService.executeBatch(queryList);

							accumulator.clear();
						}

					} catch (Exception e) {
						// TODO: handle exception
						LogUtil.logException(logger, "", e);
					}
				});
	}

	@Override
	@PostMapping(path = "/cancelProcedure/{flowName}/{version:.+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public OperationResult<String> cancelProcedure(@RequestBody String procedure,
			@PathVariable("flowName") String flowName, @PathVariable("version") String version) {
		try {

			jsonFlowService.deleteProcedure(procedure, flowName, version);

			return OperationResult.success("Pratica cancellata con successo");
		} catch (ValidationFlowException ex) {
			LogUtil.logException(logger, "", ex);
			return OperationResult.failure(ex.getErrors());
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
	}

	@Override
	@PostMapping(path = "/getErrors/{flowName}/{version:.+}")
	public OperationResult<String> getErrors(@RequestBody String request, @PathVariable("flowName") String flowName,
			@PathVariable("version") String version) {
		try {
			Thread thread = new Thread() {
				public void run() {

					// invocazione restituzione servizio
				}
			};
			thread.start();

			return OperationResult.success("Richiesta presa in carico");

		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
	}
	
	@GetMapping(path = "/clearB2bCache", produces = { "application/json" })
	public Object clearB2bCache() {
		try {
		flowCacheService.evictCaches();
		}catch (Exception e) {
			LogUtil.logException(logger, "", e);
			return OperationResult.failure();
		}
		return OperationResult.success("Cache cancellata");
	}
	
	private String extractPathValue(String pathString, JSONObject jsonObj) {
		return extractPathValue(pathString.split("\\."), jsonObj, 0);
	}
	
	private String extractPathValue(String[] path, JSONObject jsonObj, Integer index) {
		Object currentProp = jsonObj.get(path[index]);
		if(path.length > index + 1) {
			return extractPathValue(path, (JSONObject) currentProp, ++index);
		} else {
			return currentProp.toString();
		}
	}
	
}
