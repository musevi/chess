package Models;

import chess.ChessGame;

import java.util.ArrayList;
import java.util.List;

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
    private ChessGame game;

    /**
     * List of people spectating the game
     */
    private List<String> observers;

    public Game() {
        observers = new ArrayList<String>();
    }

    /**
     * Constructor for Game
     *
     * @param gameName          name of game
     */
    public Game(int id, String gameName, String whiteUsername, String blackUsername, ChessGame game) {
        this.gameID = id;
        this.gameName = gameName;
        this.game = game;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }

    public void addObserver(String observerName) {
        observers.add(observerName);
    }
}
