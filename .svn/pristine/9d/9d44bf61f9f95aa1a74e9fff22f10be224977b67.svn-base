package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.math.BigDecimal;

public class FlussiWsServiceLocator {

  private final FlussiWsServiceClient client;

  public FlussiWsServiceLocator(FlussiWsServiceClient client) {
    this.client = client;
  }

  public Esito annullaConsolidamento(String token, BigDecimal prgInvio, String note) {
    return annullaConsolidamento(new AnnullaConsolidamentoRequest(token, prgInvio, note));
  }

  public Esito elimina(String token, BigDecimal prgInvio) {
    return elimina(new EliminaRequest(token, prgInvio));
  }

  public Esito consolida(String token, BigDecimal prgInvio, String note) {
    return consolida(new ConsolidaRequest(token, prgInvio, note));
  }

  public Esito simula(String token, BigDecimal prgInvio) {
    return simula(new SimulaRequest(token, prgInvio));
  }

  public Esito stato(String token, BigDecimal prgInvio) {
    return stato(new StatoRequest(token, prgInvio));
  }

  public BigDecimal invioFile(String token, String codTipoFlusso, int anno, int numInvio, FlussiFile[] files) {
    return invioFile(new InvioFileRequest(token, codTipoFlusso, anno, numInvio, files));
  }

  public FlussiFile[] ritornoInformativoSimulazione(String token, BigDecimal prgInvio, String codTipoFile) {
    return ritornoInformativoSimulazione(new RitornoInformativoSimulazioneRequest(token, prgInvio, codTipoFile));
  }

  public FlussiFile[] scaricaRitInfCaricamento(String token, BigDecimal[] prgFile) {
    return scaricaRitInfCaricamento(new ScaricaRitInfCaricamentoRequest(token, prgFile));
  }

  public FlussiFile[] ritornoInformativoCaricamento(
      String token, String codTipoFlusso, int anno, int numInvio, String codTipoGruppo, String codFormatoFile) {
    return ritornoInformativoCaricamento(
        new RitornoInformativoCaricamentoRequest(token, codTipoFlusso, anno, numInvio, codTipoGruppo, codFormatoFile)
    );
  }

  public Esito inviaESimula(String token, String codTipoFlusso, int anno, int numInvio, FlussiFile[] files) {
    return inviaESimula(new InviaESimulaRequest(token, codTipoFlusso, anno, numInvio, files));
  }

  // ====== overload JAXB -> valore ======

  public Esito annullaConsolidamento(AnnullaConsolidamentoRequest request) {
    AnnullaConsolidamentoResponse resp = client.annullaConsolidamento(request);
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito elimina(EliminaRequest request) {
    EliminaResponse resp = client.elimina(request);
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito consolida(ConsolidaRequest request) {
    ConsolidaResponse resp = client.consolida(request);
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito simula(SimulaRequest request) {
    SimulaResponse resp = client.simula(request);
    return resp != null ? resp.getReturnValue() : null;
  }

  public Esito stato(StatoRequest request) {
    StatoResponse resp = client.stato(request);
    return resp != null ? resp.getReturnValue() : null;
  }

  public BigDecimal[] preparaRitInfCaricamento(PreparaRitInfCaricamentoRequest request) {
    PreparaRitInfCaricamentoResponse resp = client.preparaRitInfCaricamento(request);
    return (resp != null) ? resp.toArray() : new BigDecimal[0];
  }

  public BigDecimal invioFile(InvioFileRequest request) {
    InvioFileResponse resp = client.invioFile(request);
    return resp != null ? resp.getReturnValue() : null;
  }

  public FlussiFile[] ritornoInformativoSimulazione(RitornoInformativoSimulazioneRequest request) {
    RitornoInformativoSimulazioneResponse resp = client.ritornoInformativoSimulazione(request);
    if (resp == null || resp.getReturnValues() == null) return null;
    return resp.getReturnValues().toArray(new FlussiFile[0]);
  }

  public FlussiFile[] scaricaRitInfCaricamento(ScaricaRitInfCaricamentoRequest request) {
    ScaricaRitInfCaricamentoResponse resp = client.scaricaRitInfCaricamento(request);
    if (resp == null || resp.getReturnValues() == null) return null;
    return resp.getReturnValues().toArray(new FlussiFile[0]);
  }

  public FlussiFile[] ritornoInformativoCaricamento(RitornoInformativoCaricamentoRequest request) {
    RitornoInformativoCaricamentoResponse resp = client.ritornoInformativoCaricamento(request);
    if (resp == null || resp.getReturnValues() == null) return null;
    return resp.getReturnValues().toArray(new FlussiFile[0]);
  }

  public Esito inviaESimula(InviaESimulaRequest request) {
    InviaESimulaResponse resp = client.inviaESimula(request);
    return resp != null ? resp.getReturnValue() : null;
  }
}
