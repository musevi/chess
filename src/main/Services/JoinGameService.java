package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.GameDAO;
import DataAccess.UserDAO;
import Models.Game;
import Requests.JoinGameRequest;
import Results.CreateGameResult;
import Results.JoinGameResult;
import chess.ChessGame;

/**
 * Service for JoinGame, which verifies the game exists and adds caller as player or spectator
 */
public class JoinGameService {

    /**
     * returns result based on request
     *
     * @param request   JoinGameRequest
     * @return          Returns JoinGameResult
     *
     */
    public JoinGameResult joinGame(JoinGameRequest request, String authToken) {
        try {

            AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
            String playerUsername = authTokenDAO.getAuthToken(authToken).getUsername();


            GameDAO gameDAO = GameDAO.getInstance();
            if(request.getGameID() == null || !gameDAO.gameExists(request.getGameID())){
                throw new Exception("invalid input");
            }

            Game game = gameDAO.find(request.getGameID());

            gameDAO.claimSpot(playerUsername, game, request.getPlayerColor());

            return new JoinGameResult();
        } catch(Exception e) {
            System.out.println(e.toString());
            if(e.getMessage().equals("token not found")) {
                return new JoinGameResult("Error: unauthorized");
            }
            if(e.getMessage().equals("already taken")) {
                return new JoinGameResult("Error: already taken");
            }
            if(e.getMessage().equals("invalid input")) {
                return new JoinGameResult("Error: bad request");
            }
            return new JoinGameResult("Error: internal server error");
        }
    }
}
