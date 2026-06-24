package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

import it.eng.care.domain.flow.core.utility.LogUtil;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;

public class FlussiWsServiceClient {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(FlussiWsServiceClient.class);
	
  private final WebServiceTemplate webServiceTemplate;
  private final String baseEndpoint;

  // JAXB context per fault details
  private final JAXBContext faultJaxbContext;

  public FlussiWsServiceClient(WebServiceTemplate webServiceTemplate, String baseEndpoint) {
    if (webServiceTemplate == null) throw new IllegalArgumentException("webServiceTemplate is null");
    if (baseEndpoint == null || baseEndpoint.isBlank()) {
      throw new IllegalArgumentException("flow.region.address is null/blank");
    }
    this.webServiceTemplate = webServiceTemplate;
    this.baseEndpoint = baseEndpoint;

    try {
      // metti qui TUTTI i fault detail che vuoi riconoscere
      this.faultJaxbContext = JAXBContext.newInstance(
          UtenteNonAbilitatoException.class,
          FlussiWsException.class
      );
    } catch (Exception e) {
    	LogUtil.logException(LOGGER, "Cannot init JAXBContext for SOAP faults", e);
    	
      throw new IllegalStateException("Cannot init JAXBContext for SOAP faults", e);
    }
  }

  private String endpoint() {
    String e = baseEndpoint.trim();
    if (e.endsWith("/")) e = e.substring(0, e.length() - 1);

    if (e.toLowerCase().contains("/flussi/flussiws")) return e;

    return e + "/flussi/FlussiWs";
  }

  @SuppressWarnings("unchecked")
  private <T> T unwrap(Object resp, Class<T> type) {
    Object v = (resp instanceof JAXBElement<?> j) ? j.getValue() : resp;
    if (v == null) return null;
    if (!type.isInstance(v)) {
      throw new IllegalStateException(
          "Unexpected SOAP response. Expected=" + type.getName() + " actual=" + v.getClass().getName()
      );
    }
    return (T) v;
  }

  private RuntimeException mapSoapFault(SoapFaultClientException ex) {
    try {
      Source detailSource = ex.getSoapFault().getFaultDetail() != null
          ? ex.getSoapFault().getFaultDetail().getSource()
          : null;

      if (detailSource == null) {
        return ex; // niente detail, rilancio originale
      }

      String xml = sourceToString(detailSource);
      Object detailObj = unmarshalFaultDetail(xml);

      if (detailObj instanceof UtenteNonAbilitatoException u) {
        // rilancio come runtime “specifica”
        return new IllegalStateException("UtenteNonAbilitatoException: codice=" + u.getCodice() + " message=" + u.getMessage(), ex);
      }
      if (detailObj instanceof FlussiWsException f) {
        return new IllegalStateException("FlussiWsException: message=" + f.getMessage() + " errori=" + f.getErrori(), ex);
      }

      // detail sconosciuto
      return new IllegalStateException("SOAP Fault detail not recognized. detail=" + xml, ex);

    } catch (Exception parseErr) {
      // se non riesco a parsare, rilancio l’originale con contesto
    	LogUtil.logException(LOGGER, "SOAP Fault received but cannot parse detail", parseErr);
    	
        return new IllegalStateException("SOAP Fault received but cannot parse detail: " + ex.getMessage(), ex);
    }
  }

  private Object unmarshalFaultDetail(String xml) throws Exception {
    Unmarshaller um = faultJaxbContext.createUnmarshaller();
    Object obj = um.unmarshal(new StringReader(xml));
    if (obj instanceof JAXBElement<?> j) return j.getValue();
    return obj;
  }

  private String sourceToString(Source src) throws Exception {
    Transformer tf = TransformerFactory.newInstance().newTransformer();
    java.io.StringWriter sw = new java.io.StringWriter();
    tf.transform(src, new StreamResult(sw));
    return sw.toString();
  }

  private <T> T call(Object request, Class<T> responseType) {
    try {
      Object raw = webServiceTemplate.marshalSendAndReceive(endpoint(), request);
      return unwrap(raw, responseType);
    } catch (SoapFaultClientException sfe) {
    	LogUtil.logException(LOGGER, "", sfe);
    	
        throw mapSoapFault(sfe);
    }
  }

  // =========================
  // METODI SOAP (Response JAXB)
  // =========================

  public AnnullaConsolidamentoResponse annullaConsolidamento(AnnullaConsolidamentoRequest request) {
    return call(request, AnnullaConsolidamentoResponse.class);
  }

  public EliminaResponse elimina(EliminaRequest request) {
    return call(request, EliminaResponse.class);
  }

  public ConsolidaResponse consolida(ConsolidaRequest request) {
    return call(request, ConsolidaResponse.class);
  }

