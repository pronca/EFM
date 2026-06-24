package it.eng.care.domain.flow.webservice.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;

import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.entity.FlowDrgDO;
import it.eng.care.domain.flow.core.enumeration.MachineEvent;
import it.eng.care.domain.flow.core.service.FlowDrgService;
import it.eng.care.domain.flow.core.spring.statemachine.StateMachineFlow;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.webservice.service.WebServiceSender;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.EliminaRequest;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.EliminaResponse;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.Esito;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.FlussiFile;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.FlussiWsServiceClient;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.FlussiWsServiceLocator;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.InvioFileRequest;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.InvioFileResponse;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.RitornoInformativoSimulazioneRequest;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.RitornoInformativoSimulazioneResponse;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.SimulaRequest;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.SimulaResponse;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.StatoRequest;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.StatoResponse;
import it.eng.care.domain.flow.webservice.sis.ws.login.LoginRequest;
import it.eng.care.domain.flow.webservice.sis.ws.login.LoginResponse;
import it.eng.care.domain.flow.webservice.sis.ws.login.LoginWsServiceClient;
import it.eng.care.domain.flow.webservice.sis.ws.login.LoginWsServiceLocator;
import it.eng.care.domain.flow.webservice.sis.ws.login.UtenteNonAbilitatoException;

/**
 * 
 * @author mpirozzi
 *
 * Classe necessaria all'inivio presso regione emilia romagna
 * 
 */
