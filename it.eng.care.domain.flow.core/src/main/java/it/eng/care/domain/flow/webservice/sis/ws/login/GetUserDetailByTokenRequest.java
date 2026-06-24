package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getUserDetailByToken", namespace = "http://ws.sis.eng.it/")
public class GetUserDetailByTokenRequest {

    @XmlElement(required = true)
    private String token;

    public GetUserDetailByTokenRequest() {}

    public GetUserDetailByTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}
