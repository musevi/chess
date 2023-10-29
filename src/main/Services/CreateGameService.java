package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.GameDAO;
import Requests.CreateGameRequest;
import Results.CreateGameResult;


/**
 * Service for CreateGame, which creates a new game
 */
public class CreateGameService {

    /**
     * Returns result based on request
     *
     * @param request   CreateGameRequest
     * @return          Returns CreateGameResult
     */
    public CreateGameResult createGame(CreateGameRequest request, String authToken) {
        try {

            AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
            authTokenDAO.getAuthToken(authToken);

            GameDAO gameDAO = GameDAO.getInstance();
            int gameID = gameDAO.Insert();

            return new CreateGameResult(gameID);
        } catch(Exception e) {
            if(e.getMessage().equals("token not found")) {
                return new CreateGameResult("Error: unauthorized");
            }
            return new CreateGameResult("Error: internal server error");
        }
    }
}
