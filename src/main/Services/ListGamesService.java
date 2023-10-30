package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.GameDAO;
import Results.JoinGameResult;
import Results.ListGamesResult;
import Models.Game;

import java.util.List;

/**
 * Service for ListGames, which gives a list of all games
 */
public class ListGamesService {

    /**
     * returns result, no request body
     *
     * @return      Returns ListGamesResult
     */
    public ListGamesResult listGames(String authToken) {
        try {
            AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
            authTokenDAO.getAuthToken(authToken);

            GameDAO gameDAO = GameDAO.getInstance();
            List<Game> games = gameDAO.findAll();

            return new ListGamesResult(games);
        } catch(Exception e) {
            if(e.getMessage().equals("token not found")) {
                return new ListGamesResult("Error: unauthorized");
            }
            return new ListGamesResult("Error: internal server error");
        }
    }
}
