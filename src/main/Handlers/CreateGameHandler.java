package Handlers;

import Requests.CreateGameRequest;
import Requests.LoginRequest;
import Results.CreateGameResult;
import Results.LoginResult;
import Services.CreateGameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class CreateGameHandler extends BaseHandler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("Authorization");
        CreateGameService createGameService = new CreateGameService();
        CreateGameResult createGameResult = createGameService.createGame(getBody(request, CreateGameRequest.class), authToken);

        if(createGameResult.getMessage() != null && createGameResult.getMessage().equals("Error: unauthorized")) {response.status(401);}

        return new Gson().toJson(createGameResult);
    }
}