  public SimulaResponse simula(SimulaRequest request) {
    return call(request, SimulaResponse.class);
  }

  public StatoResponse stato(StatoRequest request) {
    return call(request, StatoResponse.class);
  }

  public PreparaRitInfCaricamentoResponse preparaRitInfCaricamento(PreparaRitInfCaricamentoRequest request) {
    return call(request, PreparaRitInfCaricamentoResponse.class);
  }

  public InvioFileResponse invioFile(InvioFileRequest request) {
    return call(request, InvioFileResponse.class);
  }

  public RitornoInformativoSimulazioneResponse ritornoInformativoSimulazione(RitornoInformativoSimulazioneRequest request) {
    return call(request, RitornoInformativoSimulazioneResponse.class);
  }

  public ScaricaRitInfCaricamentoResponse scaricaRitInfCaricamento(ScaricaRitInfCaricamentoRequest request) {
    return call(request, ScaricaRitInfCaricamentoResponse.class);
  }

  public RitornoInformativoCaricamentoResponse ritornoInformativoCaricamento(RitornoInformativoCaricamentoRequest request) {
    return call(request, RitornoInformativoCaricamentoResponse.class);
  }

  public InviaESimulaResponse inviaESimula(InviaESimulaRequest request) {
    return call(request, InviaESimulaResponse.class);
  }

  // =========================
  // METODI COMODI (ritornano i "return")
  // =========================

  public Esito annullaConsolidamento(String token, BigDecimal prgInvio, String note) {
    var resp = annullaConsolidamento(new AnnullaConsolidamentoRequest(token, prgInvio, note));
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito elimina(String token, BigDecimal prgInvio) {
    var resp = elimina(new EliminaRequest(token, prgInvio));
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito consolida(String token, BigDecimal prgInvio, String note) {
    var resp = consolida(new ConsolidaRequest(token, prgInvio, note));
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito simula(String token, BigDecimal prgInvio) {
    var resp = simula(new SimulaRequest(token, prgInvio));
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito stato(String token, BigDecimal prgInvio) {
    var resp = stato(new StatoRequest(token, prgInvio));
    return resp != null ? resp.getReturnValue() : null;
  }

  public List<BigDecimal> preparaRitInfCaricamento(
      String token,
      String codTipoFlusso,
      int anno,
      int numInvio,
      String codTipoGruppo,
      String codFormatoFile
  ) {
    var resp = preparaRitInfCaricamento(
        new PreparaRitInfCaricamentoRequest(token, codTipoFlusso, anno, numInvio, codTipoGruppo, codFormatoFile)
    );
    return (resp != null && resp.getReturnValues() != null) ? resp.getReturnValues() : Collections.emptyList();
  }

  public BigDecimal invioFile(String token, String codTipoFlusso, int anno, int numInvio, FlussiFile[] files) {
    var resp = invioFile(new InvioFileRequest(token, codTipoFlusso, anno, numInvio, files));
    return resp != null ? resp.getReturnValue() : null;
  }

  public FlussiFile[] ritornoInformativoSimulazione(String token, BigDecimal prgInvio, String codTipoFile) {
    var resp = ritornoInformativoSimulazione(new RitornoInformativoSimulazioneRequest(token, prgInvio, codTipoFile));
    List<FlussiFile> list = (resp != null ? resp.getReturnValues() : null);
    return list != null ? list.toArray(new FlussiFile[0]) : null;
  }

  public FlussiFile[] scaricaRitInfCaricamento(String token, BigDecimal[] prgFile) {
    var resp = scaricaRitInfCaricamento(new ScaricaRitInfCaricamentoRequest(token, prgFile));
    List<FlussiFile> list = (resp != null ? resp.getReturnValues() : null);
    return list != null ? list.toArray(new FlussiFile[0]) : null;
  }

  public FlussiFile[] ritornoInformativoCaricamento(
      String token, String codTipoFlusso, int anno, int numInvio,
      String codTipoGruppo, String codFormatoFile
  ) {
    var resp = ritornoInformativoCaricamento(
        new RitornoInformativoCaricamentoRequest(token, codTipoFlusso, anno, numInvio, codTipoGruppo, codFormatoFile)
    );
    List<FlussiFile> list = (resp != null ? resp.getReturnValues() : null);
    return list != null ? list.toArray(new FlussiFile[0]) : null;
  }

  public Esito inviaESimula(String token, String codTipoFlusso, int anno, int numInvio, FlussiFile[] files) {
    var resp = inviaESimula(new InviaESimulaRequest(token, codTipoFlusso, anno, numInvio, files));
    return resp != null ? resp.getReturnValue() : null;
  }
}
