package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getUsernameByToken", namespace = "http://ws.sis.eng.it/")
public class GetUsernameByTokenRequest {

    @XmlElement(required = true)
    private String token;

    public GetUsernameByTokenRequest() {}

    public GetUsernameByTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}
