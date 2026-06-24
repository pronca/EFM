package it.eng.care.domain.flow.webservice.sis.ws.login;

import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * LoginWsServiceClient
 *
 * OPZIONE B – sostituisce LoginWsServiceSoapBindingStub (Axis)
 * Compatibile con Java 17 + Spring Boot 3
 */
public class LoginWsServiceClient {	

	  private final WebServiceTemplate webServiceTemplate;
	  private final String baseEndpoint;

	  public LoginWsServiceClient(WebServiceTemplate webServiceTemplate, String baseEndpoint) {
	    if (webServiceTemplate == null) throw new IllegalArgumentException("webServiceTemplate is null");
	    if (baseEndpoint == null || baseEndpoint.isBlank()) {
	      throw new IllegalArgumentException("flow.region.address is null/blank");
	    }
	    this.webServiceTemplate = webServiceTemplate;
	    this.baseEndpoint = baseEndpoint;
	  }

	  private String endpoint() {
	    String e = baseEndpoint.trim();
	    if (e.endsWith("/")) e = e.substring(0, e.length() - 1);

	    String lower = e.toLowerCase();
	    // se già è completo lo uso così
	    if (lower.contains("/flussi/loginws")) return e;

	    // altrimenti compongo come faceva Axis
	    return e + "/flussi/LoginWs";
	  }

	  public LoginResponse login(LoginRequest request) {
	    return (LoginResponse) webServiceTemplate.marshalSendAndReceive(endpoint(), request);
	  }

	  public LogoutResponse logout(LogoutRequest request) {
	    return (LogoutResponse) webServiceTemplate.marshalSendAndReceive(endpoint(), request);
	  }

	  public AutorizzatoResponse autorizzato(AutorizzatoRequest request) {
	    return (AutorizzatoResponse) webServiceTemplate.marshalSendAndReceive(endpoint(), request);
	  }

	  public GetUsernameByTokenResponse getUsernameByToken(GetUsernameByTokenRequest request) {
	    return (GetUsernameByTokenResponse) webServiceTemplate.marshalSendAndReceive(endpoint(), request);
	  }

	  public GetUserDetailByTokenResponse getUserDetailByToken(GetUserDetailByTokenRequest request) {
	    return (GetUserDetailByTokenResponse) webServiceTemplate.marshalSendAndReceive(endpoint(), request);
	  }
	}