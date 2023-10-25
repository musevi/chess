package DataAccess;
import java.util.List;

/**
 * DAO for Game
 */
public class GameDAO {

    /**
     * A method for inserting a new game into the database
     *
     * @param game      game to be inserted
     * @throws DataAccessException  exception thrown
     */
    void Insert(chess.ChessGame game) throws DataAccessException {}

    /**
     * A method for retrieving a specified game from the database by gameID
     *
     * @param gameID    ID for game to be retrieved
     * @return          returns game by ID
     * @throws DataAccessException  exception thrown
     */
    chess.ChessGame Find (String gameID) throws DataAccessException {return null;}

    /**
     * A method for retrieving all games from the database
     *
     * @return      returns list of games
     * @throws DataAccessException  exception thrown
     */
    List<chess.ChessGame> FindAll() throws DataAccessException {return null;}

    /**
     * A method/methods for claiming a spot in the game. The player's username is provided and should be saved as
     * either the whitePlayer or blackPlayer in the database
     * @param username  user claiming a spot
     * @throws DataAccessException  exception thrown
     */
    void ClaimSpot(String username) throws DataAccessException {}

    /**
     * A method for updating a chessGame in the database. It should replace the chessGame string corresponding to a
     * given gameID with a new chessGame string
     * @param gameID    ID of game to be updated
     * @throws DataAccessException  exception thrown
     */
    void UpdateGame(String gameID) throws DataAccessException {}

    /**
     * A method for removing a game from the database
     * @param gameID    ID of game to be removed
     * @throws DataAccessException  exception thrown
     */
    void Remove(String gameID) throws DataAccessException {}

    /**
     * A method for clearing all data from the database
     * @throws DataAccessException  exception thrown
     */
    void Clear() throws DataAccessException {}

}
