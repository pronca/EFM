package it.eng.care.domain.flow.webservice.sis.ws.login;

import java.io.Serializable;
import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * UserDetailParametersEntry
 *
 * Versione JAXB (OPZIONE B)
 * Sostituisce la versione Axis 1.4
 * Compatibile con Java 17 / Spring Boot 3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "userDetailParametersEntry",
    propOrder = {
        "key",
        "value"
    }
)
public class UserDetailParametersEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "key")
    private String key;

    /**
     * Nel WSDL originale è un array di stringhe
     * <value>
     *   <values>...</values>
     * </value>
     */
    @XmlElement(name = "value")
    private String[] value;

    // JAXB richiede costruttore vuoto
    public UserDetailParametersEntry() {
    }

    public UserDetailParametersEntry(String key, String[] value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailParametersEntry)) return false;
        UserDetailParametersEntry that = (UserDetailParametersEntry) o;
        return key != null ? key.equals(that.key) : that.key == null
                && Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "UserDetailParametersEntry{" +
                "key='" + key + '\'' +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
