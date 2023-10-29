package Requests;

/**
 * Request for CreateGame, which creates a new game
 */
public class CreateGameRequest {

    /**
     * message string
     */
    private String gameName;

    /**
     * Constructor for CreateGameRequest
     *
     * @param gameName  new game name
     */
    public CreateGameRequest(String gameName) {}

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
