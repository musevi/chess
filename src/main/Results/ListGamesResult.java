package Results;

/**
 * Result for ListGames, which gives a list of all games
 */
public class ListGamesResult {

    /**
     * message string
     */
    private String message;

    /**
     * Constructor for ListGames
     *
     * @param message   message for action
     */
    public ListGamesResult(String message) {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
