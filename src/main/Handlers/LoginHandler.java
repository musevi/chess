package Handlers;
import Requests.LoginRequest;
import Results.LoginResult;
import Services.LoginService;
import spark.*;
import com.google.gson.Gson;

public class LoginHandler extends BaseHandler {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LoginService service = new LoginService();
        LoginResult result = service.login(getBody(request, LoginRequest.class));
        if(result.getMessage() != null && result.getMessage().equals("Error: unauthorized")) {response.status(401);}
        if(result.getMessage() != null && result.getMessage().equals("Error: internal server error")) {response.status(500);}
        return new Gson().toJson(result);
    }

}
