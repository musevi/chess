package DataAccess;

import Models.AuthToken;
import Models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * User DAO
 */
public class UserDAO {

    private Database database = new Database();
    private static UserDAO single_instance = null;
    private UserDAO() {}
    public static synchronized UserDAO getInstance() {
        if(single_instance == null) {
            single_instance = new UserDAO();
        }
        return single_instance;
    }


    /**
     * Creates a user
     *
     * @param u     user
     * @throws DataAccessException
     */
    public void createUser(User u) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("INSERT INTO Users (username, password, email) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * Reads username and password and returns a user
     *
     * @param username  user's username
     * @param password  user's password
     * @return
     * @throws DataAccessException
     */
    public User getUser(String username, String password) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("SELECT * FROM Users WHERE username=? AND password=?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            var rs = preparedStatement.executeQuery();
            if(rs.next()) {
                var usrname = rs.getString("username");
                var pssword = rs.getString("password");
                var eml = rs.getString("email");
                return new User(usrname, pssword, eml);
            }
            throw new DataAccessException("user not in database");
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * Checks if a user is already in the database
     *
     * @param username  username to check if exists
     * @throws DataAccessException
     */
    public void checkUserExists(String username) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("SELECT * FROM Users WHERE username=?")) {
            preparedStatement.setString(1, username);
            var rs = preparedStatement.executeQuery();
            if(rs.next()) {
                throw new DataAccessException("user already exists");
            }
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * Deletes a user
     *
     * @param u     user
     * @throws DataAccessException
     */
    public void deleteUser(User u) throws DataAccessException {}

    public void clearAll() throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("DELETE FROM Users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

}
