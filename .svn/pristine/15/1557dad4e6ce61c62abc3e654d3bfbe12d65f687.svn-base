package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "login", namespace = "http://ws.sis.eng.it/")
public class LoginRequest {

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
