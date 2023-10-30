package Results;

/**
 *  Result for JoinGame, which verifies the game exists and adds caller as player or spectator
 */
public class JoinGameResult {

    /**
     * player color string
     */
    private String playerColor;

    /**
     * game ID string
     */
    private String gameID;

    /**
     * message string
     */
    private String message;

    public JoinGameResult() {}

    /**
     * Constructor for JoinGameResult
     *
     * @param message       message for action
     */
    public JoinGameResult(String message) {
        this.message = message;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getGameID() {
        return gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
