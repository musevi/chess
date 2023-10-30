package Results;

import Models.Game;

import java.util.List;

/**
 * Result for ListGames, which gives a list of all games
 */
public class ListGamesResult {

    private List<Game> games;

    /**
     * message string
     */
    private String message;

    public ListGamesResult(List<Game> games) {
        this.games = games;
    }

    /**
     * Constructor for ListGames
     *
     * @param message   message for action
     */
    public ListGamesResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
