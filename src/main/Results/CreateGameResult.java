package Results;

/**
 * Result for CreateGame, which creates a new game
 */
public class CreateGameResult {

    /**
     * name of game as string
     */
    private String gameName;

    /**
     * game ID string
     */
    private String gameID;

    /**
     * message string
     */
    private String message;

    /**
     * Constructor for CreateGameResult
     *
     * @param gameName  new game name
     * @param gameID    new game ID
     * @param message   message for action
     */
    public CreateGameResult(String gameName, String gameID, String message) {}

    public String getGameName() {
        return gameName;
    }

    public String getGameID() {
        return gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
