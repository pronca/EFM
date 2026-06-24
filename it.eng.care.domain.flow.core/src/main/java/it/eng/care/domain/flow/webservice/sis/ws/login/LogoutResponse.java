package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "logoutResponse", namespace = "http://ws.sis.eng.it/")
public class LogoutResponse {
    // WSDL: logoutResponse ha sequence vuota, quindi nessun campo.
    public LogoutResponse() {}
}
