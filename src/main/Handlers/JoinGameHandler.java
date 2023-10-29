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
        JoinGameService joinGameService = new JoinGameService();
        JoinGameResult joinGameResult = joinGameService.joinGame(getBody(request, JoinGameRequest.class));

        return new Gson().toJson(joinGameResult);
    }
}
