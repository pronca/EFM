package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import jakarta.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ritornoInformativoSimulazione",
    propOrder = { "token", "prgInvio", "codTipoFile" }
)
@XmlRootElement(name = "ritornoInformativoSimulazione", namespace = "http://ws.sis.eng.it/")
public class RitornoInformativoSimulazioneRequest {

    @XmlElement(name = "token") // unqualified
    private String token;

    @XmlElement(name = "prgInvio") // unqualified
    private BigDecimal prgInvio;

    @XmlElement(name = "codTipoFile") // unqualified (può essere null)
    private String codTipoFile;

    public RitornoInformativoSimulazioneRequest() {}

    public RitornoInformativoSimulazioneRequest(String token, BigDecimal prgInvio, String codTipoFile) {
        this.token = token;
        this.prgInvio = prgInvio;
        this.codTipoFile = codTipoFile;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public BigDecimal getPrgInvio() { return prgInvio; }
    public void setPrgInvio(BigDecimal prgInvio) { this.prgInvio = prgInvio; }

    public String getCodTipoFile() { return codTipoFile; }
    public void setCodTipoFile(String codTipoFile) { this.codTipoFile = codTipoFile; }
}
