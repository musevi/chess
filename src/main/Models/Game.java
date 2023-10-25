package Models;

/**
 * A game to be played and spectated by users
 */
public class Game {

    /**
     * unique ID for a game
     */
    private int gameID;

    /**
     * username for white player
     */
    private String whiteUsername;

    /**
     * username for black player
     */
    private String blackUsername;

    /**
     * name of game
     */
    private String gameName;

    /**
     * game of chess being played
     */
    private chess.ChessGame game;

    /**
     * Constructor for Game
     *
     * @param whiteUsername     username for first player
     * @param blackUsername     username for second player
     * @param gameName          name of game
     * @param game              game to be played
     */
    public Game(String whiteUsername, String blackUsername, String gameName, chess.ChessGame game) {}
}
