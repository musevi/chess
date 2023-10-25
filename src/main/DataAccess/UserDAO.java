package DataAccess;

/**
 * User DAO
 */
public class UserDAO {
    /**
     * Creates a user
     *
     * @param u     user
     * @throws DataAccessException
     */
    void CreateUser(Models.User u) throws DataAccessException {}

    /**
     * Reads username and password and returns a user
     *
     * @param username  user's username
     * @param password  user's password
     * @return
     * @throws DataAccessException
     */
    Models.User ReadUser(String username, String password) throws DataAccessException {return null;}

    /**
     * Deletes a user
     *
     * @param u     user
     * @throws DataAccessException
     */
    void DeleteUser(Models.User u) throws DataAccessException {}
}
