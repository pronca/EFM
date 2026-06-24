package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="preparaRitInfCaricamento", propOrder={"token","codTipoFlusso","anno","numInvio","codTipoGruppo","codFormatoFile"})
@XmlRootElement(name = "preparaRitInfCaricamento", namespace = "http://ws.sis.eng.it/")
public class PreparaRitInfCaricamentoRequest {

    private String token;
    private String codTipoFlusso;
    private int anno;
    private int numInvio;
    private String codTipoGruppo;
    private String codFormatoFile;

    public PreparaRitInfCaricamentoRequest() {}

    public PreparaRitInfCaricamentoRequest(
            String token,
            String codTipoFlusso,
            int anno,
            int numInvio,
            String codTipoGruppo,
            String codFormatoFile) {

        this.token = token;
        this.codTipoFlusso = codTipoFlusso;
        this.anno = anno;
        this.numInvio = numInvio;
        this.codTipoGruppo = codTipoGruppo;
        this.codFormatoFile = codFormatoFile;
    }
}
