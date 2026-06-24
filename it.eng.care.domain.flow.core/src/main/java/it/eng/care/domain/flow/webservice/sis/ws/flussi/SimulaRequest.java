package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import jakarta.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "simula", propOrder = { "token", "prgInvio" })
@XmlRootElement(name = "simula", namespace = "http://ws.sis.eng.it/")
public class SimulaRequest {

    @XmlElement(name = "token") // unqualified
    private String token;

    @XmlElement(name = "prgInvio") // unqualified
    private BigDecimal prgInvio;

    public SimulaRequest() {}

    public SimulaRequest(String token, BigDecimal prgInvio) {
        this.token = token;
        this.prgInvio = prgInvio;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public BigDecimal getPrgInvio() { return prgInvio; }
    public void setPrgInvio(BigDecimal prgInvio) { this.prgInvio = prgInvio; }
}
