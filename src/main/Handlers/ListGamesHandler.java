package Handlers;

import Results.ListGamesResult;
import Services.ListGamesService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ListGamesHandler extends BaseHandler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("Authorization");
        ListGamesService listGamesService = new ListGamesService();
        ListGamesResult listGamesResult = listGamesService.listGames(authToken);

        if(listGamesResult.getMessage() != null && listGamesResult.getMessage().equals("Error: unauthorized")) {response.status(401);}
        if(listGamesResult.getMessage() != null && listGamesResult.getMessage().equals("Error: internal server error")) {response.status(500);}

        return new Gson().toJson(listGamesResult);
    }
}
