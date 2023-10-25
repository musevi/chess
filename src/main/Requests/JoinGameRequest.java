package Requests;

/**
 * Request for JoinGame, which verifies the game exists and adds caller as player or spectator
 */
public class JoinGameRequest {

    /**
     * player color string
     */
    private String playerColor;

    /**
     * game ID string
     */
    private String gameID;

    /**
     * authToken for request;
     */
    private Models.AuthToken authToken;

    /**
     * Constructor for JoinGameRequest
     *
     * @param playerColor   WHITE or BLACK
     * @param gameID        ID of game
     */
    public JoinGameRequest(String playerColor, String gameID) {}

    public String getPlayerColor() {
        return playerColor;
    }

    public String getGameID() {
        return gameID;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}