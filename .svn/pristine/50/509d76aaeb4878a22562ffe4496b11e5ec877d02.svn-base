package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "scaricaRitInfCaricamento", namespace = "http://ws.sis.eng.it/")
public class ScaricaRitInfCaricamentoRequest {

    private String token;

    @XmlElement(name = "prgFile")
    private BigDecimal[] prgFile;

    public ScaricaRitInfCaricamentoRequest() {}

    public ScaricaRitInfCaricamentoRequest(String token, BigDecimal[] prgFile) {
        this.token = token;
        this.prgFile = prgFile;
    }
}

