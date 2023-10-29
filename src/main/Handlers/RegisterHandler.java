package Handlers;

import Requests.RegisterRequest;
import Results.RegisterResult;
import Services.RegisterService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class RegisterHandler extends BaseHandler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        RegisterService service = new RegisterService();
        RegisterResult result = service.register(getBody(request, RegisterRequest.class));

        if(result.getMessage() != null && result.getMessage().equals("Error: already taken")) {response.status(403);}
        if(result.getMessage() != null && result.getMessage().equals("Error: bad request")) {response.status(400);}
        if(result.getMessage() != null && result.getMessage().equals("Error: internal server error")) {response.status(500);}

        return new Gson().toJson(result);
    }
}
