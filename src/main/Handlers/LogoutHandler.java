package Handlers;

import Results.LogoutResult;
import Services.LogoutService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class LogoutHandler extends BaseHandler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("Authorization");
        LogoutService logoutService = new LogoutService();
        LogoutResult result = logoutService.logout(authToken);

        if(result.getMessage() != null && result.getMessage().equals("Error: unauthorized")) {response.status(401);}
        if(result.getMessage() != null && result.getMessage().equals("Error: internal server error")) {response.status(500);}

        return new Gson().toJson(result);
    }
}
