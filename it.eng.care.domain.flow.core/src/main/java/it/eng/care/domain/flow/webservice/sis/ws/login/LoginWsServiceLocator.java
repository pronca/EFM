package it.eng.care.domain.flow.webservice.sis.ws.login;

public class LoginWsServiceLocator {

    private final LoginWsServiceClient client;

    public LoginWsServiceLocator(LoginWsServiceClient client) {
        this.client = client;
    }

    /* =========================
       API PUBBLICA (stile Axis)
       ========================= */

    public String login(String username, String password) {
        LoginResponse resp = client.login(new LoginRequest(username, password));
        return resp != null ? resp.getToken() : null;
    }

    public void logout(String token) {
        // response vuota (logoutResponse) -> puoi ignorarla
        client.logout(new LogoutRequest(token));
    }

    public void autorizzato(String token, String url) {
        // response vuota (autorizzatoResponse) -> puoi ignorarla
        client.autorizzato(new AutorizzatoRequest(token, url));
    }

    public String getUsernameByToken(String token) {
        GetUsernameByTokenResponse resp =
                client.getUsernameByToken(new GetUsernameByTokenRequest(token));
        return resp != null ? resp.getUsername() : null;
    }

    public UserDetail getUserDetailByToken(String token) {
        GetUserDetailByTokenResponse resp =
                client.getUserDetailByToken(new GetUserDetailByTokenRequest(token));
        return resp != null ? resp.getUserDetail() : null;
    }
}
