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
    private Integer gameID;

    /**
     * message string
     */
    private String message;

    public CreateGameResult(String message) {
        this.message = message;
        gameID = null;
    }

    /**
     * Constructor for CreateGameResult
     *
     * @param gameID    new game ID
     */
    public CreateGameResult(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameID() {
        return gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
