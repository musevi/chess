package DataAccess;
import Models.AuthToken;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * DAO for authToken
 */
public class AuthTokenDAO {
    private Database database = new Database();
    private static AuthTokenDAO single_instance = null;
    private AuthTokenDAO() {}
    public static synchronized AuthTokenDAO getInstance() {
        if(single_instance == null) {
            single_instance = new AuthTokenDAO();
        }
        return single_instance;
    }


    /**
     * Creates an authToken
     *
     * @param authToken     authToken to create
     */
    public void addAuthToken(AuthToken authToken) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("INSERT INTO Authtokens (authToken, username) VALUES(?, ?)")) {
            preparedStatement.setString(1, authToken.getAuthToken());
            preparedStatement.setString(2, authToken.getUsername());

            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * Reads and returns authToken from username
     * @param authToken  user's authtoken string
     * @return          user's authToken
     */
    public AuthToken getAuthToken(String authToken) throws DataAccessException {
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM AuthTokens WHERE authToken=?")) {//delete authtokne?
            preparedStatement.setString(1, authToken);
            var rs = preparedStatement.executeQuery();
            if(rs.next()) {
                var token = rs.getString("authToken");
                var username = rs.getString("username");
                return new AuthToken(token, username);
            }
            throw new DataAccessException("token not found");
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    /**
     * Changes a user's authToken
     *
     * @param username  user's username
     */
    public void changeAuthToken(String username) throws DataAccessException {}

    /**
     * Deletes a user's authToken
     * @param authToken  user's authToken string
     */
    public void deleteAuthToken(String authToken) throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("DELETE FROM AuthTokens WHERE authToken=?")) {
            preparedStatement.setString(1, authToken);
            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }

    public void clearAll() throws DataAccessException {
        Connection conn = database.getConnection();
        try(var preparedStatement = conn.prepareStatement("DELETE FROM AuthTokens")) {
            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            throw new DataAccessException(x.toString());
        } finally {
            database.closeConnection(conn);
        }
    }
}
