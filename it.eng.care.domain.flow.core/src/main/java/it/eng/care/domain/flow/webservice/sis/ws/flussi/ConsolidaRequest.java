package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="consolida", propOrder={"token","prgInvio","note"})
@XmlRootElement(name = "consolida", namespace = "http://ws.sis.eng.it/")
public class ConsolidaRequest {

    private String token;
    private BigDecimal prgInvio;
    private String note;

    public ConsolidaRequest() {}

    public ConsolidaRequest(String token, BigDecimal prgInvio, String note) {
        this.token = token;
        this.prgInvio = prgInvio;
        this.note = note;
    }
}

