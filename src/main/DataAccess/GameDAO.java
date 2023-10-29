package DataAccess;
import Models.Game;
import Models.User;
import chess.ChessGame;

import java.util.HashMap;
import java.util.List;

/**
 * DAO for Game
 */
public class GameDAO {

    private static HashMap<Integer, Game> games;
    private int count;
    private static GameDAO single_instance = null;
    private GameDAO() {
        games = new HashMap<>();
        count = 0;
    }
    public static synchronized GameDAO getInstance() {
        if(single_instance == null) {
            single_instance = new GameDAO();
        }
        return single_instance;
    }

    /**
     * A method for inserting a new game into the database
     *
     * @throws DataAccessException  exception thrown
     */
    public int Insert() throws DataAccessException {
        Game newGame = new Game();
        count++;
        games.put(count, newGame);
        return count;
    }

    /**
     * A method for retrieving a specified game from the database by gameID
     *
     * @param gameID    ID for game to be retrieved
     * @return          returns game by ID
     * @throws DataAccessException  exception thrown
     */
    public ChessGame Find (String gameID) throws DataAccessException {return null;}

    /**
     * A method for retrieving all games from the database
     *
     * @return      returns list of games
     * @throws DataAccessException  exception thrown
     */
    public List<ChessGame> FindAll() throws DataAccessException {return null;}

    /**
     * A method/methods for claiming a spot in the game. The player's username is provided and should be saved as
     * either the whitePlayer or blackPlayer in the database
     * @param username  user claiming a spot
     * @throws DataAccessException  exception thrown
     */
    public void ClaimSpot(String username) throws DataAccessException {}

    /**
     * A method for updating a chessGame in the database. It should replace the chessGame string corresponding to a
     * given gameID with a new chessGame string
     * @param gameID    ID of game to be updated
     * @throws DataAccessException  exception thrown
     */
    public void UpdateGame(String gameID) throws DataAccessException {}

    /**
     * A method for removing a game from the database
     * @param gameID    ID of game to be removed
     * @throws DataAccessException  exception thrown
     */
    public void Remove(String gameID) throws DataAccessException {}

    /**
     * A method for clearing all data from the database
     * @throws DataAccessException  exception thrown
     */
    public void Clear() throws DataAccessException {}

    public void clearAll() {
        games.clear();
    }

}
