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
    private Integer gameID;

    /**
     * Constructor for JoinGameRequest
     *
     * @param playerColor   WHITE or BLACK
     * @param gameID        ID of game
     */
    public JoinGameRequest(String playerColor, Integer gameID) {
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
