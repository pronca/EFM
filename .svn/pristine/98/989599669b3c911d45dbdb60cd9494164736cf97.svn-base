package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "elimina", namespace = "http://ws.sis.eng.it/")
public class EliminaRequest {

    @XmlElement(name = "token")
    private String token;

    @XmlElement(name = "prgInvio")
    private BigDecimal prgInvio;

    public EliminaRequest() {}

    public EliminaRequest(String token, BigDecimal prgInvio) {
        this.token = token;
        this.prgInvio = prgInvio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getPrgInvio() {
        return prgInvio;
    }

    public void setPrgInvio(BigDecimal prgInvio) {
        this.prgInvio = prgInvio;
    }
}