public class WebServiceSenderImpl implements WebServiceSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceSenderImpl.class);

    @Autowired
    private ConfigurationDAO configuration;

    @Autowired
    private FlowDrgService flowDrgService;

    @Autowired(required = false)
    private StateMachineFlow stateMachineFlow;
    
    @Value("${flow.region.address}")
    private String baseEndpoint;
    
    // ==========================================================
    // LOGIN
    // ==========================================================
    
    @Override
    public String[] login(String requestId, boolean drg) {
        String[] response = new String[2];

        try {
            LOGGER.info("Tentativo di login presso la regione");

            String user = configuration.findByKeyId("usrWebService").getValue();
            String pwd  = configuration.findByKeyId("pswWebService").getValue();

            // 1) Template SOAP con JAXB "classesToBeBound" (niente contextPath)
            WebServiceTemplate wsTemplate = SoapTemplateFactory.create(
                    5_000,
                    30_000,
                    // minime per login
                    LoginRequest.class,
                    LoginResponse.class,
//                  // se li usi anche altrove nello stesso template, aggiungi anche:
//                  LogoutRequest.class,
//                  LogoutResponseConfigurer.class,
//                  AutorizzatoRequest.class,
//                  AutorizzatoResponse.class,
//                  GetUsernameByTokenRequest.class,
//                  GetUsernameByTokenResponse.class,
//                  GetUserDetailByTokenRequest.class,
//                  GetUserDetailByTokenResponse.class,
//                  UserDetail.class,
//                  KeyValues.class
                    UtenteNonAbilitatoException.class
            );

            // 2) Client Spring-WS (endpoint passato esplicitamente)
//            LoginWsServiceClient client = new LoginWsServiceClient(wsTemplate);
            LoginWsServiceClient client = new LoginWsServiceClient(wsTemplate,baseEndpoint);

            // 3) Locator wrapper (tuo)
            LoginWsServiceLocator locator = new LoginWsServiceLocator(client);

            // 4) Chiamata -> token (String)
            String token = locator.login(user, pwd);

            response[0] = token;
            return response;

        } catch (UtenteNonAbilitatoException e) {
            // stessa semantica di prima
        	LogUtil.logException(LOGGER, "Login fallito", e);
        	
            return (String[]) handleError("Login fallito", e, requestId, drg, response);

        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Errore login", e);
        	
            return (String[]) handleError("Errore login", e, requestId, drg, response);
        }
    }

    
    // ==========================================================
    // INVIO FLUSSO
    // ==========================================================
    
    @Override
    public Object[] sendFlow(
            String codTipoFlusso,
            int anno,
            int numInvio,
            String token,
            FlussiFile[] files,
            String requestId,
            boolean drg) {

        Object[] response = new Object[2];

        try {
            LOGGER.info("Invio flusso presso la regione");

            // 1) Template SOAP 1.1 + JAXB basato su classi (NON contextPath)
            WebServiceTemplate webServiceTemplateFlussi =
                    SoapTemplateFactory.create(
                            10_000,
                            120_000,
                            InvioFileRequest.class,
                            InvioFileResponse.class,
                            FlussiFile.class,
                            Esito.class,
                            // se vuoi gestire anche fault/exception JAXB nel marshaller:
                            // FlussiWsException.class,
                            UtenteNonAbilitatoException.class
                    );

            // 2) Client Spring-WS: endpoint passato esplicitamente (perché lo istanzi a mano!)
            // endpoint deve essere quello di FlussiWs:
            // es: https://siseps.regione.emilia-romagna.it/flussi/FlussiWs
            FlussiWsServiceClient flussiClient = new FlussiWsServiceClient(webServiceTemplateFlussi,baseEndpoint);
            // 3) Locator wrapper (tuo, non Axis)
            FlussiWsServiceLocator flussiService = new FlussiWsServiceLocator(flussiClient);

            // 4) Chiamata: ritorna BigDecimal (estratto dal "return" della response)
            BigDecimal prgInvio = flussiService.invioFile(
                    token, codTipoFlusso, anno, numInvio, files
            );

            response[0] = prgInvio;
            return response;

        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Invio flusso fallito", e);
        	
            return handleError("Invio flusso fallito", e, requestId, drg, response);
        }
    }

    
    // ==========================================================
    // SIMULAZIONE
    // ==========================================================
    
    @Override
    public Object[] simulate(
            String token,
            BigDecimal prgInvio,
            String requestId,
            boolean drg) throws InterruptedException {

        Object[] response = new Object[2];

        try {
            LOGGER.info("Simulazione flusso");

            // WebServiceTemplate con JAXB basato su classi (NON contextPath)
            WebServiceTemplate webServiceTemplateFlussi =
                    SoapTemplateFactory.create(
                            10_000,
                            120_000,
                            // request/response usate in questo metodo
                            SimulaRequest.class,
                            SimulaResponse.class,
                            StatoRequest.class,
                            StatoResponse.class,
                            RitornoInformativoSimulazioneRequest.class,
                            RitornoInformativoSimulazioneResponse.class,
                            // tipi usati dentro le response
                            FlussiFile.class,
                            Esito.class,
                            // se hai anche classi JAXB per eccezioni/fault e le vuoi bindare:
                            // FlussiWsException.class,
                            UtenteNonAbilitatoException.class
                    );

            // Client/locator (NO @Component perché lo stai costruendo a mano)
            FlussiWsServiceClient flussiClient = new FlussiWsServiceClient(webServiceTemplateFlussi,baseEndpoint);
            FlussiWsServiceLocator flussiService = new FlussiWsServiceLocator(flussiClient);

            // 1) simula
            flussiService.simula(token, prgInvio);

            // 2) polling stato finché è CSSIM
            Esito stato;
            do {
                Thread.sleep(2000);
                stato = flussiService.stato(token, prgInvio);
            } while (stato != null && "CSSIM".equals(stato.getCodStato()));

            // 3) ritorni
            FlussiFile[] files = flussiService.ritornoInformativoSimulazione(token, prgInvio, null);
            response[0] = files;
            return response;

        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Simulazione fallita", e);
        	
            return handleError("Simulazione fallita", e, requestId, drg, response);
        }
    }

    
    // ==========================================================
    // STATO
    // ==========================================================
    
    @Override
    public void checkState(String token, BigDecimal prgInvio) {
        try {
            LOGGER.info("Controllo stato flusso");
         // WebServiceTemplate con JAXB basato su classi (NON contextPath)
            WebServiceTemplate webServiceTemplateFlussi =
                    SoapTemplateFactory.create(
                            10_000,
                            120_000,
                            StatoRequest.class,
                            StatoResponse.class,
                            UtenteNonAbilitatoException.class
                    );
            FlussiWsServiceClient flussiClient = new FlussiWsServiceClient(webServiceTemplateFlussi,baseEndpoint);
            FlussiWsServiceLocator flussiService = new FlussiWsServiceLocator(flussiClient);
            flussiService.stato(token, prgInvio);
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Errore controllo stato", e);
        }
    }

    
    // ==========================================================
    // CANCELLAZIONE
    // ==========================================================
    
    @Override
    public void delete(String token, BigDecimal prgInvio) {
        try {
            LOGGER.info("Cancellazione flusso");

            // 1) WebServiceTemplate con JAXB basato su classi
            WebServiceTemplate webServiceTemplateFlussi =
                    SoapTemplateFactory.create(
                            10_000,
                            120_000,
                            EliminaRequest.class,
                            EliminaResponse.class,
                            Esito.class,
                            // opzionale se le hai bindate davvero come JAXB:
                            // FlussiWsException.class,
                            UtenteNonAbilitatoException.class
                    );

            // 2) Client + locator creati a mano (NO @Component, NO @Value)
            FlussiWsServiceClient flussiClient = new FlussiWsServiceClient(webServiceTemplateFlussi, baseEndpoint);
            FlussiWsServiceLocator flussiService = new FlussiWsServiceLocator(flussiClient);

            // 3) chiamata (nel WSDL eliminaResponse ritorna Esito; puoi ignorarlo)
            flussiService.elimina(token, prgInvio);

        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "Errore cancellazione flusso", e);
        }
    }

    
    // ==========================================================
    // METODO COMUNE GESTIONE ERRORI
    // ==========================================================

    private Object[] handleError(
            String msg,
            Exception e,
            String requestId,
            boolean drg,
            Object[] response) {

        LOGGER.error(msg, e);
        String exception = e.getMessage();

        if (!drg) {
            response[1] = exception;
            return response;
        }

        FlowDrgDO resDrg = flowDrgService.searchByExtractionId(requestId).get(0);

        resDrg.setReturnDate(new Date());
        resDrg.setError(exception != null ? exception : msg);

        try {
            var state =
                    stateMachineFlow.execute(
                            requestId + "_DRG",
                            "DRG",
                            MachineEvent.DRG_TERMINATO_KO.getEvent());

            resDrg.setState(state.getId());

        } catch (Exception ex) {
        	LogUtil.logException(LOGGER, "Errore state machine DRG", e);
        }

        response[1] = exception;
        return response;
    }

    @Override
    public Object[] consolida(
            String token,
            BigDecimal prgInvio,
            String note,
            String requestId,
            boolean drg) {
				return null;

        
    }
    
}
