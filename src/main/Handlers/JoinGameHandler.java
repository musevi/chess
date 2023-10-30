package Handlers;

import Requests.JoinGameRequest;
import Results.JoinGameResult;
import Services.JoinGameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class JoinGameHandler extends BaseHandler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("Authorization");
        JoinGameService joinGameService = new JoinGameService();
        JoinGameResult joinGameResult = joinGameService.joinGame(getBody(request, JoinGameRequest.class), authToken);

        if(joinGameResult.getMessage() != null && joinGameResult.getMessage().equals("Error: unauthorized")) {response.status(401);}
        if(joinGameResult.getMessage() != null && joinGameResult.getMessage().equals("Error: bad request")) {response.status(400);}
        if(joinGameResult.getMessage() != null && joinGameResult.getMessage().equals("Error: already taken")) {response.status(403);}
        if(joinGameResult.getMessage() != null && joinGameResult.getMessage().equals("Error: internal server error")) {response.status(500);}

        return new Gson().toJson(joinGameResult);
    }
}
