package DataAccess;
import Models.AuthToken;
import Models.Game;
import chess.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * DAO for Game
 */
public class GameDAO {
    private Database database = new Database();
    private static GameDAO single_instance = null;
    private GameDAO() {}
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
    public int Insert(String gameName) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("INSERT INTO Games (gameName, game, whiteUsername, blackUsername) VALUES(?, ?, ?, ?)", RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, gameName);

            ChessGame chessGame = new ChessGameImpl();
            var json = new Gson().toJson(chessGame);
            preparedStatement.setString(2, json);

            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var ID = 0;
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
            return ID;
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * A method for retrieving a specified game from the database by gameID
     *
     * @param gameID    ID for game to be retrieved
     * @return          returns game by ID
     * @throws DataAccessException  exception thrown
     */
    public Game find (int gameID) throws DataAccessException {
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM Games WHERE id=?")) {
            preparedStatement.setInt(1, gameID);
            var rs = preparedStatement.executeQuery();
            rs.next();
            String white = rs.getString("whiteUsername");
            String black = rs.getString("blackUsername");
            String name = rs.getString("gameName");

            var json = rs.getString("game");
            var builder = new GsonBuilder();
            builder.registerTypeAdapter(ChessGame.class, new ChessGameAdapter());
            builder.registerTypeAdapter(ChessBoard.class, new ChessBoardAdapter());
            builder.registerTypeAdapter(ChessPiece.class, new ChessPieceAdapter());

            var chessGame = builder.create().fromJson(json, ChessGameImpl.class);

            Game game = new Game(gameID, name, white, black, chessGame);

            return game;
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * A method for retrieving all games from the database
     *
     * @return      returns list of games
     * @throws DataAccessException  exception thrown
     */
    public List<Game> findAll() throws DataAccessException {
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM Games")) {
            var rs = preparedStatement.executeQuery();
            List<Game> gameList = new ArrayList<>();
            while(rs.next()) {
                int gameID = rs.getInt("id");
                String white = rs.getString("whiteUsername");
                String black = rs.getString("blackUsername");
                String name = rs.getString("gameName");

                var json = rs.getString("game");
                var builder = new GsonBuilder();
                builder.registerTypeAdapter(ChessGame.class, new ChessGameAdapter());
                builder.registerTypeAdapter(ChessBoard.class, new ChessBoardAdapter());
                builder.registerTypeAdapter(ChessPiece.class, new ChessPieceAdapter());

                var chessGame = builder.create().fromJson(json, ChessGameImpl.class);

                Game game = new Game(gameID, name, white, black, chessGame);
                gameList.add(game);
            }

            return gameList;
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * A method/methods for claiming a spot in the game. The player's username is provided and should be saved as
     * either the whitePlayer or blackPlayer in the database
     * @param username  user claiming a spot
     * @throws DataAccessException  exception thrown
     */
    public void claimSpot(String username, Game game, String color) throws DataAccessException {
        if (color == null) {
            //game.addObserver(username);
        } else if (color.equals("WHITE")) {
            if (game.getWhiteUsername() != null) {
                throw new DataAccessException("already taken");
            }
            Connection conn = database.getConnection();
            try(var preparedStatement = conn.prepareStatement("UPDATE Games SET whiteUsername=? WHERE id=?")) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, game.getGameID());
                preparedStatement.executeUpdate();
            } catch (SQLException x) {
                throw new DataAccessException(x.toString());
            } finally {
                database.closeConnection(conn);
            }
        } else if (color.equals("BLACK")) {
            if (game.getBlackUsername() != null) {
                throw new DataAccessException("already taken");
            }
            Connection conn = database.getConnection();
            try(var preparedStatement = conn.prepareStatement("UPDATE Games SET blackUsername=? WHERE id=?")) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, game.getGameID());
                preparedStatement.executeUpdate();
            } catch (SQLException x) {
                throw new DataAccessException(x.toString());
            } finally {
                database.closeConnection(conn);
            }
        }
    }

    /**
     * A method for updating a chessGame in the database. It should replace the chessGame string corresponding to a
     * given gameID with a new chessGame string
     * @param gameID    ID of game to be updated
     * @throws DataAccessException  exception thrown
     */
    public void updateGame(String gameID) throws DataAccessException {}

    /**
     * A method for removing a game from the database
     * @param gameID    ID of game to be removed
     * @throws DataAccessException  exception thrown
     */
    public void remove(String gameID) throws DataAccessException {}

    /**
     * A method for clearing all data from the database
     * @throws DataAccessException  exception thrown
     */
    public void clear() throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("DELETE FROM Games")) {
            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    public boolean gameExists(int gameID) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("SELECT * FROM Games WHERE id=?")) {
            preparedStatement.setInt(1, gameID);
            var rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }
}
