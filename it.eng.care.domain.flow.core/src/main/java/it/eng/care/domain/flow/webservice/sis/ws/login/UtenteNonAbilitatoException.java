package it.eng.care.domain.flow.webservice.sis.ws.login;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * UtenteNonAbilitatoException
 *
 * Versione migrata da Axis 1.4 a JAXB Jakarta
 * Compatibile con Java 17 + Spring WS
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "UtenteNonAbilitatoException",
    propOrder = {
        "codice",
        "message"
    }
)
@XmlRootElement(
    name = "UtenteNonAbilitatoException",
    namespace = "http://ws.sis.eng.it/"
)
public class UtenteNonAbilitatoException
        extends RuntimeException
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "codice")
    private String codice;

    @XmlElement(name = "message")
    private String message;

    // JAXB richiede costruttore vuoto
    public UtenteNonAbilitatoException() {
        super();
    }

    public UtenteNonAbilitatoException(String codice, String message) {
        super(message);
        this.codice = codice;
        this.message = message;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
