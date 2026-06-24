/**
 * Esito.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.care.domain.flow.webservice.sis.ws.flussi;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Esito
 *
 * Versione migrata da Axis 1.4 a JAXB Jakarta
 * Compatibile con Java 17 + Spring Boot 3.x
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "esito",
    propOrder = {
        "codMessaggio",
        "codStato",
        "descMessaggio",
        "descStato"
    }
)
@XmlRootElement(name = "esito", namespace = "http://ws.sis.eng.it/")
public class Esito implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "codMessaggio")
    private String codMessaggio;

    @XmlElement(name = "codStato")
    private String codStato;

    @XmlElement(name = "descMessaggio")
    private String descMessaggio;

    @XmlElement(name = "descStato")
    private String descStato;

    // JAXB richiede costruttore vuoto
    public Esito() {
    }

    public Esito(
            String codMessaggio,
            String codStato,
            String descMessaggio,
            String descStato) {
        this.codMessaggio = codMessaggio;
        this.codStato = codStato;
        this.descMessaggio = descMessaggio;
        this.descStato = descStato;
    }

    public String getCodMessaggio() {
        return codMessaggio;
    }

    public void setCodMessaggio(String codMessaggio) {
        this.codMessaggio = codMessaggio;
    }

    public String getCodStato() {
        return codStato;
    }

    public void setCodStato(String codStato) {
        this.codStato = codStato;
    }

    public String getDescMessaggio() {
        return descMessaggio;
    }

    public void setDescMessaggio(String descMessaggio) {
        this.descMessaggio = descMessaggio;
    }

    public String getDescStato() {
        return descStato;
    }

    public void setDescStato(String descStato) {
        this.descStato = descStato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Esito)) return false;

        Esito other = (Esito) o;

        return java.util.Objects.equals(codMessaggio, other.codMessaggio)
            && java.util.Objects.equals(codStato, other.codStato)
            && java.util.Objects.equals(descMessaggio, other.descMessaggio)
            && java.util.Objects.equals(descStato, other.descStato);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
            codMessaggio,
            codStato,
            descMessaggio,
            descStato
        );
    }

    @Override
    public String toString() {
        return "Esito{" +
                "codMessaggio='" + codMessaggio + '\'' +
                ", codStato='" + codStato + '\'' +
                ", descMessaggio='" + descMessaggio + '\'' +
                ", descStato='" + descStato + '\'' +
                '}';
    }
}
