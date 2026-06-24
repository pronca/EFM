package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "preparaRitInfCaricamentoResponse", namespace = "http://ws.sis.eng.it/")
public class PreparaRitInfCaricamentoResponse {

    @XmlElement(name = "return")
    private List<BigDecimal> values;

    /** JAXB / uso normale */
    public List<BigDecimal> getValues() {
        if (values == null) values = new ArrayList<>();
        return values;
    }

    public void setValues(List<BigDecimal> values) {
        this.values = values;
    }

    /** Helper per chi vuole BigDecimal[] (stile Axis) */
    public BigDecimal[] toArray() {
        return getValues().toArray(new BigDecimal[0]);
    }

    /* =========================
       Backward compatibility
       se nel codice hai già getReturnValues()
       ========================= */
    public List<BigDecimal> getReturnValues() {
        return getValues();
    }

    public void setReturnValues(List<BigDecimal> returnValues) {
        setValues(returnValues);
    }
}
