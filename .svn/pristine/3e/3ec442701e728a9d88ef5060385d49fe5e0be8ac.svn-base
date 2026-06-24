package it.eng.care.domain.flow.webservice.sis.ws.login;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "autorizzato", namespace = "http://ws.sis.eng.it/")
public class AutorizzatoRequest {

    @XmlElement
    private String token;

    @XmlElement
    private String url;

    public AutorizzatoRequest() {}

    public AutorizzatoRequest(String token, String url) {
        this.token = token;
        this.url = url;
    }

    public String getToken() { return token; }
    public String getUrl() { return url; }
}
