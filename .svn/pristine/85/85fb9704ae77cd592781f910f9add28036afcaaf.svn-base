package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ritornoInformativoCaricamento", namespace = "http://ws.sis.eng.it/")
public class RitornoInformativoCaricamentoRequest {

    private String token;
    private String codTipoFlusso;
    private int anno;
    private int numInvio;
    private String codTipoGruppo;
    private String codFormatoFile;

    public RitornoInformativoCaricamentoRequest() {}

    public RitornoInformativoCaricamentoRequest(
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

