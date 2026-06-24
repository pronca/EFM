package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "logout", namespace = "http://ws.sis.eng.it/")
public class LogoutRequest {

    @XmlElement(required = true)
    private String token;

    public LogoutRequest() {}

    public LogoutRequest(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}

